package myfuel.gui;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import myfuel.Tools.TimeIgnoringComparator;
import myfuel.client.HomeOrder;
import myfuel.client.Promotion;
import myfuel.client.PromotionReport;
import myfuel.client.Purchase;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Panel that contains all the user interface for Tracking Home order.
 *
 */
@SuppressWarnings("serial")
public class TrackingOrderPanel extends JPanel{
	/**
	 * Table of all customer home orders.
	 */
	private JTable table;
	/**
	 * Table model.
	 */
	private DefaultTableModel model ; 
	
	/**
	 * Create new Tracking order panel.
	 */
	public TrackingOrderPanel() {
		setOpaque(false);
		setBounds(6, 46, 584, 384);
		setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 34, 572, 272);
		add(scrollPane);
		model = new MyTableModel(6,-1);
		String[] names = {"#" ,"Amount(L)","Price(NIS)","Address","Ship Date","U","Status"};
		for(String s : names)
			model.addColumn(s);
		
		table = new JTable(model);
		table.setFont(new Font("Calibri", Font.PLAIN, 13));
		table.setModel(model);
		table.getColumnModel().getColumn(0).setPreferredWidth(7);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(5).setPreferredWidth(20);
		//table.getColumnModel().getColumn(4).setPreferredWidth(20);
		scrollPane.setViewportView(table);
		
		JLabel lblYourOrders = new JLabel("Your Orders ");
		lblYourOrders.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 15));
		lblYourOrders.setBounds(232, 6, 120, 16);
		add(lblYourOrders);
		
		JLabel lbluIsUrgent = new JLabel("*U- Is Urgent Order");
		lbluIsUrgent.setFont(new Font("Arial", Font.PLAIN, 13));
		lbluIsUrgent.setBounds(6, 6, 133, 16);
		add(lbluIsUrgent);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( SwingConstants.CENTER );
		
		for (int x = 0; x < model.getColumnCount(); x ++)
		{
	         table. getColumnModel (). getColumn (x). setCellRenderer (centerRenderer);
	     }
		
	}

	/**
	 * Insert all the home orders to the table.
	 * @param horders - List of all the home orders.
	 */
public void updateTable(ArrayList <HomeOrder> horders)
{
	clearTable();
	String urgent;
	String status;
	if(horders != null)
	{
	for(HomeOrder order: horders)
	{
		Purchase p = order.getHomeP();
		Date date = order.getShipDate();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
		if(order.isUrgent()) urgent = "Yes";
		else urgent = "No";
		if(order.getStatus()) status = "Delivered";
		else status= "On delivery";
		model.insertRow(model.getRowCount(),new Object[]{order.getOrderid(), new DecimalFormat("##.##").format(order.getHomeP().getQty())+"L",new DecimalFormat("##.##").format(p.getBill()),order.getAddress(), format.format(date), urgent, status});
	}
	}
	else
	{
		JOptionPane.showMessageDialog(this, "You don't have any Home Order that have been recorded!","Message",JOptionPane.INFORMATION_MESSAGE);	
		

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
