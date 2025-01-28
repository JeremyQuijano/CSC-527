package edu.southalabama.dicegame.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class DiceGame {
	
	private Die die1;
	private Die die2;
	static int numSides = 6;
	
	public DiceGame() {
		this.die1 = new Die(numSides);
		this.die2 = new Die(numSides);
	}
	
	public void play() throws IOException {
		die1.roll(numSides);
		die1.getFaceValue();
		die2.roll(numSides);
		die2.getFaceValue();
	}

	public List<Integer> getDieValues() {
		List<Integer> values = new ArrayList<Integer>();
		values.add(die1.getFaceValue());
		values.add(die2.getFaceValue());
		
		return values;
	}
	
	public boolean getOutcome() {
		boolean outcome;
		
		if (getResult() == 7 || getResult() == 12) {
			outcome = true;
		}
		else {
			outcome = false;
		}
		
		return outcome;
	}
	
	public int getResult() {
		int result = die1.getFaceValue() + die2.getFaceValue();
		
		return result;
	}
	
}
