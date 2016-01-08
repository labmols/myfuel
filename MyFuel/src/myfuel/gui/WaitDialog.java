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
public class WaitDialog extends JDialog {
/**
 * This JPanel contains all components
 */
	private final JPanel contentPanel = new JPanel();
/**
 * Progress Bar that indicates the sending mails progress.
 */
	JProgressBar progressBar;

/**
 * Create new Mail Dialog 
 * @param maxValue - Number of mails need to send.
 */
	public WaitDialog() {
		setTitle("Getting Details...");
		setBounds(100, 100, 339, 133);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		progressBar = new JProgressBar();
		progressBar.setForeground(Color.ORANGE);
		progressBar.setBounds(90, 51, 146, 20);
		progressBar.setMaximum(1);
		contentPanel.add(progressBar);
		
		JLabel lblFuelProgress = new JLabel("Getting Details...");
		lblFuelProgress.setBounds(110, 23, 117, 16);
		contentPanel.add(lblFuelProgress);
		{
			JLabel label = new JLabel("");
			label.setBounds(0, 0, 450, 239);
			contentPanel.add(label);
			 java.net.URL url = getClass().getResource("/dialogBG.png");
			label.setIcon(new ImageIcon(url));
		}
		progressBar.setIndeterminate(true);
	
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
			progressBar.setIndeterminate(false);
			this.dispose();
		}
			
	}
	
	public JDialog getDialog()
	{
		return this;
	}
}
