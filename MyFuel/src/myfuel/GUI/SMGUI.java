package myfuel.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import myfuel.Entity.MessageForManager;
import myfuel.GUIActions.SMActions;
import myfuel.Tools.BackMainMenu;

import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;

import com.alee.managers.notification.NotificationManager;

/***
 * Station Manager User Interface
 * @author karmo
 *
 */
@SuppressWarnings("serial")
public class SMGUI extends SuperGUI{

	/***
	 * Controller for this GUI
	 */
	private SMActions actions;
	/***
	 * Create Reports Button
	 */
	private JButton createReports;
	/***
	 * Set Low Inventory Button
	 */
	private JButton btnSetLowInventory;
	/***
	 * Check Inventory Order Button
	 */
	private JButton btnCheckInventoryOrder;
	/***
	 * Messages For This User
	 */
	private ArrayList<MessageForManager> msg;
	
	/***
	 * Station Name
	 */
	private String sname;
	/***
	 * SMGUI Constructor
	 * @param actions - controller for this class
	 * @param msg - messages for this manager
	 * @param sname - station name
	 */
	public SMGUI(SMActions actions, ArrayList<MessageForManager> msg,String sname) {
		super(actions);
		this.msg = msg;
		this.mainMenu.addActionListener(new BackMainMenu(actions));
		lblTitle.setBounds(179, 6, 231, 25);
		lblTitle.setText(sname +" "+"Manager Menu");
		
		 createReports = new JButton("Create Station Reports");
		 createReports.addActionListener(new btnHandler());
		createReports.setBounds(179, 72, 204, 71);
		panel.add(createReports);
		
		 btnSetLowInventory = new JButton("Set Low Inventory Level");
		 btnSetLowInventory.addActionListener(new btnHandler());
		btnSetLowInventory.setBounds(179, 183, 204, 71);
		panel.add(btnSetLowInventory);
		
		 btnCheckInventoryOrder = new JButton("Check Inventory Order");
		 btnCheckInventoryOrder.addActionListener(new btnHandler());
		btnCheckInventoryOrder.setBounds(179, 307, 204, 71);
		panel.add(btnCheckInventoryOrder);
		
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
		this.actions = actions;
		this.setContentPane(contentPane);
	}

	@Override
	public void getInput(ActionEvent e) 
	{
		if(e.getSource() == btnCheckInventoryOrder)
		{
			actions.CreateCheckInventoryWindow();
		}
		
		else if(e.getSource() == btnSetLowInventory)
		{
			actions.CreateLowInventoryWindow();
		}
		
		else if(e.getSource() == createReports)
		{
			actions.CreateReports();
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
