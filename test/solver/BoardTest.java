package solver;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;

import model.Cell;
import model.Solution;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import samples.Impossible;
import samples.VeryEasyNoNeedOfMultipleThreads;
import solver.Board;
import userInteraction.Game;

import static org.mockito.Mockito.*;

public class BoardTest {
	
	private Board iut;
	private CompletionService<Solution> execMock;
	
	@SuppressWarnings("unchecked")
	@Before
	public void setUp() {
		execMock = mock(CompletionService.class);
		iut = new Board(execMock);
	}
	
	@Test
	public void softCopyShouldReturnASoftCopyOfACellSet() {
		Set<Cell> setA = new HashSet<Cell>();
		setA.add(new Cell(0,4));
		setA.add(new Cell(3,5));
		Set<Cell> setB = Board.softCopy(setA);
		assertEquals(setA, setB);
		setA.add(new Cell(2,2,1));
		assertTrue(setA.contains(new Cell(2,2,1)));
		assertFalse(setB.contains(new Cell(2,2,1)));
	}
	
	@Test
	public void hardCopyShouldReturnAHardCopyOfACellSet() {
		Cell aCell = new Cell(0,4);
		Set<Cell> setA = new HashSet<Cell>();
		setA.add(aCell);
		setA.add(new Cell(3,5));
		Set<Cell> setB = Board.hardCopy(setA);
		assertEquals(setA, setB);
		setA.add(new Cell(2,2,1));
		assertTrue(setA.contains(new Cell(2,2,1)));
		assertFalse(setB.contains(new Cell(2,2,1)));
		int expectedValue = 7;
		aCell.setValue(expectedValue);
		verifySetContainsCellWithValue(setA, aCell, expectedValue);
		expectedValue = 0;
		verifySetContainsCellWithValue(setB, aCell, expectedValue);
	}
	
	private void verifySetContainsCellWithValue(Set<Cell> set, Cell expectedCell, int expectedValue) {
		for (Cell cell: set) {
			if (cell.equals(expectedCell)) {
				assertEquals(expectedValue, cell.getValue());
				return;
			}
		}
		fail();
	}

	@Test
	public void cloneShouldReturnAnIndependentCopyOfTheBoard() {
		Cell cellA = new Cell(1, 2, 4);
		iut.addSolvedCell(cellA);
		Board cloned = iut.clone();
		Cell cellB = new Cell(1, 1, 5);
		iut.addSolvedCell(cellB);
		Cell cellC = new Cell(1, 3);
		assertTrue(iut.getSolvedCells().contains(cellA));
		assertFalse(iut.getMissingCells().contains(cellA));
		assertTrue(iut.getSolvedCells().contains(cellB));
		assertFalse(iut.getMissingCells().contains(cellB));
		assertEquals(7, iut.getRulesGuardian().getNumberOfPossibleValuesPerCell(cellC));

		assertTrue(cloned.getSolvedCells().contains(cellA));
		assertFalse(cloned.getMissingCells().contains(cellA));
		assertFalse(cloned.getSolvedCells().contains(cellB));
		assertTrue(cloned.getMissingCells().contains(cellB));
		assertEquals(8, cloned.getRulesGuardian().getNumberOfPossibleValuesPerCell(cellC));
	}
	
