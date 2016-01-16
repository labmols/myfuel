package myfuel.gui;


import java.awt.event.ActionEvent;











import myfuel.GUIActions.SetNewRatesActions;
import myfuel.client.BackMainMenu;
import myfuel.client.Fuel;
import myfuel.client.Network;
import myfuel.client.NetworkRates;
import myfuel.client.Rate;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.Font;

import javax.swing.JSeparator;

import java.awt.Color;

import javax.swing.SwingConstants;
import javax.swing.JComboBox;

/***
 * User interface for setting new rates 
 * @author karmo
 *
 */
@SuppressWarnings("serial")
public class SetNewRatesGUI extends SuperGUI {
	/***
	 * Controller for this GUI
	 */
	private SetNewRatesActions actions;
	/***
	 * Suggestion for one car rate
	 */
	private JTextField SMRoneCar;
	/***
	 * Suggestion for  few cars rate
	 */
	private JTextField SMRfewCar;
	/***
	 * Suggestion for FUlly Monthly one car rate
	 */
	private JTextField SFMoneCar;
	/***
	 * Current  one car rate
	 */
	private JLabel CMRoneCar;
	/***
	 * Current  few cars rate
	 */
	private JLabel CMRfewCar;
	/***
	 * Current FUlly Monthly one car rate
	 */
	private JLabel CFMoneCar;
	
	/***
	 * Old Rates Details for each Network
	 */
	private ArrayList<NetworkRates> oldRates;
	/***
	 * Network Details
	 */
	private ArrayList<Network> networks;
	/***
	 * WIll contain the Network Names
	 */
	private JComboBox<String>netName;
	/***
	 * Sending Button
	 */
	private JButton sendBtn;
	/***
	 * Delete Suggestion Button
	 */
	private JButton delBtn;
	/***
	 * Update BUtton
	 */
	private JButton updateBtn;
	/***
	 * If the input is legal or not 
	 */
	private boolean legal;
	/***
	 * Help to get the Network ID
	 */
	HashMap<Integer, Integer> networkID;
	/***
	 * Suggested Rates
	 */
	private ArrayList<NetworkRates> newRates;
	
	/***
	 * Rates 
	 */
	private ArrayList<Rate> r;
	/***
	 * JComboBox Model
	 */
	private DefaultComboBoxModel<String> networkModel;
	
