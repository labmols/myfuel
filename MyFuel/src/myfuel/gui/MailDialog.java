package myfuel.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JProgressBar;
import javax.swing.JLabel;

import java.awt.Color;
import java.text.DecimalFormat;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MailDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	JProgressBar progressBar;
	private JLabel lblNewLabel;
	private JLabel lblCustomersApprovedSuccessfully;
	private JButton btnOk;

	public MailDialog(int maxValue) {
		setTitle("Sending Mails...");
		setBounds(100, 100, 399, 143);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		progressBar = new JProgressBar();
		progressBar.setForeground(Color.ORANGE);
		progressBar.setBounds(164, 42, 146, 20);
		progressBar.setMaximum(maxValue);
		contentPanel.add(progressBar);
		
		JLabel lblFuelProgress = new JLabel("Sending Mails...");
		lblFuelProgress.setBounds(58, 42, 117, 16);
		contentPanel.add(lblFuelProgress);
		{
			lblNewLabel = new JLabel("Sending Complete!");
			lblNewLabel.setForeground(Color.WHITE);
			lblNewLabel.setBounds(137, 62, 126, 16);
			lblNewLabel.setVisible(false);
			contentPanel.add(lblNewLabel);
		}
		{
			lblCustomersApprovedSuccessfully = new JLabel("Customers Approved successfully! ");
			lblCustomersApprovedSuccessfully.setBounds(80, 14, 230, 16);
			contentPanel.add(lblCustomersApprovedSuccessfully);
		}
		
		btnOk = new JButton("OK");
		btnOk.setEnabled(false);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getDialog().dispose();
				getDialog().setVisible(false);
			}
		});
		btnOk.setBounds(164, 86, 61, 29);
		contentPanel.add(btnOk);
		{
			JLabel label = new JLabel("");
			label.setBounds(0, 0, 450, 239);
			contentPanel.add(label);
			 java.net.URL url = getClass().getResource("/dialogBG.png");
			label.setIcon(new ImageIcon(url));
		}
	
	}
	
	public void setProgress(int value) {
		// TODO Auto-generated method stub
		progressBar.setValue(value);
		if(progressBar.getMaximum() == value) 
			btnOk.setEnabled(true);
			
	}
	
	public JDialog getDialog()
	{
		return this;
	}
}
