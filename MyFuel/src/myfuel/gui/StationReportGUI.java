package myfuel.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import myfuel.Entity.FuelQty;
import myfuel.Entity.Purchase;
import myfuel.GUIActions.StationReportActions;
import myfuel.Tools.BackMainMenu;
import myfuel.Tools.ReportEnum;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

/***
 * User interface for station reports
 * @author karmo
 *
 */
@SuppressWarnings("serial")
public class StationReportGUI extends SuperGUI{

	/***
	 * StationReportGUI controller
	 */
	private StationReportActions actions;
	
	/***
	 * Panel for Purchase report
	 */
	private PurchaseReportPanel purchaseR = null;
	/***
	 * Create Report Button
	 */
	private JButton create;
	
	/***
	 * Panel for Inventory report
	 */
	private InventoryReportPanel inventoryR = null;
	/***
	 * Panel for Incomes report
	 */
	private IncomesReportPanel incomesR = null;
	
	/***
	 * JComboBox for showing the quarters
	 */
	private JComboBox<String> dates;
	/***
	 * JComboBox for showing the report types
	 */
	private JComboBox<String> reportType;
	/***
	 * Load report button
	 */
	private JButton load;
	
	/***
	 * StationReportGUI Constructor
	 * @param actions - controller for this GUI
	 */
	public StationReportGUI(StationReportActions actions)
	{
		super(actions);
		lblTitle.setBounds(218, 6, 183, 25);
		lblTitle.setText("Station Reports");
		
		
		this.mainMenu.addActionListener(new BackMainMenu(actions));
		 dates = new JComboBox<String>();
		dates.setModel(new DefaultComboBoxModel<String>(new String[] {"Q1", "Q2", "Q3", "Q4"}));
		dates.setBounds(243, 64, 62, 35);
		panel.add(dates);
		
		 reportType = new JComboBox<String>();
		reportType.setModel(new DefaultComboBoxModel<String>(new String[] {"Inventory Report", "Purchases Report", "Incomes Report"}));
		reportType.addActionListener(new Handler());
		reportType.setBounds(42, 64, 162, 35);
		panel.add(reportType);
		
		 create = new JButton("Save");
		 create.addActionListener(new Handler() );
		create.setBounds(443, 63, 99, 37);
		panel.add(create);
		
		inventoryR = new InventoryReportPanel();
		inventoryR.setOpaque(false);
		inventoryR.setBounds(42, 114, 517, 310);
		panel.add(inventoryR);
		inventoryR.setVisible(false);
		
		purchaseR = new PurchaseReportPanel();
		purchaseR.setOpaque(false);
		purchaseR.setBounds(42, 114, 517, 310);
		panel.add(purchaseR);
		purchaseR.setVisible(false);
		
		
		incomesR = new IncomesReportPanel();
		incomesR.setOpaque(false);
		incomesR.setBounds(42, 114, 517, 310);
		panel.add(incomesR);
		
		load = new JButton("Load");
		load.addActionListener(new Handler());
		load.setBounds(344, 63, 89, 36);
		panel.add(load);
		incomesR.setVisible(false);
		
		this.actions = actions;
		this.setContentPane(contentPane);
	}
	
	
	@Override
	public void getInput(ActionEvent e) 
	{
		if(e.getSource() == create)
		{
			int q =  dates.getSelectedIndex();
			actions.setReport(q+1);
		}
		
		else if(e.getSource() == load)
		{
			int q =  dates.getSelectedIndex();
			actions.showReport(q + 1);
		}
		
		else if(e.getSource() == reportType)
		{

			int type = reportType.getSelectedIndex();
			
			if(type == 0)
			{
				inventoryR.setVisible(true);
				incomesR.setVisible(false);
				purchaseR.setVisible(false);
			}
			
			else if(type == 1 )
			{
				inventoryR.setVisible(false);
				incomesR.setVisible(false);
				purchaseR.setVisible(true);
			}
			
			else if(type == 2 )
			{
				incomesR.setVisible(true);
				inventoryR.setVisible(false);
				purchaseR.setVisible(false);
			}
		}
		
	}
	
	/***
	 * This method will set the Inventory report panel with its details
	 * @param inventory - The fuels with it's quantities for specific station
	 */
	public void setInventoryPanel(ArrayList<FuelQty> inventory)
	{
		this.inventoryR.deleteTable();
		this.inventoryR.setTable(inventory);
		inventoryR.setVisible(true);
		
	}
	
	/***
	 * Action Listener for handling action events
	 * @author karmo
	 *
	 */
	private class Handler implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			
			getInput(e);
			
		}
		
	}

	/***
	 * This method will set the Purchase report panel with its details
	 * @param p - Details for the purchase report
	 */
	public void setPurchasePanel(ArrayList<Purchase> p) 
	{
		purchaseR.clearTable();
		purchaseR.setTable(p);
		
	}
	
	/***
	 * This method will set the Incomes report panel with its details
	 * @param incomes - Details for the incomes report
	 */
	public void setIncomesPanel(ArrayList<Purchase> incomes)
	{
		this.incomesR.clearTable();
		incomesR.setTable(incomes);
		
	}
}
