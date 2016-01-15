package myfuel.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import myfuel.GUIActions.CPromotionTemplateActions;
import myfuel.GUIActions.MDActions;
import myfuel.client.BackMainMenu;
import myfuel.client.Promotion;
import myfuel.client.PromotionTemplate;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;

import java.awt.event.ActionListener;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JTextPane;
import javax.swing.JSpinner;
import java.awt.Dimension;

/***
 * User Interface For Creating Promotion Template
 * @author karmo
 *
 */
@SuppressWarnings("serial")
public class CreatePromotionTemplateGUI extends SuperGUI {

	/***
	 * Controller for this GUI
	 */
	private CPromotionTemplateActions actions;
	/***
	 * Input for Name of the template
	 */
	private JTextField name;
	/***
	 * Input for Discount of the template
	 */
	private JTextField discount;
	/***
	 * Input for Start Time of the template
	 */
	private JSpinner startHour;
	/***
	 * Input for End Time of the template
	 */
	private JSpinner EndHour;
	/***
	 * JComboBox for Customer Type 
	 */
	private JComboBox<String> typeOfCustomer;
	/***
	 * Creation Button
	 */
	private JButton btnCreate;
	/***
	 * JComboBox for fuel Type 
	 */
	private JComboBox<String> fuel;
	/***
	 * Object for saving the PromotionTemplate Details
	 */
	private PromotionTemplate p ;
	/***
	 * True for legal values and false otherwise
	 */
	private boolean legal;
	/**
	 * CreatePromotionTemplateGUI Constructor
	 * @param actions - Controller for this GUI
	 */
	public CreatePromotionTemplateGUI(CPromotionTemplateActions actions) {
		super(actions);
		this.actions = actions;
		setContentPane(contentPane);
		lblTitle.setBounds(176, 6, 308, 16);
		
		
		this.mainMenu.addActionListener(new BackMainMenu(actions));
		lblTitle.setText("Create Promotion Template");
	
		this.mnMenu.addActionListener(new BackMainMenu(actions));
		
		
		JLabel lblPromotionName = new JLabel("Promotion Template Name:");
		lblPromotionName.setBounds(101, 82, 200, 14);
		panel.add(lblPromotionName);
		
		 name = new JTextField();
		name.setBounds(280, 79, 135, 20);
		panel.add(name);
		name.setColumns(10);
		
		JLabel lblDiscountPercentage = new JLabel("Discount Percentage:");
		lblDiscountPercentage.setBounds(101, 125, 167, 14);
		panel.add(lblDiscountPercentage);
		
		discount = new JTextField();
		discount.setBounds(280, 125, 32, 20);
		panel.add(discount);
		discount.setColumns(10);
		
		JLabel lblStartDate = new JLabel("Start Time:");
		lblStartDate.setBounds(80, 167, 117, 14);
		panel.add(lblStartDate);
		
		JLabel lblEndTime = new JLabel("End Time:");
		lblEndTime.setBounds(332, 167, 63, 14);
		panel.add(lblEndTime);
		
		//set start time spinner
		GregorianCalendar cal = new GregorianCalendar(1970,0,1);
		Date date = cal.getTime();
		SpinnerDateModel sm = new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
		startHour = new JSpinner(sm);
		JSpinner.DateEditor de = new JSpinner.DateEditor(startHour, "HH:mm");
		startHour.setEditor(de);
		startHour.setBounds(153, 156, 115, 37);
		panel.add(startHour);
		
		//set end time spinner
		Date date2 = cal.getTime();
		SpinnerDateModel sm2 =new SpinnerDateModel(date2, null, null, Calendar.HOUR_OF_DAY);
		EndHour = new JSpinner(sm2);
		
		JSpinner.DateEditor de2 = new JSpinner.DateEditor(EndHour, "HH:mm");
		EndHour.setEditor(de2);
		EndHour.setBounds(417, 156, 115, 37);
		panel.add(EndHour);
		
		
		
		JLabel lblTypeOfCustomer = new JLabel("Type Of Customer:");
		lblTypeOfCustomer.setBounds(132, 228, 149, 14);
		panel.add(lblTypeOfCustomer);
		
		typeOfCustomer = new JComboBox<String>();
		typeOfCustomer.setModel(new DefaultComboBoxModel<String>(new String[] {"Private", "Company"}));
		typeOfCustomer.setBounds(311, 226, 118, 20);
		panel.add(typeOfCustomer);
		
		btnCreate = new JButton("Create");
		btnCreate.addActionListener(new btnHandler());
		btnCreate.setBounds(211, 322, 118, 37);
		panel.add(btnCreate);
		
		JLabel lblNewLabel = new JLabel("%");
		lblNewLabel.setBounds(313, 128, 41, 16);
		panel.add(lblNewLabel);
		
		JLabel lblTypeOfFuel = new JLabel("Type of Fuel:");
		lblTypeOfFuel.setBounds(132, 267, 117, 14);
		
		panel.add(lblTypeOfFuel);
		
		 fuel = new JComboBox<String>();
		 fuel.setModel(new DefaultComboBoxModel<String>(new String[] {"95", "Diesel", "Scooter", "Home Fuel"}));
		fuel.setBounds(311, 264, 115, 20);
		panel.add(fuel);
		
	}
	


	@Override
	public void getInput(ActionEvent e) 
	{
		float d = 0;
		String n = null;
		 legal = true;
		DateFormat format = new SimpleDateFormat("HH:mm");
		Date date = new Date();
		try{
		 n = name.getText();
		 d = Float.parseFloat(discount.getText());
		}catch(Exception ex){this.showErrorMessage("Input must be a number!"); legal = false;}

		Date startD =(Date) startHour.getValue();
		Time start = new Time(startD.getTime());
		System.out.println(start);
		//Date start = (Date) startHour.getValue();
		Date endD = (Date) EndHour.getValue();
		Time end = new Time(endD.getTime());
		System.out.println(end);
		if(legal)
			p = new PromotionTemplate(0,n,d,start,end,typeOfCustomer.getSelectedIndex(),fuel.getSelectedIndex()+1);
		
	
		
		
	}
	/***
	 * Action Listener for JButton
	 * @author karmo
	 *
	 */
	private class btnHandler implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			getInput(e);
			if(legal)
				actions.verifyDetails(p);
		}
		
	}
}
