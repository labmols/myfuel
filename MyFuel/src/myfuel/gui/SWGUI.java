package myfuel.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import myfuel.GUIActions.SWActions;

import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

import java.awt.Color;

@SuppressWarnings("serial")
public class SWGUI extends SuperGUI{
	
	private SWActions actions;
	private JTextField f95Qty;
	private JTextField dieselQty;
	private JTextField scooterQty;
	private JButton confirm;
	
	public SWGUI(SWActions actions)
	{
		lblTitle.setBounds(187, 0, 241, 25);
		lblTitle.setText("Station Worker Console");
		
		 confirm = new JButton("Confirm");
		confirm.setBounds(214, 331, 129, 47);
		confirm.addActionListener(new btnHandler());
		panel.add(confirm);
		
		JLabel lblTypeOfFuel = new JLabel("Type of Fuel");
		lblTypeOfFuel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTypeOfFuel.setBounds(125, 80, 148, 25);
		panel.add(lblTypeOfFuel);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblQuantity.setBounds(327, 80, 148, 25);
		panel.add(lblQuantity);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBackground(Color.BLACK);
		separator.setBounds(89, 115, 391, 2);
		panel.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(Color.BLACK);
		separator_1.setForeground(Color.BLACK);
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(272, 80, 2, 181);
		panel.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBackground(Color.BLACK);
		separator_2.setForeground(Color.BLACK);
		separator_2.setBounds(89, 159, 391, 2);
		panel.add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setForeground(Color.BLACK);
		separator_3.setBackground(Color.BLACK);
		separator_3.setBounds(85, 204, 391, 2);
		panel.add(separator_3);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBackground(Color.BLACK);
		separator_4.setForeground(Color.BLACK);
		separator_4.setBounds(85, 243, 391, 2);
		panel.add(separator_4);
		
		JLabel label = new JLabel("95");
		label.setBounds(158, 134, 46, 14);
		panel.add(label);
		
		JLabel lblDiesel = new JLabel("Diesel");
		lblDiesel.setBounds(158, 172, 46, 14);
		panel.add(lblDiesel);
		
		JLabel lblScooter = new JLabel("Scooter");
		lblScooter.setBounds(158, 217, 46, 14);
		panel.add(lblScooter);
		
		f95Qty = new JTextField();
		f95Qty.setBounds(327, 128, 86, 20);
		panel.add(f95Qty);
		f95Qty.setColumns(10);
		
		dieselQty = new JTextField();
		dieselQty.setColumns(10);
		dieselQty.setBounds(327, 173, 86, 20);
		panel.add(dieselQty);
		
		scooterQty = new JTextField();
		scooterQty.setBounds(327, 212, 86, 20);
		panel.add(scooterQty);
		scooterQty.setColumns(10);
		this.setContentPane(contentPane);
		this.actions = actions;
	}
	@Override
	public void getInput(ActionEvent e) {
		String f95 = f95Qty.getText();
		String diesel = dieselQty.getText();
		String scooter = scooterQty.getText();
		actions.verifyDetails(f95, diesel, scooter);

	}
	
	private class btnHandler implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			getInput(e);
			
		}
		
	}
	public SWActions getActions() {
		return actions;
	}
	public void setActions(SWActions actions) {
		this.actions = actions;
	}
}
