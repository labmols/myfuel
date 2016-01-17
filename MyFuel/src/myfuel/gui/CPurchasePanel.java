package myfuel.gui;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import myfuel.client.QuarterStationPurchase;
import myfuel.client.QuarterStationIncome;

/***
 * User Interface For showing the Purchase Report
 * @author karmo
 *
 */
@SuppressWarnings("serial")
public class CPurchasePanel extends PurchaseReportPanel
{
	/***
	 * Purchase Details per QUarter
	 */
	private ArrayList<QuarterStationPurchase> qStationPurchase;
	/***
	 * Contains the Stations that mentioned in the Report
	 */
	private JComboBox<String> stations;
	/***
	 * Contains all quarters
	 */
	private JComboBox<String> quarter;
	
	/***
	 * ComboBox Model
	 */
	private DefaultComboBoxModel<String> comboModel = new DefaultComboBoxModel<String>();
	
	/***
	 * CPurchasePanel Constructor
	 */
	public CPurchasePanel() {
		billLabel.setBounds(80, 282, 128, 14);
		fuelType.setSize(128, 29);
		lblChooseFuelType.setFont(new Font("Tahoma", Font.PLAIN, 13));
		fuelType.setLocation(366, 5);
		lblChooseFuelType.setLocation(253, 4);
		
		JLabel lblPickStation = new JLabel("Pick Station:");
		lblPickStation.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPickStation.setBounds(29, 0, 116, 29);
		add(lblPickStation);
		
		stations = new JComboBox<String>();
		stations.addActionListener(new comboBoxHandler());
		stations.setBounds(103, 1, 127, 29);
		stations.setModel(comboModel);
		add(stations);
		
		JLabel lblQuarter = new JLabel("Quarter:");
		lblQuarter.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblQuarter.setBounds(231, 22, 107, 38);
		add(lblQuarter);
		
		quarter = new JComboBox<String>();
		quarter.addActionListener(new comboBoxHandler());
		quarter.setModel(new DefaultComboBoxModel<String>(new String[] {"Q1", "Q2", "Q3", "Q4"}));
		quarter.setBounds(299, 30, 39, 25);
		add(quarter);
		
	
		fuelType.removeActionListener(fuelType.getActionListeners()[0]);
		fuelType.addActionListener(new comboHandler2());
		
		
	}
	
	/***
	 * ActionListener for Fuel Type ComboBox
	 * @author karmo
	 *
	 */
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
				if(comboModel.getSize() != 0)
				{
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
				}
				String msg = new DecimalFormat("##.##").format(bill) + "(NIS)";
				 billLabel.setText(""+msg);
				 msg = quantity+"(LITER)";
				 qtyLabel.setText(""+msg);
			}
			
		}
		
	}
	
	/***
	 * Action Listener for Quarter and Stations COmboBox
	 * @author karmo
	 *
	 */
	private class comboBoxHandler implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			float bill = 0 ;
			float quantity = 0 ;
			clearTable();
			if(comboModel.getSize() != 0)
			{
				for(QuarterStationPurchase q : qStationPurchase)
				{
					
						if(stations.getSelectedItem().equals(q.getQ().getStation().getName()) && q.getQ().getQid() == quarter.getSelectedIndex()+1)
						{
							bill+= q.getQ().getP().getBill();
							quantity += q.getQ().getP().getQty();
							model.insertRow(model.getRowCount(), new Object[] {q.getQ().getP().getCustomerid(),q.getQ().getP().getBill(),q.getQ().getP().getQty()});
						}
				}
			}
			String msg = new DecimalFormat("##.##").format(bill) + "(NIS)";
			 billLabel.setText(""+msg);
			 msg = quantity+"(LITER)";
			 qtyLabel.setText(""+msg);
		}
		
	}
	
	/***
	 * This method will fill the Table with the fully report details (Without Difference of the Fuel Types)
	 */
	private void show_all()
	{
		int bill = 0 ;
		int quantity = 0 ;
		clearTable();
		if(comboModel.getSize() != 0)
		{
			for(QuarterStationPurchase q : qStationPurchase)
			{
				if(stations.getSelectedItem().equals(q.getQ().getStation().getName()) && q.getQ().getQid() == quarter.getSelectedIndex()+1)
				{
					bill+= q.getQ().getP().getBill();
					quantity += q.getQ().getP().getQty();
					model.insertRow(model.getRowCount(), new Object[] {q.getQ().getP().getCustomerid(),q.getQ().getP().getBill(),q.getQ().getP().getQty()});
				}
			}
		}
		String msg = new DecimalFormat("##.##").format(bill) + "(NIS)";
		 billLabel.setText(""+msg);
		 msg = quantity+"(LITER)";
		 qtyLabel.setText(""+msg);
	}
	
	/***
	 * This method will update all elements in this panel that linked to the Report Presentation
	 * @param qStationPurchase - Purchase Report Details 
	 */
	public void setDetails( ArrayList<QuarterStationPurchase> qStationPurchase)
	{
		this.qStationPurchase = qStationPurchase;
		ArrayList<String> stations_names = new ArrayList<String>();
		clearTable();
		
		for(QuarterStationPurchase q : qStationPurchase)
		{
			if(!stations_names.contains(q.getQ().getStation().getName()))
				stations_names.add(q.getQ().getStation().getName());
		}
		
		comboModel.removeAllElements();
		
		for(String str : stations_names)
			comboModel.addElement(str);
	}
}
