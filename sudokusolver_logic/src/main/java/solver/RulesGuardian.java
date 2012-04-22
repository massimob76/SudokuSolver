package solver;

import java.util.Arrays;
import java.util.LinkedList;

import model.Cell;

public class RulesGuardian {
	
	private static final int NINE_BITS_MASK = Integer.parseInt("111111111", 2);
	private int[] v = new int[9];
	private int[] h = new int[9];
	private int[] s = new int[9];
	
	public RulesGuardian() {
		v = new int[9];
		h = new int[9];
		s = new int[9];
	}
	
	private RulesGuardian(int[] v, int[] h, int[] s) {
		this.v = v;
		this.h = h;
		this.s = s;
	}
	
	public RulesGuardian clone() {
		return new RulesGuardian(Arrays.copyOf(this.v, this.v.length), 
				Arrays.copyOf(this.h, this.h.length), Arrays.copyOf(this.s, this.s.length));
	}
	
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
		return Integer.bitCount(getPossibleValueInBitsPerCell(cell));
	}
	
	public LinkedList<Integer> getPossibleValuesPerCell(Cell cell) {
		int valuesInBits = getPossibleValueInBitsPerCell(cell);
		LinkedList<Integer> possibleValues = new LinkedList<Integer>();
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
