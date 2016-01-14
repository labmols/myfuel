package myfuel.gui;

import myfuel.GUIActions.CarFuelActions;
import myfuel.GUIActions.FastFuelActions;
import myfuel.client.Promotion;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;
import java.text.DecimalFormat;

@SuppressWarnings("serial")
public class FastFuelGUI extends CarFuelGUI {
	//private FastFuelActions actions;
	private JLabel lblCar;
	private JLabel lblS;
	private JLabel lblNfcDetails;

	public FastFuelGUI(FastFuelActions actions) {
		super(actions);
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
		
		lblNfcDetails = new JLabel("NFC Details: ");
		lblNfcDetails.setFont(new Font("Arial", Font.BOLD, 14));
		lblNfcDetails.setBounds(35, 54, 99, 16);
		panel.add(lblNfcDetails);
		setContentPane(contentPane);
		// TODO Auto-generated constructor stub
	}

	public void setCar(int cid) {
		// TODO Auto-generated method stub
		lblCar.setText(""+cid);
	}
	
	@Override
	protected void setDetails(int fuelSelected) {
		// TODO Auto-generated method stub
	currentPrice = actions.getPrice(fuelSelected, 1);
	totalPrice.setText(""+new DecimalFormat("##.##").format(currentPrice)+" NIS");
	Promotion p = actions.getPromotion(fuelSelected);
	if(p!=null)
	promDisc.setText(new DecimalFormat("##.##").format(p.getDiscount()) +"%");
	else promDisc.setText("No Promotion.");
	}
	
}
