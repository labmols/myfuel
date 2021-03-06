package myfuel.GUI;

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

import myfuel.Entity.CustomerReport;
import myfuel.Entity.Station;

import javax.swing.JTable;
import javax.swing.JScrollPane;

/***
 * This user interface will show the details for Customer Report
 * @author karmo
 *
 */
@SuppressWarnings("serial")
public class CustomerCReportPanel extends JPanel {
	/***
	 * ComboBox with Station Names
	 */
	private JComboBox<String> station;
	/***
	 * Stations Details
	 */
	private ArrayList<Station> stations ;
	/***
	 * Customer Report Details
	 */
	private ArrayList<CustomerReport> creport;
	/***
	 * JTable Model
	 */
	private DefaultTableModel model;
	/***
	 * Table for showing report details
	 */
	private JTable table;
	/***
	 * JScrollPane for the table
	 */
	private JScrollPane scrollPane;
	/***
	 * CustomerCReportPanel Constructor
	 */
	public CustomerCReportPanel() {
		setOpaque(false);
		setLayout(null);
		
		JLabel lblChooseStation = new JLabel("Choose Station:");
		lblChooseStation.setBounds(118, 32, 115, 14);
		add(lblChooseStation);
		
		station = new JComboBox<String>();
		station.addActionListener(new comboHandler());
		station.setBounds(267, 21, 146, 28);
		add(station); 
		String[] names = {"ID","Name","Total Bills","Total Bought","Total"};
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
		
		table.getColumnModel().getColumn(0).setPreferredWidth(150);
		for(int i = 1 ; i < 4 ; i++)
			table.getColumnModel().getColumn(i).setPreferredWidth(150);
		
		for (int x = 0; x < model.getColumnCount(); x ++)
		{
	         table. getColumnModel (). getColumn (x). setCellRenderer (centerRenderer);
	     }
	}
	
	
	/***
	 * This method will set the elements of this Panel 
	 * @param stations - Station Details
	 * @param creport - Customer Report Details
	 */
	public void setElements(ArrayList<Station>stations ,ArrayList<CustomerReport>creport)
	{
		this.stations = stations;
		this.creport = creport;
		for(Station s : stations)
		{
			station.addItem(s.getName());
		}
	}
	
	/***
	 * This method  clears  JTable
	 */
	private void  clearTable()
	{
		while(model.getRowCount() > 0)
			model.removeRow(0);
	}
	
	/***
	 * ActionListener for Station ComboBox
	 * @author karmo
	 *
	 */
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
						,new DecimalFormat("##.##").format(c.getQty())+"  Liter",c.getTimes()});
			}
			
		}
		
	}
}
