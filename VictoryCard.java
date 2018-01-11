import javax.swing.ImageIcon;

public class VictoryCard extends Card {

	private int points;
	
	public VictoryCard(String cardName) {
		this(cardName, new ImageIcon("Cards/" + cardName + ".jpg"));
			
	}
	
	public VictoryCard(String cardName, ImageIcon img) {
		super( cardName, Config.VICTORY, img);
		if (cardName.equals(Config.DUCHY)) {
			
			points = 3;
		} else if (cardName.equals(Config.ESTATE)) {
			
			points = 1;
		} else if (cardName.equals(Config.PROVINCE)) {
			
			points = 6;
		} else if (cardName.equals(Config.CURSE)) {
			
			points = -1;
		}
		
		
		
	}
	
	public int getPoints() {
		return this.points;
	}

}
