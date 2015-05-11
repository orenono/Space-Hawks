package game;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComponent;
/**
 * 
 * an adapter that will extend to an object who needs to listen for key events
 * 
 * @author Oren Lahav
 */
public class ControlledObject extends SpaceObjectDecorator implements KeyListener {

	public ControlledObject(SpaceObject obj, JComponent jc) {
		super(obj);
		if (jc != null) {
			jc.addKeyListener(this);
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
	}

}
