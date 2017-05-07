import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.ArrayList;

/**
 * Created by aniquedavla on 5/3/17.
 * GameModel is the MVC Model for the Mancala board, underlying data structure and alogrithms.
 */
public class GameModel {

    public static enum STATE {
        STARTED, INCOMPLETE, COMPLETE
    };

    public static enum Player {
        ONE, TWO
    };

    private Player currentPlayer;
    private STATE gameState;


    private final int totalUndos = 3;
    private int undosP1;
    private int undosP2;

    public static final int totalPits = 14;
    private int[] pits;
    private int[] pastPits;

    //pit 6 and 13 are the Mancalas
    public static final int mancalaP1 = 6;
    public static final int mancalaP2 = 13;


    private boolean lastStoneInMancala;
    private boolean undoable;
    private ArrayList<ChangeListener> listeners;

    /**
     *
     */
    public GameModel(){

        gameState = STATE.STARTED;
        currentPlayer = Player.ONE;
        undosP1 = 0;
        undosP2 = 0;
        pits = new int[totalPits];
        pastPits = new int[totalPits];
        lastStoneInMancala = false;
        undoable = false;
        listeners = new ArrayList<ChangeListener>();


    }

    public int[] getAllPits() {
        return pits;
    }

    public STATE getGameState() {
        return gameState;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    //getPP
    private Player playerOfPit(int pit) {

        if (pit >= 0 && pit <= mancalaP1) {
            return Player.ONE;
        }
        // else it has to be Player 2
        return Player.TWO;
    }

    private boolean isMancala(int pit) {
        return (pit == mancalaP1 || pit == mancalaP2);
    }

    /**
     * oppositePit gets the pit opposite of the pit
     * @param pit the current pit
     * @return int the opposite pit
     */
    private int oppositePit(int pit) {
        if(pit <=12) {
            return 12 - pit;
        } else{
            return pit - 12;
        }
    }

    //moveab
    public boolean canPlay(int pit) {

        //make sure play is not FINISHED
        if (gameState != STATE.INCOMPLETE) {
            return false;
        }

        //make sure pit's not empty
        if (pits[pit] == 0){
            return false;
        }
        // if its not current player's pit
        if (playerOfPit(pit) != currentPlayer){
            return false;
        }
        // Moves can be made only on valid pits
        if (pit < 0 || pit > totalPits || pit == mancalaP1 || pit == mancalaP2)
            return false;

        return true;
    }

    /**
     *
     * @param c
     */
    public void attach(ChangeListener c) {

        listeners.add(c);
    }

    /**
     *
     */
    private void notifyListeners() {

        for (ChangeListener c : listeners){

            c.stateChanged(new ChangeEvent(this));
        }
    }

    /**
     * setStones sets the pits to the number of
     * @param stones are the number of stones
     */
    public void setStones(int stones) {

        for(int i = 0; i < pits.length; i++) {
            if (!isMancala(i))
                pits[i] = stones;
        }

        this.notifyListeners();
    }

    /**
     *
     * @param state
     */
    public void setState(String state) {

        if(state.equals("STARTED")) {
            gameState = STATE.STARTED;

        } else if(state.equals("INCOMPLETE")) {
            gameState = STATE.INCOMPLETE;

        } else {
            gameState = STATE.COMPLETE;
        }
    }

    /**
     * undoMove undo's the last move if possible.
     */
    public void undoMove() {
        boolean flag = false;

        if (!undoable){
            return;
        }

        switch (currentPlayer) {

            case ONE:
            {
                if (lastStoneInMancala && undosP1 < totalUndos )
                {
                    undosP1++;
                    flag = true;
                }
                else if (!lastStoneInMancala && undosP2 < totalUndos)
                {
                    undosP2++;
                    currentPlayer = Player.TWO;
                    flag = true;
                }
                break;
            }
            case TWO:
            {
                if (lastStoneInMancala && undosP1 < totalUndos)
                {
                    undosP2++;
                    flag = true;
                }
                else if (!lastStoneInMancala && undosP1 < totalUndos)
                {
                    undosP1++;
                    flag = true;
                    currentPlayer = Player.ONE;
                }
                break;
            }
        }

        if ((undosP1 < totalUndos && gameState.equals(STATE.COMPLETE))  ||  (undosP2 < totalUndos && gameState.equals(STATE.COMPLETE))) {
            gameState = STATE.INCOMPLETE;
        }

        //able to undo
        if (flag) {
            pits = pastPits.clone();
            undoable = false;
            notifyListeners();
        }
    }

    /**
     *
     * @param player the player fo the two
     * @return
     */
    public int playerScoreCard(Player player) {

        if (player == Player.ONE) {
            return pits[mancalaP1];
        }
        if (player == Player.TWO){
            return pits[mancalaP2];
        }
        return 0;
    }



    public void save() {
        pastPits = pits.clone();
    }


}
