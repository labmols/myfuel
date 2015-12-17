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

public class FuelDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	JProgressBar progressBar;
	private JLabel liter;
	private JLabel price;
	private float p;

	/**
	 * Create the dialog.
	 * @param p 
	 */
	public FuelDialog(int value, float p) {
		setTitle("Fueling...");
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		this.p=p;
		progressBar = new JProgressBar();
		progressBar.setForeground(Color.ORANGE);
		progressBar.setBounds(152, 22, 146, 20);
		progressBar.setMaximum(value);
		contentPanel.add(progressBar);
		{
			JLabel lblLiter = new JLabel("Liter: ");
			lblLiter.setBounds(100, 62, 61, 16);
			contentPanel.add(lblLiter);
		}
		{
			liter = new JLabel("");
			liter.setBounds(145, 62, 61, 16);
			contentPanel.add(liter);
		}
		{
			JLabel lblPrice = new JLabel("Price: ");
			lblPrice.setBounds(100, 88, 61, 16);
			contentPanel.add(lblPrice);
		}
		{
			price = new JLabel("");
			price.setBounds(173, 88, 61, 16);
			contentPanel.add(price);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		ProgressBarThread t = new ProgressBarThread(this);
		new Thread(t).start();
	}
	
	public void setProgress(int value) {
		// TODO Auto-generated method stub
		progressBar.setValue(value);
		liter.setText("" + progressBar.getValue());
		price.setText("" + new DecimalFormat("##.##").format((progressBar.getValue()*p)));
	}
}
