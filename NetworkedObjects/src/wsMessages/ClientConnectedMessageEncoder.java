package wsMessages;

import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class ClientConnectedMessageEncoder implements Encoder.Text<ClientConnectedMessage> {

	@Override
	public String encode(ClientConnectedMessage msg) throws EncodeException {
		JsonObject jsonProdMessage = Json.createObjectBuilder()
				.add("type", msg.getClass().getName())
				.add("clientsCount", msg.getClientsCount())
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
