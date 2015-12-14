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
		
		
		
		lblTitle.setBounds(271, 6, 206, 16);
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
		datePicker.setSize(114, 29);
		datePicker.setLocation(271, 235);
		panel.add(datePicker);
		
		JDatePanelImpl datePanel2 = new JDatePanelImpl(model2,p);
		 datePicker2 = new JDatePickerImpl(datePanel2, new DateLabelFormatter());	 
		 datePicker2.setSize(114, 29);
		 datePicker2.setLocation(271, 287);
		panel.add(datePicker2);
		
		JLabel lblNewLabel = new JLabel("Select a Template:");
		lblNewLabel.setBounds(130, 71, 111, 14);
		panel.add(lblNewLabel);
		
		templates = new JComboBox();
		templates.addActionListener(new template_show());
		templates.setModel(model);
		templates.setBounds(248, 69, 137, 20);
		panel.add(templates);
		
		JLabel lblDiscountPercentage = new JLabel("Discount Percentage:");
		lblDiscountPercentage.setBounds(61, 129, 159, 14);
		panel.add(lblDiscountPercentage);
		
		
		
		JLabel sign = new JLabel("%");
		sign.setBounds(230, 129, 46, 14);
		panel.add(sign);
		
		dis = new JLabel();
		dis.setBounds(195, 129, 46, 14);
		panel.add(dis);
		
		JLabel lblTypeOfCustomer = new JLabel("Type of Customer:");
		lblTypeOfCustomer.setBounds(61, 193, 117, 14);
		panel.add(lblTypeOfCustomer);
		
		 type = new JLabel();
		type.setBounds(176, 193, 77, 14);
		panel.add(type);
		
		JLabel lblStartHour = new JLabel("Start Time:");
		lblStartHour.setBounds(330, 129, 77, 14);
		panel.add(lblStartHour);
		
		JLabel lblEndTime = new JLabel("End Time:");
		lblEndTime.setBounds(330, 193, 77, 14);
		panel.add(lblEndTime);
		
		 start = new JLabel();
		start.setBounds(417, 129, 84, 14);
		panel.add(start);
		
		 end = new JLabel();
		end.setBounds(417, 193, 84, 14);
		panel.add(end);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.setBounds(233, 364, 114, 38);
		btnCreate.addActionListener(new createPromotion());
		panel.add(btnCreate);
		
		JLabel lblStartDate = new JLabel("Start Date:");
		lblStartDate.setBounds(129, 235, 91, 14);
		panel.add(lblStartDate);
		
		JLabel lblEndDate = new JLabel("End Date:");
		lblEndDate.setBounds(130, 298, 90, 14);
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
