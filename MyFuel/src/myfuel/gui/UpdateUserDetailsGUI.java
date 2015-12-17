package myfuel.gui;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JInternalFrame;
import javax.swing.JTextField;
import javax.swing.JButton;

import myfuel.GUIActions.UpdateDetailsActions;
import myfuel.client.Customer;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;


public class UpdateUserDetailsGUI extends SuperGUI {
	
	
	private UpdateDetailsActions actions;
	private JTextField fnameText;
	private JTextField lnameText;
	private JTextField addText;
	private JTextField emailText;
	private JTextField CCText;
	
	/**
	 * Create the frame.
	 */
	public UpdateUserDetailsGUI(UpdateDetailsActions actions) {
		
		lblTitle.setBounds(220, 6, 144, 23);
		lblTitle.setText("Update Details");
		this.actions = actions;
		setContentPane(contentPane);
		
		JButton btnConfirmUpdate = new JButton("Confirm Update");
		btnConfirmUpdate.setBounds(83, 365, 130, 29);
		panel.add(btnConfirmUpdate);
		
		JPanel panel2 = new JPanel();
		panel2.setBackground(Color.WHITE);
		panel2.setBounds(30, 55, 535, 298);
		panel2.setOpaque(false);
		panel.add(panel2);
		panel2.setLayout(null);
		
		fnameText = new JTextField();
		fnameText.setBounds(123, 36, 141, 26);
		panel2.add(fnameText);
		fnameText.setColumns(10);
		
		lnameText = new JTextField();
		lnameText.setBounds(123, 71, 141, 26);
		panel2.add(lnameText);
		lnameText.setColumns(10);
		
		addText = new JTextField();
		addText.setBounds(123, 109, 141, 26);
		panel2.add(addText);
		addText.setColumns(10);
		
		emailText = new JTextField();
		emailText.setBounds(123, 185, 141, 26);
		panel2.add(emailText);
		emailText.setColumns(10);
		
		CCText = new JTextField();
		CCText.setBounds(123, 147, 141, 26);
		panel2.add(CCText);
		CCText.setColumns(10);
		
		JLabel lblUserName = new JLabel("First Name:");
		lblUserName.setBounds(41, 41, 78, 16);
		panel2.add(lblUserName);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setBounds(41, 76, 78, 16);
		panel2.add(lblLastName);
		
		JLabel lblAddress = new JLabel("Address: ");
		lblAddress.setBounds(41, 114, 78, 16);
		panel2.add(lblAddress);
		
		JLabel lblEmail = new JLabel("E-mail: ");
		lblEmail.setBounds(41, 190, 78, 16);
		panel2.add(lblEmail);
		
		JLabel lblCreditCard = new JLabel("Credit Card: ");
		lblCreditCard.setBounds(41, 152, 91, 16);
		panel2.add(lblCreditCard);
		
		JLabel lblAccesstype = new JLabel("Access Type:");
		lblAccesstype.setBounds(299, 41, 91, 16);
		panel2.add(lblAccesstype);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"One Station", "Few Stations"}));
		comboBox.setBounds(383, 37, 124, 27);
		panel2.add(comboBox);
		
		
	
		showUserDetails(actions.getUserDetails());
		
		mainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actions.returnToMain();
			}
			
		});
		
	}
	
	public void showUserDetails(Customer user){
		fnameText.setText(user.getFname());
		lnameText.setText(user.getLname());
		addText.setText(user.getAddress());;
		emailText.setText(user.getEmail());;
		CCText.setText(user.getCnumber());
		
	}

	@Override
	public void getInput(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
