package myfuel.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import myfuel.GUIActions.ConfirmNewRatesActions;
import myfuel.client.BackMainMenu;
import myfuel.client.saleModel;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

public class ConfirmNewRatesGUI extends SuperGUI{
	
	private ConfirmNewRatesActions actions;
	private JButton btnConfirm;
	private JTable table;
	private DefaultTableModel model;
	
	/***
	 * 
	 * @param actions - the controller of the GUI
	 */
	public ConfirmNewRatesGUI(ConfirmNewRatesActions actions)
	{
		lblTitle.setBounds(187, 0, 264, 25);
		lblTitle.setText("New Rates Confirmation");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(47, 90, 498, 217);
		panel.add(scrollPane);
		String[] names = {"Type ID","Type Name","Current","Suggested","Approve/Deny"};
		this.model = new MyTableModel(4,4);
		for(String str : names)
			model.addColumn(str);
		
		
		
		table = new JTable(model);
		table.setModel(model);
		scrollPane.setViewportView(table);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new btnHandler());
		btnConfirm.setBounds(220, 359, 137, 45);
		panel.add(btnConfirm);
		this.actions = actions;
		this.setContentPane(contentPane);
		
		this.mainMenu.addActionListener(new BackMainMenu(actions));
	}

	@Override
	public void getInput(ActionEvent e) 
	{
		
		getApproved();
	}
	
	void getApproved()
	{
		ArrayList<Integer> approved = new ArrayList<Integer>();
		boolean app;
		
		for(int i=0;i<model.getRowCount();i++)
		{
			app = (boolean) model.getValueAt(i, 4);
			if(app)
			approved.add((Integer)model.getValueAt(i,0))	;
		}
		
		if (JOptionPane.showConfirmDialog(null, "Unpicked Rates Will be considered as denied and will be removed from the Suggestion \n "
				+ "Picked Rates will be the new Rates,"
				+ " Are you sure?", "WARNING",
		        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) 
		{
		    // yes option
			
			actions.sendNewRates(approved);
		} 
		
	}
	
	private class btnHandler implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			getInput(e);
			
		}
		
	}

	public void setDetails(ArrayList<saleModel> s, ArrayList<saleModel> c)
	{
		String[] type = {"Monthly Regular - One Car","Monthly Regular - few Cars","Fully monthly"};
		for(int i=0; i < s.size() ; i++)
		{
			model.insertRow(i,new Object[]{s.get(i).getType(),type[i],c.get(i).getDiscount(),s.get(i).getDiscount(),false});
		}
		
	}
}
