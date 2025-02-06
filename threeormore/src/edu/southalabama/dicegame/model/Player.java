package edu.southalabama.dicegame.model;

public class Player {
	
	private final String f_name;
	private int f_score;

	public Player(String playername) {
		f_name = playername;
		f_score = 0;
	}
	
	public String getName() {
		return f_name;
	}
	
	public int getScore() {
		return f_score;
	}
	
	public void addPoints(int points) {
		f_score = f_score + points;
	}

}
