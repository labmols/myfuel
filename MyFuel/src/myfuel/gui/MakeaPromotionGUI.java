package myfuel.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import myfuel.GUIActions.MakeAPromotionActions;
import myfuel.client.Promotion;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextPane;
import javax.swing.JTextField;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.JButton;
import java.awt.Color;


public class MakeaPromotionGUI extends SuperGUI{

	MakeAPromotionActions actions;
	private JLabel dis;  // discount amount
	private JLabel type; // type of customer
	private JLabel end; // end time 
	private JLabel start; // start time
	private JComboBox<?> templates;
	private Promotion p ;
	private Date endDate;
	private Date startDate;
	
	DefaultComboBoxModel model;
	JDatePickerImpl datePicker;
	JDatePickerImpl datePicker2;
	public MakeaPromotionGUI(MakeAPromotionActions actions) 
	{
		model = new DefaultComboBoxModel(  );
		
		
		
		lblTitle.setBounds(201, 6, 206, 20);
		lblTitle.setText("Make a Promotion");
		this.actions = actions;
		this.setContentPane(contentPane);
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		
		UtilDateModel model1 = new UtilDateModel();
		UtilDateModel model2 = new UtilDateModel();
	
		JDatePanelImpl datePanel = new JDatePanelImpl(model1,p);
		
		 datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());	 
<<<<<<< HEAD
		 datePicker.setOpaque(false);
		datePicker.setSize(114, 29);
		datePicker.setLocation(271, 235);
=======
		datePicker.setSize(159, 29);
		datePicker.setLocation(226, 215);
>>>>>>> branch 'master' of https://github.com/labmols/myfuel.git 
		panel.add(datePicker);
		
		JDatePanelImpl datePanel2 = new JDatePanelImpl(model2,p);
		 datePicker2 = new JDatePickerImpl(datePanel2, new DateLabelFormatter());	 
<<<<<<< HEAD
		 datePicker2.setSize(114, 29);
		 datePicker2.setOpaque(false);
		 datePicker2.setLocation(271, 287);
=======
		 datePicker2.setSize(159, 29);
		 datePicker2.setLocation(226, 256);
>>>>>>> branch 'master' of https://github.com/labmols/myfuel.git
		panel.add(datePicker2);
		
		JLabel lblNewLabel = new JLabel("Select a Template:");
		lblNewLabel.setBounds(112, 71, 129, 14);
		panel.add(lblNewLabel);
		
		templates = new JComboBox();
		templates.addActionListener(new template_show());
		templates.setModel(model);
		templates.setBounds(248, 69, 137, 20);
		panel.add(templates);
		
		JLabel lblDiscountPercentage = new JLabel("Discount Percentage:");
		lblDiscountPercentage.setBounds(112, 127, 137, 14);
		panel.add(lblDiscountPercentage);
		
		
		
		JLabel sign = new JLabel("%");
		sign.setForeground(Color.WHITE);
		sign.setBounds(289, 127, 18, 14);
		panel.add(sign);
		
		dis = new JLabel();
		dis.setForeground(Color.WHITE);
		dis.setBounds(252, 127, 37, 14);
		panel.add(dis);
		
		JLabel lblTypeOfCustomer = new JLabel("Type of Customer:");
		lblTypeOfCustomer.setBounds(112, 157, 117, 14);
		panel.add(lblTypeOfCustomer);
		
		 type = new JLabel();
		 type.setForeground(Color.WHITE);
		type.setBounds(238, 157, 77, 14);
		panel.add(type);
		
		JLabel lblStartHour = new JLabel("Start Time:");
		lblStartHour.setBounds(345, 127, 77, 14);
		panel.add(lblStartHour);
		
		JLabel lblEndTime = new JLabel("End Time:");
		lblEndTime.setBounds(345, 157, 77, 14);
		panel.add(lblEndTime);
		
		 start = new JLabel();
		 start.setForeground(Color.WHITE);
		start.setBounds(425, 127, 84, 14);
		panel.add(start);
		
		 end = new JLabel();
		 end.setForeground(Color.WHITE);
		end.setBounds(425, 157, 84, 14);
		panel.add(end);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.setBounds(239, 348, 114, 38);
		btnCreate.addActionListener(new createPromotion());
		panel.add(btnCreate);
		
		JLabel lblStartDate = new JLabel("Start Date:");
		lblStartDate.setBounds(157, 219, 91, 14);
		panel.add(lblStartDate);
		
		JLabel lblEndDate = new JLabel("End Date:");
		lblEndDate.setBounds(158, 259, 90, 14);
		panel.add(lblEndDate);
	}

	@Override
	public void getInput(ActionEvent e)
	{
		setStartDate((Date) datePicker.getModel().getValue());
		setEndDate((Date) datePicker2.getModel().getValue());
		
	}
	
	private class template_show implements ActionListener
	{
		
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			p = actions.getPromotion(templates.getSelectedIndex());
			dis.setText(""+p.getDiscount());
			
			if(p.getTypeOfCustomer() == 0)
				type.setText("Private");
			else
				type.setText("Company");
			start.setText(""+p.getStartTime());
			end.setText(""+p.getEndTime());
			
		}
		
	}
	
	private class createPromotion implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			 
			getInput(e);
			actions.verifyDetails(startDate, endDate);
			
			
		}
		
	}
	
	public Promotion getP()
	{
		return this.p;
	}
	
	public void addElementToModel(String name)
	{
		model.addElement(name);
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}
