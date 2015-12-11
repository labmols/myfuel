package myfuel.client;

import java.io.IOException;
import myfuel.ocsf.client.ObservableClient;

public class MyFuelClient extends ObservableClient {
	public MyFuelClient(String host, int port) throws IOException {
		super(host, port);
		openConnection();
	}

	@Override
	protected void handleMessageFromServer(Object msg) {
		setChanged();
		notifyObservers(msg);
	}
	
	protected void handleMessageFromGUI(Object msg) {
		try {
			sendToServer(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		MyFuelClient client = new MyFuelClient("localhost",5555);
		new LoginActions(client);
		
	}
	


}
