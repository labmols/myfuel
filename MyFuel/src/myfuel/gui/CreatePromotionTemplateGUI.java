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
		lblTitle.setBounds(236, 6, 233, 16);
	
		lblTitle.setText("Create Promotion Template");
		this.actions = actions;
		
		JLabel lblPromotionName = new JLabel("Promotion Name:");
		lblPromotionName.setBounds(59, 116, 165, 14);
		panel.add(lblPromotionName);
		
		JLabel lblDiscountPercentage = new JLabel("Discount Percentage:");
		lblDiscountPercentage.setBounds(60, 154, 164, 14);
		panel.add(lblDiscountPercentage);
		
		JLabel lblStartDate = new JLabel("Start Time:");
		lblStartDate.setBounds(60, 190, 100, 14);
		panel.add(lblStartDate);
		
		JLabel lblEndTime = new JLabel("End Time:");
		lblEndTime.setBounds(60, 223, 117, 14);
		panel.add(lblEndTime);
		
	    /* Setting the Jspinner StartHour for HH:mm 24h */
	    Date date = new Date();
	    SpinnerDateModel dateModel = new SpinnerDateModel(date, null, null, Calendar.MINUTE);
		
		/* Setting the Jspinner EndHour for HH:mm 24h */
		Date date2 = new Date();
	    SpinnerDateModel dateModel2 = new SpinnerDateModel(date2, null, null, Calendar.MINUTE);
		
		JLabel lblTypeOfCustomer = new JLabel("Type Of Customer:");
		lblTypeOfCustomer.setBounds(60, 264, 164, 14);
		panel.add(lblTypeOfCustomer);
		
		btnCreate = new JButton("Create");
		btnCreate.addActionListener(new btnHandler());
		btnCreate.setBounds(217, 344, 127, 37);
		panel.add(btnCreate);
		
		 name = new JTextField();
		 name.setBounds(271, 112, 135, 20);
		 panel.add(name);
		 name.setColumns(10);
		 
		 discount = new JTextField();
		 discount.setBounds(320, 148, 86, 20);
		 panel.add(discount);
		 
		 discount.setColumns(10);
		 startHour = new JSpinner(dateModel);
		 startHour.setBounds(346, 190, 60, 20);
		 panel.add(startHour);
		 JSpinner.DateEditor de_startHour = new JSpinner.DateEditor(startHour, "HH:mm");
		 de_startHour.getTextField().setEditable( false );
		 startHour.setEditor(de_startHour);
		 
		 EndHour = new JSpinner(dateModel2);
		 EndHour.setBounds(346, 221, 60, 20);
		 panel.add(EndHour);
		 JSpinner.DateEditor de_EndHour = new JSpinner.DateEditor(EndHour, "HH:mm");
		 de_EndHour.getTextField().setEditable( false );
		 EndHour.setEditor(de_EndHour);
		 
		 typeOfCustomer = new JComboBox();
		 typeOfCustomer.setBounds(336, 262, 70, 20);
		 panel.add(typeOfCustomer);
		 typeOfCustomer.setModel(new DefaultComboBoxModel(new String[] {"Private", "Company"}));
		
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
