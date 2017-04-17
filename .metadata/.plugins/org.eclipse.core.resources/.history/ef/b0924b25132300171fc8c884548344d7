/**
   COPYRIGHT (C) 2014 StackSmashers. All Rights Reserved.
   Class for pit in Mancala game
   Solves CS151 Project Model component of MVC
   @author Saurav Agrawal, John Lee, Nick Redman
   @version 1.00 2014/5/5
*/

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import javax.swing.JComponent;

/** 
 * This class contains the methods pertaining to functionality for the game and 
 * the data for a given configuration and moment of gameplay.
 * 
 */

public class Pit extends JComponent{
	
	int marbles;
	int location;
	Player playa;
	BoardStyle style;
	
	/**
     * Pit class constructor - creates a pit with the given parameters
     * @param n - the number of marbles to initialize the pit with
     * @param index - the location of the mancala
     * @param player - the Player this mancala belongs to
     * @param sty - a concrete implementation of BoardStyle determining
     * the shape of the pits to be used in the game
     */        
	public Pit (int n, int index, Player player, BoardStyle sty)
	{
		marbles = n;
		location = index;
		playa = player;
		style = sty;     
	}
	
	/**
	 *Get the player the pit belongs to
	 *@return the pit's player
	 */
	public Player getPlayer()
	{
		return playa;
	}

	/**
	 *Change the number of marbles in the current pit
	 *@param n number of marbles to be set to
	 */
	public void setMarbles(int n)
	{
		marbles = n;
	}
	
	/**
	 *Get the number of marbles in the current pit
	 *@return the pit's marbles
	 */
	public int getMarbles()
	{
		return marbles;
	}
	
	/**
	 *Get the index or location of each pit
	 *@return the location of each pit
	 */
	public int getIndex()
	{
		return location;
	}
	
	/**
	 *Check if the pit is empty or not
	 *@return true if the pit is empty and false otherwise
	 */
	public boolean isEmpty()
	{
		if(marbles == 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 *Get the style of the board, i.e. circles or rectangles
	 *@return the style of the board
	 */
	public BoardStyle getStyle()
	{
            return style;
    }
	
	/**
	 *Get the shape of the pit to be drawn
	 *@param b - the board style determining the shape
	 *@return the Shape based on the board style
	 */
	public Shape drawPit(BoardStyle b)
	{
		return b.getPit();
	}
	
	/**
	 *Get the player the pit belongs to
	 *@param g graphics object used to draw shape
	 */
	public void paintComponent(Graphics g)
	{
        super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.draw(this.drawPit(style));	
		int shapeHeight=this.drawPit(style).getBounds().height;
		int shapeWidth=this.drawPit(style).getBounds().width;
		int col = shapeWidth/2-5;
		int y = 0;
		int row = shapeHeight/2-5;
		int x = 0;
		for(int i = 0; i< getMarbles(); i++)
		{
			
			if( y< shapeHeight)
			{
				g2.drawOval(col,y, 10,10);
				y+= 10;				
			}
			else{
			g2.drawOval(x,row, 10,10);
			x += 10;
			}
		}
	}
}