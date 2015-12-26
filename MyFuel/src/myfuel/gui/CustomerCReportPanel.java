package myfuel.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class CustomerCReportPanel extends JPanel {
	public CustomerCReportPanel() {
		setLayout(null);
		
		JLabel lblChooseStation = new JLabel("Choose Station:");
		lblChooseStation.setBounds(89, 32, 90, 14);
		add(lblChooseStation);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(189, 29, 126, 20);
		add(comboBox);
	}
}
