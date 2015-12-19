package myfuel.gui;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JInternalFrame;
import javax.swing.JTextField;
import javax.swing.JButton;

import myfuel.GUIActions.UpdateDetailsActions;
import myfuel.client.Car;
import myfuel.client.Customer;
import myfuel.client.Station;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.Font;
import java.util.ArrayList;


public class UpdateUserDetailsGUI extends SuperGUI {
	
	
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
	private JComboBox<Integer> carsCB;
	private JComboBox<String> accessCB;
	private JPanel panel3;
	private JComboBox<String> typeCB;
	private JButton sAdd;
	private JButton btnRemove;
	private JButton btnConfirmUpdate;
	private JComboBox<String> fuelCB;
	private JButton carAdd;
	private JLabel lblStation;
	int access;
	private JComboBox<String> stationCB;
	/**
	 * Create the frame.
	 */
	public UpdateUserDetailsGUI(UpdateDetailsActions actions) {
		
		lblTitle.setBounds(220, 6, 144, 23);
		lblTitle.setText("Update Details");
		this.actions = actions;
		setContentPane(contentPane);
		
		btnConfirmUpdate = new JButton("Confirm Update");
		btnConfirmUpdate.setBounds(68, 395, 130, 29);
		btnConfirmUpdate.addActionListener(new eventListener());
		panel.add(btnConfirmUpdate);
		
		JPanel panel2 = new JPanel();
		panel2.setBackground(Color.WHITE);
		panel2.setBounds(30, 55, 560, 341);
		panel2.setOpaque(false);
		panel.add(panel2);
		panel2.setLayout(null);
		
		fnameText = new JTextField();
		fnameText.setBounds(88, 36, 141, 26);
		panel2.add(fnameText);
		fnameText.setColumns(10);
		
		lnameText = new JTextField();
		lnameText.setBounds(88, 71, 141, 26);
		panel2.add(lnameText);
		lnameText.setColumns(10);
		
		addText = new JTextField();
		addText.setBounds(88, 109, 141, 26);
		panel2.add(addText);
		addText.setColumns(10);
		
		emailText = new JTextField();
		emailText.setBounds(88, 185, 141, 26);
		panel2.add(emailText);
		emailText.setColumns(10);
		
		CCText = new JTextField();
		CCText.setBounds(88, 147, 141, 26);
		panel2.add(CCText);
		CCText.setColumns(10);
		
		JLabel lblUserName = new JLabel("First Name:");
		lblUserName.setBounds(6, 41, 78, 16);
		panel2.add(lblUserName);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setBounds(6, 76, 78, 16);
		panel2.add(lblLastName);
		
		JLabel lblAddress = new JLabel("Address: ");
		lblAddress.setBounds(6, 114, 78, 16);
		panel2.add(lblAddress);
		
		JLabel lblEmail = new JLabel("E-mail: ");
		lblEmail.setBounds(6, 190, 78, 16);
		panel2.add(lblEmail);
		
		JLabel lblCreditCard = new JLabel("Credit Card: ");
		lblCreditCard.setBounds(6, 152, 91, 16);
		panel2.add(lblCreditCard);
		
		JLabel lblAccesstype = new JLabel("Access Type:");
		lblAccesstype.setBounds(259, 65, 91, 16);
		panel2.add(lblAccesstype);
		
		accessCB = new JComboBox<String>();
		accessCB.addActionListener(new eventListener());
		accessCB.setModel(new DefaultComboBoxModel<String>(new String[] {"One Station", "Few Stations"}));
		accessCB.setBounds(348, 61, 132, 27);
		panel2.add(accessCB);
		
		JLabel lblSaleModel = new JLabel("Sale Model:");
		lblSaleModel.setBounds(6, 227, 91, 16);
		panel2.add(lblSaleModel);
		
		saleCB = new JComboBox<String>();
		saleCB.setModel(new DefaultComboBoxModel<String>(new String[] {"Occassional", "Monthly"}));
		saleCB.setBounds(88, 223, 149, 27);
		panel2.add(saleCB);
		
		JLabel lblNewLabel = new JLabel("Your Stations: ");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel.setBounds(241, 35, 113, 26);
		panel2.add(lblNewLabel);
		
		JLabel lblCars = new JLabel("Cars: ");
		lblCars.setBounds(259, 274, 48, 16);
		panel2.add(lblCars);
		
		carsCB = new JComboBox<Integer>();
		carModel= new DefaultComboBoxModel<Integer>();
		carsCB.setModel(carModel);
		carsCB.setBounds(301, 270, 132, 27);
		panel2.add(carsCB);
		
		JLabel lblYourCars = new JLabel("Add Car:");
		lblYourCars.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 14));
		lblYourCars.setBounds(259, 152, 76, 21);
		panel2.add(lblYourCars);
		
		JLabel lblFuelType = new JLabel("Fuel Type:");
		lblFuelType.setBounds(259, 208, 86, 16);
		panel2.add(lblFuelType);
		
		fuelCB = new JComboBox<String>();
		fuelCB.setModel(new DefaultComboBoxModel<String>(new String[] {"95\t", "Diesel", "Scooter"}));
		fuelCB.setBounds(357, 204, 132, 27);
		panel2.add(fuelCB);
		
		btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new eventListener());
		btnRemove.setToolTipText("Remove this Car");
		btnRemove.setBounds(439, 269, 91, 29);
		panel2.add(btnRemove);
		
		JLabel lblType = new JLabel("Type:");
		lblType.setBounds(6, 269, 91, 16);
		panel2.add(lblType);
		
		typeCB = new JComboBox<String>();
		typeCB.setModel(new DefaultComboBoxModel<String>(new String[] {"Private", "Company"}));
		typeCB.setBounds(88, 265, 149, 27);
		panel2.add(typeCB);
		
		JLabel lblCarId = new JLabel("Car ID:");
		lblCarId.setBounds(266, 180, 57, 16);
		panel2.add(lblCarId);
		
		cidText = new JTextField();
		cidText.setBounds(357, 175, 127, 26);
		panel2.add(cidText);
		cidText.setColumns(10);
		
		panel3 = new JPanel();
		panel3.setOpaque(false);
		panel3.setBounds(210, 92, 315, 62);
		panel2.add(panel3);
		panel3.setLayout(null);
		
		stationCB = new JComboBox<String>();
		stationModel= new DefaultComboBoxModel<String>();
		stationCB.setModel(stationModel);
		stationCB.setBounds(140, 6, 132, 27);
		panel3.add(stationCB);
		
		lblStation = new JLabel("Add Station:");
		lblStation.setBounds(35, 10, 115, 16);
		panel3.add(lblStation);
		
		sAdd = new JButton("Add");
		sAdd.setBounds(223, 33, 86, 29);
		panel3.add(sAdd);
		sAdd.setToolTipText("Add New Station");
		
		carAdd = new JButton("Add");
		carAdd.setToolTipText("Add New Station");
		carAdd.addActionListener(new eventListener());
		carAdd.setBounds(439, 235, 86, 29);
		panel2.add(carAdd);
		
		
	
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
			lblStation.setText("Change Station: ");
			sAdd.setText("Change");
		}
		else{
			access=1;
			lblStation.setText("Add Station: ");
			sAdd.setText("Add");
		}
		for(Station s: stations){
			stationModel.addElement(s.getName());
		}
		
	}
	
	private class eventListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			getInput(e);
		
	}
	}

	@Override
	public void getInput(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()== accessCB)
			if(accessCB.getSelectedIndex()==0) {
				access=0;
				lblStation.setText("Change Station: ");
				sAdd.setText("Change");
			}
			else{
				access=1;
				lblStation.setText("Add Station: ");
				sAdd.setText("Add");
			}
		
		if(e.getSource()==carAdd){
			actions.verifyCar(cidText.getText().toString(),fuelCB.getSelectedIndex()+1);
		}
		
		if(e.getSource() == btnConfirmUpdate)
		{
			actions.verifyDetails(fnameText.getText(), lnameText.getText(), emailText.getText(),
					addText.getText(), CCText.getText(), typeCB.getSelectedIndex(), saleCB.getSelectedIndex(), accessCB.getSelectedIndex());
		}
		
		if(e.getSource()==btnRemove)
		{
			actions.removeCar((Integer)carsCB.getSelectedItem(),carsCB.getSelectedIndex());
		}
	}
}



