package myfuel.gui;

import java.util.ArrayList;

import javax.swing.JPanel;

import myfuel.client.Purchase;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import java.awt.Rectangle;

@SuppressWarnings("serial")
public class IncomesReportPanel extends JPanel
{
	private ArrayList<Purchase> incomes;
	private JTable table;
	protected DefaultTableModel model;
	protected JLabel billLabel;
	protected JLabel qtyLabel;
	
	public IncomesReportPanel()
	{
		setOpaque(false);
		setBounds(new Rectangle(42, 110, 517, 310));
		setLayout(null);
		model = new MyTableModel(3,-1);
		String[] names = {"Customer ID" , "Bill" , "Quantity"};
		
		for(String str : names)
		{
			model.addColumn(str);
		}
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 61, 464, 212);
		add(scrollPane);
		
		table = new JTable(model);
		scrollPane.setViewportView(table);
		table.setModel(model);
		
		JLabel lblTotalBills = new JLabel("Total Bills:");
		lblTotalBills.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTotalBills.setBounds(63, 31, 93, 22);
		add(lblTotalBills);
		
		JLabel lblNewLabel = new JLabel("Total Quantity:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(260, 34, 121, 16);
		add(lblNewLabel);
		
		qtyLabel = new JLabel("0");
		qtyLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		qtyLabel.setBounds(379, 36, 66, 14);
		add(qtyLabel);
		
		billLabel = new JLabel("0");
		billLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		billLabel.setBounds(145, 36, 75, 14);
		add(billLabel);
		
	}
	protected void  clearTable()
	{
		billLabel.setText("0"); qtyLabel.setText("0");
		while(model.getRowCount() > 0)
			model.removeRow(0);
	}
	
	public void setTable(ArrayList<Purchase> incomes)
	{
		
			this.incomes = incomes;
			clearTable();
		
		float bill = 0;
		float qty = 0;
		
		for(Purchase p : incomes)
		{
			qty += p.getQty();
			bill += p.getBill();
			model.insertRow(model.getRowCount(), new Object[]{p.getCustomerid(),p.getBill(),p.getQty()});
		}
		billLabel.setText(""+bill); qtyLabel.setText(""+qty);
	}
	

}
