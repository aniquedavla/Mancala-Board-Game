import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import javax.swing.JComponent;

public class Pit extends JComponent
{
	private int stones;
	private Player player;
	private BoardFormat bf;
	
	public Pit (int stones, Player player, BoardFormat bf)
	{
		this.stones = stones;
		this.player = player;
		this.bf = bf;
	}
	
	public void setStones(int s)
	{
		stones = s;
	}
	
	public int getStones()
	{
		return stones;
	}
	
	public Player getPlayer()
	{
		return player;
	}
	
	public boolean isEmpty()
	{
		if (stones == 0)
			return true;
		else
			return false;
	}
	
	public 
	
	public Shape drawPits(BoardFormat bf)
	{
		return ps.pitShape();
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g2.draw(this.drawPitShape())
	}
	
}
