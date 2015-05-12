package wsMessages;

import java.awt.Color;
/**
 * @author orenono
 */

public class LaserShotMessage extends Message {

	private int x;
	private int y;
	private Color color;

	public LaserShotMessage(int x, int y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
	}
	
	public int getX() { return x; }
	public int getY() { return y; }
	public Color getColor() { return color; }
}
