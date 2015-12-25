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
	private JLabel liter;
	private JLabel price;
	private JLabel lblNewLabel;
	private JLabel lblCustomersApprovedSuccessfully;

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
		{
			liter = new JLabel("");
			liter.setBounds(137, 62, 61, 16);
			contentPanel.add(liter);
		}
		{
			price = new JLabel("");
			price.setBounds(137, 88, 61, 16);
			contentPanel.add(price);
		}
		
		JLabel lblFuelProgress = new JLabel("Sending Mails...");
		lblFuelProgress.setBounds(58, 42, 117, 16);
		contentPanel.add(lblFuelProgress);
		{
			lblNewLabel = new JLabel("Sending Complete!");
			lblNewLabel.setForeground(Color.WHITE);
			lblNewLabel.setBounds(137, 74, 126, 16);
			lblNewLabel.setVisible(false);
			contentPanel.add(lblNewLabel);
		}
		{
			lblCustomersApprovedSuccessfully = new JLabel("Customers Approved successfully! ");
			lblCustomersApprovedSuccessfully.setBounds(80, 14, 230, 16);
			contentPanel.add(lblCustomersApprovedSuccessfully);
		}
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
		{
		lblNewLabel.setVisible(true);
		
		this.dispose();
		this.setVisible(false);
		}
	}
	
	public JDialog getDialog()
	{
		return this;
	}
	
	
}
