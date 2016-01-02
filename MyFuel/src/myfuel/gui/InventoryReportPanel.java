package myfuel.gui;

import javax.swing.JPanel;

import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import myfuel.client.FuelQty;

@SuppressWarnings("serial")
public class InventoryReportPanel extends JPanel 
{
	private JTable table;
	private DefaultTableModel model;
	private ArrayList<FuelQty> inventory;
	
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
		scrollPane.setBounds(32, 29, 436, 242);
		add(scrollPane);
		
		table = new JTable(model);
		table.setModel(model);
		scrollPane.setViewportView(table);
	}
	
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
