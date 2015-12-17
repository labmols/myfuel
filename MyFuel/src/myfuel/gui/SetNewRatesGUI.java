package myfuel.gui;


import java.awt.event.ActionEvent;


import myfuel.GUIActions.SetNewRatesActions;
import myfuel.client.Fuel;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.util.ArrayList;

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
		lblTitle.setBounds(173, 11, 162, 16);
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
		Sugg_F95.setBounds(88, 119, 86, 20);
		panel.add(Sugg_F95);
		Sugg_F95.setColumns(10);
		
		Sugg_FDiesel = new JTextField();
		Sugg_FDiesel.setBounds(88, 155, 86, 20);
		panel.add(Sugg_FDiesel);
		Sugg_FDiesel.setColumns(10);
		
		Sugg_FScooter = new JTextField();
		Sugg_FScooter.setBounds(88, 189, 86, 20);
		panel.add(Sugg_FScooter);
		Sugg_FScooter.setColumns(10);
		
		JLabel lblCurrentPrice = new JLabel("Current Price");
		lblCurrentPrice.setBounds(209, 122, 72, 14);
		panel.add(lblCurrentPrice);
		
		JLabel lblCurrentPrice_1 = new JLabel("Current Price");
		lblCurrentPrice_1.setBounds(209, 158, 72, 14);
		panel.add(lblCurrentPrice_1);
		
		JLabel lblCurrentPrice_2 = new JLabel("Current Price");
		lblCurrentPrice_2.setBounds(209, 192, 72, 14);
		panel.add(lblCurrentPrice_2);
		
		Current_F95 = new JLabel("New label");
		Current_F95.setBounds(304, 122, 46, 14);
		panel.add(Current_F95);
		
		Current_FDiesel = new JLabel("New label");
		Current_FDiesel.setBounds(304, 158, 46, 14);
		panel.add(Current_FDiesel);
		
		Current_FScooter = new JLabel("New label");
		Current_FScooter.setBounds(304, 192, 46, 14);
		panel.add(Current_FScooter);
		
		JLabel lblMaxPrice = new JLabel("Max Price");
		lblMaxPrice.setBounds(389, 122, 46, 14);
		panel.add(lblMaxPrice);
		
		JLabel lblNewLabel_4 = new JLabel("Max price");
		lblNewLabel_4.setBounds(387, 158, 46, 14);
		panel.add(lblNewLabel_4);
		
		JLabel lblMaxPrice_1 = new JLabel("Max price");
		lblMaxPrice_1.setBounds(387, 192, 46, 14);
		panel.add(lblMaxPrice_1);
		
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
		UpdateBut.setBounds(206, 281, 144, 54);
		panel.add(UpdateBut);
		
		JLabel lblHomeFuel = new JLabel("Home Fuel");
		lblHomeFuel.setBounds(24, 224, 66, 14);
		panel.add(lblHomeFuel);
		
		Sugg_FHomeFuel = new JTextField();
		Sugg_FHomeFuel.setBounds(88, 220, 86, 20);
		panel.add(Sugg_FHomeFuel);
		Sugg_FHomeFuel.setColumns(10);
		
		JLabel lblCurrentPrice_3 = new JLabel("Current Price");
		lblCurrentPrice_3.setBounds(209, 224, 72, 14);
		panel.add(lblCurrentPrice_3);
		
		Current_HomeFuel = new JLabel("New label");
		Current_HomeFuel.setBounds(304, 224, 46, 14);
		panel.add(Current_HomeFuel);
		
		JLabel lblMaxPrice_2 = new JLabel("Max Price");
		lblMaxPrice_2.setBounds(387, 224, 46, 14);
		panel.add(lblMaxPrice_2);
		
		Max_FHomeFuel = new JLabel("New label");
		Max_FHomeFuel.setBounds(463, 224, 46, 14);
		panel.add(Max_FHomeFuel);
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
