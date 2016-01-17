package myfuel.gui;

import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import myfuel.Entity.Promotion;
import myfuel.Entity.PromotionReport;
import myfuel.Tools.TimeIgnoringComparator;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/***
 * This user Interface will Show the details of the Promotion Report
 * @author karmo
 *
 */
@SuppressWarnings("serial")
public class PromtionRPanel extends JPanel{
	/***
	 * Table For showing the report
	 */
	private JTable table;
	/***
	 * Table Model
	 */
	private DefaultTableModel model ; 
	/***
	 * Combobox For picking a promotion
	 */
	private JComboBox<String> comboBox;
	/***
	 * Promoiton Report Details
	 */
	private ArrayList<PromotionReport> p;
	/***
	 * Promtions Details
	 */
	private ArrayList<Promotion> pnames;
	/***
	 * Will show the number of buyers
	 */
	private JLabel buyers ;
	/***
	 * Will show the total incomes
	 */
	private JLabel incomes;
	/***
	 * Will show the end date
	 */
	private JLabel endDate;
	/***
	 * Will show the Start Date
	 */
	private JLabel startDate;
	/***
	 * Will Show the Fuel Type
	 */
	private JLabel fuelType;
	/****
	 * Will show the total Discount amount
	 */
	private JLabel discount;
	/***
	 * Constructor for Promotion Report Panel
	 */
	public PromtionRPanel() {
		setOpaque(false);
		setLayout(null);
		
		 comboBox = new JComboBox<String>();
		 comboBox.addActionListener(new comboHandler());
		comboBox.setBounds(161, 11, 373, 31);
		add(comboBox);
		
		JLabel lblChoosePromotion = new JLabel("Choose Promotion:");
		lblChoosePromotion.setBounds(43, 14, 136, 14);
		add(lblChoosePromotion);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 99, 540, 188);
		add(scrollPane);
		model = new MyTableModel(5,-1);
		String[] names = {"ID" ,"Name","Quantity","Type","Date","Bill"};
		
		for(String s : names)
			model.addColumn(s);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( SwingConstants.CENTER );
		
		table = new JTable(model);
		table.setFont(new Font("Calibri", Font.PLAIN, 13));
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		for (int x = 0; x < model.getColumnCount(); x ++)
		{
	         table. getColumnModel (). getColumn (x). setCellRenderer (centerRenderer);
	     }
		
		JLabel lblBuyers = new JLabel("Buyers:");
		lblBuyers.setBounds(43, 48, 46, 14);
		add(lblBuyers);
		
		JLabel lblNewLabel = new JLabel("Total Incomes:");
		lblNewLabel.setBounds(134, 48, 94, 14);
		add(lblNewLabel);
		
		 buyers = new JLabel("0");
		buyers.setForeground(new Color(0, 0, 0));
		buyers.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		buyers.setBounds(91, 48, 46, 14);
		add(buyers);
		
		 incomes = new JLabel("0");
		incomes.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		incomes.setBounds(226, 48, 104, 14);
		add(incomes);
		
		JLabel lblStartDate = new JLabel("Start Date:");
		lblStartDate.setBounds(43, 81, 79, 14);
		add(lblStartDate);
		
		JLabel lblEndDate = new JLabel("End Date:");
		lblEndDate.setBounds(212, 81, 79, 14);
		add(lblEndDate);
		
		endDate = new JLabel("00");
		endDate.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		endDate.setBounds(283, 81, 104, 14);
		add(endDate);
		
		 startDate = new JLabel("00");
		 startDate.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		startDate.setBounds(124, 81, 136, 14);
		add(startDate);
		
		JLabel lblDiscount = new JLabel("Discount:");
		lblDiscount.setBounds(363, 48, 66, 14);
		add(lblDiscount);
		
		JLabel lblFuelType = new JLabel("Fuel Type:");
		lblFuelType.setBounds(363, 81, 66, 14);
		add(lblFuelType);
		
		fuelType = new JLabel("0");
		fuelType.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		fuelType.setBounds(434, 81, 46, 14);
		add(fuelType);
		
		discount = new JLabel("0");
		discount.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		discount.setBounds(434, 48, 46, 14);
		add(discount);
	}
	
	/***
	 * Action Listener for Combobox
	 * @author karmo
	 *
	 */
	private class comboHandler implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			String str = null;
			float amount = 0;
			int buy = 0 ;
			clearTable();
			
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy ");
			for(PromotionReport pr : p)
			{
				
				
				if((pnames.get((comboBox.getSelectedIndex()))).getPid() == pr.getPid())
				{

					if(pr.getToc() == 0 )
						str = "Private";
					else
						str = "Company";
					
					model.insertRow(model.getRowCount(),new Object[]{pr.getUid(),pr.getFname()+" "+pr.getLname(),new DecimalFormat("##.##").format(pr.getQty()) ,str,df.format(pr.getPdate()),new DecimalFormat("##.##").format(pr.getBill()) + "(NIS)"});
					amount += pr.getBill();
					buy ++;
				
				
				}
			}
			
			incomes.setText(new DecimalFormat("##.##").format(amount) + "(NIS)");
			buyers.setText(""+buy);
			
			
			startDate.setText(df.format(pnames.get(comboBox.getSelectedIndex()).getStartDate()));
			endDate.setText(df.format(pnames.get(comboBox.getSelectedIndex()).getEndDate()));
			fuelType.setText(pnames.get(comboBox.getSelectedIndex()).getNameOfFuel());
			discount.setText(pnames.get(comboBox.getSelectedIndex()).getDiscount()+"%");
		
			
		}
		
	}
	
	/***
	 * Clears the table
	 */
 void clearTable()
{
	while(model.getRowCount() > 0 )
		model.removeRow(0);
}
 
 /***
  *  Sets the combobox objects and sets the ArrayList of the report in this class
  * @param p  - Details for the promotion reports
  * @param pnames - Promotion that were activated at MyFuel
  */
public 	void setCombo(ArrayList<PromotionReport> p,ArrayList<Promotion> pnames)
	{
		this.p = p ;
		this.pnames = pnames;
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy ");
		
		this.startDate.setText(df.format(pnames.get(0).getStartDate()));
		this.endDate.setText(df.format(pnames.get(0).getEndDate()));
		this.fuelType.setText(pnames.get(0).getNameOfFuel());
		this.discount.setText(pnames.get(0).getDiscount()+"%");
		
		for(Promotion s : pnames)
			comboBox.addItem(s.toString());
		
	}
}
