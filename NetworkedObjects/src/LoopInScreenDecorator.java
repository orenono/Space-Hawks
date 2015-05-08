/**
 * 
 * this decorator will give a spaceObject the function to return into the screen once it left the screen-- returning from the other side
 * creating the illusion that the ship is flying through the stars. Sort of like a moving image affect.
 * @author Oren Lahav
 *
 */
public class LoopInScreenDecorator extends SpaceObjectDecorator {
	private int screenWidth;
	private int screenHeight;
	private Point offset;
	
	public LoopInScreenDecorator(SpaceObject decoratedObject, Point inOffset, int width, int height) {
		super(decoratedObject);
		screenWidth = width;
		screenHeight = height;
		offset = inOffset;
	}
	
	/**
	 * we override the move function so when an object reached the end of the screen(height or width of screen) it will then return 
	 * from the other side of the screen
	 */
	public void move(int x, int y) {
		super.move(x,y);
		offset.x += x;
		offset.y += y;
		
		if (offset.x < 0) {
			move(screenWidth - (int)offset.x, 0);
		} else if (offset.x > screenWidth) {
			move(0 - (int)offset.x, 0);
		}
		
		if (offset.y < 0) {
			move(0, screenHeight - (int)offset.y);
		} else if (offset.y > screenHeight) {
			move(0, 0 - (int)offset.y);
		}
	}

}
