package myfuel.client;

import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.alee.laf.WebLookAndFeel;


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
	
	
	
	public static void main(String[] args)  {
	
		 SwingUtilities.invokeLater ( new Runnable ()
	        {
	            public void run ()
	            {
	            	WebLookAndFeel.install ();
	        		ConnectDialog dialog = new ConnectDialog();
	        		dialog.setVisible(true);
	            }
	        } );
		
		
		
	}
	


}
