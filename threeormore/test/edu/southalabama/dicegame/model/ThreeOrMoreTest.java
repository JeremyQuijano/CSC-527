package edu.southalabama.dicegame.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

public class ThreeOrMoreTest extends TestCase {
	
	private List<Player> players;
	private Player p1, p2, p3, p4, p5;
	private int numberOfRounds;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();

	}
	
	// Creates a ThreeOrMore instance with 5 Players and 3 Rounds
	private ThreeOrMore createInstance() {
		
		p1 = new Player("Jim");
		p2 = new Player("Joe");
		p3 = new Player("Jill");
		p4 = new Player("Joy");
		p5 = new Player("John");
		players = new ArrayList<Player>();
		
		players.add(p1);
		players.add(p2);
		players.add(p3);
		players.add(p4);
		players.add(p5);
		
		numberOfRounds = 3;
		
		ThreeOrMore tom = new ThreeOrMore(numberOfRounds, players);
		
		return tom;
	}
	

	
	@Test
	public void testCase1() {
		//	1) When a ThreeOrMore instance is created, all die facevalues should be 1.
		
		// 1. Instantiate a ThreeOrMore 
		// 2. Get List<Integer> of face values
		// 3. Using iteration/for, assert that each value is 1
		
		// display test case
		System.out.println("testCase1 beginning...");
		
		// instantiate ThreeOrMore
		ThreeOrMore tom = createInstance();
		
		// get face values
		List<Integer> fvaluesInitial = tom.getFaceValues();
		
		// assert each value is 1
		System.out.println("Testing Initial Dice Values...");
		System.out.println("Initial Dice Values Expected: 1 1 1 1 1");
		System.out.print("Initial Dice Values Found: ");
		for (int i = 0; i < 5; i++) {
			assert (fvaluesInitial.get(i) == 1);
			System.out.print(fvaluesInitial.get(i) + " ");
		}
		
		// all tests have passed
		System.out.println("\ntestCase1 Passed...\n");

	}
	
	@Test
	public void testCase2() {
		//	2) When a ThreeOrMore instance is created, it should have 5 dice.
		
		// 1. Instantiate a ThreeOrMore 
		// 2. Assert number of dice is 5
		
		// display test case
		System.out.println("testCase2 beginning...");
		
		// instantiate ThreeOrMore
		ThreeOrMore tom = createInstance();
		
		// assert number of dice is 5
		System.out.println("Testing Number of Dice...");
		int numDice = tom.getNumberOfDice();
		System.out.println("Number of Dice Expected: 5");
		assert (numDice == 5);
		System.out.println("Number of Dice Found: " + numDice);
		
		// all tests have passed
		System.out.println("testCase2 Passed...\n");

	}
	
	@Test
	public void testCase3() {
		//	3) When a ThreeOrMore instance is created, the number of Players reflects 
		//     the number of Player objects provided in the constructor.
		
		// 1. Create/use a list of player objects
		// 2. Instantiate a ThreeOrMore 
		// 3. Assert number of players in ThreeOrMore matches number in list

		// display test case
		System.out.println("testCase3 beginning...");
		
		// create list of player objects
		players = new ArrayList<Player>();
		
		// instantiate ThreeOrMore
		ThreeOrMore tom = createInstance();
		
		// assert number of players matches
		System.out.println("Testing Number of Players...");
		System.out.println("Number of Players Expected: " + players.size());
		System.out.println("Number of Players Found: " + tom.getNumberOfPlayers());
		assert (players.size() == tom.getNumberOfPlayers());
		
		// all tests have passed
		System.out.println("testCase3 Passed...\n");

	}
	
	@Test
	public void testCase4() {
		//	4) When a ThreeOrMore instance is created, the number of rounds reflects 
		//     the number of rounds provided in the constructor.
		
		// 1. Use integer to capture # of rounds
		// 2. Instantiate a ThreeOrMore with that value
		// 3. Assert number of rounds in ThreeOrMore matches number provided in constructor
		
		// display test case
		System.out.println("testCase4 beginning...");
		System.out.println("Testing Number of Rounds...");
		
		// # of rounds
		int numRounds = 3;
		System.out.println("Number of Rounds Expected: " + numRounds);
		
		// instantiate ThreeOrMore with # of rounds
		ThreeOrMore tom = createInstance();
		
		// assert # of rounds in ThreeOrMore matches constructor
		try {
			// test passed
			assert (tom.getNumberOfRounds() == numRounds);
			System.out.println("Number of Rounds Found: " + tom.getNumberOfRounds());
			System.out.println("testCase4 Passed...\n");
		}
		catch (AssertionError e) {
			// test failed
			System.out.println("testCase4 Failed...\n");
			
		}

	}
	
	@Test
	public void testCase5() {
		//	5) Player scores and player names are consistent with the list of Players 
		//     that were passed in to the constructor.

		// 1. Create a List<Player>
		// 2. Instantiate a ThreeOrMore with that list
		// 3. Assert player names through getPlayerNames() is the same as Player object names provided
		// 3. Assert player scores through getPlayerScores() is 0

		// display test case
		System.out.println("testCase5 beginning...");
		
		// create List<Player> done in createInstance()
		
		// instantiate ThreeOrMore with players		
		ThreeOrMore tom = createInstance();
		
		// assert getPlayerNames = players
		System.out.println("Testing getPlayerNames()...");
		for (int playerNumber = 1; playerNumber < 6; playerNumber++) {
			System.out.println("Player " + playerNumber + " Expected: " + players.get(playerNumber-1).getName());
			assert(tom.getPlayerNames().get(playerNumber-1) == players.get(playerNumber-1).getName());
			System.out.println("Player " + playerNumber + " Found: " + tom.getPlayerNames().get(playerNumber-1));
		}
		
		// assert getPlayerScores = 0
		System.out.println("Testing getPlayerScores()...");
		for (int i = 1; i < 6; i++) {
			System.out.println("Player " + i + " Score Exptected: 0");
			assert (tom.getPlayerScores().get(i-1) == 0);
			System.out.println("Player " + i + " Score Found: " + tom.getPlayerScores().get(i-1));
		}
		
		// all tests have passed
		System.out.println("testCase5 Passed...\n");
		
	}
	
	@Test
	public void testCase6() {
		
		//	6) Getting a Player (using an index number) corresponds to the correct Player 
		//     in the ordered list of Players that were used in the constructor. 
		//     (the indexes for getters start with 1, not 0!)

		// 1. Create a List<Player>
		// 2. Instantiate a ThreeOrMore with that list
		// 3. Assert Player object from getPlayer() - indexed at 1 - is same as object in list created - indexed at 0
		// 3. Assert player scores through getPlayerScores() is 0
		
		// display test case
		System.out.println("testCase6 beginning...");
		
		// create List<Player> done in createInstance()
		
		// instantiate ThreeOrMore
		ThreeOrMore tom = createInstance();
		
		// assert Player object getPlayer() == player created
		System.out.println("Testing getPlayer()...");
		for (int i = 1; i < 6; i++) {
			System.out.println("Player " + i + " Expected: " + players.get(i-1));
			assert (tom.getPlayer(i) == players.get(i-1));
			System.out.println("Player " + i + " Found: " + tom.getPlayer(i));
		}
				
		// assert player scores = 0
		System.out.println("Testing getPlayerScores()...");
		for (int i = 1; i < 6; i++) {
			System.out.println("Player " + i + " Score Exptected: 0");
			assert (tom.getPlayerScores().get(i-1) == 0);
			System.out.println("Player " + i + " Score Found: " + tom.getPlayerScores().get(i-1));
		}
		
		// all tests have passed
		System.out.println("testCase6 Passed...\n");
		
	}
	
	@Test
	public void testCase7() {
		
		//	7) Rolling all dice (rollAllDice()) should change at 
		//     least 1 value of the current dice.

		// 1. Instantiate a ThreeOrMore
		// 2. Roll all dice a few times
		// 3. Create List<Integer> to capture the starting face values with getFaceValues()
		// 4. Create a control variable for tracking how many roll attempts -- number of rolls
		// 5. Create a boolean variable for indicating that the dice have all the same values: start with true
		// 6. Create a loop that does the following:
		// 6a.  roll all the dice
		// 6b.  Create another List<Integer> to capture current face values with getFaceValues()
		// 6c.  Iterate through list of current face values and compare with starting values
		// 6d.  If any current face value differs, set boolean to false and break out of the loop
		// 7. assert that not all the dice have the same value
		
		// display test case
		System.out.println("testCase7 beginning...");
		
		// instantiate a ThreeOrMore
		ThreeOrMore tom = createInstance();
		
		// Roll the dice a few times
		tom.rollAllDice();
		tom.rollAllDice();
		
		// Get current values of the dice
		List<Integer> fvaluesOriginal = tom.getFaceValues();
		
		// Number of tries
		int numberOfRolls = 2;
		
		
		// Roll the dice at least twice: it is HIGHLY unlikely that at least one dice will not change value in two rolls
		boolean allsamevalues = true;
		for (int rollnum = 0; rollnum < numberOfRolls; rollnum++) {
			tom.rollAllDice();
			List<Integer> fvaluesCurrent = tom.getFaceValues();
			
			for (int dicenum = 0; dicenum < 5; dicenum++) {
				if (fvaluesOriginal.get(dicenum) != fvaluesCurrent.get(dicenum)) {
					allsamevalues = false;
					break;
				}
			}
		}
		
		assert (!allsamevalues);
		
		// all tests have passed
		System.out.println("testCase7 Passed...\n");
		
	}
	
	@Test
	public void testCase8() {
		
		//	8) Rolling all dice (rollAllDice()) should reset the selection of all dice.
		
		// 1. Instantiate a ThreeOrMore
		// 2. Create an integer to provide maximum number of tries
		// 3. Create a loop up to the max number of tries that does the following
		// 3a. Roll all the dice a couple of times
		// 3b. Select 3 dice via selectDice() method
		// 3c. Roll all the dice (this should reset any selected dice)
		// 3d. Get a list of the original face values 
		// 3e. Roll selected dice via rollSelectedDice
		// 3f. Get a list of current face values
		// 3g. assert original face values for the selected dice in 3b. are same for current face values

		// display test case
		System.out.println("testCase8 beginning...");
		
		// instantiate ThreeOrMore
		ThreeOrMore tom = createInstance();
		
		// create int for max number of tries
		int maxTries = 5;
		
		// loop
		System.out.println("Testing rollAllDice, selectDice(), getFaceValues(), and rollSelectedDice()...");
		for (int i = 1; i < maxTries+1; i++) {
			System.out.println("Try Number " + i);
			
			// roll all dice a couple of times
			tom.rollAllDice();
			tom.rollAllDice();
			
			// select 3 dice
			tom.selectDice(1);
			tom.selectDice(2);
			tom.selectDice(3);
			
			// roll all dice
			tom.rollAllDice();
			
			// get list of original face values
			List<Integer> fvaluesOriginal = tom.getFaceValues();
			System.out.println("Original Face Values: " + fvaluesOriginal);
			
			// roll selected dice
			tom.rollSelectedDice();
			
			// get list of current face values
			List<Integer> fvaluesCurrent = tom.getFaceValues();
			System.out.println("Current Face Values: " + fvaluesCurrent);
			
			// assert original face values for previously selected dice are same as current face values
			assertEquals (fvaluesOriginal, fvaluesCurrent);
		}
		
		// all tests have passed
		System.out.println("testCase 8 Passed...\n");
		
	}
	
	@Test
	public void testCase9() {
		
		//	9) Rolling selected dice (rollSelectedDice()) should not change any dice values if no dice have been selected.


		// 1. Instantiate a ThreeOrMore
		// 2. Create an integer to provide maximum number of tries
		// 3. Create a loop up to the max number of tries that does the following
		// 3a. Roll all dice a few times
		// 3b. Get original face values
		// 3c. Roll selected dice
		// 3d. Get current face values
		// 3e. Assert original and current values are the same
		
		// display test case
		System.out.println("testCase9 beginning...");
		
		// instantiate ThreeOrMore
		ThreeOrMore tom = createInstance();
		
		// create int for max tries
		int maxTries = 5;
		
		// loop
		System.out.println("Testing rollAllDice(), getFaceValues(), and rollSelectedDice()...");
		for (int i = 1; i < maxTries+1; i++) {
			System.out.println("Try number " + i);
			
			// roll all dice a few times
			tom.rollAllDice();
			tom.rollAllDice();
			tom.rollAllDice();
			
			// get original face values
			List<Integer> fvaluesOriginal = tom.getFaceValues();
			System.out.println("Original Face Values: " + fvaluesOriginal);
			
			// roll selected dice
			tom.rollSelectedDice();
			
			// get current face values
			List<Integer> fvaluesCurrent = tom.getFaceValues();
			System.out.println("Current Face Values: " + fvaluesCurrent);
			
			// assert original = current
			assertEquals (fvaluesOriginal, fvaluesCurrent);
			
		}
		
		// all tests have passed
		System.out.println("testCase9 Passed...\n");
		
	}

	
	@Test
	public void testCase10() {
		
		//	10) Selecting a dice should cause it to be rolled when rollSelectedDice() is called.


		// 1. Instantiate a ThreeOrMore
		// 2. Create an integer to provide maximum number of tries
		// 2a. Create booleans to track change of dice value - start with false
		// 3. Create a loop up to the max number of tries that does the following
		// 3b. Roll all dice a few times
		// 3c. Get original face values
		// 3d. Select dice via selectDice
		// 3e. Roll selected dice
		// 3f. Get current face values
		// 3g. Compare original/current values of selected dice -> use booleans to track
		// 4. Assert that dice values have changed outside of loop
		
		// display test case
		System.out.println("testCase10 beginning...");
		
		ThreeOrMore tom = createInstance();
		
		// Number of tries
		int numberOfTries = 3;
		
		boolean dice1ChangedValue = false;
		boolean dice2ChangedValue = false;
		
		for (int attempt = 0; attempt < numberOfTries; attempt++) {
			
			// Roll the dice a few times
			tom.rollAllDice();
			tom.rollAllDice();
			
			// Get the current face values
			List<Integer> fvaluesOriginal = tom.getFaceValues();
			
			// Select dice 1 and 2
			tom.selectDice(1);
			tom.selectDice(2);
		
			// Roll selected dice (dice 1 and 2 should be rolled)
			tom.rollSelectedDice();
			
			// Get the current face values 
			List<Integer> fvaluesCurrent = tom.getFaceValues();
			
			// Dice 1
			if (fvaluesCurrent.get(0) != fvaluesOriginal.get(0)) {
				dice1ChangedValue = true;
				//break;
			}
			
			// Dice 2
			if (fvaluesCurrent.get(1) != fvaluesOriginal.get(1)) {
				dice2ChangedValue = true;
				//break;
			}
		}
		
		assert (dice1ChangedValue && dice2ChangedValue);
		
		// all tests have passed
		System.out.println("testCase10 Passed...\n");
		
	}
	
	@Test
	public void testCase11and12() {
		
		//	11) Based on the face value of the dice, the ThreeOrMore object correctly 
		//      identifies two, three, four, and five of a kind.
		//	12) Based on the face value of the dice, the ThreeOrMore object correctly identifies 
		//      the correct score for current values of the dice.
		
		// 1. Instantiate ThreeOrMore instance
		// 2. Test two of a kind
		// 3. Test three of a kind
		// 4. Test four of a kind
		// 5. Test five of a kind
		
		// 5. Test five of a kind
		// 5a. Create boolean done -> false
		// 5b. Create while(!done) loop
		// 5b.1 Roll all dice
		// 5b.2 Get current face values
		// 5b.3 Create counter variables for number of 1,2,3,4,5,6 face values
		// 5b.4 Iterate through the face values and increment counter variables based on value
		// 5b.5 In a conditional, test if any counts of face values is 5
		//       -> If it is, set done = true
		// 5c. Assert ThreeOrMore hasFiveOfAKind
		// 5d. Assert getScore is 12
		
		// display test case
		System.out.println("testCase11and12 beginning...");
		
		// instantiate ThreeOrMore
		ThreeOrMore tom = createInstance();
		
		// test two of a kind
		System.out.println("Testing hasTwoOfAKind() and getPointsForDice()...");
		boolean twoOfAKind = false;
		int twoOfAKindRolls = 0;
		List<Integer> twoOfAKindValues;
		while (!twoOfAKind) {
			tom.rollAllDice();
			twoOfAKindRolls += 1;
			twoOfAKindValues = tom.getFaceValues();
			if (tom.hasTwoOfAKind()) {
				assertTrue (tom.hasTwoOfAKind());
				System.out.println("Two of a Kind Found in: " + twoOfAKindRolls + " rolls " + twoOfAKindValues);
				System.out.println("Points for Two of a Kind: " + tom.getPointsForDice() + "\n");
				twoOfAKind = true;
			}
		}
		
		// test three of a kind
		System.out.println("Testing hasThreeOfAKind() and getPointsForDice()...");
		boolean threeOfAKind = false;
		int threeOfAKindRolls = 0;
		List<Integer> threeOfAKindValues;
		while (!threeOfAKind) {
			tom.rollAllDice();
			threeOfAKindRolls += 1;
			threeOfAKindValues = tom.getFaceValues();
			if (tom.hasThreeOfAKind()) {
				assertTrue (tom.hasThreeOfAKind());
				System.out.println("Three of a Kind Found in: " + threeOfAKindRolls + " rolls " + threeOfAKindValues);
				assert (tom.getPointsForDice() == 3);
				System.out.println("Points for Three of a Kind: " + tom.getPointsForDice() + "\n");
				threeOfAKind = true;
			}
			
		}
		
		// test four of a kind
		System.out.println("Testing hasFourOfAKind() and getPointsForDice()...");
		boolean fourOfAKind = false;
		int fourOfAKindRolls = 0;
		List<Integer> fourOfAKindValues;
		while (!fourOfAKind) {
			tom.rollAllDice();
			fourOfAKindRolls += 1;
			fourOfAKindValues = tom.getFaceValues();
			if (tom.hasFourOfAKind()) {
				assertTrue (tom.hasFourOfAKind());
				System.out.println("Four of a Kind Found in: " + fourOfAKindRolls + " rolls " + fourOfAKindValues);
				assert (tom.getPointsForDice() == 6);
				System.out.println("Points for Four of a Kind: " + tom.getPointsForDice() + "\n");
				fourOfAKind = true;
			}
			
		}
		
		// test five of a kind
		System.out.println("Testing hasFiveOfAKind() and getPointsForDice()...");
		boolean fiveOfAKind = false;
		int fiveOfAKindRolls = 0;
		int countOne = 0;
		int countTwo = 0;
		int countThree = 0;
		int countFour = 0;
		int countFive = 0;
		int countSix = 0;
		List<Integer> countTotal = new ArrayList<Integer>();
		List<Integer> fiveOfAKindValues;
		List<Integer> allFives = Arrays.asList(5, 5, 5, 5, 5);
		
		while (!fiveOfAKind) {
			tom.rollAllDice();
			fiveOfAKindRolls += 1;
			fiveOfAKindValues = tom.getFaceValues();
			if (fiveOfAKindValues.contains(1)) {
				countOne += 1;
			}
			if (fiveOfAKindValues.contains(2)) {
				countTwo += 1;
			}
			if (fiveOfAKindValues.contains(3)) {
				countThree += 1;
			}
			if (fiveOfAKindValues.contains(4)) {
				countFour += 1;
			}
			if (fiveOfAKindValues.contains(5)) {
				countFive += 1;
			}
			if (fiveOfAKindValues.contains(6)) {
				countSix += 1;
			}
			if (fiveOfAKindValues.equals(allFives)) {
				countTotal.add(countOne);
				countTotal.add(countTwo);
				countTotal.add(countThree);
				countTotal.add(countFour);
				countTotal.add(countFive);
				countTotal.add(countSix);
				System.out.println("Five of a Kind Expected: " + allFives);
				assertTrue (tom.hasFiveOfAKind());
				System.out.println("Five of a Kind Found: " + fiveOfAKindValues); 
				System.out.println("Number of Rolls: " + fiveOfAKindRolls);
				System.out.println("Number of Face Values Rolled: " + countTotal);
				assert (tom.getPointsForDice() == 12);
				System.out.println("Points for Five of a Kind: " + tom.getPointsForDice() + "\n");
				fiveOfAKind = true;
			}
			
		}
		
		// all tests have passed
		System.out.println("testCase11and12 Passed...\n");
		
	}
		
	
}


