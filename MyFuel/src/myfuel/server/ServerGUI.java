package myfuel.server;

import java.awt.Color;
import java.awt.event.ActionEvent;

import myfuel.gui.SuperGUI;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JTextArea;

public class ServerGUI extends SuperGUI{
	private JTextField textField;
	private JButton btnStartServer;
	private JButton btnStopServer;
	private JTextArea textArea;
	
	ServerGUI(){
		lblTitle.setBounds(207, 6, 163, 22);
		lblTitle.setText("MyFuel Server");
		setContentPane(contentPane);
		
		btnStartServer = new JButton("Start Server");
		btnStartServer.setBounds(266, 63, 104, 34);
		panel.add(btnStartServer);
		
		JLabel lblPort = new JLabel("Port: ");
		lblPort.setBounds(156, 71, 61, 16);
		panel.add(lblPort);
		
		textField = new JTextField();
		textField.setBounds(186, 66, 67, 26);
		panel.add(textField);
		textField.setColumns(10);
		
		btnStopServer = new JButton("Stop Server");
		btnStopServer.setBounds(382, 63, 104, 34);
		panel.add(btnStopServer);
		
		 textArea = new JTextArea(5, 20);
	      textArea.setBackground(Color.darkGray);
	        textArea.setForeground(Color.WHITE);
	        textArea.setEditable(false);
	        JScrollPane scrollPane = new JScrollPane(textArea);
		panel.add(textArea);
		
	}

	@Override
	public void getInput(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
