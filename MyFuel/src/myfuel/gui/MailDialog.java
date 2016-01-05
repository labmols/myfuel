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

/**
 *JDialog that presents progress of sending mails to the confirmed customers(created in ConfirmationGUI).
 */
public class MailDialog extends JDialog {
/**
 * This JPanel contains all components
 */
	private final JPanel contentPanel = new JPanel();
/**
 * Progress Bar that indicates the sending mails progress.
 */
	JProgressBar progressBar;
/**
 * Sending complete label(visible when all mails sent successfully).
 */
	private JLabel sendComplbl;
/**
 * Customers approved message label.
 */
	private JLabel lblCustomersApprovedSuccessfully;
/**
 * OK Button(exit from Dialog).
 */
	private JButton btnOk;

/**
 * Create new Mail Dialog 
 * @param maxValue - Number of mails need to send.
 */
	public MailDialog(int maxValue) {
		setTitle("Sending Mails...");
		setBounds(100, 100, 399, 181);
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
			sendComplbl = new JLabel("Sending Complete!");
			sendComplbl.setForeground(Color.WHITE);
			sendComplbl.setBounds(134, 83, 126, 16);
			sendComplbl.setVisible(false);
			contentPanel.add(sendComplbl);
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
		btnOk.setBounds(164, 111, 61, 29);
		contentPanel.add(btnOk);
		{
			JLabel label = new JLabel("");
			label.setBounds(0, 0, 450, 239);
			contentPanel.add(label);
			 java.net.URL url = getClass().getResource("/dialogBG.png");
			label.setIcon(new ImageIcon(url));
		}
	
	}
	
	/**
	 * Set progress of the Mail ProgressBar.
	 * @param value - Current number of mails that sent successfully.
	 */
	public void setProgress(int value) {
		// TODO Auto-generated method stub
		//progressBar.setValue(value);
		if(progressBar.getMaximum() == value) 
		{
			btnOk.setEnabled(true);
			progressBar.setIndeterminate(false);
			sendComplbl.setVisible(true);
		}
			
	}
	
	public JDialog getDialog()
	{
		return this;
	}
}
