import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * Builds Frame that contains PanelComponent, Selection Board Panel, Undo button Panel 
 *
 */
public class Main extends JFrame{
    
     
      private GameView gameView;
      private boolean BoardVisible;
      final GameModel gameModel;

    /**
     * Creates a new MancalaMain
     */
      
  public Main() {
        BoardVisible = true; // Displays Selections Board Panel

        gameModel = new GameModel();
        gameView = new GameView(gameModel, new HighTechBoardFormat());

        final JPanel lowerPanel = new JPanel();
        JButton undo = new JButton("undo");
        undo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameModel.undoMove();
            }
        });

        final JPanel selectionBoardPanel = new JPanel();

        JLabel label = new JLabel("Select style and number of stones");
        selectionBoardPanel.add(label);

        //strategy pattern comboboxes

        JButton submit = new JButton("Submit");
        String[] comboint = {"select", "3", "4"};
        String[] comboformat = {"select", "High Tech Board", "Oak Board"};
        final JComboBox combo = new JComboBox(comboint);
        final JComboBox comboselectformat = new JComboBox(comboformat);

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
        
        selectionBoardPanel.add(comboselectformat);
        selectionBoardPanel.add(combo);
        selectionBoardPanel.add(submit);
        lowerPanel.add(undo);
        
        add(selectionBoardPanel, BorderLayout.NORTH);
        add(gameView, BorderLayout.CENTER);
        add(lowerPanel, BorderLayout.SOUTH);
        setButtonVisible(false, lowerPanel);
  
    }
    
    
    /**
     * Creates MancalaMain which starts the game.
     * @param args the arguments
     */
    public static void main(String[] args)
    {
        Main main = new Main();  
        
        main.setSize(800, 550);
        main.setTitle("Mancala");
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setVisible(true);
        
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