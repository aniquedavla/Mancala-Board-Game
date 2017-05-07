import javax.swing.event.ChangeListener;
import java.util.ArrayList;

/**
 * Created by aniquedavla on 5/3/17.
 * GameModel is the MVC Model for the Mancala board, underlying data structure and alogrithms.
 */
public class GameModel {

    public static enum STATE {
        STARTING, NOTFINISHED, COMPLETED}
    ;
    public static enum Player {
        ONE, TWO
    };

    private Player currentPlayer;
    private STATE gameState;

    private final int totalUndos = 3;
    public static final int totalPits = 14;
    public static final int player1Mancala = 6;
    public static final int player2Mancala = 13;
    private int[] pits;
    private int[] previousPits;
    private int player1Undos;
    private int player2Undos;
    private boolean lastStoneInMancala;
    private boolean undoable;
    private ArrayList<ChangeListener> listeners;

    /**
     *
     */
    public GameModel(){

    }
}
