package myfuel.gui;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import myfuel.GUIActions.ConfirmationActions;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import myfuel.client.*;
@SuppressWarnings("serial")
public class ConfirmationGUI extends SuperGUI{

	private ConfirmationActions actions;
	private DefaultTableModel model;
	private JTable table;
	public ConfirmationGUI(ConfirmationActions actions)
	{
		this.actions = actions;
		this.setContentPane(contentPane);
		this.mainMenu.addActionListener(new BackMainMenu(actions));
		lblTitle.setBounds(168, 0, 360, 25);
		lblTitle.setText("Customers Waiting For Confirmation");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(57, 72, 501, 273);
		panel.add(scrollPane);
		
		model = new DefaultTableModel();
		String[] columns = new String[] {"ID", "First Name", "Last Name", "Type", "Approve/Deny"};
		
		table = new JTable(model);
		
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		for(int i=0; i< columns.length; i++)
		{
			model.addColumn(columns[i]);
		}
		scrollPane.setViewportView(table);
		table.setSurrendersFocusOnKeystroke(true);
		table.setModel(model);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(110);
		table.getColumnModel().getColumn(1).setPreferredWidth(112);
		table.getColumnModel().getColumn(2).setPreferredWidth(117);
		table.getColumnModel().getColumn(3).setPreferredWidth(103);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.setBounds(231, 377, 115, 36);
		panel.add(btnConfirm);
		
		
		
	}
	
	public void setDetails(int size,ArrayList<Customer> customers)
	{
		String type ;
		for(Customer c : customers)
		{
			if(c.getToc() == 0 )
				type = "Company";
			else
				type = "Private";
			model.insertRow(model.getRowCount(),new String[]{""+c.getUserid(),c.getFname(),c.getLname(),type});
			
		}
		 
	}
	@Override
	public void getInput(ActionEvent e) {
		
		
	}
}
