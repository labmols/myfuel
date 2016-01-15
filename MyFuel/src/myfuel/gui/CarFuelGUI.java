package myfuel.gui;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;

import myfuel.GUIActions.CarFuelActions;
import myfuel.client.*;
import myfuel.response.FuelOrderResponse;

import javax.swing.DefaultComboBoxModel;

import java.awt.event.ActionListener;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;

import java.awt.Font;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

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
	int nid;
	/**
	 * Quantity Text Field.
	 */
	protected JTextField LimitText;
	/**
	 * GUI Controller object.
	 */
	protected CarFuelActions actions;
	/**
	 * Stations ComboBox model(used for handle elements functions).
	 */
	protected  DefaultComboBoxModel<String> stationModel;
	/**
	 * Cars ComboBox model(used for handle elements functions).
	 */
	protected  DefaultComboBoxModel<Integer> carModel;
	/**
	 * 95 Fuel type pump Radio Button.
	 */
	protected  XRadioButton rb95;
	/**
	 *  Scooter Fuel type pump Radio Button.
	 */
	protected XRadioButton rbscooter;
	/**
	 *  Diesel Fuel type pump Radio Button.
	 */
	protected  XRadioButton rbdiesel;
	/**
	 * Current fuel selected value (1-95/2-Diesel/3-Scooter).
	 */
	protected  int fuelSelected=-1;
	/**
	 * 95 Fuel type price Label
	 */
	protected  JLabel lblp95;
	/**
	 * Diesel Fuel type price Label
	 */
	protected  JLabel lblpdiesel;
	/**
	 * Scooter Fuel type price Label
	 */
	protected  JLabel lblpscooter;
	/**
	 * Cars ComboBox , contain all the customer cars.
	 */
	protected  JComboBox<Integer> carCB;
	/**
	 * Stations ComboBox, contain all the available Stations.
	 */
	protected  JComboBox <String> stationCombo;

	
	protected int customerModel;
	
	protected  HashMap<Integer, Integer> IDHolder;
	
	protected  JTextField dName;
	protected  JLabel totalPrice;
	protected  JLabel promDisc;
	protected  float currentPrice;
	protected  JButton btnStartFuel;
	protected  JLabel lblDriverName;
	protected  JComboBox <String> limitBox;
	protected JLabel lblChooseStation;
	protected JLabel lblChooseCar;
	private JLabel lblModelDiscount;
	protected JLabel lblModelDisc;
	protected FuelOrderResponse res;
	protected float discount;
	protected Promotion p;
	protected ActionListener listener ;
	/**
	 * Create new Car Fuel user interface.
	 * @param actions - Car Fuel GUI Controller.
	 */
	public CarFuelGUI(CarFuelActions actions) {
		this.actions=actions;
		IDHolder = new HashMap();
		p = null;
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
		
		
		lblChooseStation = new JLabel("Choose Station:");
		lblChooseStation.setFont(new Font("Arial", Font.PLAIN, 13));
		lblChooseStation.setBounds(35, 10, 99, 16);
		panel2.add(lblChooseStation);
		lblChooseStation.setBackground(Color.RED);
		
		stationCombo = new JComboBox<String>();
		stationCombo.setFont(new Font("Arial", Font.PLAIN, 13));
		stationModel = new DefaultComboBoxModel<String>();
		stationCombo.setModel(stationModel);
		stationCombo.setBounds(134, 5, 109, 27);
		stationCombo.addItemListener(new eventListener());
		panel2.add(stationCombo);
		
		JLabel lblChooseFuelPump = new JLabel("Choose Fuel Pump:");
		lblChooseFuelPump.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 13));
		lblChooseFuelPump.setBounds(35, 44, 130, 16);
		panel2.add(lblChooseFuelPump);
		
		
		
		
		 java.net.URL url = getClass().getResource("/fuel.png");

		rb95 = new XRadioButton();
		rb95.getLabel().setFont(new Font("Arial", Font.BOLD, 14));
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
		rbdiesel.getLabel().setFont(new Font("Arial", Font.BOLD, 14));
		rbdiesel.getLabel().setBounds(39, 0, 145, 119);
		rbdiesel.getRadioButton().setBounds(6, 0, 28, 23);
		rbdiesel.setOpaque(false);
		rbdiesel.setBounds(186, 64, 183, 125);
		rbdiesel.setText("Diesel");
		rbdiesel.setIcon(new ImageIcon(url));
		panel2.add(rbdiesel);
		rbdiesel.setLayout(null);
		
		 
		LimitText = new JTextField();
		LimitText.setFont(new Font("Arial", Font.PLAIN, 13));
		LimitText.setBounds(362, 201, 61, 26);
		panel2.add(LimitText);
		LimitText.setColumns(10);
		
		btnStartFuel = new JButton("Start Fueling");
		btnStartFuel.setFont(new Font("Arial", Font.PLAIN, 13));
		btnStartFuel.setBounds(228, 328, 117, 29);
		panel2.add(btnStartFuel);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(rb95.getRadioButton());
		
		JLabel label_2 = new JLabel("Price:");
		label_2.setFont(new Font("Arial", Font.PLAIN, 13));
		label_2.setBounds(64, 109, 78, 16);
		rb95.add(label_2);
		buttonGroup.add(rbdiesel.getRadioButton());
		
		JLabel label = new JLabel("Price:");
		label.setFont(new Font("Arial", Font.PLAIN, 13));
		label.setBounds(88, 109, 78, 16);
		rbdiesel.add(label);
		
		
		
		rbscooter = new XRadioButton();
		rbscooter.getRadioButton().addActionListener(new eventListener());
		rbscooter.setBounds(362, 64, 164, 125);
		panel2.add(rbscooter);
		rbscooter.getLabel().setFont(new Font("Arial", Font.BOLD, 14));
		rbscooter.getLabel().setBounds(36, 0, 141, 125);
		rbscooter.getRadioButton().setBounds(6, 0, 28, 23);
		rbscooter.setOpaque(false);
		rbscooter.setText("Scooter");
		rbscooter.setIcon(new ImageIcon(url));
		rbscooter.setLayout(null);
		buttonGroup.add(rbscooter.getRadioButton());
		
		JLabel label_1 = new JLabel("Price:");
		label_1.setFont(new Font("Arial", Font.PLAIN, 13));
		label_1.setBounds(80, 109, 78, 16);
		rbscooter.add(label_1);
		
		lblpscooter = new JLabel("");
		lblpscooter.setFont(new Font("Arial", Font.BOLD, 13));
		lblpscooter.setForeground(Color.WHITE);
		lblpscooter.setBounds(116, 109, 61, 16);
		rbscooter.add(lblpscooter);
		
		lblp95 = new JLabel("");
		lblp95.setFont(new Font("Arial", Font.BOLD, 13));
		lblp95.setForeground(Color.WHITE);
		lblp95.setBounds(134, 173, 61, 16);
		panel2.add(lblp95);
		
		lblpdiesel = new JLabel("");
		lblpdiesel.setFont(new Font("Arial", Font.BOLD, 13));
		lblpdiesel.setForeground(Color.WHITE);
		lblpdiesel.setBounds(308, 173, 61, 16);
		panel2.add(lblpdiesel);
		
		lblChooseCar = new JLabel("Choose Car:");
		lblChooseCar.setFont(new Font("Arial", Font.PLAIN, 13));
		lblChooseCar.setBackground(Color.RED);
		lblChooseCar.setBounds(276, 10, 99, 16);
		panel2.add(lblChooseCar);
		
		carCB = new JComboBox<Integer>();
		carCB.setFont(new Font("Arial", Font.PLAIN, 13));
		carModel = new DefaultComboBoxModel<Integer>();
		carCB.setModel(carModel);
		carCB.setBounds(358, 5, 109, 27);
		panel2.add(carCB);
		
		JLabel lblLimitBy = new JLabel("Limit By: ");
		lblLimitBy.setFont(new Font("Arial", Font.PLAIN, 13));
		lblLimitBy.setBounds(175, 206, 68, 16);
		panel2.add(lblLimitBy);
		
		limitBox = new JComboBox<String>();
		limitBox.setFont(new Font("Arial", Font.PLAIN, 13));
		limitBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Amount", "Price"}));
		limitBox.setBounds(237, 201, 108, 27);
		panel2.add(limitBox);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(107, 239, 370, 75);
		panel2.add(panel);
		panel.setLayout(null);
		
		JLabel lblPromotion = new JLabel("Promotion : ");
		lblPromotion.setFont(new Font("Arial", Font.BOLD, 13));
		lblPromotion.setBounds(6, 29, 85, 16);
		panel.add(lblPromotion);
		
		JLabel lblYourPricefor = new JLabel("Your Price (For Liter): ");
		lblYourPricefor.setFont(new Font("Arial", Font.BOLD, 13));
		lblYourPricefor.setBounds(6, 53, 142, 16);
		panel.add(lblYourPricefor);
		
		promDisc = new JLabel("");
		promDisc.setFont(new Font("Arial", Font.PLAIN, 13));
		promDisc.setBounds(87, 29, 120, 16);
		panel.add(promDisc);
		
		totalPrice = new JLabel("");
		totalPrice.setFont(new Font("Arial", Font.PLAIN, 13));
		totalPrice.setBounds(146, 53, 61, 16);
		panel.add(totalPrice);
		
		lblModelDiscount = new JLabel("Total Model Discount: ");
		lblModelDiscount.setFont(new Font("Arial", Font.BOLD, 13));
		lblModelDiscount.setBounds(6, 6, 142, 16);
		panel.add(lblModelDiscount);
		
		lblModelDisc = new JLabel("");
		lblModelDisc.setFont(new Font("Arial", Font.PLAIN, 13));
		lblModelDisc.setBounds(146, 5, 218, 16);
		panel.add(lblModelDisc);
		
		lblDriverName = new JLabel("Driver Name: ");
		lblDriverName.setFont(new Font("Arial", Font.PLAIN, 13));
		lblDriverName.setBounds(6, 206, 99, 16);
		lblDriverName.setVisible(false);
		panel2.add(lblDriverName);
		
		dName = new JTextField();
		dName.setVisible(false);
		dName.setBounds(92, 201, 74, 26);
		panel2.add(dName);
		dName.setColumns(10);
		listener = new eventListener();
		btnStartFuel.addActionListener(listener);
		
		
	}
	
	/**
	 * This Class is used for components events handling.
	 *
	 */
	private class eventListener implements ActionListener,ItemListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			getInput(e);
			
		}

		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			if(e.getStateChange()==ItemEvent.DESELECTED &&  e.getSource() == stationCombo)
			{
				nid = IDHolder.get(stationCombo.getSelectedIndex());
				
				setDetails(fuelSelected, nid);
			}
		}	
	}
	
	/**
	 * Handle User input.
	 */
	@Override
	public void getInput(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == rb95.getRadioButton())
		{
			
			fuelSelected = Fuel.Fuel95ID;
			setDetails(fuelSelected, nid);
			
		}
		
		if(e.getSource() == rbscooter.getRadioButton())
		{
			fuelSelected = Fuel.FuelScooter;
			setDetails(fuelSelected, nid);
		}
		
		if(e.getSource() == rbdiesel.getRadioButton())
		{
			fuelSelected = Fuel.FuelDiesel;
			setDetails(fuelSelected, nid);
		}
		
		if(e.getSource() == btnStartFuel)
		{
			if(actions.verifyDetails(LimitText.getText(), fuelSelected, dName.getText(), (String)stationCombo.getSelectedItem(),(Integer)carCB.getSelectedItem(),IDHolder.get(stationCombo.getSelectedIndex()),this.limitBox.getSelectedIndex()))
				startFuel(Float.parseFloat(LimitText.getText()), this.limitBox.getSelectedIndex());
		}
		
	}
	
	protected void setDetails(int fuelSelected,int nid) {
		// TODO Auto-generated method stub
		
		discount = 0;
		this.nid = nid;
		float MonthlyOneDisc=0;
		NetworkRates rates= res.getNRates(nid);
		for(int i=0; i<customerModel; i++)
		{
			if(i!=1) discount += rates.getModelDiscount(i+1);
			if(i==1)  MonthlyOneDisc = rates.getModelDiscount(i+1);
		}
	currentPrice = actions.getPriceForLiter(fuelSelected, nid);
	totalPrice.setText(""+new DecimalFormat("##.##").format(currentPrice)+" NIS");
	lblModelDisc.setText(MonthlyOneDisc +"% (For Liter) ,"+new DecimalFormat("##.##").format(discount)+" %"+"(from total)");
	p = actions.getPromotion(fuelSelected);
	
	if(p!=null)
	{
	promDisc.setText(new DecimalFormat("##.##").format(p.getDiscount()) +"%");
	discount += p.getDiscount();
	}
	else promDisc.setText("No Promotion.");
	}

