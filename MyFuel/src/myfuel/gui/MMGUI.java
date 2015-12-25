package myfuel.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import myfuel.GUIActions.MMActions;

public class MMGUI extends SuperGUI {
	JButton btnMakeAPromotion;
	JButton btnShowReports;
	JButton btnSetNewRates;
	MMActions actions;
	/**
	 * Create the frame.
	 */
	public MMGUI(MMActions actions) {
		this.actions = actions;
		setContentPane(contentPane);
		lblTitle.setBounds(160, 6, 250, 22);
		lblTitle.setText("Marketing Manager Menu");
		
		 btnMakeAPromotion = new JButton("Make a Promotion");
		btnMakeAPromotion.setBounds(193, 113, 189, 48);
		panel.add(btnMakeAPromotion);
		btnMakeAPromotion.addActionListener(new btnHandler());
		
		 btnShowReports = new JButton("Show Reports");
		btnShowReports.setBounds(193, 185, 189, 48);
		panel.add(btnShowReports);
		btnShowReports.addActionListener(new btnHandler());
		
		 btnSetNewRates = new JButton("Set New Rates");
		btnSetNewRates.setBounds(193, 260, 189, 48);
		panel.add(btnSetNewRates);
		btnSetNewRates.addActionListener(new btnHandler());
	}

	@Override
	public void getInput(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	/***
	 * ActionListener for the button of this JFrame
	 * The MMActions method will be accessed due to the button pressed
	 * 
	 *
	 */
	private class btnHandler implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == btnMakeAPromotion)
				actions.createMakeaPromotionWindow();
			
			else if(e.getSource() == btnShowReports)
				actions.createShowReportsWindow();
			
			else if(e.getSource() == btnSetNewRates)
				actions.createSetNewRatesWindow();
			
			
		}
		
	}
}
