package myfuel.gui;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import myfuel.client.quarterStationIncome;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;

@SuppressWarnings("serial")
public class CIncomePanel extends IncomesReportPanel
{
	private ArrayList<quarterStationIncome> qStationIncome = null;
	private JComboBox<String> stations;
	private JComboBox quarter;
	public CIncomePanel() {
		
		 stations = new JComboBox<String>();
		 stations.addActionListener(new comboHandler());
		 stations.setBounds(154, 11, 135, 20);
		add(stations);
		
		JLabel lblPickStation = new JLabel("Pick Station:");
		lblPickStation.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPickStation.setBounds(64, 4, 120, 31);
		add(lblPickStation);
		
		JLabel lblQuarter = new JLabel("Quarter:");
		lblQuarter.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblQuarter.setBounds(303, 6, 71, 31);
		add(lblQuarter);
		
		quarter = new JComboBox();
		quarter.addActionListener(new comboHandler());
		quarter.setModel(new DefaultComboBoxModel(new String[] {"Q1", "Q2", "Q3", "Q4"}));
		quarter.setBounds(373, 11, 88, 20);
		add(quarter);
	}
	
	private class comboHandler implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			int bill = 0 ;
			int quantity = 0 ;
			clearTable();
			for(quarterStationIncome q : qStationIncome)
			{
				if(stations.getSelectedItem().equals(q.getStation().getName()) && q.getQid() == quarter.getSelectedIndex()+1)
				{
					bill+= q.getP().getBill();
					quantity += q.getP().getQty();
					model.insertRow(model.getRowCount(), new Object[] {q.getP().getCustomerid(),q.getP().getBill(),q.getP().getQty()});
				}
			}
			 billLabel.setText(""+bill);
			 qtyLabel.setText(""+quantity);
		}
		
	}
	public void setDetails(ArrayList<quarterStationIncome> qStationIncome)
	{
		this.qStationIncome = qStationIncome;
		ArrayList<String> stations_names = new ArrayList<String>();
		
		for(quarterStationIncome q : qStationIncome)
		{
			if(!stations_names.contains(q.getStation().getName()))
				stations_names.add(q.getStation().getName());
		}
		
		for(String str : stations_names)
			stations.addItem(str);
	}
}
