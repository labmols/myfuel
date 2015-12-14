package myfuel.gui;

import java.awt.event.ActionEvent;

import myfuel.GUIActions.MakeAPromotionActions;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextPane;
import javax.swing.JTextField;

public class MakeaPromotionGUI extends SuperGUI{

	MakeAPromotionActions actions;
	JLabel dis;  // discount amount
	JLabel type; // type of customer
	JLabel end; // end time 
	JLabel start; // start time
	public MakeaPromotionGUI(MakeAPromotionActions actions) 
	{
		lblTitle.setBounds(271, 6, 206, 16);
		lblTitle.setText("Make a Promotion");
		this.actions = actions;
		this.setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Select a Template:");
		lblNewLabel.setBounds(130, 71, 111, 14);
		panel.add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Template 1", "Template 2", "Template 3", "Template 4"}));
		comboBox.setBounds(248, 69, 137, 20);
		panel.add(comboBox);
		
		JLabel lblDiscountPercentage = new JLabel("Discount Percentage:");
		lblDiscountPercentage.setBounds(61, 129, 159, 14);
		panel.add(lblDiscountPercentage);
		
		
		
		JLabel sign = new JLabel("%");
		sign.setBounds(230, 129, 46, 14);
		panel.add(sign);
		
		dis = new JLabel("dis");
		dis.setBounds(195, 129, 46, 14);
		panel.add(dis);
		
		JLabel lblTypeOfCustomer = new JLabel("Type of Customer:");
		lblTypeOfCustomer.setBounds(61, 193, 117, 14);
		panel.add(lblTypeOfCustomer);
		
		 type = new JLabel("Company");
		type.setBounds(176, 193, 77, 14);
		panel.add(type);
		
		JLabel lblStartHour = new JLabel("Start Time:");
		lblStartHour.setBounds(330, 129, 77, 14);
		panel.add(lblStartHour);
		
		JLabel lblEndTime = new JLabel("End Time:");
		lblEndTime.setBounds(330, 193, 77, 14);
		panel.add(lblEndTime);
		
		 start = new JLabel("HH:MM");
		start.setBounds(417, 129, 84, 14);
		panel.add(start);
		
		 end = new JLabel("HH:MM");
		end.setBounds(417, 193, 84, 14);
		panel.add(end);
	}

	@Override
	public void getInput(ActionEvent e) {
		
		
	}
}
