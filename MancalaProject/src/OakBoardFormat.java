import java.awt.*;
import java.awt.geom.*;
import java.net.URL;

public class OakBoardFormat implements BoardFormat {

	@Override
	public Shape formatPitShape(PitShape ps) {
		return new Rectangle2D.Double(ps.getX(), ps.getY(), ps.getWidth(), ps.getHeight());
	}

	@Override
	public Image backgroundImg() {
		URL imgURL = this.getClass().getResource("/board1.jpg");
		Image img = Toolkit.getDefaultToolkit().getImage(imgURL);
        return img;
	}
}
