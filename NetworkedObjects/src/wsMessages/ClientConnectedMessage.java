package wsMessages;

public class ClientConnectedMessage extends Message {
	private int clientsCount;

	public ClientConnectedMessage(int clientsCount) {
		this.clientsCount = clientsCount;
	}
	
	public int getClientsCount() { return this.clientsCount; }
}

