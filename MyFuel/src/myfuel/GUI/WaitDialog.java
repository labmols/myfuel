package myfuel.GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.JProgressBar;
import javax.swing.JLabel;

import java.awt.Color;
import java.text.DecimalFormat;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.SwingConstants;

/**
 *JDialog that presents progress of task waiting.
 */
public class WaitDialog extends JDialog {
/**
 * This JPanel contains all components
 */
	private final JPanel contentPanel = new JPanel();
/**
 * Progress Bar that indicates the wait progress.
 */
	private JProgressBar progressBar;

	

/**
 * Create new Wait Dialog 
 */
	public WaitDialog(String msg) {
	setResizable(false);
	setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
	setResizable(false);
	this.setAlwaysOnTop(true);
		setTitle("Wait...");
		setBounds(100, 100, 339, 110);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		progressBar = new JProgressBar();
		progressBar.setForeground(Color.ORANGE);
		progressBar.setBounds(84, 44, 146, 20);
		progressBar.setMaximum(1);
		contentPanel.add(progressBar);
		
		JLabel msgLabel = new JLabel(msg);
		msgLabel.setHorizontalAlignment(SwingConstants.CENTER);
		msgLabel.setBounds(6, 23, 327, 16);
		contentPanel.add(msgLabel);
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
	 * Set progress.
	 * @param value - value of progress.
	 */
	public void setProgress(int value) {
		// TODO Auto-generated method stub
		//progressBar.setValue(value);
		if(progressBar.getMaximum() == value) 
		{
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
			t.start();
			try {
				t.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				        progressBar.setIndeterminate(false);
	        			dispose();
	        			setVisible(false);
		}	
	}
	
	public JDialog getDialog()
	{
		return this;
	}
}
