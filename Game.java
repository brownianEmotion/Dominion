import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Game {
	
	private Player player1;
	private Player player2;
	private Player currentPlayer;
	private Mat mat;
	private boolean gameOver;
	
	public Game(ArrayList<String> cardsToUse) {
		ArrayList<Card> allCards = convert(cardsToUse);
		this.mat = new Mat(allCards);
		setUpGame();
		
		
	}
	
	private void setUpGame() {
		
		this.player1 = new Player("Player 1");
		this.player2 = new Player("Player 2");
		currentPlayer = player1;
		gameOver = false;
		player1.newTurn();
		player2.newTurn();
	}
	
	private ArrayList<Card> convert(ArrayList<String> cardsToUse) {
		ArrayList<Card> toReturn = new ArrayList<Card>();
		for (int i = 0; i < cardsToUse.size(); i++) {
			String pic = "Cards/" + cardsToUse.get(i) + ".jpg";
			ImageIcon picture = new ImageIcon(pic);
			toReturn.add(new ActionCard(cardsToUse.get(i), picture));
		}
		
		return toReturn;
		
		
	}
	
	public Player playerOne() {
		return player1;
	}
	
	public Player playerTwo() {
		return player2;
	}
	
	
	
	public int numProvincesLeft() {
		return mat.getProvincePile().size();
	}
	
	public int numPilesOut() {
		return mat.numPilesOut();
	}
	
	public Mat getMat() {
		return mat;
	}
	
	public void buy(String card) throws StringMismatchException, EmptyPileException {
		Card toBuy = mat.convert(card);
		if (toBuy == null) {
			throw new EmptyPileException();
		}
		currentPlayer.buy(toBuy);
		
		
		
	}
	
	//returns null in the case of a tie.
	public Player getWinner() {
		if (player1.getTotalPoints() > player2.getTotalPoints()) {
			return player1;
		} else if (player1.getTotalPoints() < player2.getTotalPoints()) {
			return player2;
		}
		
		return null;
	}
	
	public void play(String card) throws StringMismatchException, ActionException {
		
		ActionCard toPlay = (ActionCard) currentPlayer.hand().convert(card); //Need to fix this part
		if (currentPlayer.hasActions() && currentPlayer.getCurrentPhase().equals("action")) {
			currentPlayer.playCard(toPlay);
		} else {
			throw new ActionException();
		}
		
	}
		
	public boolean gameIsOver() {
	//TODO
		if (numPilesOut() == 3 | numProvincesLeft() == 0) {
			gameOver = true;
		}
		
		return(gameOver);
		
		
	}
	
	public Player getCurrentPlayer() {
		
		return currentPlayer;
	}

	private Player switchCurrentPlayer() {
		
		if (currentPlayer.equals(player1)) {
			currentPlayer = player2;
		} else {
			currentPlayer = player1;
		}
		return currentPlayer;
	}
	
	public void nextTurn() {
		//TODO
		currentPlayer.newTurn();
		currentPlayer = switchCurrentPlayer();
	}
	
	
	
}
