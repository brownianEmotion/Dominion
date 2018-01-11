import java.util.ArrayList;
import java.util.Iterator;

public class Hand {
	private ArrayList<Card> hand;
	
	public Hand() {
		hand = new ArrayList<Card> ();
	}
	
	public void add(Card card) {
		hand.add(card);
	}
	
	public int getSize() {
		return hand.size();
	}
	
	public int getHandValue() {
		int toReturn = 0;
		for (int i = 0; i < hand.size(); i++) {
			if (hand.get(i).getType().equals(Config.TREASURE)) {
				TreasureCard temp = (TreasureCard) hand.get(i);
				toReturn += temp.getValue();
			}
		}
		
		return toReturn;
	}
	
	//play a card from the hand's perspective
	public ActionCard playCard(Card card) {
		ActionCard toReturn = null;
		if (hand.contains(card) && card.getType().equals(Config.ACTION)) {
			toReturn = (ActionCard) hand.get(hand.indexOf(card));
			hand.remove(card);
		}
		
		return toReturn;
	}
	
	public ArrayList<Card> discardAll() {
		ArrayList<Card> temp = new ArrayList<Card>();
		for (int i = hand.size() - 1; i >= 0; i--) {
			temp.add(hand.get(i));
			hand.remove(i);
			
		}
		
		return temp;
	}
	
	public Iterator<Card> iterator() {
		return hand.iterator();
	}

	

	public boolean contains(String cardname) {
		for (int i = 0; i < hand.size(); i++) {
			if (hand.get(i).getName().equals(cardname)) {
				return true;
			}
		}
		return false;
	}

	public Card convert(String card) throws StringMismatchException {
		if (this.contains(card)) {
			for (int i = 0; i < hand.size(); i++) {
				if (hand.get(i).getName().equals(card)) {
					return hand.get(i);
				}
			}
		}	
		throw new StringMismatchException();
	}

}
