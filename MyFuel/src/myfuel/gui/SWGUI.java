package myfuel.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import myfuel.GUIActions.SWActions;
import myfuel.client.InventoryOrder;

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

@SuppressWarnings("serial")
public class SWGUI extends SuperGUI{
	
	private SWActions actions;
	private JButton confirm;
	private String str;
	private JTable table;
	private DefaultTableModel model;
	private ArrayList<Float> q = null;
	private ArrayList<String> s = null;
	
	
	public SWGUI(SWActions actions)
	{
		lblTitle.setBounds(187, 0, 241, 25);
		lblTitle.setText("");
		
		 confirm = new JButton("Confirm");
		confirm.setBounds(214, 331, 129, 47);
		confirm.addActionListener(new btnHandler());
		panel.add(confirm);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(148, 95, 280, 190);
		panel.add(scrollPane);
		model = new MyTableModel();
		table = new JTable(model);
		table.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		table.setAlignmentX(Component.LEFT_ALIGNMENT);
		table.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

		scrollPane.setViewportView(table);
		table.setModel(model);
		String[] names = {"Type of Fuel","Quantity"};
		
		for(int i=0 ; i<names.length; i++)
			model.addColumn(names[i]);
		
		this.setContentPane(contentPane);
		this.actions = actions;
	}
	
	
	public void updateLables(InventoryOrder order)
	{
		str = "Inventory Orders for"+" "+ order.getStation().getName();
		lblTitle.setText(str);
		 q = order.getQty();
		 s = order.getFnames();
		
		for(int i = 0 ; i < s.size() ; i ++ )
			model.insertRow(model.getRowCount(), new Object[] {s.get(i),q.get(i)});
		
	
	}
	@Override
	public void getInput(ActionEvent e)
	{
		
		actions.ConfirmOrder();

	}
	
	private class btnHandler implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if( q != null && s!= null)
				getInput(e);
			
			
		}
		
	}
	public SWActions getActions() {
		return actions;
	}
	public void setActions(SWActions actions) {
		this.actions = actions;
	}
}
