package myfuel.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import myfuel.client.CustomerReport;
import myfuel.client.Station;

import javax.swing.JTable;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class CustomerCReportPanel extends JPanel {
	private JComboBox<String> station;
	private ArrayList<Station> stations ;
	private ArrayList<CustomerReport> creport;
	private DefaultTableModel model;
	private JTable table;
	private JScrollPane scrollPane;
	
	public CustomerCReportPanel() {
		setOpaque(false);
		setLayout(null);
		
		JLabel lblChooseStation = new JLabel("Choose Station:");
		lblChooseStation.setBounds(118, 32, 115, 14);
		add(lblChooseStation);
		
		station = new JComboBox<String>();
		station.addActionListener(new comboHandler());
		station.setBounds(267, 29, 146, 20);
		add(station); 
		String[] names = {"ID","Name","Total Bills","Total Quantity Bought","No. Purchases"};
		model = new MyTableModel(5,-1);
		
		for(String s: names)
			model.addColumn(s);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( SwingConstants.CENTER );
		
	
	
		scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 75, 481, 182);
		add(scrollPane);
		
		table = new JTable(model);
		scrollPane.setViewportView(table);
		table.setModel(model);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(55);
		table.getColumnModel().getColumn(1).setPreferredWidth(80);
		table.getColumnModel().getColumn(2).setPreferredWidth(80);
		table.getColumnModel().getColumn(3).setPreferredWidth(130);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		
		for (int x = 0; x < model.getColumnCount(); x ++)
		{
	         table. getColumnModel (). getColumn (x). setCellRenderer (centerRenderer);
	     }
	}
	
	
	
	public void setElements(ArrayList<Station>stations ,ArrayList<CustomerReport>creport)
	{
		this.stations = stations;
		this.creport = creport;
		for(Station s : stations)
		{
			station.addItem(s.getName());
		}
	}
	
	private void  clearTable()
	{
		while(model.getRowCount() > 0)
			model.removeRow(0);
	}
	
	private class comboHandler implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			int index = station.getSelectedIndex();
			clearTable();
			for(CustomerReport c : creport)
			{
				
				if(stations.get(index).getsid() == c.getSid())
					model.insertRow(model.getRowCount(), new Object[] {c.getUid(),c.getName(),new DecimalFormat("##.##").format(c.getPrice())
						,c.getQty()+"  Liter",c.getTimes()});
			}
			
		}
		
	}
}
