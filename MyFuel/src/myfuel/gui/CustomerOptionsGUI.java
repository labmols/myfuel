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

import myfuel.GUIActions.CustomerOptionsActions;


@SuppressWarnings("serial")
public class CustomerOptionsGUI extends SuperGUI {

	
	private CustomerOptionsActions actions;
	private JButton btnChangePassword ;
	private JButton btnUpdateDetails;
	private JButton btnCarFuel;
	private JButton btnHomeFuel;
	private JButton btnPurchases;
	/**
	 * Create the frame.
	 */
	public CustomerOptionsGUI(CustomerOptionsActions actions) {
	
		lblTitle.setBounds(201, 0, 207, 41);
		lblTitle.setText("Customer Options");
		this.actions = actions;
		
		
		setContentPane(contentPane);
		JPanel MenuPanel = new JPanel();
		MenuPanel.setBackground(Color.ORANGE);
		MenuPanel.setBounds(23, 59, 567, 364);
		MenuPanel.setOpaque(false);
		panel.add(MenuPanel);
		MenuPanel.setLayout(null);
		
		btnCarFuel = new JButton("Car Fuel");
		btnCarFuel.setBounds(178, 22, 180, 50);
		btnCarFuel.addActionListener(new ButtonListener());
		MenuPanel.add(btnCarFuel);
		
		btnHomeFuel = new JButton("Home Fuel");
		btnHomeFuel.setBounds(178, 81, 180, 50);
		btnHomeFuel.addActionListener(new ButtonListener());
		MenuPanel.add(btnHomeFuel);
		
		btnUpdateDetails = new JButton("Update Details");
		btnUpdateDetails.setBounds(178, 143, 180, 50);
		btnUpdateDetails.addActionListener(new ButtonListener());
		MenuPanel.add(btnUpdateDetails);
		
		btnChangePassword = new JButton("Change Password");
		btnChangePassword.addActionListener(new ButtonListener());
		btnChangePassword.setBounds(178, 205, 180, 50);
		MenuPanel.add(btnChangePassword);
		
		btnPurchases = new JButton("Purchases");
		btnPurchases.addActionListener(new ButtonListener());
		btnPurchases.setBounds(178, 267, 180, 50);
		MenuPanel.add(btnPurchases);
		
		
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
		else if(e.getSource() == btnHomeFuel ) actions.HomeFuelScreen();
		else if(e.getSource() == btnPurchases ) {
			this.dispose();
			this.setVisible(false);
			CustomerPurchaseGUI gui = new CustomerPurchaseGUI();
			gui.setVisible(true);
		}
	}
}
