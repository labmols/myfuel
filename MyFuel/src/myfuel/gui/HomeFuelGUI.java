package myfuel.gui;

import java.awt.event.ActionEvent;

import myfuel.GUIActions.HomeFuelActions;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import java.awt.Color;
import java.util.Properties;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import java.awt.GridBagLayout;
import java.awt.Font;

@SuppressWarnings("serial")
public class HomeFuelGUI extends SuperGUI {
	
	HomeFuelActions actions;
	private JTextField textField;
	private JCheckBox urgentCB;
	private JTextField textField_1;
	private JDatePickerImpl datePicker;
	
	public HomeFuelGUI(HomeFuelActions actions)
	{
		this.actions = actions;
		setContentPane(contentPane);
		
		JLabel lblQuantity = new JLabel("Quantity (Liter):");
		lblQuantity.setBounds(141, 176, 115, 16);
		panel.add(lblQuantity);
		
		textField = new JTextField();
		textField.setBounds(268, 171, 128, 26);
		panel.add(textField);
		textField.setColumns(10);
		
		urgentCB = new JCheckBox("Urgent Order?");
		urgentCB.setToolTipText("In 6 hours from now");
		urgentCB.setHorizontalTextPosition(SwingConstants.LEFT);
		urgentCB.setHorizontalAlignment(SwingConstants.CENTER);
		urgentCB.setBounds(268, 252, 128, 23);
		panel.add(urgentCB);
		
		JLabel lblNewLabel = new JLabel("Shipping Address:");
		lblNewLabel.setBounds(141, 216, 134, 16);
		panel.add(lblNewLabel);
		
		textField_1 = new JTextField();
		textField_1.setBounds(268, 211, 128, 26);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Is it your correct Address?");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(401, 216, 174, 16);
		panel.add(lblNewLabel_1);
		
		JLabel lblIfNotEnter = new JLabel("if not, enter your address.");
		lblIfNotEnter.setBounds(401, 236, 174, 16);
		panel.add(lblIfNotEnter);
		
		JLabel lblNewLabel_2 = new JLabel("Ship Date:");
		lblNewLabel_2.setBounds(141, 296, 76, 29);
		panel.add(lblNewLabel_2);
		
		UtilDateModel model1 = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		
		JDatePanelImpl datePanel = new JDatePanelImpl(model1,p);
		 datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());	 
		datePicker.setSize(159, 29);
		datePicker.setLocation(255, 296);
		panel.add(datePicker);
		
		JLabel lblOrderType = new JLabel("Order Type:");
		lblOrderType.setBounds(141, 256, 85, 16);
		panel.add(lblOrderType);
		
		JPanel panel2 = new JPanel();
		panel2.setOpaque(false);
		panel2.setBounds(87, 48, 449, 116);
		panel.add(panel2);
		panel2.setLayout(null);
		
		JTextPane txtPane = new JTextPane();
		txtPane.setFont(new Font("Arial Hebrew", Font.ITALIC, 14));
		txtPane.setEditable(false);
		
		txtPane.setText("Prices:\n\nUrgent Order (in 6 hours from now) : Original price + 2%\n600 - 800 Liters: 3% Discount from Total order price.\nMore then 800 Liters: 4% Discount from Total order price. ");
		txtPane.setBounds(6, 6, 437, 104);
		txtPane.setOpaque(false);
		panel2.add(txtPane);
		
		
		
	}

	@Override
	public void getInput(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
