/**
 * 
 * this is the creation of a star object
 * @author orenono
 *
 */

public class Star extends SpaceObjectDecorator{
	
	//here we create the shape of the star
	private static Point[] starShape = {new Point (1,2), new Point(2,1), new Point (3,2), new Point (2,3)};
	
	/**
	 * the star (a simpleSpaceObject) will be wrapped in the LoopInScreen and the FlyingObject decorators.
	 * @param x
	 * @param y
	 * @param inScreenWidth
	 * @param inScreenHeight
	 */
	public Star(int x, int y, int inScreenWidth, int inScreenHeight)
	{
		
		super(new FlyingObject(new LoopInScreenDecorator(new SimpleSpaceObject(starShape, new Point(x, y),0), new Point(x, y), inScreenWidth, inScreenHeight), -2, 0));
	}
}
