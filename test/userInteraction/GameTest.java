package userInteraction;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import model.Cell;
import model.Solution;

import org.junit.Before;
import org.junit.Test;

import samples.PartialGame;
import userInteraction.Game;


public class GameTest {
	
	private Game iut;
	
	@Before
	public void setUp() {
		iut = new PartialGame();
	}
	
	@Test
			public void testGetUnsolvedGame() {
				List<Cell> expected = new LinkedList<Cell>();
				expected.add(new Cell(1,0,2));
				expected.add(new Cell(3,0,6));
				expected.add(new Cell(5,0,8));
				expected.add(new Cell(0,1,5));
				expected.add(new Cell(1,1,8));
				expected.add(new Cell(5,1,9));
				expected.add(new Cell(6,1,7));
				List<Cell> actual = iut.getUnsolvedGame();
				assertEquals(expected, actual);
			}
	
	@Test
		public void testGetSolution() {
			@SuppressWarnings("serial")
			Solution expected = new Solution(new LinkedList<Cell>() {
				{
					add(new Cell(0, 0, 1));
					add(new Cell(1, 0, 2));
					add(new Cell(2, 0, 3));
					add(new Cell(3, 0, 6));
					add(new Cell(4, 0, 7));
					add(new Cell(5, 0, 8));
					add(new Cell(6, 0, 9));
					add(new Cell(7, 0, 4));
					add(new Cell(8, 0, 5));
					add(new Cell(0, 1, 5));
					add(new Cell(1, 1, 8));
					add(new Cell(2, 1, 4));
					add(new Cell(3, 1, 2));
					add(new Cell(4, 1, 3));
					add(new Cell(5, 1, 9));
					add(new Cell(6, 1, 7));
					add(new Cell(7, 1, 6));
					add(new Cell(8, 1, 1));
				}
			});
			Solution actual = iut.getSolution();
			assertEquals(expected, actual);
		}

}
