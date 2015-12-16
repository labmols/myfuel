package myfuel.server;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;

import myfuel.gui.SuperGUI;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;

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
		btnStartServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getInput(e);
			}
		});
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
		
		JPanel panel2 = new JPanel();
		panel2.setOpaque(false);
		panel2.setLocation(48, 113);
		panel2.setSize(476, 293);
		panel.add(panel2);
		textArea = new JTextArea(20, 40);
		textArea.setBackground(Color.WHITE);
		
		JScrollPane scrollPane = new JScrollPane(textArea); 
		textArea.setEditable(false);
		panel2.add(scrollPane);
		
		
	}

	@Override
	public void getInput(ActionEvent e) {
		// TODO Auto-generated method stub
		textArea.append("a" +"\n");
		
	}
	
	public static void main(String [] args){
		JFrame f = new ServerGUI();
		f.setVisible(true);
		
		
	}
}
