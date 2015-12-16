package myfuel.gui;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;

import myfuel.GUIActions.GUIActions;
import myfuel.GUIActions.LoginActions;
import myfuel.client.MyFuelClient;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;


public class LogInGUI extends SuperGUI {
	private JPasswordField passwordField;
	private JTextField useridField;
	private JComboBox comboBox;
	private JButton fastButton;
	private JButton loginButton;
	private JButton registerButton;
	LoginActions actions;

	/**
	 * Create the frame.
	 */
	public LogInGUI(LoginActions actions) {
		
		this.actions=actions;
		lblTitle.setBounds(271, 6, 61, 26);
		lblTitle.setText("Log In");
		setContentPane(contentPane);
		
		JLabel uidLabel = new JLabel("UserID:");
		uidLabel.setBounds(205, 136, 61, 16);
		panel.add(uidLabel);
		
		JLabel passLabel = new JLabel("Password:");
		passLabel.setBounds(182, 169, 73, 16);
		panel.add(passLabel);
		
		useridField = new JTextField();
		useridField.setBounds(253, 131, 130, 26);
		panel.add(useridField);
		useridField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(253, 164, 130, 26);
		panel.add(passwordField);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Customer", "Worker"}));
		comboBox.setBounds(253, 92, 130, 27);
		panel.add(comboBox);
		
		JLabel typeLabel = new JLabel("Type:");
		typeLabel.setBounds(205, 96, 61, 16);
		panel.add(typeLabel);
		
		loginButton = new JButton("Login");
		loginButton.setBounds(230, 215, 117, 29);
		loginButton.addActionListener(new ButtonListener());
		panel.add(loginButton);
		
		fastButton = new JButton("Fast Fuel");
		fastButton.setIcon(new ImageIcon("images/fast.png"));
		fastButton.setBounds(424, 51, 141, 46);
		panel.add(fastButton);
		
		JLabel nfcLabel = new JLabel("Do you have NFC?");
		nfcLabel.setBounds(302, 66, 117, 16);
		panel.add(nfcLabel);
		
		JLabel registerLabel = new JLabel("Don't have an account? ");
		registerLabel.setBounds(27, 275, 166, 16);
		panel.add(registerLabel);
		
		registerButton = new JButton("Register");
		registerButton.setBounds(171, 270, 117, 29);
		registerButton.addActionListener(new ButtonListener());
		panel.add(registerButton);
		

	
	}

	
	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			getInput(e);
			
		}
		
	}
	
	public void getInput(ActionEvent e){
	if(e.getSource()==loginButton){
	String pass =new String(passwordField.getPassword());
	actions.sendRequest(comboBox.getSelectedIndex(),Integer.parseInt(useridField.getText().toString()), pass);}
	if(e.getSource()==registerButton){
		actions.RegisterScreen();
	}
	}

}
