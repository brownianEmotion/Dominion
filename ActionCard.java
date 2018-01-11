import javax.swing.ImageIcon;

public class ActionCard extends Card {
	private int plusActions;
	private int plusBuys;
	private int plusDraws;
	private int plusCoins;
	
	
	public ActionCard(String name) {
		this(name, new ImageIcon("Cards/" + name + ".jpg"));
		
			
	}
	
	public ActionCard(String name, ImageIcon pic) {
		super(name, Config.ACTION, pic);
		
		
		
		switch (name) { 
			case Config.VILLAGE:
				plusActions = 2;
				plusDraws = 1;
			
				break;
			case (Config.FESTIVAL):
				plusBuys = 1;
				plusCoins = 2;
				plusActions =2;
		
				break;
			
		}
			
	}
	
	public int extraActions() {
		return plusActions;
	}
	
	public boolean hasExtraActions() {
		return plusActions > 0;
	}
	
	public int extraDraws() {
		return plusDraws;
	}
	
	public boolean hasExtraDraws() {
		return plusDraws > 0;
	}
	
	public int extrabuys() {
		return plusBuys;
	}
	
	public boolean hasExtrabuys() {
		return plusBuys > 0;
	}
	
	public int extraCoins() {
		return plusCoins;
	}
	
	public boolean hasExtraCoins() {
		return plusCoins > 0;
	}
}
