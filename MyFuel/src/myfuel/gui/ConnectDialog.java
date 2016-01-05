package myfuel.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import myfuel.GUIActions.LoginActions;
import myfuel.client.MyFuelClient;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class ConnectDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField serverAddr;
	private JTextField port;

	/**
	 * Create the dialog.
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
			JButton btnConnect = new JButton("Connect");
			btnConnect.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				
						connectToServer();
						
				
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
	
	public void connectToServer()
	{
		boolean check = true;
		int porti=0;
		if(port.getText().equals("") || serverAddr.getText().equals(""))
		{
			check = false;
			JOptionPane.showMessageDialog(this, "Please fill all the fields!","Error",JOptionPane.ERROR_MESSAGE);	
			return;
		}
		try{
			porti = Integer.parseInt(port.getText());
		}
		catch(NumberFormatException e)
		{
			check = false;
			JOptionPane.showMessageDialog(this, "Port must be a number!","Error",JOptionPane.ERROR_MESSAGE);	
			e.printStackTrace();
			return;
		}
		if(check)
		{
		try {
			
			//this.connectToServer();
			MyFuelClient client = new MyFuelClient(serverAddr.getText(),porti);
			JOptionPane.showMessageDialog(this, "Connection Successful!","Connected",JOptionPane.PLAIN_MESSAGE);	
			dispose();
			new LoginActions(client);
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			JOptionPane.showMessageDialog(this, "Can't Connect to server in port "+port.getText(),"Error",JOptionPane.ERROR_MESSAGE);
		}
		
		
	}
	}
}
