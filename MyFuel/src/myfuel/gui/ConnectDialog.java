package myfuel.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

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
					try {
						MyFuelClient client = new MyFuelClient("localhost",5555);
						new LoginActions(client);
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "Can't Connect to server in port 5555");
					}
				}
				
			});
			btnConnect.setBounds(163, 151, 96, 29);
			contentPanel.add(btnConnect);
		}
		
		serverAddr = new JTextField();
		serverAddr.setBounds(210, 42, 130, 26);
		contentPanel.add(serverAddr);
		serverAddr.setColumns(10);
		
		port = new JTextField();
		port.setBounds(210, 82, 130, 26);
		contentPanel.add(port);
		port.setColumns(10);
	}
}
