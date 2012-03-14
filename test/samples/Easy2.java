package samples;

import user_interaction.Sample;

public class Easy2 implements Sample {
	
	private String[] game = new String[] {
			"1--489--6",
			"73-----4-",
			"-----1295",
			"--712-6--",
			"5--7-3--8",
			"--6-957--",
			"9146-----",
			"-2-----37",
			"8--512--4",
	};
	
	private String[] solution = new String[] {
			"152489376",
			"739256841",
			"468371295",
			"387124659",
			"591763428",
			"246895713",
			"914637582",
			"625948137",
			"873512964",
	};
	
	public String[] getGame() {
		return game;
	}
	
	public String[] getSolution() {
		return solution;
	}
}
