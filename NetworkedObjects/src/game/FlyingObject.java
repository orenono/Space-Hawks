package game;
import java.awt.Graphics;
/**
 * 
 * this SpaceObjectDecorator will give the a spaceObject the ability to move on screen in any direction we wish it to
 * @author Oren Lahav
 *
 */

public class FlyingObject extends SpaceObjectDecorator {

	private int directionX;
	private int directionY;
	
	public FlyingObject(SpaceObject decoratedObject) {
		super(decoratedObject);
	}
		
	
	public FlyingObject(SpaceObject decoratedObject, int direction_x, int direction_y ) {
		super(decoratedObject);
		directionX = direction_x;
		directionY = direction_y;
	
	}
	
	public void paint(Graphics g) {
		decoratedObject.paint(g);
		decoratedObject.move(directionX, directionY);
		
	}
}
