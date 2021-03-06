package myfuel.GUI;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import myfuel.Entity.Car;
import myfuel.Entity.Customer;
import myfuel.Entity.Network;
import myfuel.Entity.Station;
import myfuel.GUIActions.UpdateDetailsActions;
import myfuel.Tools.BackMainMenu;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.Font;
import java.util.ArrayList;

/**
 * 
 * @author Maor
 *
 */
@SuppressWarnings("serial")
public class UpdateCustomerDetailsGUI extends SuperGUI {
	
	/**
	 * Update details controller object.
	 */
	private UpdateDetailsActions actions;
	/**
	 * Customer First name TextField.
	 */
	private JTextField fnameText;
	/**
	 * Customer Last name TextField.
	 */
	private JTextField lnameText;
	/**
	 * Customer Address TextField.
	 */
	private JTextField addText;
	/**
	 * Customer email TextField.
	 */
	private JTextField emailText;
	/**
	 * Customer Credit Card TextField.
	 */
	private JTextField CCText;
	/**
	 * Added Car ID TextField.
	 */
	private JTextField cidText;
	/**
	 * Sale Model ComboBox.
	 */
	private JComboBox<String> saleCB;
	/**
	 * Customer Cars ComboBox.
	 */
	private DefaultComboBoxModel<Integer> carModel;
	/**
	 * Customer Stations ComboBox model(used for elements functions).
	 */
	private DefaultComboBoxModel<String> stationModel;
	/**
	 * All stations ComboBox model (used for elements functions).
	 */
	private DefaultComboBoxModel<String> stationModel2;
	/**
	 * Customer Cars ComboBox.
	 */
	private JComboBox<Integer> carsCB;
	/**
	 * Customer Access Level ComboBox(One Station, Few Stations).
	 */
	private JComboBox<String> accessCB;
	/**
	 * Add new Station Button.
	 */
	private JButton sAdd;
	/**
	 * Remove station Button.
	 */
	private JButton btnRemove;
	/**
	 * Confirm Update Button.
	 */
	private JButton btnConfirmUpdate;
	/**
	 * Fuel Type ComboBox.
	 */
	private JComboBox<String> fuelCB;
	/**
	 * Add new Car Button.
	 */
	private JButton carAdd;
	/**
	 * Access Level of the Customer (0-Private,1-Company) , changed in ComboBox select item event.
	 */
	int access;
	/**
	 * Customer Stations ComboBox (contains all the customer stations).
	 */
	private JComboBox<String> stationCB;
	/**
	 * Change Station Button.
	 */
	private JButton btnChange;
	/**
	 * All Stations ComboBox (contains all the stations available in the company).
	 */
	private JComboBox<String> stationCB2;

	
	/**
	 * Create new Update Customer Details User Interface.
	 * @param actions - Update Details GUI Controller object.
	 */
	public UpdateCustomerDetailsGUI(UpdateDetailsActions actions) {
		super(actions);
		lblTitle.setBounds(220, 6, 144, 23);
		lblTitle.setText("Update Details");
		this.actions = actions;
		setContentPane(contentPane);
		
		JPanel panel2 = new JPanel();
		panel2.setBackground(Color.WHITE);
		panel2.setBounds(6, 55, 584, 355);
		panel2.setOpaque(false);
		panel.add(panel2);
		panel2.setLayout(null);
		
		fnameText = new JTextField();
		fnameText.setBounds(111, 22, 160, 26);
		panel2.add(fnameText);
		fnameText.setColumns(10);
		
		lnameText = new JTextField();
		lnameText.setBounds(111, 57, 160, 26);
		panel2.add(lnameText);
		lnameText.setColumns(10);
		
		addText = new JTextField();
		addText.setBounds(111, 95, 160, 26);
		panel2.add(addText);
		addText.setColumns(10);
		
		emailText = new JTextField();
		emailText.setBounds(111, 171, 160, 26);
		panel2.add(emailText);
		emailText.setColumns(10);
		
		CCText = new JTextField();
		CCText.setBounds(111, 133, 160, 26);
		panel2.add(CCText);
		CCText.setColumns(10);
		
		JLabel lblUserName = new JLabel("First Name:");
		lblUserName.setBounds(29, 27, 78, 16);
		panel2.add(lblUserName);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setBounds(29, 62, 78, 16);
		panel2.add(lblLastName);
		
		JLabel lblAddress = new JLabel("Address: ");
		lblAddress.setBounds(29, 100, 78, 16);
		panel2.add(lblAddress);
		
		JLabel lblEmail = new JLabel("E-mail: ");
		lblEmail.setBounds(29, 176, 78, 16);
		panel2.add(lblEmail);
		
		JLabel lblCreditCard = new JLabel("Credit Card: ");
		lblCreditCard.setBounds(29, 138, 91, 16);
		panel2.add(lblCreditCard);
		
		JLabel lblAccesstype = new JLabel("Access Type:");
		lblAccesstype.setBounds(301, 31, 91, 16);
		panel2.add(lblAccesstype);
		
		accessCB = new JComboBox<String>();
		accessCB.addItemListener(new eventListener());
		accessCB.setModel(new DefaultComboBoxModel(new String[] {"One Network", "Few Networks"}));
		accessCB.setBounds(396, 27, 132, 27);
		panel2.add(accessCB);
		
		JLabel lblSaleModel = new JLabel("Sale Model:");
		lblSaleModel.setBounds(16, 213, 91, 16);
		panel2.add(lblSaleModel);
		
		saleCB = new JComboBox<String>();
		saleCB.setModel(new DefaultComboBoxModel<String>(new String[] {"Occasional", "Monthly- One Car", "Monthly - Few Cars", "Fully Monthly - One Car"}));
		saleCB.setBounds(111, 209, 181, 27);
		panel2.add(saleCB);
		
		JLabel lblNewLabel = new JLabel("Add/Remove Station:");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel.setBounds(274, 6, 169, 26);
		panel2.add(lblNewLabel);
		
		JLabel lblCars = new JLabel("Cars: ");
		lblCars.setBounds(20, 253, 48, 16);
		panel2.add(lblCars);
		
		carsCB = new JComboBox<Integer>();
		carModel= new DefaultComboBoxModel<Integer>();
		carsCB.setModel(carModel);
		carsCB.setBounds(94, 249, 132, 27);
		panel2.add(carsCB);
		
		JLabel lblYourCars = new JLabel("Add/Remove Car:");
		lblYourCars.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 14));
		lblYourCars.setBounds(283, 167, 137, 21);
		panel2.add(lblYourCars);
		
		JLabel lblFuelType = new JLabel("Fuel Type:");
		lblFuelType.setBounds(306, 231, 86, 16);
		panel2.add(lblFuelType);
		
		fuelCB = new JComboBox<String>();
		fuelCB.setModel(new DefaultComboBoxModel<String>(new String[] {"95\t", "Diesel", "Scooter"}));
		fuelCB.setBounds(376, 227, 132, 27);
		panel2.add(fuelCB);
		
		btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new eventListener());
		btnRemove.setToolTipText("Remove this Car");
		btnRemove.setBounds(236, 248, 91, 29);
		panel2.add(btnRemove);
		
		JLabel lblCarId = new JLabel("Car ID:");
		lblCarId.setBounds(329, 194, 63, 16);
		panel2.add(lblCarId);
		
		cidText = new JTextField();
		cidText.setBounds(376, 189, 127, 26);
		panel2.add(cidText);
		cidText.setColumns(10);
		stationModel= new DefaultComboBoxModel<String>();
		stationModel2= new DefaultComboBoxModel<String>();
		
		carAdd = new JButton("Add");
		carAdd.setToolTipText("Add New Car");
		carAdd.addActionListener(new eventListener());
		carAdd.setBounds(417, 258, 91, 29);
		panel2.add(carAdd);
		
		sAdd = new JButton("Add");
		sAdd.setBounds(403, 126, 87, 29);
		panel2.add(sAdd);
		sAdd.addActionListener(new eventListener());
		sAdd.setToolTipText("Add New Station");
		
		btnChange = new JButton("Change");
		btnChange.setBounds(286, 126, 86, 29);
		btnChange.addActionListener(new eventListener());
		panel2.add(btnChange);
		btnChange.setToolTipText("Change to another station");
		
		stationCB = new JComboBox<String>();
		stationCB.setBounds(398, 62, 132, 27);
		panel2.add(stationCB);
		stationCB.setModel(stationModel);
		
		JLabel lblStation = new JLabel("Your Networks:");
		lblStation.setBounds(294, 67, 103, 16);
		panel2.add(lblStation);
		
		stationCB2 = new JComboBox<String>();
		stationCB2.setBounds(398, 94, 132, 27);
		panel2.add(stationCB2);
		stationCB2.setModel(stationModel2);
		
		JLabel lblChangeTo = new JLabel("Change To/Add:");
		lblChangeTo.setBounds(281, 101, 121, 16);
		panel2.add(lblChangeTo);
		
		btnConfirmUpdate = new JButton("Confirm Update");
		btnConfirmUpdate.setBounds(216, 320, 130, 29);
		panel2.add(btnConfirmUpdate);
		btnConfirmUpdate.addActionListener(new eventListener());
		stationCB2.addActionListener(new eventListener());
		
		
		this.mainMenu.addActionListener(new BackMainMenu(actions));
		showUserDetails(actions.getUserDetails(),actions.getNetworks());
		
		
		
		
	}
	
	/**
	 * Load all current customer details from DB into the interface.
	 * @param user - Customer details object.
	 * @param stations - All stations list.
	 */
	public void showUserDetails(Customer user, ArrayList<Network> networks){
		fnameText.setText(user.getFname());
		lnameText.setText(user.getLname());
		addText.setText(user.getAddress());
		emailText.setText(user.getEmail());
		CCText.setText(user.getCnumber());
		for(Car car: user.getCars())
		{
			carModel.addElement(car.getcid());
		}
		
		accessCB.setSelectedIndex(user.getAtype());
		saleCB.setSelectedIndex(user.getSmodel()-1);
		if(accessCB.getSelectedIndex()==0) {
			access=0;
			sAdd.setEnabled(false);
		}
		else{
			access=1;
			sAdd.setEnabled(true);
		}
		
		for(Network s: networks){
			if(user.getStations().contains(s.getNid()))
			stationModel.addElement(s.getName());
			stationModel2.addElement(s.getName());
			
		}
		
		
		
	}
	/**
	 * This class used for handling all components events.
	 *
	 */
	private class eventListener implements ActionListener,ItemListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			getInput(e);
		
	}

		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			if(e.getStateChange()==ItemEvent.DESELECTED &&  e.getSource() == accessCB)
			{
				
				if(accessCB.getSelectedIndex()==0) {
					
					access=0;
					if(stationModel.getSize() > 1)
					{
					actions.resetAccess();
					showOKMessage("Now you need to add one station!");
					}
					
				}
				else{
					access=1;
					sAdd.setEnabled(true);
				}
			}
		}
	}
	
	

	@Override
	public void getInput(ActionEvent e) {
		// TODO Auto-generated method stub
		
		
		if(e.getSource() == sAdd)
		{
			actions.addNetwork((String)stationCB2.getSelectedItem(),access);
		}
		
		if(e.getSource()==carAdd){
			actions.verifyCar(cidText.getText().toString(),fuelCB.getSelectedIndex()+1);
		}
		
		if(e.getSource() == btnConfirmUpdate)
		{
			actions.verifyDetails(fnameText.getText(), lnameText.getText(), emailText.getText(),
					addText.getText(), CCText.getText(), saleCB.getSelectedIndex()+1, access);
		}
		
		if(e.getSource()==btnRemove)
		{
			if(carsCB.getItemCount() > 1){
			actions.removeCar((Integer)carsCB.getSelectedItem(),carsCB.getSelectedIndex());
			carModel.removeElementAt(carsCB.getSelectedIndex());
			}
			else  showErrorMessage("You must have at least one car!");
		}
		
		if(e.getSource() == btnChange)
		{
			actions.changeStation((String)stationCB.getSelectedItem(), (String)stationCB2.getSelectedItem());
		}
	}

	/**
	 * Set all fields according to the customer details before any update.
	 * @param user - The Customer details object.
	 */
	public void clearAll(Customer user) {
		cidText.setText("");
		fnameText.setText(user.getFname());
		lnameText.setText(user.getLname());
		addText.setText(user.getAddress());
		emailText.setText(user.getEmail());
		CCText.setText(user.getCnumber());
		carModel.removeAllElements();
		for(Car car: user.getCars())
		{
			carModel.addElement(car.getcid());
		}
		
		accessCB.setSelectedIndex(user.getAtype());
		saleCB.setSelectedIndex(user.getSmodel()-1);
		
	
	}
	
/**
 * Update Customer stations ComboBox after Add/Remove a station.
 * @param userStations - Current List of the customer stations.
 * @param arrayList - All stations list.
 */
	public void updateStationCB(ArrayList<Integer> userStations,ArrayList<Network> arrayList)
	{
		stationModel.removeAllElements();
		for(Network s: arrayList){
			if(userStations.contains(s.getNid()))
			stationModel.addElement(s.getName());
		}
	}
	
	

	public void updateCarsCB(int cid) {
		// TODO Auto-generated method stub
		
		carModel.addElement(cid);
		}
	
}



