import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.event.*;
import java.net.*;

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
	
	public void updateGame() {
		pits = new ArrayList<PitShape>();
		shapes = new ArrayList<Shape>();
		
		final int pitHeight = 150;
        final int pitWidth = 80; 
        final int pitTopY = 30;
        final int pitBotY = 290; 
        
        PitShape pit0 = new PitShape(pitWidth + pitWidth/4, pitTopY, pitWidth, pitHeight);
        pit0.setShape(boardFormat.formatPitShape(pit0));
        pit0.setMarbles(marbles[0]);

       
	}
	public void paintComponent(Graphics g) {

		g.drawImage(boardFormat.backgroundImg(), 0, 0, this);

		Graphics2D g2 = (Graphics2D) g;

		for(Shape s: shapes)
			g2.draw(s);

		for(PitShape p: pits) {
			p.fill(g2);
		}

		//player information
		String turnInfo = "";
		if (gameModel.getGameState() == GameModel.STATE.INCOMPLETE)
		{
			if (gameModel.getCurrentPlayer() == GameModel.Player.ONE) {
				turnInfo = "Player One's Turn";
			}
			else {
				turnInfo = "Player Two's Turn";
			}
		}
		else if (gameModel.getGameState() == GameModel.STATE.COMPLETE)
		{
			turnInfo = "Final Score: " + "Player One's " + gameModel.playerScoreCard(GameModel.Player.ONE) + " vs " + "Player 2 " + gameModel.playerScoreCard(GameModel.Player.TWO);
		}
		g.setColor(Color.BLACK);//Set g color to black for font

		g2.drawString(turnInfo, (getWidth()/2)-80,getHeight()/2); //print turn information
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
