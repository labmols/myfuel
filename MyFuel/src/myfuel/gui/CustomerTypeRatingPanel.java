package myfuel.gui;

import java.util.ArrayList;

import javax.swing.JPanel;

import myfuel.client.AnalyzeDetails;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;

/***
 * User Interface for showing the Ratings for each Customer type
 * @author karmo
 *
 */
@SuppressWarnings("serial")
public class CustomerTypeRatingPanel extends JPanel
{
	/***
	 * Customer Type with its Ratings
	 */
	private ArrayList <AnalyzeDetails> cType;
	/***
	 * Table For Showing The ratings
	 */
	private JTable table;
	/***
	 * Table Model
	 */
	private DefaultTableModel model;
	/***
	 * Scrollpane for Scrolling the table
	 */
	private JScrollPane scrollPane;
	
	/***
	 * CustomerTypePanel Constructor
	 */
	public CustomerTypeRatingPanel()
	{
		setOpaque(false);
		setLayout(null);
		String[] names = {"Type ID" , "Customer Type" , "Rate"};
		
		model = new MyTableModel(3,-1);
		for(String str : names)
			model.addColumn(str)
			;
		scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setOpaque(false);
		scrollPane.setBounds(18, 34, 423, 240);
		add(scrollPane);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( SwingConstants.CENTER );
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		
		for (int x = 0; x < model.getColumnCount(); x ++)
		{
	         table. getColumnModel (). getColumn (x). setCellRenderer (centerRenderer);
	     }
		
	}

	/***
	 * Delete all rows at the table
	 */
	private void clearTable()
	{
		while(model.getRowCount() > 0)
			model.removeRow(0);
	}
	
	/***
	 * Saves the arrayList with the customer type and rating and insert it into the table
	 * @param cType - arrayList with the customer type and rating
	 */
	public void setcType(ArrayList <AnalyzeDetails> cType)
	{
		clearTable();
		this.cType = cType;
		
		for(AnalyzeDetails a : cType )
			model.insertRow(model.getRowCount(), new Object[]{a.getSaleModel(),a.getSaleModelName(),a.getRate()});
	}
}
