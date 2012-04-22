package userInteraction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import userInteraction.Game;

public class InteractiveGame extends Game {
	
	private static final int ROWS = 9;
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
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
	
	public String[] getSolutionAsStringArray() {
		return null;
	}
}
