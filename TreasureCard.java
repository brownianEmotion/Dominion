import javax.swing.ImageIcon;

public class TreasureCard extends Card {
	
	private int value;
	
	public TreasureCard(String cardName) {
		
		super(cardName, Config.TREASURE ,new ImageIcon("Cards/" + cardName + ".jpg"));
		switch (cardName) {
		case Config.COPPER:
			value = 1;
			break;
		case Config.SILVER:
			value = 2;
			break;
		case Config.GOLD:
			value =3;
			break;
		}
	}
	
	
	
	
	
	public int getValue() {
		return value;
		
	}
}
