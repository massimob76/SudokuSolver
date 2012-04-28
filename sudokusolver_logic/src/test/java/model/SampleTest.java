package model;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import model.Cell;
import model.Sample;
import model.Solution;

import org.junit.Before;
import org.junit.Test;

import samples.Crazy;


public class SampleTest {
	
	private Sample iut;
	
	@Before
	public void setUp() {
		iut = new Crazy();
	}
	
	@Test
	public void testGetUnsolvedGame() {
		Set<Cell> expected = new HashSet<Cell>();
		expected.add(new Cell(1,0,2));
		expected.add(new Cell(3,1,6));
		expected.add(new Cell(8,1,3));
		expected.add(new Cell(1,2,7));
		expected.add(new Cell(2,2,4));
		expected.add(new Cell(4,2,8));
		expected.add(new Cell(5,3,3));
		expected.add(new Cell(8,3,2));
		expected.add(new Cell(1,4,8));
		expected.add(new Cell(4,4,4));
		expected.add(new Cell(7,4,1));
		expected.add(new Cell(0,5,6));
		expected.add(new Cell(3,5,5));
		expected.add(new Cell(4,6,1));
		expected.add(new Cell(6,6,7));
		expected.add(new Cell(7,6,8));
		expected.add(new Cell(0,7,5));
		expected.add(new Cell(5,7,9));
		expected.add(new Cell(7,8,4));
		
		Set<Cell> actual = iut.getUnsolvedGame();
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetSolution() {
		@SuppressWarnings("serial")
		Solution expected = new Solution(new HashSet<Cell>() {
			{
				add(new Cell(0, 0, 1));
				add(new Cell(1, 0, 2));
				add(new Cell(2, 0, 6));
				add(new Cell(3, 0, 4));
				add(new Cell(4, 0, 3));
				add(new Cell(5, 0, 7));
				add(new Cell(6, 0, 9));
				add(new Cell(7, 0, 5));
				add(new Cell(8, 0, 8));
				
				add(new Cell(0, 1, 8));
				add(new Cell(1, 1, 9));
				add(new Cell(2, 1, 5));
				add(new Cell(3, 1, 6));
				add(new Cell(4, 1, 2));
				add(new Cell(5, 1, 1));
				add(new Cell(6, 1, 4));
				add(new Cell(7, 1, 7));
				add(new Cell(8, 1, 3));
				
				add(new Cell(0, 2, 3));
				add(new Cell(1, 2, 7));
				add(new Cell(2, 2, 4));
				add(new Cell(3, 2, 9));
				add(new Cell(4, 2, 8));
				add(new Cell(5, 2, 5));
				add(new Cell(6, 2, 1));
				add(new Cell(7, 2, 2));
				add(new Cell(8, 2, 6));
				
				add(new Cell(0, 3, 4));
				add(new Cell(1, 3, 5));
				add(new Cell(2, 3, 7));
				add(new Cell(3, 3, 1));
				add(new Cell(4, 3, 9));
				add(new Cell(5, 3, 3));
				add(new Cell(6, 3, 8));
				add(new Cell(7, 3, 6));
				add(new Cell(8, 3, 2));
				
				add(new Cell(0, 4, 9));
				add(new Cell(1, 4, 8));
				add(new Cell(2, 4, 3));
				add(new Cell(3, 4, 2));
				add(new Cell(4, 4, 4));
				add(new Cell(5, 4, 6));
				add(new Cell(6, 4, 5));
				add(new Cell(7, 4, 1));
				add(new Cell(8, 4, 7));
				
				add(new Cell(0, 5, 6));
				add(new Cell(1, 5, 1));
				add(new Cell(2, 5, 2));
				add(new Cell(3, 5, 5));
				add(new Cell(4, 5, 7));
				add(new Cell(5, 5, 8));
				add(new Cell(6, 5, 3));
				add(new Cell(7, 5, 9));
				add(new Cell(8, 5, 4));
				
				add(new Cell(0, 6, 2));
				add(new Cell(1, 6, 6));
				add(new Cell(2, 6, 9));
				add(new Cell(3, 6, 3));
				add(new Cell(4, 6, 1));
				add(new Cell(5, 6, 4));
				add(new Cell(6, 6, 7));
				add(new Cell(7, 6, 8));
				add(new Cell(8, 6, 5));
				
				add(new Cell(0, 7, 5));
				add(new Cell(1, 7, 4));
				add(new Cell(2, 7, 8));
				add(new Cell(3, 7, 7));
				add(new Cell(4, 7, 6));
				add(new Cell(5, 7, 9));
				add(new Cell(6, 7, 2));
				add(new Cell(7, 7, 3));
				add(new Cell(8, 7, 1));
				
				add(new Cell(0, 8, 7));
				add(new Cell(1, 8, 3));
				add(new Cell(2, 8, 1));
				add(new Cell(3, 8, 8));
				add(new Cell(4, 8, 5));
				add(new Cell(5, 8, 2));
				add(new Cell(6, 8, 6));
				add(new Cell(7, 8, 4));
				add(new Cell(8, 8, 9));
			}
		});
		Solution actual = iut.getSolution();
		assertEquals(expected, actual);
	}

}
