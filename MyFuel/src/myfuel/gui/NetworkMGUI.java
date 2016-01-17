package myfuel.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import myfuel.Entity.MessageForManager;
import myfuel.GUIActions.CMActions;
import myfuel.Tools.BackMainMenu;

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

import com.alee.managers.notification.NotificationManager;

/***
 * Network Manager User Interface 
 * @author karmo
 *
 */
@SuppressWarnings("serial")
public class NetworkMGUI extends SuperGUI{
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
	/***
	 * NMGUI Constructor
	 * @param actions - Controller for this GUI
	 */
	public NetworkMGUI(CMActions actions,ArrayList<MessageForManager> msg) {
		super(actions);
		lblTitle.setBounds(192, 0, 220, 25);
		lblTitle.setText("Network Manager Menu");
		this.mainMenu.addActionListener(new BackMainMenu(actions));
		btnConfirmNewRates = new JButton("Confirm New Rates");
		btnConfirmNewRates.addActionListener(new btnHandler() );
		btnConfirmNewRates.setBounds(165, 126, 213, 64);
		panel.add(btnConfirmNewRates);
		
		btnShowCompanyReports = new JButton("Show Company Reports");
		btnShowCompanyReports.addActionListener(new btnHandler());
		btnShowCompanyReports.setBounds(165, 228, 213, 64);
		panel.add(btnShowCompanyReports);
		
		if(msg.isEmpty())
		{
			NotificationManager.setLocation(2);
			NotificationManager.showNotification (this,"No new Messages for you" );
		}
		else
		{

			
			for(MessageForManager m : msg)
			{
				NotificationManager.setLocation(2);
				NotificationManager.showNotification (this,m.getMsg() );
			}
		}
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
