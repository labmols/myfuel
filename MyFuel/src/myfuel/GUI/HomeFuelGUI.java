package myfuel.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import myfuel.GUIActions.HomeOrderActions;
import myfuel.Tools.BackMainMenu;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.util.Date;
import java.util.Properties;

import javax.swing.JPanel;
import javax.swing.JTextPane;

import java.awt.GridBagLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.border.MatteBorder;

/**
 * Home Fuel User Interface(Main Frame).
 * Contains Order and Tracking JPanels.
 */
@SuppressWarnings("serial")
public class HomeFuelGUI extends SuperGUI {
	/**
	 * Home Order GUI Controller object.
	 */
	HomeOrderActions actions;
	/**
	 * Make new order Button.
	 */
	private JButton btnNewOrder;
	/**
	 * Tracking order menu option Button.
	 */
	private JButton btnTracking;
	/**
	 * Home Order JPanel.
	 */
	HomeOrderPanel orderPanel;
	/**
	 * Tracking Home Order JPanel.
	 */
	TrackingOrderPanel trackingPanel;
	
	/**
	 * Create new Home Fuel User Interface.
	 * @param actions - Home Fuel GUI Controller.
	 */
	public HomeFuelGUI(HomeOrderActions actions)
	{
		super(actions);
		lblTitle.setBounds(248, 6, 115, 32);
		lblTitle.setText("Home Fuel");
		this.actions = actions;
		this.mainMenu.addActionListener(new BackMainMenu(actions));
		setContentPane(contentPane);
		
		orderPanel = new HomeOrderPanel(actions);
		orderPanel.setBounds(6, 90, 584, 351);
		panel.add(orderPanel);
		
		trackingPanel = new TrackingOrderPanel();
		trackingPanel.setBounds(6, 90, 584, 351);
		panel.add(trackingPanel);
		trackingPanel.setVisible(false);
		
		JPanel optionPanel = new JPanel();
		optionPanel.setOpaque(false);
		optionPanel.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)), "Options", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		optionPanel.setBounds(6, 43, 349, 48);
		panel.add(optionPanel);
		optionPanel.setLayout(null);
		
		btnNewOrder = new JButton("New Order");
		btnNewOrder.addActionListener(new eventListener());
		btnNewOrder.setBounds(66, 11, 117, 29);
		optionPanel.add(btnNewOrder);
		btnNewOrder.setToolTipText("Make new Order");
		
		btnTracking = new JButton("Tracking");
		btnTracking.addActionListener(new eventListener());
		btnTracking.setBounds(199, 11, 117, 29);
		optionPanel.add(btnTracking);
		btnTracking.setToolTipText("Tracking your orders ");
		
		
	}
	
	/**
	 * This class used for handling all components events.
	 *
	 */
	private class eventListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			getInput(e);
		}
		
	}

	@Override
	public void getInput(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btnNewOrder)
		{
			trackingPanel.setVisible(false);
			orderPanel.setVisible(true);
		}
		else 
		{
			orderPanel.setVisible(false);
			trackingPanel.setVisible(true);
		}
	}

	public HomeOrderPanel getOrderPanel() {
		// TODO Auto-generated method stub
		return this.orderPanel;
	}
	
	public TrackingOrderPanel getTrackingPanel() {
		// TODO Auto-generated method stub
		return this.trackingPanel;
	}
}
