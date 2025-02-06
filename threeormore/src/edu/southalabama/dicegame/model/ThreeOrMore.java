package edu.southalabama.dicegame.model;

import java.util.ArrayList;
import java.util.List;


public class ThreeOrMore {
	
	/**
	 * The ordered list of die for this type of game
	 */
	private final List<Die> f_dice;
	
	/**
	 * The number of die used in this game
	 */
	private final int f_numDice;
	
	/**
	 * The number of rounds for this game
	 */
	private final int f_numRounds;
	
	/**
	 * The ordered list of players in this game
	 */
	private final List<Player> f_players;
	
	/**
	 * Whether or not the game play has completed
	 */
	private boolean f_gameover = false;

	/**
	 * @param rounds
	 * @param players
	 */
	public ThreeOrMore(int rounds, List<Player> players) {
		
		// Assign number of rounds
		f_numRounds = rounds;
		
		// Set the number of dice
		f_numDice = 5;
		
		// Create die instances
		f_dice = createDice();
		
		// Associate the players involved in this game
		f_players = new ArrayList<Player>();
		f_players.addAll(players);
	}

	private List<Die> createDice() {
		List<Die> dice = new ArrayList<Die>();
		for (int i = 1; i <= getNumberOfDice(); i++) {
			Die d = new Die(6);
			dice.add(d);
		}
		return dice;
	}
	
	public int getNumberOfDice() {
		return f_numDice;
	}
	
	public int getNumberOfRounds() {
		return f_numRounds;
	}
	
	public int getNumberOfPlayers() {
		return f_players.size();
	}

	public List<String> getPlayerNames() {
		List<String> names = new ArrayList<String>();
		for (Player p: f_players) {
			names.add(p.getName());
		}
		return names;
	}
	
	public List<Integer> getPlayerScores() {
		List<Integer> scores = new ArrayList<Integer>();
		for (Player p: f_players) {
			scores.add(p.getScore());
		}
		return scores;
	}

	public boolean isGameOver() {
		return f_gameover;
	}
	
	public void rollAllDice() {
		clearAllDiceSelections();
		for (Die d: f_dice) {
			d.roll();
		}
	}
	
	public void rollSelectedDice() {
		for (Die d: f_dice) {
			if (d.isSelected()) {
				d.roll();
			}
		}
	}
	
	private void clearAllDiceSelections() {
		for (Die d: f_dice) {
			d.setSelected(false);
		}
	}
	
	public void selectDice(int dienumber) {
		assert (dienumber >= 1);
		assert (dienumber <= f_numDice);
		
		Die d = f_dice.get(dienumber - 1);
		d.setSelected(true);
	}
	
	public Player getPlayer(int playernumber) {
		assert (playernumber >= 1);
		assert (playernumber <= this.getNumberOfPlayers());
		return f_players.get(playernumber - 1);
	}
	
	public List<Integer> getFaceValues () {
		List<Integer> values = new ArrayList<Integer>();
		for (Die d: f_dice) {
			values.add(d.getFaceValue());
		}
		return values;
	}
	
	private int numberOfDiceWithFaceValue(int fv) {
		int numberWithFaceValue = 0;
		for (Die d: f_dice) {
			if (d.getFaceValue() == fv) {
				numberWithFaceValue++;
			}
		}
		return numberWithFaceValue;
	}
	
	public boolean hasTwoOfAKind() {
		if (!hasFiveOfAKind() && !hasFourOfAKind() && !hasThreeOfAKind()) {
			for (int facevalue = 1; facevalue <= 6; facevalue++) {
				if (numberOfDiceWithFaceValue(facevalue) == 2) {
					return true;
				}
			}
		}
		return false;	
	}
	
	public boolean hasThreeOfAKind() {
		if (!hasFiveOfAKind() && !hasFourOfAKind()) {
			for (int facevalue = 1; facevalue <= 6; facevalue++) {
				if (numberOfDiceWithFaceValue(facevalue) == 3) {
					return true;
				}
			}
		}
		return false;			
	}
	
	public boolean hasFourOfAKind() {
		for (int facevalue = 1; facevalue <= 6; facevalue++) {
			if (numberOfDiceWithFaceValue(facevalue) == 4) {
				return true;
			}
		}
		return false;	
	}
	
	public boolean hasFiveOfAKind() {
		for (int facevalue = 1; facevalue <= 6; facevalue++) {
			if (numberOfDiceWithFaceValue(facevalue) == 5) {
				return true;
			}
		}
		return false;
	}
	
	public int getPointsForDice() {
		if (hasThreeOfAKind()) {
			return 3;
		}
		else if (hasFourOfAKind()) {
			return 6;
		}
		else if (hasFiveOfAKind()) {
			return 12;
		}
		else {
			return 0;
		}
	}
	
	
}
