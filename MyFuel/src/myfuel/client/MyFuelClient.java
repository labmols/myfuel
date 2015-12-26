package myfuel.client;

import java.io.IOException;

import javax.swing.JOptionPane;

import myfuel.GUIActions.LoginActions;
import myfuel.gui.ConnectDialog;
import myfuel.ocsf.client.ObservableClient;

public class MyFuelClient extends ObservableClient {
	
	/**
	 * 
	 * @param host
	 * @param port
	 * @throws IOException
	 */
	public MyFuelClient(String host, int port) throws IOException {
		super(host, port);
		openConnection();
	}

	@Override
	protected void handleMessageFromServer(Object msg) {
		setChanged();
		notifyObservers(msg);
	}
	
	/**
	 * 
	 * @param msg
	 */
	public void handleMessageFromGUI(Object msg) {
		try {
			sendToServer(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		MyFuelClient client;
		
		//ConnectDialog dialog = new ConnectDialog();
	//	dialog.setVisible(true);
		
		try {
			client = new MyFuelClient("localhost",5555);
			new LoginActions(client);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Can't Connect to server in port 5555");
		}
		
		
	}
	


}
