package myfuel.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import myfuel.GUIActions.LoginActions;
import myfuel.client.MyFuelClient;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

/**
 * Connect to server dialog user interface.
 * This class creates new connection to server.
 *
 */
public class ConnectDialog extends JDialog {

	/**
	 * Content Panel
	 */
	private final JPanel contentPanel = new JPanel();
	/**
	 * Server address TextField.
	 */
	private JTextField serverAddr;
	/**
	 * Port value TextField.
	 */
	private JTextField port;
	/**
	 * Wait dialog(for indicates the connecting status)
	 */
	private WaitDialog waitD;
	/**
	 * Port value integer.
	 */
    private int porti;
    
    JButton btnConnect ;
	
/**
 * Create new connection dialog.
 */
	public ConnectDialog() {
		setBounds(100, 100, 450, 231);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblHostAddress = new JLabel("Server Address: ");
			lblHostAddress.setBounds(107, 47, 105, 16);
			contentPanel.add(lblHostAddress);
		}
		{
			JLabel lblPort = new JLabel("Port: ");
			lblPort.setBounds(132, 87, 44, 16);
			contentPanel.add(lblPort);
		}
		{
			btnConnect = new JButton("Connect");
			btnConnect.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
						connectToServer();
						btnConnect.setEnabled(false);
				}
				
			});
			btnConnect.setBounds(178, 141, 96, 29);
			contentPanel.add(btnConnect);
		}
		
		serverAddr = new JTextField();
		serverAddr.setText("localhost");
		serverAddr.setBounds(210, 42, 130, 26);
		contentPanel.add(serverAddr);
		serverAddr.setColumns(10);
		
		port = new JTextField();
		port.setText("5555");
		port.setBounds(210, 82, 130, 26);
		contentPanel.add(port);
		port.setColumns(10);
		{
			JLabel lblConnectToMyfuel = new JLabel("Connect to MyFuel Server");
			lblConnectToMyfuel.setBounds(142, 6, 178, 16);
			contentPanel.add(lblConnectToMyfuel);
		}
		{
			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setBounds(0, 0, 450, 209);
			contentPanel.add(lblNewLabel);
			 java.net.URL url = getClass().getResource("/dialogBG.png");
			 lblNewLabel.setIcon(new ImageIcon(url));
		}
	}
	
	/**
	 * This method check the user input and if all correct , it creates new connection to server. 
	 */
	public void connectToServer()
	{
		boolean check = true;
		this.porti =0;
		if(port.getText().equals("") || serverAddr.getText().equals(""))
		{
			check = false;
			JOptionPane.showMessageDialog(this, "Please fill all the fields!","Error",JOptionPane.ERROR_MESSAGE);	
			btnConnect.setEnabled(true);
			return;
		}
		try{
			this.porti = Integer.parseInt(port.getText());
		}
		catch(NumberFormatException e)
		{
			check = false;
			JOptionPane.showMessageDialog(this, "Port must be a number!","Error",JOptionPane.ERROR_MESSAGE);	
			e.printStackTrace();
			btnConnect.setEnabled(true);
			return;
		}
		if(check)
		{
			//Connect to server thread (to not interrupt the user interface).
			Thread t = new Thread (new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					createWaitDialog("Connecting...");
					try
					{
						MyFuelClient client = new MyFuelClient(serverAddr.getText(),getPort());
							// TODO Auto-generated method stub
						setWaitPorgress();
						JOptionPane.showMessageDialog(null, "Connection Successful!","Connected",JOptionPane.INFORMATION_MESSAGE);	
						dispose();
						new LoginActions(client);
							
					}catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
									setWaitPorgress();
									btnConnect.setEnabled(true);
									JOptionPane.showMessageDialog(null, "Can't Connect to server in port "+port.getText(),"Error",JOptionPane.ERROR_MESSAGE);
								}
				}
			});
			t.start();
			
		
		
	}
	}
	
	public int getPort()
	{
		return this.porti;
	}
	
	/**
	 * Create new Waiting dialog for indicates process.
	 * @param msg
	 */
	public void createWaitDialog(String msg)
	{
		waitD = new WaitDialog(msg);
		waitD.setVisible(true);
	}
	
	/**
	 * Stop waiting dialog.
	 */
	public void setWaitPorgress()
	{
		waitD.setProgress(1);
	}
}
