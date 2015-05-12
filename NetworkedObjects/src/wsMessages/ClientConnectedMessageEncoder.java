package wsMessages;

import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
/**
 * 
 * @author orenono
 *
 */
public class ClientConnectedMessageEncoder implements Encoder.Text<ClientConnectedMessage> {

	@Override
	public String encode(ClientConnectedMessage msg) throws EncodeException {
		JsonObject jsonClientConnectedMessage = Json.createObjectBuilder()
				.add("type", msg.getClass().getName())
				.add("clientsCount", msg.getClientsCount())
                .build();

        return jsonClientConnectedMessage.toString();
    }	

	@Override
	public void destroy() {
	}

	@Override
	public void init(EndpointConfig arg0) {
	}

}
