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


public class UpdateUserDetailsGUI extends SuperGUI {
	
	
	private UpdateDetailsActions actions;
	private JLabel fnamelbl;
	private JLabel lnamelbl;
	private JLabel addlbl;
	private JLabel emaillbl;
	private JLabel CClbl;
	
	/**
	 * Create the frame.
	 */
	public UpdateUserDetailsGUI(UpdateDetailsActions actions) {
		
		lblTitle.setBounds(220, 6, 144, 23);
		lblTitle.setText("Update Details");
		this.actions = actions;
		setContentPane(contentPane);
		
		JLabel lblUserName = new JLabel("First Name:");
		lblUserName.setBounds(50, 46, 78, 16);
		panel.add(lblUserName);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setBounds(50, 74, 78, 16);
		panel.add(lblLastName);
		
		JLabel lblEmail = new JLabel("E-mail: ");
		lblEmail.setBounds(50, 130, 78, 16);
		panel.add(lblEmail);
		
		JLabel lblAddress = new JLabel("Address: ");
		lblAddress.setBounds(50, 102, 78, 16);
		panel.add(lblAddress);
		
		JLabel lblCreditCard = new JLabel("Credit Card: ");
		lblCreditCard.setBounds(50, 158, 91, 16);
		panel.add(lblCreditCard);
		
		JButton btnConfirmUpdate = new JButton("Confirm Update");
		btnConfirmUpdate.setBounds(50, 204, 130, 29);
		panel.add(btnConfirmUpdate);
		
		fnamelbl = new JLabel("");
		fnamelbl.setBounds(127, 46, 185, 16);
		panel.add(fnamelbl);
		
		lnamelbl = new JLabel("");
		lnamelbl.setBounds(127, 74, 185, 16);
		panel.add(lnamelbl);
		
		addlbl = new JLabel("");
		addlbl.setBounds(140, 102, 61, 16);
		panel.add(addlbl);
		
		emaillbl = new JLabel("");
		emaillbl.setBounds(127, 130, 185, 16);
		panel.add(emaillbl);
		
		CClbl = new JLabel("");
		CClbl.setBounds(140, 158, 61, 16);
		panel.add(CClbl);
		
	
		showUserDetails(actions.getUserDetails());
		
		mainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actions.returnToMain();
			}
			
		});
		
	}
	
	public void showUserDetails(Customer user){
		fnamelbl.setText(user.getFname());
		lnamelbl.setText(user.getLname());
		emaillbl.setText(user.getEmail());
	}

	@Override
	public void getInput(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
