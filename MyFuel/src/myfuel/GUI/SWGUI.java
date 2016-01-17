package myfuel.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import myfuel.Entity.FuelQty;
import myfuel.Entity.InventoryOrder;
import myfuel.GUIActions.SWActions;
import myfuel.Tools.BackMainMenu;

import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

import java.awt.Component;
import java.awt.ComponentOrientation;

/***
 * User interface for the Station Worker
 * @author karmo
 *
 */
@SuppressWarnings("serial")
public class SWGUI extends SuperGUI{
	
	/***
	 * Controller for this GUI
	 */
	private SWActions actions;
	/***
	 * Confirmation Button
	 */
	private JButton confirm;
	/***
	 * Station Name
	 */
	private String station_name;
	/***
	 * Table for showing the inventory order
	 */
	private JTable table;
	/***
	 * Table model
	 */
	private DefaultTableModel model;
	/***
	 * Inventory Order for this station
	 */
	private InventoryOrder qty = null;
	
	
	/***
	 * SWGUI Constructor
	 * @param actions - controller for this GUI
	 */
	public SWGUI(SWActions actions)
	{
		super(actions);
		lblTitle.setBounds(187, 0, 241, 25);
		lblTitle.setText("");
		
		 confirm = new JButton("Confirm");
		confirm.setBounds(214, 331, 129, 47);
		confirm.addActionListener(new btnHandler());
		panel.add(confirm);
		this.mainMenu.addActionListener(new BackMainMenu(actions));
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(148, 95, 280, 190);
		panel.add(scrollPane);
		model = new MyTableModel(3,-1);
		table = new JTable(model);
		table.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

		scrollPane.setViewportView(table);
		table.setModel(model);
		String[] names = {"Fuel ID","Type of Fuel","Quantity"};
		
		for(int i=0 ; i<names.length; i++)
			model.addColumn(names[i]);
		
		this.setContentPane(contentPane);
		this.actions = actions;
	}
	
	/***
	 * This method will update the labels in this frame
	 * @param order - order details
	 */
	public void updateLables(InventoryOrder order)
	{
		station_name = "Inventory Orders for"+" "+ order.getStation().getName();
		lblTitle.setText(station_name);
		this.qty = order;
		 for(FuelQty q : order.getfQty())
			model.insertRow(model.getRowCount(), new Object[] {q.getFid(),q.getFname(),q.getQty()});
		
	
	}
	@Override
	public void getInput(ActionEvent e)
	{
		qty = null;
		actions.ConfirmOrder();

	}
	
	/***
	 * Actions listener for handling action events
	 * @author karmo
	 *
	 */
	private class btnHandler implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if( qty != null )
				getInput(e);
			
			
		}
		
	}
	
	/***
	 * This method will delete all details from the table
	 */
	public void deleteTable()
	{
			while(model.getRowCount()!=0)
				model.removeRow(0);
	}

}
