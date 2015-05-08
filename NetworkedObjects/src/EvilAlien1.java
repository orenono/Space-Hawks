import java.util.Vector;

/**
 * 
 * This class creates an evil alien's spaceship-- our nemesis.
 * @author Oren Lahav
 *
 */
public class EvilAlien1 extends SpaceObjectDecorator {
	
	// this is the shape of the alien's spaceship
	private static Point[] alienShape1 = { new Point (27,44), new Point(55,23), new Point (30,54), new Point (55,40)};
	private static Point[] emptyShape = { new Point (-1, -1), new Point(-1, -1)};
	
	/**
	 * The constructor will create a simpleSpaceObject (an alien's spaceship) that will be wrapped in the spinning, the loopInScreen, and the 
	 * FlyingObject decorators. 
	 * 
	 * @param xStartingPoint
	 * @param yStartingPoint
	 * @param s
	 * @param rotation
	 * @param directionHorizon
	 * @param directionVertical
	 * @param screenWidth
	 * @param screenHeight
	 */
	
	//??
	public EvilAlien1(int xStartingPoint, int yStartingPoint, int s, int rotation, int directionHorizon, int directionVertical, int screenWidth, int screenHeight)
	{
		
		super(new ShapeShiftingSpaceObject(
				new SpaceObject[] {
						new FlyingObject(new LoopInScreenDecorator(new SpinningObject(new SimpleSpaceObject(alienShape1, new Point(xStartingPoint, yStartingPoint), s), rotation), new Point(xStartingPoint, yStartingPoint), screenWidth, screenHeight),directionHorizon, directionVertical),
						new SimpleSpaceObject(emptyShape, new Point(-1,	-1), 0)
				}
		));
	}
	
	public Point[] getPos()
	{
		return this.getCurrentPosition();
	}
	
	public void takeHit()
	{
		((ShapeShiftingSpaceObject)this.decoratedObject).switchShape(1);
	}
}
