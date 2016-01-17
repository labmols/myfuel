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
import java.awt.Font;

/**
 * Customer Options(Main Screen) User Interface.
 *
 */
@SuppressWarnings("serial")
public class CustomerOptionsGUI extends SuperGUI {

	/**
	 * GUI Controller object.
	 */
	private CustomerOptionsActions actions;
	/**
	 * Change Password option Button.
	 */
	private JButton btnChangePassword ;
	/**
	 * Update Details option Button.
	 */
	private JButton btnUpdateDetails;
	/**
	 * Car Fuel option Button.
	 */
	private JButton btnCarFuel;
	/**
	 * Home Fuel option Button.
	 */
	private JButton btnHomeFuel;
	/**
	 * Customer Purchases option Button.
	 */
	private JButton btnPurchases;
	
	private int type;
	
	/**
	 * Create new Customer Options User Interface.
	 * @param actions - Customer Options GUI Controller.
	 */
	public CustomerOptionsGUI(CustomerOptionsActions actions, int type) {
		super(actions);
	
		lblTitle.setBounds(201, 0, 207, 41);
		lblTitle.setText("Customer Options");
		this.actions = actions;
		this.type = type;
		
		setContentPane(contentPane);
		JPanel MenuPanel = new JPanel();
		MenuPanel.setBackground(Color.ORANGE);
		MenuPanel.setBounds(23, 59, 567, 364);
		MenuPanel.setOpaque(false);
		panel.add(MenuPanel);
		MenuPanel.setLayout(null);
		
		btnCarFuel = new JButton("Car Fuel");
		btnCarFuel.setFont(new Font("Arial", Font.PLAIN, 13));
		btnCarFuel.setBounds(179, 32, 180, 50);
		btnCarFuel.addActionListener(new ButtonListener());
		MenuPanel.add(btnCarFuel);
		
		btnHomeFuel = new JButton("Home Fuel");
		btnHomeFuel.setFont(new Font("Arial", Font.PLAIN, 13));
		btnHomeFuel.setBounds(179, 273, 180, 50);
		btnHomeFuel.addActionListener(new ButtonListener());
		if(type == 1)
		btnHomeFuel.setVisible(false);
		MenuPanel.add(btnHomeFuel);
		
		btnUpdateDetails = new JButton("Update Details");
		btnUpdateDetails.setFont(new Font("Arial", Font.PLAIN, 13));
		btnUpdateDetails.setBounds(179, 153, 180, 50);
		btnUpdateDetails.addActionListener(new ButtonListener());
		MenuPanel.add(btnUpdateDetails);
		
		btnChangePassword = new JButton("Change Password");
		btnChangePassword.setFont(new Font("Arial", Font.PLAIN, 13));
		btnChangePassword.addActionListener(new ButtonListener());
		btnChangePassword.setBounds(179, 215, 180, 50);
		MenuPanel.add(btnChangePassword);
		
		btnPurchases = new JButton("Purchase History");
		btnPurchases.setFont(new Font("Arial", Font.PLAIN, 13));
		btnPurchases.addActionListener(new ButtonListener());
		btnPurchases.setBounds(179, 91, 180, 50);
		MenuPanel.add(btnPurchases);
		
		
	}
	
	/**
	 * This Class used for handle all components events.
	 */
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
		else if(e.getSource() == btnPurchases ) actions.PurchaseHistory();
		
	}
	

}
