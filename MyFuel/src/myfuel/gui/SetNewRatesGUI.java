package myfuel.gui;


import java.awt.event.ActionEvent;





import myfuel.GUIActions.SetNewRatesActions;
import myfuel.client.BackMainMenu;
import myfuel.client.Fuel;
import myfuel.client.Rate;

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
	private JTextField SMRoneCar;
	private JTextField SMRfewCar;
	private JTextField SFMoneCar;
	private JLabel CMRoneCar;
	private JLabel CMRfewCar;
	private JLabel CFMoneCar;
	
	/**
	 * Create the frame.
	 */
	public SetNewRatesGUI(SetNewRatesActions actions) {
		panel.setLocation(0, 0);
		this.actions = actions;
		lblTitle.setBounds(203, 11, 162, 16);
		lblTitle.setText("Set New Rates");
		this.mainMenu.addActionListener(new BackMainMenu(actions));
		JLabel lblNewLabel = new JLabel("Monthly Regular-one Car");
		lblNewLabel.setBounds(24, 122, 162, 14);
		panel.add(lblNewLabel);
		
		JLabel lblDiesel = new JLabel("Monthly Regular-Few Cars");
		lblDiesel.setBounds(24, 158, 172, 14);
		panel.add(lblDiesel);
		
		JLabel lblScooter = new JLabel("Fully Monthly-one Car");
		lblScooter.setBounds(24, 192, 125, 14);
		panel.add(lblScooter);
		
		SMRoneCar = new JTextField();
		SMRoneCar.setBounds(250, 126, 41, 20);
		panel.add(SMRoneCar);
		SMRoneCar.setColumns(10);
		
		SMRfewCar = new JTextField();
		SMRfewCar.setBounds(250, 162, 41, 20);
		panel.add(SMRfewCar);
		SMRfewCar.setColumns(10);
		
		SFMoneCar = new JTextField();
		SFMoneCar.setBounds(250, 196, 41, 20);
		panel.add(SFMoneCar);
		SFMoneCar.setColumns(10);
		
		CMRoneCar = new JLabel("0");
		CMRoneCar.setBounds(430, 122, 46, 14);
		panel.add(CMRoneCar);
		
		CMRfewCar = new JLabel("0");
		CMRfewCar.setToolTipText("0");
		CMRfewCar.setBounds(430, 158, 46, 14);
		panel.add(CMRfewCar);
		
		CFMoneCar = new JLabel("0");
		CFMoneCar.setBounds(430, 192, 46, 14);
		panel.add(CFMoneCar);
		
		JButton UpdateBut = new JButton("Update");
		UpdateBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actions.verifyDetails(SMRoneCar.getText(),SMRfewCar.getText(),
						SFMoneCar.getText());
			}
		});
		UpdateBut.setBounds(184, 303, 144, 54);
		panel.add(UpdateBut);
		
		JLabel lblTypeOfFuel = new JLabel("Sale Model");
		lblTypeOfFuel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTypeOfFuel.setBounds(26, 84, 142, 23);
		panel.add(lblTypeOfFuel);
		
		JLabel lblSuggested = new JLabel("Suggested");
		lblSuggested.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSuggested.setBounds(230, 84, 104, 31);
		panel.add(lblSuggested);
		
		JLabel lblCurrent = new JLabel("Current");
		lblCurrent.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCurrent.setBounds(407, 85, 92, 21);
		panel.add(lblCurrent);
		
		JSeparator separator_8 = new JSeparator();
		separator_8.setOrientation(SwingConstants.VERTICAL);
		separator_8.setForeground(Color.BLACK);
		separator_8.setBackground(Color.BLACK);
		separator_8.setBounds(184, 84, 2, 180);
		panel.add(separator_8);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setForeground(Color.BLACK);
		separator.setBackground(Color.BLACK);
		separator.setBounds(369, 84, 16, 180);
		panel.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setForeground(Color.BLACK);
		separator_1.setBackground(Color.BLACK);
		separator_1.setBounds(497, 84, 2, 180);
		panel.add(separator_1);
		
		JLabel label = new JLabel("%");
		label.setBounds(441, 107, 46, 45);
		panel.add(label);
		
		JLabel label_1 = new JLabel("%");
		label_1.setBounds(297, 114, 46, 45);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("%");
		label_2.setBounds(297, 145, 46, 45);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("%");
		label_3.setBounds(297, 184, 46, 45);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("%");
		label_4.setBounds(440, 143, 46, 45);
		panel.add(label_4);
		
		JLabel label_5 = new JLabel("%");
		label_5.setBounds(440, 177, 46, 45);
		panel.add(label_5);
		setContentPane(contentPane);
	}

	@Override
	public void getInput(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void SetNewDetails(ArrayList<Rate> arrayList)
	{
		Rate s;
		s=arrayList.get(1);
		CMRoneCar.setText(""+s.getDiscount());
		s=arrayList.get(2);
		CMRfewCar.setText(""+s.getDiscount());
		s=arrayList.get(3);
		CFMoneCar.setText(""+s.getDiscount());
	}
}
