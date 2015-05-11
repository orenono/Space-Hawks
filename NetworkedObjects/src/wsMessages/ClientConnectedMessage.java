package wsMessages;

public class ClientConnectedMessage extends Message {
	private int clientsCount;

	public ClientConnectedMessage(int clientsCount) {
		this.clientsCount = clientsCount;
	}
	
	public int getClientsCount() { return this.clientsCount; }
}

/**
 * add shipMove message
 * add shipMoveMessage Encoder
 * 
 * add implemantaion to message decoder (if else)
 * 
 * in server endpoint and client endpoint - add encoder in the annotaions.
 * 
 * somehow gamePanel need to know that ship has moved and send a message back to server that the ship moved.
 * 
 * client endpoint need to listen to the message and notify gamepanel which in turn should paint the movement of the ship
 * 
 * 
 * 
 * 
 */
