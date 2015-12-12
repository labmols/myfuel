package myfuel.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import myfuel.client.CPromotionTemplateActions;
import myfuel.client.MDActions;

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
		lblTitle.setBounds(164, 6, 308, 16);
	
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
		
	    /* Setting the Jspinner StartHour for HH:mm 24h */
	    Date date = new Date();
	    SpinnerDateModel dateModel = new SpinnerDateModel(date, null, null, Calendar.MINUTE);
		startHour = new JSpinner(dateModel);
		JSpinner.DateEditor de_startHour = new JSpinner.DateEditor(startHour, "HH:mm");
		de_startHour.getTextField().setEditable( false );
		startHour.setEditor(de_startHour);
	/*	DateFormat format = new SimpleDateFormat("HH:mm:ss");
		Date start = (Date) startHour.getValue();
		String s = format.format(start);*/
		
		startHour.setBounds(280, 132, 115, 20);
		panel.add(startHour);
		
		/* Setting the Jspinner EndHour for HH:mm 24h */
		Date date2 = new Date();
	    SpinnerDateModel dateModel2 = new SpinnerDateModel(date2, null, null, Calendar.MINUTE);
		EndHour = new JSpinner(dateModel2);
		JSpinner.DateEditor de_EndHour = new JSpinner.DateEditor(EndHour, "HH:mm");
		de_EndHour.getTextField().setEditable( false );
		EndHour.setEditor(de_EndHour);
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
		p = new Promotion(name.getText(),Float.parseFloat(discount.getText()),start,end,typeOfCustomer.getSelectedIndex());
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
