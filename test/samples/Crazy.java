package samples;

import userInteraction.Game;

public class Crazy extends Game {
	
	private String[] unsolvedGame = new String[] {
			"-2-------",
			"---6----3",
			"-74-8----",
			"-----3--2",
			"-8--4--1-",
			"6--5-----",
			"----1-78-",
			"5----9---",
			"-------4-",
	};
	
	private String[] solution = new String[] {
			"126437958",
			"895621473",
			"374985126",
			"457193862",
			"983246517",
			"612578394",
			"269314785",
			"548769231",
			"731852649",
	};
	
	public String[] getUnsolvedGameAsStringArray() {
		return unsolvedGame;
	}
	
	public String[] getSolutionAsStringArray() {
		return solution;
	}
}
