package myfuel.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import myfuel.GUIActions.GUIActions;
import myfuel.GUIActions.PurchaseActions;
import myfuel.client.BackMainMenu;
import myfuel.client.HomeOrder;
import myfuel.client.Purchase;

import javax.swing.JLabel;

/**
 * Customer Purchase User interface, display all the purchase history for the customer.
 * @author Maor
 *
 */
@SuppressWarnings("serial")
public class CustomerPurchaseGUI extends SuperGUI {

	/**
	 * Table model.
	 */
	private MyTableModel tableModel;
	/**
	 * Table that contains all the customer purchases details.
	 */
	private JTable purchaseTable;
	/**
	 * Purchase GUI Controller object, for handle all the logic functionality.
	 */
	private PurchaseActions actions;
	
	/**
	 * Mapping between ComboBox item to Date object.
	 */
	private HashMap<Integer,Date> comboMap;
	/**
	 * Date ComboBox(choosing the desired month).
	 */
	private JComboBox<String> dateCombo;
	/**
	 * ComboBox model(used for combobox functions).
	 */
	private DefaultComboBoxModel<String> comboModel;
	/**
	 *Customer Purchase history list.
	 */
	private ArrayList<Purchase> pList;
	/**
	 * Total charges for month label
	 */
	private JLabel totalLabel;
	
	/**
	 * Create new Customer Purchase User interface.
	 * @param actions - GUI Controller object,for handle all the logic functionality.
	 */
	public CustomerPurchaseGUI(PurchaseActions actions)
	{
		super(actions);
		this.actions = actions;
		this.mainMenu.addActionListener(new BackMainMenu(actions));
		setContentPane(contentPane);
		panel.setLocation(0, 0);
		lblTitle.setBounds(226, 6, 175, 31);
		lblTitle.setText("Purchase History");
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 123, 584, 229);
		tableModel = new MyTableModel(6,-1);
		String[] names = {"#" ,"Car","Station","Fuel","Date","Amount","Price","Status"};
		for(String s : names)
			tableModel.addColumn(s);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( SwingConstants.CENTER );
		
		purchaseTable = new JTable(tableModel);
		purchaseTable.setFont(new Font("Arial", Font.PLAIN, 12));
		purchaseTable.setModel(tableModel);
		purchaseTable.getColumnModel().getColumn(0).setPreferredWidth(30);
		purchaseTable.getColumnModel().getColumn(4).setPreferredWidth(90);
		for (int x = 0; x < tableModel.getColumnCount(); x ++)
		{
			purchaseTable. getColumnModel (). getColumn (x). setCellRenderer (centerRenderer);
	     }
		scrollPane.setViewportView(purchaseTable);
		panel.add(scrollPane);
		
		JLabel lblHereYouCan = new JLabel("Here you can find all your purchases :");
		lblHereYouCan.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		lblHereYouCan.setBounds(6, 93, 274, 16);
		panel.add(lblHereYouCan);
		
		comboMap = new HashMap<Integer, Date>();
		comboModel = new DefaultComboBoxModel<String>();
		dateCombo = new JComboBox<String>();
		dateCombo.setFont(new Font("Arial", Font.PLAIN, 13));
		dateCombo.addItemListener(new comboListener());
		dateCombo.setBounds(289, 54, 112, 27);
		dateCombo.setModel(comboModel);
		panel.add(dateCombo);
		
		JLabel lblShowOnlyFor = new JLabel("Choose Month: ");
		lblShowOnlyFor.setFont(new Font("Arial", Font.BOLD, 13));
		lblShowOnlyFor.setBounds(190, 58, 101, 16);
		panel.add(lblShowOnlyFor);
		
		JLabel lblTotalChargeFor = new JLabel("Total charge for this month: ");
		lblTotalChargeFor.setFont(new Font("Arial", Font.BOLD, 14));
		lblTotalChargeFor.setBounds(62, 385, 210, 16);
		panel.add(lblTotalChargeFor);
		
		totalLabel = new JLabel("");
		totalLabel.setFont(new Font("Arial", Font.BOLD, 14));
		totalLabel.setBounds(267, 385, 162, 16);
		panel.add(totalLabel);
		
	}
	@Override
	public void getInput(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	private class comboListener implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			if(e.getStateChange()==ItemEvent.DESELECTED )
			{
				Date d = comboMap.get(dateCombo.getSelectedIndex());
				System.out.println(d);
				sortDate(d);
			}
		}
		
	}
	
	public void addDates(ArrayList<Date> dateList)
	{
		SimpleDateFormat format = new SimpleDateFormat("MM/yy");
		for(Date d: dateList)
		{
			System.out.println(d);
			String dateString = format.format(d);
			comboMap.put(comboModel.getSize(), d);
			comboModel.addElement(dateString);
		}
		sortDate(comboMap.get(0));
	}
	
	public void Init(ArrayList<Purchase> pList)
	{
		this.pList = pList;
	}
	
	@SuppressWarnings("deprecation")
	public void sortDate(Date d) {
		clearTable();
		String sdate="";
		String paid = "";
		float total= 0;
		if(pList != null && pList.size() > 0)
		{
			for(Purchase p: pList)
			{
			if(p.isPaid()) paid = "Paid";
			else paid="Not Paid";
			Date date = p.getPdate();
			if(date.getYear() == d.getYear() && date.getMonth()==d.getMonth())
			{
			total += p.getBill();
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy HH:mm");
			sdate = format.format(date);
			tableModel.insertRow(tableModel.getRowCount(),new Object[]{p.getPid(),p.getCustomerCarID(),p.getSname(),p.getFuelName(),sdate,new DecimalFormat("##.##").format(p.getQty())+"(L)",new DecimalFormat("##.##").format(p.getBill())+" NIS",paid});
			}
			}
		totalLabel.setText(new DecimalFormat("##.##").format(total) +" NIS");
			
		}
		else
		{
			this.showOKMessage("You don't have any purchase history recorded!");
			actions.backToMenu();
		}
			
		
	}
	
	
	/**
	 * Clear all table.
	 */
	private void clearTable()
	{
		while(tableModel.getRowCount() > 0 )
			tableModel.removeRow(0);
	}
}
