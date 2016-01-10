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

/**
 *Home Order panel ,Part of Home Fuel User Interface.
 */
@SuppressWarnings("serial")
public class HomeOrderPanel extends JPanel{
	/**
	 * Home Order GUI Controller object.
	 */
	private HomeOrderActions actions;
	/**
	 * Fuel Quantity TextField.
	 */
	private JTextField qtyText;
	/**
	 * Urgent CheckBox (Is the order urgent?).
	 */
	private JCheckBox urgentCB;
	/**
	 * Shipping address TextField.
	 */
	private JTextField shipAddrText;
	/**
	 * Shipping DatePicker 
	 */
	private JDatePickerImpl datePicker;
	/**
	 * Make order button.
	 */
	private JButton btnMakeOrder;
	/**
	 * Urgent Label.
	 */
	private JLabel lblUrgent ;
	
	/**
	 * Create new Home Order Panel(part of Home Fuel GUI).
	 * @param actions - Home Order GUI Controller.
	 */
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
		
		JLabel lblQuantity = new JLabel("Amount (Liter):");
		lblQuantity.setFont(new Font("Arial", Font.BOLD, 13));
		lblQuantity.setBounds(97, 100, 115, 16);
		add(lblQuantity);
		
		qtyText = new JTextField();
		qtyText.setFont(new Font("Arial", Font.PLAIN, 13));
		qtyText.setBounds(224, 95, 128, 26);
		add(qtyText);
		qtyText.setColumns(10);
		
		urgentCB = new JCheckBox("Urgent Order?");
		urgentCB.setFont(new Font("Arial", Font.PLAIN, 13));
		urgentCB.setBounds(224, 169, 128, 23);
		add(urgentCB);
		urgentCB.setToolTipText("In 6 hours from now");
		urgentCB.setHorizontalTextPosition(SwingConstants.LEFT);
		urgentCB.setHorizontalAlignment(SwingConstants.CENTER);
		urgentCB.setOpaque(false);
		
		JLabel lblShipAddr = new JLabel("Shipping Address:");
		lblShipAddr.setFont(new Font("Arial", Font.BOLD, 13));
		lblShipAddr.setBounds(97, 133, 134, 16);
		add(lblShipAddr);
		
		shipAddrText = new JTextField();
		shipAddrText.setFont(new Font("Arial", Font.PLAIN, 13));
		shipAddrText.setBounds(224, 128, 128, 26);
		add(shipAddrText);
		shipAddrText.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Is it your correct Address?");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(357, 133, 174, 16);
		add(lblNewLabel_1);
		lblNewLabel_1.setForeground(Color.WHITE);
		
		JLabel lblIfNotEnter = new JLabel("if not, enter your address.");
		lblIfNotEnter.setFont(new Font("Arial", Font.PLAIN, 13));
		lblIfNotEnter.setBounds(357, 153, 174, 16);
		add(lblIfNotEnter);
		
		JLabel lblShipDate = new JLabel("Ship Date:");
		lblShipDate.setFont(new Font("Arial", Font.BOLD, 13));
		lblShipDate.setBounds(97, 213, 76, 29);
		add(lblShipDate);
		
		lblUrgent = new JLabel("You will receieve your order within 6 hours from now!");
		lblUrgent.setFont(new Font("Arial", Font.PLAIN, 13));
		lblUrgent.setBounds(186, 213, 345, 29);
	    add(lblUrgent);
		lblUrgent.setForeground(Color.RED);
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.setBounds(211, 213, 159, 29);
		add(datePicker);
		
		JLabel lblOrderType = new JLabel("Order Type:");
		lblOrderType.setFont(new Font("Arial", Font.BOLD, 13));
		lblOrderType.setBounds(97, 173, 85, 16);
		add(lblOrderType);
		
		btnMakeOrder = new JButton("Make Order");
		btnMakeOrder.setFont(new Font("Arial", Font.PLAIN, 13));
		btnMakeOrder.setBounds(217, 310, 117, 29);
		add(btnMakeOrder);
		
		JTextPane txtPane = new JTextPane();
		txtPane.setBounds(87, 26, 437, 57);
		add(txtPane);
		txtPane.setFont(new Font("Arial", Font.PLAIN, 13));
		txtPane.setEditable(false);
		
		txtPane.setText("Urgent Order (in 6 hours from now) : Original price + 2%\n600 - 800 Liters: 3% Discount from Total order price.\nMore then 800 Liters: 4% Discount from Total order price. ");
		txtPane.setOpaque(false);
		btnMakeOrder.addActionListener(new eventListener());
		lblUrgent.setVisible(false);
		urgentCB.addActionListener(new eventListener());
	}
	
	/**
	 * This class used for handling all components events.
	 *
	 */
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
	
	/**
	 * Set Customer address according the value from DB.
	 * @param address - The address received from DB.
	 */
	public void setAddress(String address)
	{
		shipAddrText.setText(address);
	}
		
	}

