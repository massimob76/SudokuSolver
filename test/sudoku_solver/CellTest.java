package sudoku_solver;

import static org.junit.Assert.*;

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
}
