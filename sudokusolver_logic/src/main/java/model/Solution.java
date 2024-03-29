package model;

import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;


public class Solution {
	
	private final SortedSet<Cell> sortedSolution;
	
	public Solution(Set<Cell> solution) {
		if (solution.size() != Cell.NO_OF_CELLS) {
			throw new IncompleteSolutionException("Solution contained " + solution.size() 
					+ " cells instead of " + Cell.NO_OF_CELLS + "; solution: " + solution);
		}
		this.sortedSolution = new TreeSet<Cell>(solution);
	}
	
	@SuppressWarnings("serial")
	static class IncompleteSolutionException extends IllegalArgumentException {
		
		public IncompleteSolutionException(String msg) {
			super(msg);
		}
	}
	
	public SortedSet<Cell> getSolution() {
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
	
	@Override
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
		
		class SolutionIterator {
			
			private Iterator<Cell> iter = sortedSolution.iterator();
			
			public Cell next() {
				if (iter.hasNext()) {
					return iter.next();
				} else {
					return null;
				}
			}
		}
		
		Formatter formatter = new Formatter();
		SolutionIterator iter = new SolutionIterator();
		
		Cell cell = iter.next();
		for (int row = Cell.MIN_POS; row <= Cell.MAX_POS; row++) {
			formatter.addNewRow();
			for (int col = Cell.MIN_POS; col <= Cell.MAX_POS; col++) {
				if (cell!=null && cell.getCol()==col && cell.getRow()==row) {
					formatter.addValue(cell.getValue());
					cell = iter.next();
				} else {
					formatter.addEmpty();
				}
			}
		}
		formatter.addFinalRow();
		return formatter.toString();
	}
	
}
