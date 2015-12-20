package myfuel.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import myfuel.GUIActions.SMActions;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class SMGUI extends SuperGUI{

	private SMActions actions;
	private JButton createReports;
	private JButton btnSetLowInventory;
	private JButton btnCheckInventoryOrder;
	
	public SMGUI(SMActions actions) {
		lblTitle.setBounds(179, 6, 231, 25);
		lblTitle.setText("Station Manager Menu");
		
		 createReports = new JButton("Create Station Reports");
		 createReports.addActionListener(new btnHandler());
		createReports.setBounds(182, 95, 204, 46);
		panel.add(createReports);
		
		 btnSetLowInventory = new JButton("Set Low Inventory Level");
		 btnSetLowInventory.addActionListener(new btnHandler());
		btnSetLowInventory.setBounds(182, 185, 204, 46);
		panel.add(btnSetLowInventory);
		
		 btnCheckInventoryOrder = new JButton("Check Inventory Order");
		 btnCheckInventoryOrder.addActionListener(new btnHandler());
		btnCheckInventoryOrder.setBounds(182, 272, 204, 46);
		panel.add(btnCheckInventoryOrder);
		this.actions = actions;
		this.setContentPane(contentPane);
	}

	@Override
	public void getInput(ActionEvent e) 
	{
		if(e.getSource() == btnCheckInventoryOrder)
		{
			actions.CreateCheckInventoryWindow();
		}
		
		else if(e.getSource() == btnSetLowInventory)
		{
			actions.CreateLowInventoryWindow();
		}
		
	}
	
	private class btnHandler implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			getInput(e);
			
		}
		
	}



}
