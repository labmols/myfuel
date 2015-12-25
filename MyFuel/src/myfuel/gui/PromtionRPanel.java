package myfuel.gui;

import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import myfuel.client.PromotionReport;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class PromtionRPanel extends JPanel{
	private JTable table;
	private DefaultTableModel model ; 
	private JComboBox<String> comboBox;
	private ArrayList<PromotionReport> p;
	private JLabel buyers ;
	private JLabel incomes;
	
	public PromtionRPanel() {
		setOpaque(false);
		setLayout(null);
		
		 comboBox = new JComboBox();
		 comboBox.addActionListener(new comboHandler());
		comboBox.setBounds(293, 25, 143, 20);
		add(comboBox);
		
		JLabel lblChoosePromotion = new JLabel("Choose Promotion:");
		lblChoosePromotion.setBounds(116, 28, 136, 14);
		add(lblChoosePromotion);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 87, 540, 188);
		add(scrollPane);
		model = new MyTableModel(5,-1);
		String[] names = {"ID" ,"Name","Quantity","Customer Type","Fuel","Bill"};
		
		for(String s : names)
			model.addColumn(s);
		
		table = new JTable(model);
		table.setFont(new Font("Calibri", Font.PLAIN, 13));
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		JLabel lblBuyers = new JLabel("Buyers:");
		lblBuyers.setBounds(78, 62, 46, 14);
		add(lblBuyers);
		
		JLabel lblNewLabel = new JLabel("Total Incomes:");
		lblNewLabel.setBounds(256, 62, 94, 14);
		add(lblNewLabel);
		
		 buyers = new JLabel("0");
		buyers.setForeground(new Color(0, 0, 0));
		buyers.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		buyers.setBounds(147, 62, 46, 14);
		add(buyers);
		
		 incomes = new JLabel("0");
		incomes.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		incomes.setBounds(360, 62, 46, 14);
		add(incomes);
	}
	
	private class comboHandler implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			String str = null;
			float amount = 0;
			int buy = 0 ;
			clearTable();
			for(PromotionReport pr : p)
			{
				if(((String)(comboBox.getSelectedItem())).equals(pr.getPname()))
				{
					if(pr.getToc() == 0 )
						str = "Private";
					else
						str = "Company";
					
					model.insertRow(model.getRowCount(),new Object[]{pr.getUid(),pr.getFname()+" "+pr.getLname(),pr.getQty(),str,pr.getBill()});
					amount += pr.getBill();
					buy ++;
				
					
				}
			}
			incomes.setText(""+amount);
			buyers.setText(""+buy);
			
			
		}
		
	}
 void clearTable()
{
	while(model.getRowCount() > 0 )
		model.removeRow(0);
}
public 	void setCombo(ArrayList<PromotionReport> p)
	{
		this.p = p ;
		
		for(PromotionReport pr : p)
			comboBox.addItem(pr.getPname());
	}
}
