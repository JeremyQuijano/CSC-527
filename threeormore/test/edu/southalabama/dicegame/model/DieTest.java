package edu.southalabama.dicegame.model;

import junit.framework.TestCase;

import org.junit.Test;

public class DieTest extends TestCase {
	
	private Die d1;
	private Die d2;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		d1 = new Die(6);
		d2 = new Die(6);
	}
	

	@Test
	public void testDiceRoll() {
		int d1faceval;
		int d2faceval;
		
		d1faceval = d1.getFaceValue();
		assertEquals(d1faceval,1);
		
		d2faceval = d2.getFaceValue();
		assertEquals(d2faceval,1);
		
		System.out.println ("Dice 1 = " + d1faceval);
		System.out.println ("Dice 2 = " + d2faceval);
		
		
		for (int i = 0; i < 50; i++) {
			System.out.println ("Rolling...");
			
			d1.roll();
			d1faceval = d1.getFaceValue();
			assert (d1faceval >= 1);
			assert (d1faceval <= 6);
			assertEquals (d1faceval, d1.getFaceValue());
			
			d2.roll();
			d2faceval = d2.getFaceValue();
			assert (d2faceval >= 1);
			assert (d2faceval <= 6);
			assertEquals (d2faceval, d2.getFaceValue());
			
			System.out.println ("  Dice 1 = " + d1faceval);
			System.out.println ("  Dice 2 = " + d2faceval);
		}
			
	}

	
	
}


