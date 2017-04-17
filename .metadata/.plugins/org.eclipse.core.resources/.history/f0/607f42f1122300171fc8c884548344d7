
/**
   COPYRIGHT (C) 2014 StackSmashers. All Rights Reserved.
   Class for Board Model in Mancala game
   Solves CS151 Project Model component of MVC
   @author Saurav Agrawal, John Lee, Nick Redman
   @version 1.00 2014/5/5
*/
import java.util.ArrayList;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/** 
 * This class contains the methods pertaining to functionality for the game and 
 * the data for a given configuration and moment of gameplay.
 * 
 */
public class Board {

	private Player p;
	private boolean extraTurn;
	private ArrayList<ChangeListener> listeners;
	private int countUndo;
	private int[] previousState;
	private ArrayList<Pit> pits;  
	private boolean redo;

        /**
         * Board class constructor - creates a starting board initializing 
         * empty "pits" or containers with no marbles.
         * @param aBoardStyle - a concrete implementation of BoardStyle determining
         * the shape of the pits to be used in the game
         */
	public Board(BoardStyle aBoardStyle) {
		redo = false;
		countUndo=0;
		previousState = new int[14];
		p = Player.ONE;
		extraTurn = false;
		pits = new ArrayList<Pit>();
		for (int i = 0; i < 6; i++) {
			pits.add(new Pit(0, i, Player.ONE, aBoardStyle));
		}
		pits.add(new Mancala(0, 6, Player.ONE, aBoardStyle));
		for (int i = 7; i < 13; i++) {
			pits.add(new Pit(0, i, Player.TWO, aBoardStyle));
		}
		pits.add(new Mancala(0, 13, Player.TWO, aBoardStyle));

		listeners = new ArrayList<ChangeListener>();
	}
        
        /**
         * Populates board with given marbles for new game
         * @param marbles - the number of marbles per pit.
         */
	
	public void fillBoard(int marbles) {
		int counter = 0;
		for (Pit p : pits) {
			if (!(p instanceof Mancala)) {
				p.setMarbles(marbles);
				previousState[counter] = marbles;
			}  
			else {
				previousState[counter] = 0;
				counter++;
			}
		}
		ChangeEvent event = new ChangeEvent(this);
		for (ChangeListener listener : listeners) {
			listener.stateChanged(event);
		}
	}
        
        /**
         * Attaches listeners to the collection of listeners for this model
         * @param listener - ChangeListeners to be notified of changes to the model
         */
	public void addChangeListener(ChangeListener listener) {
		listeners.add(listener);
	}
        
        /**
         * Returns the current player.
         * @return - a player, Player.ONE or Player.TWO
         */
	public Player getPlayer() {
		return p;
	}
        
        /**
         * Evaluates and returns the game status
         * @return - a boolean to signal the end of the game.
         */
	public boolean getGameStatus() {

		return gameOver();
	}
        
        /**
         * Determines the position of the pit of the last marble dropped on the 
         * board for the selected pit.
         * @param pit - a pit object containing the count of marbles to distribute
         * @return the pit index in the board's array of pits that the last marble
         * lands in
         */
	public int getLastMarble(Pit pit) {
		int numberOfMarbles = pit.getMarbles();
		int currentIndex = pit.getIndex();
		while (numberOfMarbles > 0) {
			if (currentIndex == 5 && p == Player.TWO) {
				currentIndex += 2;
			} else if (currentIndex == 12 && p == Player.ONE) {
				currentIndex += 2;
			} else {
				currentIndex++;
			}
			if (currentIndex == 14) {
				currentIndex = 0;
			}
			numberOfMarbles--;
		}
		return currentIndex;
	}

        /**
         * Distributes marbles of the pit chosen by the player around the pits 
         * on the board beginning with the pit after the chosen pit.
         * @param startIndex - the starting index to begin distributing marbles
         * @param marbles - the number of marbles to distribute
         */
	public void distributeMarbles(int startIndex, int marbles) {
		int numberOfMarbles = marbles;
		int currentIndex = startIndex;

		while (numberOfMarbles > 0) {
			if (currentIndex == 5 && p == Player.TWO) {
				currentIndex += 2;
			} else if (currentIndex == 12 && p == Player.ONE) {
				currentIndex = 0;
			} else {
				currentIndex++;
			}
			if (currentIndex == 14) {
				currentIndex = 0;
			}
			pits.get(currentIndex).setMarbles(pits.get(currentIndex).getMarbles() + 1);
			numberOfMarbles--;
		}
	}

        /**
         * Checks to see if conditions have been met where the player wins 
         * marbles from the other player depending on the location 
         * where the last pit dropped on the board. The last pit must be empty 
         * and on the current player's side.
         * @param lastMarbleDropped - the index of the pit where the last marble
         * dropped.
         */
	private void wonOpponentMarbles(int lastMarbleDropped) {
		int mancala=6;
		if(lastMarbleDropped==6 || lastMarbleDropped==13)return;
		else if (pits.get(lastMarbleDropped).getMarbles() == 1 && pits.get(lastMarbleDropped).getPlayer() == p) {
			if(pits.get(12-lastMarbleDropped).getMarbles()==0)return;
			if(p==Player.ONE)mancala=6;
			else mancala=13;

			pits.get(mancala).setMarbles(pits.get(mancala).getMarbles() + pits.get(12 - lastMarbleDropped).getMarbles());
			pits.get(mancala).setMarbles(pits.get(mancala).getMarbles() + pits.get(lastMarbleDropped).getMarbles());
			pits.get(12 - lastMarbleDropped).setMarbles(0);
			pits.get(lastMarbleDropped).setMarbles(0);
		} 
	}

