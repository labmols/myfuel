package myfuel.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import myfuel.GUIActions.GUIActions;
import myfuel.client.HomeOrder;
import myfuel.client.Purchase;

@SuppressWarnings("serial")
public class CustomerPurchaseGUI extends SuperGUI {
	private MyTableModel model;
	private JTable purchaseTable;
	
	public CustomerPurchaseGUI(GUIActions actions)
	{
		super(actions);
		setContentPane(contentPane);
		panel.setLocation(0, 0);
		lblTitle.setBounds(226, 6, 175, 31);
		lblTitle.setText("Purchase History");
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 96, 584, 279);
		model = new MyTableModel(6,-1);
		String[] names = {"#" ,"Car","Station","Fuel","Date","Amount(L)","Price(NIS)"};
		for(String s : names)
			model.addColumn(s);
		
		purchaseTable = new JTable(model);
		purchaseTable.setFont(new Font("Arial", Font.PLAIN, 12));
		purchaseTable.setModel(model);
		scrollPane.setViewportView(purchaseTable);
		panel.add(scrollPane);
		
	}
	@Override
	public void getInput(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void showPurchases(ArrayList<Purchase> pList) 
	{
			clearTable();
			String sdate="";
			for(Purchase p: pList)
			{
				Date date = p.getPdate();
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy HH:mm");
				sdate = format.format(date);
				model.insertRow(model.getRowCount(),new Object[]{p.getPid(),p.getCustomerCarID(),p.getSid(),p.getFuelid(),sdate,p.getQty(),p.getBill()});
			}
		}
	
	private void clearTable()
	{
		while(model.getRowCount() > 0 )
			model.removeRow(0);
	}

}
