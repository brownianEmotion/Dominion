import java.util.ArrayList;
import java.util.Iterator;

public class Mat {
	
	private Pile provincePile;
	private Pile duchyPile;
	private Pile estatePile;
	private Pile cursePile;
	
	private Pile copperPile;
	private Pile silverPile;
	private Pile goldPile;
	
	private ArrayList<Pile> supplyPiles;
	
	private int pilesOut;
	
	
	public Mat(ArrayList<Card> cardsToUse) {
		supplyPiles = new ArrayList<Pile>();
		
		pilesOut = 0;
		setUpGame(cardsToUse);
	}
	
	public ArrayList<Pile> getAllPiles() {
		ArrayList<Pile> toReturn = new ArrayList<Pile>();
		toReturn.add(provincePile);
		toReturn.add(duchyPile);
		toReturn.add(estatePile);
		toReturn.add(cursePile);
	
		toReturn.add(copperPile);
		toReturn.add(silverPile);
		toReturn.add(goldPile);
		
		toReturn.addAll(supplyPiles);
		return toReturn;
		
	}
	
	public int numPilesOut() {
	
		return pilesOut;
	}
	
	public int getNumProvince() {
		return provincePile.size();
	}
	
	private void addCardToMat(Card card) {
		if (card.getType().equals( Config.ACTION)) {
			Pile toAdd = new Pile(card);
			supplyPiles.add(toAdd);
			
		} else if (card.getName().equals(Config.PROVINCE)) {
			provincePile = new Pile(card);
		} else if (card.getName().equals(Config.DUCHY)) {
			duchyPile = new Pile(card);
		} else if (card.getName().equals(Config.ESTATE)) {
			estatePile= new Pile(card);
		} else if (card.getName().equals(Config.CURSE)) {
			cursePile= new Pile(card);
		} else if (card.getName().equals(Config.COPPER)) {
			copperPile= new Pile(card);
		} else if (card.getName().equals(Config.SILVER)) {
			silverPile= new Pile(card);
		} else if (card.getName().equals(Config.GOLD)) {
			goldPile= new Pile(card);
		}
	}
	
	private void setUpGame(ArrayList<Card> cardsToUse) {
		
		
		for (int i = 0; i < cardsToUse.size(); i++) {
			addCardToMat(cardsToUse.get(i));
		}
		
		
		addCardToMat(new VictoryCard(Config.PROVINCE));
		addCardToMat(new VictoryCard(Config.DUCHY));
		addCardToMat(new VictoryCard(Config.ESTATE));
		addCardToMat(new VictoryCard(Config.CURSE));
		
		
		addCardToMat(new TreasureCard(Config.COPPER));
		addCardToMat(new TreasureCard(Config.SILVER));
		addCardToMat(new TreasureCard(Config.GOLD));
		//addCard(new Card());
	}
	
	public int totalPiles() {
		return 7 + supplyPiles.size();
	}
	//allows you to buy a card specifying only a string
	public Card buyCardFromMat(String card) throws StringMismatchException, EmptyPileException {
		Card toReturn = convert(card);
		return toReturn;
	}
	
	public Iterator<Card> iterator() {
		ArrayList<Card> toIterate = new ArrayList<Card>();
		Iterator<Pile> pileItr = getAllPiles().iterator();
		while (pileItr.hasNext()) {
			toIterate.add(pileItr.next().viewNextCard());
		}
		
		return toIterate.iterator();
		
	}
	