	/**
	 * SetNewRatesGUI Constructor
	 */
	public SetNewRatesGUI(SetNewRatesActions actions) {
		super(actions);
		panel.setLocation(0, 0);
		this.actions = actions;
		lblTitle.setBounds(203, 11, 162, 16);
		lblTitle.setText("Set New Rates");
		this.mainMenu.addActionListener(new BackMainMenu(actions));
		JLabel lblNewLabel = new JLabel("Monthly Regular-one Car");
		lblNewLabel.setBounds(36, 155, 162, 14);
		panel.add(lblNewLabel);
		
		JLabel lblDiesel = new JLabel("Monthly Regular-Few Cars");
		lblDiesel.setBounds(36, 191, 172, 14);
		panel.add(lblDiesel);
		
		JLabel lblScooter = new JLabel("Fully Monthly-one Car");
		lblScooter.setBounds(36, 225, 125, 14);
		panel.add(lblScooter);
		
		SMRoneCar = new JTextField();
		SMRoneCar.setBounds(262, 159, 41, 20);
		panel.add(SMRoneCar);
		SMRoneCar.setColumns(10);
		
		SMRfewCar = new JTextField();
		SMRfewCar.setBounds(262, 195, 41, 20);
		panel.add(SMRfewCar);
		SMRfewCar.setColumns(10);
		
		SFMoneCar = new JTextField();
		SFMoneCar.setBounds(262, 229, 41, 20);
		panel.add(SFMoneCar);
		SFMoneCar.setColumns(10);
		
		CMRoneCar = new JLabel("0");
		CMRoneCar.setBounds(442, 155, 46, 14);
		panel.add(CMRoneCar);
		
		CMRfewCar = new JLabel("0");
		CMRfewCar.setToolTipText("0");
		CMRfewCar.setBounds(442, 191, 46, 14);
		panel.add(CMRfewCar);
		
		CFMoneCar = new JLabel("0");
		CFMoneCar.setBounds(442, 225, 46, 14);
		panel.add(CFMoneCar);
		
		networkID = new HashMap<Integer, Integer>();
		
		updateBtn = new JButton("Save Suggestion");
		updateBtn.setToolTipText("Press This Button For Saving This Network's Suggsetion");
		updateBtn.addActionListener(new Handler());
		updateBtn.setBounds(24, 315, 144, 54);
		panel.add(updateBtn);
		
		JLabel lblTypeOfFuel = new JLabel("Sale Model");
		lblTypeOfFuel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTypeOfFuel.setBounds(38, 117, 142, 23);
		panel.add(lblTypeOfFuel);
		
		JLabel lblSuggested = new JLabel("Suggested");
		lblSuggested.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSuggested.setBounds(242, 117, 104, 31);
		panel.add(lblSuggested);
		
		JLabel lblCurrent = new JLabel("Current");
		lblCurrent.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCurrent.setBounds(419, 118, 92, 21);
		panel.add(lblCurrent);
		
		JSeparator separator_8 = new JSeparator();
		separator_8.setOrientation(SwingConstants.VERTICAL);
		separator_8.setForeground(Color.BLACK);
		separator_8.setBackground(Color.BLACK);
		separator_8.setBounds(196, 117, 2, 180);
		panel.add(separator_8);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setForeground(Color.BLACK);
		separator.setBackground(Color.BLACK);
		separator.setBounds(381, 117, 16, 180);
		panel.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setForeground(Color.BLACK);
		separator_1.setBackground(Color.BLACK);
		separator_1.setBounds(509, 117, 2, 180);
		panel.add(separator_1);
		
		JLabel label = new JLabel("%");
		label.setBounds(471, 140, 46, 45);
		panel.add(label);
		
		JLabel label_1 = new JLabel("%");
		label_1.setBounds(309, 147, 46, 45);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("%");
		label_2.setBounds(309, 178, 46, 45);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("%");
		label_3.setBounds(309, 217, 46, 45);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("%");
		label_4.setBounds(470, 176, 46, 45);
		panel.add(label_4);
		
		JLabel label_5 = new JLabel("%");
		label_5.setBounds(470, 210, 46, 45);
		panel.add(label_5);
		
		JLabel lblChooseNetwork = new JLabel("Choose Network:");
		lblChooseNetwork.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblChooseNetwork.setBounds(102, 59, 155, 36);
		panel.add(lblChooseNetwork);
		
		networkModel = new DefaultComboBoxModel<String>();
		
		netName = new JComboBox<String>();
		netName.setModel(networkModel);
		netName.setBounds(260, 69, 125, 20);
		netName.addActionListener(new Handler());
		panel.add(netName);
		
		sendBtn = new JButton("Send For Confirmation");
		sendBtn.addActionListener(new Handler());
		sendBtn.setToolTipText("Press This Button For Sending The Rates To The Network Manager");
		sendBtn.setBounds(196, 315, 189, 54);
		panel.add(sendBtn);
		
		delBtn = new JButton("Delete Suggestion");
		delBtn.setToolTipText("Press This Button To Delete The Suggestion For This Network");
		delBtn.setBounds(409, 315, 144, 54);
		delBtn.addActionListener(new Handler());
		panel.add(delBtn);
		setContentPane(contentPane);
		r = new ArrayList<Rate>();
		newRates = new ArrayList<NetworkRates>();
	}
	
	/***
	 * This method updates the labels with the rates that retrieved from the DB for picked network
	 */
	private void update_labels()
	{
		for(Network n : networks)
		{
			if(n.getName().equals(netName.getSelectedItem()))
			{
				for(NetworkRates r : oldRates)
				{
					if(r.getNid() == n.getNid())
					{
						 CMRoneCar.setText(r.getRates().get(1).getDiscount()+"");
						 CMRfewCar.setText(r.getRates().get(2).getDiscount()+"");
						 CFMoneCar.setText(r.getRates().get(3).getDiscount()+"");
					}
				}
				
				for(NetworkRates net : newRates)
				{
					if(n.getNid() == net.getNid())
					{
						SMRoneCar.setText(net.getRates().get(1).getDiscount()+"");
						SMRfewCar.setText(net.getRates().get(2).getDiscount()+"");
						SFMoneCar.setText(net.getRates().get(3).getDiscount()+"");
					}
				}
			}
			
			
		}
		
		
		
		
	}
	
