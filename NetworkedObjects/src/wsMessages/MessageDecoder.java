package wsMessages;

import java.awt.Color;
import java.io.StringReader;

import javax.json.*;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
/**
 * 
 * @author orenono
 *
 */
public class MessageDecoder implements Decoder.Text<Message> {

	@Override
	public Message decode(String msg) throws DecodeException {
		JsonObject jsonObject = Json.createReader(new StringReader(msg))
				.readObject();

		if (jsonObject.getString("type").equals(LaserShotMessage.class.getName())) {
			LaserShotMessage message = new LaserShotMessage(jsonObject.getInt("x"), jsonObject.getInt("y"), new Color(jsonObject.getInt("color")));
			return message;
		} else if (jsonObject.getString("type").equals(ShipMovedMessage.class.getName())) {
			ShipMovedMessage message = new ShipMovedMessage(jsonObject.getInt("x"), jsonObject.getInt("y"), jsonObject.getInt("shipNumber"));
			return message;
		} else if (jsonObject.getString("type").equals(ClientConnectedMessage.class.getName())) {
			ClientConnectedMessage message = new ClientConnectedMessage(jsonObject.getInt("clientsCount"));
			return message;
		} else {
			throw new DecodeException(msg, "Unknown message");
		}
	}

	/**
	 * Check to see if incoming message is valid JSON
	 */

	@Override
	public boolean willDecode(String msg) {
		try {
			Json.createReader(new StringReader(msg)).readObject();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public void destroy() {
	}

	@Override
	public void init(EndpointConfig arg0) {
	}

}
