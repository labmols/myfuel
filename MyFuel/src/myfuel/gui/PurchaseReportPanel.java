package myfuel.gui;

import javax.swing.JPanel;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import myfuel.client.Purchase;
import javax.swing.JLabel;
import java.awt.Font;

@SuppressWarnings("serial")
public class PurchaseReportPanel extends JPanel {
	private JTable table;
	protected JComboBox<?> fuelType;
	protected DefaultTableModel model;
	private ArrayList<Purchase> purchase;
	protected JLabel qtyLabel;
	protected JLabel billLabel;
	protected JLabel lblChooseFuelType;
	public PurchaseReportPanel() {
		setOpaque(false);
		setBounds(new Rectangle(42, 110, 517, 310));
		setLayout(null);
		purchase = null;
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 58, 430, 213);
		add(scrollPane);
		String[] names = {"Customer ID","BIll","Quantity"};
		model = new MyTableModel(3,-1);
		for(String str : names)
		{
			model.addColumn(str);
		}
		
		table = new JTable(model);
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		 fuelType = new JComboBox();
		 fuelType.addActionListener(new comboHandler());
		fuelType.setModel(new DefaultComboBoxModel(new String[] {"All","95", "Diesel", "Scooter"}));
		fuelType.setBounds(255, 27, 128, 20);
		add(fuelType);
		
		JLabel lblTotalBill = new JLabel("Total Bill :");
		lblTotalBill.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTotalBill.setBounds(70, 276, 90, 23);
		add(lblTotalBill);
		
		billLabel = new JLabel("0");
		billLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		billLabel.setBounds(150, 282, 95, 14);
		add(billLabel);
		
		JLabel lblTotalQuantity = new JLabel("Total Quantity:");
		lblTotalQuantity.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTotalQuantity.setBounds(279, 279, 115, 17);
		add(lblTotalQuantity);
		
		qtyLabel = new JLabel("0");
		qtyLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		qtyLabel.setBounds(404, 282, 90, 14);
		add(qtyLabel);
		
		lblChooseFuelType = new JLabel("Choose Fuel Type:");
		lblChooseFuelType.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblChooseFuelType.setBounds(95, 27, 167, 20);
		add(lblChooseFuelType);
	}
	
	protected class comboHandler implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			float bill = 0;
			float qty = 0;
			clearTable();
			if(fuelType.getSelectedIndex() == 0 )
				setTable(purchase);
			
			else
			{
				for(Purchase p : purchase )
				{
						if(fuelType.getSelectedIndex() == p.getFuelid())
						{
							qty += p.getQty();
							bill += p.getBill();
							model.insertRow(model.getRowCount(), new Object[]{p.getCustomerid(),p.getBill(),p.getQty()});
						}
				}
				
				billLabel.setText(""+bill); qtyLabel.setText(""+qty);
			}
			
		}
		
	}
	public void setTable(ArrayList<Purchase> purchase)
	{
		if(this.purchase == null)
			this.purchase = purchase;
		
		clearTable();
		float bill = 0;
		float qty = 0;
		
		for(Purchase p : purchase)
		{
			qty += p.getQty();
			bill += p.getBill();
			model.insertRow(model.getRowCount(), new Object[]{p.getCustomerid(),p.getBill(),p.getQty()});
		}
		billLabel.setText(""+bill); qtyLabel.setText(""+qty);
	}
	
	protected void  clearTable()
	{
		billLabel.setText("0"); qtyLabel.setText("0");
		while(model.getRowCount() > 0)
			model.removeRow(0);
	}
}
