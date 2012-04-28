package model;

import java.util.HashSet;
import java.util.Set;


public abstract class Sample implements Game {
	
	public abstract String[] getUnsolvedGameAsStringArray();
	
	public abstract String[] getSolutionAsStringArray();
	
	public Set<Cell> getUnsolvedGame() {
		return convertArrayOfStringToCellSet(getUnsolvedGameAsStringArray());
	}

	public Solution getSolution() {
		Set<Cell> list = convertArrayOfStringToCellSet(getSolutionAsStringArray());
		return new Solution(list);
	}
	
	private static Set<Cell> convertArrayOfStringToCellSet(String[] array) {
		Set<Cell> set = new HashSet<Cell>();
		for (int row = Cell.MIN_POS; row <= Cell.MAX_POS; row++) {
			for (int col = Cell.MIN_POS; col <= Cell.MAX_POS; col++) {
				int value = Character.getNumericValue(array[row].charAt(col));
				if (value != -1) {
					set.add(new Cell(col, row, value));
				}
			}
		}
		return set;
	}
	
}
