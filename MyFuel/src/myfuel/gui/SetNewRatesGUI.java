package myfuel.gui;


import java.awt.event.ActionEvent;


import myfuel.GUIActions.SetNewRatesActions;
import myfuel.client.Fuel;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.SwingConstants;

public class SetNewRatesGUI extends SuperGUI {

	SetNewRatesActions actions;
	private JTextField Sugg_F95;
	private JTextField Sugg_FDiesel;
	private JTextField Sugg_FScooter;
	private JTextField Sugg_FHomeFuel;
	JLabel Current_F95;
	JLabel Current_FDiesel;
	JLabel Current_FScooter;
	JLabel Current_HomeFuel;
	JLabel Max_F95;
	JLabel Max_FDiesel;
	JLabel Max_FScooter;
	JLabel Max_FHomeFuel;
	
	/**
	 * Create the frame.
	 */
	public SetNewRatesGUI(SetNewRatesActions actions) {
		panel.setLocation(0, 0);
		this.actions = actions;
		lblTitle.setBounds(203, 11, 162, 16);
		lblTitle.setText("Set New Rates");
		
		JLabel lblNewLabel = new JLabel("95");
		lblNewLabel.setBounds(32, 122, 46, 14);
		panel.add(lblNewLabel);
		
		JLabel lblDiesel = new JLabel("Diesel");
		lblDiesel.setBounds(24, 158, 46, 14);
		panel.add(lblDiesel);
		
		JLabel lblScooter = new JLabel("Scooter");
		lblScooter.setBounds(24, 192, 46, 14);
		panel.add(lblScooter);
		
		Sugg_F95 = new JTextField();
		Sugg_F95.setBounds(169, 119, 86, 20);
		panel.add(Sugg_F95);
		Sugg_F95.setColumns(10);
		
		Sugg_FDiesel = new JTextField();
		Sugg_FDiesel.setBounds(169, 155, 86, 20);
		panel.add(Sugg_FDiesel);
		Sugg_FDiesel.setColumns(10);
		
		Sugg_FScooter = new JTextField();
		Sugg_FScooter.setBounds(169, 189, 86, 20);
		panel.add(Sugg_FScooter);
		Sugg_FScooter.setColumns(10);
		
		Current_F95 = new JLabel("New label");
		Current_F95.setBounds(331, 122, 46, 14);
		panel.add(Current_F95);
		
		Current_FDiesel = new JLabel("New label");
		Current_FDiesel.setBounds(331, 158, 46, 14);
		panel.add(Current_FDiesel);
		
		Current_FScooter = new JLabel("New label");
		Current_FScooter.setBounds(331, 192, 46, 14);
		panel.add(Current_FScooter);
		
		Max_F95 = new JLabel("New label");
		Max_F95.setBounds(463, 122, 46, 14);
		panel.add(Max_F95);
		
		Max_FDiesel = new JLabel("New label");
		Max_FDiesel.setBounds(463, 158, 46, 14);
		panel.add(Max_FDiesel);
		
		Max_FScooter = new JLabel("New label");
		Max_FScooter.setBounds(463, 192, 46, 14);
		panel.add(Max_FScooter);
		
		JButton UpdateBut = new JButton("Update");
		UpdateBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actions.verifyDetails(Float.parseFloat(Sugg_F95.getText()),Float.parseFloat(Sugg_FDiesel.getText()),
						Float.parseFloat(Sugg_FScooter.getText()),Float.parseFloat(Sugg_FHomeFuel.getText()));
			}
		});
		UpdateBut.setBounds(206, 333, 144, 54);
		panel.add(UpdateBut);
		
		JLabel lblHomeFuel = new JLabel("Home Fuel");
		lblHomeFuel.setBounds(24, 224, 66, 14);
		panel.add(lblHomeFuel);
		
		Sugg_FHomeFuel = new JTextField();
		Sugg_FHomeFuel.setBounds(169, 221, 86, 20);
		panel.add(Sugg_FHomeFuel);
		Sugg_FHomeFuel.setColumns(10);
		
		Current_HomeFuel = new JLabel("New label");
		Current_HomeFuel.setBounds(331, 224, 46, 14);
		panel.add(Current_HomeFuel);
		
		Max_FHomeFuel = new JLabel("New label");
		Max_FHomeFuel.setBounds(463, 224, 46, 14);
		panel.add(Max_FHomeFuel);
		
		JLabel lblTypeOfFuel = new JLabel("Type of Fuel");
		lblTypeOfFuel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTypeOfFuel.setBounds(7, 84, 124, 23);
		panel.add(lblTypeOfFuel);
		
		JLabel lblSuggested = new JLabel("Suggested");
		lblSuggested.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSuggested.setBounds(159, 80, 104, 31);
		panel.add(lblSuggested);
		
		JLabel lblCurrent = new JLabel("Current");
		lblCurrent.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCurrent.setBounds(321, 85, 92, 21);
		panel.add(lblCurrent);
		
		JLabel lblMaximal = new JLabel("Maximal");
		lblMaximal.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMaximal.setBounds(443, 85, 97, 20);
		panel.add(lblMaximal);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBackground(Color.BLACK);
		separator_3.setForeground(Color.BLACK);
		separator_3.setBounds(8, 107, 530, 3);
		panel.add(separator_3);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setForeground(Color.BLACK);
		separator_4.setBackground(Color.BLACK);
		separator_4.setBounds(8, 145, 530, 3);
		panel.add(separator_4);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setForeground(Color.BLACK);
		separator_5.setBackground(Color.BLACK);
		separator_5.setBounds(7, 179, 530, 3);
		panel.add(separator_5);
		
		JSeparator separator_6 = new JSeparator();
		separator_6.setForeground(Color.BLACK);
		separator_6.setBackground(Color.BLACK);
		separator_6.setBounds(7, 215, 530, 3);
		panel.add(separator_6);
		
		JSeparator separator_7 = new JSeparator();
		separator_7.setForeground(Color.BLACK);
		separator_7.setBackground(Color.BLACK);
		separator_7.setBounds(8, 251, 530, 3);
		panel.add(separator_7);
		
		JSeparator separator_8 = new JSeparator();
		separator_8.setOrientation(SwingConstants.VERTICAL);
		separator_8.setForeground(Color.BLACK);
		separator_8.setBackground(Color.BLACK);
		separator_8.setBounds(141, 91, 2, 180);
		panel.add(separator_8);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setForeground(Color.BLACK);
		separator.setBackground(Color.BLACK);
		separator.setBounds(280, 84, 16, 180);
		panel.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setForeground(Color.BLACK);
		separator_1.setBackground(Color.BLACK);
		separator_1.setBounds(419, 93, 2, 180);
		panel.add(separator_1);
		setContentPane(contentPane);
	}

	@Override
	public void getInput(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void SetNewDetails(ArrayList <Fuel> newdetails)
	{
		Fuel f;
		f=newdetails.get(0);
		Current_F95.setText(""+f.getFprice());
		Max_F95.setText(""+f.getMaxPrice());
		f=newdetails.get(1);
		Current_FDiesel.setText(""+f.getFprice());
		Max_FDiesel.setText(""+f.getMaxPrice());
		f=newdetails.get(2);
		Current_FScooter.setText(""+f.getFprice());
		Max_FScooter.setText(""+f.getMaxPrice());
		f=newdetails.get(3);
		Current_HomeFuel.setText(""+f.getFprice());
		Max_FHomeFuel.setText(""+f.getMaxPrice());
	}
}
