import java.awt.Graphics;
/**
 * 
 * this SpaceObjectDecorator will give the a spaceObject the ability to move vertically and exit the screen
 * @author Oren Lahav
 *
 */

public class FallingObject extends SpaceObjectDecorator {

	private int fallRate = 1;
	
	public FallingObject(SpaceObject obj) {
		super(obj);
	}
	
	public FallingObject(SpaceObject obj, int fallRate_) {
		super(obj);
		fallRate = fallRate_;
	}
	
	public void paint(Graphics g) {
		decoratedObject.paint(g);
		decoratedObject.move(0, fallRate);
	}
}
