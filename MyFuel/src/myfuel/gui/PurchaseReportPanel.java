package myfuel.gui;

import javax.swing.JPanel;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import myfuel.Entity.Purchase;

import javax.swing.JLabel;

import java.awt.Font;

/***
 * User Interface for Showing Purchase Report 
 * @author karmo
 *
 */
@SuppressWarnings("serial")
public class PurchaseReportPanel extends JPanel {
	/***
	 * Table for showing report details
	 */
	private JTable table;
	/***
	 * ComboBox for picking FuelType
	 */
	protected JComboBox<String> fuelType;
	/***
	 * JTable Model
	 */
	protected DefaultTableModel model;
	/***
	 * Purchase Report Details
	 */
	private ArrayList<Purchase> purchase;
	/***
	 * Total Quantity Label
	 */
	protected JLabel qtyLabel;
	/***
	 * Total Bill Label
	 */
	protected JLabel billLabel;
	/***
	 * Choose Fuel Type
	 */
	protected JLabel lblChooseFuelType;
	
	/***
	 * PurchaseReportPanel Constructor
	 */
	public PurchaseReportPanel() {
		setOpaque(false);
		setBounds(new Rectangle(42, 110, 517, 310));
		setLayout(null);
		purchase = null;
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 58, 430, 213);
		add(scrollPane);
		String[] names = {"Customer ID","BIll","Quantity"};
		model = new MyTableModel(3,-1);
		for(String str : names)
		{
			model.addColumn(str);
		}
		
		table = new JTable(model);
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		 fuelType = new JComboBox<String>();
		 fuelType.addActionListener(new comboHandler());
		fuelType.setModel(new DefaultComboBoxModel<String>(new String[] {"All","95", "Diesel", "Scooter"}));
		fuelType.setBounds(255, 11, 128, 36);
		add(fuelType);
		
		JLabel lblTotalBill = new JLabel("Total Bill :");
		lblTotalBill.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTotalBill.setBounds(10, 276, 90, 23);
		add(lblTotalBill);
		
		billLabel = new JLabel("0");
		billLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		billLabel.setBounds(90, 282, 95, 14);
		add(billLabel);
		
		JLabel lblTotalQuantity = new JLabel("Total Quantity:");
		lblTotalQuantity.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblTotalQuantity.setBounds(279, 279, 115, 17);
		add(lblTotalQuantity);
		
		qtyLabel = new JLabel("0");
		qtyLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		qtyLabel.setBounds(381, 282, 90, 14);
		add(qtyLabel);
		
		lblChooseFuelType = new JLabel("Choose Fuel Type:");
		lblChooseFuelType.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblChooseFuelType.setBounds(90, 17, 167, 20);
		add(lblChooseFuelType);
	}
	
	/***
	 * Action Listener will handle an event that occurred with an object it attached to.
	 * @author karmo
	 *
	 */
	protected class comboHandler implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			float bill = 0;
			float qty = 0;
			clearTable();
			if(fuelType.getSelectedIndex() == 0 )
				setTable(purchase);
			
			else
			{
				for(Purchase p : purchase )
				{
						if(fuelType.getSelectedIndex() == p.getFuelid())
						{
							qty += p.getQty();
							bill += p.getBill();
							model.insertRow(model.getRowCount(), new Object[]{p.getCustomerid(),new DecimalFormat("##.##").format(p.getBill()),new DecimalFormat("##.##").format(p.getQty())});
						}
				}
				
		
				 billLabel.setText( new DecimalFormat("##.##").format(bill) + "(NIS)");
				
				 qtyLabel.setText( new DecimalFormat("##.##").format(qty) + "(L)");
			}
			
		}
		
	}
	/***
	 * This method will set the necessary elements of this panel for showing the Report
	 * @param purchase - Purchase Report Details
	 */
	public void setTable(ArrayList<Purchase> purchase)
	{
		if(this.purchase == null)
			this.purchase = purchase;
		
		clearTable();
		float bill = 0;
		float qty = 0;
		
		for(Purchase p : purchase)
		{
			qty += p.getQty();
			bill += p.getBill();
			model.insertRow(model.getRowCount(), new Object[]{p.getCustomerid(),	new DecimalFormat("##.##").format(p.getBill()),new DecimalFormat("##.##").format(p.getQty())});
		}
	
		 billLabel.setText(new DecimalFormat("##.##").format(bill) + "(NIS)");
		 qtyLabel.setText(new DecimalFormat("##.##").format(qty) + "(L)");
	}
	/***
	 * This method Clears the JTable and The Labels
	 */
	public void  clearTable()
	{
		billLabel.setText("0"); qtyLabel.setText("0");
		while(model.getRowCount() > 0)
			model.removeRow(0);
	}
}
