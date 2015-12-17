package myfuel.gui;

import java.awt.event.ActionEvent;

import myfuel.GUIActions.SMActions;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class SMGUI extends SuperGUI{

	private SMActions actions;
	
	
	public SMGUI(SMActions actions) {
		lblTitle.setBounds(179, 6, 231, 25);
		lblTitle.setText("Station Manager Menu");
		
		JButton btnNewButton = new JButton("Create Station Reports");
		btnNewButton.setBounds(182, 95, 204, 46);
		panel.add(btnNewButton);
		
		JButton btnSetLowInventory = new JButton("Set Low Inventory Level");
		btnSetLowInventory.setBounds(182, 185, 204, 46);
		panel.add(btnSetLowInventory);
		
		JButton btnCheckInventoryOrder = new JButton("Check Inventory Order");
		btnCheckInventoryOrder.setBounds(182, 272, 204, 46);
		panel.add(btnCheckInventoryOrder);
		this.actions = actions;
		this.setContentPane(contentPane);
	}

	@Override
	public void getInput(ActionEvent e) {
		
		
	}

}
