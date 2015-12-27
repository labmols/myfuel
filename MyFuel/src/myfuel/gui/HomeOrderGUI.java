package myfuel.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import myfuel.GUIActions.HomeOrderActions;
import myfuel.client.BackMainMenu;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import java.awt.Color;
import java.util.Date;
import java.util.Properties;

import javax.swing.JPanel;
import javax.swing.JTextPane;

import java.awt.GridBagLayout;
import java.awt.Font;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class HomeOrderGUI extends SuperGUI {
	
	HomeOrderActions actions;
	private JTextField qtyText;
	private JCheckBox urgentCB;
	private JTextField shipAddrText;
	private JDatePickerImpl datePicker;
	private JButton btnMakeOrder;
	JLabel lblUrgent ;
	
	public HomeOrderGUI(HomeOrderActions actions)
	{
		lblTitle.setBounds(208, 10, 174, 16);
		lblTitle.setText("Home Fuel Order");
		this.actions = actions;
		this.mainMenu.addActionListener(new BackMainMenu(actions));
		setContentPane(contentPane);
		
		JLabel lblQuantity = new JLabel("Quantity (Liter):");
		lblQuantity.setBounds(116, 181, 115, 16);
		panel.add(lblQuantity);
		
		qtyText = new JTextField();
		qtyText.setBounds(243, 176, 128, 26);
		panel.add(qtyText);
		qtyText.setColumns(10);
		
		urgentCB = new JCheckBox("Urgent Order?");
		urgentCB.setToolTipText("In 6 hours from now");
		urgentCB.setHorizontalTextPosition(SwingConstants.LEFT);
		urgentCB.setHorizontalAlignment(SwingConstants.CENTER);
		urgentCB.setBounds(243, 257, 128, 23);
		urgentCB.setOpaque(false);
		urgentCB.addActionListener(new eventListener());
		panel.add(urgentCB);
		
		JLabel lblShipAddr = new JLabel("Shipping Address:");
		lblShipAddr.setBounds(116, 221, 134, 16);
		panel.add(lblShipAddr);
		
		shipAddrText = new JTextField();
		shipAddrText.setBounds(243, 216, 128, 26);
		panel.add(shipAddrText);
		shipAddrText.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Is it your correct Address?");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(376, 221, 174, 16);
		panel.add(lblNewLabel_1);
		
		JLabel lblIfNotEnter = new JLabel("if not, enter your address.");
		lblIfNotEnter.setBounds(376, 241, 174, 16);
		panel.add(lblIfNotEnter);
		
		JLabel lblShipDate = new JLabel("Ship Date:");
		lblShipDate.setBounds(116, 301, 76, 29);
		panel.add(lblShipDate);
		
		lblUrgent = new JLabel("You will receieve your order within 6 hours from now!");
		lblUrgent.setForeground(Color.RED);
		lblUrgent.setBounds(205, 301, 345, 29);
		lblUrgent.setVisible(false);
		panel.add(lblUrgent);
		
		UtilDateModel model1 = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		
		JDatePanelImpl datePanel = new JDatePanelImpl(model1,p);
		 datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());	 
		datePicker.setSize(159, 29);
		datePicker.setLocation(230, 301);
		panel.add(datePicker);
		
		JLabel lblOrderType = new JLabel("Order Type:");
		lblOrderType.setBounds(116, 261, 85, 16);
		panel.add(lblOrderType);
		
		JPanel panel2 = new JPanel();
		panel2.setOpaque(false);
		panel2.setBounds(87, 48, 449, 116);
		panel.add(panel2);
		panel2.setLayout(null);
		
		JTextPane txtPane = new JTextPane();
		txtPane.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPane.setEditable(false);
		
		txtPane.setText("Prices:\n\nUrgent Order (in 6 hours from now) : Original price + 2%\n600 - 800 Liters: 3% Discount from Total order price.\nMore then 800 Liters: 4% Discount from Total order price. ");
		txtPane.setBounds(6, 6, 437, 91);
		txtPane.setOpaque(false);
		panel2.add(txtPane);
		
		btnMakeOrder = new JButton("Make Order");
		btnMakeOrder.setBounds(237, 371, 117, 29);
		btnMakeOrder.addActionListener(new eventListener());
		panel.add(btnMakeOrder);
		
		
		
	}
	
	private class eventListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			getInput(e);
		}
		
	}

	@Override
	public void getInput(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == urgentCB) 
		{
			if(urgentCB.isSelected())
			{
			datePicker.setVisible(false);
			lblUrgent.setVisible(true);
			}
			else {
				datePicker.setVisible(true);
				lblUrgent.setVisible(false);
			}
		}
		else if(e.getSource() == btnMakeOrder)
		{
			actions.verifyDetails((Date)datePicker.getModel().getValue(), qtyText.getText(), shipAddrText.getText(), urgentCB.isSelected());
		}
	}
}
