package myfuel.gui;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JPasswordField;

import myfuel.GUIActions.RegisterActions;
import myfuel.client.BackMainMenu;
import java.awt.Font;

/**
 * Register User Interface.
 * @author Maor
 *
 */
@SuppressWarnings("serial")
public class RegisterGUI extends SuperGUI {
/**
 * User ID TextField
 */
	private JTextField idTextField;
/**
 * First Name TextField.
 */
	private JTextField fnameTextField;
/**
 * Last Name TextField.
 */
	private JTextField lnameTextField;
/**
 * Credit Card TextField.
 */
	private JTextField CCTextField;
/**
 * E-Mail TextField.
 */
	private JTextField emailTextField;
/**
 * Password TextField.
 */
	private JPasswordField passTextField;
/**
 * Retype password TextField.
 */
	private JPasswordField rePassTextField;
/**
 * Car ID TextField.
 */
	private JTextField cidTextField;
/**
 * Customer Access Level ComboBox (One network, Few networks).
 */
	private JComboBox<String> accessCB;
/**
 * Current available networks ComboBox.
 */
	private JComboBox<String> networkCB;
/**
 * Fuel Type ComboBox.
 */
	private JComboBox<String> fuelCB;
/**
 * Sale Model ComboBox.
 */
	private JComboBox<String> saleModelCB;
/**
 * Register GUI Controller.
 */
	private RegisterActions actions;
/**
 * Station ComboBox model (used for elements functions).
 */
	private DefaultComboBoxModel<String> networkModel ;
/**
 * Add new station network Button.
 */
	private JButton btnAddNetwork;
/**
 * Add new Car Button.
 */
	private JButton btnAddCar;
/**
 * Register Button(Send register request to DB).
 */
	private JButton btnRegister;
/**
 * Customer type (Private/Company).
 */
	private JComboBox<String> typeCB;
/**
 * Customer Address TextField.
 */
	private JTextField addressTextField;
/**
 * Clear all fields Button.
 */
	private JButton btnClear;


/**
 * Create new Register User Interface.
 * @param actions
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
		lblId.setFont(new Font("Arial", Font.PLAIN, 13));
		lblId.setBounds(73, 27, 25, 16);
		panel2.add(lblId);
		
		idTextField = new JTextField();
		idTextField.setFont(new Font("Arial", Font.PLAIN, 13));
		idTextField.setBounds(92, 22, 110, 26);
		panel2.add(idTextField);
		idTextField.setColumns(10);
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setFont(new Font("Arial", Font.PLAIN, 13));
		lblFirstName.setBounds(19, 60, 79, 16);
		panel2.add(lblFirstName);
		
		fnameTextField = new JTextField();
		fnameTextField.setFont(new Font("Arial", Font.PLAIN, 13));
		fnameTextField.setColumns(10);
		fnameTextField.setBounds(92, 55, 110, 26);
		panel2.add(fnameTextField);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setFont(new Font("Arial", Font.PLAIN, 13));
		lblLastName.setBounds(19, 98, 79, 16);
		panel2.add(lblLastName);
		
		lnameTextField = new JTextField();
		lnameTextField.setFont(new Font("Arial", Font.PLAIN, 13));
		lnameTextField.setColumns(10);
		lnameTextField.setBounds(92, 93, 110, 26);
		panel2.add(lnameTextField);
		
		JLabel label_3 = new JLabel("E-mail:");
		label_3.setFont(new Font("Arial", Font.PLAIN, 13));
		label_3.setBounds(46, 131, 53, 16);
		panel2.add(label_3);
		
		emailTextField = new JTextField();
		emailTextField.setFont(new Font("Arial", Font.PLAIN, 13));
		emailTextField.setBounds(92, 126, 110, 26);
		panel2.add(emailTextField);
		emailTextField.setColumns(10);
		
		addressTextField = new JTextField();
		addressTextField.setFont(new Font("Arial", Font.PLAIN, 13));
		addressTextField.setColumns(10);
		addressTextField.setBounds(92, 159, 110, 26);
		panel2.add(addressTextField);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setFont(new Font("Arial", Font.PLAIN, 13));
		lblAddress.setBounds(33, 164, 66, 16);
		panel2.add(lblAddress);
		
		JPanel panel3 = new JPanel();
		panel3.setLayout(null);
		panel3.setOpaque(false);
		panel3.setBounds(284, 40, 306, 205);
		panel.add(panel3);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Arial", Font.PLAIN, 13));
		lblPassword.setBounds(48, 24, 72, 16);
		panel3.add(lblPassword);
		
		JLabel lblRetypePassword = new JLabel("Retype Password:");
		lblRetypePassword.setFont(new Font("Arial", Font.PLAIN, 13));
		lblRetypePassword.setBounds(6, 57, 109, 16);
		panel3.add(lblRetypePassword);
		
		JLabel lblCreditCard = new JLabel("Credit Card:");
		lblCreditCard.setFont(new Font("Arial", Font.PLAIN, 13));
		lblCreditCard.setBounds(41, 95, 79, 16);
		panel3.add(lblCreditCard);
		
		CCTextField = new JTextField();
		CCTextField.setFont(new Font("Arial", Font.PLAIN, 13));
		CCTextField.setColumns(10);
		CCTextField.setBounds(114, 90, 94, 26);
		panel3.add(CCTextField);
		
		JLabel lblSaleModel = new JLabel("Sale Model:");
		lblSaleModel.setFont(new Font("Arial", Font.PLAIN, 13));
		lblSaleModel.setBounds(41, 130, 91, 16);
		panel3.add(lblSaleModel);
		
		saleModelCB = new JComboBox<String>();
		saleModelCB.setFont(new Font("Arial", Font.PLAIN, 13));
		saleModelCB.setModel(new DefaultComboBoxModel<String>(new String[] {"Occasional", "Monthly- One Car", "Monthly - Few Cars", "Fully Monthly - One Car"}));
		saleModelCB.addItemListener(new ButtonListener());
		saleModelCB.setBounds(114, 126, 186, 27);
		panel3.add(saleModelCB);
		
		passTextField = new JPasswordField();
		passTextField.setFont(new Font("Arial", Font.PLAIN, 13));
		passTextField.setBounds(114, 19, 94, 26);
		panel3.add(passTextField);
		
		rePassTextField = new JPasswordField();
		rePassTextField.setFont(new Font("Arial", Font.PLAIN, 13));
		rePassTextField.setBounds(114, 52, 94, 26);
		panel3.add(rePassTextField);
		
		JLabel lblType = new JLabel("Type: ");
		lblType.setFont(new Font("Arial", Font.PLAIN, 13));
		lblType.setBounds(70, 162, 91, 16);
		panel3.add(lblType);
		
		typeCB = new JComboBox<String>();
		typeCB.setFont(new Font("Arial", Font.PLAIN, 13));
		typeCB.setModel(new DefaultComboBoxModel<String>(new String[] {"Private", "Company"}));
		typeCB.setBounds(114, 158, 186, 27);
		panel3.add(typeCB);
		
		JPanel panel4 = new JPanel();
		panel4.setLayout(null);
		panel4.setOpaque(false);
		panel4.setBounds(52, 245, 251, 113);
		panel.add(panel4);
		
		JLabel lblNewLabel = new JLabel("Access Type:");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNewLabel.setBounds(18, 20, 91, 16);
		panel4.add(lblNewLabel);
		
		accessCB = new JComboBox<String>();
		accessCB.setFont(new Font("Arial", Font.PLAIN, 13));
		accessCB.setModel(new DefaultComboBoxModel<String>(new String[] {"One Station", "Few Stations"}));
		accessCB.addItemListener(new ButtonListener());
		accessCB.setBounds(104, 16, 141, 27);
		panel4.add(accessCB);
		
		JLabel lblChooseStation = new JLabel("Choose Network:");
		lblChooseStation.setFont(new Font("Arial", Font.PLAIN, 13));
		lblChooseStation.setBounds(6, 53, 104, 16);
		panel4.add(lblChooseStation);
		
		networkCB = new JComboBox<String>();
		networkCB.setBounds(104, 48, 141, 27);
		panel4.add(networkCB);
		
		btnAddNetwork = new JButton("Add Station Network");
		btnAddNetwork.setFont(new Font("Arial", Font.PLAIN, 13));
		btnAddNetwork.setBounds(84, 78, 161, 29);
		btnAddNetwork.addActionListener(new ButtonListener());
		panel4.add(btnAddNetwork);
		
		JPanel panel5 = new JPanel();
		panel5.setLayout(null);
		panel5.setOpaque(false);
		panel5.setBounds(302, 245, 251, 113);
		panel.add(panel5);
		
		JLabel lblCarId = new JLabel("Car ID:");
		lblCarId.setFont(new Font("Arial", Font.PLAIN, 13));
		lblCarId.setBounds(37, 18, 53, 16);
		panel5.add(lblCarId);
		
		fuelCB = new JComboBox<String>();
		fuelCB.setFont(new Font("Arial", Font.PLAIN, 13));
		fuelCB.setModel(new DefaultComboBoxModel<String>(new String[] {"95", "Diesel", "Scooter"}));
		fuelCB.setBounds(101, 51, 94, 27);
		panel5.add(fuelCB);
		
		btnAddCar = new JButton("Add Car");
		btnAddCar.setFont(new Font("Arial", Font.PLAIN, 13));
		btnAddCar.addActionListener(new ButtonListener());
		btnAddCar.setBounds(94, 78, 117, 29);
		panel5.add(btnAddCar);
		
		cidTextField = new JTextField();
		cidTextField.setFont(new Font("Arial", Font.PLAIN, 13));
		cidTextField.setColumns(10);
		cidTextField.setBounds(102, 13, 94, 26);
		panel5.add(cidTextField);
		
		JLabel lblFuelType = new JLabel("Fuel Type:");
		lblFuelType.setFont(new Font("Arial", Font.PLAIN, 13));
		lblFuelType.setBounds(25, 55, 72, 16);
		panel5.add(lblFuelType);
		
		btnRegister = new JButton("Register");
		btnRegister.setFont(new Font("Arial", Font.PLAIN, 13));
		btnRegister.setToolTipText("Confirm register");
		btnRegister.setBounds(241, 389, 117, 29);
		btnRegister.addActionListener(new ButtonListener());
		panel.add(btnRegister);
		setContentPane(contentPane);
		
		
		networkModel= new DefaultComboBoxModel<String>();
		networkCB.setModel(networkModel);
		
		btnClear = new JButton("Clear");
		btnClear.setFont(new Font("Arial", Font.PLAIN, 13));
		btnClear.setToolTipText("Clear all input include all added cars");
		btnClear.setBounds(52, 389, 117, 29);
		btnClear.addActionListener(new ButtonListener());
		panel.add(btnClear);

			
		}
	
	/**
	 * This class used for handling all components events.
	 *
	 */
	private class ButtonListener implements ActionListener,ItemListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			getInput(e);
		}

		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			if(e.getStateChange()==ItemEvent.DESELECTED)
			{
			if(e.getSource() == saleModelCB)
				actions.resetCars();
			else if(e.getSource() == accessCB)
				actions.resetNetworks();
			}
		}
		
	}

	@Override
	public void getInput(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()== accessCB)
			actions.resetNetworks();
		
		if(e.getSource() == btnAddNetwork){
			actions.addNetwork((String)networkModel.getSelectedItem(), accessCB.getSelectedIndex());
		}
		
		if(e.getSource() == btnAddCar){
			actions.addCar(cidTextField.getText().toString(), fuelCB.getSelectedIndex()+1,saleModelCB.getSelectedIndex()+1);
		}
		
		if(e.getSource()==btnRegister ){
			actions.verifyDetails(idTextField.getText().toString(), fnameTextField.getText().toString(), lnameTextField.getText().toString(), 
					passTextField.getPassword(),rePassTextField.getPassword(), emailTextField.getText().toString(),addressTextField.getText().toString(),
					CCTextField.getText().toString(),typeCB.getSelectedIndex(), accessCB.getSelectedIndex(),saleModelCB.getSelectedIndex()+1);
		}
		
		if(e.getSource() == btnClear){
			clearAll();
			actions.resetAll();
		}
	}
/**
 * Add new Station to the Stations ComboBox.
 * @param st - The Station name received from DB.
 */
	public void addNetwork(String st)
	{
		networkModel.addElement(st);
	}

	
/**
 * Clear all Form fields.
 */
	public void clearAll()
	{
		idTextField.setText("");
		fnameTextField.setText("");
		lnameTextField.setText("");
		CCTextField.setText("");
		emailTextField.setText("");
		passTextField.setText("");
		rePassTextField.setText("");
		cidTextField.setText("");
		addressTextField.setText("");
	}
}
