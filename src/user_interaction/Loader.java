package user_interaction;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;

import solver.Board;
import solver.Cell;

public class Loader {
	
	private final ExecutorService exec;
	
	public Loader(ExecutorService exec) {
		this.exec = exec;
	}
	
	public Board loadGame(Sample sample) {
		return loadFromStringArray(sample.getGame());
	}
	
	public Board loadSolution(Sample sample) {
		return loadFromStringArray(sample.getSolution());
	}
	
	public boolean isSolutionCorrect(Sample sample, List<Cell> solution) {
		Collections.sort(solution);
		return solution.equals(sample.getSolution());
	}
	
	private Board loadFromStringArray(String[] array) {
		Board board = new Board(exec);
		for (int row = Cell.MIN_POS; row < Cell.MAX_POS; row++) {
			for (int col = Cell.MIN_POS; col < Cell.MAX_POS; col++) {
				int value = Character.getNumericValue(array[row].charAt(col));
				if (value != -1) {
					board.addSolvedCell(new Cell(col, row, value));
				}
			}
		}
		return board;
	}
}
