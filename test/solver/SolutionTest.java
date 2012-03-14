package solver;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class SolutionTest {
	
	private Solution iut;
	
	@Test
	public void solutionsShouldContainSortedListOfCells() {
		List<Cell> list = CellTest.getUnsortedListOfCells();
		iut = new Solution(list);
		List<Cell> expectedList = CellTest.getSortedListofCells();
		List<Cell> actualList = iut.getSolution();
		assertEquals(expectedList, actualList);
	}
	
	@Test
	public void solutionsShouldBeIndependentFromTheListOfCellsWhoHasGeneratedThem() {
		List<Cell> list = CellTest.getUnsortedListOfCells();
		iut = new Solution(list);
		list.remove(0);
		List<Cell> expectedList = CellTest.getSortedListofCells();
		List<Cell> actualList = iut.getSolution();
		assertEquals(expectedList, actualList);
	}
	
	@Test
	public void solutionsSholdBeEquilIfFormedByTheSameCells() {
		iut = new Solution(CellTest.getUnsortedListOfCells());
		Solution otherSolution = new Solution(CellTest.getUnsortedListOfCells());
		assertTrue(iut.equals(otherSolution));
	}
	
	@Test
	public void solutionsShouldBeNotEqualIfFormedByDifferentCells() {
		List<Cell> list = CellTest.getUnsortedListOfCells();
		list.remove(0);
		list.add(new Cell(0,0,1));
		iut = new Solution(list);
		Solution otherSolution = new Solution(CellTest.getUnsortedListOfCells());
		assertFalse(iut.equals(otherSolution));
	}
}