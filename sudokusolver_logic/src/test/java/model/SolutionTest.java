package model;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import model.Cell;
import org.junit.Test;


public class SolutionTest {
	
	private Solution iut;
	
	@Test
	public void solutionShouldContainAllTheCellsInASudokuGame() {
		try {
			Set<Cell> set = new HashSet<Cell>();
			set.add(new Cell(1,1,4));
			new Solution(set);
			fail();
		} catch(Solution.IncompleteSolutionException e) {
			String expectedMsg = "Solution contained 1 cells instead of 81; solution: [cell: (1,1) value: 4]";
			String actualMsg = e.getMessage();
			assertEquals(expectedMsg, actualMsg);
		}
			
	}
	
	@Test
	public void solutionsShouldContainSortedListOfCells() {
		iut = new Solution(getCellSet());
		SortedSet<Cell> expectedList = new TreeSet<Cell>(getCellSet());
		SortedSet<Cell> actualList = iut.getSolution();
		assertEquals(expectedList, actualList);
	}
	
	@Test
	public void solutionsShouldBeIndependentFromTheListOfCellsWhoHasGeneratedThem() {
		Set<Cell> set = getCellSet();
		iut = new Solution(set);
		SortedSet<Cell> expectedList = new TreeSet<Cell>(set);
		set.add(new Cell(1,1,1));
		SortedSet<Cell> actualList = iut.getSolution();
		assertEquals(expectedList, actualList);
	}
	
	@Test
	public void solutionsSholdBeEqualIfFormedByTheSameCells() {
		iut = new Solution(getCellSet());
		Solution otherSolution = new Solution(getCellSet());
		assertTrue(iut.equals(otherSolution));
	}
	
	@Test
	public void solutionsShouldBeNotEqualIfFormedByDifferentCells() {
		Set<Cell> set = getCellSet();
		iut = new Solution(set);
		set.remove(new Cell(0,0,4));
		set.add(new Cell(0,0,5));
		Solution otherSolution = new Solution(set);
		assertFalse(iut.equals(otherSolution));
	}
	
	@Test
	public void toStringCreatesANicelyFormattedOutputString() {
		Set<Cell> game = getCellSet();
		iut = new Solution(game);
		String expected = 
			"\n+-+-+-+-+-+-+-+-+-+" +
			"\n|4|7|3|5|9|8|2|6|1|" +
			"\n+-+-+-+-+-+-+-+-+-+" +
			"\n|6|5|2|3|1|4|8|7|9|" +
			"\n+-+-+-+-+-+-+-+-+-+" +
			"\n|8|9|1|6|2|7|4|3|5|" +
			"\n+-+-+-+-+-+-+-+-+-+" +
			"\n|7|6|4|8|5|3|9|1|2|" +
			"\n+-+-+-+-+-+-+-+-+-+" +
			"\n|1|3|9|4|6|2|5|8|7|" +
			"\n+-+-+-+-+-+-+-+-+-+" +
			"\n|5|2|8|9|7|1|6|4|3|" +
			"\n+-+-+-+-+-+-+-+-+-+" +
			"\n|3|4|7|2|8|5|1|9|6|" +
			"\n+-+-+-+-+-+-+-+-+-+" +
			"\n|2|1|6|7|4|9|3|5|8|" +
			"\n+-+-+-+-+-+-+-+-+-+" +
			"\n|9|8|5|1|3|6|7|2|4|" +
			"\n+-+-+-+-+-+-+-+-+-+" +
			"\n";
		String actual = iut.toString();
		assertEquals(expected, actual);
	}
	
	private Set<Cell> getCellSet() {
		Set<Cell> set = new HashSet<Cell>();
		set.add(new Cell(0,0,4));
		set.add(new Cell(1,0,7));
		set.add(new Cell(2,0,3));
		set.add(new Cell(3,0,5));
		set.add(new Cell(4,0,9));
		set.add(new Cell(5,0,8));
		set.add(new Cell(6,0,2));
		set.add(new Cell(7,0,6));
		set.add(new Cell(8,0,1));
		
		set.add(new Cell(0,1,6));
		set.add(new Cell(1,1,5));
		set.add(new Cell(2,1,2));
		set.add(new Cell(3,1,3));
		set.add(new Cell(4,1,1));
		set.add(new Cell(5,1,4));
		set.add(new Cell(6,1,8));
		set.add(new Cell(7,1,7));
		set.add(new Cell(8,1,9));
		
		set.add(new Cell(0,2,8));
		set.add(new Cell(1,2,9));
		set.add(new Cell(2,2,1));
		set.add(new Cell(3,2,6));
		set.add(new Cell(4,2,2));
		set.add(new Cell(5,2,7));
		set.add(new Cell(6,2,4));
		set.add(new Cell(7,2,3));
		set.add(new Cell(8,2,5));
		
		set.add(new Cell(0,3,7));
		set.add(new Cell(1,3,6));
		set.add(new Cell(2,3,4));
		set.add(new Cell(3,3,8));
		set.add(new Cell(4,3,5));
		set.add(new Cell(5,3,3));
		set.add(new Cell(6,3,9));
		set.add(new Cell(7,3,1));
		set.add(new Cell(8,3,2));
		
		set.add(new Cell(0,4,1));
		set.add(new Cell(1,4,3));
		set.add(new Cell(2,4,9));
		set.add(new Cell(3,4,4));
		set.add(new Cell(4,4,6));
		set.add(new Cell(5,4,2));
		set.add(new Cell(6,4,5));
		set.add(new Cell(7,4,8));
		set.add(new Cell(8,4,7));
		
		set.add(new Cell(0,5,5));
		set.add(new Cell(1,5,2));
		set.add(new Cell(2,5,8));
		set.add(new Cell(3,5,9));
		set.add(new Cell(4,5,7));
		set.add(new Cell(5,5,1));
		set.add(new Cell(6,5,6));
		set.add(new Cell(7,5,4));
		set.add(new Cell(8,5,3));
		
		set.add(new Cell(0,6,3));
		set.add(new Cell(1,6,4));
		set.add(new Cell(2,6,7));
		set.add(new Cell(3,6,2));
		set.add(new Cell(4,6,8));
		set.add(new Cell(5,6,5));
		set.add(new Cell(6,6,1));
		set.add(new Cell(7,6,9));
		set.add(new Cell(8,6,6));
		
		set.add(new Cell(0,7,2));
		set.add(new Cell(1,7,1));
		set.add(new Cell(2,7,6));
		set.add(new Cell(3,7,7));
		set.add(new Cell(4,7,4));
		set.add(new Cell(5,7,9));
		set.add(new Cell(6,7,3));
		set.add(new Cell(7,7,5));
		set.add(new Cell(8,7,8));
		
		set.add(new Cell(0,8,9));
		set.add(new Cell(1,8,8));
		set.add(new Cell(2,8,5));
		set.add(new Cell(3,8,1));
		set.add(new Cell(4,8,3));
		set.add(new Cell(5,8,6));
		set.add(new Cell(6,8,7));
		set.add(new Cell(7,8,2));
		set.add(new Cell(8,8,4));
		
		return set;
	}
	
}
