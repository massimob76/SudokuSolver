package userInteraction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import model.Sample;


public class InteractiveGame extends Sample {
	
	private static final int ROWS = 9;
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	@Override
	public String[] getUnsolvedGameAsStringArray() {
		String[] unsolvedGame = new String[ROWS];
		try {
			for (int i = 0; i < ROWS; i++) {
				unsolvedGame[i] = reader.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return unsolvedGame;
	}
	
	@Override
	public String[] getSolutionAsStringArray() {
		return null;
	}
}
