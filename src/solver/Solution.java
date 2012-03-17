package solver;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import user_interaction.Utilities;

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
	
	@Override
	public String toString() {
		return Utilities.getNicelyFormattedOutputFromSortedCellList(sortedSolution);
	}
}
