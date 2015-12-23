package myfuel.gui;

import java.awt.event.ActionEvent;

import myfuel.GUIActions.homeQtyOrderActions;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class homeQtyOrderGUI extends SuperGUI{

	private homeQtyOrderActions actions;
	private JTable table;
	private JButton setLowLvl;
	private final JTextField lowLvl;
	private JLabel lblLowLevel;
	private JButton confirmOrder;
	
	
	public homeQtyOrderGUI(homeQtyOrderActions actions)
	{
		lblTitle.setBounds(200, 0, 232, 25);
		lblTitle.setText("Home Fuel Control");
		
		table = new JTable();
		table.setBounds(28, 107, 212, 116);
		panel.add(table);
		
		JLabel lblNewOrders = new JLabel("Orders");
		lblNewOrders.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewOrders.setBounds(109, 72, 97, 24);
		panel.add(lblNewOrders);
		
		confirmOrder = new JButton("Confirm Order");
		confirmOrder.setBounds(81, 298, 125, 49);
		panel.add(confirmOrder);
		
		setLowLvl = new JButton("Set Low Level");
		setLowLvl.setBounds(343, 298, 125, 49);
		panel.add(setLowLvl);
		
		lowLvl = new JTextField();
		lowLvl.setBounds(359, 149, 97, 25);
		panel.add(lowLvl);
		lowLvl.setColumns(10);
		
		lblLowLevel = new JLabel("Low Level");
		lblLowLevel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblLowLevel.setBounds(363, 72, 131, 24);
		panel.add(lblLowLevel);
		this.actions = actions;
		this.setContentPane(contentPane);
	}
	
	@Override
	public void getInput(ActionEvent e) 
	{
		
		
	}
}
