package workflow;

public interface WebInteraction {
	
	public boolean addCellOfUnsolvedGame(int col, int row, String value);
	
	public void solveIt() throws Exception;
	
	public int getNextCellValueFromSolvedGame();
	
	public long getElapsedTime();

}
