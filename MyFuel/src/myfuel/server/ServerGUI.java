package myfuel.server;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;

import myfuel.GUIActions.GUIActions;
import myfuel.gui.SuperGUI;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import javax.swing.ImageIcon;

import java.awt.Font;

public class ServerGUI extends SuperGUI{
	private JTextField portTxt;
	private JButton btnStartServer;
	private JButton btnStopServer;
	private JTextArea textArea;
	MyFuelServer server;
	private JTextField sqlAdd;
	private JTextField user;
	private JTextField pass;
	private JLabel serverStatus;
	private JLabel sqlStatus;
	
	
	
	public ServerGUI(GUIActions actions){
		super(actions);
		lblTitle.setBounds(228, 6, 163, 22);
		lblTitle.setText("MyFuel Server");
		setContentPane(contentPane);

		btnStartServer = new JButton("Start Server");
		btnStartServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					getInput(e);
				
			}
		});
		btnStartServer.setBounds(321, 140, 104, 34);
		panel.add(btnStartServer);
		
		JLabel lblPort = new JLabel("Port: ");
		lblPort.setBounds(311, 112, 61, 16);
		panel.add(lblPort);
		
		portTxt = new JTextField();
		portTxt.setText("5555");
		portTxt.setBounds(350, 107, 67, 26);
		panel.add(portTxt);
		portTxt.setColumns(10);
		
		btnStopServer = new JButton("Stop Server");
		btnStopServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stopServer();
			}
		});
		btnStopServer.setEnabled(false);
		btnStopServer.setBounds(437, 140, 104, 34);
		panel.add(btnStopServer);
		
		JPanel panel2 = new JPanel();
		panel2.setOpaque(false);
		panel2.setLocation(47, 191);
		panel2.setSize(476, 247);
		panel.add(panel2);
		textArea = new JTextArea(14, 35);
		textArea.setBackground(Color.WHITE);
		
		JScrollPane scrollPane = new JScrollPane(textArea); 
		textArea.setEditable(false);
		panel2.add(scrollPane);
		
		JLabel lblServerStatus = new JLabel("Server Status:");
		lblServerStatus.setBounds(210, 53, 94, 16);
		panel.add(lblServerStatus);
		
		JLabel lblMysqlStatus = new JLabel("MySQL Status:");
		lblMysqlStatus.setBounds(210, 79, 94, 16);
		panel.add(lblMysqlStatus);
		
		JLabel lblMysqlServerAddress = new JLabel("MySQL Server Address: ");
		lblMysqlServerAddress.setBounds(16, 112, 155, 16);
		panel.add(lblMysqlServerAddress);
		
		JLabel lblMysqlUser = new JLabel("MySQL User: ");
		lblMysqlUser.setBounds(77, 135, 85, 16);
		panel.add(lblMysqlUser);
		
		JLabel lblMysqlPassword = new JLabel("MySQL Password:");
		lblMysqlPassword.setBounds(49, 158, 122, 16);
		panel.add(lblMysqlPassword);
		
		sqlAdd = new JTextField();
		sqlAdd.setText("23.244.69.163:3306");
		sqlAdd.setBounds(160, 107, 139, 26);
		panel.add(sqlAdd);
		sqlAdd.setColumns(10);
		
		user = new JTextField();
		user.setText("myfuel");
		user.setColumns(10);
		user.setBounds(160, 130, 139, 26);
		panel.add(user);
		
		pass = new JTextField();
		pass.setText("labmols1");
		pass.setColumns(10);
		pass.setBounds(160, 153, 139, 26);
		panel.add(pass);
		
		serverStatus = new JLabel("Disconnected");
		serverStatus.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));
		serverStatus.setForeground(Color.RED);
		serverStatus.setBounds(302, 53, 104, 16);
		panel.add(serverStatus);
		
		sqlStatus = new JLabel("Disconnected");
		sqlStatus.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));
		sqlStatus.setForeground(Color.RED);
		sqlStatus.setBounds(302, 79, 115, 16);
		panel.add(sqlStatus);
		this.setJMenuBar(null);
		
	}
	
	public void startServer(int port)
	{
		server = new MyFuelServer(port,this);
		StartServer process = new StartServer(port);
		process.start();	    
	}
	
	private class StartServer extends Thread {
		private int port;
			public StartServer(int port)
			{
				this.port=port;
			}
			public void run() {
			  try{  
				  btnStartServer.setEnabled(false); 
		      server.createDBConnection(sqlAdd.getText(), user.getText(), pass.getText());
			  printMsg("Connected to MySQL Server successfully...");
			  sqlStatus.setText("Connected");
			  sqlStatus.setForeground(Color.GREEN);
			  
			  btnStopServer.setEnabled(true);
			  server.listen(); //Start listening for connections
		      serverStatus.setText("Connected");
		      serverStatus.setForeground(Color.GREEN);
		     
		      printMsg("Server is up and listening to port "+port +" ...");
		      
			  disableAll();
			  }
			  catch(SQLException e)
			  {
				  printMsg("Failed to connect MySQL Server ...");
				  stopServer();
			      btnStartServer.setEnabled(true);
				  e.printStackTrace();
			  }
			  catch (Exception ex) 
			    {
			      printMsg("ERROR - Could not listen for clients!");
			    }
			
		  }
	}
	
	public void stopServer()
	{
		server.stopListening();
		try {
			server.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		  this.serverStatus.setText("Disconnected");
	      this.serverStatus.setForeground(Color.RED);
	      btnStartServer.setEnabled(true);
	      this.btnStopServer.setEnabled(false);
	      server.closeDBConnection();
	      this.sqlStatus.setText("Disconnected");
	      this.sqlStatus.setForeground(Color.RED);
	      printMsg("Server is down... ");
	      this.enableAll();
	}
		  

	

	@Override
	public void getInput(ActionEvent e) {
		// TODO Auto-generated method stub
	int port;
		if(sqlAdd.getText().equals("") || user.getText().equals("") )
			this.showErrorMessage("Please fill all the fields!");
		else {
		try {
			port = Integer.parseInt(portTxt.getText());
			 startServer(port);
		}
		catch(NumberFormatException e1){
			this.showErrorMessage("Illegal port value!");
			e1.printStackTrace();
		}
		}
	}
	
	public void disableAll()
	{
		sqlAdd.setEditable(false);
		user.setEditable(false);
		pass.setEditable(false);
		portTxt.setEditable(false);
	}
	
	public void enableAll()
	{
		sqlAdd.setEditable(true);
		user.setEditable(true);
		pass.setEditable(true);
		portTxt.setEditable(true);
		 
	}
	
	public void printMsg(String msg)
	{
		textArea.append(msg +"\n");
	        //Make sure the new text is visible, even if there
	        //was a selection in the text area.
	        textArea.setCaretPosition(textArea.getDocument().getLength());
	}
	
	
}


