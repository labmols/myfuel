package myfuel.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JPasswordField;

import myfuel.GUIActions.RegisterActions;
import myfuel.client.BackMainMenu;

public class RegisterGUI extends SuperGUI {
	private JTextField idTextField;
	private JTextField fnameTextField;
	private JTextField lnameTextField;
	private JTextField CCTextField;
	private JTextField emailTextField;
	private JPasswordField passTextField;
	private JPasswordField rePassTextField;
	private JTextField cidTextField;
	private JComboBox accessCB;
	private JComboBox stationsCB;
	private JComboBox fuelCB;
	private JComboBox saleModelCB;
	private RegisterActions actions;
	private DefaultComboBoxModel<String> stationModel ;
	private JButton btnAddStation;
	private JButton btnAddCar;
	private JButton btnRegister;
	private JComboBox typeCB;
	private JTextField addressTextField;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public RegisterGUI(RegisterActions actions) {
		this.actions=actions;
		panel.setLocation(0, 0);
		lblTitle.setBounds(238, 6, 94, 22);
		lblTitle.setText("Register");
		this.mainMenu.addActionListener(new BackMainMenu(actions));
		JPanel panel2 = new JPanel();
		panel2.setBounds(52, 40, 232, 205);
		panel.add(panel2);
		panel2.setOpaque(false);
		panel2.setLayout(null);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(73, 27, 25, 16);
		panel2.add(lblId);
		
		idTextField = new JTextField();
		idTextField.setBounds(92, 22, 110, 26);
		panel2.add(idTextField);
		idTextField.setColumns(10);
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setBounds(19, 60, 79, 16);
		panel2.add(lblFirstName);
		
		fnameTextField = new JTextField();
		fnameTextField.setColumns(10);
		fnameTextField.setBounds(92, 55, 110, 26);
		panel2.add(fnameTextField);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setBounds(19, 98, 79, 16);
		panel2.add(lblLastName);
		
		lnameTextField = new JTextField();
		lnameTextField.setColumns(10);
		lnameTextField.setBounds(92, 93, 110, 26);
		panel2.add(lnameTextField);
		
		JLabel label_3 = new JLabel("E-mail:");
		label_3.setBounds(46, 131, 53, 16);
		panel2.add(label_3);
		
		emailTextField = new JTextField();
		emailTextField.setBounds(92, 126, 110, 26);
		panel2.add(emailTextField);
		emailTextField.setColumns(10);
		
		addressTextField = new JTextField();
		addressTextField.setColumns(10);
		addressTextField.setBounds(92, 159, 110, 26);
		panel2.add(addressTextField);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setBounds(33, 164, 66, 16);
		panel2.add(lblAddress);
		
		JPanel panel3 = new JPanel();
		panel3.setLayout(null);
		panel3.setOpaque(false);
		panel3.setBounds(284, 40, 269, 205);
		panel.add(panel3);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(48, 24, 72, 16);
		panel3.add(lblPassword);
		
		JLabel lblRetypePassword = new JLabel("Retype Password:");
		lblRetypePassword.setBounds(6, 57, 109, 16);
		panel3.add(lblRetypePassword);
		
		JLabel lblCreditCard = new JLabel("Credit Card:");
		lblCreditCard.setBounds(41, 95, 79, 16);
		panel3.add(lblCreditCard);
		
		CCTextField = new JTextField();
		CCTextField.setColumns(10);
		CCTextField.setBounds(114, 90, 94, 26);
		panel3.add(CCTextField);
		
		JLabel lblSaleModel = new JLabel("Sale Model:");
		lblSaleModel.setBounds(41, 130, 91, 16);
		panel3.add(lblSaleModel);
		
		saleModelCB = new JComboBox();
		saleModelCB.setModel(new DefaultComboBoxModel(new String[] {"Occassional", "Monthly"}));
		saleModelCB.setBounds(114, 126, 137, 27);
		panel3.add(saleModelCB);
		
		passTextField = new JPasswordField();
		passTextField.setBounds(114, 19, 94, 26);
		panel3.add(passTextField);
		
		rePassTextField = new JPasswordField();
		rePassTextField.setBounds(114, 52, 94, 26);
		panel3.add(rePassTextField);
		
		JLabel lblType = new JLabel("Type: ");
		lblType.setBounds(70, 162, 91, 16);
		panel3.add(lblType);
		
		typeCB = new JComboBox();
		typeCB.setModel(new DefaultComboBoxModel(new String[] {"Private", "Company"}));
		typeCB.setBounds(114, 158, 137, 27);
		panel3.add(typeCB);
		
		JPanel panel4 = new JPanel();
		panel4.setLayout(null);
		panel4.setOpaque(false);
		panel4.setBounds(52, 245, 251, 113);
		panel.add(panel4);
		
		JLabel lblNewLabel = new JLabel("Access Type:");
		lblNewLabel.setBounds(18, 20, 91, 16);
		panel4.add(lblNewLabel);
		
		accessCB = new JComboBox();
		accessCB.setModel(new DefaultComboBoxModel(new String[] {"One Station", "Few Stations"}));
		accessCB.addActionListener(new ButtonListener());
		accessCB.setBounds(104, 16, 141, 27);
		panel4.add(accessCB);
		
		JLabel lblChooseStation = new JLabel("Choose Station:");
		lblChooseStation.setBounds(6, 50, 104, 16);
		panel4.add(lblChooseStation);
		
		stationsCB = new JComboBox();
		stationsCB.setBounds(104, 48, 141, 27);
		panel4.add(stationsCB);
		
		btnAddStation = new JButton("Add Station");
		btnAddStation.setBounds(114, 78, 117, 29);
		btnAddStation.addActionListener(new ButtonListener());
		panel4.add(btnAddStation);
		
		JPanel panel5 = new JPanel();
		panel5.setLayout(null);
		panel5.setOpaque(false);
		panel5.setBounds(302, 245, 251, 113);
		panel.add(panel5);
		
		JLabel lblCarId = new JLabel("Car ID:");
		lblCarId.setBounds(44, 23, 53, 16);
		panel5.add(lblCarId);
		
		fuelCB = new JComboBox();
		fuelCB.setModel(new DefaultComboBoxModel(new String[] {"95", "Diesel", "Scooter"}));
		fuelCB.setBounds(91, 51, 94, 27);
		panel5.add(fuelCB);
		
		btnAddCar = new JButton("Add Car");
		btnAddCar.addActionListener(new ButtonListener());
		btnAddCar.setBounds(68, 78, 117, 29);
		panel5.add(btnAddCar);
		
		cidTextField = new JTextField();
		cidTextField.setColumns(10);
		cidTextField.setBounds(91, 18, 94, 26);
		panel5.add(cidTextField);
		
		JLabel lblFuelType = new JLabel("Fuel Type:");
		lblFuelType.setBounds(25, 55, 72, 16);
		panel5.add(lblFuelType);
		
		btnRegister = new JButton("Register");
		btnRegister.setBounds(238, 389, 117, 29);
		btnRegister.addActionListener(new ButtonListener());
		panel.add(btnRegister);
		setContentPane(contentPane);
		
		
		stationModel= new DefaultComboBoxModel<String>();
		stationsCB.setModel(stationModel);
		
		
		mainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actions.returnToMain();
			}
			
		});

			
		}
	
	
	private class ButtonListener implements ActionListener{

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
			actions.resetStations();
		
		if(e.getSource() == btnAddStation){
			actions.addStation((String)stationModel.getSelectedItem(), accessCB.getSelectedIndex());
		}
		
		if(e.getSource() == btnAddCar){
			actions.addCar(cidTextField.getText().toString(), fuelCB.getSelectedIndex()+1);
		}
		
		if(e.getSource()==btnRegister ){
			actions.verifyDetails(idTextField.getText().toString(), fnameTextField.getText().toString(), lnameTextField.getText().toString(), 
					passTextField.getPassword(),rePassTextField.getPassword(), emailTextField.getText().toString(),addressTextField.getText().toString(),
					CCTextField.getText().toString(),typeCB.getSelectedIndex(), accessCB.getSelectedIndex(),saleModelCB.getSelectedIndex());
		}
	}
	
	public void addStation(String st){
		stationModel.addElement(st);
	}
}
