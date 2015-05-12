package game;
import java.awt.Color;
import java.util.Vector;


public interface GameEventsListener {

	public void shotFired(int x, int y, Color color);
	
	public void shipMoved(int x, int y, int shipNumber);
}
