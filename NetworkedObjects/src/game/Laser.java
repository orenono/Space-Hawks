package game;

import java.awt.Color;

public class Laser extends SpaceObjectDecorator{

	private static Point[] laserShape = { new Point (100,40), new Point(90,40), new Point (85,40), new Point (85,41)};
	private static Point[] emptyShape = { new Point(-1, -1), new Point(-1, -1) };
	
	public Laser(int x, int y, Color color)
	{
		super(new ShapeShiftingSpaceObject(
				new SpaceObject[] {
						new FlyingObject(new ColorSpaceObject(new SimpleSpaceObject(laserShape, new Point(x, y), 0), color),6 ,0),
						new SimpleSpaceObject(emptyShape, new Point(0, 0), 0)
				}));
	}
	
	public void hitTarget()
	{
		((ShapeShiftingSpaceObject)this.decoratedObject).switchShape(1);
	}
}
