package userInteraction;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ExecutionException;

import solver.Solver;
import model.Cell;
import model.Game;
import model.Solution;

public class WebInteractionImpl implements WebInteraction {
	
	private final Set<Cell> unsolvedGame = new TreeSet<Cell>();
	private Solution solution;
	private Iterator<Cell> iter;
	private long elapsedTime;

	@Override
	public boolean addCellOfUnsolvedGame(int col, int row, String valueAsString) {
		try {
			int value = Integer.valueOf(valueAsString);
			unsolvedGame.add(new Cell(col - 1, row - 1, Integer.valueOf(value)));
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}

	@Override
	public void solveIt() throws InterruptedException, ExecutionException {
		Game game = new Game() {

			@Override
			public Set<Cell> getUnsolvedGame() {
				return unsolvedGame;
			}
			
		};
		
		Solver solver = new Solver(game, true);
		solver.timedSolveIt();
		solution = solver.getSolution();
		elapsedTime = solver.getElapsed();
		iter = solution.getSolution().iterator();
	}

	@Override
	public int getNextCellValueFromSolvedGame() {
		if (iter.hasNext()) {
			return iter.next().getValue();
		} else {
			throw new NoMoreCellsException();
		}
	}
	
	@SuppressWarnings("serial")
	static class NoMoreCellsException extends IllegalStateException {
		
	}

	@Override
	public long getElapsedTime() {
		return elapsedTime;
	}

}
