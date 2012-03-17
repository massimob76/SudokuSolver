package user_interaction;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import samples.SampleTest;
import solver.Cell;

public class UtilitiesTest {
	
	@Test
	public void testLoadGame() {
		List<Cell> expected = new LinkedList<Cell>();
		expected.add(new Cell(1,0,2));
		expected.add(new Cell(3,0,6));
		expected.add(new Cell(5,0,8));
		expected.add(new Cell(0,1,5));
		expected.add(new Cell(1,1,8));
		expected.add(new Cell(5,1,9));
		expected.add(new Cell(6,1,7));
		List<Cell> actual = Utilities.loadGame(new SampleTest());
		assertEquals(expected, actual);
	}
	
	@Test
	public void testLoadSolution() {
		List<Cell> expected = new LinkedList<Cell>();
		expected.add(new Cell(0,0,1));
		expected.add(new Cell(1,0,2));
		expected.add(new Cell(2,0,3));
		expected.add(new Cell(3,0,6));
		expected.add(new Cell(4,0,7));
		expected.add(new Cell(5,0,8));
		expected.add(new Cell(6,0,9));
		expected.add(new Cell(7,0,4));
		expected.add(new Cell(8,0,5));
		expected.add(new Cell(0,1,5));
		expected.add(new Cell(1,1,8));
		expected.add(new Cell(2,1,4));
		expected.add(new Cell(3,1,2));
		expected.add(new Cell(4,1,3));
		expected.add(new Cell(5,1,9));
		expected.add(new Cell(6,1,7));
		expected.add(new Cell(7,1,6));
		expected.add(new Cell(8,1,1));
		List<Cell> actual = Utilities.loadSolution(new SampleTest());
		assertEquals(expected, actual);
	}
	
	@Test
	public void getNicelyFormattedOutputFromCellListTest() {
		List<Cell> game = Utilities.loadGame(new SampleTest());
		String actual = Utilities.getNicelyFormattedOutputFromSortedCellList(game);
		System.out.println(actual);
	}
	
}
