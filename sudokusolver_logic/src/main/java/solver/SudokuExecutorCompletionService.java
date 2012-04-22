package solver;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

import model.Solution;

public class SudokuExecutorCompletionService extends ExecutorCompletionService<Solution> {
	
	private final AtomicInteger noOfSubmittedTasks = new AtomicInteger(0);
	private final ExecutorService exec;
	
	private SudokuExecutorCompletionService(ExecutorService exec) {
		super(exec);
		this.exec = exec;
	}
	
	public SudokuExecutorCompletionService() {
		this(Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()));
	}
	
	public int getNoOfSubmittedTasks() {
		return noOfSubmittedTasks.get();
	}

	@Override
	public Future<Solution> submit(Callable<Solution> task) {
		noOfSubmittedTasks.incrementAndGet();
		return super.submit(task);
	}

	public Set<Solution> getSolutions() throws InterruptedException, ExecutionException {
		int noOfProcessedFutures = 0;
		Set<Solution> solutions = new HashSet<Solution>();
		while (noOfProcessedFutures < noOfSubmittedTasks.get()) {
			noOfProcessedFutures++;
			Solution solution = take().get();
			if (solution!=null) {
				solutions.add(solution);
			}
		}
		exec.shutdown();
		return solutions;
	}
	
}
