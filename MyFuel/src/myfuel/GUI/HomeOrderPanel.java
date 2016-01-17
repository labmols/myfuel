package myfuel.GUI;

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

import com.alee.extended.date.WebDateField;

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
	 * Make order button.
	 */
	private JButton btnMakeOrder;
	/**
	 * Urgent Label.
	 */
	private JLabel lblUrgent ;
	
	 WebDateField dateField;
	
	/**
	 * Create new Home Order Panel(part of Home Fuel GUI).
	 * @param actions - Home Order GUI Controller.
	 */
	public HomeOrderPanel(HomeOrderActions actions)
	{
		setOpaque(false);
		this.actions = actions;

		setBounds(6, 46, 584, 384);
		setLayout(null);
		
		JLabel lblQuantity = new JLabel("Amount (Liter):");
		lblQuantity.setFont(new Font("Arial", Font.BOLD, 13));
		lblQuantity.setBounds(88, 59, 115, 16);
		add(lblQuantity);
		
		qtyText = new JTextField();
		qtyText.setFont(new Font("Arial", Font.PLAIN, 13));
		qtyText.setBounds(215, 54, 128, 26);
		add(qtyText);
		qtyText.setColumns(10);
		
		urgentCB = new JCheckBox("Urgent Order?");
		urgentCB.setFont(new Font("Arial", Font.PLAIN, 13));
		urgentCB.setBounds(213, 128, 128, 23);
		add(urgentCB);
		urgentCB.setToolTipText("In 6 hours from now");
		urgentCB.setHorizontalTextPosition(SwingConstants.LEFT);
		urgentCB.setHorizontalAlignment(SwingConstants.CENTER);
		urgentCB.setOpaque(false);
		
		JLabel lblShipAddr = new JLabel("Shipping Address:");
		lblShipAddr.setFont(new Font("Arial", Font.BOLD, 13));
		lblShipAddr.setBounds(88, 92, 134, 16);
		add(lblShipAddr);
		
		shipAddrText = new JTextField();
		shipAddrText.setFont(new Font("Arial", Font.PLAIN, 13));
		shipAddrText.setBounds(215, 87, 128, 26);
		add(shipAddrText);
		shipAddrText.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Is it your correct Address?");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(348, 92, 174, 16);
		add(lblNewLabel_1);
		lblNewLabel_1.setForeground(Color.WHITE);
		
		JLabel lblIfNotEnter = new JLabel("if not, enter your address.");
		lblIfNotEnter.setFont(new Font("Arial", Font.PLAIN, 13));
		lblIfNotEnter.setBounds(348, 112, 174, 16);
		add(lblIfNotEnter);
		
		JLabel lblShipDate = new JLabel("Ship Date:");
		lblShipDate.setFont(new Font("Arial", Font.BOLD, 13));
		lblShipDate.setBounds(88, 172, 76, 29);
		add(lblShipDate);
		
		lblUrgent = new JLabel("You will receieve your order within 6 hours from now!");
		lblUrgent.setFont(new Font("Arial", Font.PLAIN, 13));
		lblUrgent.setBounds(201, 172, 345, 29);
	    add(lblUrgent);
		lblUrgent.setForeground(Color.RED);

		
		JLabel lblOrderType = new JLabel("Order Type:");
		lblOrderType.setFont(new Font("Arial", Font.BOLD, 13));
		lblOrderType.setBounds(88, 132, 85, 16);
		add(lblOrderType);
		
		btnMakeOrder = new JButton("Make Order");
		btnMakeOrder.setFont(new Font("Arial", Font.PLAIN, 13));
		btnMakeOrder.setBounds(226, 250, 117, 29);
		add(btnMakeOrder);
		
		JLabel lblMakeNewOrder = new JLabel("Make New Order");
		lblMakeNewOrder.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 16));
		lblMakeNewOrder.setBounds(213, 17, 159, 16);
		add(lblMakeNewOrder);
		btnMakeOrder.addActionListener(new eventListener());
		lblUrgent.setVisible(false);
		urgentCB.addActionListener(new eventListener());
		
		 dateField = new WebDateField ( new Date () );
		 dateField.setSize(128, 25);
		 dateField.setLocation(215, 173);
		 add(dateField);

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
				dateField.setVisible(false);
				lblUrgent.setVisible(true);
				}
				else {
					dateField.setVisible(true);
					lblUrgent.setVisible(false);
				}
			}
			else if(e.getSource() == btnMakeOrder)
			{
				actions.verifyDetails(dateField.getDate(), qtyText.getText(), shipAddrText.getText(), urgentCB.isSelected());
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

