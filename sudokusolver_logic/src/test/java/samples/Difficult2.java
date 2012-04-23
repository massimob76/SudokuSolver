package samples;

import model.Sample;

public class Difficult2 extends Sample {
	
	private String[] unsolvedGame = new String[] {
			"2--3-----",
			"8-4-62--3",
			"-138--2--",
			"----2-39-",
			"5-7---621",
			"-32--6---",
			"-2---914-",
			"6-125-8-9",
			"-----1--2",
	};
	
	private String[] solution = new String[] {
			"276314958",
			"854962713",
			"913875264",
			"468127395",
			"597438621",
			"132596487",
			"325789146",
			"641253879",
			"789641532",
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
