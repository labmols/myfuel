package myfuel.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.Date;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import myfuel.GUIActions.GUIActions;

@SuppressWarnings("serial")
public class CustomerPurchaseGUI extends SuperGUI {
	private MyTableModel model;
	private JTable purchaseTable;
	
	public CustomerPurchaseGUI(GUIActions actions)
	{
		super(actions);
		setContentPane(contentPane);
		panel.setLocation(0, 0);
		lblTitle.setBounds(226, 6, 175, 31);
		lblTitle.setText("Purchase History");
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 96, 584, 279);
		model = new MyTableModel(6,-1);
		String[] names = {"#" ,"Car","Station","Fuel","Date","Time","Amount(L)","Price(NIS)"};
		for(String s : names)
			model.addColumn(s);
		
		purchaseTable = new JTable(model);
		purchaseTable.setFont(new Font("Arial", Font.PLAIN, 12));
		purchaseTable.setModel(model);
		scrollPane.setViewportView(purchaseTable);
		panel.add(scrollPane);
		//model.insertRow(model.getRowCount(),new Object[]{1, 1234567,"Abalolo","Paz", "95", "31/12/15","11:10",435,40});
	}
	@Override
	public void getInput(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
