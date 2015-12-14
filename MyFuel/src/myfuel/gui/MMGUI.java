package myfuel.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import myfuel.GUIActions.MMActions;

public class MMGUI extends SuperGUI {

	MMActions actions;
	/**
	 * Create the frame.
	 */
	public MMGUI(MMActions actions) {
		this.actions = actions;
		setContentPane(contentPane);
		lblTitle.setBounds(160, 6, 250, 22);
		lblTitle.setText("Marketing Manager Menu");
		
		JButton btnMakeAPromotion = new JButton("Make a Promotion");
		btnMakeAPromotion.setBounds(201, 115, 127, 48);
		panel.add(btnMakeAPromotion);
		
		JButton btnShowReports = new JButton("Show Reports");
		btnShowReports.setBounds(201, 197, 127, 48);
		panel.add(btnShowReports);
	}

	@Override
	public void getInput(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
