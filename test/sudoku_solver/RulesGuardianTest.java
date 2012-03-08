package sudoku_solver;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class RulesGuardianTest {
	
	private RulesGuardian iut;
	
	@Before
	public void setUp() {
		iut = new RulesGuardian();
	}
	
	@Test
	public void addCellValueShouldSucceedIfThatValueIsAPossibleValue() {
		Cell cell = new Cell(0, 1);
		cell.setValue(2);
		iut.addCellValue(cell);
	}
	
	@Test
	public void addCellValueShouldThrowAnExceptionIfThatValueIsNotAcceptableAccordinglyToSudokuRules() {
		Cell cell = new Cell(0, 1);
		cell.setValue(2);
		iut.addCellValue(cell);
		try {
			iut.addCellValue(cell);
			fail();
		} catch(RulesGuardian.IllegalValue e) {
			String expected = "sudoku_solver.RulesGuardian$IllegalValue: Illegal value trying to add "
				+ "cell: (0,1) value: 2 "
				+ "indexes:  vertical: 10, 0, 0, 0, 0, 0, 0, 0, 0,  horizontal: 0, 10, 0, 0, 0, 0, 0, 0, 0,  square: 10, 0, 0, 0, 0, 0, 0, 0, 0, ";
			assertEquals(expected, e.toString());
		}
	}
	
	@Test
	public void getPossibleValuesPerCellReturnsTheRightPossibilities() {
		iut.addCellValue(new Cell(1,0,1));
		iut.addCellValue(new Cell(0,1,2));
		iut.addCellValue(new Cell(2,2,3));
		iut.addCellValue(new Cell(3,1,4));
		iut.addCellValue(new Cell(1,3,5));
		List<Integer> expected = new LinkedList<Integer>();
		expected.add(6);
		expected.add(7);
		expected.add(8);
		expected.add(9);
		List<Integer> actual = iut.getPossibleValuesPerCell(new Cell(1,1));
		assertEquals(expected, actual);
	}
	
	@Test
	public void getNumberOfPossibleValuesPerCellReturnsTheRightNumberOfPossibilities() {
		iut.addCellValue(new Cell(1,0,1));
		iut.addCellValue(new Cell(0,1,2));
		iut.addCellValue(new Cell(2,2,3));
		iut.addCellValue(new Cell(3,1,4));
		iut.addCellValue(new Cell(1,3,5));
		int expected = 4;
		assertEquals(expected, iut.getNumberOfPossibleValuesPerCell(new Cell(1,1)));
	}
	
	@Test
	public void toStringShouldReturnANicelyFormattedString() {
		iut.addCellValue(new Cell(1,0,1));
		iut.addCellValue(new Cell(0,1,2));
		iut.addCellValue(new Cell(2,2,3));
		iut.addCellValue(new Cell(3,1,4));
		iut.addCellValue(new Cell(1,3,5));
		String expected = "indexes:  "
			+ "vertical: 10, 10001, 100, 1000, 0, 0, 0, 0, 0,  "
			+ "horizontal: 1, 1010, 100, 10000, 0, 0, 0, 0, 0,  "
			+ "square: 111, 1000, 0, 10000, 0, 0, 0, 0, 0, ";
		assertEquals(expected, iut.toString());
	}
	
}
