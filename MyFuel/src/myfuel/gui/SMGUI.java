package myfuel.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import myfuel.GUIActions.SMActions;
import myfuel.client.BackMainMenu;
import myfuel.client.MessageForManager;

import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;

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
	 * SMGUI Constructor
	 * @param actions - Controller for this GUI
	 */
	public SMGUI(SMActions actions, ArrayList<MessageForManager> msg) {
		super(actions);
		this.msg = msg;
		this.mainMenu.addActionListener(new BackMainMenu(actions));
		lblTitle.setBounds(179, 6, 231, 25);
		lblTitle.setText("Station Manager Menu");
		
		 createReports = new JButton("Create Station Reports");
		 createReports.addActionListener(new btnHandler());
		createReports.setBounds(179, 155, 204, 46);
		panel.add(createReports);
		
		 btnSetLowInventory = new JButton("Set Low Inventory Level");
		 btnSetLowInventory.addActionListener(new btnHandler());
		btnSetLowInventory.setBounds(179, 245, 204, 46);
		panel.add(btnSetLowInventory);
		
		 btnCheckInventoryOrder = new JButton("Check Inventory Order");
		 btnCheckInventoryOrder.addActionListener(new btnHandler());
		btnCheckInventoryOrder.setBounds(179, 332, 204, 46);
		panel.add(btnCheckInventoryOrder);
		
		JLabel lblNotifications = new JLabel("notifications");
		lblNotifications.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNotifications.setBounds(230, 42, 168, 33);
		panel.add(lblNotifications);
		
		JTextArea textArea = new JTextArea();
		textArea.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		textArea.setBounds(179, 77, 204, 46);
		
		if(msg.isEmpty())
		{
			textArea.append("No new messages");
		}
		else
		{
			for(MessageForManager m : msg)
			{
				textArea.append(m.getMsg()+"\n");
			}
		}
		textArea.setEditable(false);
		panel.add(textArea);
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