	@Test
	public void addSolvedCellShouldAddTheCellToTheSolvedCellListAndRemoveItFromTheMissingCellList() {
		for (int col = Cell.MIN_POS; col <= Cell.MAX_POS; col++) {
			for (int row = Cell.MAX_POS; row >= Cell.MIN_POS; row--) {
				Cell cell = getCellFromSudokuDummyTemplate(col, row);
				assertFalse(iut.isGameSolved());
				iut.addSolvedCell(cell);
				assertTrue(iut.getSolvedCells().contains(cell));
				assertFalse(iut.getMissingCells().contains(cell));
			}
		}
		assertTrue(iut.isGameSolved());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testDelegatePossibleOptionsToOtherThreadsAndRetainOnlyOneOption() {
		iut.addSolvedCell(new Cell(1,0,1));
		iut.addSolvedCell(new Cell(0,1,2));
		iut.addSolvedCell(new Cell(2,2,3));
		iut.addSolvedCell(new Cell(3,1,4));
		iut.addSolvedCell(new Cell(1,3,5));
		
		Cell cellWithNoValueYet = new Cell(1,1);
		
		when(execMock.submit((Callable<Solution>)anyObject())).thenReturn(null);

		Cell firstOptionCell = iut.delegatePossibleOptionsToOtherThreadsAndRetainOnlyOneOption(cellWithNoValueYet);
		assertEquals(cellWithNoValueYet, firstOptionCell);
		assertEquals(6, firstOptionCell.getValue());
		
		ArgumentCaptor<Board> argument = ArgumentCaptor.forClass(Board.class);
		int[] expectedValues = new int[]{7, 8, 9};
		verify(execMock, times(expectedValues.length)).submit(argument.capture());
		
		verifyBoardContainsCellWithValue(argument.getAllValues(), cellWithNoValueYet, expectedValues);
		
	}

	private void verifyBoardContainsCellWithValue(List<Board> boards, Cell expectedCell, int[] expectedValues) {
		for (int i = 0; i < boards.size(); i++) {
			Set<Cell> solvedCells = boards.get(i).getSolvedCells();
			for (Cell actualCell: solvedCells) {
				if (actualCell.equals(expectedCell)) {
					assertEquals(expectedValues[i], actualCell.getValue());
				}
			}
		}
	}
	
	@Test
	public void findNextSolvedCellDetectsWhenThereAreNoOptions() {
		iut.addSolvedCell(new Cell(2,0,1));
		iut.addSolvedCell(new Cell(0,1,2));
		iut.addSolvedCell(new Cell(1,1,3));
		iut.addSolvedCell(new Cell(2,1,4));
		iut.addSolvedCell(new Cell(0,2,5));
		iut.addSolvedCell(new Cell(1,2,6));
		iut.addSolvedCell(new Cell(2,2,7));
		iut.addSolvedCell(new Cell(1,3,8));
		iut.addSolvedCell(new Cell(1,4,9));
		Cell actualCell = iut.findNextSolvedCell();
		assertNull(actualCell);
	}
	
	@Test
	public void findNextSolvedCellDetectsWhenThereIsOnlyOneOption() {
		iut.addSolvedCell(new Cell(2,0,1));
		iut.addSolvedCell(new Cell(0,1,2));
		iut.addSolvedCell(new Cell(1,1,3));
		iut.addSolvedCell(new Cell(2,1,4));
		iut.addSolvedCell(new Cell(0,2,5));
		iut.addSolvedCell(new Cell(1,2,6));
		iut.addSolvedCell(new Cell(2,2,7));
		iut.addSolvedCell(new Cell(1,3,8));
		Cell expectedCell = new Cell(1,0,9);
		Cell actualCell = iut.findNextSolvedCell();
		assertEquals(expectedCell, actualCell);
		assertEquals(expectedCell.getValue(), actualCell.getValue());		
	}
	
	@Test
	public void findNextSolvedCellDetectsWhenThereAreMoreOptionsAndChoosedTheOneWithLessPossibilities() {
		iut.addSolvedCell(new Cell(1,0,1));
		iut.addSolvedCell(new Cell(2,0,2));
		iut.addSolvedCell(new Cell(0,1,3));
		iut.addSolvedCell(new Cell(2,1,4));
		iut.addSolvedCell(new Cell(3,1,7));
		iut.addSolvedCell(new Cell(0,2,5));
		iut.addSolvedCell(new Cell(1,2,6));
		Cell expectedCell = new Cell(1,1,8);
		Cell actualCell = iut.findNextSolvedCell();
		assertEquals(expectedCell, actualCell);
		assertEquals(expectedCell.getValue(), actualCell.getValue());

	}

	private static Cell getCellFromSudokuDummyTemplate(int col, int row) {
		int colInSquare = col % 3;
		int rowInSquare = row % 3;
		Cell cell = new Cell(col, row);
		int value = rowInSquare + colInSquare * 3 + cell.getSquare();
		cell.setValue(value % 9 + 1);
		return cell;
	}
	
	@Test
	public void boardCanSolveASimpleSudokuGame() {
		Game game = new VeryEasyNoNeedOfMultipleThreads();
		Set<Cell> solvedCells = game.getUnsolvedGame();
		iut.addSolvedCells(solvedCells);
		Solution expected = game.getSolution();
		Solution actual = iut.call();
		assertEquals(expected, actual);
	}
	
	@Test
	public void boardCanDetectWhenAGameCannotBeSolved() {
		Game game = new Impossible();
		Set<Cell> solvedCells = game.getUnsolvedGame();
		iut.addSolvedCells(solvedCells);
		Solution actual = iut.call();
		assertNull(actual);
	}
	
}
