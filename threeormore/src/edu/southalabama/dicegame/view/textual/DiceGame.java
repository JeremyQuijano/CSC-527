package edu.southalabama.dicegame.view.textual;

import static java.lang.System.out;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import edu.southalabama.dicegame.model.Player;
import edu.southalabama.dicegame.model.ThreeOrMore;

public class DiceGame  {
	
	/**
	 * The operating system-specific line separation character.
	 */
	private static final String LINESEP = System.getProperty("line.separator");
	
	/**
	 *  Let's be realistic here....
	 */
	private final static int MINPLAYERS = 2; 
	private final static int MAXPLAYERS = 200; 
	private final static int MINROUNDS = 1;
	private final static int MAXROUNDS = 200; 
	
	/**
	 * A IO stream used to input the user's commands from the console.
	 */
	private static final BufferedReader f_in = new BufferedReader(
			new InputStreamReader(System.in));
	
	/**
	 * The ThreeOrMore dice game instance (Model)
	 */
	private ThreeOrMore f_tom;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		out.println("################################");
		out.println("Welcome to Dice Game!");
		out.println("################################");
		out.println("Playing Three-Or-More");	
		out.println();

		DiceGame dg = new DiceGame();

		int numRounds = -1;
		List<Player> players = null;	
		
		try {
			int numPlayers = getNumberOfPlayers();
			numRounds = getNumberOfRounds();
			
			out.println("Enter names for each player in the order of their turns:");
			players = createPlayers(numPlayers);	
			
		} catch (IOException e) {
			/*
			 * Our ability to input from the console has failed for some reason.
			 * Print out a stack trace to the console.
			 */
			e.printStackTrace();
			System.exit(-1);
		}

		// To have a valid game, we must know number of rounds and the players involved!
		dg.f_tom = new ThreeOrMore(numRounds, players);
		
		out.println();
		out.println("Game Setup:");	
		out.println("# of rounds = " + dg.f_tom.getNumberOfRounds());
		
		List<String> names = dg.f_tom.getPlayerNames();
		List<Integer> scores = dg.f_tom.getPlayerScores();

		for (int playernum = 1; playernum <= dg.f_tom.getNumberOfPlayers(); playernum++) {
			out.println("Player #" + playernum + ": " + names.get(playernum-1) + "\tScore = " + scores.get(playernum-1));
		}
		
