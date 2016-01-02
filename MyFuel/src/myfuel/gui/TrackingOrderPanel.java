package myfuel.gui;

import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import myfuel.client.HomeOrder;
import myfuel.client.Promotion;
import myfuel.client.PromotionReport;
import myfuel.client.TimeIgnoringComparator;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@SuppressWarnings("serial")
public class TrackingOrderPanel extends JPanel{
	private JTable table;
	private DefaultTableModel model ; 
	/***
	 * Constructor for Promotion Report Panel
	 */
	public TrackingOrderPanel() {
		setOpaque(false);
		setBounds(6, 46, 584, 384);
		setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 34, 572, 279);
		add(scrollPane);
		model = new MyTableModel(6,-1);
		String[] names = {"Order ID" ,"Amount(L)","Ship Date","Order Date","Urgent","Status"};
		for(String s : names)
			model.addColumn(s);
		
		table = new JTable(model);
		table.setFont(new Font("Calibri", Font.PLAIN, 13));
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		JLabel lblYourOrders = new JLabel("Your Orders ");
		lblYourOrders.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 15));
		lblYourOrders.setBounds(232, 6, 120, 16);
		add(lblYourOrders);
		
	}

public void updateTable(ArrayList <HomeOrder> horders)
{
	clearTable();
	String urgent;
	String status;
	for(HomeOrder order: horders)
	{
		Date date = order.getShipDate();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("HH:mm");
		if(order.isUrgent()) urgent = "Yes";
		else urgent = "No";
		if(order.getStatus()) status = "Delivered";
		else status= "On delivery";
		model.insertRow(model.getRowCount(),new Object[]{order.getOrderid(), order.getQty()+"L", format.format(date),format2.format(date), urgent, status});
	}
}

	
	/***
	 * Clears the table
	 */
 private void clearTable()
{
	while(model.getRowCount() > 0 )
		model.removeRow(0);
}
}
