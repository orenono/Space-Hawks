package wsMessages;

import javax.json.*;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * 
 * @author orenono
 *
 */

public class ShipMovedMessageEncoder implements Encoder.Text<ShipMovedMessage> {

	@Override
	public String encode(ShipMovedMessage msg) throws EncodeException {
		JsonObject jsonShipMoveMessage = Json.createObjectBuilder()
				.add("type", msg.getClass().getName())
				.add("x", msg.getX())
                .add("y", msg.getY())
                .add("shipNumber", msg.getShipNumber())
                .build();

        return jsonShipMoveMessage.toString();
    }	

	@Override
	public void destroy() {
	}

	@Override
	public void init(EndpointConfig arg0) {
	}
}