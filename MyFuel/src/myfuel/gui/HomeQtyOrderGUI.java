package myfuel.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import myfuel.GUIActions.homeQtyOrderActions;
import myfuel.client.BackMainMenu;
import myfuel.client.FuelQty;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

/***
 * Home Fuel Control User Interface
 * @author karmo
 *
 */
@SuppressWarnings("serial")
public class HomeQtyOrderGUI extends SuperGUI{

	/***
	 * Controller For this GUI
	 */
	private homeQtyOrderActions actions;
	/***
	 * Table for showing the Home Fuel inventory Order
	 */
	private JTable table;
	/***
	 * Setting Low inventory Level
	 */
	private JButton setLowLvl;
	/***
	 * New low lvl from the user
	 */
	private final JTextField lowLvl;
	/***
	 * Showing info to the user
	 */
	private JLabel lblLowLevel;
	/***
	 * Order Confirmation
	 */
	private JButton confirmOrder;
	/***
	 * Table model
	 */
	private DefaultTableModel model;
	/***
	 * Scroll pane for the table
	 */
	private JScrollPane scrollPane;
	/***
	 * Showing current Minimal Quantity
	 */
	private JTextField currMinQty;
	/***
	 * Showing current inventory Quantity
	 */
	private JTextField currQty;
	/***
	 * Indicating if there is a order or not
	 */
	private boolean order;
	/***
	 * Home Fuel Inventory Order details
	 */
	private FuelQty oDetails; // order details
	
	/***
	 * HomeQtyOrderGUI Constructor
	 * @param actions - COntroller for this gui
	 */
	public HomeQtyOrderGUI(homeQtyOrderActions actions)
	{
		lblTitle.setBounds(200, 0, 232, 25);
		lblTitle.setText("Home Fuel Control");
		
		model = new MyTableModel(2,-1);
		this.mainMenu.addActionListener(new BackMainMenu(actions));
		String[] names = {"Order ID","Quantity"};
		for(String str : names)
		{
			model.addColumn(str);
		}
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 117, 212, 106);
		panel.add(scrollPane);
		
		table = new JTable(model);
		scrollPane.setViewportView(table);
		table.setModel(model);
		
		JLabel lblNewOrders = new JLabel("Orders");
		lblNewOrders.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewOrders.setBounds(109, 72, 97, 24);
		panel.add(lblNewOrders);
		
		confirmOrder = new JButton("Confirm Order");
		confirmOrder.addActionListener(new btnHandler());
		confirmOrder.setBounds(81, 298, 125, 49);
		panel.add(confirmOrder);
		
		setLowLvl = new JButton("Set Low Level");
		setLowLvl.addActionListener(new btnHandler());
		setLowLvl.setBounds(343, 298, 125, 49);
		panel.add(setLowLvl);
		
		lowLvl = new JTextField();
		lowLvl.setBounds(432, 187, 59, 25);
		panel.add(lowLvl);
		lowLvl.setColumns(10);
		
		lblLowLevel = new JLabel("Home Fuel Level");
		lblLowLevel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblLowLevel.setBounds(343, 72, 148, 24);
		panel.add(lblLowLevel);
		
		JLabel lblCurrentQuantity = new JLabel("Current Quantity:");
		lblCurrentQuantity.setBounds(291, 126, 107, 25);
		panel.add(lblCurrentQuantity);
		
		JLabel lblMinimalQuantity = new JLabel("Current Low Level:");
		lblMinimalQuantity.setBounds(291, 162, 141, 14);
		panel.add(lblMinimalQuantity);
		
		JLabel lblNewLabel = new JLabel("New Low Level:");
		lblNewLabel.setBounds(291, 192, 118, 14);
		panel.add(lblNewLabel);
		
		currMinQty = new JTextField();
		currMinQty.setEditable(false);
		currMinQty.setColumns(10);
		currMinQty.setBounds(432, 157, 59, 25);
		panel.add(currMinQty);
		
		currQty = new JTextField();
		currQty.setEditable(false);
		currQty.setColumns(10);
		currQty.setBounds(432, 126, 59, 25);
		panel.add(currQty);
		
		this.actions = actions;
		this.setContentPane(contentPane);
	}
	/***
	 *  Insert details from the DB to GUI for company manager
	 * @param m  - minimal quantity and current quantity
	 * @param o - order details
	 */
	public void insertDetails(FuelQty m,FuelQty o)
	{
		if(o == null)
		{
			this.showErrorMessage("There are no new inventory orders for home fuel!");
			order = false;
		}
		else
		{
			this.oDetails = o;
			model.insertRow(0, new Object[]{o.getFid(),o.getQty()});
			order = true;
		}
		
		this.setCurrQty(m.getQty());
		this.setCurrMinQty(m.getMqty());
	}
	
	/***
	 * Update interface for the user
	 * @param f - Minimal Quantity
	 */
	public void setCurrMinQty(Float f )
	{
		this.currMinQty.setText(""+f);
		lowLvl.setText("");
	}
	
	/***
	 * Update interface for the user
	 * @param current - current Quantity
	 */
	public void setCurrQty(Float current) 
	{
		
		this.currQty.setText(""+current);
	}
	
	/***
	 * getting the details from the user interface
	 */
	@Override
	public void getInput(ActionEvent e) 
	{
		String str = null;
		
		if(e.getSource() == confirmOrder)
		{
			if(this.order)
				actions.setOrder();
			else
				this.showErrorMessage("There is no order to confirm!");
		}
		
		else if (e.getSource() == setLowLvl)
		{
			 str = lowLvl.getText();
			 actions.setLowLevel(str);
		}
		
	}
	/***
	 * Button action listener
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
 * Clear the table
 */
	public void clearTable() 
	{
		
		while(model.getRowCount() != 0)
			model.removeRow(0);
		
		order = false;
	}

	
}
