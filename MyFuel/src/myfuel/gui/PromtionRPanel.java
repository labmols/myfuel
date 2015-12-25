package myfuel.gui;

import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import java.awt.Color;

@SuppressWarnings("serial")
public class PromtionRPanel extends JPanel{
	private JTable table;
	private DefaultTableModel model ; 
	
	public PromtionRPanel() {
		setOpaque(false);
		setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(217, 25, 143, 20);
		add(comboBox);
		
		JLabel lblChoosePromotion = new JLabel("Choose Promotion:");
		lblChoosePromotion.setBounds(26, 28, 136, 14);
		add(lblChoosePromotion);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 87, 414, 188);
		add(scrollPane);
		model = new MyTableModel(4,-1);
		String[] names = {"ID" ,"Name","Quantity","Type","Bill"};
		
		for(String s : names)
			model.addColumn(s);
		
		table = new JTable(model);
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		JLabel lblBuyers = new JLabel("Buyers:");
		lblBuyers.setBounds(50, 64, 46, 14);
		add(lblBuyers);
		
		JLabel lblNewLabel = new JLabel("Total Incomes:");
		lblNewLabel.setBounds(198, 64, 94, 14);
		add(lblNewLabel);
		
		JLabel buyers = new JLabel("0");
		buyers.setForeground(new Color(0, 0, 0));
		buyers.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		buyers.setBounds(116, 64, 46, 14);
		add(buyers);
		
		JLabel incomes = new JLabel("0");
		incomes.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		incomes.setBounds(314, 62, 46, 14);
		add(incomes);
	}
}
