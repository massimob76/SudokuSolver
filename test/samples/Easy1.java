package samples;

import user_interaction.Game;

public class Easy1 extends Game {
	
	private String[] unsolvedGame = new String[] {
			"---26-7-1",
			"68--7--9-",
			"19---45--",
			"82-1---4-",
			"--46-29--",
			"-5---3-28",
			"--93---74",
			"-4--5--36",
			"7-3-18---",
	};
	
	private String[] solution = new String[] {
			"435269781",
			"682571493",
			"197834562",
			"826195347",
			"374682915",
			"951743628",
			"519326874",
			"248957136",
			"763418259",
	};
	
	public String[] getUnsolvedGame() {
		return unsolvedGame;
	}
	
	public String[] getSolution() {
		return solution;
	}
}
