package solver;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import model.Cell;
import model.Solution;

import org.junit.Before;
import org.junit.Test;

public class SudokuExecutorCompletionServiceTest {
	
	private SudokuExecutorCompletionService iut;
	
	@Before
	public void setUp() {
		iut = new SudokuExecutorCompletionService();
	}
	
	@Test
	public void getSolutionsShouldReturnNullWhenThereAreNoSolutions() throws InterruptedException, ExecutionException {
		iut.submit(generateTask(null, 300));
		iut.submit(generateTask(null, 200));
		iut.submit(generateTask(null, 400));
		iut.submit(generateTask(null, 500));
		Set<Solution> expected = new HashSet<Solution>();
		Set<Solution> actual = iut.getSolutions();
		assertEquals(expected, actual);
	}
	
	@Test
	public void getFirstSolutionOnlyShouldReturnNullWhenThereAreNoSolutions() throws InterruptedException, ExecutionException {
		iut.submit(generateTask(null, 300));
		iut.submit(generateTask(null, 200));
		iut.submit(generateTask(null, 400));
		iut.submit(generateTask(null, 500));
		Solution actual = iut.getFirstSolutionOnly();
		assertNull(actual);
	}
	
	@Test
	public void getSolutionsShouldReturnOnlyOneSolutionWhenThereIsOnlyOneSolution() throws InterruptedException, ExecutionException {
		iut.submit(generateTask(null, 300));
		iut.submit(generateTask(null, 200));
		iut.submit(generateTask(1, 400));
		iut.submit(generateTask(null, 500));
		Set<Solution> expected = new HashSet<Solution>();
		expected.add(new Solution(createSet(1)));
		Set<Solution> actual = iut.getSolutions();
		assertEquals(expected, actual);
	}
	
	@Test
	public void getFirstSolutionOnlyShouldReturnOnlyOneSolutionWhenThereIsOnlyOneSolution() throws InterruptedException, ExecutionException {
		iut.submit(generateTask(null, 300));
		iut.submit(generateTask(null, 200));
		iut.submit(generateTask(1, 400));
		iut.submit(generateTask(null, 500));
		Solution expected = new Solution(createSet(1));
		Solution actual = iut.getFirstSolutionOnly();
		assertEquals(expected, actual);
	}
	
	@Test
	public void getSolutionsShouldReturnTwoSolutionsWhenThereAreTwoSolutions() throws InterruptedException, ExecutionException {
		iut.submit(generateTask(null, 300));
		iut.submit(generateTask(null, 200));
		iut.submit(generateTask(1, 400));
		iut.submit(generateTask(null, 500));
		iut.submit(generateTask(2, 500));
		Set<Solution> expected = new HashSet<Solution>();
		expected.add(new Solution(createSet(1)));
		expected.add(new Solution(createSet(2)));
		Set<Solution> actual = iut.getSolutions();
		assertEquals(expected, actual);
	}
	
	@Test
	public void getFirstSolutionOnlyShouldReturnOneSolutionWhenThereAreTwoSolutions() throws InterruptedException, ExecutionException {
		iut.submit(generateTask(null, 300));
		iut.submit(generateTask(null, 200));
		iut.submit(generateTask(1, 400));
		iut.submit(generateTask(null, 500));
		iut.submit(generateTask(2, 500));
		Solution expected = new Solution(createSet(1));
		Solution actual = iut.getFirstSolutionOnly();
		assertEquals(expected, actual);
	}

	private Callable<Solution> generateTask(final Integer value, final int delay) {
		
		return new Callable<Solution>() {
			
			@Override
			public Solution call() {
				try {
					Thread.sleep(delay);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (value == null) {
					return null;
				} else {
					return new Solution(createSet(value));
				}
			}
		};
	}
	
	private Set<Cell> createSet(int value) {
		final Set<Cell> set = new HashSet<Cell>();
		for (int row = Cell.MIN_POS; row <= Cell.MAX_POS; row++) {
			for (int col = Cell.MIN_POS; col <= Cell.MAX_POS; col++) {
				set.add(new Cell(col, row, value));
			}
		}
		return set;
	}
	
	@Test
	public void getNoOfSubmittedTasksReturnsTheRightValue() {
		iut.submit(generateTask(null, 300));
		iut.submit(generateTask(null, 200));
		iut.submit(generateTask(1, 400));
		iut.submit(generateTask(null, 500));
		iut.submit(generateTask(2, 500));
		int expected = 5;
		int actual = iut.getNoOfSubmittedTasks();
		assertEquals(expected, actual);
	}

}
