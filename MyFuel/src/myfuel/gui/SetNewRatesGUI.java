package myfuel.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import myfuel.GUIActions.SetNewRatesActions;

public class SetNewRatesGUI extends SuperGUI {

	SetNewRatesActions actions;
	/**
	 * Create the frame.
	 */
	public SetNewRatesGUI(SetNewRatesActions actions) {
		this.actions = actions;
		lblTitle.setBounds(271, 6, 162, 16);
		lblTitle.setText("Set New Rates");
		setContentPane(contentPane);
	}

	@Override
	public void getInput(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
