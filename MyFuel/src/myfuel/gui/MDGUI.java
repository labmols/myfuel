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
		lblTitle.setBounds(194, 6, 250, 22);
		lblTitle.setText("Marketing Delegate Menu");
		
		 analystic = new JButton("Analystic System");
		 analystic.addActionListener(new btnHandler());
		analystic.setBounds(182, 88, 199, 61);
		panel.add(analystic);
		
		 btnCreatePromotionTemplate = new JButton("Create Promotion Template");
		btnCreatePromotionTemplate.setBounds(182, 173, 199, 61);
		btnCreatePromotionTemplate.addActionListener(new btnHandler());
		panel.add(btnCreatePromotionTemplate);
		
		 btnConfirmNewCustomers = new JButton("Confirm New Customers");
		 btnConfirmNewCustomers.addActionListener(new btnHandler());
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
			getInput(e);
			
		}
		
	}
	@Override
	public void getInput(ActionEvent e) {
		
		if(e.getSource() == btnCreatePromotionTemplate )
		{
			actions.createPromotionTemplate();
		}
		
		else if(e.getSource() == btnConfirmNewCustomers)
		{
			actions.createConfirmationWindow();
		}
		
		else if(e.getSource() == analystic)
		{
			actions.createAnalystic();
		}
		
	}
}
