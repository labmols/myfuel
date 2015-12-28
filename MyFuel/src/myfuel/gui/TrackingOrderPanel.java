package myfuel.gui;

import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

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
		scrollPane.setBounds(25, 122, 540, 188);
		add(scrollPane);
		model = new MyTableModel(4,-1);
		String[] names = {"	Order ID" ,"Quantity","Ship Date","Urgent","Status"};
		
		for(String s : names)
			model.addColumn(s);
		
		table = new JTable(model);
		table.setFont(new Font("Calibri", Font.PLAIN, 13));
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		
	}

	

	
	/***
	 * Clears the table
	 */
 void clearTable()
{
	while(model.getRowCount() > 0 )
		model.removeRow(0);
}
 
 
}
