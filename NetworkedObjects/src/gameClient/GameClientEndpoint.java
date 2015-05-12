package gameClient;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.DeploymentException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

import org.glassfish.tyrus.client.ClientManager;

import wsMessages.ClientConnectedMessage;
import wsMessages.ClientConnectedMessageEncoder;
import wsMessages.LaserShotMessage;
import wsMessages.LaserShotMessageEncoder;
import wsMessages.Message;
import wsMessages.MessageDecoder;
import wsMessages.ShipMovedMessage;
import wsMessages.ShipMovedMessageEncoder;

/**
 * 
 * @author sdexter72
 * @author orenono
 */

@ClientEndpoint(
		decoders = { 
			MessageDecoder.class 
		},
		encoders = {
			LaserShotMessageEncoder.class,
			ClientConnectedMessageEncoder.class,
			ShipMovedMessageEncoder.class
		})
public class GameClientEndpoint {
	private static CountDownLatch latch;
	private Logger logger = Logger.getLogger(this.getClass().getName());
	private boolean gameStarted = false;
	private GamePanel gamePanel;
	private static Session peer;

	@OnOpen
	public void onOpen(Session session) {
		logger.info("Connected ... " + session.getId());
		try {
			session.getBasicRemote().sendText("start");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@OnMessage
	public void onMessage(Session session, Message message) {
		logger.info("Received ...." + message.toString());

		if (message instanceof ClientConnectedMessage) {
			if (!gameStarted) {
				gameStarted = true;
				createAndShowGUI(peer, ((ClientConnectedMessage)message).getClientsCount());
			}
			
			if (((ClientConnectedMessage)message).getClientsCount() == 2) {
				gamePanel.startGame();
			}
		} else if (message instanceof LaserShotMessage) {
			gamePanel.displayLaser(((LaserShotMessage)message).getX(), ((LaserShotMessage)message).getY(), ((LaserShotMessage)message).getColor());
		} else if (message instanceof ShipMovedMessage) {
			gamePanel.displayShip(((ShipMovedMessage)message).getX(), ((ShipMovedMessage)message).getY(),((ShipMovedMessage)message).getShipNumber());
		}
	}

	@OnClose
	public void onClose(Session session, CloseReason closeReason) {
		logger.info(String.format("Session %s close because of %s",
				session.getId(), closeReason));
		latch.countDown();
	}

	public static void main(String[] args) {
		latch = new CountDownLatch(1);

		ClientManager client = ClientManager.createClient();
		try {
			peer = client.connectToServer(GameClientEndpoint.class, new URI(
					"ws://localhost:8025/websockets/game"));
			latch.await();

		} catch (DeploymentException | URISyntaxException
				| InterruptedException | IOException e) {
			throw new RuntimeException(e);
		}
	}

	private void createAndShowGUI(Session session, int playerNumber) {
		logger.info("CREATING GAME FOR PLAYER #" + playerNumber);
		gamePanel = new GamePanel(session);
		gamePanel.requestFocus();
		gamePanel.setPlayer(playerNumber);
	}
}
