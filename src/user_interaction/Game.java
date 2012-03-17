package user_interaction;

import java.util.LinkedList;
import java.util.List;

import solver.Cell;
import solver.Solution;

public abstract class Game {
	
	public abstract String[] getUnsolvedGame();
	
	public abstract String[] getSolution();
	
	public List<Cell> loadUnsolvedGame() {
		return convertArrayOfStringToCellList(getUnsolvedGame());
	}

	public Solution loadSolution() {
		List<Cell> list = convertArrayOfStringToCellList(getSolution());
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
