package user_interaction;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import samples.PartialGame;
import solver.Cell;

public class UtilitiesTest {
	
	@Test
	public void getNicelyFormattedOutputFromCellListTest() {
		List<Cell> game = new PartialGame().loadUnsolvedGame();
		String expected = 
		"\n+-+-+-+-+-+-+-+-+-+\n" 
		+ "| |2| |6| |8| | | |\n"
		+ "+-+-+-+-+-+-+-+-+-+\n" 
		+ "|5|8| | | |9|7| | |\n"
		+ "+-+-+-+-+-+-+-+-+-+\n" 
		+ "| | | | | | | | | |\n"
		+ "+-+-+-+-+-+-+-+-+-+\n" 
		+ "| | | | | | | | | |\n"
		+ "+-+-+-+-+-+-+-+-+-+\n"
		+ "| | | | | | | | | |\n"
		+ "+-+-+-+-+-+-+-+-+-+\n" 
		+ "| | | | | | | | | |\n"
		+ "+-+-+-+-+-+-+-+-+-+\n" 
		+ "| | | | | | | | | |\n"
		+ "+-+-+-+-+-+-+-+-+-+\n"
		+ "| | | | | | | | | |\n"
		+ "+-+-+-+-+-+-+-+-+-+\n" 
		+ "| | | | | | | | | |\n"
		+ "+-+-+-+-+-+-+-+-+-+\n"; 
		String actual = Utilities.getNicelyFormattedOutputFromSortedCellList(game);
		assertEquals(expected, actual);
	}
	
}
