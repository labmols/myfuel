package myfuel.gui;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import myfuel.Entity.AnalyzeDetails;

/***
 * User Interface for showing the Sale Model Type with hours and  its Ratings
 * @author karmo
 *
 */
@SuppressWarnings("serial")
public class HourRatingPanel extends JPanel
{
	/***
	 * Sale Model Type with hours and  its Ratings
	 */
	private ArrayList <AnalyzeDetails> hType;
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
	 * HourRatingPanel Constructor
	 */
	public HourRatingPanel()
	{
		setOpaque(false);
		setLayout(null);
		String[] names = {"ID" , "Sale Model Name","Hours" , "Rating"};
		
		model = new MyTableModel(4,-1);
		for(String str : names)
			model.addColumn(str)
			;
		scrollPane = new JScrollPane();
		scrollPane.setOpaque(false);
		scrollPane.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setBounds(18, 34, 423, 240);
		add(scrollPane);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( SwingConstants.CENTER );
		
		table = new JTable();
		table.setModel(model);
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		table.getColumnModel().getColumn(1).setPreferredWidth(250);
		table.getColumnModel().getColumn(2).setPreferredWidth(200);
		scrollPane.setViewportView(table);
		
		for (int x = 0; x < model.getColumnCount(); x ++)
		{
	         table. getColumnModel (). getColumn (x). setCellRenderer (centerRenderer);
	     }
		
	}
	
	/***
	 * Clear The Rows at the table
	 */
	private void clearTable()
	{
		while(model.getRowCount() > 0)
			model.removeRow(0);
	}
	
/***
 * Saving the details about customer type fueling by Hour and rating
 * @param hType - Sale Model Type with hours and  its Ratings
 */
	public void sethType(ArrayList <AnalyzeDetails> hType)
	{
		this.hType = hType;
		clearTable();
		for(AnalyzeDetails a : hType )
			model.insertRow(model.getRowCount(), new Object[]{a.getSaleModel(),a.getSaleModelName(),a.getHour(),a.getRate()});
	}
}
