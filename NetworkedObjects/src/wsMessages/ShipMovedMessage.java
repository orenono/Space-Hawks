package wsMessages;
import java.awt.Color;
/**
 * 
 * @author orenono
 *
 */

public class ShipMovedMessage extends Message {

	private int x;
	private int y;
	private int shipNumber;

	public ShipMovedMessage(int x, int y, int shipNumber) {
		this.x = x;
		this.y = y;
		this.shipNumber = shipNumber;
	}
	
	public int getX() { return x; }
	public int getY() { return y; }
	public int getShipNumber() { return shipNumber; }
}
