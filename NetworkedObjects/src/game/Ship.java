package game;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Vector;

import javax.swing.JComponent;
/**
 * 
 * our ship object is using the keylistener adapter in order for us to control the ship object with keyboard events
 * @author Oren Lahav
 *
 */

public class Ship extends ControlledObject {
	
	private int moveUpAndDown;
	private int moveLeftAndRight;
	private ShootListener shootListener;
	
	
	// creating the shapes for the ship and the laser it shoots
	
	private static Point[] shipShape = { new Point(245, 100), new Point(190, 90), new Point(150, 100), new Point(190, 110) };
	
	public Ship(int x, int y, int width, int height, JComponent keyDispatcher, ShootListener s) 
	{
		
		super(new LoopInScreenDecorator(new SimpleSpaceObject(shipShape, new Point(x, y), 0), new Point(x, y), width, height), keyDispatcher);
		this.shootListener = s;
	}
	
	public void moveUp()
	{
		moveUpAndDown -= 5;	
	}
	
	public void moveDown()
	{
		moveUpAndDown += 5;
	}
	
	public void stop()
	{
		moveUpAndDown = 0;
	}
	public int getOffsetX()
	{
		return (int)((LoopInScreenDecorator)decoratedObject).getOffset().x;
	}
	public int getOffsetY()
	{
		return (int)((LoopInScreenDecorator)decoratedObject).getOffset().y;
	}


	/**
	 * here we will use our vector in order to create as many laser objects as we want on screen
	 */
	public void paint(Graphics g) {
		decoratedObject.paint(g); 
		decoratedObject.move(moveLeftAndRight, moveUpAndDown);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {

	}
	//when the letter 'K' is pressed our ship will go down, when letter 'O' is pressed the ship will go up
	//when space bar is pressed, our ship will fire a laser beam
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_O) 
			moveUp();
		else if (e.getKeyCode() == KeyEvent.VK_K)
			moveDown();
   		else if(e.getKeyCode() == KeyEvent.VK_SPACE)
			shootListener.shotFired(getOffsetX(), getOffsetY());
   		else if (e.getKeyCode() == KeyEvent.VK_K && e.getKeyCode()== KeyEvent.VK_O)
			stop();
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_O || e.getKeyCode() == KeyEvent.VK_K)
			stop();			
	}
}
