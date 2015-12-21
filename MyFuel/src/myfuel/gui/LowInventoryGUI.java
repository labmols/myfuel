package myfuel.gui;

import java.awt.event.ActionEvent;

import myfuel.GUIActions.LowInventoryActions;

import myfuel.client.BackMainMenu;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;

public class LowInventoryGUI extends SuperGUI{

	private LowInventoryActions actions;
	private JTextField LowFuel95;
	private JTextField LowFuelDiesel;
	private JTextField LowFuelScooter;
	
	public LowInventoryGUI(LowInventoryActions actions)
	{
		lblTitle.setBounds(203, 11, 162, 16);
		panel.setLocation(0, 0);
		this.actions = actions;
		this.setContentPane(contentPane);
		lblTitle.setText("Set Low limit Inventory");
		this.mainMenu.addActionListener(new BackMainMenu(actions));
		
		LowFuel95 = new JTextField();
		LowFuel95.setBounds(184, 122, 86, 20);
		panel.add(LowFuel95);
		LowFuel95.setColumns(10);
		
		JLabel lblLowInventory = new JLabel("Fuel 95 :");
		lblLowInventory.setBounds(93, 125, 97, 14);
		panel.add(lblLowInventory);
		
		JButton Update = new JButton("Update");
		Update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actions.verifyDetails(LowFuel95.getText(),LowFuelDiesel.getText(),LowFuelScooter.getText());
			}
		});
		Update.setBounds(311, 241, 89, 23);
		panel.add(Update);
		
		JLabel lblFuelDiesel = new JLabel("Fuel Diesel :");
		lblFuelDiesel.setBounds(93, 161, 80, 14);
		panel.add(lblFuelDiesel);
		
		JLabel lblFuelScooter = new JLabel("Fuel Scooter :");
		lblFuelScooter.setBounds(93, 203, 86, 14);
		panel.add(lblFuelScooter);
		
		LowFuelDiesel = new JTextField();
		LowFuelDiesel.setBounds(184, 158, 86, 20);
		panel.add(LowFuelDiesel);
		LowFuelDiesel.setColumns(10);
		
		LowFuelScooter = new JTextField();
		LowFuelScooter.setBounds(186, 200, 86, 20);
		panel.add(LowFuelScooter);
		LowFuelScooter.setColumns(10);
		
	}
	@Override
	public void getInput(ActionEvent e) {
		
	}
}
