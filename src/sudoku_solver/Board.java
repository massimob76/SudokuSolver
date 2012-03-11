package sudoku_solver;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

public class Board implements Callable<List<Cell>> {
	
	private static final int NO_OPTIONS = 0;
	private static final int ONE_OPTION_ONLY = 1;
	private final List<Cell> solvedCells;
	private final List<Cell> missingCells;
	private final RulesGuardian rulesGuardian;
	private final ExecutorService exec;
	
	public Board(ExecutorService exec) {
		solvedCells = new LinkedList<Cell>();
		missingCells = new LinkedList<Cell>();
		initializeMissingCells();
		rulesGuardian = new RulesGuardian();
		this.exec = exec;
	}
	
	private Board(List<Cell> solvedCells, List<Cell> missingCells, RulesGuardian rulesGuardian, ExecutorService exec) {
		this.solvedCells = solvedCells;
		this.missingCells = missingCells;
		this.rulesGuardian = rulesGuardian;
		this.exec = exec;
	}
	
	List<Cell> getSolvedCells() {
		return solvedCells;
	}
	
	List<Cell> getMissingCells() {
		return missingCells;
	}
	
	RulesGuardian getRulesGuardian() {
		return rulesGuardian;
	}
	
	public Board clone() {
		return new Board(softCopy(solvedCells), hardCopy(missingCells), rulesGuardian.clone(), exec);
	}
	
	static List<Cell> hardCopy(List<Cell> cells) {
		LinkedList<Cell> copy = new LinkedList<Cell>();
		for (Cell cell: cells) {
			copy.add(cell.clone());
		}
		return copy;
	}

	static List<Cell> softCopy(List<Cell> cells) {
		LinkedList<Cell> copy = new LinkedList<Cell>();
		for (Cell cell: cells) {
			copy.add(cell);
		}
		return copy;
	}

	@Override
	public List<Cell> call() {
		Cell solvedCell = findNextSolvedCell();
		while (!isGameSolved() && solvedCell != null) {
			addSolvedCell(solvedCell);
			solvedCell = findNextSolvedCell();
		}
		
		return isGameSolved() ? solvedCells : null;
	}
	
	boolean isGameSolved() {
		return missingCells.isEmpty();
	}

	public void addSolvedCell(Cell solvedCell) {
		rulesGuardian.addCellValue(solvedCell);
		missingCells.remove(solvedCell);
		solvedCells.add(solvedCell);
	}
	
	Cell findNextSolvedCell() {
		Cell cellWithLessPossibleOptions = null;
		int noOfOptionsForCellWithLessOptions = -1;
		for (Cell cell: missingCells) {
			int noOfPossibleValues = rulesGuardian.getNumberOfPossibleValuesPerCell(cell);
			switch (noOfPossibleValues) {
			case NO_OPTIONS:
				return null;
			case ONE_OPTION_ONLY:
				int onlyPossibleValue = rulesGuardian.getPossibleValuesPerCell(cell).get(0);
				cell.setValue(onlyPossibleValue);
				return cell;
			default:
				if (noOfOptionsForCellWithLessOptions==-1 || noOfOptionsForCellWithLessOptions > noOfPossibleValues) {
					noOfOptionsForCellWithLessOptions = noOfPossibleValues;
					cellWithLessPossibleOptions = cell;
				}
			}
		}
		return delegatePossibleOptionsToOtherThreadsAndRetainOnlyOneOption(cellWithLessPossibleOptions);
	}
	
	Cell delegatePossibleOptionsToOtherThreadsAndRetainOnlyOneOption(Cell cellWithLessPossibleOptions) {
		LinkedList<Integer> possibleValuesPerCell = rulesGuardian.getPossibleValuesPerCell(cellWithLessPossibleOptions);
		int possibleValuesHead = possibleValuesPerCell.pop();
		startSeparateThreadsForEachOption(cellWithLessPossibleOptions, possibleValuesPerCell);
		cellWithLessPossibleOptions.setValue(possibleValuesHead);
		return cellWithLessPossibleOptions;
	}

	private void startSeparateThreadsForEachOption(Cell cellWithLessPossibleOptions, LinkedList<Integer> possibleValuesPerCell) {
		for (Integer value: possibleValuesPerCell) {
			Cell clonedCell = cellWithLessPossibleOptions.clone();
			clonedCell.setValue(value);
			Board clonedBoard = this.clone();
			clonedBoard.addSolvedCell(clonedCell);
			System.out.println("about to submit: " + clonedBoard);
			exec.submit(clonedBoard);
		}
	}

	private void initializeMissingCells() {
		for (int row = Cell.MIN_POS; row <= Cell.MAX_POS; row++) {
			for (int col = Cell.MIN_POS; col <= Cell.MAX_POS; col++) {
				missingCells.add(new Cell(col, row));
			}
		}
	}

}
