package myfuel.gui;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import myfuel.client.QuarterStationPurchase;
import myfuel.client.QuarterStationIncome;

@SuppressWarnings("serial")
public class CPurchasePanel extends PurchaseReportPanel
{
	private ArrayList<QuarterStationPurchase> qStationPurchase;
	private JComboBox<String> stations;
	private JComboBox<String> quarter;
	public CPurchasePanel() {
		
		JLabel lblPickStation = new JLabel("Pick Station:");
		lblPickStation.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPickStation.setBounds(29, 0, 116, 29);
		add(lblPickStation);
		
		stations = new JComboBox<String>();
		stations.addActionListener(new comboBoxHandler());
		stations.setBounds(119, 6, 127, 20);
		add(stations);
		
		JLabel lblQuarter = new JLabel("Quarter:");
		lblQuarter.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblQuarter.setBounds(315, -5, 107, 38);
		add(lblQuarter);
		
		quarter = new JComboBox<String>();
		quarter.addActionListener(new comboBoxHandler());
		quarter.setModel(new DefaultComboBoxModel<String>(new String[] {"Q1", "Q2", "Q3", "Q4"}));
		quarter.setBounds(388, 6, 78, 20);
		add(quarter);
		
	
		fuelType.removeActionListener(fuelType.getActionListeners()[0]);
		fuelType.addActionListener(new comboHandler2());
		
		
	}
	
	private  class comboHandler2 implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			if(fuelType.getSelectedIndex() == 0)
				show_all();
			else
			{
				float bill = 0 ;
				float quantity = 0 ;
				clearTable();
				for(QuarterStationPurchase q : qStationPurchase)
				{
					
					if(stations.getSelectedItem().equals(q.getQ().getStation().getName()) && q.getQ().getQid() == quarter.getSelectedIndex()+1
									&& fuelType.getSelectedIndex() == q.getQ().getP().getFuelid())
					{
						bill+= q.getQ().getP().getBill();
						quantity += q.getQ().getP().getQty();
						model.insertRow(model.getRowCount(), new Object[] {q.getQ().getP().getCustomerid(),q.getQ().getP().getBill(),q.getQ().getP().getQty()});
					}
				}
				 billLabel.setText(""+bill);
				 qtyLabel.setText(""+quantity);
			}
			
		}
		
	}
	private class comboBoxHandler implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			float bill = 0 ;
			float quantity = 0 ;
			clearTable();
			for(QuarterStationPurchase q : qStationPurchase)
			{
				if(stations.getSelectedItem().equals(q.getQ().getStation().getName()) && q.getQ().getQid() == quarter.getSelectedIndex()+1)
				{
					bill+= q.getQ().getP().getBill();
					quantity += q.getQ().getP().getQty();
					model.insertRow(model.getRowCount(), new Object[] {q.getQ().getP().getCustomerid(),q.getQ().getP().getBill(),q.getQ().getP().getQty()});
				}
			}
			 billLabel.setText(""+bill);
			 qtyLabel.setText(""+quantity);
		}
		
	}
	
	private void show_all()
	{
		int bill = 0 ;
		int quantity = 0 ;
		clearTable();
		for(QuarterStationPurchase q : qStationPurchase)
		{
			if(stations.getSelectedItem().equals(q.getQ().getStation().getName()) && q.getQ().getQid() == quarter.getSelectedIndex()+1)
			{
				bill+= q.getQ().getP().getBill();
				quantity += q.getQ().getP().getQty();
				model.insertRow(model.getRowCount(), new Object[] {q.getQ().getP().getCustomerid(),q.getQ().getP().getBill(),q.getQ().getP().getQty()});
			}
		}
		 billLabel.setText(""+bill);
		 qtyLabel.setText(""+quantity);
	}
	
	public void setDetails( ArrayList<QuarterStationPurchase> qStationPurchase)
	{
		this.qStationPurchase = qStationPurchase;
		ArrayList<String> stations_names = new ArrayList<String>();
		
		for(QuarterStationPurchase q : qStationPurchase)
		{
			if(!stations_names.contains(q.getQ().getStation().getName()))
				stations_names.add(q.getQ().getStation().getName());
		}
		
		for(String str : stations_names)
			stations.addItem(str);
	}

}
