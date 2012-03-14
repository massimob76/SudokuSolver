package user_interaction;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import solver.Cell;

public class Utilities {
	
	public static List<Cell> loadGame(Sample sample) {
		return convertArrayOfStringToCellList(sample.getGame());
	}

	public static List<Cell> loadSolution(Sample sample) {
		return convertArrayOfStringToCellList(sample.getSolution());
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
	
	public static String getNicelyFormattedOutputFromCellList(List<Cell> list) {
		
		class Formatter {
			
			private final StringBuilder sb = new StringBuilder();
			
			private void addNewRow() {
				sb.append("\n+-+-+-+-+-+-+-+-+-+\n");
				sb.append("|");
			}
			
			private void addFinalRow() {
				sb.append("\n+-+-+-+-+-+-+-+-+-+\n");
			}
			
			private void addEmpty() {
				sb.append(" |");
			}
			
			private void addValue(int value) {
				sb.append(Integer.toString(value) + "|");
			}
			
			public String toString() {
				return sb.toString();
			}
		}
		
		Formatter formatter = new Formatter();
		
		LinkedList<Cell> sortedList = new LinkedList<Cell>(list);
		Collections.sort(sortedList);
		Cell head = sortedList.pop();
		for (int row = Cell.MIN_POS; row <= Cell.MAX_POS; row++) {
			formatter.addNewRow();
			for (int col = Cell.MIN_POS; col <= Cell.MAX_POS; col++) {
				if (head!=null && head.getCol()==col && head.getRow()==row) {
					formatter.addValue(head.getValue());
					head = sortedList.poll();
				} else {
					formatter.addEmpty();
				}
			}
		}
		formatter.addFinalRow();
		return formatter.toString();
	}

}
