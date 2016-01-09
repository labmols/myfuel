package myfuel.gui;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import myfuel.client.QuarterStationInventory;

/****
 * Company Inventory Report Panel
 * @author karmo
 *
 */
@SuppressWarnings("serial")
public class CinventoryPanel extends JPanel{
	/***
	 * Quarters that are specified in the report
	 */
	private JComboBox<String> quarter;
	/***
	 * Station that are shown in the Report
	 */
	private JComboBox<String> station;
	/***
	 * Table for showing report details
	 */
	private JTable table;
	/***
	 * The details of the Company inventory detailed by Station and Quarter
	 */
	private ArrayList<QuarterStationInventory > in;
	/***
	 * Table model
	 */
	private DefaultTableModel model;
	
	/***
	 * ComboBox Model
	 */
	private DefaultComboBoxModel<String> comboModel = new DefaultComboBoxModel<String>();
	
	/***
	 * All station names that will be presented in this panel
	 */
	ArrayList<String> station_names ;
	/***
	 * CinventoryPanel Constructor
	 */
	public CinventoryPanel() {
		setLayout(null);
		
		
		station_names = new ArrayList<String>();
		JLabel lblChooseStation = new JLabel("Choose Station:");
		lblChooseStation.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblChooseStation.setBounds(46, 4, 102, 19);
		add(lblChooseStation);
		
		station = new JComboBox<String>();
		station.addActionListener(new stationHandler());
		station.setModel(comboModel);
		station.setBounds(158, 5, 82, 20);
		add(station);
		
		JLabel lblQuarter = new JLabel("Quarter:");
		lblQuarter.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblQuarter.setBounds(272, 4, 55, 19);
		add(lblQuarter);
		
		quarter = new JComboBox<String>();
		quarter.addActionListener(new stationHandler());
		quarter.setModel(new DefaultComboBoxModel<String>(new String[] {"Q1", "Q2", "Q3", "Q4"}));
		quarter.setBounds(337, 5, 55, 20);
		add(quarter);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 58, 430, 213);
		add(scrollPane);
		model = new MyTableModel(3,-1);
		String[] names = {"Fuel ID","Fuel Name","Quantity"};
		
		for(String str : names)
			model.addColumn(str);
		
		table = new JTable(model);
		table.setModel(model);
		scrollPane.setViewportView(table);
	}
	
	/***
	 * ActionListener for station JCombobox
	 *
	 */
	private class stationHandler implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			clearTable();
			if(comboModel.getSize() != 0)
			{
				for(QuarterStationInventory i: in)
				{
					if(station.getSelectedItem().equals(i.getStation().getName()) && i.getQid() == quarter.getSelectedIndex()+1)
					{
						model.insertRow(model.getRowCount(),new Object[] {i.getFuel().getFid(),i.getFuel().getFname(),i.getFuel().getQty()});
					}
				}
			}
			
		}
		
	}
	/***
	 * This method will set the details of the report to this panel
	 * @param in - Company inventory detailed for stations and quarters
	 */
	public void setDetails(ArrayList<QuarterStationInventory> in)
	{
		try{
		this.in = in ;
	
		clearTable();
		
		for(QuarterStationInventory q : in)
		{
			if(!station_names.contains(q.getStation().getName()))
				station_names.add(q.getStation().getName());
		}
		
		comboModel.removeAllElements();
		
		for(String str : station_names)
		{
			comboModel.addElement(str);
		}
		
		} catch(Exception e )
		{
			e.printStackTrace();
		}
		
	}
	/***
	 * This method will clear the table
	 */
	private void  clearTable()
	{
		
		while(model.getRowCount() > 0)
			model.removeRow(0);
	}
}
