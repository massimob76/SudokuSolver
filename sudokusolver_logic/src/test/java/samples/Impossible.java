package samples;

import model.Sample;

public class Impossible extends Sample {
	
	private String[] unsolvedGame = new String[] {
			"-13678945",
			"58423976-",
			"967-45318",
			"37246-589",
			"69-583174",
			"4587926-3",
			"836914-57",
			"2-9857436",
			"7453-6891",
	};
	
	@Override
	public String[] getUnsolvedGameAsStringArray() {
		return unsolvedGame;
	}
	
	@Override
	public String[] getSolutionAsStringArray() {
		return null;
	}
}
