package myfuel.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import myfuel.GUIActions.GUIActions;
import myfuel.GUIActions.PurchaseActions;
import myfuel.client.BackMainMenu;
import myfuel.client.HomeOrder;
import myfuel.client.Purchase;

import javax.swing.JLabel;

/**
 * Customer Purchase User interface, display all the purchase history for the customer.
 * @author Maor
 *
 */
@SuppressWarnings("serial")
public class CustomerPurchaseGUI extends SuperGUI {

	/**
	 * Table model.
	 */
	private MyTableModel model;
	/**
	 * Table that contains all the customer purchases details.
	 */
	private JTable purchaseTable;
	/**
	 * Purchase GUI Controller object, for handle all the logic functionality.
	 */
	private PurchaseActions actions;
	
	/**
	 * Create new Customer Purchase User interface.
	 * @param actions - GUI Controller object,for handle all the logic functionality.
	 */
	public CustomerPurchaseGUI(PurchaseActions actions)
	{
		super(actions);
		this.mainMenu.addActionListener(new BackMainMenu(actions));
		setContentPane(contentPane);
		panel.setLocation(0, 0);
		lblTitle.setBounds(226, 6, 175, 31);
		lblTitle.setText("Purchase History");
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 96, 584, 229);
		model = new MyTableModel(6,-1);
		String[] names = {"#" ,"Car","Station","Fuel","Date","Amount","Price"};
		for(String s : names)
			model.addColumn(s);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( SwingConstants.CENTER );
		
		purchaseTable = new JTable(model);
		purchaseTable.setFont(new Font("Arial", Font.PLAIN, 12));
		purchaseTable.setModel(model);
		purchaseTable.getColumnModel().getColumn(0).setPreferredWidth(5);
		purchaseTable.getColumnModel().getColumn(4).setPreferredWidth(100);
		for (int x = 0; x < model.getColumnCount(); x ++)
		{
			purchaseTable. getColumnModel (). getColumn (x). setCellRenderer (centerRenderer);
	     }
		scrollPane.setViewportView(purchaseTable);
		panel.add(scrollPane);
		
		JLabel lblHereYouCan = new JLabel("Here you can find all your purchases :");
		lblHereYouCan.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		lblHereYouCan.setBounds(16, 68, 274, 16);
		panel.add(lblHereYouCan);
		
	}
	@Override
	public void getInput(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void showPurchases(ArrayList<Purchase> pList) 
	{
			clearTable();
			String sdate="";
			if(pList != null)
			{
				for(Purchase p: pList)
				{
				Date date = p.getPdate();
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy HH:mm");
				sdate = format.format(date);
				model.insertRow(model.getRowCount(),new Object[]{p.getPid(),p.getCustomerCarID(),p.getSname(),p.getFuelName(),sdate,new DecimalFormat("##.##").format(p.getQty())+"(L)",new DecimalFormat("##.##").format(p.getBill())+" NIS"});
				}
			}
			else
			{
				this.showOKMessage("You don't have any Purchase that have been recorded!");
				actions.backToMenu();
			}
			
		}
	
	private void clearTable()
	{
		while(model.getRowCount() > 0 )
			model.removeRow(0);
	}

}
