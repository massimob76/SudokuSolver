package model;

public class Cell implements Comparable<Cell> {
	
	public static final int MIN_POS = 0;
	public static final int MAX_POS = 8;
	private static final int MIN_VALUE = 1;
	private static final int MAX_VALUE = 9;
	public static final int NO_OF_CELLS = 81;
	
	@SuppressWarnings("serial")
	public class OutOfBoundsValueException extends IllegalArgumentException {
		
		public OutOfBoundsValueException(String msg) {
			super(msg);
		}
	}

	private final int row, col, square;
	private final Integer value;
	
	public Cell(int col, int row) {
		this(col, row, null);
	}
	
	public Cell(int col, int row, Integer value) {
		verifyPosition(col);
		verifyPosition(row);
		verifyValue(value);
		this.row = row;
		this.col = col;
		this.square = (row / 3) * 3 + col / 3;
		this.value = value;
	}
	
	private Cell(int col, int row, int square, Integer value) {
		this.col = col;
		this.row = row;
		this.square = square;
		this.value = value;
	}
	
	public Cell clone() {
		return new Cell(this.col, this.row, this.square, this.value);
	}
	
	public Cell cloneWithoutValue() {
		return new Cell(this.col, this.row, this.square, null);
	}
	
	public Cell cloneSettingValue(Integer value) {
		verifyValue(value);
		return new Cell(this.col, this.row, this.square, value);
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
	
	public int getSquare() {
		return square;
	}
	
	public Integer getValue() {
		return value;
	}
	
	public String toString() {
		return "cell: (" + col + "," + row + ") value: " + value;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Cell) {
			Cell otherCell = (Cell)o;
			if ((otherCell.col == this.col) && (otherCell.row == this.row)) {
				if (otherCell.value == null) {
					return this.value == null;
				} else {
					return otherCell.value.equals(this.value);
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return row * (MAX_POS + 1) + col;
	}
	
	private void verifyPosition(int pos) {
		if (pos < MIN_POS || pos > MAX_POS) {
			throw new OutOfBoundsValueException("out of bound position: " + pos);
		}
	}

	private void verifyValue(Integer value) {
		if (value != null && (value < MIN_VALUE || value > MAX_VALUE)) {
			throw new OutOfBoundsValueException("out of bound value: " + value);
		}
	}

	@Override
	public int compareTo(Cell otherCell) {
		int rowDiff = row - otherCell.row;
		if (rowDiff!=0) {
			return rowDiff;
		} else {
			int colDiff = col - otherCell.col;
			if (colDiff!=0) {
				return colDiff;
			} else {
				return value - otherCell.value;
			}
		}
	}
}
