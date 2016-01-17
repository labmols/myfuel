package myfuel.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import myfuel.GUIActions.StationReportActions;
import myfuel.client.BackMainMenu;
import myfuel.client.FuelQty;
import myfuel.client.Purchase;
import myfuel.client.ReportEnum;

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
	
	private InventoryReportPanel inventoryR = null;
	private IncomesReportPanel incomesR = null;
	
	private JComboBox<String> dates;
	private JComboBox<String> reportType;
	
	public StationReportGUI(StationReportActions actions)
	{
		super(actions);
		lblTitle.setBounds(218, 6, 183, 25);
		lblTitle.setText("Station Reports");
		
		
		this.mainMenu.addActionListener(new BackMainMenu(actions));
		 dates = new JComboBox<String>();
		dates.setModel(new DefaultComboBoxModel<String>(new String[] {"Q1", "Q2", "Q3", "Q4"}));
		dates.setBounds(254, 64, 62, 35);
		panel.add(dates);
		
		 reportType = new JComboBox<String>();
		reportType.setModel(new DefaultComboBoxModel<String>(new String[] {"Inventory Report", "Purchases Report", "Incomes Report"}));
		reportType.setBounds(42, 64, 162, 35);
		panel.add(reportType);
		
		 create = new JButton("Create");
		 create.addActionListener(new btnHandler() );
		create.setBounds(369, 64, 138, 37);
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
			int report = reportType.getSelectedIndex();
			ReportEnum r = null;
			
			switch(report)
			{
			case 0:
					r = ReportEnum.InventoryReport;
					
					break;
			case 1:
					r = ReportEnum.PurchaseReport;
					break;
			case 2:
					r = ReportEnum.IncomesReport;
					break;
				
			}
			actions.getReport(q+1,r);
		}
		
	}
	/***
	 * This method will set the Inventory report panel with its details
	 * @param inventory - The fuels with it's quantities for specific station
	 */
	public void setInventoryPanel(ArrayList<FuelQty> inventory)
	{
		this.inventoryR.setTable(inventory);
		inventoryR.setVisible(true);
		incomesR.setVisible(false);
		purchaseR.setVisible(false);
	}
	
	/***
	 * Action Listener for handling action events
	 * @author karmo
	 *
	 */
	private class btnHandler implements ActionListener
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
		purchaseR.setTable(p);
		inventoryR.setVisible(false);
		incomesR.setVisible(false);
		purchaseR.setVisible(true);
	}
	
	/***
	 * This method will set the Incomes report panel with its details
	 * @param incomes - Details for the incomes report
	 */
	public void setIncomesPanel(ArrayList<Purchase> incomes)
	{
		incomesR.setTable(incomes);
		incomesR.setVisible(true);
		inventoryR.setVisible(false);
		purchaseR.setVisible(false);
	}
}
