import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class ConsoleDominion {


	
	
	public static void playGame(Game game, Scanner stdin) {
		startTurn( game, stdin);
		endTurn(game);
	}
	
	private static void startTurn(Game game,Scanner stdin ) {
		System.out.println("\nYou are player: " + game.getCurrentPlayer().getName());
		String getActions = "You have " + game.getCurrentPlayer().getActions();
		if (game.getCurrentPlayer().getActions() == 1) {
			getActions += " action.";
		} else {
			getActions += " actions.";
		}
			
		System.out.println(getActions);
		System.out.println("Here is your hand:");
		Iterator<Card> iter = game.getCurrentPlayer().hand().iterator();
		
		while(iter.hasNext()) {
			System.out.println("                     " + iter.next().getName());
		}
		
		System.out.println("What would you like to do?");
		System.out.println("      1.) Play a Card");
		System.out.println("      2.) Play Treasures");
		System.out.println("      3.) End Turn");
		
		
		int getNumber = stdin.nextInt();
		stdin.nextLine();
		
		
		switch(getNumber) {
		case 1:
			playCard(game, stdin);
			break;
		case 2:
			playTreasures(game, stdin);
			break;
		case 3:
			
			break;
		default:
			System.out.println("Not a valid entry.  Please enter a number. ");
			startTurn(game,stdin);
		}
		
		
		
	}
	

	
	private static void endTurn(Game game) {
		// TODO Auto-generated method stub
		game.nextTurn();
		
		
	}

	private static void playTreasures(Game game, Scanner stdin) {
		//need to implement buying more than one card
		// TODO Auto-generated method stub
		System.out.println("You have " + game.getCurrentPlayer().playTreasures() 
				+ " coins and " + game.getCurrentPlayer().getBuys() + " buys.");
		System.out.println("What would you like to buy?  Here are your options:");
		for (int i = 0; i < game.getMat().totalPiles(); i ++) {
			if (!game.getMat().getAllPiles().get(i).isEmpty()) {
				System.out.println("       " + (i + 1) + ": " + 
						game.getMat().getAllPiles().get(i).getOnlyCardInPile().getName() + "(" +
						game.getMat().getAllPiles().get(i).getOnlyCardInPile().getCost() + " coins)");
			} else {
				System.out.println("       " + (i + 1) + ": This pile is out! ");
			}
		}
		
		System.out.println("Type the name of the card (or type none): ");
		
		String card = stdin.nextLine().toLowerCase();
		if (card.equals("none")) {
			return;
		} else {
			try{
				game.buy(card);
				if (game.getCurrentPlayer().hasBuys()) {
					playTreasures(game, stdin);
				}
			} catch (StringMismatchException e){
				System.out.println("You can't buy that card.  Try again");
				playTreasures(game,stdin);
			} catch (EmptyPileException e) {
				System.out.println("That pile is out!  Try again");
				playTreasures(game,stdin);
			}
		}
		
		
		
	}

	private static void playCard(Game game, Scanner stdin) {
		if (game.getCurrentPlayer().getCurrentPhase().equals("action")) {
			System.out.println("Which card would you like to play? (or none)");
			String card = stdin.nextLine().toLowerCase();
			if (card.equals("none")) {
				startTurn(game,stdin);
			} else {
				try {
					game.play(card);
					startTurn(game,stdin);
				
				} catch (StringMismatchException e) {
					System.out.println("You can't play that card.  Try again");
					startTurn(game,stdin);
				} catch (ActionException e) {
					System.out.println("You have no actions!");
					startTurn(game,stdin);
				}
			}
		
			
		} else {
			System.out.print("You are no longer in your action phase!");
			startTurn(game,stdin);
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner stdin = new Scanner(System.in);
		ArrayList<String> cardsToUse = new ArrayList<String>();
		for (int i = 0; i < Config.STANDARD_CARDS.length; i ++) {
			cardsToUse.add(Config.STANDARD_CARDS[i]);
		}
		Game game = new Game(cardsToUse);
		System.out.println("----------------------------------------");
		System.out.println("Hello, welcome to console dominion!");
		System.out.println("----------------------------------------");
		
		while (!game.gameIsOver()) {
			playGame(game, stdin);
		}
		
		System.out.println("----------------------------------------");
		System.out.println("And it looks like the game is over!");
		System.out.println("And the winner is: (drumroll please) ");
		
		stdin.nextLine();
		
		
		Player winner = game.getWinner();
		if (!(winner == null)) {
			System.out.println(game.getWinner().getName()
					+ "! Congratulations!");
			
		} else {
			System.out.println("Looks like we have a tie!");
		}
		
		System.out.println("Thank you for playing Console Dominion!");
		System.out.println("----------------------------------------");

		stdin.close();
	}

}
