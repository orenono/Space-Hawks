package gameServer;
import java.io.IOException;
import java.util.logging.*;

import javax.websocket.*;

import wsMessages.*;

import javax.websocket.server.ServerEndpoint;

/**
 * 
 * 
 * @author sdexter72
 * @author orenono
 *
 */
 
@ServerEndpoint(value = "/game",
				decoders = { MessageDecoder.class }, 
				encoders = {
					LaserShotMessageEncoder.class,
					ClientConnectedMessageEncoder.class,
					ShipMovedMessageEncoder.class
				})
	public class GameServerEndpoint{
 
    private Logger logger = Logger.getLogger(this.getClass().getName());
    private static int connetedClients = 0;
 
    @OnOpen
    public void onOpen(Session peer) throws EncodeException {
        logger.info("Connected ... " + peer.getId());
        connetedClients++;
        
        for (Session other : peer.getOpenSessions()) {
            try {
                other.getBasicRemote().sendObject(new ClientConnectedMessage(connetedClients));
            } catch (IOException ex) {
                Logger.getLogger(GameServerEndpoint.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
 
    @OnMessage
    public void onMessage(Session peer, Message msg) throws EncodeException {
        logger.log(Level.FINE, "Message {0} from {1}", new Object[]{msg, peer.getId()});

        for (Session other : peer.getOpenSessions()) {
            try {
                other.getBasicRemote().sendObject(msg);
            } catch (IOException ex) {
                Logger.getLogger(GameServerEndpoint.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
 
    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        logger.info(String.format("Session %s closed because of %s", session.getId(), closeReason));
        connetedClients--;
    }
    
 
}
