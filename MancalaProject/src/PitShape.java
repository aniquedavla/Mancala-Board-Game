import java.awt.*;
import java.awt.geom.*;

public class PitShape {
	private int x;
	private int y;
	private int width;
	private int height;
	private int numberOfMarbles;
	private Shape shape;
	final int MARBLE_SIZE = 20;

	public PitShape(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

//	public void fill(Graphics2D g) {
//		g.setColor(Color.BLACK);
//		g.setStroke(new BasicStroke(10));
//		g.draw(shape);
//		g.setColor(Color.ORANGE);
//		g.setStroke(new BasicStroke(3));
//
//		int counter = 1;
//		double radius = Math.min(this.height, this.width) / 4.0;
//
//		if (numberOfMarbles > 0) {
//			g.setColor(Color.ORANGE);
//			Font font = new Font("Futura", Font.BOLD, 20);
//			g.setFont(font);
//			g.drawString(numberOfMarbles + "", x + 30, y + 20);
//		}
//
//		for (int i = 0; i < numberOfMarbles; i++) {
//			double x;
//			double y;
//			if (counter % 2 == 1) {
//				x = this.x + 30;
//				y = (this.y + 5);
//			}
//			else {
//				x = this.x + 
//			}
//		}
//	}
}