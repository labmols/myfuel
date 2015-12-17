package myfuel.gui;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import myfuel.GUIActions.ChangePassActions;

import java.awt.Font;


@SuppressWarnings("serial")
public class ChangePasswordGUI extends SuperGUI {

	//private JPanel contentPane;
	private JButton btnConfirm;
	private JPasswordField origPass;
	private JPasswordField newPass1;
	private JPasswordField newPass2;
	ChangePassActions actions;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public ChangePasswordGUI(ChangePassActions actions) {
	
		this.actions= actions;
		mainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actions.returnToMain();
			}
			
		});
		lblTitle.setBounds(230, 6, 174, 29);
		lblTitle.setText("Change Password");
		panel.setBounds(0, 0, 596, 458);
		
	
		setContentPane(contentPane);
		
		JLabel lblCurrentPassword = new JLabel("Current Password: ");
		lblCurrentPassword.setBounds(149, 59, 124, 16);
		panel.add(lblCurrentPassword);
		
		JLabel lblNewPassword = new JLabel("New Password: ");
		lblNewPassword.setBounds(171, 87, 113, 16);
		panel.add(lblNewPassword);
		
		JLabel lblRepeatNewPassword = new JLabel("Repeat New Password: ");
		lblRepeatNewPassword.setBounds(124, 115, 144, 16);
		panel.add(lblRepeatNewPassword);
		
		btnConfirm = new JButton("Confirm");
		btnConfirm.setBounds(244, 186, 117, 29);
		btnConfirm.addActionListener(new ButtonListener());
		panel.add(btnConfirm);
		
		origPass = new JPasswordField();
		origPass.setBounds(266, 54, 113, 26);
		panel.add(origPass);
		
		newPass1 = new JPasswordField();
		newPass1.setBounds(266, 82, 113, 26);
		panel.add(newPass1);
		
		newPass2 = new JPasswordField();
		newPass2.setBounds(266, 110, 113, 26);
		panel.add(newPass2);
	}
	
	private class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			getInput(e);
		}
		
	}
	

	@Override
	public void getInput(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == (JButton)btnConfirm)
			actions.verifyDetails(origPass.getPassword(), newPass1.getPassword(), newPass2.getPassword());
			//System.out.print(passwordField_1.getPassword());
	}
}
