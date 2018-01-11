
import java.util.Iterator;

public class Player {
	private Deck deck;
	private int coins;
	private int buys;
	private int actions;
	private String name;
	private String phase;
	private int totalPoints;
	
	public Player(String name) {
		this.name = name;
		deck = new Deck();
		coins = 0;
		buys = 1;
		actions =1;
		phase = "action";
		totalPoints = 3;
	}
	
	
	
	public String getCurrentPhase() {
		return this.phase;
	}
	
	public String getName() {
		return name;
	}
	
	public int playTreasures() {
		phase = "buy";
		return coins + hand().getHandValue();
	}

	public Deck deck() {
		return deck;
	}
	
	boolean hasActions() {
		return actions >= 0;
	}
	
	public int getActions() {
		return actions;
	}

	
	public int getBuys() {
		return buys;
	}
	
	public boolean hasBuys() {
		return buys > 0;
	}
	
	public void draw(int j) {
		deck.drawCards(j);
	}
	
	public void addToDiscard(Card card) {
		deck.addToDiscard(card);
	}
	
	public int extraCoins() {
		return coins;
	}
	
	public Hand newTurn() {
		deck.newTurn();
		phase = "action";
		coins = 0;
		buys = 1;
		actions =1;
		
		return deck.getHand();
	}
	
	public int getTotalPoints() {
		return this.totalPoints;
	}
	
	
	public void buy(Card card) {
		phase = "buy";
		
		if (card.getCost() >= coins) {
			buys--;
			deck.addToDiscard(card);
			coins = coins - card.getCost();
			if (card.getName().equals(Config.DUCHY)) {
				totalPoints += 3;
			} else if (card.getName().equals(Config.ESTATE)) {
				totalPoints += 1;
			} else if (card.getName().equals(Config.DUCHY)) {
				totalPoints +=6;
			}
		}
		
	}
	
	public Hand hand() {
		return deck.getHand();
	}
	
	public Iterator<Card> iterator() {
		return hand().iterator();
	}
	
	public void playCard(ActionCard card) {
		ActionCard toPlay = deck.getHand().playCard(card);
		if (!(toPlay == null)) {
			this.actions--;
			this.actions += card.extraActions();
			this.buys += card.extrabuys();
			this.coins = card.extrabuys();
			if (card.hasExtraDraws()) {
				draw(card.extraDraws());
			}
			if (!hasActions()) {
				phase = "buy";
			}
		}		
		
		
	}
	

	
	
	
	
	
	
	
	
	
	

	
	
	
}
