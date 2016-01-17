package myfuel.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import myfuel.Entity.AnalyzeDetails;
import myfuel.GUIActions.MMAnalyzedActions;




import myfuel.Tools.BackMainMenu;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;

/***
 * User Interface for the marketing manager that shows the analystic system results due to date
 * @author karmo
 *
 */
@SuppressWarnings("serial")
public class MMAnalyzedGUI extends SuperGUI{
	
	/***
	 * Controller For this GUI
	 */
	private MMAnalyzedActions actions;
	/***
	 * Showing the available dates that has analyzed details
	 */
	private JComboBox<String> datesPick;
	/***
	 * Get date button
	 */
	private JButton  getBtn ;
	/***
	 * ArrayList That has the available dates that has analyzed details
	 */
	private ArrayList<Date> dates;
	/***
	 * Showing the  rating types
	 */
	private JComboBox <String>rating;
	
	/***
	 * Panel for Customer Type Ratings 
	 */
	private CustomerTypeRatingPanel customerPanel;
	/***
	 * Panel for fuel type ratings
	 */
	private FuelTypeRatingPanel fuelPanel;
	/***
	 * Panel for Customer Type Ratings per hour
	 */
	private HourRatingPanel hoursPanel;
	
	/***
	 * MMAnalyzedGUI Constructor
	 * @param actions  - this GUI controller.
	 */
	public MMAnalyzedGUI(MMAnalyzedActions actions)
	{
		super(actions);
		this.mainMenu.addActionListener(new BackMainMenu(actions));
		lblTitle.setBounds(230, 0, 168, 27);
		lblTitle.setText("Analyzed Details");
		
		this.actions = actions;
		this.setContentPane(contentPane);
		
		datesPick = new JComboBox<String>();
		datesPick.setBounds(193, 49, 135, 27);
		panel.add(datesPick);
		
		JLabel lblPickDate = new JLabel("Pick Date:");
		lblPickDate.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPickDate.setBounds(82, 51, 101, 27);
		panel.add(lblPickDate);
		
		 getBtn = new JButton("Get Details");
		 getBtn.addActionListener(new Handler());
		getBtn.setBounds(360, 49, 135, 27);
		panel.add(getBtn);
		
		rating = new JComboBox<String>();
		rating.addActionListener(new Handler());
		rating.setModel(new DefaultComboBoxModel<String>(new String[] {"Fuel Type Ratings", "Customer Type Ratings", "Customer Type(Hours)Ratings"}));
		rating.setBounds(203, 87, 208, 27);
		panel.add(rating);
		rating.setVisible(false);
		
		customerPanel = new CustomerTypeRatingPanel();
		customerPanel.setBounds(69, 87, 517, 310);
		panel.add(customerPanel);
		customerPanel.setVisible(false);
		
		fuelPanel = new FuelTypeRatingPanel();
		fuelPanel.setBounds(69, 87, 517, 310);
		panel.add(fuelPanel);
		fuelPanel.setVisible(false);
		
		hoursPanel = new HourRatingPanel();
		hoursPanel.setBounds(69, 87, 517, 310);
		panel.add(hoursPanel);
		hoursPanel.setVisible(false);
	}
	
	/***
	 * Updates the combobox with the dates of the analyzed details
	 * @param d -  dates of the analyzed details
	 */
	public void updateCombobox(ArrayList<Date> d)
	{
		this.dates = d;
		for(Date date : d)
			datesPick.addItem(date.toString());
	}

	@Override
	public void getInput(ActionEvent e) 
	{
		if(e.getSource() == getBtn)
		{
			actions.getDetails(dates.get(datesPick.getSelectedIndex()));
			rating.setVisible(true);
			fuelPanel.setVisible(true);
			
		}
		else if(e.getSource() == rating)
		{
			if(rating.getSelectedItem().equals("Fuel Type Ratings"))
			{
				fuelPanel.setVisible(true);
				customerPanel.setVisible(false);
				hoursPanel.setVisible(false);
			}
			
			else if(rating.getSelectedItem().equals("Customer Type Ratings"))
			{
				fuelPanel.setVisible(false);
				customerPanel.setVisible(true);
				hoursPanel.setVisible(false);
			}
			
			else if(rating.getSelectedItem().equals("Customer Type(Hours)Ratings"))
			{
				fuelPanel.setVisible(false);
				customerPanel.setVisible(false);
				hoursPanel.setVisible(true);
			}
		}
		
	}
	
	/***
	 * ActionListener for the Get Details Button
	 * @author karmo
	 *
	 */
	private class Handler implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			getInput(e);
			
		}
		
	}
	
	
	/***
	 * Will forward the sale model with its ratings to its panel
	 * @param cType - Sale models with their rating.
	 */
	public void updateCustomerPanel(ArrayList <AnalyzeDetails> cType)
	{
		customerPanel.setcType(cType);
	}
	
	/***
	 * Will forward the Fuel Types  with its ratings to its panel
	 * @param fType - Fuel Types with their rating.
	 */
	public void updateFuelsPanel(ArrayList <AnalyzeDetails> fType)
	{
		fuelPanel.setfType(fType);
	}
	
	/***
	 *  Will forward the Customer Types to hours  with its ratings to its panel
	 * @param hType  - Customer Types to hours  with its ratings
	 */
	public void updateHoursPanel(ArrayList <AnalyzeDetails> hType)
	{
		hoursPanel.sethType(hType);
	}
}
