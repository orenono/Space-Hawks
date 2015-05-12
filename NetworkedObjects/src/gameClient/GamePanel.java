
package gameClient;
/**
 * @author orenono
 */
import java.awt.Color;
import java.io.IOException;

import game.Ship;
import game.SpaceMassacreGame;

import javax.websocket.EncodeException;
import javax.websocket.Session;

import wsMessages.LaserShotMessage;
import wsMessages.ShipMovedMessage;


public class GamePanel extends SpaceMassacreGame {
	Session session;
	
	public GamePanel(Session session) {
		super("Network Space Massacre", 600, 400);
		this.session = session;
	}
	
	protected void sendMessage(Object msg) {
		try {
			this.session.getBasicRemote().sendObject(msg);
		} catch (IOException | EncodeException e) {
			System.err.println("Problem with sending a message.");
		}
	}
	
	@Override
	public void shotFired(int x, int y, Color color) {
		sendMessage(new LaserShotMessage(x, y, color));
	}
	
	public void displayLaser(int x, int y, Color color) {
		super.shotFired(x, y, color);
	}
	
	public void shipMoved( int x, int y, int shipNumber){
		sendMessage(new ShipMovedMessage (x, y, shipNumber));
	}
	
	public void displayShip(int x, int y, int shipNumber){
		super.shipMoved(x, y, shipNumber);
	}
	
}