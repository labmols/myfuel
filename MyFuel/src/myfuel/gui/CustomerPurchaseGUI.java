package myfuel.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.Date;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class CustomerPurchaseGUI extends SuperGUI {
	private MyTableModel model;
	private JTable purchaseTable;
	
	public CustomerPurchaseGUI()
	{
		setContentPane(contentPane);
		panel.setLocation(0, 0);
		lblTitle.setBounds(226, 6, 175, 31);
		lblTitle.setText("Your Purchases");
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 96, 584, 279);
		model = new MyTableModel(6,-1);
		String[] names = {"PID" ,"Car","Driver","Station","Fuel Type","Date","Time","Price","Amount(L)"};
		for(String s : names)
			model.addColumn(s);
		
		purchaseTable = new JTable(model);
		purchaseTable.setFont(new Font("Calibri", Font.PLAIN, 13));
		purchaseTable.setModel(model);
		scrollPane.setViewportView(purchaseTable);
		panel.add(scrollPane);
		model.insertRow(model.getRowCount(),new Object[]{1, 1234567,"Abalolo","Paz", "31/12/15", "18:00",435,40});
	}
	@Override
	public void getInput(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
