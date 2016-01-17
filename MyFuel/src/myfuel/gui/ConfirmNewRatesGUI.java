package myfuel.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import myfuel.Entity.Rate;
import myfuel.GUIActions.ConfirmNewRatesActions;
import myfuel.Tools.BackMainMenu;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

/***
 * User interface for New Rates Confirmation
 * @author karmo
 *
 */
@SuppressWarnings("serial")
public class ConfirmNewRatesGUI extends SuperGUI{
	/***
	 * Controller for this GUI
	 */
	private ConfirmNewRatesActions actions;
	/***
	 * Confirmation Button
	 */
	private JButton btnConfirm;
	/***
	 * Table for showing the Rates
	 */
	private JTable table;
	/***
	 * Table Model
	 */
	private DefaultTableModel model;
	
	/***
	 * ConfirmNewRatesGUI Constructor
	 * @param actions - the controller of this GUI
	 */
	public ConfirmNewRatesGUI(ConfirmNewRatesActions actions)
	{
		super(actions);
		lblTitle.setBounds(187, 0, 264, 25);
		lblTitle.setText("New Rates Confirmation");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(47, 90, 498, 217);
		panel.add(scrollPane);
		String[] names = {"ID","Type Name","Current (%)","Suggested (%)","Y/N "};
		this.model = new MyTableModel(4,4);
		for(String str : names)
			model.addColumn(str);
		
		
		
		table = new JTable(model);
		table.setModel(model);
		scrollPane.setViewportView(table);
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		for(int i = 2 ; i < 4 ; i++)
			table.getColumnModel().getColumn(i).setPreferredWidth(150);
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
	
	/***
	 * Getting approved rates from table
	 */
	void getApproved()
	{
		ArrayList<Rate> approved = new ArrayList<Rate>();
		boolean app;
		
		for(int i=0;i<model.getRowCount();i++)
		{
			app = (boolean) model.getValueAt(i, 4);
			if(app)
				approved.add(new Rate((Integer)model.getValueAt(i,0),null,(Float)model.getValueAt(i,3)))	;
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
	/***
	 * Action Listener for JButton
	 *
	 */
	private class btnHandler implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			getInput(e);
			
		}
		
	}
/***
 * Setting the table info
 * @param s - suggested discounts
 * @param c - current discounts
 */
	public void setDetails(ArrayList<Rate> s, ArrayList<Rate> c)
	{
		String[] type = {"Monthly Regular - One Car","Monthly Regular - few Cars","Fully monthly"};
		for(int i=0; i < s.size() ; i++)
		{
			model.insertRow(i,new Object[]{s.get(i).getModelid(),type[i],c.get(i).getDiscount(),s.get(i).getDiscount(),false});
		}
		
	}
/***
 * Empty the table
 */
	public void clearTable()
	{
		while(model.getRowCount() > 0)
			model.removeRow(0);
		
	}
}
