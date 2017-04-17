/**
   COPYRIGHT (C) 2014 StackSmashers. All Rights Reserved.
   Class for mancala pit in Mancala game
   Solves CS151 Project Model component of MVC
   @author Saurav Agrawal, John Lee, Nick Redman
   @version 1.00 2014/5/5
*/

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;


/** 
 * This class contains the methods pertaining to functionality for the game and 
 * the data for a given configuration and moment of gameplay.
 * 
 */

public class Mancala extends Pit {
    
	/**
     * Mancala class constructor - creates a mancala pit with the given parameters
     * @param n - the number of marbles to initialize the pit with
     * @param index - the location of the mancala
     * @param player - the Player this mancala belongs to
     * @param b - a concrete implementation of BoardStyle determining
     * the shape of the pits to be used in the game
     */
    public Mancala(int n, int index, Player player, BoardStyle b) {
        super(n, index, player, b);
    }
    
    /**
	 *Get the player the pit belongs to
	 *@return the pit's player
	 */
    public Player getPlayer() {
        return super.getPlayer();
    }
    
    /**
	 *Change the number of marbles in the current pit
	 *@param n number of marbles to be set to
	 */
    public void setMarbles(int n) {
        super.setMarbles(n);
    }
    
    /**
	 *Get the number of marbles in the current pit
	 *@return the pit's marbles
	 */
    public int getMarbles() {
        return super.getMarbles();
    }
    
    /**
	 *Get the index or location of each pit
	 *@return the location of each pit
	 */
    public int getIndex() {
        return super.getIndex();
    }
    
    /**
	 *Check if the pit is empty or not
	 *@return true if the pit is empty and false otherwise
	 */
    public boolean isEmpty() {
        return super.isEmpty();
    }

    /**
	 *Get the shape of the pit to be drawn
	 *@param b - the board style determining the shape
	 *@return the Shape based on the board style
	 */
    public Shape drawPit(BoardStyle b) {
        return b.getMancala(getPlayer());
    }
    
    /**
	 *Get the player the pit belongs to
	 *@param g graphics object used to draw shape
	 */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.draw(this.drawPit(getStyle()));
    }
}