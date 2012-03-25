package model;

import static org.junit.Assert.*;

import java.util.SortedSet;
import java.util.TreeSet;

import model.Cell;

import org.junit.Test;

public class CellTest {
	
	@Test
	public void cellObjectIdentifiesTheRightRowColumnAndSquare() {
		assertCellValues(1, 0, 0, 2);
		assertCellValues(8, 3, 5, 7);
	}
	
	private void assertCellValues(int col, int row, int square, int value) {
		Cell cell = new Cell(col, row);
		cell.setValue(value);
		assertEquals(row, cell.getRow());
		assertEquals(col, cell.getCol());
		assertEquals(square, cell.getSquare());
		assertEquals(value, cell.getValue());
	}
	
	@Test
	public void cellObjectRejectOutOfBoundsCells() {
		tryOutOfBoundsValues(-1, 0);
		tryOutOfBoundsValues(9, 0);
		tryOutOfBoundsValues(0, -1);
		tryOutOfBoundsValues(0, 9);
		tryOutOfBoundsValues(0, 0, 0);
		tryOutOfBoundsValues(0, 0, 10);
	}
	
	private void tryOutOfBoundsValues(int col, int row) {
		try {
			new Cell(col, row);
			fail();
		} catch (Cell.OutOfBoundsValue e) {
		}
	}
	
	private void tryOutOfBoundsValues(int col, int row, int value) {
		try {
			new Cell(col, row, value);
			fail();
		} catch (Cell.OutOfBoundsValue e) {
		}
	}
	
	@Test
	public void cellsWithSamePositionShouldBeConsideredEqual() {
		Cell cellA = new Cell(3,5,7);
		Cell cellB = new Cell(3,5);
		assertTrue(cellA.equals(cellB));
	}
	
	@Test
	public void cellsWithDistinctPositionShouldBeConsideredNotEqual() {
		Cell cellA = new Cell(1,5,7);
		Cell cellB = new Cell(3,5);
		assertFalse(cellA.equals(cellB));
	}
	
	@Test
	public void cloneShouldReturnACompletelyIndependentCopyOfTheCell() {
		int expectedCol = 1;
		int expectedRow = 5;
		int expectedSquare = 3;
		int expectedValue = 7;
		Cell cellA = new Cell(expectedCol, expectedRow);
		Cell cellB = cellA.clone();
		cellA.setValue(expectedValue);
		
		assertEquals(expectedCol, cellA.getCol());
		assertEquals(expectedRow, cellA.getRow());
		assertEquals(expectedSquare, cellA.getSquare());
		assertEquals(expectedValue, cellA.getValue());
		
		assertEquals(expectedCol, cellB.getCol());
		assertEquals(expectedRow, cellB.getRow());
		assertEquals(expectedSquare, cellB.getSquare());
		assertEquals(0, cellB.getValue());
	}
	
	@Test
	public void itShouldBePossibleToSortACellList() {
		SortedSet<Cell> set = new TreeSet<Cell>();
		set.add(new Cell(2,3,3));
		set.add(new Cell(1,4,5));
		set.add(new Cell(1,3,1));
		assertEquals(new Cell(1,3,1), set.first());
		assertEquals(new Cell(1,4,5), set.last());
	}

}
