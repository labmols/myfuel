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

/***
 * User interface for the Marketing Manager
 * @author karmo
 *
 */
public class MMGUI extends SuperGUI {
	/***
	 * Make a Promotion Button
	 */
	private JButton btnMakeAPromotion;
	/***
	 * Show Reports Button
	 */
	private JButton btnShowReports;
	/***
	 * Set New Rates BUtton
	 */
	private JButton btnSetNewRates;
	/***
	 * Controller for this GUI
	 */
	private MMActions actions;
	/***
	 * Analyzed Details BUtton
	 */
	private JButton aDetails;
	/***
	 * MMGUI Constructor
	 * @param actions - controller for this GUI
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
		
		aDetails = new JButton("Analyzed Details");
		aDetails.addActionListener(new btnHandler());
		aDetails.setBounds(193, 334, 189, 48);
		panel.add(aDetails);
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
			
			else if(e.getSource() == aDetails)
				actions.createaDetailsWindow();
			
			
		}
		
	}
}
