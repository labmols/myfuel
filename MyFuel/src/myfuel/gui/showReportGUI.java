package myfuel.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import myfuel.GUIActions.StationReportActions;
import myfuel.GUIActions.showReportsActions;
import myfuel.client.BackMainMenu;
import myfuel.client.QuarterStationPurchase;
import myfuel.client.quarterStationIncome;
import myfuel.client.quarterStationInventory;

import javax.swing.JComboBox;
import javax.swing.JLabel;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;

@SuppressWarnings("serial")
public class showReportGUI extends SuperGUI{

	private showReportsActions actions;
	private CinventoryPanel inventoryPanel;
	private CIncomePanel incomesPanel;
	private CPurchasePanel purchasePanel;
	private JComboBox<String> comboBox;
	public showReportGUI(showReportsActions actions)
	{
		lblTitle.setBounds(205, 6, 194, 25);
		lblTitle.setText("Company Reports");
		this.mainMenu.addActionListener(new BackMainMenu(actions));
		 comboBox = new JComboBox<String>();
		comboBox.addActionListener(new panelPicker());
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Inventory Report", "Incomes Report", "Purchase Report"}));
		comboBox.setBounds(259, 71, 140, 20);
		panel.add(comboBox);
		
		JLabel lblChoose = new JLabel("Choose Report:");
		lblChoose.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblChoose.setBounds(102, 71, 131, 17);
		panel.add(lblChoose);
		
		
		inventoryPanel = new CinventoryPanel();
		inventoryPanel.setBounds(42, 114, 517, 310);
		inventoryPanel.setOpaque(false);
		
		panel.add(inventoryPanel);
		
		incomesPanel = new CIncomePanel();
		panel.add(incomesPanel);
		
		purchasePanel = new CPurchasePanel();
		panel.add(purchasePanel);
		
		purchasePanel.setVisible(false);
		incomesPanel.setVisible(false);
		inventoryPanel.setVisible(true);
		
		
		this.actions = actions;
		this.setContentPane(contentPane);
	}
	@Override
	public void getInput(ActionEvent e) 
	{
		if(comboBox.getSelectedIndex() == 0)
		{
			purchasePanel.setVisible(false);
			incomesPanel.setVisible(false);
			inventoryPanel.setVisible(true);
		}
		
		else if(comboBox.getSelectedIndex() == 1)
		{
			incomesPanel.setVisible(true);
			purchasePanel.setVisible(false);
			inventoryPanel.setVisible(false);
		}
		
		else if(comboBox.getSelectedIndex() == 2)
		{
			incomesPanel.setVisible(false);
			purchasePanel.setVisible(true);
			inventoryPanel.setVisible(false);
		}
		
		
		
	}
	
	
	private class panelPicker implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			getInput(e);
			
		}
		
	}
	public void setInventoryPanel(ArrayList<quarterStationInventory > in )
	{
		
		inventoryPanel.setDetails( in);
	}
	
	public void setIncomePanel(ArrayList<quarterStationIncome> qStationIncome) 
	{
		incomesPanel.setDetails(qStationIncome);
	}
	public void setPurchasePanel( ArrayList<QuarterStationPurchase> qStationPurchase) 
	{
		
		purchasePanel.setDetails(qStationPurchase);
	}
}
