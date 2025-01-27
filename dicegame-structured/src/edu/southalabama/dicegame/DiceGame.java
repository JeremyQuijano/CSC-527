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
	
	public static String getName() {
		String userName = new String();
		while (userName.length() < 1 || userName.length() > 50) {
			System.out.print("Enter player name (between 1 and 50 characters): ");
			userName = commandLine.nextLine();
		}
		System.out.println("");
		return userName;
	}
	
	public static int rollDie(int sides) {
		Random random = getRandomNumberGenerator();
		int dieValue = random.nextInt(6) + 1;
		return dieValue;
	}
	
	public boolean determineWin(int die1, int die2) {
		boolean result;
		int score = die1 + die2;
		
		if (score == 7 || score == 12) {
			result = true;
		}
		else {
			result = false;
		}
		
		return result;
	}
	
	public void displayStats(int wins, int losses) {
		System.out.println("# Wins: " + wins);
		System.out.println("# Losses: " + losses);
	}
	
	public boolean continueGame() {
		char userInput;
		boolean isValid;
		boolean yesno;
		
		do {
			System.out.println("Continue (Y/N)?");
			userInput = commandLine.nextLine().charAt(0);
			if (userInput == 'Y' || userInput == 'y' || userInput == 'N' || userInput == 'n') {
				isValid = true;
			}
			else {
				System.out.println("Invalid input. Please enter 'Y' or 'N'.");
				isValid = false;
			}
			
		} while (!isValid);
		
		System.out.println();
		
		if (userInput == 'Y' || userInput == 'y') {
			yesno = true;
		}
		else {
			yesno = false;
		}
		
		return yesno;
	}
	
	public void play() throws IOException {
		// Pre-game setup
		System.out.println("Dice Game by Jeremy Quijano\n");
		System.out.println("Rules:\n1) Enter your name\n2) Two dice will be rolled\n"
				+ "3) If the sum equals 7 or 12, then you win!\n4) Play again, or Exit\n");
		
		// prompt player for name
		String playerName = getName();
		
		// initialize win/loss/replay
		int wins = 0;
		int losses = 0;
		boolean replay = true;
		
		while (replay == true) {
			// dice being rolled
			System.out.println(playerName + ": Rolling the dice...\n");
			int roll1 = rollDie(6);
			int roll2 = rollDie(6);
			System.out.println("Roll 1: " + roll1);
			System.out.println("Roll 2: " + roll2);
			
			// sum dice
			int score = roll1 + roll2;
			System.out.println("Result: " + score);
			
			// determine outcome
			boolean result = determineWin(roll1, roll2);
			if (result == true) {
				System.out.println("You win!");
				wins++;
			}
			else {
				System.out.println("You lose!");
				losses++;
			}
			
			// total wins/losses
			displayStats(wins, losses);
			
			// continue?
			//System.out.println("Continue (Y/N)?");
			replay = continueGame();
		}
		
		System.out.println("Thanks for playing!");
	}
	

}