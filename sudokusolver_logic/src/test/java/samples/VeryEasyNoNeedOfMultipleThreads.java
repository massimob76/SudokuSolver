package samples;

import userInteraction.Game;

public class VeryEasyNoNeedOfMultipleThreads extends Game {
	
	private String[] unsolvedGame = new String[] {
			"-23678945",
			"58423976-",
			"967-45328",
			"37246-589",
			"69-583274",
			"4587926-3",
			"836924-57",
			"2-9857436",
			"7453-6892",
	};
	
	private String[] solution = new String[] {
			"123678945",
			"584239761",
			"967145328",
			"372461589",
			"691583274",
			"458792613",
			"836924157",
			"219857436",
			"745316892",
	};
	
	public String[] getUnsolvedGameAsStringArray() {
		return unsolvedGame;
	}
	
	public String[] getSolutionAsStringArray() {
		return solution;
	}
}
