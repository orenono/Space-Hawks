package game;

import java.awt.Color;
/**
 * 
 * This class creates a laser beam (this is the only weapon that can destroy the evil aliens)
 * @author Oren Lahav
 *
 */

public class Laser extends SpaceObjectDecorator{

	private static Point[] laserShape = { new Point (100,40), new Point(90,40), new Point (85,40), new Point (85,41)};
	private static Point[] emptyShape = { new Point(-1, -1), new Point(-1, -1) };
	
	/**
	 * this constructor will create a SimpleSpace Object --laser beam, that will be wrapped in the FlyingObject and the ShapeShifting
	 *  Decorators
	 * @param x
	 * @param y
	 * @param color
	 */
	public Laser(int x, int y, Color color)
	{
		super(new ShapeShiftingSpaceObject(
				new SpaceObject[] {
						new FlyingObject(new ColorSpaceObject(new SimpleSpaceObject(laserShape, new Point(x, y), 0), color),6 ,0),
						new SimpleSpaceObject(emptyShape, new Point(0, 0), 0)
				}));
	}
	/**
	 * The hitTarget method will be called whenever a laser hits a target, then the laser will switch shape.
	 */
	public void hitTarget()
	{
		((ShapeShiftingSpaceObject)this.decoratedObject).switchShape(1);
	}
}
