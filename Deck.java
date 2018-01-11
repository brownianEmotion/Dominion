import java.util.ArrayList;
import java.util.Collections;


public class Deck {
	private Hand hand;
	private Pile discardPile;
	private Pile drawPile;
	public Deck() {
		discardPile = new Pile();
		drawPile = new Pile();
		for (int i = 0; i < 3; i++) {
			drawPile.add(new VictoryCard(Config.ESTATE));
		}
		
		for (int j = 0; j < 7; j++) {
			drawPile.add(new TreasureCard(Config.COPPER));
		}
		//System.out.println("start:\n" + drawPile);
		shuffle();
		//System.out.println("end:" + drawPile);

		hand = new Hand();
		
	}
	
	
	
	private void shuffle() {
		ArrayList<Card> temp = new ArrayList<Card>();
		
		if (!discardPile.hasNextCard()) {
			discardPile.addAll(drawPile);
			
		}
		
		//System.out.println(discardPile);
		while (discardPile.hasNextCard()) {
			
			temp.add(discardPile.getNextCard());
		}
			
		
		Collections.shuffle(temp);
		
		
		
		for (int i = 0; i  < temp.size(); i++) {
			drawPile.add(temp.get(i));
		}
		
		
	}
	
	
	public void newTurn() {
		ArrayList<Card> toAdd = hand.discardAll();
		for (int i = 0; i < toAdd.size(); i ++) {
			addToDiscard(toAdd.get(i));
		}
		
		hand = new Hand();
		
		for (int j = 0; j < 5; j++) {
			draw();
		}
		
		
	}

	public Hand getHand() {
		return this.hand;
	}
	
	public void addToDeck(Card card) {
		
		addToDiscard(card);
	}

	public Pile getDrawPile() {
		// TODO Auto-generated method stub
		return this.drawPile;
	}

	public Pile getDiscardPile() {
		// TODO Auto-generated method stub
		return this.discardPile;
	}

	public void addToDiscard(Card card) {
		// TODO Auto-generated method stub
		discardPile.add(card);
	}

	public void drawCards(int j) {
		for (int i = 0; i < j; i++) {
			draw();
		}
	}
	
	private void draw() {
		// TODO Auto-generated method stub
		if (drawPile.hasNextCard()) {
			hand.add(drawPile.getNextCard());
		} else {
			shuffle();
			draw();
		}
		
		
	}
	
	
}
