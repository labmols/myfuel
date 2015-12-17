package myfuel.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import myfuel.GUIActions.*;
import myfuel.client.BackMainMenu;
import myfuel.client.Fuel;

import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JSeparator;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class ConfirmNewRatesGUI extends SuperGUI{

	private ConfirmNewRatesActions actions;
	private JButton btnConfirm ;
	private JButton btnDeny;
	
	private JLabel sug_95;
	private JLabel sug_diesel;
	private JLabel sug_scooter;
	private JLabel sug_home;
	
	private JLabel cur_95;
	private JLabel cur_diesel;
	private JLabel cur_scooter;
	private JLabel cur_home;
	
	private JLabel max_95;
	private JLabel max_diesel;
	private JLabel max_scooter;
	private JLabel max_home;
	
	private ArrayList<Fuel>fuels;
	
	public ConfirmNewRatesGUI(ConfirmNewRatesActions actions)
	{
		lblTitle.setBounds(211, 0, 176, 25);
		lblTitle.setText("Confirm New Rates");
		this.mainMenu.addActionListener(new BackMainMenu(actions));
		 btnConfirm = new JButton("Confirm");
		 btnConfirm.addActionListener(new btnHandler());
		btnConfirm.setBounds(126, 333, 117, 46);
		panel.add(btnConfirm);
		
		 btnDeny = new JButton("Deny");
		 btnDeny.addActionListener(new btnHandler());
		btnDeny.setBounds(343, 333, 117, 46);
		panel.add(btnDeny);
		
		JLabel lblSuggested = new JLabel("Suggested");
		lblSuggested.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSuggested.setBounds(167, 56, 117, 33);
		panel.add(lblSuggested);
		
		JLabel lblCurrent = new JLabel("Current");
		lblCurrent.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCurrent.setBounds(328, 62, 117, 21);
		panel.add(lblCurrent);
		
		JLabel lblMaximal = new JLabel("Maximal");
		lblMaximal.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMaximal.setBounds(469, 60, 117, 24);
		panel.add(lblMaximal);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.BLACK);
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setForeground(Color.BLACK);
		separator.setBounds(286, 78, 9, 212);
		panel.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(Color.BLACK);
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setForeground(Color.BLACK);
		separator_1.setBounds(436, 78, 9, 212);
		panel.add(separator_1);
		
		JLabel lblFuel = new JLabel("Fuel Type");
		lblFuel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblFuel.setBounds(10, 61, 106, 21);
		panel.add(lblFuel);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBackground(Color.BLACK);
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setForeground(Color.BLACK);
		separator_2.setBounds(126, 78, 9, 212);
		panel.add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBackground(Color.BLACK);
		separator_3.setForeground(Color.BLACK);
		separator_3.setBounds(10, 130, 564, 2);
		panel.add(separator_3);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setForeground(Color.BLACK);
		separator_4.setBackground(Color.BLACK);
		separator_4.setBounds(10, 87, 564, 2);
		panel.add(separator_4);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setForeground(Color.BLACK);
		separator_5.setBackground(Color.BLACK);
		separator_5.setBounds(10, 176, 564, 2);
		panel.add(separator_5);
		
		JSeparator separator_6 = new JSeparator();
		separator_6.setForeground(Color.BLACK);
		separator_6.setBackground(Color.BLACK);
		separator_6.setBounds(10, 218, 564, 2);
		panel.add(separator_6);
		
		JSeparator separator_7 = new JSeparator();
		separator_7.setForeground(Color.BLACK);
		separator_7.setBackground(Color.BLACK);
		separator_7.setBounds(10, 261, 564, 2);
		panel.add(separator_7);
		
		JLabel label = new JLabel("95");
		label.setBounds(20, 100, 46, 14);
		panel.add(label);
		
		JLabel lblDiesel = new JLabel("Diesel");
		lblDiesel.setBounds(20, 151, 46, 14);
		panel.add(lblDiesel);
		
		JLabel lblScooter = new JLabel("Scooter");
		lblScooter.setBounds(20, 193, 46, 14);
		panel.add(lblScooter);
		
		JLabel lblHomeFuel = new JLabel("Home Fuel");
		lblHomeFuel.setBounds(20, 236, 70, 14);
		panel.add(lblHomeFuel);
		
		 sug_95 = new JLabel("Suggested_95");
		sug_95.setBounds(177, 105, 46, 14);
		panel.add(sug_95);
		
		 sug_diesel = new JLabel("Suggested_Diesel");
		sug_diesel.setBounds(177, 151, 46, 14);
		panel.add(sug_diesel);
		
		 sug_scooter = new JLabel("Suggested_Scooter");
		 sug_scooter.setBounds(177, 193, 46, 14);
		 panel.add(sug_scooter);
		
		 sug_home = new JLabel("Suggested_Home_Fuel");
		 sug_home.setBounds(177, 236, 46, 14);
		 panel.add(sug_home);
		
		 cur_95 = new JLabel("Current");
		cur_95.setBounds(343, 105, 46, 14);
		panel.add(cur_95);
		
		 cur_diesel = new JLabel("Current");
		cur_diesel.setBounds(343, 151, 46, 14);
		panel.add(cur_diesel);
		
		 cur_scooter = new JLabel("Current");
		 cur_scooter.setBounds(341, 193, 46, 14);
		panel.add(cur_scooter);
		
		 cur_home = new JLabel("Current");
		 cur_home.setBounds(341, 231, 46, 14);
		panel.add(cur_home);
		
		 max_95 = new JLabel("maximal");
		max_95.setBounds(479, 105, 46, 14);
		panel.add(max_95);
		
		 max_diesel = new JLabel("maximal");
		max_diesel.setBounds(479, 151, 46, 14);
		panel.add(max_diesel);
		
		 max_scooter = new JLabel("maximal");
		max_scooter.setBounds(479, 193, 46, 14);
		panel.add(max_scooter);
		
		 max_home = new JLabel("maximal");
		max_home.setBounds(479, 236, 46, 14);
		panel.add(max_home);
		this.setContentPane(contentPane);
		this.actions = actions;
	}
	
	/***
	 * set the fuel prices to the labels
	 * @param fuels - arraylist with the fuel prices
	 */
	public void setLabels(ArrayList<Fuel> fuels)
	{
		this.fuels = fuels;
		Fuel f = null;
		
		f = fuels.get(0);
		sug_95.setText(""+f.getFprice());
		cur_95.setText(""+ f.getCurrPrice());
		max_95.setText(""+f.getMaxPrice());
		
		f = fuels.get(1);
		sug_diesel.setText(""+f.getFprice());
		cur_diesel.setText(""+ f.getCurrPrice());
		max_diesel.setText(""+f.getMaxPrice());
		
		f = fuels.get(2);
		sug_scooter.setText(""+f.getFprice());
		cur_scooter.setText(""+ f.getCurrPrice());
		max_scooter.setText(""+f.getMaxPrice());
		
		f = fuels.get(3);
		sug_home.setText(""+f.getFprice());
		cur_home.setText(""+ f.getCurrPrice());
		max_home.setText(""+f.getMaxPrice());
		
		
	}
	
	/***
	 * 
	 * ActionListener for the buttons
	 *
	 */
	private class btnHandler implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			getInput(e);
		}
		
	}
	
	/***
	 * handling the action event
	 */
	
	@Override
	public void getInput(ActionEvent e) 
	{
		if(e.getSource() == this.btnConfirm)
			actions.confirmRates();
		
		else if(e.getSource() == this.btnDeny)
			actions.denyRates();
		
	}
	/***
	 * 
	 * @return arrayList with fuel prices
	 */

	public ArrayList<Fuel> getFuels() {
		return fuels;
	}
	
	/***
	 *  set the arraylost with the fuel prices
	 * @param fuels
	 */
	public void setFuels(ArrayList<Fuel> fuels) {
		this.fuels = fuels;
	}
}
