package samples;

import user_interaction.Sample;

public class Intermediate implements Sample {
	
	private String[] game = new String[] {
			"-2-6-8---",
			"58---97--",
			"----4----",
			"37----5--",
			"6-------4",
			"--8----13",
			"----2----",
			"--98---36",
			"---3-6-9-",
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
	
	public String[] getGame() {
		return game;
	}
	
	public String[] getSolution() {
		return solution;
	}
}
