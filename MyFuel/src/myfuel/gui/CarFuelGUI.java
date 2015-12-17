package myfuel.gui;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;

import myfuel.GUIActions.CarFuelActions;

import javax.swing.JProgressBar;
import javax.swing.DefaultComboBoxModel;

import java.awt.event.ActionListener;

import javax.swing.SwingConstants;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.FlowLayout;


public class CarFuelGUI extends SuperGUI {

	
	private JTextField qtyText;
	private JTextField cashTextField;
	private JTextField changeTextField;
	CarFuelActions actions;
	JProgressBar progressBar;
	/**
	 * Create the frame.
	 */
	public CarFuelGUI(CarFuelActions actions) {
		this.actions=actions;
		panel.setLocation(0, 0);
		lblTitle.setBounds(218, 6, 117, 23);
		lblTitle.setText("Car Fueling");
		setContentPane(contentPane);
		
		
		JLabel lblChooseStation = new JLabel("Choose Station:");
		lblChooseStation.setBackground(Color.RED);
		lblChooseStation.setBounds(43, 93, 99, 16);
		panel.add(lblChooseStation);
		
		JLabel lblChooseFuelPump = new JLabel("Choose Fuel Pump:");
		lblChooseFuelPump.setBounds(28, 121, 130, 16);
		panel.add(lblChooseFuelPump);
		
		JRadioButton rbdiesel = new JRadioButton("Diesel");
		rbdiesel.setBackground(Color.ORANGE);
		rbdiesel.setBounds(159, 160, 141, 23);
		panel.add(rbdiesel);
		
		JRadioButton rb95 = new JRadioButton("95");
		rb95.setBackground(Color.ORANGE);
		rb95.setBounds(159, 117, 141, 23);
		panel.add(rb95);
		
		JRadioButton rbscooter = new JRadioButton("Scooter");
		rbscooter.setBackground(Color.ORANGE);
		rbscooter.setBounds(159, 138, 141, 23);
		panel.add(rbscooter);
		
		JComboBox stationCombo = new JComboBox();
		stationCombo.setBounds(153, 89, 84, 27);
		panel.add(stationCombo);
		
		JLabel lblQuantity = new JLabel("Quantity:");
		lblQuantity.setBounds(89, 200, 61, 16);
		panel.add(lblQuantity);
		
		qtyText = new JTextField();
		qtyText.setBounds(161, 195, 76, 26);
		panel.add(qtyText);
		qtyText.setColumns(10);
		
		JButton btnStartFuel = new JButton("Start Fueling");
		btnStartFuel.setBounds(99, 230, 117, 29);
		panel.add(btnStartFuel);
		
		
		JPanel p = new JPanel();
		p.setOpaque(false);
		p.setLocation(183, 293);
		p.setSize(200, 69);
		p.setLayout(new FlowLayout());
		

		FuelDialog dialog = new FuelDialog(40,(float)5.8);
		
		JPanel panel2 = new JPanel();
		panel2.setVisible(false);
		panel2.setBackground(Color.WHITE);
		panel2.setBounds(312, 87, 278, 182);
		panel.add(panel2);
		panel2.setOpaque(false);
		panel2.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Price:");
		lblNewLabel.setBounds(6, 6, 61, 16);
		panel2.add(lblNewLabel);
		
		JLabel lblPaymentMethod = new JLabel("Payment Method:");
		lblPaymentMethod.setBounds(6, 29, 130, 16);
		panel2.add(lblPaymentMethod);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Credit Card", "Cash"}));
		comboBox_1.setBounds(115, 25, 139, 27);
		panel2.add(comboBox_1);
		
		JButton btnPay = new JButton("Pay");
		btnPay.setBounds(62, 141, 117, 29);
		panel2.add(btnPay);
		
		JPanel panel3 = new JPanel();
		panel3.setBounds(6, 57, 248, 77);
		panel2.add(panel3);
		panel3.setLayout(null);
		
		JLabel lblInsertCash = new JLabel("Insert Cash:");
		lblInsertCash.setBounds(6, 11, 76, 16);
		panel3.add(lblInsertCash);
		
		cashTextField = new JTextField();
		cashTextField.setColumns(10);
		cashTextField.setBounds(78, 6, 76, 26);
		panel3.add(cashTextField);
		
		JLabel lblChange = new JLabel("Change:");
		lblChange.setBounds(6, 44, 61, 16);
		panel3.add(lblChange);
		
		changeTextField = new JTextField();
		changeTextField.setColumns(10);
		changeTextField.setBounds(78, 39, 76, 26);
		panel3.add(changeTextField);
		panel3.setOpaque(false);
		
		btnStartFuel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel2.setVisible(true);
				
				
				dialog.setVisible(true);
				//new Thread(ju).start();
			}
		});
		
		
		
	}

	@Override
	public void getInput(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}
