package myfuel.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import myfuel.GUIActions.MMReportsActions;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MMReportGUI extends SuperGUI{
	private JPanel reportPanel;
	private MMReportsActions actions;
	private JComboBox comboBox;
	
	public MMReportGUI(MMReportsActions actions)
	{
		lblTitle.setBounds(271, 6, 125, 25);
		lblTitle.setText("Reports\r\n");
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ComboHandler());
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Promotions Reports", "Customer Characterization"}));
		comboBox.setBounds(193, 64, 191, 20);
		panel.add(comboBox);
		
		
		this.actions = actions;
		this.setContentPane(contentPane);
	}
	
	
	
	private class ComboHandler implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if(comboBox.getSelectedIndex() == 0) // promotion reports
			{
				reportPanel = new PromtionRPanel();
			}
			
			else  // customer reports
			{
				
			}
			
			reportPanel.setBounds(64, 90, 447, 297);
			panel.add(reportPanel);
			
		}
		
	}
	@Override
	public void getInput(ActionEvent e) 
	{
		
		
	}
	
	

}
