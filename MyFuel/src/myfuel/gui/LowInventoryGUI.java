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
		lblTitle.setBounds(203, 0, 234, 30);
		panel.setLocation(0, 0);
		this.actions = actions;
		this.setContentPane(contentPane);
		lblTitle.setText("Set Low limit Inventory");
		this.mainMenu.addActionListener(new BackMainMenu(actions));
		
		LowFuel95 = new JTextField();
		LowFuel95.setBounds(298, 129, 86, 20);
		panel.add(LowFuel95);
		LowFuel95.setColumns(10);
		
		JLabel lblLowInventory = new JLabel("Fuel 95 :");
		lblLowInventory.setBounds(187, 132, 97, 14);
		panel.add(lblLowInventory);
		
		JButton Update = new JButton("Update");
		Update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actions.verifyDetails(LowFuel95.getText(),LowFuelDiesel.getText(),LowFuelScooter.getText());
			}
		});
		Update.setBounds(225, 286, 127, 41);
		panel.add(Update);
		
		JLabel lblFuelDiesel = new JLabel("Fuel Diesel :");
		lblFuelDiesel.setBounds(187, 163, 80, 14);
		panel.add(lblFuelDiesel);
		
		JLabel lblFuelScooter = new JLabel("Fuel Scooter :");
		lblFuelScooter.setBounds(187, 194, 86, 14);
		panel.add(lblFuelScooter);
		
		LowFuelDiesel = new JTextField();
		LowFuelDiesel.setBounds(298, 160, 86, 20);
		panel.add(LowFuelDiesel);
		LowFuelDiesel.setColumns(10);
		
		LowFuelScooter = new JTextField();
		LowFuelScooter.setBounds(298, 191, 86, 20);
		panel.add(LowFuelScooter);
		LowFuelScooter.setColumns(10);
		
	}
	@Override
	public void getInput(ActionEvent e) {
		
	}
}
