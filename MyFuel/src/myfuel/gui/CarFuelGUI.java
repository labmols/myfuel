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
import java.util.ArrayList;

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
	private JTextField qtyText;
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
		panel2.setBounds(0, 58, 596, 382);
		panel2.setOpaque(false);
		panel.add(panel2);
		panel2.setLayout(null);
		
		
		JLabel lblChooseStation = new JLabel("Choose Station:");
		lblChooseStation.setBounds(35, 9, 99, 16);
		panel2.add(lblChooseStation);
		lblChooseStation.setBackground(Color.RED);
		
		stationCombo = new JComboBox<String>();
		stationModel = new DefaultComboBoxModel<String>();
		stationCombo.setModel(stationModel);
		stationCombo.setBounds(146, 5, 109, 27);
		panel2.add(stationCombo);
		
		JLabel lblChooseFuelPump = new JLabel("Choose Fuel Pump:");
		lblChooseFuelPump.setBounds(35, 68, 130, 16);
		panel2.add(lblChooseFuelPump);
		
		
		
		
		 java.net.URL url = getClass().getResource("/fuel.png");

		rb95 = new XRadioButton();
		rb95.getLabel().setFont(new Font("Lucida Grande", Font.BOLD, 14));
		rb95.getLabel().setBounds(34, 0, 108, 120);
		rb95.getRadioButton().setBounds(6, 0, 28, 23);
		
		rb95.setOpaque(false);
		rb95.setBounds(35, 96, 148, 125);
		rb95.setText("95");
	
		rb95.setIcon(new ImageIcon(url));
		panel2.add(rb95);
		rb95.setLayout(null);

		
		rbdiesel = new XRadioButton();
		rbdiesel.getLabel().setFont(new Font("Lucida Grande", Font.BOLD, 14));
		rbdiesel.getLabel().setBounds(39, 0, 145, 119);
		rbdiesel.getRadioButton().setBounds(6, 0, 28, 23);
		rbdiesel.setOpaque(false);
		rbdiesel.setBounds(186, 96, 183, 125);
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
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(rb95.getRadioButton());
		
		JLabel label_2 = new JLabel("Price:");
		label_2.setBounds(64, 109, 78, 16);
		rb95.add(label_2);
		buttonGroup.add(rbdiesel.getRadioButton());
		
		JLabel label = new JLabel("Price:");
		label.setBounds(88, 109, 78, 16);
		rbdiesel.add(label);
		
		JLabel lblPrice2 = new JLabel("");
		lblPrice2.setForeground(Color.RED);
		lblPrice2.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));
		lblPrice2.setBounds(374, 270, 68, 16);
		panel2.add(lblPrice2);
		
		
		
		rbscooter = new XRadioButton();
		rbscooter.setBounds(362, 96, 164, 125);
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
		lblpscooter.setForeground(Color.WHITE);
		lblpscooter.setBounds(116, 109, 61, 16);
		rbscooter.add(lblpscooter);
		
		lblp95 = new JLabel("");
		lblp95.setForeground(Color.WHITE);
		lblp95.setBounds(134, 205, 61, 16);
		panel2.add(lblp95);
		
		lblpdiesel = new JLabel("");
		lblpdiesel.setForeground(Color.WHITE);
		lblpdiesel.setBounds(308, 205, 61, 16);
		panel2.add(lblpdiesel);
		
		JLabel lblChooseCar = new JLabel("Choose Car:");
		lblChooseCar.setBackground(Color.RED);
		lblChooseCar.setBounds(35, 41, 99, 16);
		panel2.add(lblChooseCar);
		
		carCB = new JComboBox<Integer>();
		carModel = new DefaultComboBoxModel<Integer>();
		carCB.setModel(carModel);
		carCB.setBounds(146, 37, 109, 27);
		panel2.add(carCB);
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
	
	/**
	 * This Class is used for components events handling.
	 *
	 */
	private class eventListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			getInput(e);
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
	public void setPrices(ArrayList <Fuel> fuels)
	{
				lblp95.setText(fuels.get(1).getMaxPrice()+"₪");
				lblpdiesel.setText(fuels.get(2).getMaxPrice()+"₪");
				lblpscooter.setText(fuels.get(3).getMaxPrice()+"₪");
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
