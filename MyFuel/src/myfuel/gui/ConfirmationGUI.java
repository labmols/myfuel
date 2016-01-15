package myfuel.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import myfuel.GUIActions.ConfirmationActions;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;

import myfuel.client.*;

/***
 * Marketing Delegate Customer Confirmation User Interface
 * @author karmo
 *
 */
@SuppressWarnings("serial")
public class ConfirmationGUI extends SuperGUI{

	/***
	 * Controller for this GUI
	 */
	private ConfirmationActions actions;
	/***
	 * Table Model
	 */
	private DefaultTableModel model;
	/***
	 * Table
	 */
	private JTable table;
	/****
	 * Customer ArrayList
	 */
	private ArrayList<Customer> c ; 
	/***
	 * Check Box
	 */
	JCheckBox c2;
	
	/***
	 * ConfirmationGUI constructor
	 * @param actions - controller for this GUI
	 */
	public ConfirmationGUI(ConfirmationActions actions)
	{
		super(actions);
		this.actions = actions;
		this.setContentPane(contentPane);
		this.mainMenu.addActionListener(new BackMainMenu(actions));
		lblTitle.setBounds(168, 0, 360, 25);
		lblTitle.setText("Customers Waiting For Confirmation");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(57, 72, 501, 264);
		panel.add(scrollPane);
		model = new MyTableModel(4,4);
		String[] columns = new String[] {"ID", "First Name", "Last Name", "Type", "Approve/Deny"};
		c2 = new JCheckBox();
		table = new JTable(model);
		
	
		
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		for(int i=0; i< columns.length; i++)
		{
			model.addColumn(columns[i]);
		}
		
		scrollPane.setViewportView(table);
		table.setSurrendersFocusOnKeystroke(true);
		table.setOpaque(false);
		table.setModel(model);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(110);
		table.getColumnModel().getColumn(1).setPreferredWidth(112);
		table.getColumnModel().getColumn(2).setPreferredWidth(117);
		table.getColumnModel().getColumn(3).setPreferredWidth(103);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new btnHandler());
		btnConfirm.setBounds(231, 377, 115, 36);
		panel.add(btnConfirm);
		
		
		
	}
	
	/***
	 * will set the details of the customers to the table
	 * @param size - number of customers
	 * @param customers - customer details
	 */
	public void setDetails(int size,ArrayList<Customer> customers)
	{
		String type ;
		for(Customer c : customers)
		{
			if(c.getToc() == 0 )
				type = "Private";
			else
				type = "Company";
			model.insertRow(model.getRowCount(),new Object[]{c.getUserid(),c.getFname(),c.getLname(),type,false});
			
		}
	 
	}
	/***
	 * Will get the approved customers from the table
	 */
	@Override
	public void getInput(ActionEvent e) 
	{
		ArrayList<Integer> approved = new ArrayList<Integer>();
		boolean app;
		for(int i=0;i<model.getRowCount();i++)
		{
			app = (boolean) model.getValueAt(i, 4);
			if(app)
			approved.add((Integer)model.getValueAt(i,0))	;
		}
		if(approved.isEmpty())
			showErrorMessage("You have to pick a customer!");
		else
		actions.sendApproved(approved);
		
	}
	
	/***
	 * JButton ActionListener
	 * @author karmo
	 *
	 */
	private class btnHandler implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			getInput(e);
			
		}
		
	}

/***
 * updating the table after the Marketing Delegate approved the customers
 * @param approved - the id of the approved customers
 */
	public void updateTable(ArrayList<Integer> approved) {
		
		
		int n = model.getRowCount();
		System.out.println(n);
		int index =0;
		
		for(int i=0; i<n; i++)
		{
			 if(approved.contains((Integer)model.getValueAt(index, 0)))
			 {
				model.removeRow(index);
			 }
			 else index ++ ;
		}
		
	
		
	
		
	}

	
	
}
