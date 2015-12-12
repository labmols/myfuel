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
		lblPromotionName.setBounds(59, 116, 94, 14);
		panel.add(lblPromotionName);
		
		 name = new JTextField();
		name.setBounds(161, 112, 135, 20);
		panel.add(name);
		name.setColumns(10);
		
		JLabel lblDiscountPercentage = new JLabel("Discount Percentage:");
		lblDiscountPercentage.setBounds(60, 154, 117, 14);
		panel.add(lblDiscountPercentage);
		
		discount = new JTextField();
		discount.setBounds(210, 151, 86, 20);
		panel.add(discount);
		discount.setColumns(10);
		
		JLabel lblStartDate = new JLabel("Start Time:");
		lblStartDate.setBounds(60, 190, 63, 14);
		panel.add(lblStartDate);
		
		JLabel lblEndTime = new JLabel("End Time:");
		lblEndTime.setBounds(60, 223, 63, 14);
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
		
		startHour.setBounds(181, 187, 115, 20);
		panel.add(startHour);
		
		/* Setting the Jspinner EndHour for HH:mm 24h */
		Date date2 = new Date();
	    SpinnerDateModel dateModel2 = new SpinnerDateModel(date2, null, null, Calendar.MINUTE);
		EndHour = new JSpinner(dateModel2);
		JSpinner.DateEditor de_EndHour = new JSpinner.DateEditor(EndHour, "HH:mm");
		de_EndHour.getTextField().setEditable( false );
		EndHour.setEditor(de_EndHour);
		EndHour.setBounds(181, 220, 115, 20);
		panel.add(EndHour);
		
		JLabel lblTypeOfCustomer = new JLabel("Type Of Customer:");
		lblTypeOfCustomer.setBounds(60, 264, 117, 14);
		panel.add(lblTypeOfCustomer);
		
		typeOfCustomer = new JComboBox();
		typeOfCustomer.setModel(new DefaultComboBoxModel(new String[] {"Private", "Company"}));
		typeOfCustomer.setBounds(181, 263, 118, 20);
		panel.add(typeOfCustomer);
		
		btnCreate = new JButton("Create");
		btnCreate.addActionListener(new btnHandler());
		btnCreate.setBounds(217, 344, 118, 37);
		panel.add(btnCreate);
		
	}

	@Override
	public void getInput(ActionEvent e) 
	{
		DateFormat format = new SimpleDateFormat("HH:mm:ss");
		
		String start = format.format((Date) startHour.getValue());
		String end = format.format((Date) EndHour.getValue());
		
		p = new Promotion(name.getText(),Float.parseFloat(discount.getText()),start,end,typeOfCustomer.getSelectedIndex());
	}
	
	private class btnHandler implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			getInput(e);
			actions.PromotionTemplate(p);
		}
		
	}
}
