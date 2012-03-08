package sudoku_solver;

public class Cell {
	
	private static final int MIN_POS = 0;
	private static final int MAX_POS = 8;
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
}
