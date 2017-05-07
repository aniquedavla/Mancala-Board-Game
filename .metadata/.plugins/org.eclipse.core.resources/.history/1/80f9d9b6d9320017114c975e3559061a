import java.awt.*;
import java.awt.geom.*;
import java.net.URL;

public class HighTechFormat implements BoardFormat {

	/**
	 * Returns the shape of the pit relative to this format
	 * @return the shape of the pit relative to this format
	 */
	public Shape formatPitShape(PitShape ps) {
		return new RoundRectangle2D.Double(ps.getX(), ps.getY(), ps.getWidth(), ps.getHeight(), 15, 15);
	}

	/**
	 * Returns the background image for this format
	 * @return the image for this format
	 */
	public Image backgroundImg() {
		URL imgURL = this.getClass().getResource("/board2.jpg");
		Image img = Toolkit.getDefaultToolkit().getImage(imgURL);
        return img;
	}

}
