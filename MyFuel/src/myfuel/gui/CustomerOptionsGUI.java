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
public class CustomerOptionsGUI extends SuperGUI {

	
	private UserOptionsActions actions;
	private JButton btnChangePassword ;
	private JButton btnUpdateDetails;
	private JButton btnCarFuel;
	/**
	 * Create the frame.
	 */
	public CustomerOptionsGUI(UserOptionsActions actions) {
	
		lblTitle.setBounds(211, 6, 207, 19);
		lblTitle.setText("Customer Options");
		this.actions = actions;
		
		
		setContentPane(contentPane);
		JPanel MenuPanel = new JPanel();
		MenuPanel.setBackground(Color.ORANGE);
		MenuPanel.setBounds(96, 61, 475, 337);
		MenuPanel.setOpaque(false);
		panel.add(MenuPanel);
		MenuPanel.setLayout(null);
		
		btnCarFuel = new JButton("Car Fuel");
		btnCarFuel.setBounds(103, 22, 180, 50);
		btnCarFuel.addActionListener(new ButtonListener());
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
		else if(e.getSource()==btnCarFuel) actions.carFuelScreen();
	}
}
