package myfuel.gui;

import javax.swing.JPanel;

import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import myfuel.client.FuelQty;

/***
 * Inventory Report User Interface
 * @author karmo
 *
 */
@SuppressWarnings("serial")
public class InventoryReportPanel extends JPanel 
{
	/***
	 * Table for showing the details
	 */
	private JTable table;
	/***
	 * Table Model
	 */
	private DefaultTableModel model;
	/***
	 * Report Details
	 */
	private ArrayList<FuelQty> inventory;
	
	/***
	 * InventoryReportPanel Constructor
	 */
	public InventoryReportPanel() 
	{
		setBounds(new Rectangle(42, 110, 517, 310));
		setLayout(null);
		
		String[] names = {"FuelID","Fuel Name","Quantity"};
		
		model = new MyTableModel(3,-1);
		
		for(String str : names)
		{
			model.addColumn(str);
		}
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setOpaque(false);
		scrollPane.setBounds(37, 58, 430, 213);
		add(scrollPane);
		
		table = new JTable(model);
		table.setModel(model);
		scrollPane.setViewportView(table);
	}
	
	/***
	 * Sets the table with details
	 * @param inventory - inventory report details
	 */
	public void setTable(ArrayList<FuelQty> inventory)
	{
		this.inventory = inventory;
		String str = "";
		
		for(FuelQty f : inventory)
		{
			switch(f.getFid())
			{
			case 1:
				str = "95";
				break;
			case 2:
				str = "Diesel";
				break;
			case 3:
				str = "Scooter";
				break;
			}
			model.insertRow(model.getRowCount(), new Object[] {f.getFid(),str,f.getQty()});
			
			
		}
	}
	
	
}
