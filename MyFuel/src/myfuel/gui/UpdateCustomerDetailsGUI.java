package myfuel.gui;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.ItemSelectable;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JLabel;
import javax.swing.JInternalFrame;
import javax.swing.JTextField;
import javax.swing.JButton;

import myfuel.GUIActions.UpdateDetailsActions;
import myfuel.client.BackMainMenu;
import myfuel.client.Car;
import myfuel.client.Customer;
import myfuel.client.Station;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.Font;
import java.util.ArrayList;


public class UpdateCustomerDetailsGUI extends SuperGUI {
	
	
	private UpdateDetailsActions actions;
	private JTextField fnameText;
	private JTextField lnameText;
	private JTextField addText;
	private JTextField emailText;
	private JTextField CCText;
	private JTextField cidText;
	private JComboBox<String> saleCB;
	private DefaultComboBoxModel<Integer> carModel;
	private DefaultComboBoxModel<String> stationModel;
	private DefaultComboBoxModel<String> stationModel2;
	private JComboBox<Integer> carsCB;
	private JComboBox<String> accessCB;
	private JComboBox<String> typeCB;
	private JButton sAdd;
	private JButton btnRemove;
	private JButton btnConfirmUpdate;
	private JComboBox<String> fuelCB;
	private JButton carAdd;
	private JLabel lblStation;
	int access;
	private JComboBox<String> stationCB;
	private JButton btnChange;
	private JComboBox<String> stationCB2;
	/**
	 * Create the frame.
	 */
	public UpdateCustomerDetailsGUI(UpdateDetailsActions actions) {
		
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
		fnameText.setBounds(111, 22, 141, 26);
		panel2.add(fnameText);
		fnameText.setColumns(10);
		
		lnameText = new JTextField();
		lnameText.setBounds(111, 57, 141, 26);
		panel2.add(lnameText);
		lnameText.setColumns(10);
		
		addText = new JTextField();
		addText.setBounds(111, 95, 141, 26);
		panel2.add(addText);
		addText.setColumns(10);
		
		emailText = new JTextField();
		emailText.setBounds(111, 171, 141, 26);
		panel2.add(emailText);
		emailText.setColumns(10);
		
		CCText = new JTextField();
		CCText.setBounds(111, 133, 141, 26);
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
		accessCB.addItemListener(new ItemLEvent());
		accessCB.setModel(new DefaultComboBoxModel<String>(new String[] {"One Station", "Few Stations"}));
		accessCB.setBounds(396, 27, 132, 27);
		panel2.add(accessCB);
		
		JLabel lblSaleModel = new JLabel("Sale Model:");
		lblSaleModel.setBounds(29, 213, 91, 16);
		panel2.add(lblSaleModel);
		
		saleCB = new JComboBox<String>();
		saleCB.setModel(new DefaultComboBoxModel<String>(new String[] {"Occassional", "Monthly"}));
		saleCB.setBounds(103, 209, 149, 27);
		panel2.add(saleCB);
		
		JLabel lblNewLabel = new JLabel("Add/Remove Station:");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel.setBounds(264, 6, 169, 26);
		panel2.add(lblNewLabel);
		
		JLabel lblCars = new JLabel("Cars: ");
		lblCars.setBounds(303, 254, 48, 16);
		panel2.add(lblCars);
		
		carsCB = new JComboBox<Integer>();
		carModel= new DefaultComboBoxModel<Integer>();
		carsCB.setModel(carModel);
		carsCB.setBounds(363, 250, 132, 27);
		panel2.add(carsCB);
		
		JLabel lblYourCars = new JLabel("Add/Remove Car:");
		lblYourCars.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 14));
		lblYourCars.setBounds(269, 160, 137, 21);
		panel2.add(lblYourCars);
		
		JLabel lblFuelType = new JLabel("Fuel Type:");
		lblFuelType.setBounds(286, 218, 86, 16);
		panel2.add(lblFuelType);
		
		fuelCB = new JComboBox<String>();
		fuelCB.setModel(new DefaultComboBoxModel<String>(new String[] {"95\t", "Diesel", "Scooter"}));
		fuelCB.setBounds(363, 214, 132, 27);
		panel2.add(fuelCB);
		
		btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new eventListener());
		btnRemove.setToolTipText("Remove this Car");
		btnRemove.setBounds(501, 249, 78, 29);
		panel2.add(btnRemove);
		
		JLabel lblType = new JLabel("Type:");
		lblType.setBounds(29, 255, 91, 16);
		panel2.add(lblType);
		
		typeCB = new JComboBox<String>();
		typeCB.setModel(new DefaultComboBoxModel<String>(new String[] {"Private", "Company"}));
		typeCB.setBounds(103, 251, 149, 27);
		panel2.add(typeCB);
		
		JLabel lblCarId = new JLabel("Car ID:");
		lblCarId.setBounds(294, 181, 57, 16);
		panel2.add(lblCarId);
		
		cidText = new JTextField();
		cidText.setBounds(363, 176, 127, 26);
		panel2.add(cidText);
		cidText.setColumns(10);
		stationModel= new DefaultComboBoxModel<String>();
		stationModel2= new DefaultComboBoxModel<String>();
		
		carAdd = new JButton("Add");
		carAdd.setToolTipText("Add New Station");
		carAdd.addActionListener(new eventListener());
		carAdd.setBounds(501, 213, 78, 29);
		panel2.add(carAdd);
		
		sAdd = new JButton("Add");
		sAdd.setBounds(403, 126, 71, 29);
		panel2.add(sAdd);
		sAdd.addActionListener(new eventListener());
		sAdd.setToolTipText("Add New Station");
		
		btnChange = new JButton("Change");
		btnChange.setBounds(301, 126, 71, 29);
		btnChange.addActionListener(new eventListener());
		panel2.add(btnChange);
		btnChange.setToolTipText("Add New Station");
		
		stationCB = new JComboBox<String>();
		stationCB.setBounds(398, 62, 132, 27);
		panel2.add(stationCB);
		stationCB.setModel(stationModel);
		
		lblStation = new JLabel("Your Stations:");
		lblStation.setBounds(291, 67, 115, 16);
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
		showUserDetails(actions.getUserDetails(),actions.getStations());
		
		
		
		
	}
	
	public void showUserDetails(Customer user, ArrayList<Station> stations){
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
		saleCB.setSelectedItem(user.getSmodel());
		typeCB.setSelectedItem(user.getToc());
		if(accessCB.getSelectedIndex()==0) {
			access=0;
			sAdd.setEnabled(false);
		}
		else{
			access=1;
			sAdd.setEnabled(true);
		}
		
		for(Station s: stations){
			if(user.getStations().contains(s.getsid()))
			stationModel.addElement(s.getName());
			stationModel2.addElement(s.getName());
			
		}
		
		
		
	}
	
	private class eventListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			getInput(e);
		
	}
	}
	
	private class ItemLEvent implements ItemListener{

		@Override
		public void itemStateChanged(java.awt.event.ItemEvent e) {
			// TODO Auto-generated method stub
			if(e.getStateChange()==ItemEvent.DESELECTED &&  e.getSource() == accessCB)
			{
				
				if(accessCB.getSelectedIndex()==0) {
					
					access=0;
					if(stationModel.getSize() > 1)
					{
					actions.resetAccess();
					showMessage("Now you need to add one station!");
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
			actions.addStation((String)stationCB2.getSelectedItem(),access);
		}
		
		if(e.getSource()==carAdd){
			actions.verifyCar(cidText.getText().toString(),fuelCB.getSelectedIndex()+1);
		}
		
		if(e.getSource() == btnConfirmUpdate)
		{
			actions.verifyDetails(fnameText.getText(), lnameText.getText(), emailText.getText(),
					addText.getText(), CCText.getText(), typeCB.getSelectedIndex(), saleCB.getSelectedIndex(), access);
		}
		
		if(e.getSource()==btnRemove)
		{
			if(carsCB.getItemCount() > 1){
			actions.removeCar((Integer)carsCB.getSelectedItem(),carsCB.getSelectedIndex());
			carModel.removeElementAt(carsCB.getSelectedIndex());
			}
			else  showMessage("You must have at least one car!");
		}
		
		if(e.getSource() == btnChange)
		{
			actions.changeStation((String)stationCB.getSelectedItem(), (String)stationCB2.getSelectedItem());
		}
	}

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
		saleCB.setSelectedItem(user.getSmodel());
		typeCB.setSelectedItem(user.getToc());
		
	
	}
	
	public void updateCB(ArrayList<Integer> userStations,ArrayList<Station> stations)
	{
		stationModel.removeAllElements();
		for(Station s: stations){
			if(userStations.contains(s.getsid()))
			stationModel.addElement(s.getName());
		}
	}
}



