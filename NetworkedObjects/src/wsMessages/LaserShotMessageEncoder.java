package wsMessages;

import javax.json.*;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class LaserShotMessageEncoder implements Encoder.Text<LaserShotMessage> {

	@Override
	public String encode(LaserShotMessage msg) throws EncodeException {
		JsonObject jsonProdMessage = Json.createObjectBuilder()
				.add("type", msg.getClass().getName())
				.add("x", msg.getX())
                .add("y", msg.getY())
                .build();

        return jsonProdMessage.toString();
    }	

	@Override
	public void destroy() {
	}

	@Override
	public void init(EndpointConfig arg0) {
	}
}
