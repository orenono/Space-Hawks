package wsMessages;

public class LaserShotMessage extends Message {

	private int x;
	private int y;

	public LaserShotMessage(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() { return x; }
	public int getY() { return y; }
}
