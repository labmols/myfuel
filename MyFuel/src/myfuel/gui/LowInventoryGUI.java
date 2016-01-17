package myfuel.gui;

import java.awt.event.ActionEvent;

import myfuel.GUIActions.LowInventoryActions;
import myfuel.client.BackMainMenu;
import myfuel.client.FuelQty;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JSeparator;
import javax.swing.SwingConstants;

/***
 * User interface for showing the Low inventory level and current levels of fuels 
 * @author karmo
 *
 */
@SuppressWarnings("serial")
public class LowInventoryGUI extends SuperGUI{

	/***
	 * Controller for this GUI
	 */
	private LowInventoryActions actions;
	/****
	 * TextField for getting the suggested low level for 95 fuel type
	 */
	private JTextField LowFuel95;
	/****
	 * TextField for getting the suggested low level for Diesel fuel type
	 */
	private JTextField LowFuelDiesel;
	/****
	 * TextField for getting the suggested low level for Scooter fuel type
	 */
	private JTextField LowFuelScooter;
	/****
	 * TextField for Showing the current  Quantity for 95 fuel type
	 */
	private JTextField Current95;
	/****
	 * TextField for Showing the current  Quantity for Diesel fuel type
	 */
	private JTextField CurrentDiesel;
	/****
	 * TextField for Showing the current  Quantity for Scooter fuel type
	 */
	private JTextField CurrentScooter;
	/****
	 * TextField for Showing the current  low level for 95 fuel type
	 */
	private JTextField min95;
	/****
	 * TextField for Showing the current  low level for Diesel fuel type
	 */
	private JTextField minDiesel;
	/****
	 * TextField for Showing the current  low level for Scooter fuel type
	 */
	private JTextField minScotter;
	
	/***
	 * LowInventoryGUI Constructor
	 * @param actions - controller for this GUI
	 */
	public LowInventoryGUI(LowInventoryActions actions)
	{
		super(actions);
		lblTitle.setBounds(203, 0, 234, 30);
		panel.setLocation(0, 0);
		this.actions = actions;
		this.setContentPane(contentPane);
		lblTitle.setText("Set Low Inventory Level");
		this.mainMenu.addActionListener(new BackMainMenu(actions));
		
		LowFuel95 = new JTextField();
		LowFuel95.setBounds(158, 126, 86, 20);
		panel.add(LowFuel95);
		LowFuel95.setColumns(10);
		
		JLabel lblLowInventory = new JLabel("Fuel 95 :");
		lblLowInventory.setBounds(51, 132, 97, 14);
		panel.add(lblLowInventory);
		
		JButton Update = new JButton("Update");
		Update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actions.verifyDetails(LowFuel95.getText(),LowFuelDiesel.getText(),LowFuelScooter.getText());
			}
		});
		Update.setBounds(225, 286, 127, 41);
		panel.add(Update);
		
		JLabel lblFuelDiesel = new JLabel("Fuel Diesel :");
		lblFuelDiesel.setBounds(51, 163, 80, 14);
		panel.add(lblFuelDiesel);
		
		JLabel lblFuelScooter = new JLabel("Fuel Scooter :");
		lblFuelScooter.setBounds(51, 194, 86, 14);
		panel.add(lblFuelScooter);
		
		LowFuelDiesel = new JTextField();
		LowFuelDiesel.setBounds(158, 157, 86, 20);
		panel.add(LowFuelDiesel);
		LowFuelDiesel.setColumns(10);
		
		LowFuelScooter = new JTextField();
		LowFuelScooter.setBounds(158, 188, 86, 20);
		panel.add(LowFuelScooter);
		LowFuelScooter.setColumns(10);
		
		Current95 = new JTextField();
		Current95.setEditable(false);
		Current95.setBounds(285, 126, 86, 20);
		panel.add(Current95);
		Current95.setColumns(10);
		
		CurrentDiesel = new JTextField();
		CurrentDiesel.setEditable(false);
		CurrentDiesel.setColumns(10);
		CurrentDiesel.setBounds(285, 157, 86, 20);
		panel.add(CurrentDiesel);
		
		CurrentScooter = new JTextField();
		CurrentScooter.setEditable(false);
		CurrentScooter.setColumns(10);
		CurrentScooter.setBounds(285, 188, 86, 20);
		panel.add(CurrentScooter);
		
		JLabel lblCurrent = new JLabel("Current Low Level\r\n");
		lblCurrent.setBounds(419, 93, 127, 25);
		panel.add(lblCurrent);
		
		JLabel lblCurrentQuantities = new JLabel("Current Quantities");
		lblCurrentQuantities.setBounds(275, 95, 111, 14);
		panel.add(lblCurrentQuantities);
		
		min95 = new JTextField();
		min95.setEditable(false);
		min95.setColumns(10);
		min95.setBounds(419, 129, 86, 20);
		panel.add(min95);
		
		minDiesel = new JTextField();
		minDiesel.setEditable(false);
		minDiesel.setColumns(10);
		minDiesel.setBounds(419, 160, 86, 20);
		panel.add(minDiesel);
		
		minScotter = new JTextField();
		minScotter.setEditable(false);
		minScotter.setColumns(10);
		minScotter.setBounds(419, 191, 86, 20);
		panel.add(minScotter);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(138, 93, 7, 160);
		panel.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(258, 93, 7, 160);
		panel.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(381, 93, 7, 160);
		panel.add(separator_2);
		
		JLabel lblUpdate = new JLabel("Update");
		lblUpdate.setBounds(164, 98, 80, 14);
		panel.add(lblUpdate);
		
	}
	@Override
	public void getInput(ActionEvent e) {
		
	}
	
	
	/***
	 * This method will update the textefields with the current low level value and current quantity
	 * @param qty - current details
	 */
	public void setText(ArrayList<FuelQty> qty) 
	{
		min95.setText(qty.get(0).getMqty()+"");
		minDiesel.setText(qty.get(1).getMqty()+"");
		minScotter.setText(qty.get(2).getMqty()+"");
		
		Current95.setText(qty.get(0).getQty()+"");
		CurrentDiesel.setText(qty.get(1).getQty()+"");
		CurrentScooter.setText(qty.get(2).getQty()+"");
		
	}
	
	/***
	 * This method will the current low level with the new low level after the change.
	 * @param newLowInventory - New low inventory levels.
	 */
	public void updateNew(ArrayList<Float> newLowInventory) 
	{
		min95.setText(newLowInventory.get(0)+"");
		minDiesel.setText(newLowInventory.get(1)+"");
		minScotter.setText(newLowInventory.get(2)+"");
		
		LowFuel95.setText("");
		LowFuelDiesel.setText("");
		LowFuelScooter.setText("");
		
	}
}
