package myfuel.gui;

import java.awt.event.ActionEvent;

import myfuel.client.MDActions;
import javax.swing.JButton;

public class MDGUI extends SuperGUI {
	MDActions actions;
	public MDGUI(MDActions actions){
		lblTitle.setBounds(160, 6, 250, 22);
		lblTitle.setText("Marketing Delegate Menu");
		
		JButton btnNewButton = new JButton("Analystic System");
		btnNewButton.setBounds(182, 88, 199, 61);
		panel.add(btnNewButton);
		
		JButton btnCreatePromotionTemplate = new JButton("Create Promotion Template");
		btnCreatePromotionTemplate.setBounds(182, 173, 199, 61);
		panel.add(btnCreatePromotionTemplate);
		
		JButton btnConfirmNewCustomers = new JButton("Confirm New Customers");
		btnConfirmNewCustomers.setBounds(182, 269, 199, 61);
		panel.add(btnConfirmNewCustomers);
		this.actions=actions;
		setContentPane(contentPane);
	}

	@Override
	public void getInput(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
