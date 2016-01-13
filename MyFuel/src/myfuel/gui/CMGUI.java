package myfuel.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import myfuel.GUIActions.CMActions;
import myfuel.client.MessageForManager;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;

/***
 * Company Manager User Interface 
 * @author karmo
 *
 */
@SuppressWarnings("serial")
public class CMGUI extends SuperGUI{
	/***
	 * Controller for this GUI
	 */
	private CMActions actions;
	/***
	 * Confirm New Rates Button 
	 */
	private JButton btnConfirmNewRates;
	/***
	 * Show Company Reports Button
	 */
	private JButton btnShowCompanyReports;
	/***
	 * Messages for this user
	 */
	private ArrayList<MessageForManager> msg;
	private JTextArea msgBox;
	/***
	 * CMGUI Constructor
	 * @param actions - Controller for this GUI
	 */
	public CMGUI(CMActions actions,ArrayList<MessageForManager> msg) {
		lblTitle.setBounds(192, 0, 220, 25);
		lblTitle.setText("Network Manager Menu");
		
		btnConfirmNewRates = new JButton("Confirm New Rates");
		btnConfirmNewRates.addActionListener(new btnHandler() );
		btnConfirmNewRates.setBounds(165, 163, 213, 48);
		panel.add(btnConfirmNewRates);
		
		btnShowCompanyReports = new JButton("Show Company Reports");
		btnShowCompanyReports.addActionListener(new btnHandler());
		btnShowCompanyReports.setBounds(165, 228, 213, 48);
		panel.add(btnShowCompanyReports);
		
		JPanel p = new JPanel();
		p.setOpaque(false);
		p.setBounds(96, 56, 349, 96);
		panel.add(p);
		p.setLayout(null);
		
		JLabel lblNotifications = new JLabel("Notifications");
		lblNotifications.setBounds(129, 5, 94, 14);
		p.add(lblNotifications);
		
		msgBox = new JTextArea();
		msgBox.setCaretColor(Color.LIGHT_GRAY);
		msgBox.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		msgBox.setBounds(59, 27, 224, 58);
		
		if(!msg.isEmpty())
		{
			for(MessageForManager str : msg )
				msgBox.append(str.getMsg()+"\n");
		}
		
		else
			msgBox.append("No new messages");
		
		msgBox.setEditable(false);
		p.add(msgBox);
		this.setContentPane(contentPane);
		this.actions = actions;
	}
/***
 *  This method will get the source of ActionEvent and will turn to the correct method at the Controller
 */
	@Override
	public void getInput(ActionEvent e) {
		
		if(e.getSource() == btnConfirmNewRates)
			actions.ConfirmNewRatesWindow();
		
		else if(e.getSource() == btnShowCompanyReports)
		{
			actions.showReportsWindows();
		}
		
	}
	

	
	/***
	 * Action Listener for JButton
	 * @author karmo
	 *
	 */
	private class btnHandler implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			getInput(e);
			
		}
		
	}
}