		/*
		 * Enter the main loop (input-response) for the game.
		 */
		try {
			dg.playGame();
		} catch (IOException e) {
			/*
			 * Our ability to input from the console has failed for some reason.
			 * Print out a stack trace to the console.
			 */
			e.printStackTrace();
			System.exit(-1);
		}

	}

	private void playGame() throws IOException {
		for (int roundnum = 1; roundnum <= f_tom.getNumberOfRounds(); roundnum++) {
			// Print the current round
			out.println();
			out.println("====================");
			out.println("Round #" + roundnum);
			out.println("====================");
			for (int playernum = 1; playernum <= f_tom.getNumberOfPlayers(); playernum++) {
				Player p = f_tom.getPlayer(playernum);
				
				// Print the player name
				out.println ("Player #" + playernum + ": " + p.getName());
				
				// Wait for player to request roll
				out.println ("Press <ENTER> to roll the dice");
				readLineFromConsole();
				
				// Roll the dice!
				out.println ("Rolling dice...");
				f_tom.rollAllDice();
				
				// Display results to the user
				out.println (getFaceValueString(f_tom.getFaceValues()));
				
				// Evaluate and assign points for the roll
				if (f_tom.hasTwoOfAKind()) {
					
					out.println("You have a Two-Of-A-Kind: You get an extra roll!");
					out.println("Choose the dice numbers [1-" + f_tom.getNumberOfDice() + "] for the pair of dice you want to keep:");
					
					List<Integer> matchingDice = getMatchingPair(f_tom.getFaceValues());
					
					out.println();
					for (Integer i: matchingDice) {
						out.println ("Keeping dice #" + i);
					}

					// Select all other dice for re-rolling
					for (int dicenum = 1; dicenum < f_tom.getNumberOfDice(); dicenum++) {
						if (dicenum != matchingDice.get(0)) {
							if (dicenum != matchingDice.get(1)) {
								f_tom.selectDice(dicenum);
							}
						}
					}
					
					// Wait for player to request roll
					out.println ("Press <ENTER> to roll the dice");
					readLineFromConsole();
					
					// Roll the selected dice
					out.print ("Rolling dice [");
					for (int dicenum = 1; dicenum < f_tom.getNumberOfDice(); dicenum++) {
						if (!(dicenum == matchingDice.get(0) || dicenum == matchingDice.get(1))) {
							out.print (" " + dicenum);
						}
					}
					out.println (" ] ...");
					f_tom.rollSelectedDice();
					
					// Display results to the user
					out.println (getFaceValueString(f_tom.getFaceValues()));
					
				}
				
				
				if (f_tom.hasThreeOfAKind()) {
					out.println ("You have a Three-Of-A-Kind!");
				}
				else if (f_tom.hasFourOfAKind()) {
					out.println ("You have a Four-Of-A-Kind!");
				}
				else if (f_tom.hasFiveOfAKind()) {
					out.println ("You have a Five-Of-A-Kind!");
				}
				else {
					out.println ("Sorry, you have no matching dice of three or more...");
				}
				
				// Award points to Player
				p.addPoints(f_tom.getPointsForDice());
				
				// Display results to the user
				out.println ("Points awarded: " + f_tom.getPointsForDice());
				out.println ("Current score: " + p.getScore());
				out.println ("-------------------------------------------");
				
			}
		}
		
		out.println ("Game is over! Here are the final scores:");
		
		// Display scores and determine winner or tie
		int highscore = -1;
		for (int playernum = 1; playernum <= f_tom.getNumberOfPlayers(); playernum++) {
			Player p = f_tom.getPlayer(playernum);
			out.println("  Player #" + playernum + ": " + p.getName() + "\tScore = " + p.getScore());
			if (p.getScore() >= highscore) {
				highscore = p.getScore();
			}
		}
		
		// Determine ties	
		int playersWithHighScore = 0;
		for (int playernum = 1; playernum <= f_tom.getNumberOfPlayers(); playernum++) {
			Player p = f_tom.getPlayer(playernum);
			if (p.getScore() == highscore) {
				playersWithHighScore++;
			}
		}
		
		out.println();
		out.println("*****************************");
		out.println("Final game results:");
		
		if (playersWithHighScore != 1) {
			// Announce we have a tie
			out.println ("We have a tie for the winners...");
		}

		for (int playernum = 1; playernum <= f_tom.getNumberOfPlayers(); playernum++) {
			Player p = f_tom.getPlayer(playernum);
			if (p.getScore() == highscore) {
				out.println ("Congratulation to Player #" + playernum + ": " + p.getName() + "! Score = " + p.getScore());
			}
		} 
	}
	
	private String getFaceValueString(List<Integer> values) {
		StringBuilder sb = new StringBuilder();
		int dicenum = 1;
		for (Integer fv: values) {
			sb.append("Dice #" + dicenum + ": " + fv);
			sb.append(LINESEP);
			dicenum++;
		}
		return sb.toString();
	}

	private static List<Player> createPlayers(int numPlayers) throws IOException {
		
		List<Player> players = new ArrayList<Player>();
		
		for (int playernum = 1; playernum <= numPlayers; playernum++) {	
			String playername = getPlayerName(playernum);
			Player p = new Player(playername);
			players.add(p);
		}
	
		return players;
	}

	private static int getNumberOfRounds() throws IOException {
		int numRounds = -1;
		
		while (numRounds < MINROUNDS || numRounds > MAXROUNDS)  {
			out.print("Enter number of rounds [" + MINROUNDS+" .. "+ MAXROUNDS + "]: ");
			String commandline = readLineFromConsole();
			try {
				numRounds = Integer.parseInt(commandline);
			}
			catch (NumberFormatException e) {
				numRounds = -1;
			}
		}

		return numRounds;
	}

	private static int getNumberOfPlayers() throws IOException {
		
		int numPlayers = -1;
		
		while (numPlayers < MINPLAYERS || numPlayers > MAXPLAYERS)  {
			out.print("Enter number of players ["+ MINPLAYERS + " .. " + MAXPLAYERS + "]:");
			String commandline = readLineFromConsole();
			try {
				numPlayers = Integer.parseInt(commandline);
			}
			catch (NumberFormatException e) {
				numPlayers = -1;
			}
		}

		return numPlayers;
	}
	
	private static String getPlayerName(int playernum) throws IOException {
		
		String name = new String();
		
		while (name.length() < 1 || name.length() > 50)  {
			out.print("  Player #" + playernum + " name:");
			String commandline = readLineFromConsole();
			name = commandline.trim();
		}

		return name;
	}
	
	private static List<Integer> getMatchingPair(List<Integer> facevalues) throws IOException {
		List<Integer> matchingPair = new ArrayList<Integer>();
		int dicenum1 = -1;
		int dicenum2 = -1;
		int facevalue1 = -1;
		int facevalue2 = -1;
		
		boolean goodpair = false;
		
		while (!goodpair)  {
			out.print("Enter first dice number of the pair: ");
			String commandline = readLineFromConsole();
			try {
				dicenum1 = Integer.parseInt(commandline);
			}
			catch (NumberFormatException e) {
				dicenum1 = -1;
			}
			out.print("Enter second dice number of the pair: ");
			commandline = readLineFromConsole();
			try {
				dicenum2 = Integer.parseInt(commandline);
			}
			catch (NumberFormatException e) {
				dicenum2 = -1;
			}
			if (dicenum1 < 1 || dicenum1 > 6 || dicenum2 < 1 || dicenum2 > 6) {
				out.println ("Legal dice numbers are from 1 .. 6!");
			}
			else {
				facevalue1 = facevalues.get(dicenum1 - 1);
				facevalue2 = facevalues.get(dicenum2 - 1);
				
				if (facevalue1 != facevalue2) {
					out.println ("Please choose dice numbers with the same face value!");
				}
				else {
					goodpair = true;
				}
			}
			
		}
		
		matchingPair.add(dicenum1);
		matchingPair.add(dicenum2);

		return matchingPair;
	}
	
	/**
	 * Reads a single line of text from the console. This is the users's next
	 * command for the game.
	 * 
	 * @return the user's command (a single line of text).
	 * @throws IOException
	 *             if our attempt to input from the console has failed for some
	 *             reason. This should typically not happen.
	 */
	private static String readLineFromConsole() throws IOException {
		out.print(" ");
		return f_in.readLine();
	}


}
