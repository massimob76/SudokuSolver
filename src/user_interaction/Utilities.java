package user_interaction;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import solver.Cell;

public class Utilities {
	
	public static String getNicelyFormattedOutputFromSortedCellList(List<Cell> list) {
		
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