	public Card convert(String card) throws StringMismatchException {
		switch(card) {
		case Config.COPPER:
			if(copperPile.hasNextCard()) {
				if (copperPile.size() == 1) {
					pilesOut++;
				}
				return copperPile.getNextCard();
				
			}
		case Config.SILVER:
			if (silverPile.hasNextCard()) {
				if (silverPile.size() == 1) {
					pilesOut++;
				}
				return silverPile.getNextCard();
			}
		case Config.GOLD:
			if (goldPile.hasNextCard()) {
				if (goldPile.size() == 1) {
					pilesOut++;
				}
				return goldPile.getNextCard();
			}
		case Config.ESTATE:
			if (estatePile.hasNextCard()) {
				if (estatePile.size() == 1) {
					pilesOut++;
				}
				return estatePile.getNextCard();
			}
		case Config.PROVINCE:
			if (provincePile.hasNextCard()) {
				if (provincePile.size() == 1) {
					pilesOut++;
				}
				return provincePile.getNextCard();
			}
		case Config.DUCHY:
			if (duchyPile.hasNextCard()) {
				if (duchyPile.size() == 1) {
					pilesOut++;
				}
				return duchyPile.getNextCard();
			}
		case Config.CURSE:
			if (cursePile.hasNextCard()) {
				if (cursePile.size() == 1) {
					pilesOut++;
				}
				return cursePile.getNextCard();
			}
		case Config.VILLAGE:
			for (int i = 0; i < supplyPiles.size(); i++) {
				if (supplyPiles.get(i).getOnlyCardInPile().getName().equals(Config.VILLAGE)) {
					if (supplyPiles.get(i).size() == 1) {
						pilesOut++;
					}
					return supplyPiles.get(i).getNextCard();
				}
			}
		case Config.FESTIVAL:
			for (int i = 0; i < supplyPiles.size(); i++) {
				if (supplyPiles.get(i).getOnlyCardInPile().getName().equals(Config.FESTIVAL)) {
					if (supplyPiles.get(i).size() == 1) {
						pilesOut++;
					}
					return supplyPiles.get(i).getNextCard();
				}
			}
		}
		throw new StringMismatchException();
	}
	
	
	public Pile getProvincePile() {
		return provincePile;
	}
	
	public Card buy(Card card)  {
		switch(card.getName()) {
		case Config.COPPER:
			if(copperPile.hasNextCard()) {
				if (copperPile.size() == 1) {
					pilesOut++;
				}
				return copperPile.getNextCard();
				
			}
		case Config.SILVER:
			if (silverPile.hasNextCard()) {
				if (silverPile.size() == 1) {
					pilesOut++;
				}
				return silverPile.getNextCard();
			}
		case Config.GOLD:
			if (goldPile.hasNextCard()) {
				if (goldPile.size() == 1) {
					pilesOut++;
				}
				return goldPile.getNextCard();
			}
		case Config.ESTATE:
			if (estatePile.hasNextCard()) {
				if (estatePile.size() == 1) {
					pilesOut++;
				}
				return estatePile.getNextCard();
			}
		case Config.PROVINCE:
			if (provincePile.hasNextCard()) {
				if (provincePile.size() == 1) {
					pilesOut++;
				}
				return provincePile.getNextCard();
			}
		case Config.DUCHY:
			if (duchyPile.hasNextCard()) {
				if (duchyPile.size() == 1) {
					pilesOut++;
				}
				return duchyPile.getNextCard();
			}
		case Config.CURSE:
			if (cursePile.hasNextCard()) {
				if (cursePile.size() == 1) {
					pilesOut++;
				}
				return cursePile.getNextCard();
			}
		case Config.VILLAGE:
			for (int i = 0; i < supplyPiles.size(); i++) {
				if (supplyPiles.get(i).getOnlyCardInPile().getName().equals(Config.VILLAGE)) {
					if (supplyPiles.get(i).size() == 1) {
						pilesOut++;
					}
					return supplyPiles.get(i).getNextCard();
				}
			}
		case Config.FESTIVAL:
			for (int i = 0; i < supplyPiles.size(); i++) {
				if (supplyPiles.get(i).getOnlyCardInPile().getName().equals(Config.FESTIVAL)) {
					if (supplyPiles.get(i).size() == 1) {
						pilesOut++;
					}
					return supplyPiles.get(i).getNextCard();
				}
			}
		}
		
		return null;
	}

	public int getSize() {
		// TODO Auto-generated method stub
		return supplyPiles.size() + 7;
	}
	
}
