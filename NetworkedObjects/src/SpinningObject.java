import java.awt.Graphics;
/**
 * 
 * this decorator will add the spinning functionality to an object
 * @author Oren Lahav
 *
 */
public class SpinningObject extends SpaceObjectDecorator{

	private double rotation = 1.0;
	
	public SpinningObject(SpaceObject obj) {
		super(obj);
	}
	
	public SpinningObject(SpaceObject obj, double rotation_)
	{
		super(obj);
		rotation = rotation_;
	}
	public void paint(Graphics g) {
		decoratedObject.paint(g);
		decoratedObject.rotate(rotation);
		
	}
	
	

}
