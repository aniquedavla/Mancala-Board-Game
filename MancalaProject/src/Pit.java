import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import javax.swing.JComponent;

public class Pit extends JComponent
{
	int stones;
	int location;
	Player player;
	
	public Pit (int stones, int location, Player player)
	{
		this.stones = stones;
		this.location = location;
		this.player = player;
	}
	
	
}
