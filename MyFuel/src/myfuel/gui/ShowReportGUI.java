package myfuel.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import myfuel.GUIActions.StationReportActions;
import myfuel.GUIActions.showReportsActions;
import myfuel.client.BackMainMenu;
import myfuel.client.QuarterStationPurchase;
import myfuel.client.QuarterStationIncome;
import myfuel.client.QuarterStationInventory;

import javax.swing.JComboBox;
import javax.swing.JLabel;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

/***
 * User interface for showing the Company Reports
 * @author karmo
 *
 */
@SuppressWarnings("serial")
public class ShowReportGUI extends SuperGUI{

	/***
	 * Controller for this GUI
	 */
	private showReportsActions actions;
	/***
	 * Panel for showing the inventory Report
	 */
	private CinventoryPanel inventoryPanel;
	/***
	 * Panel for showing the Incomes Report
	 */
	private CIncomePanel incomesPanel;
	/***
	 * Panel for showing the Purchases Report
	 */
	private CPurchasePanel purchasePanel;
	/***
	 * ComboBox for picking the type of report
	 */
	private JComboBox<String> comboBox;
	/***
	 * ComboBox for picking the Year
	 */
	private JComboBox<Integer> years;
	/***
	 * Selection Button
	 */
	private JButton btnSelect;
	/***
	 * ShowReportGUI Constructor
	 * @param actions - Controller for this GUI
	 */
	public ShowReportGUI(showReportsActions actions)
	{
		lblTitle.setBounds(205, 6, 194, 25);
		lblTitle.setText("Company Reports");
		this.mainMenu.addActionListener(new BackMainMenu(actions));
		 comboBox = new JComboBox<String>();
		comboBox.addActionListener(new Handler());
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Inventory Report", "Incomes Report", "Purchase Report"}));
		comboBox.setBounds(128, 71, 140, 20);
		panel.add(comboBox);
		
		JLabel lblChoose = new JLabel("Choose Report:");
		lblChoose.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblChoose.setBounds(10, 71, 131, 17);
		panel.add(lblChoose);
		
		
		inventoryPanel = new CinventoryPanel();
		inventoryPanel.setBounds(42, 114, 517, 310);
		inventoryPanel.setOpaque(false);
		
		panel.add(inventoryPanel);
		
		incomesPanel = new CIncomePanel();
		panel.add(incomesPanel);
		
		purchasePanel = new CPurchasePanel();
		panel.add(purchasePanel);
		
		years = new JComboBox<Integer>();
		years.setBounds(376, 71, 87, 20);
		panel.add(years);
		
		JLabel lblChooseYear = new JLabel("Choose Year:");
		lblChooseYear.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblChooseYear.setBounds(278, 71, 118, 17);
		panel.add(lblChooseYear);
		
		btnSelect = new JButton("Select");
		btnSelect.setBounds(481, 70, 89, 23);
		btnSelect.addActionListener(new Handler());
		panel.add(btnSelect);
		
		purchasePanel.setVisible(false);
		incomesPanel.setVisible(false);
		inventoryPanel.setVisible(true);
		
		
		this.actions = actions;
		this.setContentPane(contentPane);
	}
	
	
	
	
	@Override
	public void getInput(ActionEvent e) 
	{
		if(e.getSource() == comboBox)
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
		
		else if(e.getSource() == btnSelect)
		{
			actions.getDetails((Integer)years.getSelectedItem());
		}
		
		
		
	}
	
	/***
	 * Action Listener for GUI Objects in this class
	 * @author karmo
	 *
	 */
	private class Handler implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			getInput(e);
			
		}
		
	}
	
	/***
	 * This method will update the Inventory Report Panel
	 * @param in - Inventory Report Details
	 */
	public void setInventoryPanel(ArrayList<QuarterStationInventory > in )
	{
		
		inventoryPanel.setDetails( in);
	}
	/***
	 * This method will update the Incomes Report Panel
	 * @param qStationIncome - Incomes Report Details
	 */
	public void setIncomePanel(ArrayList<QuarterStationIncome> qStationIncome) 
	{
		incomesPanel.setDetails(qStationIncome);
	}
	/***
	 * This method will update the Purchase Report Panel
	 * @param qStationPurchase - Purchase Report Details
	 */
	public void setPurchasePanel( ArrayList<QuarterStationPurchase> qStationPurchase) 
	{
		
		purchasePanel.setDetails(qStationPurchase);
	}
	
	/***
	 * Setting the Years ComboBox with the Years Documented at the DB
	 * @param y - Years that documented at the DB
	 */
	public void setYears(ArrayList<Integer> y)
	{
		for(Integer i : y)
			years.addItem(i);
	}
}
