
import java.util.Stack;

public class Pile {
	
	private Card cardName;
	private Stack<Card> pile;
	
	private boolean inSupply;
	//may need to change this card to just be a string
	public Pile(Card card) {
		// TODO Auto-generated constructor stub
		inSupply = true;
		this.pile = new Stack<Card>();
		this.cardName = card;
		if (card.getType().equals(Config.ACTION)) {
			for (int i = 0; i  < 10; i++) {
				
				pile.push(card);
				
			}
		} else if (card.getType().equals(Config.TREASURE)) {
			for (int i = 0; i < 50; i++) {
				pile.push(card);
			}
			
		} else if (card.getName().equals(Config.CURSE)) {
			for (int i = 0; i < 20; i++) {
				pile.push(card);
			}
		} else if (card.getType().equals(Config.VICTORY)) {
			for (int i = 0; i < 8; i ++) {
				pile.push(card);
			}
		}
	}
	

	public Pile() {
		this.pile = new Stack<Card>();
		inSupply = false;
		
	}
	
	public int size() {
		return pile.size();
	}
	
	public boolean isEmpty() {
		return pile.isEmpty();
	}
	public boolean isInSupply() {
		return inSupply;
	}
	
	//not sure what this is
	public Card getOnlyCardInPile()  {
		return cardName;
		
	}
	
	public void add(Card card) {
		pile.push(card);
		
	}
	
	public Card viewNextCard() {
		return pile.peek();
	}
	
	public void addAll(Pile p) {
		while(p.hasNextCard()) {
			pile.push(p.getNextCard());
		}
	}
	
	
	public Card getNextCard() {
		if (pile.size() == 0) {
			return null;
		}
		
		return pile.pop();
	}
	
	public boolean hasNextCard() {
		return !pile.isEmpty();
	}

	
	public String toString() {
		String toReturn = "";
		Stack<Card> temp = new Stack<Card>();
		while(!pile.isEmpty()){
			temp.add(pile.pop());
			toReturn += temp.peek().toString() + "\n";
		}
		
		while(!temp.isEmpty()){
			pile.add(temp.pop());
			
		}
		
		return toReturn;
	}
}
