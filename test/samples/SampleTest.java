package samples;

import user_interaction.Sample;

public class SampleTest implements Sample {
	
	private String[] game = new String[] {
			"-2-6-8---",
			"58---97--",
			"---------",
			"---------",
			"---------",
			"---------",
			"---------",
			"---------",
			"---------",
	};
	
	private String[] solution = new String[] {
			"123678945",
			"584239761",
			"---------",
			"---------",
			"---------",
			"---------",
			"---------",
			"---------",
			"---------",
	};
	
	public String[] getGame() {
		return game;
	}
	
	public String[] getSolution() {
		return solution;
	}
}