	/***
	 * Clears the JTextfield 
	 */
	private void clearTextFields()
	{
		SMRoneCar.setText("");
		SMRfewCar.setText("");
		SFMoneCar.setText("");
	}
	
	/***
	 * Save the rate in the ArrayList
	 */
	private void saveRate()
	{
		int nid = networkID.get(netName.getSelectedIndex());
		for(NetworkRates net : newRates)
		{
			if(nid == net.getNid())
			{
				this.showErrorMessage("You have already saved a suggestion for this network!");
				return;
			}
		}
		
		actions.verifyDetails(SMRoneCar.getText(), SMRfewCar.getText(), SFMoneCar.getText());
		
		if(legal)
		{
			
			r.add(new Rate(1,0));
			r.add(new Rate(2,Float.parseFloat(SMRoneCar.getText())));
			r.add(new Rate(3,Float.parseFloat(SMRfewCar.getText())));
			r.add(new Rate(4,Float.parseFloat(SFMoneCar.getText())));
			
			
			newRates.add(new NetworkRates(nid,new ArrayList<Rate>(r)));
			
			r.clear();
			this.showOKMessage("Suggstion for "+" "+netName.getSelectedItem()+" "+"has been saved \n hasn't been sent yet for confirmation");
		}
		
		else
		{
			this.showErrorMessage("Please enter legal values");
		}
		
		
			
	}
	
	/***
	 * Set the legal attribute for true or false
	 */
	public void setLegal(boolean answer)
	{
		legal = answer;
	}
 
	@Override
	public void getInput(ActionEvent e) 
	{
		if(e.getSource() == netName)
		{
			clearTextFields();
			update_labels();
		}
		
		else if(e.getSource() == updateBtn )
		{
			saveRate();
		}
		
		else if(e.getSource() == delBtn)
		{
			deleteSuggestion();
		}
		
		else if(e.getSource() == sendBtn)
		{
			if(newRates.isEmpty())
			{
				this.showErrorMessage("You can't send empty suggestion");
				return;
			}
			  if (JOptionPane.showConfirmDialog(null, "Are you sure want to send the suggsetion (You can't edit it once you press yes)?", "Confirmation", 
			            JOptionPane.YES_NO_OPTION,
			            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
			  {
				  
				  this.clearTextFields();
				  actions.sendSuggestion(newRates);
			  }
			            
		}
		
	}
	
	/***
	 * This method will delete a suggestion from the ArrayList
	 */
	private void deleteSuggestion() 
	{
		int nid = networkID.get(netName.getSelectedIndex());
		NetworkRates temp = null;
		
		for(NetworkRates net : newRates)
		{
			if(nid == net.getNid())
			{
				temp = net;
				break;
			}
		}
		
		if(temp != null)
		{
			newRates.remove(temp);
			this.showOKMessage("Suggstion for "+" "+netName.getSelectedItem()+" "+"has been deleted");
			this.clearTextFields();
		}
		
		else
		{
			this.showErrorMessage("You didn't set any suggestion for"+" "+netName.getSelectedItem());
		}
		
		
	}

	/***
	 * This class will handle Actions Events 
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
	 * This method will update the user interface with the details
	 * @param oldRates - Current Rates for the networks
	 * @param networks - networks Details
	 * 
	 */
	public void SetNewDetails(ArrayList<NetworkRates> oldRates, ArrayList<Network> networks) 
	{
		this.oldRates = oldRates;
		this.networks = networks;
		
		for(Network n : networks)
		{
			networkID.put(networkModel.getSize(), n.getNid());
			networkModel.addElement(n.getName());
			
		}
		
	}
}
