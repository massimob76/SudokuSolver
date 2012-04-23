package solver;

import java.util.Set;
import java.util.concurrent.ExecutionException;

import model.Cell;
import model.GameModel;
import model.Solution;

import userInteraction.InteractiveGame;

public class Solver {
	
	private final GameModel game;
	private final boolean firstSolutionOnly;
	private long startTime;
	private long endTime;
	private Set<Solution> solutions;
	private Solution solution;
	
	public Solver(GameModel game, boolean firstSolutionOnly) {
		this.game = game;
		this.firstSolutionOnly = firstSolutionOnly;
	}
	
	public Set<Solution> getSolutions() {
		return solutions;
	}
	
	public Solution getSolution() {
		return solution;
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		Solver solver = new Solver(new InteractiveGame(), false);
		solver.solveIt();
		Set<Solution> solutions = solver.getSolutions();
		System.out.println(solutions);
	}
	
	public void timedSolveIt() throws InterruptedException, ExecutionException {
		startTime = System.currentTimeMillis();
		solveIt();
		endTime = System.currentTimeMillis();
	}
	
	public long getElapsed() {
		return endTime - startTime;
	}
	
	private void solveIt() throws InterruptedException, ExecutionException {
		Set<Cell> unsolvedGame = game.getUnsolvedGame();
		SudokuExecutorCompletionService completionService = new SudokuExecutorCompletionService();
		Board board = new Board(completionService);
		board.addSolvedCells(unsolvedGame);
		completionService.submit(board);
		if (firstSolutionOnly) {
			solution = completionService.getFirstSolutionOnly();
		} else {
			solutions = completionService.getSolutions();
		}
	}

}
