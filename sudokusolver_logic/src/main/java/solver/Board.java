package solver;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;

import model.Cell;
import model.Solution;

public class Board implements Callable<Solution> {
	
	private static final int NO_OPTIONS = 0;
	private static final int ONE_OPTION_ONLY = 1;
	private final Set<Cell> solvedCells;
	private final Set<Cell> missingCells;
	private final RulesGuardian rulesGuardian;
	private final CompletionService<Solution> exec;
	
	public Board(CompletionService<Solution> exec) {
		solvedCells = new HashSet<Cell>();
		missingCells = new HashSet<Cell>();
		initializeMissingCells();
		rulesGuardian = new RulesGuardian();
		this.exec = exec;
	}
	
	private Board(Set<Cell> solvedCells, Set<Cell> missingCells, RulesGuardian rulesGuardian, CompletionService<Solution> exec) {
		this.solvedCells = solvedCells;
		this.missingCells = missingCells;
		this.rulesGuardian = rulesGuardian;
		this.exec = exec;
	}
	
	Set<Cell> getSolvedCells() {
		return solvedCells;
	}
	
	Set<Cell> getMissingCells() {
		return missingCells;
	}
	
	RulesGuardian getRulesGuardian() {
		return rulesGuardian;
	}
	
	public Board clone() {
		return new Board(softCopy(solvedCells), hardCopy(missingCells), rulesGuardian.clone(), exec);
	}
	
	static Set<Cell> hardCopy(Set<Cell> cells) {
		Set<Cell> copy = new HashSet<Cell>();
		for (Cell cell: cells) {
			copy.add(cell.clone());
		}
		return copy;
	}

	static Set<Cell> softCopy(Set<Cell> cells) {
		Set<Cell> copy = new HashSet<Cell>();
		for (Cell cell: cells) {
			copy.add(cell);
		}
		return copy;
	}

	@Override
	public Solution call() {
		
		while (!isGameSolved()) {
			Cell solvedCell = findNextSolvedCell();
			if (solvedCell == null) {
				return null;
			} else {
				addSolvedCell(solvedCell);
			}
		}
		return new Solution(solvedCells);
	}
	
	boolean isGameSolved() {
		return missingCells.isEmpty();
	}
	
	public void addSolvedCells(Set<Cell> solvedCells) {
		for (Cell solvedCell: solvedCells) {
			addSolvedCell(solvedCell);
		}
	}

	public void addSolvedCell(Cell solvedCell) {
		rulesGuardian.addCellValue(solvedCell);
		missingCells.remove(solvedCell.cloneWithoutValue());
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
				return cell.cloneSettingValue(onlyPossibleValue);
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
		return cellWithLessPossibleOptions.cloneSettingValue(possibleValuesHead);
	}

	private void startSeparateThreadsForEachOption(Cell cellWithLessPossibleOptions, LinkedList<Integer> possibleValuesPerCell) {
		for (Integer value: possibleValuesPerCell) {
			Cell clonedCell = cellWithLessPossibleOptions.cloneSettingValue(value);
			Board clonedBoard = this.clone();
			clonedBoard.addSolvedCell(clonedCell);
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
