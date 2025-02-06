package edu.southalabama.dicegame.model;

import java.util.Random;

public class Die {
	
	private final int f_sides;
	private int f_faceValue = 1;
	private static volatile long seedQualifier = 432282912137141232L;
	
	private boolean f_selected = false;
	
	Random f_randgen = new Random(++seedQualifier + System.nanoTime());

	public Die(int sides) {
		f_sides = sides;
	}

	public void roll() {
		f_faceValue = f_randgen.nextInt(f_sides) + 1;
	}
	
	public int getFaceValue() {
		return f_faceValue;
	}
	
	public boolean isSelected() {
		return f_selected;
	}
	
	public void setSelected(boolean s) {
		f_selected = s;
	}

}
