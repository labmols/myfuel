package myfuel.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import myfuel.GUIActions.CPromotionTemplateActions;
import myfuel.GUIActions.MDActions;
import myfuel.client.Promotion;

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

public class CreatePromotionTemplateGUI extends SuperGUI {

	
	CPromotionTemplateActions actions;
	JTextField name;
	JTextField discount;
	private JSpinner startHour;
	private JSpinner EndHour;
	private JComboBox typeOfCustomer;
	private JButton btnCreate;
	Promotion p ;
	/**
	 * Create the frame.
	 */
	public CreatePromotionTemplateGUI(CPromotionTemplateActions actions) {
		this.actions = actions;
		setContentPane(contentPane);
		lblTitle.setBounds(176, 6, 308, 16);
	
		lblTitle.setText("Create Promotion Template");
	
		
		JLabel lblPromotionName = new JLabel("Promotion Template Name:");
		lblPromotionName.setBounds(101, 82, 200, 14);
		panel.add(lblPromotionName);
		
		 name = new JTextField();
		name.setBounds(280, 79, 135, 20);
		panel.add(name);
		name.setColumns(10);
		
		JLabel lblDiscountPercentage = new JLabel("Discount Percentage:");
		lblDiscountPercentage.setBounds(101, 108, 167, 14);
		panel.add(lblDiscountPercentage);
		
		discount = new JTextField();
		discount.setBounds(280, 108, 32, 20);
		panel.add(discount);
		discount.setColumns(10);
		
		JLabel lblStartDate = new JLabel("Start Time:");
		lblStartDate.setBounds(101, 135, 117, 14);
		panel.add(lblStartDate);
		
		JLabel lblEndTime = new JLabel("End Time:");
		lblEndTime.setBounds(101, 167, 63, 14);
		panel.add(lblEndTime);
		
		//set start time spinner
		GregorianCalendar cal = new GregorianCalendar(1970,0,1);
		Date date = cal.getTime();
		SpinnerDateModel sm = new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
		startHour = new JSpinner(sm);
		JSpinner.DateEditor de = new JSpinner.DateEditor(startHour, "HH:mm");
		startHour.setEditor(de);
		startHour.setBounds(280, 132, 115, 20);
		panel.add(startHour);
		
		//set end time spinner
		Date date2 = cal.getTime();
		SpinnerDateModel sm2 =new SpinnerDateModel(date2, null, null, Calendar.HOUR_OF_DAY);
		EndHour = new JSpinner(sm2);
		JSpinner.DateEditor de2 = new JSpinner.DateEditor(EndHour, "HH:mm");
		EndHour.setEditor(de2);
		EndHour.setBounds(280, 132, 115, 20);
		panel.add(EndHour);
		
		EndHour.setBounds(280, 132, 115, 20);
		panel.add(EndHour);
		
		EndHour.setBounds(280, 164, 115, 20);
		panel.add(EndHour);
		
		JLabel lblTypeOfCustomer = new JLabel("Type Of Customer:");
		lblTypeOfCustomer.setBounds(101, 203, 149, 14);
		panel.add(lblTypeOfCustomer);
		
		typeOfCustomer = new JComboBox();
		typeOfCustomer.setModel(new DefaultComboBoxModel(new String[] {"Private", "Company"}));
		typeOfCustomer.setBounds(280, 201, 118, 20);
		panel.add(typeOfCustomer);
		
		btnCreate = new JButton("Create");
		btnCreate.addActionListener(new btnHandler());
		btnCreate.setBounds(211, 277, 118, 37);
		panel.add(btnCreate);
		
		JLabel lblNewLabel = new JLabel("%");
		lblNewLabel.setBounds(313, 111, 41, 16);
		panel.add(lblNewLabel);
		
	}

	@Override
	public void getInput(ActionEvent e) 
	{
		DateFormat format = new SimpleDateFormat("HH:mm");
		Date date = new Date();
		
		Date start = (Date) startHour.getValue();
		Date end = (Date) EndHour.getValue();
		p = new Promotion(0,name.getText(),Float.parseFloat(discount.getText()),start,end,typeOfCustomer.getSelectedIndex());
	}
	
	private class btnHandler implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			getInput(e);
			actions.verifyDetails(p);
		}
		
	}
}
