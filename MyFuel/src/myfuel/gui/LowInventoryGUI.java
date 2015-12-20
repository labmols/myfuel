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
	private JTextField NewLowInventory;
	
	public LowInventoryGUI(LowInventoryActions actions)
	{
		lblTitle.setBounds(203, 11, 162, 16);
		panel.setLocation(0, 0);
		this.actions = actions;
		this.setContentPane(contentPane);
		lblTitle.setText("Set Low limit Inventory");
		this.mainMenu.addActionListener(new BackMainMenu(actions));
		
		NewLowInventory = new JTextField();
		NewLowInventory.setBounds(259, 146, 86, 20);
		panel.add(NewLowInventory);
		NewLowInventory.setColumns(10);
		
		JLabel lblLowInventory = new JLabel("Low Inventory:");
		lblLowInventory.setBounds(152, 149, 97, 14);
		panel.add(lblLowInventory);
		
		JButton Update = new JButton("Update");
		Update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actions.verifyDetails(NewLowInventory.getText());
			}
		});
		Update.setBounds(256, 203, 89, 23);
		panel.add(Update);
		
	}
	@Override
	public void getInput(ActionEvent e) {
		
	}
}
