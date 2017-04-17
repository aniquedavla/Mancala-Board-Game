/**
   COPYRIGHT (C) 2014 StackSmashers. All Rights Reserved.
   Class for Circle style in Mancala game
   Solves CS151 Project view component of MVC
   @author Saurav Agrawal, John Lee, Nick Redman
   @version 1.00 2014/5/5
*/

import java.awt.Shape;
import java.awt.geom.Ellipse2D;


/** 
 * This class is the concrete implementation of BoardStyle interface
 * and contains the methods pertaining to display for the game and 
 * the data for a given configuration and moment of gameplay.
 * 
 */

public class CircleStyle implements BoardStyle{

	/**
	 *Creates an elliptical pit
	 *@return an ellipse with size 120 by 120
	 */
	public Shape getPit() {
		return new Ellipse2D.Double(0, 0, 120, 120);
	}

	/**
	 *Creates an elliptical mancala
	 *@param p - Player this mancala belongs to
	 *@return an ellipse with size 50 by 500
	 */
	public Shape getMancala(Player p) {
		return new Ellipse2D.Double(0, 0, 50, 500);

	}


}
