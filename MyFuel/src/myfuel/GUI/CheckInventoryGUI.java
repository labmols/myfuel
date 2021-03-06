package myfuel.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import myfuel.Entity.Customer;
import myfuel.Entity.FuelQty;
import myfuel.GUIActions.CheckInventoryActions;
import myfuel.GUIActions.ConfirmationActions;
import myfuel.Tools.BackMainMenu;

import javax.swing.JLabel;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

/***
 * This class will be user interface for Station Worker Inventory Confirmation
 *
 */
@SuppressWarnings("serial")
public class CheckInventoryGUI extends SuperGUI{

	/***
	 * Controller for this GUI
	 */
	private CheckInventoryActions actions;
	/***
	 * Table model
	 */
	private DefaultTableModel model;
	/***
	 * Table for showing the details
	 */
	private JTable table;
	/***
	 * Confirmation Button
	 */
	private JButton Confirm ;
	
	/***
	 * CheckInventoryGUI Constructor
	 * @param actions - Controller for this GUI
	 */
	public CheckInventoryGUI(CheckInventoryActions actions,String StationName) {
		super(actions);
		this.actions = actions;
		this.setContentPane(contentPane);
		this.mainMenu.addActionListener(new BackMainMenu(actions));
		lblTitle.setBounds(168, 0, 360, 25);
		lblTitle.setText("Inventory Orders for"+" "+StationName);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(57, 72, 501, 156);
		panel.add(scrollPane);
		scrollPane.setOpaque(false);
		model = new MyTableModel(3,3);
		String[] columns = new String[] {"Fuel ID", "Fuel Name", "Quantity","Approve/Deny"};
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
		
		Confirm = new JButton("Confirm");
		Confirm.addActionListener(new ActionListener() { 
			/***
			 * Action Listener Method
			 */
			public void actionPerformed(ActionEvent arg0) {
				getInput(arg0);
			}
		});
		Confirm.setBounds(246, 272, 118, 41);
		panel.add(Confirm);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(110);
		table.getColumnModel().getColumn(1).setPreferredWidth(112);
		table.getColumnModel().getColumn(2).setPreferredWidth(117);
		table.getColumnModel().getColumn(3).setPreferredWidth(103);
	}
	
	/***
	 * This method will update the table with the Order details
	 * @param NewOrder - order Details
	 */
	public void setDetails(ArrayList<FuelQty> NewOrder)
	{
		String type="";
		for(FuelQty c : NewOrder)
		{
			if(c.getFid() == 1 )
				type = "95";
			else if(c.getFid()==2)
				type = "Diesel";
			else if(c.getFid()==3)
				type = "Scooter";
			model.insertRow(model.getRowCount(),new Object[]{c.getFid(),type,c.getQty(),false});
		}
	}
	
	/***
	 * Getting the input from the Attributes of this GUI
	 */
	@Override
	public void getInput(ActionEvent e) {
		
		ArrayList <Integer> FuelId=new ArrayList <Integer>();
		boolean app;
		for(int i=0;i<model.getRowCount();i++)
		{
			app = (boolean) model.getValueAt(i, 3);
			if(app)
				FuelId.add((Integer)model.getValueAt(i,0))	;
		}
		actions.UpdateInventory(FuelId);
	}
}
