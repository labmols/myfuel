package myfuel.gui;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import myfuel.client.QuarterStationIncome;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;

/***
 * Company Incomes Panel
 *
 */
@SuppressWarnings("serial")
public class CIncomePanel extends IncomesReportPanel
{
	/***
	 * Incomes details for a quarter
	 */
	private ArrayList<QuarterStationIncome> qStationIncome = null;
	/***
	 * Station that are shown in the Report
	 */
	private JComboBox<String> stations;
	/***
	 * Quarters that are specified in the report
	 */
	private JComboBox<String> quarter;
	
	/***
	 * ComboBox Model
	 */
	private DefaultComboBoxModel<String> comboModel = new DefaultComboBoxModel<String>();
	
	/***
	 * CIncomePanel Constructor
	 */
	public CIncomePanel() {
		
		 stations = new JComboBox<String>();
		 stations.addActionListener(new comboHandler());
		 stations.setModel(comboModel);
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
		
		quarter = new JComboBox<String>();
		quarter.addActionListener(new comboHandler());
		quarter.setModel(new DefaultComboBoxModel<String>(new String[] {"Q1", "Q2", "Q3", "Q4"}));
		quarter.setBounds(373, 11, 88, 20);
		add(quarter);
	}
	/***
	 * Action Listener for the comboboxes
	 *
	 */
	private class comboHandler implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			int bill = 0 ;
			int quantity = 0 ;
			clearTable();
			if(comboModel.getSize() != 0)
			{
				for(QuarterStationIncome q : qStationIncome)
				{
					if(stations.getSelectedItem().equals(q.getStation().getName()) && q.getQid() == quarter.getSelectedIndex()+1)
					{
						bill+= q.getP().getBill();
						quantity += q.getP().getQty();
						model.insertRow(model.getRowCount(), new Object[] {q.getP().getCustomerid(),q.getP().getBill(),q.getP().getQty()});
					}
				}
			}
			 billLabel.setText(""+bill);
			 qtyLabel.setText(""+quantity);
		}
		
	}
	/***
	 * This method will set the details of the report to this panel
	 * @param qStationIncome - Company incomes detailed for stations and quarters
	 */
	public void setDetails(ArrayList<QuarterStationIncome> qStationIncome)
	{
		this.qStationIncome = qStationIncome;
		ArrayList<String> stations_names = new ArrayList<String>();
		clearTable();
		
		
		for(QuarterStationIncome q : qStationIncome)
		{
			if(!stations_names.contains(q.getStation().getName()))
				stations_names.add(q.getStation().getName());
		}
		
		comboModel.removeAllElements();
		
		for(String str : stations_names)
		{
			comboModel.addElement(str);
		}
	}
}
