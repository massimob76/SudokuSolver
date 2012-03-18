package userInteraction;

import java.util.LinkedList;
import java.util.List;

import model.Cell;
import model.Solution;


public abstract class Game {
	
	public abstract String[] getUnsolvedGameAsStringArray();
	
	public abstract String[] getSolutionAsStringArray();
	
	public List<Cell> getUnsolvedGame() {
		return convertArrayOfStringToCellList(getUnsolvedGameAsStringArray());
	}

	public Solution getSolution() {
		List<Cell> list = convertArrayOfStringToCellList(getSolutionAsStringArray());
		return new Solution(list);
	}
	
	private static List<Cell> convertArrayOfStringToCellList(String[] array) {
		List<Cell> list = new LinkedList<Cell>();
		for (int row = Cell.MIN_POS; row <= Cell.MAX_POS; row++) {
			for (int col = Cell.MIN_POS; col <= Cell.MAX_POS; col++) {
				int value = Character.getNumericValue(array[row].charAt(col));
				if (value != -1) {
					list.add(new Cell(col, row, value));
				}
			}
		}
		return list;
	}
	
}
