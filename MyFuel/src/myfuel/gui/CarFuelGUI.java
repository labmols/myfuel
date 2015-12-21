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
import myfuel.client.BackMainMenu;

import javax.swing.JProgressBar;
import javax.swing.DefaultComboBoxModel;

import java.awt.event.ActionListener;

import javax.swing.SwingConstants;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import java.awt.Font;


public class CarFuelGUI extends SuperGUI {

	
	private JTextField qtyText;
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
		
		this.mainMenu.addActionListener(new BackMainMenu(actions));
		
		
		JPanel p = new JPanel();
		p.setOpaque(false);
		p.setLocation(183, 293);
		p.setSize(200, 69);
		p.setLayout(new FlowLayout());
		

		FuelDialog dialog = new FuelDialog(40,(float)5.8);
		
		JPanel panel2 = new JPanel();
		panel2.setBounds(49, 58, 521, 366);
		panel2.setOpaque(false);
		panel.add(panel2);
		panel2.setLayout(null);
		
		JLabel lblChooseStation = new JLabel("Choose Station:");
		lblChooseStation.setBounds(40, 9, 99, 16);
		panel2.add(lblChooseStation);
		lblChooseStation.setBackground(Color.RED);
		
		JComboBox stationCombo = new JComboBox();
		stationCombo.setBounds(161, 5, 94, 27);
		panel2.add(stationCombo);
		
		JLabel lblChooseFuelPump = new JLabel("Choose Fuel Pump:");
		lblChooseFuelPump.setBounds(19, 47, 130, 16);
		panel2.add(lblChooseFuelPump);
		
		
		
		
		 java.net.URL url = getClass().getResource("/fuel.png");

		XRadioButton rb95 = new XRadioButton();
		rb95.getLabel().setFont(new Font("Lucida Grande", Font.BOLD, 13));
		rb95.getLabel().setBounds(34, 0, 179, 62);
		rb95.getRadioButton().setBounds(6, 0, 28, 23);
		rb95.setOpaque(false);
		rb95.setBounds(161, 44, 241, 62);
		rb95.setText("95");
	
		rb95.setIcon(new ImageIcon(url));
		panel2.add(rb95);
		rb95.setLayout(null);

		
		XRadioButton rbdiesel = new XRadioButton();
		rbdiesel.getLabel().setFont(new Font("Lucida Grande", Font.BOLD, 13));
		rbdiesel.getLabel().setBounds(39, 0, 202, 62);
		rbdiesel.getRadioButton().setBounds(6, 0, 28, 23);
		rbdiesel.setOpaque(false);
		rbdiesel.setBounds(161, 188, 241, 62);
		rbdiesel.setText("Diesel");
		rbdiesel.setIcon(new ImageIcon(url));
		panel2.add(rbdiesel);
		rbdiesel.setLayout(null);
		
		
		
		XRadioButton rbscooter = new XRadioButton();
		rbscooter.getLabel().setFont(new Font("Lucida Grande", Font.BOLD, 13));
		rbscooter.getLabel().setBounds(36, 0, 199, 62);
		rbscooter.getRadioButton().setBounds(6, 0, 28, 23);
		rbscooter.setOpaque(false);
		rbscooter.setBounds(161, 114, 241, 62);
		rbscooter.setText("Scooter");
		rbscooter.setIcon(new ImageIcon(url));
		panel2.add(rbscooter);
		rbscooter.setLayout(null);
		
		
		
		JLabel lblQuantity = new JLabel("Quantity:");
		lblQuantity.setBounds(99, 274, 61, 16);
		panel2.add(lblQuantity);
		
		qtyText = new JTextField();
		qtyText.setBounds(171, 269, 76, 26);
		panel2.add(qtyText);
		qtyText.setColumns(10);
		
		JButton btnStartFuel = new JButton("Start Fueling");
		btnStartFuel.setBounds(196, 331, 117, 29);
		panel2.add(btnStartFuel);
		
		
		btnStartFuel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialog.setVisible(true);
			}
		});
		
	}

	@Override
	public void getInput(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
