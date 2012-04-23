package model;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;
import model.Cell;
import org.junit.Before;
import org.junit.Test;

public class GameTest {
	
	private Game iut;
	
	@Before
	public void setUp() {
		iut = new Game();
	}
	
	@Test
	public void testGetUnsolvedGame() {
		Set<Cell> expected = new HashSet<Cell>();
		expected.add(new Cell(1,0,2));
		expected.add(new Cell(3,0,6));
		expected.add(new Cell(5,0,8));
		expected.add(new Cell(0,1,5));
		expected.add(new Cell(1,1,8));
		expected.add(new Cell(5,1,9));
		expected.add(new Cell(6,1,7));
		iut.addCell(1,0,2);
		iut.addCell(3,0,6);
		iut.addCell(5,0,8);
		iut.addCell(0,1,5);
		iut.addCell(1,1,8);
		iut.addCell(5,1,9);
		iut.addCell(6,1,7);
		Set<Cell> actual = iut.getUnsolvedGame();
		assertEquals(expected, actual);
	}
	
}
