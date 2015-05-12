package game;
/**
 * This is a decorator that will be used for any object that could change his shape, if a certain action occurred
 * @author orenono
 *
 */
public class ShapeShiftingSpaceObject extends SpaceObjectDecorator {

	protected SpaceObject[] decoratedObjects;
	
	public ShapeShiftingSpaceObject(SpaceObject[] objects) {
		super(objects[0]);
		decoratedObjects = objects;
	}
	
	public void switchShape(int index)
	{
		// If index is out of bounds, log to console and do nothing.
		if (index >= this.decoratedObjects.length || index < 0) {
			System.out.println("ShapeShiftingSpaceObject::switchShape - Index is out of bounds");
			return;
		}
		
		this.decoratedObject = this.decoratedObjects[index];
	}

}
