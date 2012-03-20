package model;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import model.Cell;
import org.junit.Test;
import samples.PartialGame;


public class SolutionTest {
	
	private Solution iut;
	
	@Test
	public void solutionsShouldContainSortedListOfCells() {
		iut = new Solution(getSetOfCells());
		SortedSet<Cell> expectedList = new TreeSet<Cell>(getSetOfCells());
		SortedSet<Cell> actualList = iut.getSolution();
		assertEquals(expectedList, actualList);
	}
	
	@Test
	public void solutionsShouldBeIndependentFromTheListOfCellsWhoHasGeneratedThem() {
		Set<Cell> set = getSetOfCells();
		iut = new Solution(set);
		SortedSet<Cell> expectedList = new TreeSet<Cell>(set);
		set.add(new Cell(1,1,1));
		SortedSet<Cell> actualList = iut.getSolution();
		assertEquals(expectedList, actualList);
	}
	
	@Test
	public void solutionsSholdBeEqualIfFormedByTheSameCells() {
		iut = new Solution(getSetOfCells());
		Solution otherSolution = new Solution(getSetOfCells());
		assertTrue(iut.equals(otherSolution));
	}
	
	@Test
	public void solutionsShouldBeNotEqualIfFormedByDifferentCells() {
		Set<Cell> set = getSetOfCells();
		iut = new Solution(set);
		set.add(new Cell(0,0,1));
		Solution otherSolution = new Solution(set);
		assertFalse(iut.equals(otherSolution));
	}
	
	@Test
	public void toStringCreatesANicelyFormattedOutputString() {
		Set<Cell> game = new PartialGame().getUnsolvedGame();
		iut = new Solution(game);
		String expected = 
			"\n+-+-+-+-+-+-+-+-+-+" +
			"\n| |2| |6| |8| | | |" +
			"\n+-+-+-+-+-+-+-+-+-+" +
			"\n|5|8| | | |9|7| | |" +
			"\n+-+-+-+-+-+-+-+-+-+" +
			"\n| | | | | | | | | |" +
			"\n+-+-+-+-+-+-+-+-+-+" +
			"\n| | | | | | | | | |" +
			"\n+-+-+-+-+-+-+-+-+-+" +
			"\n| | | | | | | | | |" +
			"\n+-+-+-+-+-+-+-+-+-+" +
			"\n| | | | | | | | | |" +
			"\n+-+-+-+-+-+-+-+-+-+" +
			"\n| | | | | | | | | |" +
			"\n+-+-+-+-+-+-+-+-+-+" +
			"\n| | | | | | | | | |" +
			"\n+-+-+-+-+-+-+-+-+-+" +
			"\n| | | | | | | | | |" +
			"\n+-+-+-+-+-+-+-+-+-+" +
			"\n";
		String actual = iut.toString();
		assertEquals(expected, actual);
	}
	
	private Set<Cell> getSetOfCells() {
		Set<Cell> set = new HashSet<Cell>();
		set.add(new Cell(1,2,3));
		set.add(new Cell(4,3,7));
		set.add(new Cell(4,4,4));
		set.add(new Cell(2,5,1));
		set.add(new Cell(4,5,8));
		set.add(new Cell(3,6,3));
		return set;
	}
}
