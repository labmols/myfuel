package myfuel.gui;

import java.awt.event.ActionEvent;

import myfuel.GUIActions.CheckInventoryActions;
import javax.swing.JLabel;
import java.awt.Font;

@SuppressWarnings("serial")
public class CheckInventoryGUI extends SuperGUI{

	CheckInventoryActions actions;
	public CheckInventoryGUI(CheckInventoryActions actions) {
		lblTitle.setBounds(218, 0, 287, 25);
		lblTitle.setText("Inventory Orders");
		
		JLabel lblTypeOfFuel = new JLabel("Type of Fuel");
		lblTypeOfFuel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTypeOfFuel.setBounds(91, 84, 165, 40);
		panel.add(lblTypeOfFuel);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblQuantity.setBounds(351, 89, 143, 30);
		panel.add(lblQuantity);
		
		this.setContentPane(contentPane);
		this.actions = actions;
	}
	
	
	
	@Override
	public void getInput(ActionEvent e) {
		
		
	}
}
