package solver;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Solution {
	
	private final List<Cell> sortedSolution;
	
	public Solution(List<Cell> solution) {
		LinkedList<Cell> sortedSolution = new LinkedList<Cell>(solution);
		Collections.sort(sortedSolution);
		this.sortedSolution = sortedSolution;
	}
	
	public List<Cell> getSolution() {
		return sortedSolution;
	}
	
	public boolean equals(Object o) {
		if (o instanceof Solution) {
			Solution otherSolution = (Solution)o;
			return sortedSolution.equals(otherSolution.sortedSolution);
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return sortedSolution.hashCode();
	}
	
	public String toString() {
		
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
		
		Iterator<Cell> iter = sortedSolution.iterator();
		Cell cell = iter.next();
		for (int row = Cell.MIN_POS; row <= Cell.MAX_POS; row++) {
			formatter.addNewRow();
			for (int col = Cell.MIN_POS; col <= Cell.MAX_POS; col++) {
				if (cell!=null && cell.getCol()==col && cell.getRow()==row) {
					formatter.addValue(cell.getValue());
					cell = iter.hasNext() ? iter.next() : null;
				} else {
					formatter.addEmpty();
				}
			}
		}
		formatter.addFinalRow();
		return formatter.toString();
	}
}
