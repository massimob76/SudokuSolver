package samples;

import model.Sample;

public class Difficult1 extends Sample {
	
	private String[] unsolvedGame = new String[] {
			"---6--4--",
			"7----36--",
			"----91-8-",
			"---------",
			"-5-18---3",
			"---3-6-45",
			"-4-2---6-",
			"9-3------",
			"-2----1--",
	};
	
	private String[] solution = new String[] {
			"581672439",
			"792843651",
			"364591782",
			"438957216",
			"256184973",
			"179326845",
			"845219367",
			"913768524",
			"627435198",
	};
	
	@Override
	public String[] getUnsolvedGameAsStringArray() {
		return unsolvedGame;
	}
	
	@Override
	public String[] getSolutionAsStringArray() {
		return solution;
	}
}
