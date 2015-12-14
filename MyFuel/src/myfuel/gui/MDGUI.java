package myfuel.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import myfuel.GUIActions.CPromotionTemplateActions;
import myfuel.GUIActions.MDActions;

import javax.swing.JButton;

public class MDGUI extends SuperGUI {
	MDActions actions;
	JButton btnCreatePromotionTemplate;
	JButton analystic;
	JButton btnConfirmNewCustomers;
	
	public MDGUI(MDActions actions){
		setContentPane(contentPane);
		lblTitle.setBounds(160, 6, 250, 22);
		lblTitle.setText("Marketing Delegate Menu");
		
		 analystic = new JButton("Analystic System");
		analystic.setBounds(182, 88, 199, 61);
		panel.add(analystic);
		
		 btnCreatePromotionTemplate = new JButton("Create Promotion Template");
		btnCreatePromotionTemplate.setBounds(182, 173, 199, 61);
		btnCreatePromotionTemplate.addActionListener(new btnHandler());
		panel.add(btnCreatePromotionTemplate);
		
		 btnConfirmNewCustomers = new JButton("Confirm New Customers");
		btnConfirmNewCustomers.setBounds(182, 269, 199, 61);
		panel.add(btnConfirmNewCustomers);
		this.actions=actions;
	
	}

	/***
	 * Listener for JButtons
	 * When a button is pressed the correct window will be show to the user 
	 * 
	 *
	 */
	private class btnHandler implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnCreatePromotionTemplate )
			{
				actions.createPromotionTemplate();
			}
			
		}
		
	}
	@Override
	public void getInput(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
