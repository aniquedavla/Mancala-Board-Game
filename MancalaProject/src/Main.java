import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 *
 * Builds Frame that contains GameView, Selection Board Panel, Undo button Panel
 * @author Anique Davla, Brain Lai
 *
 */
public class Main extends JFrame{
    
     
      private GameView gameView;
      private boolean BoardVisible;
      final GameModel gameModel;

    /**
     * Makes a new Board with the appropiate rectangle and board formats
     */
      
  public Main() {
        super();
        BoardVisible = true; // Displays Selections Board Panel

        gameModel = new GameModel();
        gameView = new GameView(gameModel, new HighTechBoardFormat());

        final JPanel lowerPanel = new JPanel();
        JButton undo = new JButton("Undo");
        undo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameModel.undoMove();
            }
        });

        final JPanel selectionBoardPanel = new JPanel();
        JLabel mainLabel = new JLabel("Select board style and number of stones");

        final JPanel formatPanel = new JPanel();
        JLabel selectFormat = new JLabel("Select Board Format:");
        JLabel selectPits   = new JLabel("Select number of pits:");

        //strategy pattern comboboxes

        JButton submit = new JButton("Submit");
        String[] comboint = {"Select marbles", "3", "4"};
        String[] comboformat = {"Select", "High Tech Board", "Oak Board"};
        final JComboBox combo = new JComboBox(comboint);
        final JComboBox comboselectformat = new JComboBox(comboformat);

        selectionBoardPanel.add(mainLabel);
//        formatPanel.add(selectFormat);
//        selectionBoardPanel.add(selectFormat);
        selectionBoardPanel.add(comboselectformat);
//        selectionBoardPanel.add(selectPits);
        selectionBoardPanel.add(combo);
        selectionBoardPanel.add(submit);
        lowerPanel.add(undo);

        super.add(selectionBoardPanel, BorderLayout.NORTH);
//        add(formatPanel,BorderLayout.NORTH);
        super.add(gameView, BorderLayout.CENTER);
        super.add(lowerPanel, BorderLayout.SOUTH);
        setButtonVisible(false, lowerPanel);

        combo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                combo.setEnabled(false);
            }
        });
        comboselectformat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comboselectformat.setEnabled(false);
            }
        });

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String comboint = (String) combo.getSelectedItem();
                String comboformat = (String) comboselectformat.getSelectedItem();
                combo.setEnabled(false);
                comboselectformat.setEnabled(false);
                if (comboint.equals("3") && comboformat.equals("High Tech Board")) {

                    gameModel.setMarbles(3);
                    gameModel.setState("STARTED");
                    BoardVisible = false;
                    setButtonVisible(false, selectionBoardPanel);
                    gameView.setBoardFormat(new HighTechBoardFormat());
                    setButtonVisible(true, lowerPanel);
                } else if (comboint.equals("4") && comboformat.equals("High Tech Board")) {
                    gameModel.setMarbles(4);
                    gameModel.setState("STARTED");
                    BoardVisible = false;
                    setButtonVisible(false, selectionBoardPanel);
                    gameView.setBoardFormat(new HighTechBoardFormat());
                    setButtonVisible(true, lowerPanel);
                } else if (comboint.equals("3") && comboformat.equals("Oak Board")) {
                    gameModel.setMarbles(3);
                    gameModel.setState("STARTED");
                    BoardVisible = false;
                    setButtonVisible(false, selectionBoardPanel);
                    gameView.setBoardFormat(new OakBoardFormat());
                    setButtonVisible(true, lowerPanel);
                } else if (comboint.equals("4") && comboformat.equals("Oak Board")) {
                    gameModel.setMarbles(4);
                    gameModel.setState("STARTED");
                    BoardVisible = false;
                    setButtonVisible(false, selectionBoardPanel);
                    gameView.setBoardFormat(new OakBoardFormat());
                    setButtonVisible(true, lowerPanel);

                }else{
                    combo.setEnabled(true);
                    comboselectformat.setEnabled(true);
                }
            }
        });
  
    }
    
    
    /**
     * Creates MancalaMain which starts the game.
     * @param args the arguments
     */
    public static void main(String[] args) {
        Main main = new Main();
        main.setVisible(true);
        main.setSize(800, 550);
        main.setTitle("Mancala");
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    
     /**
     * sets the buttons on the frame visible or not depending on if the game is
     * in session
     *
     * @param visible true if visible
     */
    public void setButtonVisible(boolean visible, JPanel p) {
        p.setVisible(visible);
        //if both are false, start the Game.
        if (!BoardVisible) {
            gameView.start();
        }
    }
}