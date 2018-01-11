import java.awt.Image;

import javax.swing.ImageIcon;

public class Card {
	private int cost;
	private String cardType;
	private String cardName;
	private ImageIcon img;
	//image
	
	public Card(int cost, String cardName,String cardType) {
		this.cost = cost;
		this.cardType = cardType;
		this.cardName = cardName;
		img = null;
		
	}
	
	public boolean hasImageIcon() {
		return(!(img == null));
	}

	public Card(int cost, String cardName,String cardType, ImageIcon img) {
		this.cost = cost;
		this.cardType = cardType;
		this.cardName = cardName;
		ImageIcon imageIcon = img;
    	Image image = imageIcon.getImage(); // transform it 
    	Image newimg = image.getScaledInstance(90, 150,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
    	imageIcon = new ImageIcon(newimg);
		this.img = imageIcon;
	}
	
	public Card(String cardName, String cardType) {
		this.cardType = cardType;
		this.cardName = cardName;
		
		this.cost = findCost();
	}
	
	public Card(String cardName, String cardType, ImageIcon img) {
		this.cardType = cardType;
		this.cardName = cardName;
		ImageIcon imageIcon = img;
    	Image image = imageIcon.getImage(); // transform it 
    	Image newimg = image.getScaledInstance(90, 150,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
    	imageIcon = new ImageIcon(newimg);
		this.img = imageIcon;
		this.cost = findCost();
	}
	


	public String toString() {
		return this.cardName;
	}
	
	public ImageIcon getImageIcon() {
		return img;
	}
	
	private int findCost() {
		switch (cardName) {
		case Config.CURSE:
			return(Config.CURSE_COST);
		case Config.ESTATE:
			return(Config.ESTATE_COST);
		case Config.PROVINCE:
			return (Config.PROVINCE_COST);
		case Config.DUCHY:
			return Config.DUCHY_COST;
		
		case Config.COPPER:
			return Config.COPPER_COST;
		case Config.GOLD:
			return Config.GOLD_COST;
		case Config.SILVER:
			return Config.SILVER_COST;
		
		case Config.VILLAGE:
			return Config.VILLAGE_COST;
		case Config.FESTIVAL:
			return Config.FESTIVAL_COST;
		
		}
		
	return 0;
	}
	
	public int getCost() {
		return cost;
	}
		
	public String getName() {
		return cardName;
	}
	
	public String getType() {
		return cardType;
	}
	
}
