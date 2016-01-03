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

import myfuel.client.quarterStationInventory;

@SuppressWarnings("serial")
public class CinventoryPanel extends JPanel{
	private JComboBox quarter;
	private JComboBox<String> station;
	private JTable table;
	private ArrayList<quarterStationInventory > in;
	private DefaultTableModel model;
	public CinventoryPanel() {
		setLayout(null);
		
		JLabel lblChooseStation = new JLabel("Choose Station:");
		lblChooseStation.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblChooseStation.setBounds(46, 4, 102, 19);
		add(lblChooseStation);
		
		station = new JComboBox<String>();
		station.addActionListener(new stationHandler());
		station.setBounds(158, 5, 82, 20);
		add(station);
		
		JLabel lblQuarter = new JLabel("Quarter:");
		lblQuarter.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblQuarter.setBounds(272, 4, 55, 19);
		add(lblQuarter);
		
		quarter = new JComboBox();
		quarter.addActionListener(new stationHandler());
		quarter.setModel(new DefaultComboBoxModel(new String[] {"Q1", "Q2", "Q3", "Q4"}));
		quarter.setBounds(337, 5, 55, 20);
		add(quarter);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 36, 430, 253);
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
			for(quarterStationInventory i: in)
			{
				if(station.getSelectedItem().equals(i.getStation().getName()) && i.getQid() == quarter.getSelectedIndex()+1)
				{
					model.insertRow(model.getRowCount(),new Object[] {i.getFuel().getFid(),i.getFuel().getFname(),i.getFuel().getQty()});
				}
			}
			
		}
		
	}
	
	public void setDetails(ArrayList<quarterStationInventory> in)
	{
		this.in = in;
		ArrayList<String> station_names = new ArrayList<String>();
	
		for(quarterStationInventory q : in)
		{
			if(!station_names.contains(q.getStation().getName()))
				station_names.add(q.getStation().getName());
		}
		
		for(String str : station_names)
		{
			this.station.addItem(str);
		}
		
	}
	
	private void  clearTable()
	{
		
		while(model.getRowCount() > 0)
			model.removeRow(0);
	}
}
