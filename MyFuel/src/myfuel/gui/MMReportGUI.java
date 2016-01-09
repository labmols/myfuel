package myfuel.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import myfuel.GUIActions.MMReportsActions;
import myfuel.client.BackMainMenu;
import myfuel.client.CustomerReport;
import myfuel.client.Promotion;
import myfuel.client.PromotionReport;
import myfuel.client.Station;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;

/***
 * User Interface For the Marketing Manager Reports
 * @author karmo
 *
 */
@SuppressWarnings("serial")
public class MMReportGUI extends SuperGUI{
	/***
	 * Promotion Report Panel
	 */
	private PromtionRPanel reportPanel;
	/***
	 * Customer Report Panel
	 */
	private CustomerCReportPanel customerPanel;
	/***
	 * Controller for this GUI
	 */
	private MMReportsActions actions;
	/***
	 * ComboBOx For Type of Report
	 */
	private JComboBox<String> comboBox;
	/***
	 * MMReportGUI COnstructor
	 * @param actions - COntroller For This GUI
	 */
	public MMReportGUI(MMReportsActions actions)
	{
		lblTitle.setBounds(271, 6, 125, 25);
		lblTitle.setText("Reports\r\n");
		this.mainMenu.addActionListener(new BackMainMenu(actions));
		comboBox = new JComboBox<String>();
		comboBox.addActionListener(new ComboHandler());
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Promotions Reports", "Customer Characterization"}));
		comboBox.setBounds(193, 64, 191, 20);
		panel.add(comboBox);
		
		reportPanel = new PromtionRPanel();
		reportPanel.setBounds(10, 90, 576, 297);
		panel.add(reportPanel);
		
		customerPanel = new CustomerCReportPanel();
		customerPanel.setBounds(10, 90, 576, 297);
		panel.add(customerPanel);
		customerPanel.setVisible(false);
		
		this.actions = actions;
		this.setContentPane(contentPane);
	}
	
	
	/***
	 * Action Listener 
	 * @author karmo
	 *
	 */
	private class ComboHandler implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if(comboBox.getSelectedIndex() == 0) // promotion reports
			{
				reportPanel.setVisible(true);
				customerPanel.setVisible(false);
			}
			
			else  // customer reports
			{
				reportPanel.setVisible(false);
				customerPanel.setVisible(true);
			}
			
			
			
		}
		
	}
	/***
	 * This method will set the reports panels 
	 * @param pr - Promotion Reports Details
	 * @param names - Name of The Promotions
	 * @param stations - Station Details
	 * @param creport - Customer Report Details
	 */
	public void setReports(ArrayList<PromotionReport> pr,ArrayList<Promotion> names ,ArrayList<Station> stations,ArrayList<CustomerReport> creport)
	{
		
		 reportPanel.setCombo(pr,names);
		 customerPanel.setElements(stations,creport);
		
	}
	
	
	@Override
	public void getInput(ActionEvent e) 
	{
		
		
	}
	
	

}
