package myfuel.gui;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.Cursor;

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

/**
 * Login User Interface(For Worker/Customer).
 */
public class LogInGUI extends SuperGUI {
	
	/**
	 * User Password TextField.
	 */
	private JPasswordField passwordField;
	/**
	 * User ID value.
	 */
	private JTextField useridField;
	/**
	 * Type of user ComboBox.
	 */
	private JComboBox comboBox;
	/**
	 * Fast fuel option button.
	 */
	private JButton fastButton;
	/**
	 * Login(send new login request) button.
	 */
	private JButton loginButton;
	/**
	 * Register option button (move to register screen).
	 */
	private JButton registerButton;
	/**
	 * Login GUI Controller.
	 */
	private LoginActions actions;

	/**
	 * Create new Login User Interface.
	 * @param actions - Login GUI Controller.
	 */
	public LogInGUI(LoginActions actions) {
		this.actions=actions;
		lblTitle.setBounds(271, 6, 61, 26);
		lblTitle.setText("Log In");
		setContentPane(contentPane);
		
		JLabel uidLabel = new JLabel("UserID:");
		uidLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 13));
		uidLabel.setToolTipText("Your user id");
		uidLabel.setBounds(192, 170, 61, 16);
		panel.add(uidLabel);
		
		JLabel passLabel = new JLabel("Password:");
		passLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 13));
		passLabel.setToolTipText("Your password");
		passLabel.setBounds(182, 203, 73, 16);
		panel.add(passLabel);
		
		useridField = new JTextField();
		useridField.setFont(new Font("Arial", Font.PLAIN, 13));
		useridField.setBounds(253, 165, 130, 26);
		panel.add(useridField);
		useridField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Arial", Font.PLAIN, 13));
		passwordField.setBounds(253, 198, 130, 26);
		panel.add(passwordField);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Customer", "Worker"}));
		comboBox.setBounds(253, 131, 130, 27);
		panel.add(comboBox);
		
		JLabel typeLabel = new JLabel("Type:");
		typeLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 13));
		typeLabel.setToolTipText("Customer/Worker");
		typeLabel.setBounds(205, 135, 61, 16);
		panel.add(typeLabel);
		
		loginButton = new JButton("Login");
		loginButton.setFont(new Font("Arial", Font.PLAIN, 13));
		loginButton.setBounds(253, 237, 117, 29);
		loginButton.addActionListener(new ButtonListener());
		panel.add(loginButton);
		
		fastButton = new JButton("Fast Fuel");
		fastButton.setFont(new Font("Arial", Font.PLAIN, 13));
		java.net.URL url = getClass().getResource("/fast.png");
		fastButton.setIcon(new ImageIcon(url));
		fastButton.setBounds(280, 56, 150, 46);
		panel.add(fastButton);
		
		JLabel nfcLabel = new JLabel("Do you have NFC?");
		nfcLabel.setFont(new Font("Arial", Font.PLAIN, 13));
		nfcLabel.setBounds(158, 71, 117, 16);
		panel.add(nfcLabel);
		
		JLabel registerLabel = new JLabel("Don't have an account? ");
		registerLabel.setFont(new Font("Arial", Font.ITALIC, 14));
		registerLabel.setBounds(182, 283, 166, 16);
		panel.add(registerLabel);
		
		registerButton = new JButton("Register");
		registerButton.setFont(new Font("Arial", Font.PLAIN, 13));
		registerButton.setBounds(338, 278, 117, 29);
		registerButton.addActionListener(new ButtonListener());
		panel.add(registerButton);
		
		

	
	}

	/**
	 * This class used for handling all components events.
	 *
	 */
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
	
	actions.verifyDetails(comboBox.getSelectedIndex(),useridField.getText(), pass);
	
	}
	if(e.getSource()==registerButton){
		actions.RegisterScreen();
	}
	}

	

}
