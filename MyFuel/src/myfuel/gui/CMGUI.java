package myfuel.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import myfuel.GUIActions.CMActions;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class CMGUI extends SuperGUI{
	
	private CMActions actions;
	private JButton btnConfirmNewRates;
	private JButton btnShowCompanyReports;
	private JLabel msg;
	private JButton homeQty;
	
	public CMGUI(CMActions actions) {
		lblTitle.setBounds(192, 0, 220, 25);
		lblTitle.setText("Company Manager Menu");
		
		btnConfirmNewRates = new JButton("Confirm New Rates");
		btnConfirmNewRates.addActionListener(new btnHandler() );
		btnConfirmNewRates.setBounds(165, 129, 213, 48);
		panel.add(btnConfirmNewRates);
		
		btnShowCompanyReports = new JButton("Show Company Reports");
		btnShowCompanyReports.setBounds(165, 228, 213, 48);
		panel.add(btnShowCompanyReports);
		
		homeQty = new JButton("Home Fuel Quantity");
		homeQty.addActionListener(new btnHandler());
		homeQty.setBounds(165, 318, 213, 48);
		panel.add(homeQty);
		
		msg = new JLabel("No new messages");
		msg.setHorizontalAlignment(SwingConstants.CENTER);
		msg.setBounds(76, 62, 380, 35);
		panel.add(msg);
		this.setContentPane(contentPane);
		this.actions = actions;
	}

	@Override
	public void getInput(ActionEvent e) {
		
		if(e.getSource() == btnConfirmNewRates)
			actions.ConfirmNewRatesWindow();
		
		else if(e.getSource() == homeQty)
			actions.homeQuantity();
		
	}
	
	public void setMsg(String m)
	{
		this.msg.setText(m);
	}
	
	private class btnHandler implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			getInput(e);
			
		}
		
	}
}
