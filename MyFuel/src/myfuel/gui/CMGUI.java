package myfuel.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import myfuel.GUIActions.CMActions;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import java.awt.Color;

@SuppressWarnings("serial")
public class CMGUI extends SuperGUI{
	
	private CMActions actions;
	private JButton btnConfirmNewRates;
	private JButton btnShowCompanyReports;
	private JButton homeQty;
	
	public CMGUI(CMActions actions) {
		lblTitle.setBounds(192, 0, 220, 25);
		lblTitle.setText("Company Manager Menu");
		
		btnConfirmNewRates = new JButton("Confirm New Rates");
		btnConfirmNewRates.addActionListener(new btnHandler() );
		btnConfirmNewRates.setBounds(165, 163, 213, 48);
		panel.add(btnConfirmNewRates);
		
		btnShowCompanyReports = new JButton("Show Company Reports");
		btnShowCompanyReports.addActionListener(new btnHandler());
		btnShowCompanyReports.setBounds(165, 228, 213, 48);
		panel.add(btnShowCompanyReports);
		
		homeQty = new JButton("Home Fuel Quantity");
		homeQty.addActionListener(new btnHandler());
		homeQty.setBounds(165, 287, 213, 48);
		panel.add(homeQty);
		
		JPanel p = new JPanel();
		p.setOpaque(false);
		p.setBounds(96, 56, 349, 96);
		panel.add(p);
		p.setLayout(null);
		
		JTextPane textPane = new JTextPane();
		textPane.setDisabledTextColor(Color.WHITE);
		textPane.setEditable(false);
		textPane.setBounds(22, 30, 299, 55);
		p.add(textPane);
		
		JLabel lblNotifications = new JLabel("Notifications");
		lblNotifications.setBounds(129, 5, 94, 14);
		p.add(lblNotifications);
		this.setContentPane(contentPane);
		this.actions = actions;
	}

	@Override
	public void getInput(ActionEvent e) {
		
		if(e.getSource() == btnConfirmNewRates)
			actions.ConfirmNewRatesWindow();
		
		else if(e.getSource() == homeQty)
			actions.homeQuantity();
		else if(e.getSource() == btnShowCompanyReports)
		{
			actions.showReportsWindows();
		}
		
	}
	
	/*public void setMsg(String m)
	{
		this.msg.setText(m);
	}*/
	
	private class btnHandler implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			getInput(e);
			
		}
		
	}
}
