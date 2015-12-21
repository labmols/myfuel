package myfuel.gui;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.ButtonGroup;
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
	private CarFuelActions actions;
	private JProgressBar progressBar;
	private DefaultComboBoxModel<String> stationModel;
	private XRadioButton rb95;
	private XRadioButton rbscooter;
	private XRadioButton rbdiesel;
	private int fuelSelected;
	private JLabel lblPrice;
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
		
		JPanel panel2 = new JPanel();
		panel2.setBounds(0, 58, 596, 382);
		panel2.setOpaque(false);
		panel.add(panel2);
		panel2.setLayout(null);
		
		JLabel lblChooseStation = new JLabel("Choose Station:");
		lblChooseStation.setBounds(35, 9, 99, 16);
		panel2.add(lblChooseStation);
		lblChooseStation.setBackground(Color.RED);
		
		JComboBox <String> stationCombo = new JComboBox<String>();
		stationModel = new DefaultComboBoxModel<String>();
		stationCombo.setModel(stationModel);
		stationCombo.setBounds(146, 5, 109, 27);
		panel2.add(stationCombo);
		
		JLabel lblChooseFuelPump = new JLabel("Choose Fuel Pump:");
		lblChooseFuelPump.setBounds(35, 46, 130, 16);
		panel2.add(lblChooseFuelPump);
		
		
		
		
		 java.net.URL url = getClass().getResource("/fuel.png");

		rb95 = new XRadioButton();
		rb95.getLabel().setFont(new Font("Lucida Grande", Font.BOLD, 13));
		rb95.getLabel().setBounds(34, 0, 179, 62);
		rb95.getRadioButton().setBounds(6, 0, 28, 23);
		
		rb95.setOpaque(false);
		rb95.setBounds(35, 74, 148, 62);
		rb95.setText("95");
	
		rb95.setIcon(new ImageIcon(url));
		panel2.add(rb95);
		rb95.setLayout(null);

		
		rbdiesel = new XRadioButton();
		rbdiesel.getLabel().setFont(new Font("Lucida Grande", Font.BOLD, 13));
		rbdiesel.getLabel().setBounds(39, 0, 145, 62);
		rbdiesel.getRadioButton().setBounds(6, 0, 28, 23);
		rbdiesel.setOpaque(false);
		rbdiesel.setBounds(186, 74, 183, 62);
		rbdiesel.setText("Diesel");
		rbdiesel.setIcon(new ImageIcon(url));
		panel2.add(rbdiesel);
		rbdiesel.setLayout(null);
		
		
		
		JLabel lblQuantity = new JLabel("Quantity:");
		lblQuantity.setBounds(91, 275, 61, 16);
		panel2.add(lblQuantity);
		
		qtyText = new JTextField();
		qtyText.setBounds(164, 270, 76, 26);
		panel2.add(qtyText);
		qtyText.setColumns(10);
		
		JButton btnStartFuel = new JButton("Start Fueling");
		btnStartFuel.setBounds(214, 331, 117, 29);
		panel2.add(btnStartFuel);
		
		lblPrice = new JLabel("");
		lblPrice.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));
		lblPrice.setForeground(Color.RED);
		lblPrice.setBounds(288, 270, 68, 16);
		panel2.add(lblPrice);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(rb95.getRadioButton());
		buttonGroup.add(rbdiesel.getRadioButton());
		
		JLabel lblPrice2 = new JLabel("");
		lblPrice2.setForeground(Color.RED);
		lblPrice2.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));
		lblPrice2.setBounds(374, 270, 68, 16);
		panel2.add(lblPrice2);
		
		JPanel panel = new JPanel();
		panel.setBounds(189, 209, 198, 49);
		panel2.add(panel);
		
		JLabel lblPriceliter = new JLabel("Current Price(Liter): ");
		lblPriceliter.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(lblPriceliter);
		
		JLabel lblYourPriceliter = new JLabel("Your Price(Liter): ");
		panel.add(lblYourPriceliter);
		
		
		
		rbscooter = new XRadioButton();
		rbscooter.setBounds(374, 74, 183, 62);
		panel2.add(rbscooter);
		rbscooter.getLabel().setFont(new Font("Lucida Grande", Font.BOLD, 13));
		rbscooter.getLabel().setBounds(36, 0, 199, 62);
		rbscooter.getRadioButton().setBounds(6, 0, 28, 23);
		rbscooter.setOpaque(false);
		rbscooter.setText("Scooter");
		rbscooter.setIcon(new ImageIcon(url));
		rbscooter.setLayout(null);
		buttonGroup.add(rbscooter.getRadioButton());
		rbscooter.addActionListener(new eventListener());
		
		rb95.addActionListener(new eventListener());
		rbdiesel.addActionListener(new eventListener());
		
		btnStartFuel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FuelDialog dialog = new FuelDialog(40,(float)5.8);
				dialog.setVisible(true);
			}
		});
		
	}
	
	private class eventListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == rb95.getRadioButton())
			{
				fuelSelected = 1;
				lblPrice.setText(actions.getFuelPrice(fuelSelected));
			}
			
			
			if(e.getSource() == rbdiesel.getRadioButton())
			{
				fuelSelected = 2;
				lblPrice.setText(actions.getFuelPrice(fuelSelected));
			}
			
			
			if(e.getSource() == rbscooter.getRadioButton())
			{
				fuelSelected = 3;
				lblPrice.setText(actions.getFuelPrice(fuelSelected));
			}
		}
		
	}

	@Override
	public void getInput(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void addStation(String st)
	{
		stationModel.addElement(st);
	}
}
