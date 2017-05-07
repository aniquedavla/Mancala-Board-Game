import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * @author Anique Davla, Brian Lai
 */
public class GameView extends JComponent implements ChangeListener {

	private ArrayList<Shape> shapes;
	private ArrayList<PitShape>	pits;
	private GameModel gameModel;
	private BoardFormat boardFormat;
	private int[] marbles;
	
	public GameView(GameModel gameModel, BoardFormat boardFormat) {
		this.gameModel = gameModel;
		this.boardFormat = boardFormat;
		marbles = new int[14];
		gameModel.attach(this);
		
		addMouseListener(new 
				MouseAdapter() {
					public void mousePressed(MouseEvent m) {
						for (int i = 0; i < pits.size(); i++) {
							if (pits.get(i).contains(m.getPoint()) && gameModel.canPlay(i)) {
								gameModel.move(i);
								return;
							}
						}
					}
				});
		
	}
	
	public void addPit(PitShape ps) {
		pits.add(ps);
	}
	
	public void updateGame() {
		pits = new ArrayList<PitShape>();
//		shapes = new ArrayList<Shape>();
		
		final int pitHeight = 150;
        final int pitWidth = 80; 
        final int pitTopY = 30;
        final int pitBotY = 290; 
        
        PitShape pit0 = new PitShape(pitWidth + pitWidth/4, pitTopY, pitWidth, pitHeight);
        pit0.setShape(boardFormat.formatPitShape(pit0));
        pit0.setMarbles(marbles[0]);
        
        PitShape pit1 = new PitShape(2*pitWidth + pitWidth/2, pitTopY, pitWidth, pitHeight); //adds a pit 1
        pit1.setShape(boardFormat.formatPitShape(pit1));
        pit1.setMarbles(marbles[1]);

        PitShape pit2 = new PitShape(3*pitWidth + 3*pitWidth/4, pitTopY, pitWidth, pitHeight); //adds a pit 2
        pit2.setShape(boardFormat.formatPitShape(pit2));
        pit2.setMarbles(marbles[2]);

        PitShape pit3 = new PitShape(5*pitWidth, pitTopY, pitWidth, pitHeight); //adds a pit 3
        pit3.setShape(boardFormat.formatPitShape(pit3));
        pit3.setMarbles(marbles[3]);

        PitShape pit4 = new PitShape(6*pitWidth + pitWidth/4, pitTopY, pitWidth, pitHeight); //adds a pit 4
        pit4.setShape(boardFormat.formatPitShape(pit4));
        pit4.setMarbles(marbles[4]);

        PitShape pit5 = new PitShape(7*pitWidth + pitWidth/2, pitTopY, pitWidth, pitHeight); //adds a pit 5
        pit5.setShape(boardFormat.formatPitShape(pit5));
        pit5.setMarbles(marbles[5]);

        PitShape leftMancala = new PitShape(7, 60, pitWidth, 3*pitHeight-100);
        leftMancala.setShape(boardFormat.formatPitShape(leftMancala));
        leftMancala.setMarbles(marbles[6]);

        //bottom pits
        PitShape pit7 = new PitShape(pitWidth + pitWidth/4, pitBotY, pitWidth, pitHeight);
        pit7.setShape(boardFormat.formatPitShape(pit7));
        pit7.setMarbles(marbles[7]);

        PitShape pit8 = new PitShape(2*pitWidth + pitWidth/2, pitBotY, pitWidth, pitHeight);
        pit8.setShape(boardFormat.formatPitShape(pit8));
        pit8.setMarbles(marbles[8]);

        PitShape pit9 = new PitShape(3*pitWidth + 3*pitWidth/4, pitBotY, pitWidth, pitHeight);
        pit9.setShape(boardFormat.formatPitShape(pit9));
        pit9.setMarbles(marbles[9]);

        PitShape pit10 = new PitShape(5*pitWidth, pitBotY, pitWidth, pitHeight);
        pit10.setShape(boardFormat.formatPitShape(pit10));
        pit10.setMarbles(marbles[10]);

        PitShape pit11 = new PitShape(6*pitWidth + pitWidth/4, pitBotY, pitWidth, pitHeight);
        pit11.setShape(boardFormat.formatPitShape(pit11));
        pit11.setMarbles(marbles[11]);

        PitShape pit12 = new PitShape(7*pitWidth + pitWidth/2, pitBotY, pitWidth, pitHeight);
        pit12.setShape(boardFormat.formatPitShape(pit12));
        pit12.setMarbles(marbles[12]);

        PitShape rightMancala = new PitShape(693 , 60, pitWidth, 3*pitHeight-100);
        rightMancala.setShape(boardFormat.formatPitShape(rightMancala));
        rightMancala.setMarbles(marbles[13]);

        // Add Shapes in right order
        addPit(pit7);
        addPit(pit8);
        addPit(pit9);
        addPit(pit10);
        addPit(pit11);
        addPit(pit12);   
        addPit(rightMancala);
        addPit(pit5);
        addPit(pit4);
        addPit(pit3);
        addPit(pit2);
        addPit(pit1);
        addPit(pit0);
        addPit(leftMancala);
	}
	
	public void paintComponent(Graphics g) {

		g.drawImage(boardFormat.backgroundImg(), 0, 0, this);

		Graphics2D g2 = (Graphics2D) g;

//		for(Shape s: shapes) {
//			g2.draw(s);
//		}
		for(PitShape p: pits) {
			p.fill(g2);
		}

		//player information
		String turnInfo = "";
		String finalScore = "";

		if (gameModel.getGameState() == GameModel.STATE.INCOMPLETE)
		{
			if (gameModel.getCurrentPlayer() == GameModel.Player.ONE) {
				turnInfo = "Player One's Turn";
			}
			else {
				turnInfo = "Player Two's Turn";
			}
		}
		else if (gameModel.getGameState() == GameModel.STATE.COMPLETE) {

			turnInfo = "Final Score: ";
			finalScore = "Player One's " + gameModel.playerScoreCard(GameModel.Player.ONE) + " vs " + "Player Two's " + gameModel.playerScoreCard(GameModel.Player.TWO);
		}
		g.setColor(Color.BLACK);//Set g color to black for font

		g2.drawString(turnInfo, (getWidth()/2)-200,getHeight()/2); //print turn information
		g2.drawString(finalScore,(getWidth()/2)-200,getHeight()/2+20);
	}

	public void setVisibility(boolean visibility) {

		this.setVisible(visibility);
	}
	
	public void setBoardFormat(BoardFormat formatType) {

		boardFormat = formatType;
		updateGame();
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {

		updateGame();

		//marbles from model
		marbles = gameModel.getAllPits();

		int sizeOfPit = pits.size();
		//update the pits with the marbles from the model
		for(int i = 0; i < sizeOfPit; i++) {
			pits.get(i).setMarbles(marbles[i]);
		}
		repaint();
		
	}

	public void start() {

		setVisibility(true);
		gameModel.setState("INCOMPLETE");
	}


}
