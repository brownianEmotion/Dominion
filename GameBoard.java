import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.util.*;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import net.miginfocom.swing.MigLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.TitledBorder;


@SuppressWarnings("serial")
public class GameBoard extends BackgroundPanel {
	
//grabbed from some thing online...
	private static final int DEFAULT_HEIGHT = 302;
	/** Width of the game frame. */
	private static final int DEFAULT_WIDTH = 800;
	/** Width of a card. */
	private static final int CARD_WIDTH = 73;
	/** Height of a card. */
	private static final int CARD_HEIGHT = 97;
	/** Row (y coord) of the upper left corner of the first card. */
	private static final int LAYOUT_TOP = 30;
	/** Column (x coord) of the upper left corner of the first card. */
	private static final int LAYOUT_LEFT = 30;
	/** Distance between the upper left x coords of
	 *  two horizonally adjacent cards. */
	private static final int LAYOUT_WIDTH_INC = 100;
	/** Distance between the upper left y coords of
	 *  two vertically adjacent cards. */
	private static final int LAYOUT_HEIGHT_INC = 125;
	/** y coord of the "Replace" button. */
	private static final int BUTTON_TOP = 30;
	/** x coord of the "Replace" button. */
	private static final int BUTTON_LEFT = 570;
	/** Distance between the tops of the "Replace" and "Restart" buttons. */
	private static final int BUTTON_HEIGHT_INC = 50;
	/** y coord of the "n undealt cards remain" label. */
	private static final int LABEL_TOP = 160;
	/** x coord of the "n undealt cards remain" label. */
	private static final int LABEL_LEFT = 540;
	/** Distance between the tops of the "n undealt cards" and
	 *  the "You lose/win" labels. */
	private static final int LABEL_HEIGHT_INC = 35;


	
	ArrayList<String> cardsToUse;
	JButton playTreasureButton;
	JButton playCardButton;
	JButton endTurnButton;
	ArrayList<JButton> cardButtons;
	JTextPane progressBoard;
	Point progressBoardLocation;
	JTextPane currentPlayerPane;
	Point currentPlayerPaneLocation;
	TheListener genButton;
	JPanel gamePanel;
	Game game;
	Point[] cardLocations; //where all the cards will be located
	JPanel currentPlayerPanel;
	Point currentPlayerPanelLocation;
	JPanel matPanel; //mat panel
	JPanel handPanel; //current player hand panel
	
	
	
	
	public GameBoard() {
		super(new ImageIcon("Cards/background.jpg").getImage());
		setTransparentAdd(false);
		setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		
		//basic game engine setup:
		cardsToUse = new ArrayList<String>();
		for (int i = 0; i < Config.STANDARD_CARDS.length; i++) {
			cardsToUse.add(Config.STANDARD_CARDS[i]);
		}
		game = new Game( cardsToUse);
		
		
		//start setting up GUI:
		gamePanel = new JPanel();
		
		//general listener
		genButton = new TheListener();
	    
	    //playing treasures:
	    playTreasureButton = new JButton();
	    playTreasureButton.setFont(new Font("Sitka Text", Font.PLAIN, 16));
	    playTreasureButton.setBackground(Color.LIGHT_GRAY);
	    playTreasureButton.addActionListener(genButton);
	    playTreasureButton.setText("Play Treasures");
	    
	    //playing cards:
	    playCardButton = new JButton();
	    playCardButton.setFont(new Font("Sitka Text", Font.PLAIN, 16));
	    playCardButton.setBackground(Color.LIGHT_GRAY);
	    playCardButton.addActionListener(genButton);
	    playCardButton.setText("Play Card");
	    
	    //ending turn:
	    endTurnButton = new JButton();
	    endTurnButton.setFont(new Font("Sitka Text", Font.PLAIN, 16));
	    endTurnButton.setBackground(Color.LIGHT_GRAY);
	    endTurnButton.addActionListener(genButton);
	    endTurnButton.setText("End Turn");
	    
	    cardButtons = new ArrayList<JButton>();
	    matPanel = new JPanel();
	    matPanel.setLayout(new GridLayout(3,5));
	    Iterator<Card> cardItr = game.getMat().iterator();
	    
	    while (cardItr.hasNext()) {
	    	Card nextCard = cardItr.next();
	    	ImageIcon imageIcon = nextCard.getImageIcon();
	    	Image image = imageIcon.getImage(); // transform it 
	    	Image newimg = image.getScaledInstance(90, 150,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
	    	imageIcon = new ImageIcon(newimg);  // transform it back
	    	cardButtons.add(new JButton(imageIcon));
	    	
	    }
	    
	      
	    int xAxis = 0;
	    int yAxis = 0;
	    for (int i = 0; i < cardButtons.size(); i++) {
	    	cardButtons.get(i).addActionListener(genButton);
	    	
	    	matPanel.add(cardButtons.get(i),yAxis,xAxis );
	    	xAxis++;
	    	if(xAxis == 5) {
	    		xAxis = 0;
	    		yAxis++;
	    	}
	    	
	    }
	    
	    
	    currentPlayerPane = new TransparentTextPane();
	    currentPlayerPane.setOpaque(true);
	    currentPlayerPane.setFont(new Font("Sitka Text", Font.BOLD, 24));
	    currentPlayerPane.setText("Current Player:" + game.getCurrentPlayer().getName() +
	    		"\nNumber Provinces: 8 \nNumber Piles Out: 0");
	    currentPlayerPane.setLocation(10, 10);
	    currentPlayerPane.setSize(50,50);
	    currentPlayerPane.setBackground(Color.LIGHT_GRAY);
	    
		
		gamePanel.setLayout(new GridLayout());
	    gamePanel.add(playTreasureButton);
	    gamePanel.add(playCardButton);
	    gamePanel.add(endTurnButton);
	    
	    handPanel = new JPanel();
	    handPanel.setLayout(new FlowLayout());
	    /*Iterator<Card> itr = game.getCurrentPlayer().hand().iterator();
	  
	    while (itr.hasNext()) {
			
			Card curr = itr.next();
			System.out.println( curr.getName());
			JButton card = new JButton(curr.getImageIcon());
			handPanel.add(card);
		}*/
	    	    
	    setLayout(new MigLayout("", "[1920.00px,center]", "[15.00px][27.00px:n,center]"));
	    add(currentPlayerPane, "cell 0 0,alignx center,aligny center");
	    add(matPanel, "cell 0 2,alignx center,aligny center");
	    add(gamePanel,"cell 0 1,alignx center,aligny center");
	    add(handPanel, "cell 0 10, alignx center, aligny center");
	    makeComponentTransparent(matPanel);
	    makeComponentTransparent(gamePanel);
	  	makeComponentTransparent(handPanel);
	   
	   
	   
	}
	  
	public void paint(Graphics g) {
		
		super.paint(g);
		
		//draw the hand:
		Iterator<Card> itr = game.getCurrentPlayer().hand().iterator();
	    handPanel.removeAll();
		while (itr.hasNext()) {
			
			Card curr = itr.next();
			JButton card = new JButton(curr.getImageIcon());
			handPanel.add(card);
		}
		
		//Draw the game event log
		currentPlayerPane.setText("Current Player:" + game.getCurrentPlayer().getName() +
	    		"\nNumber Provinces: " + 
				game.numProvincesLeft() + "\nNumber Piles Out: " +
	    		game.numPilesOut());
		
	}

	 public void display() {
	    JFrame myFrame = new JFrame("Dominion");
	    
	    myFrame.setIconImage(new ImageIcon("Cards/dominion.jpg").getImage());
	    
	    myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    myFrame.setContentPane(this);
	    myFrame.setPreferredSize(new Dimension(2000,1000));

	    //Display the window.
	    myFrame.pack();
	    myFrame.setVisible(true);
	    
	  }//end display

	 
	
	 
	 public static void main (String[] args) {
		 GameBoard frame = new GameBoard();
		 frame.display();
	 }

	 
	 /**
	  * Meant to set up the game.  gets cards to play with and creates
	  * instances of those cards.
	  */
	 private  ArrayList<String> setUpGame() {
		return null;
	 }
	
	 
	 class TheListener implements ActionListener {
		
		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub
			Object object = event.getSource();
			if (object == playTreasureButton) {
				playTreasures(event);
			} else if (object == playCardButton) {
				playCardButton(event);
			} else if (object == endTurnButton) {
				endTurn(event);
			} else if (!playCardButton.isEnabled()) {
				playCard(getCardToPlay(event));
			} //else if (object ==  )
				
			
		}
		
		void playTreasures(ActionEvent event) {
			int coins = game.getCurrentPlayer().playTreasures(); 
			playTreasureButton.setText("You have " + coins 
					+ " coins.\nChoose which card to buy");
			//repaint();
			
		}
		
		void playCardButton(ActionEvent event) {
			//toDo
			playCardButton.setEnabled(false);
			playCardButton.setText("Click on Card to play"); 
			//repaint();
			
			
		}
		
		void buy(ActionEvent event) {
			
		}
		
		void playCard(Card card) {
			if (card.getType().equals("action")) {
				game.getCurrentPlayer().playCard((ActionCard) card);
				//repaint();
			}
			
			
		}
		
		Card getCardToPlay(ActionEvent event) {
			
			
			//TODO
			return null;
			
		}
		
		void endTurn(ActionEvent event) {
			game.nextTurn();
			
			//repaint();
		}
	 }
	 
	 /*
	  * Class to make buttons more transparent
	  * thanks to stackexchange for the idea
	  */
	 class TransparentTextPane extends JTextPane {
		
		 
		 public TransparentTextPane() {
			 super();
			 setOpaque(false);
		 }
		 
		 
		 public void paint(Graphics g) { 
		 
			 Graphics2D g2 = (Graphics2D) g.create(); 
		 
			 g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f)); 
		 
			 super.paint(g2); 
		 
			 g2.dispose(); 
		 
		 } 
		 
	}


	 
	 
	 
}
	

