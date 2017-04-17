import java.awt.*;
import java.awt.geom.*;
public class CircleFormat
{
	public Shape pitShape()
	{
		return new Ellipse2D.Double(0, 0, 150, 150);
	}
	
	public Shape mancalaShape(Player player)
	{
		return new Ellipse2D.Double(0, 0, 70, 650);
	}
}