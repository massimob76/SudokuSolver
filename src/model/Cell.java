package model;

public class Cell implements Comparable<Cell> {
	
	public static final int MIN_POS = 0;
	public static final int MAX_POS = 8;
	private static final int MIN_VALUE = 1;
	private static final int MAX_VALUE = 9;
	
	@SuppressWarnings("serial")
	public class OutOfBoundsValue extends RuntimeException {
		
		public OutOfBoundsValue(String msg) {
			super(msg);
		}
	}

	private final int row, col, square;
	private int value;
	
	public Cell(int col, int row) {
		verifyPosition(col);
		verifyPosition(row);
		this.row = row;
		this.col = col;
		this.square = (row / 3) * 3 + col / 3;
	}
	
	public Cell(int col, int row, int value) {
		this(col, row);
		setValue(value);
	}
	
	private Cell(int col, int row, int square, int value) {
		this.col = col;
		this.row = row;
		this.square = square;
		this.value = value;
	}
	
	public Cell clone() {
		return new Cell(this.col, this.row, this.square, this.value);
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
	
	public void setValue(int value) {
		verifyValue(value);
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
	public String toString() {
		return "cell: (" + col + "," + row + ") value: " + value;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Cell) {
			Cell otherCell = (Cell)o;
			return (otherCell.col == this.col) && (otherCell.row == this.row);
		} else {
			return false;
		}
	}
	
	private void verifyPosition(int pos) {
		if (pos < MIN_POS || pos > MAX_POS) {
			throw new OutOfBoundsValue("out of bound position: " + pos);
		}
	}
	
	private void verifyValue(int value) {
		if (value < MIN_VALUE || value > MAX_VALUE) {
			throw new OutOfBoundsValue("out of bound value: " + value);
		}
	}

	@Override
	public int compareTo(Cell otherCell) {
		int rowDiff = row - otherCell.row;
		if (rowDiff!=0) {
			return rowDiff;
		} else {
			return col - otherCell.col;
		}
	}
}
