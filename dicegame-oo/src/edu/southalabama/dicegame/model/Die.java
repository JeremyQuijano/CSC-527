package edu.southalabama.dicegame.model;

import java.util.Random;

public class Die {
	
	private int faceValue;
	private static int numSides;
	
	Die(int sides) {
		this.faceValue = 0;
		Die.numSides = sides;
	}
	
	public int getFaceValue() {
		return faceValue;
	}
	
	public void roll(int numSides) {
		
		Random random = new Random();
		
		int randInt = random.nextInt(numSides) + 1;
		faceValue = randInt;
	}

}
