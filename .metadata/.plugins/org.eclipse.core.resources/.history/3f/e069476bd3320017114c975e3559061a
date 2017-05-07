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
							if (pits.get(i).contains(m.getPoint()) && gameModel.moveable()) {
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
	
	
	
	
	
	@Override
	public void stateChanged(ChangeEvent e) {
		
		
	}

}
