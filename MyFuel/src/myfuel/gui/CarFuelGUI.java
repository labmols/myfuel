package myfuel.gui;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;

import myfuel.GUIActions.CarFuelActions;
import myfuel.client.BackMainMenu;
import myfuel.client.Fuel;

import javax.swing.DefaultComboBoxModel;

import java.awt.event.ActionListener;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;

import java.awt.Font;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JTextPane;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Car Fuel User Interface
 * @author Maor
 *
 */
@SuppressWarnings("serial")
public class CarFuelGUI extends SuperGUI {
	
	/**
	 * Quantity Text Field.
	 */
	private JTextField LimitText;
	/**
	 * GUI Controller object.
	 */
	private CarFuelActions actions;
	/**
	 * Stations ComboBox model(used for handle elements functions).
	 */
	private DefaultComboBoxModel<String> stationModel;
	/**
	 * Cars ComboBox model(used for handle elements functions).
	 */
	private DefaultComboBoxModel<Integer> carModel;
	/**
	 * 95 Fuel type pump Radio Button.
	 */
	private XRadioButton rb95;
	/**
	 *  Scooter Fuel type pump Radio Button.
	 */
	private XRadioButton rbscooter;
	/**
	 *  Diesel Fuel type pump Radio Button.
	 */
	private XRadioButton rbdiesel;
	/**
	 * Current fuel selected value (1-95/2-Diesel/3-Scooter).
	 */
	private int fuelSelected;
	/**
	 * 95 Fuel type price Label
	 */
	private JLabel lblp95;
	/**
	 * Diesel Fuel type price Label
	 */
	private JLabel lblpdiesel;
	/**
	 * Scooter Fuel type price Label
	 */
	private JLabel lblpscooter;
	/**
	 * Cars ComboBox , contain all the customer cars.
	 */
	private JComboBox<Integer> carCB;
	/**
	 * Stations ComboBox, contain all the available Stations.
	 */
	private JComboBox <String> stationCombo;
	private JTextField dName;
	private JLabel totalPrice;
	private JLabel sModelDisc;
	private JLabel promDisc;

