import java.awt.*;

public interface BoardFormat {

	/**
	 * Gets the shape of the formatted Pit
	 * @param ps the Pit to be formatted
	 * @return the shape of the formatted Pit
	 */
	Shape formatPitShape(PitShape ps);
	
	/**
	 * Gets the image of the background of the board
	 * @return the image of the background of the board
	 */
	Image backgroundImg();
}