/**
 * 
 * @param rates
 * @param fuels
 */
	public void Initialize(int customerModel,FuelOrderResponse infoRes)
	{
				if(stationCombo.getSelectedIndex()>= 0)
				nid = IDHolder.get(stationCombo.getSelectedIndex());
				ArrayList<Fuel>fuels = infoRes.getFuels();
				lblp95.setText(fuels.get(0).getMaxPrice()+"NIS");
				lblpdiesel.setText(fuels.get(1).getMaxPrice()+"NIS");
				lblpscooter.setText(fuels.get(2).getMaxPrice()+"NIS");
				this.customerModel = customerModel;
				this.res= infoRes;
				rb95.getRadioButton().setSelected(true);
				fuelSelected=1;
				setDetails(fuelSelected,nid);
				
	}
	

	
	/**
	 * Add Station to the Stations ComboBox.
	 * @param st - The Station name String.
	 */
	public void addStation(Station s)
	{
		int nid = s.getNetwork().getNid();
		IDHolder.put(stationModel.getSize(), nid);
		stationModel.addElement(s.getName());
	}

	/**
	 * Add new Car to the Cars ComboBox.
	 * @param cid - The Car ID number.
	 */
	public void addCar(int cid) {
		
		carModel.addElement(cid);
	}
	
	protected void startFuel(float qty, int limit) {
		// TODO Auto-generated method stub
		float max;
		float origPrice ;
		float borigPrice = actions.getPriceForLiter(fuelSelected, nid) * qty;
		if(limit == 1)
		{
			max = qty / currentPrice; 
			borigPrice = qty;
			
		}
		else max = qty;
		origPrice = actions.getTotalPrice(fuelSelected, nid, max);
		float origQty = max;
		FuelDialog dialog = new FuelDialog(this.actions,this,max,currentPrice,origQty,origPrice,discount,borigPrice);
		dialog.setVisible(true);
	}

	public void checkType(int toc) {
		// TODO Auto-generated method stub
		
		if(toc == 1)
		{
			dName.setVisible(true);
			lblDriverName.setVisible(true);
		}
	}
	
	
	

	
}
