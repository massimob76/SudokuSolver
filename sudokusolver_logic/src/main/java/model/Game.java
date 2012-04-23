package model;

import java.util.HashSet;
import java.util.Set;

public class Game implements GameModel {
	
	private Set<Cell> unsolvedGame = new HashSet<Cell>();
	
	public void addCell(int col, int row, int value) {
		unsolvedGame.add(new Cell(col, row, value));
	}
	
	public Set<Cell> getUnsolvedGame() {
		return unsolvedGame;
	}
	
}
