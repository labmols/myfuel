package myfuel.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import myfuel.GUIActions.HomeOrderActions;
import myfuel.client.BackMainMenu;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

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

@SuppressWarnings("serial")
public class HomeFuelGUI extends SuperGUI {
	
	HomeOrderActions actions;
	private JButton btnNewOrder;
	private JButton btnTracking;
	HomeOrderPanel orderPanel;
	TrackingOrderPanel trackingPanel;
	
	
	public HomeFuelGUI(HomeOrderActions actions)
	{
		lblTitle.setBounds(208, 10, 174, 16);
		lblTitle.setText("Home Fuel Order");
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
		optionPanel.setBorder(new TitledBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)), "Options", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		optionPanel.setBounds(6, 50, 584, 40);
		panel.add(optionPanel);
		optionPanel.setLayout(null);
		
		btnNewOrder = new JButton("New Order");
		btnNewOrder.addActionListener(new eventListener());
		btnNewOrder.setBounds(143, 11, 117, 29);
		optionPanel.add(btnNewOrder);
		btnNewOrder.setToolTipText("Make new Order");
		
		btnTracking = new JButton("Tracking");
		btnTracking.addActionListener(new eventListener());
		btnTracking.setBounds(305, 11, 117, 29);
		optionPanel.add(btnTracking);
		btnTracking.setToolTipText("Tracking your orders ");
		
		
	}
	
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
}
