package myfuel.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import myfuel.GUIActions.UserOptionsActions;


@SuppressWarnings("serial")
public class UserOptionsGUI extends SuperGUI {

	
	UserOptionsActions actions;
	JButton btnChangePassword ;
	JButton btnUpdateDetails;
	/**
	 * Create the frame.
	 */
	public UserOptionsGUI(UserOptionsActions actions) {
	
		lblTitle.setBounds(211, 6, 138, 19);
		lblTitle.setText("User Options");
		this.actions = actions;
		setResizable(false);
		
		
		
	
		
		setContentPane(contentPane);

		
		JPanel MenuPanel = new JPanel();
		MenuPanel.setBackground(Color.ORANGE);
		MenuPanel.setBounds(96, 61, 475, 337);
		MenuPanel.setOpaque(false);
		panel.add(MenuPanel);
		MenuPanel.setLayout(null);
		
		JButton btnCarFuel = new JButton("Car Fuel");
		btnCarFuel.setBounds(103, 22, 180, 50);
		MenuPanel.add(btnCarFuel);
		
		JButton btnHomeFuel = new JButton("Home Fuel");
		btnHomeFuel.setBounds(103, 84, 180, 50);
		MenuPanel.add(btnHomeFuel);
		
		btnUpdateDetails = new JButton("Update Details");
		btnUpdateDetails.setBounds(103, 208, 180, 50);
		btnUpdateDetails.addActionListener(new ButtonListener());
		MenuPanel.add(btnUpdateDetails);
		
		JButton btnNewButton = new JButton("Tracking Home Fuel Order");
		btnNewButton.setBounds(103, 146, 180, 50);
		MenuPanel.add(btnNewButton);
		
		btnChangePassword = new JButton("Change Password");
		btnChangePassword.addActionListener(new ButtonListener());
		btnChangePassword.setBounds(103, 270, 180, 50);
		MenuPanel.add(btnChangePassword);
		
		
	}
	
	private class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			getInput(e);
		}
		
	}

	@Override
	public void getInput(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btnChangePassword) actions.changePasswordScreen();
		else if (e.getSource() ==btnUpdateDetails )actions.updateDetailsScreen();
	}
}
