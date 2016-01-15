package myfuel.gui;

import myfuel.GUIActions.CarFuelActions;
import myfuel.GUIActions.FastFuelActions;
import myfuel.client.NetworkRates;
import myfuel.client.Promotion;
import myfuel.client.Station;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

/**
 *	Fast Fuel User Interface
 */
@SuppressWarnings("serial")
public class FastFuelGUI extends CarFuelGUI {
	//private FastFuelActions actions;
	/**
	 * The Car number that identified by the nfc sensor.
	 */
	private JLabel lblCar;
	/**
	 * Random station name.
	 */
	private JLabel lblS;
	/**
	 * Random Station ID.
	 */
	private int sid;
	/**
	 * Fast Fuel GUI Controller.
	 */
	private FastFuelActions actionsF;

	/**
	 * Create new Fast Fuel user interface that extends Car Fuel user interface.
	 * @param actions - Fast Fuel GUI Controller, handle all the logic functionality.
	 */
	public FastFuelGUI(FastFuelActions actions) {
		super(actions);
		this.actionsF = actions;
		lblTitle.setBounds(218, 6, 205, 23);
		lblTitle.setText("Fast Fuel With NFC");

		lblChooseStation.setSize(0, 0);
		lblChooseStation.setVisible(false);
		lblChooseCar.setSize(0, 0);
		lblChooseCar.setVisible(false);
		carCB.setSize(-3, 27);
		carCB.setLocation(593, 145);
		stationCombo.setSize(33, 27);
		stationCombo.setLocation(-46, 116);
		carCB.setVisible(false);
		stationCombo.setVisible(false);
		
		JLabel lblStation = new JLabel("Station:");
		lblStation.setFont(new Font("Arial", Font.BOLD, 14));
		lblStation.setBounds(135, 69, 61, 16);
		panel.add(lblStation);
		
		JLabel lblCarId = new JLabel("Car ID:");
		lblCarId.setFont(new Font("Arial", Font.BOLD, 14));
		lblCarId.setBounds(335, 69, 61, 16);
		panel.add(lblCarId);
		
		lblS = new JLabel("");
		lblS.setForeground(Color.WHITE);
		lblS.setFont(new Font("Arial", Font.PLAIN, 14));
		lblS.setBounds(196, 69, 75, 16);
		panel.add(lblS);
		
		lblCar = new JLabel("");
		lblCar.setForeground(Color.WHITE);
		lblCar.setFont(new Font("Arial", Font.PLAIN, 14));
		lblCar.setBounds(388, 69, 117, 16);
		panel.add(lblCar);
		
		JLabel lblNfcDetails = new JLabel("NFC Details: ");
		lblNfcDetails.setFont(new Font("Arial", Font.BOLD, 14));
		lblNfcDetails.setBounds(35, 54, 99, 16);
		panel.add(lblNfcDetails);
		btnStartFuel.removeActionListener(listener);
		btnStartFuel.addActionListener(new eventListener());
		setContentPane(contentPane);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Set NFC Details, received random Car and station details from DataBase.
	 * @param cid- Car ID Number.
	 * @param s - One of the customer stations.
	 */
	public void setNFC(int cid, Station s) {
		// TODO Auto-generated method stub
		lblCar.setText(""+cid);
		lblS.setText(s.getName());
		this.sid = s.getsid();
		this.nid = s.getNetwork().getNid();
	}
	

	/**
	 * This Class is used for components events handling.
	 *
	 */
	private class eventListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == btnStartFuel )
			{
				
				if(actionsF.verifyDetails(LimitText.getText(), fuelSelected, dName.getText(),nid,limitBox.getSelectedIndex()));
				startFuel(Float.parseFloat(LimitText.getText()), limitBox.getSelectedIndex());
			}
		}
		
	}
}
