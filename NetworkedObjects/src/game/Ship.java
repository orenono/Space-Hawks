package game;
import java.awt.Color;
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
	private int shipNumber;
	private GameEventsListener gameListener;
	private Color color;
	
	
	// creating the shapes for the ship and the laser it shoots
	
	private static Point[] shipShape = { new Point(245, 100), new Point(190, 90), new Point(150, 100), new Point(190, 110) };
	
	public Ship(int x, int y, int width, int height, Color color, JComponent keyDispatcher, GameEventsListener gameListener, int shipNumber) 
	{
		
		super(new LoopInScreenDecorator(new ColorSpaceObject(new SimpleSpaceObject(shipShape, new Point(x, y), 0), color), new Point(x, y), width, height), keyDispatcher);
		this.color = color;
		this.gameListener = gameListener;
		this.shipNumber = shipNumber;
	}
	
	public void moveUp()
	{
		moveUpAndDown -= 10;	
		
		
	}
	
	public void moveDown()
	{
		moveUpAndDown += 10;
		
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
		
		 if(e.getKeyCode() == KeyEvent.VK_SPACE)
   			gameListener.shotFired(getOffsetX(), getOffsetY(), color);
		
	}
	//I wanted to set the ships to move one step at a time (instead of continuously while keys are pressed)
	// for some reason using 'keyTyped' didn't work well. But this method sort of works...
	@Override
	public void keyReleased(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_O) {
			moveUp();
			gameListener.shipMoved(0, moveUpAndDown, this.shipNumber);
			if(e.getKeyCode() == KeyEvent.VK_O || e.getKeyCode() == KeyEvent.VK_K)
				stop();
			gameListener.shipMoved(0, moveUpAndDown, this.shipNumber);
			}
		
		else if (e.getKeyCode() == KeyEvent.VK_K) {
			moveDown();
			gameListener.shipMoved(0, moveUpAndDown, this.shipNumber);
			if(e.getKeyCode() == KeyEvent.VK_O || e.getKeyCode() == KeyEvent.VK_K)
			stop();
			gameListener.shipMoved(0, moveUpAndDown, this.shipNumber);
			}
	}
}
