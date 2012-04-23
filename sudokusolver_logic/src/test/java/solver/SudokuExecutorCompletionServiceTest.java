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
		iut.submit(generateTask(0, 300));
		iut.submit(generateTask(0, 200));
		iut.submit(generateTask(0, 400));
		iut.submit(generateTask(0, 500));
		Set<Solution> expected = new HashSet<Solution>();
		Set<Solution> actual = iut.getSolutions();
		assertEquals(expected, actual);
	}
	
	@Test
	public void getFirstSolutionOnlyShouldReturnNullWhenThereAreNoSolutions() throws InterruptedException, ExecutionException {
		iut.submit(generateTask(0, 300));
		iut.submit(generateTask(0, 200));
		iut.submit(generateTask(0, 400));
		iut.submit(generateTask(0, 500));
		Solution actual = iut.getFirstSolutionOnly();
		assertNull(actual);
	}
	
	@Test
	public void getSolutionsShouldReturnOnlyOneSolutionWhenThereIsOnlyOneSolution() throws InterruptedException, ExecutionException {
		iut.submit(generateTask(0, 300));
		iut.submit(generateTask(0, 200));
		iut.submit(generateTask(1, 400));
		iut.submit(generateTask(0, 500));
		Set<Solution> expected = new HashSet<Solution>();
		expected.add(new Solution(createSet(1)));
		Set<Solution> actual = iut.getSolutions();
		assertEquals(expected, actual);
	}
	
	@Test
	public void getFirstSolutionOnlyShouldReturnOnlyOneSolutionWhenThereIsOnlyOneSolution() throws InterruptedException, ExecutionException {
		iut.submit(generateTask(0, 300));
		iut.submit(generateTask(0, 200));
		iut.submit(generateTask(1, 400));
		iut.submit(generateTask(0, 500));
		Solution expected = new Solution(createSet(1));
		Solution actual = iut.getFirstSolutionOnly();
		assertEquals(expected, actual);
	}
	
	@Test
	public void getSolutionsShouldReturnTwoSolutionsWhenThereAreTwoSolutions() throws InterruptedException, ExecutionException {
		iut.submit(generateTask(0, 300));
		iut.submit(generateTask(0, 200));
		iut.submit(generateTask(1, 400));
		iut.submit(generateTask(0, 500));
		iut.submit(generateTask(2, 500));
		Set<Solution> expected = new HashSet<Solution>();
		expected.add(new Solution(createSet(1)));
		expected.add(new Solution(createSet(2)));
		Set<Solution> actual = iut.getSolutions();
		assertEquals(expected, actual);
	}
	
	@Test
	public void getFirstSolutionOnlyShouldReturnOneSolutionWhenThereAreTwoSolutions() throws InterruptedException, ExecutionException {
		iut.submit(generateTask(0, 300));
		iut.submit(generateTask(0, 200));
		iut.submit(generateTask(1, 400));
		iut.submit(generateTask(0, 500));
		iut.submit(generateTask(2, 500));
		Solution expected = new Solution(createSet(1));
		Solution actual = iut.getFirstSolutionOnly();
		assertEquals(expected, actual);
	}

	private Callable<Solution> generateTask(final int elements, final int delay) {
		
		return new Callable<Solution>() {
			
			@Override
			public Solution call() {
				try {
					Thread.sleep(delay);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (elements == 0) {
					return null;
				} else {
					return new Solution(createSet(elements));
				}
			}
		};
	}
	
	private Set<Cell> createSet(int noOfCells) {
		final Set<Cell> set = new HashSet<Cell>();
		switch (noOfCells) {
		case 1:
			set.add(new Cell(1,2,3));
		case 2:
			set.add(new Cell(2,3,4));
		case 3:
			set.add(new Cell(3,4,5));
		case 4:
			set.add(new Cell(4,5,6));
		case 5:
			set.add(new Cell(5,6,7));
			break;
		default:
			throw new IllegalArgumentException("Cannot create set of " + noOfCells + " elements");
		}
		return set;
	}
	
	@Test
	public void getNoOfSubmittedTasksReturnsTheRightValue() {
		iut.submit(generateTask(0, 300));
		iut.submit(generateTask(0, 200));
		iut.submit(generateTask(1, 400));
		iut.submit(generateTask(0, 500));
		iut.submit(generateTask(2, 500));
		int expected = 5;
		int actual = iut.getNoOfSubmittedTasks();
		assertEquals(expected, actual);
	}

}
