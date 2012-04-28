package userInteraction;

import static org.junit.Assert.*;

import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ExecutionException;

import model.Cell;
import model.Sample;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import samples.Easy1;

public class WebInteractionImplTest {
	
	private WebInteractionImpl iut;
	private Set<Cell> unsolvedGameMock;
	
	@Before
	public void setUp() {
		iut = new WebInteractionImpl();
	}
	
	@Test
	public void addCellOfUnsolvedGameTest() {
		unsolvedGameMock = new TreeSet<Cell>();
		ReflectionTestUtils.setField(iut, "unsolvedGame", unsolvedGameMock);
		assertTrue(iut.addCellOfUnsolvedGame(1, 9, "1"));
		assertTrue(iut.addCellOfUnsolvedGame(9, 1, "9"));
		assertTrue(iut.addCellOfUnsolvedGame(4, 5, "5"));
		assertFalse(iut.addCellOfUnsolvedGame(0, 1, "1"));
		assertFalse(iut.addCellOfUnsolvedGame(10, 1, "1"));
		assertFalse(iut.addCellOfUnsolvedGame(1, 0, "1"));
		assertFalse(iut.addCellOfUnsolvedGame(1, 10, "1"));
		assertFalse(iut.addCellOfUnsolvedGame(1, 1, "a"));
		assertFalse(iut.addCellOfUnsolvedGame(1, 1, "10"));
		assertFalse(iut.addCellOfUnsolvedGame(1, 1, "0"));
		Set<Cell> expected = new TreeSet<Cell>();
		expected.add(new Cell(0,8,1));
		expected.add(new Cell(8,0,9));
		expected.add(new Cell(3,4,5));
		Set<Cell> actual = unsolvedGameMock;
		assertEquals(expected, actual);
	}
	
	@Test
	public void flowTest() throws InterruptedException, ExecutionException {
		Sample sample = new Easy1();
		unsolvedGameMock = sample.getUnsolvedGame();
		ReflectionTestUtils.setField(iut, "unsolvedGame", unsolvedGameMock);
		iut.solveIt();
		for (Cell cell: sample.getSolution().getSolution()) {
			int expectedValue = cell.getValue();
			int actualValue = iut.getNextCellValueFromSolvedGame();
			assertEquals(expectedValue, actualValue);
		}
		
		try {
			iut.getNextCellValueFromSolvedGame();
			fail();
		} catch(WebInteractionImpl.NoMoreCellsException e) {
			// ignored
		}
		
	}

}
