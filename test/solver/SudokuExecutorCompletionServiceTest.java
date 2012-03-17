package solver;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import org.junit.Before;
import org.junit.Test;

public class SudokuExecutorCompletionServiceTest {
	
	private SudokuExecutorCompletionService iut;
	private static final List<Cell> list = new LinkedList<Cell>();
	static {
		list.add(new Cell(1,2,3));
		list.add(new Cell(2,3,4));
		list.add(new Cell(3,4,5));
		list.add(new Cell(4,5,6));
		list.add(new Cell(5,6,7));
	}
	
	@Before
	public void setUp() {
		iut = new SudokuExecutorCompletionService();
	}
	
	@Test
	public void getSolutionsShouldReturnOnlyOneSolutionWhenThereIsOnlyOneSolution() throws InterruptedException, ExecutionException {
		iut.submit(generateTask(0, 300));
		iut.submit(generateTask(0, 200));
		iut.submit(generateTask(1, 400));
		iut.submit(generateTask(0, 500));
		Set<Solution> expected = new HashSet<Solution>();
		expected.add(new Solution(list.subList(0, 1)));
		Set<Solution> actual = iut.getSolutions();
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
		expected.add(new Solution(list.subList(0, 1)));
		expected.add(new Solution(list.subList(0, 2)));
		Set<Solution> actual = iut.getSolutions();
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
					return new Solution(list.subList(0, elements));
				}
			}
		};
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
