package game;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

/**
 * 
 * This describes what a SpaceObject decorator needs to do. The 'default'
 * behavior is to have the underlying SpaceObject (here named decoratedObject)
 * implement all the behavior (that is, to delegate all behavior to
 * decoratedObject, but specific decorators will override this.
 * 
 * @author sdexter72
 * @author orenono
 */

public abstract class SpaceObjectDecorator implements SpaceObject {

	/**
	 * the SpaceObject subject to decoration
	 */

	protected SpaceObject decoratedObject;

	public SpaceObjectDecorator(SpaceObject decoratedObject) {
		this.decoratedObject = decoratedObject;
	}

	public void paint(Graphics g) {
		decoratedObject.paint(g);
	}

	public boolean collide(SpaceObject obj) {
		return decoratedObject.collide(obj);
	}

	public void move(int x, int y) {
		decoratedObject.move(x, y);
	}

	public void rotate(double d) {
		decoratedObject.rotate(d);
	}
	
	public Point[] getCurrentPosition(){
		
		return decoratedObject.getCurrentPosition();
	}
	

}
