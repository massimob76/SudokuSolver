package solver;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import user_interaction.Game;

public class Solver {
	
	private final Game game;
	private long startTime;
	private long endTime;
	
	public Solver(Game game) {
		this.game = game;
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
		List<Cell> unsolvedGame = game.loadUnsolvedGame();
		SudokuExecutorCompletionService completionService = new SudokuExecutorCompletionService();
		Board board = new Board(completionService);
		board.addSolvedCells(unsolvedGame);
		completionService.submit(board);
		return completionService.getSolutions();
	}

}
