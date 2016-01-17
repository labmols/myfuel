package myfuel.client;

import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.alee.laf.WebLookAndFeel;


import myfuel.gui.ConnectDialog;
import myfuel.ocsf.client.ObservableClient;

/**
 * Client side implement Using the OCSF Framework.
 *  Using Observe/Observable pattern for manage all messages received from server and notify
 *  all the controllers needed these messages.
 *
 */
public class MyFuelClient extends ObservableClient {
	
	/**
	 * Create new Client connection.
	 * @param host - The host address.
	 * @param port - port value.
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
	 * This method received the Request object from the GUI Controller and sent it to the server.
	 * @param msg - The Request details object.
	 */
	public void handleMessageFromGUI(Object msg) {
		try {
			sendToServer(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void connectionClosed()
	{
		JOptionPane.showMessageDialog(null, "Error! Server is down, exit from Application...");
		System.exit(0);
		
	}
	
	@Override
	public void connectionException(Exception e)
	{
		e.printStackTrace();
		JOptionPane.showMessageDialog(null, "Error! exit from Application...");
		System.exit(0);
	}
	
	
	/**
	 * Main method for client side, opens new connection dialog.
	 * @param args - Execute parameters(not used).
	 */
	public static void main(String[] args)  {
	
		 SwingUtilities.invokeLater ( new Runnable ()
	        {
	            public void run ()
	            {
	            	//Install Java Look and feel
	            	WebLookAndFeel.install ();
	            	//Open connection dialog
	        		ConnectDialog dialog = new ConnectDialog();
	        		dialog.setVisible(true);
	            }
	        } );
		
		
		
	}
	


}
