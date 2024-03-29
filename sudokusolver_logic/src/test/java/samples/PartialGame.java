package samples;

import model.Sample;

public class PartialGame extends Sample {
	
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
	
	@Override
	public String[] getUnsolvedGameAsStringArray() {
		return game;
	}
	
	@Override
	public String[] getSolutionAsStringArray() {
		return solution;
	}
}
