package edu.southalabama.dicegame.model;

import junit.framework.TestCase;

import org.junit.Test;

public class PlayerTest extends TestCase {
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	
	
	@Test
	public void testPlayer() {
		
		// Test name attribute and getName() method
		Player p1 = new Player("Lex Luther");
		assertTrue(p1.getName().contentEquals("Lex Luther"));
		
		// Test initial score
		assertTrue(p1.getScore() == 0);
		try {
			assertTrue(p1.getScore() != 0);
		}
		catch (AssertionError e) {
			// The test passed; score = 0!
		}
		
		// Test adding points
		int originalScore = p1.getScore();
		p1.addPoints(0);
		assertTrue(p1.getScore() == originalScore);
		
		// Add 10 points
		int currentScore = p1.getScore();
		p1.addPoints(10);
		assertTrue(p1.getScore() == currentScore + 10);
		
		// Subtract 50 points
		currentScore = p1.getScore();
		p1.addPoints(-50);
		assertTrue(p1.getScore() == currentScore - 50);
		
		// Double points
		currentScore = p1.getScore();
		p1.addPoints(p1.getScore());
		assertTrue(p1.getScore() == currentScore + currentScore);
		
	}
	
}
