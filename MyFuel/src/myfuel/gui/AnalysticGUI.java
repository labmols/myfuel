package myfuel.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import myfuel.GUIActions.AnalysticActions;
import myfuel.client.AnalyzeDetails;
import myfuel.client.BackMainMenu;

import javax.swing.JComboBox;
import javax.swing.JLabel;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;

/***
 * Analytic System User Interface
 * @author karmo
 *
 */
@SuppressWarnings("serial")
public class AnalysticGUI extends SuperGUI{
	/***
	 * Controller for this GUI
	 */
	private AnalysticActions actions;
	
	/***
	 * ComboBox to pick type of rating
	 */
	private JComboBox<String> rating;
	
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
	 * Save to DataBase Button
	 */
	private JButton saveBtn;
	
	/***
	 * AnalysticGUI Constructor
	 * @param actions - Controller for this GUI
	 */
	public AnalysticGUI(AnalysticActions actions)
	{
		super(actions);
		lblTitle.setBounds(215, 6, 176, 28);
		lblTitle.setText("Analystic System");
		this.actions = actions;
		this.setContentPane(contentPane);
		
		rating = new JComboBox<String>();
		rating.addActionListener(new Handler());
		rating.setModel(new DefaultComboBoxModel<String>(new String[] {"Fuel Type Ratings", "Customer Type Ratings", "Customer Type(Hours)Ratings"}));
		rating.setBounds(215, 45, 208, 31);
		panel.add(rating);
		
		JLabel lblPickCategory = new JLabel("Pick Category:");
		lblPickCategory.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPickCategory.setBounds(69, 45, 132, 28);
		panel.add(lblPickCategory);
		this.mainMenu.addActionListener(new BackMainMenu(actions));
		
		customerPanel = new CustomerTypeRatingPanel();
		customerPanel.setBounds(69, 87, 517, 310);
		panel.add(customerPanel);
		customerPanel.setVisible(false);
		
		fuelPanel = new FuelTypeRatingPanel();
		fuelPanel.setBounds(69, 87, 517, 310);
		panel.add(fuelPanel);
		fuelPanel.setVisible(true);
		
		hoursPanel = new HourRatingPanel();
		hoursPanel.setBounds(69, 87, 517, 310);
		panel.add(hoursPanel);
		
		saveBtn = new JButton("Save Details");
		saveBtn.addActionListener(new Handler());
		saveBtn.setBounds(442, 45, 124, 33);
		panel.add(saveBtn);
		hoursPanel.setVisible(false);
		
	}
	
	
	private class Handler implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
				getInput(e);
			
		}
		
	}
	@Override
	public void getInput(ActionEvent e) 
	{
		if(e.getSource() == rating)
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
		
		
		else if(e.getSource() == saveBtn)
		{
			actions.saveToDB();
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
	
	public void updateHoursPanel(ArrayList <AnalyzeDetails> hType)
	{
		hoursPanel.sethType(hType);
	}
}
