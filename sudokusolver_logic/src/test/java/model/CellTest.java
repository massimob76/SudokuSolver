package model;

import static org.junit.Assert.*;

import java.util.SortedSet;
import java.util.TreeSet;

import model.Cell;
import model.Cell.OutOfBoundsValueException;

import org.junit.Test;

public class CellTest {
	
	@Test
	public void cellObjectIdentifiesTheRightRowColumnAndSquare() {
		assertCellValues(1, 0, 0, 2);
		assertCellValues(8, 3, 5, 7);
	}
	
	private void assertCellValues(int col, int row, int square, Integer value) {
		Cell cell = new Cell(col, row, value);
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
		} catch (Cell.OutOfBoundsValueException e) {
		}
	}
	
	private void tryOutOfBoundsValues(int col, int row, int value) {
		try {
			new Cell(col, row, value);
			fail();
		} catch (Cell.OutOfBoundsValueException e) {
		}
	}
	
	@Test
	public void distinctCellsShouldNotBeConsideredEqual() {
		Cell cellA = new Cell(1,5,7);
		Cell cellB = new Cell(3,5);
		assertFalse(cellA.equals(cellB));
	}
	
	@Test
	public void cloneShouldReturnACompletelyIndependentCopyOfTheCell() {
		int expectedCol = 1;
		int expectedRow = 5;
		int expectedSquare = 3;
		Integer expectedValue = 7;
		Cell cellA = new Cell(expectedCol, expectedRow, expectedValue);
		Cell cellB = cellA.clone();
		
		assertEquals(expectedCol, cellA.getCol());
		assertEquals(expectedRow, cellA.getRow());
		assertEquals(expectedSquare, cellA.getSquare());
		assertEquals(expectedValue, cellA.getValue());
		
		assertEquals(expectedCol, cellB.getCol());
		assertEquals(expectedRow, cellB.getRow());
		assertEquals(expectedSquare, cellB.getSquare());
		assertEquals(expectedValue, cellB.getValue());
	}
	
	@Test
	public void cloneWithoutValueShouldReturnACloneOfTheCellWithNoValue() {
		Cell cell = new Cell(1,2,3);
		Cell expected = new Cell(1,2);
		Cell actual = cell.cloneWithoutValue();
		assertEquals(expected, actual);
	}
	
	@Test
	public void cloneSettingValueShouldCloneTheCellAndResetTheValue() {
		Cell cell = new Cell(1,2,3);
		int expectedValue = 4;
		Cell expected = new Cell(1,2,4);
		Cell actual = cell.cloneSettingValue(expectedValue);
		assertEquals(expected, actual);
	}
	
	@Test
	public void cloneSettingValueShouldCheckForTheValidityOfTheValue() {
		Cell cell = new Cell(1,2,3);
		int outOfBoundValue = 10;
		try {
			cell.cloneSettingValue(outOfBoundValue);
			fail();
		} catch(OutOfBoundsValueException e) {
			String expectedMsg = "out of bound value: 10";
			String actualMsg = e.getMessage();
			assertEquals(expectedMsg, actualMsg);
		}
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
