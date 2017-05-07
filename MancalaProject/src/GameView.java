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

        PitShape leftPit = new PitShape(7, 60, pitWidth, 3*pitHeight-100);
        leftPit.setShape(boardFormat.formatPitShape(leftPit));
        leftPit.setMarbles(marbles[6]);

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

        PitShape rightPit = new PitShape(693 , 60, pitWidth, 3*pitHeight-100);
        rightPit.setShape(boardFormat.formatPitShape(rightPit));
        rightPit.setMarbles(marbles[13]);

        // Add Shapes in right order
        addShape(pit7);
        addShape(pit8);
        addShape(pit9);
        addShape(pit10);
        addShape(pit11);
        addShape(pit12);   
        addShape(rightPit);
        addShape(pit5);
        addShape(pit4);
        addShape(pit3);
        addShape(pit2);
        addShape(pit1);
        addShape(pit0);
        addShape(leftPit);
	}
	
	
	
	
	
	@Override
	public void stateChanged(ChangeEvent e) {
		
		
	}

}
