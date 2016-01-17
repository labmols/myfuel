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
 * User Interface for showing the Ratings for each fuel type
 * @author karmo
 *
 */
@SuppressWarnings("serial")
public class FuelTypeRatingPanel extends JPanel
{
	/***
	 * Fuel Type with its Ratings
	 */
	private ArrayList <AnalyzeDetails> fType;
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
	 * FuelTypeRatingPanel Constructor
	 */
	public FuelTypeRatingPanel()
	{
		setOpaque(false);
		setLayout(null);
		String[] names = {"Fuel Type ID" , "Fuel Type Name" , "Rating"};
		
		model = new MyTableModel(3,-1);
		for(String str : names)
			model.addColumn(str)
			;
		scrollPane = new JScrollPane();
		scrollPane.setOpaque(false);
		scrollPane.setBounds(18, 34, 423, 240);
		scrollPane.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(scrollPane);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( SwingConstants.CENTER );
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		table.setModel(model);
		scrollPane.setViewportView(table);
		
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
	 * Saves the arrayList with the fuel type and rating and insert it into the table
	 * @param fType - arrayList with the fuel type and rating
	 */
	public void setfType(ArrayList <AnalyzeDetails> fType)
	{
		clearTable();
		this.fType = fType;
		
		for(AnalyzeDetails a : fType )
			model.insertRow(model.getRowCount(), new Object[]{a.getFuelId(),a.getFuelName(),a.getRate()});
	}
}
