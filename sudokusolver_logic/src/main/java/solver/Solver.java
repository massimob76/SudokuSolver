package solver;

import java.util.Set;
import java.util.concurrent.ExecutionException;

import model.Cell;
import model.GameModel;
import model.Solution;

import userInteraction.InteractiveGame;

public class Solver {
	
	private final GameModel game;
	private long startTime;
	private long endTime;
	
	public Solver(GameModel game) {
		this.game = game;
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		Solver solver = new Solver(new InteractiveGame());
		Set<Solution> solutions = solver.solveIt();
		System.out.println(solutions);
	}
	
	public Set<Solution> timedSolveIt() throws InterruptedException, ExecutionException {
		startTime = System.currentTimeMillis();
		Set<Solution> solutions = solveIt();
		endTime = System.currentTimeMillis();
		return solutions;
	}
	
	public long getElapsed() {
		return endTime - startTime;
	}
	
	public Set<Solution> solveIt() throws InterruptedException, ExecutionException {
		Set<Cell> unsolvedGame = game.getUnsolvedGame();
		SudokuExecutorCompletionService completionService = new SudokuExecutorCompletionService();
		Board board = new Board(completionService);
		board.addSolvedCells(unsolvedGame);
		completionService.submit(board);
		return completionService.getSolutions();
	}

}
