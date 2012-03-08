package sudoku_solver;

import java.util.LinkedList;
import java.util.List;

public class RulesGuardian {
	
	private static final int NINE_BITS_MASK = Integer.parseInt("111111111", 2);
	private int[] v = new int[9];
	private int[] h = new int[9];
	private int[] s = new int[9];
	
	@SuppressWarnings("serial")
	public class IllegalValue extends RuntimeException {
		
		public IllegalValue(Cell cell) {
			super("Illegal value trying to add "
					+ cell.toString() + " "
					+ RulesGuardian.this.toString());
		}
	}
	
	public void addCellValue(Cell cell) {
		int valueInBits = 1 << (cell.getValue() - 1);
		verifyCellValueInBits(valueInBits, cell);
		addCellValueInBits(valueInBits, cell);
	}
	
	private void verifyCellValueInBits(int valueInBits, Cell cell) {
		if (((h[cell.getRow()] | v[cell.getCol()] | s[cell.getSquare()]) & valueInBits) != 0) {
			throw new IllegalValue(cell);
		}
	}

	private void addCellValueInBits(int valueInBits, Cell cell) {
		h[cell.getRow()] |= valueInBits;
		v[cell.getCol()] |= valueInBits;
		s[cell.getSquare()] |= valueInBits;
	}
	
	public int getNumberOfPossibleValuesPerCell(Cell cell) {
		System.out.println(Integer.toBinaryString(getPossibleValueInBitsPerCell(cell)));
		return Integer.bitCount(getPossibleValueInBitsPerCell(cell));
	}
	
	public List<Integer> getPossibleValuesPerCell(Cell cell) {
		int valuesInBits = getPossibleValueInBitsPerCell(cell);
		List<Integer> possibleValues = new LinkedList<Integer>();
		int index = 1;
		for (int value = 1; value <= 9; value++) {
			if ((valuesInBits & index) != 0) {
				possibleValues.add(value);
			}
			index <<= 1;
		}
		return possibleValues;
	}
	
	private int getPossibleValueInBitsPerCell(Cell cell) {
		return ~(h[cell.getRow()] | v[cell.getCol()] | s[cell.getSquare()]) & NINE_BITS_MASK;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("indexes: ");
		sb.append(" vertical: " + indexesToString(v));
		sb.append(" horizontal: " + indexesToString(h));
		sb.append(" square: " + indexesToString(s));
		return sb.toString();
	}
	
	private String indexesToString(int[] indexes) {
		StringBuilder sb = new StringBuilder();
		for (int index: indexes) {
			sb.append(Integer.toBinaryString(index));
			sb.append(", ");
		}
		return sb.toString();
	}
}
