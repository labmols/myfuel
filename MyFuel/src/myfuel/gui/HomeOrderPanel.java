package myfuel.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

import myfuel.GUIActions.HomeOrderActions;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class HomeOrderPanel extends JPanel{
	private HomeOrderActions actions;
	private JTextField qtyText;
	private JCheckBox urgentCB;
	private JTextField shipAddrText;
	private JDatePickerImpl datePicker;
	private JButton btnMakeOrder;
	private JLabel lblUrgent ;
	
	public HomeOrderPanel(HomeOrderActions actions)
	{
		this.actions = actions;
		UtilDateModel model1 = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		
		JDatePanelImpl datePanel = new JDatePanelImpl(model1,p);
		
		
		setOpaque(false);
		setBounds(6, 46, 584, 384);
		setLayout(null);
		
		JLabel lblQuantity = new JLabel("Quantity (Liter):");
		lblQuantity.setBounds(110, 129, 115, 16);
		add(lblQuantity);
		
		qtyText = new JTextField();
		qtyText.setBounds(237, 124, 128, 26);
		add(qtyText);
		qtyText.setColumns(10);
		
		urgentCB = new JCheckBox("Urgent Order?");
		urgentCB.setBounds(237, 198, 128, 23);
		add(urgentCB);
		urgentCB.setToolTipText("In 6 hours from now");
		urgentCB.setHorizontalTextPosition(SwingConstants.LEFT);
		urgentCB.setHorizontalAlignment(SwingConstants.CENTER);
		urgentCB.setOpaque(false);
		
		JLabel lblShipAddr = new JLabel("Shipping Address:");
		lblShipAddr.setBounds(110, 162, 134, 16);
		add(lblShipAddr);
		
		shipAddrText = new JTextField();
		shipAddrText.setBounds(237, 157, 128, 26);
		add(shipAddrText);
		shipAddrText.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Is it your correct Address?");
		lblNewLabel_1.setBounds(370, 162, 174, 16);
		add(lblNewLabel_1);
		lblNewLabel_1.setForeground(Color.WHITE);
		
		JLabel lblIfNotEnter = new JLabel("if not, enter your address.");
		lblIfNotEnter.setBounds(370, 182, 174, 16);
		add(lblIfNotEnter);
		
		JLabel lblShipDate = new JLabel("Ship Date:");
		lblShipDate.setBounds(110, 242, 76, 29);
		add(lblShipDate);
		
		lblUrgent = new JLabel("You will receieve your order within 6 hours from now!");
		lblUrgent.setBounds(199, 242, 345, 29);
	    add(lblUrgent);
		lblUrgent.setForeground(Color.RED);
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.setBounds(224, 242, 159, 29);
		add(datePicker);
		
		JLabel lblOrderType = new JLabel("Order Type:");
		lblOrderType.setBounds(110, 202, 85, 16);
		add(lblOrderType);
		
		btnMakeOrder = new JButton("Make Order");
		btnMakeOrder.setBounds(240, 310, 117, 29);
		add(btnMakeOrder);
		
		JTextPane txtPane = new JTextPane();
		txtPane.setBounds(99, 6, 437, 91);
		add(txtPane);
		txtPane.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPane.setEditable(false);
		
		txtPane.setText("Prices:\n\nUrgent Order (in 6 hours from now) : Original price + 2%\n600 - 800 Liters: 3% Discount from Total order price.\nMore then 800 Liters: 4% Discount from Total order price. ");
		txtPane.setOpaque(false);
		btnMakeOrder.addActionListener(new eventListener());
		lblUrgent.setVisible(false);
		urgentCB.addActionListener(new eventListener());
	}
	
	private class eventListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
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
	
	public void setAddress(String address)
	{
		shipAddrText.setText(address);
	}
		
	}

