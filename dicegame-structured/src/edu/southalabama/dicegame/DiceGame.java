package edu.southalabama.dicegame;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class DiceGame {
	
	private static volatile long seedQualifier = 432282912137141232L;
	
	public static Random getRandomNumberGenerator() {
		return new Random(++seedQualifier + System.nanoTime());
	}
	
	private static Scanner commandLine = new Scanner(System.in);

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		DiceGame dg = new DiceGame();
		dg.play();
	}
	
	public void play() throws IOException {
		
	}
	

}