        /**
         * Begins the sequence of actions after a player chooses a particular
         * pit on the board.
         * @param pit - the chosen pit by the player.
         */
	public void choosePit(Pit pit) {
		if(!redo)
			countUndo=0;
		if(pit.getPlayer()!=p)
			return;
		if(pit.getMarbles()==0)
			return;
		for (Pit pitt : pits) {
			previousState[pitt.getIndex()] = pitt.getMarbles();
		}

		extraTurn = getExtraTurn(pit);
		int lastMarbleDropped = getLastMarble(pit);
		int numberOfMarbles = pit.getMarbles();
		pit.setMarbles(0);

		distributeMarbles(pit.getIndex(), numberOfMarbles);
		wonOpponentMarbles(lastMarbleDropped);
		if (gameOver()) 
			clearBoard();
		if (!extraTurn) {
			switchPlayer();
		}
		redo=false;
		ChangeEvent event = new ChangeEvent(this);
		for (ChangeListener listener : listeners) {
			listener.stateChanged(event);
		}
	}
        
        /**
         * Executes a check to see if the current player gets an extra turn
         * depending on the condition where the last marble dropped from the 
         * chosen pit by the player is the player's mancala.
         * @param pit - the pit chosen by the player
         * @return - a boolean determining whether or not the player receives
         * an extra turn
         */
	private boolean getExtraTurn(Pit pit) {
		int totalMoves = pit.getIndex() + pit.getMarbles();
		if ((totalMoves - 6) % 13 == 0 && p == Player.ONE) 
		{
			return true;
		}
		if ((totalMoves - 13) % 13 == 0 && p == Player.TWO) {
			return true;
		}
		return false;
	}
        
        /**
         * Checks to see if conditions have been met to end the game -- the game
         * ends if any player's set of pits are empty.
         * @return a boolean determining if game end conditions are satisfied
         */
	public boolean gameOver() {
		boolean emptyRow = true;
		for (int i = 0; i < 6; i++) {
			if (pits.get(i).getMarbles() != 0) {
				emptyRow = false;
			}
		}
		if (emptyRow) {
			return emptyRow;
		}

		emptyRow = true;

		for (int i = 7; i < 13; i++) {
			if (pits.get(i).getMarbles() != 0) {
				emptyRow = false;
			}
		}

		return emptyRow;
	}

        /**
         * Checks if an undo action is valid and performs an undo function 
         * where the previous state of the gameplay is restored.
         */
	public void undo() {

		if (!canUndo()) {
			System.out.println("You are not allowed to Undo");
			return;
		}

		redo = true;

		countUndo++;
		for (Pit pat : pits) 
			pat.setMarbles(previousState[pat.getIndex()]);

		if(!extraTurn)
			switchPlayer();


		ChangeEvent event = new ChangeEvent(this);
		for (ChangeListener listener : listeners) {
			listener.stateChanged(event);
		}
	}

        /**
         * Gets the ArrayList containing the pits.
         * @return an arraylist of Pits.
         */
	public ArrayList<Pit> getData() {
		return pits;
	}

        /**
         * Gets the mancala of a given player
         * @param player - the player to whom the mancala belongs
         * @return - the mancala object belonging to the player.
         */
	public Mancala getMancala(Player player) {
		for (Pit pat : pits) {
			if (pat.getPlayer() == player && pat instanceof Mancala) {
				return (Mancala) pat;
			}
		}
		return null;
	}

        /**
         * Switches the player status between Player.ONE and Player.TWO
         */
	private void switchPlayer() {
		if (p == Player.ONE) {
			p = Player.TWO;
		} else {
			p = Player.ONE;
		}
		extraTurn = false;
	}

        /**
         * Checks to see if a previous state exists for the current snapshot
         * of the distribution of marbles
         * @return a boolean determining if the previous state exists.
         */
	private boolean noPreviousState(){
		for(Pit pat: pits)
			if(pat.getMarbles()!=previousState[pat.getIndex()])
				return false;
		return true;
	}
        
        /**
         * Checks the allowable conditions for a player to undo an action.
         * @return a boolean, true if the player can undo, false if not.
         */
	private boolean canUndo(){
		if(gameOver())
			return false;

		if(noPreviousState()){
			System.out.println("no previous state");
			return false;

		}

		if(countUndo==3){
			System.out.println("you are not allowed any more attempts");
			return false;
		}

		return true;

	}

        /**
         * Clears the pits on the board to be zero
         */
	private void clearBoard(){
		for (int i = 0; i < 6; i++) {
			pits.get(6).setMarbles(pits.get(6).getMarbles() + pits.get(i).getMarbles());
			pits.get(i).setMarbles(0);
		}
		for (int i = 7; i < 13; i++) {
			pits.get(13).setMarbles(pits.get(13).getMarbles() + pits.get(i).getMarbles());
			pits.get(i).setMarbles(0);
		}
	}
}