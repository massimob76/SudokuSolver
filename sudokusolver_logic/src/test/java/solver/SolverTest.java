package solver;

import static org.junit.Assert.*;

import java.util.Set;
import java.util.concurrent.ExecutionException;

import model.Sample;
import model.Solution;

import org.junit.Test;

import samples.Crazy;
import samples.Difficult1;
import samples.Difficult2;
import samples.Easy1;
import samples.Easy2;


public class SolverTest {
	
	private Solver iut;
	
	@Test
	public void solveItCanSolveEasy1() throws InterruptedException, ExecutionException {
		Sample game = new Easy1();
		iut = new Solver(game);
		Set<Solution> solutions = iut.timedSolveIt();
		int noOfSolutions = 1;
		assertEquals(noOfSolutions, solutions.size());
		for (Solution solution: solutions) {
			assertEquals(game.getSolution(), solution);
		}
		System.out.println("Took: " + iut.getElapsed() + "ms to find a solution");
	}
	
	@Test
	public void solveItCanSolveEasy2() throws InterruptedException, ExecutionException {
		Sample game = new Easy2();
		iut = new Solver(game);
		Set<Solution> solutions = iut.timedSolveIt();
		int noOfSolutions = 1;
		assertEquals(noOfSolutions, solutions.size());
		for (Solution solution: solutions) {
			assertEquals(game.getSolution(), solution);
		}
		System.out.println("Took: " + iut.getElapsed() + "ms to find a solution");
	}
	
	@Test
	public void solveItCanSolveDifficult1() throws InterruptedException, ExecutionException {
		Sample game = new Difficult1();
		iut = new Solver(game);
		Set<Solution> solutions = iut.timedSolveIt();
		int noOfSolutions = 1;
		assertEquals(noOfSolutions, solutions.size());
		for (Solution solution: solutions) {
			assertEquals(game.getSolution(), solution);
		}
		System.out.println("Took: " + iut.getElapsed() + "ms to find a solution");
	}
	
	@Test
	public void solveItCanSolveDifficult2() throws InterruptedException, ExecutionException {
		Sample game = new Difficult2();
		iut = new Solver(game);
		Set<Solution> solutions = iut.timedSolveIt();
		int noOfSolutions = 1;
		assertEquals(noOfSolutions, solutions.size());
		for (Solution solution: solutions) {
			assertEquals(game.getSolution(), solution);
		}
		System.out.println("Took: " + iut.getElapsed() + "ms to find a solution");
	}
	
	@Test
	public void solveItCanSolveCrazy() throws InterruptedException, ExecutionException {
		Sample game = new Crazy();
		iut = new Solver(game);
		Set<Solution> solutions = iut.timedSolveIt();
		int noOfSolutions = 1;
		assertEquals(noOfSolutions, solutions.size());
		for (Solution solution: solutions) {
			assertEquals(game.getSolution(), solution);
		}
		System.out.println("Took: " + iut.getElapsed() + "ms to find a solution");
	}
	
}