	/**
	 * Create new Car Fuel user interface.
	 * @param actions - Car Fuel GUI Controller.
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
		panel2.setBounds(0, 52, 596, 388);
		panel2.setOpaque(false);
		panel.add(panel2);
		panel2.setLayout(null);
		
		
		JLabel lblChooseStation = new JLabel("Choose Station:");
		lblChooseStation.setBounds(33, 9, 99, 16);
		panel2.add(lblChooseStation);
		lblChooseStation.setBackground(Color.RED);
		
		stationCombo = new JComboBox<String>();
		stationModel = new DefaultComboBoxModel<String>();
		stationCombo.setModel(stationModel);
		stationCombo.setBounds(134, 5, 109, 27);
		panel2.add(stationCombo);
		
		JLabel lblChooseFuelPump = new JLabel("Choose Fuel Pump:");
		lblChooseFuelPump.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));
		lblChooseFuelPump.setBounds(35, 44, 130, 16);
		panel2.add(lblChooseFuelPump);
		
		
		
		
		 java.net.URL url = getClass().getResource("/fuel.png");

		rb95 = new XRadioButton();
		rb95.getLabel().setFont(new Font("Lucida Grande", Font.BOLD, 14));
		rb95.getLabel().setBounds(34, 0, 108, 120);
		rb95.getRadioButton().setBounds(6, 0, 28, 23);
		rb95.getRadioButton().addActionListener(new eventListener());
		rb95.setOpaque(false);
		rb95.setBounds(35, 64, 148, 125);
		rb95.setText("95");
	
		rb95.setIcon(new ImageIcon(url));
		panel2.add(rb95);
		rb95.setLayout(null);

		
		rbdiesel = new XRadioButton();
		rbdiesel.getRadioButton().addActionListener(new eventListener());
		rbdiesel.getLabel().setFont(new Font("Lucida Grande", Font.BOLD, 14));
		rbdiesel.getLabel().setBounds(39, 0, 145, 119);
		rbdiesel.getRadioButton().setBounds(6, 0, 28, 23);
		rbdiesel.setOpaque(false);
		rbdiesel.setBounds(186, 64, 183, 125);
		rbdiesel.setText("Diesel");
		rbdiesel.setIcon(new ImageIcon(url));
		panel2.add(rbdiesel);
		rbdiesel.setLayout(null);
		
		 
		LimitText = new JTextField();
		LimitText.setBounds(362, 201, 61, 26);
		panel2.add(LimitText);
		LimitText.setColumns(10);
		
		JButton btnStartFuel = new JButton("Start Fueling");
		btnStartFuel.setBounds(237, 321, 117, 29);
		panel2.add(btnStartFuel);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(rb95.getRadioButton());
		
		JLabel label_2 = new JLabel("Price:");
		label_2.setBounds(64, 109, 78, 16);
		rb95.add(label_2);
		buttonGroup.add(rbdiesel.getRadioButton());
		
		JLabel label = new JLabel("Price:");
		label.setBounds(88, 109, 78, 16);
		rbdiesel.add(label);
		
		
		
		rbscooter = new XRadioButton();
		rbscooter.getRadioButton().addActionListener(new eventListener());
		rbscooter.setBounds(362, 64, 164, 125);
		panel2.add(rbscooter);
		rbscooter.getLabel().setFont(new Font("Lucida Grande", Font.BOLD, 14));
		rbscooter.getLabel().setBounds(36, 0, 141, 125);
		rbscooter.getRadioButton().setBounds(6, 0, 28, 23);
		rbscooter.setOpaque(false);
		rbscooter.setText("Scooter");
		rbscooter.setIcon(new ImageIcon(url));
		rbscooter.setLayout(null);
		buttonGroup.add(rbscooter.getRadioButton());
		
		JLabel label_1 = new JLabel("Price:");
		label_1.setBounds(80, 109, 78, 16);
		rbscooter.add(label_1);
		
		lblpscooter = new JLabel("");
		lblpscooter.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblpscooter.setForeground(Color.WHITE);
		lblpscooter.setBounds(116, 109, 61, 16);
		rbscooter.add(lblpscooter);
		
		lblp95 = new JLabel("");
		lblp95.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblp95.setForeground(Color.WHITE);
		lblp95.setBounds(134, 173, 61, 16);
		panel2.add(lblp95);
		
		lblpdiesel = new JLabel("");
		lblpdiesel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblpdiesel.setForeground(Color.WHITE);
		lblpdiesel.setBounds(308, 173, 61, 16);
		panel2.add(lblpdiesel);
		
		JLabel lblChooseCar = new JLabel("Choose Car:");
		lblChooseCar.setBackground(Color.RED);
		lblChooseCar.setBounds(276, 9, 99, 16);
		panel2.add(lblChooseCar);
		
		carCB = new JComboBox<Integer>();
		carModel = new DefaultComboBoxModel<Integer>();
		carCB.setModel(carModel);
		carCB.setBounds(358, 5, 109, 27);
		panel2.add(carCB);
		
		JLabel lblLimitBy = new JLabel("Limit By: ");
		lblLimitBy.setBounds(175, 206, 68, 16);
		panel2.add(lblLimitBy);
		
		JComboBox <String> comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Amount", "Price"}));
		comboBox.setBounds(237, 201, 108, 27);
		panel2.add(comboBox);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(175, 234, 242, 75);
		panel2.add(panel);
		panel.setLayout(null);
		
		JLabel lblPromotion = new JLabel("Promotion : ");
		lblPromotion.setBounds(6, 30, 77, 16);
		panel.add(lblPromotion);
		
		JLabel lblSaleModelDiscount = new JLabel("Sale Model Discount: ");
		lblSaleModelDiscount.setBounds(6, 6, 142, 16);
		panel.add(lblSaleModelDiscount);
		
		JLabel lblYourPricefor = new JLabel("Your Price (For Liter): ");
		lblYourPricefor.setBounds(6, 54, 142, 16);
		panel.add(lblYourPricefor);
		
		sModelDisc = new JLabel("");
		sModelDisc.setBounds(146, 6, 61, 16);
		panel.add(sModelDisc);
		
		promDisc = new JLabel("");
		promDisc.setBounds(87, 30, 61, 16);
		panel.add(promDisc);
		
		totalPrice = new JLabel("");
		totalPrice.setBounds(146, 54, 61, 16);
		panel.add(totalPrice);
		
		JLabel lblDriverName = new JLabel("Driver Name: ");
		lblDriverName.setBounds(276, 37, 99, 16);
		panel2.add(lblDriverName);
		
		dName = new JTextField();
		dName.setEnabled(false);
		dName.setBounds(362, 32, 86, 26);
		panel2.add(dName);
		dName.setColumns(10);

	

		
		btnStartFuel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FuelDialog dialog = new FuelDialog(40,(float)5.8);
				dialog.setVisible(true);
			}
		});
		
		
	}
	
	/**
	 * This Class is used for components events handling.
	 *
	 */
	private class eventListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			getInput(e);
			if(e.getSource() == rb95.getRadioButton())
			{
				totalPrice.setText(""+new DecimalFormat("##.##").format(actions.getPrice(Fuel.Fuel95ID))+" NIS");
				//promDisc.setText(text);
			}
			
			if(e.getSource() == rbscooter.getRadioButton())
				totalPrice.setText(""+new DecimalFormat("##.##").format(actions.getPrice(Fuel.FuelScooter))+" NIS");
			
			if(e.getSource() == rbdiesel.getRadioButton())
				totalPrice.setText(""+new DecimalFormat("##.##").format(actions.getPrice(Fuel.FuelDiesel))+" NIS");
		}

		

		
	}
	
	/**
	 * Handle User input.
	 */
	@Override
	public void getInput(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == rb95.getRadioButton())
			fuelSelected = 1;
			
		if(e.getSource() == rbdiesel.getRadioButton())
			fuelSelected = 2;
		
		if(e.getSource() == rbscooter.getRadioButton())
			fuelSelected = 3;
		
	}
	
	/**
	 * Set all price labels to the current prices from DB.
	 * @param fuels - List of all fuels prices.
	 */
	public void setInfo(float modelDisc, ArrayList <Fuel> fuels)
	{
				lblp95.setText(fuels.get(0).getMaxPrice()+"NIS");
				lblpdiesel.setText(fuels.get(1).getMaxPrice()+"NIS");
				lblpscooter.setText(fuels.get(2).getMaxPrice()+"NIS");
				sModelDisc.setText(new DecimalFormat("##.##").format(modelDisc) + "%");
				
	}
	

	
	/**
	 * Add Station to the Stations ComboBox.
	 * @param st - The Station name String.
	 */
	public void addStation(String st)
	{
		stationModel.addElement(st);
	}

	/**
	 * Add new Car to the Cars ComboBox.
	 * @param cid - The Car ID number.
	 */
	public void addCar(int cid) {
		
		carModel.addElement(cid);
	}
}
