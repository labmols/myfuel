package myfuel.gui;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JProgressBar;
import javax.swing.JLabel;

import java.awt.Color;
import java.text.DecimalFormat;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import myfuel.GUIActions.CarFuelActions;

import java.awt.Font;

import javax.swing.JTextField;

public class FuelDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	CarFuelActions actions;
	JProgressBar progressBar;
	private JLabel liter;
	private JLabel price;
	private float p;
	private float qty;
	private JPanel payPanel;
	private JButton btnPay;
	private JLabel changeLabel;
	private JLabel ccLabel;
	private JPanel cashPanel;
	private JComboBox methodCB;
	private JTextField moneyTxt;
	private JLabel lblCreditCardNo;
	private CarFuelGUI gui;
	private float currentPrice;

	/**
	 * Create the dialog.
	 * @param p 
	 */
	public FuelDialog(CarFuelActions actions,CarFuelGUI gui, float qty, float p) {
		this.actions = actions;
		this.gui = gui;
		setTitle("Fueling...");
		setBounds(100, 100, 451, 273);
		this.setAlwaysOnTop(true);
		setLocationRelativeTo(gui);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		this.p=p;
		this.qty = qty;
		progressBar = new JProgressBar();
		progressBar.setForeground(Color.ORANGE);
		progressBar.setBounds(177, 18, 146, 20);
		progressBar.setMaximum((int)qty);
		contentPanel.add(progressBar);
		{
			JLabel lblLiter = new JLabel("Liter: ");
			lblLiter.setFont(new Font("Arial", Font.BOLD, 13));
			lblLiter.setBounds(163, 62, 46, 16);
			contentPanel.add(lblLiter);
		}
		{
			liter = new JLabel("");
			liter.setFont(new Font("Arial", Font.PLAIN, 13));
			liter.setBounds(209, 62, 61, 16);
			contentPanel.add(liter);
		}
		{
			JLabel lblPrice = new JLabel("Price: ");
			lblPrice.setFont(new Font("Arial", Font.BOLD, 13));
			lblPrice.setBounds(163, 88, 56, 16);
			contentPanel.add(lblPrice);
		}
		{
			price = new JLabel("");
			price.setFont(new Font("Arial", Font.PLAIN, 13));
			price.setBounds(209, 88, 61, 16);
			contentPanel.add(price);
		}
		
		JLabel lblFuelProgress = new JLabel("Fuel Progress: ");
		lblFuelProgress.setFont(new Font("Arial", Font.PLAIN, 13));
		lblFuelProgress.setBounds(78, 22, 97, 16);
		contentPanel.add(lblFuelProgress);
		
		payPanel = new JPanel();
		payPanel.setOpaque(false);
		payPanel.setBounds(78, 116, 276, 124);
		contentPanel.add(payPanel);
		payPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Payment Method: ");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 13));
		lblNewLabel.setBounds(6, 6, 112, 16);
		payPanel.add(lblNewLabel);
		
		methodCB = new JComboBox();
		methodCB.setFont(new Font("Arial", Font.PLAIN, 13));
		methodCB.setModel(new DefaultComboBoxModel(new String[] {"Credit Card", "Cash"}));
		methodCB.setBounds(130, 2, 127, 27);
		methodCB.addItemListener(new eventListener());
		payPanel.add(methodCB);
		
		lblCreditCardNo = new JLabel("Credit Card no: ");
		lblCreditCardNo.setFont(new Font("Arial", Font.BOLD, 13));
		lblCreditCardNo.setBounds(41, 42, 112, 16);
		payPanel.add(lblCreditCardNo);
		
		ccLabel = new JLabel("");
		ccLabel.setText(actions.getCustomerCC());
		ccLabel.setBounds(140, 41, 158, 16);
		payPanel.add(ccLabel);
		
		btnPay = new JButton("Pay");
		btnPay.setFont(new Font("Arial", Font.PLAIN, 13));
		btnPay.setBounds(78, 92, 100, 29);
		btnPay.addActionListener(new eventListener());
		payPanel.add(btnPay);
		
		cashPanel = new JPanel();
		cashPanel.setVisible(false);
		cashPanel.setOpaque(false);
		cashPanel.setBounds(51, 23, 149, 83);
		payPanel.add(cashPanel);
		cashPanel.setLayout(null);
		
		JLabel label_2 = new JLabel("Enter Money: ");
		label_2.setBounds(6, 24, 100, 16);
		cashPanel.add(label_2);
		label_2.setFont(new Font("Arial", Font.BOLD, 13));
		
		changeLabel = new JLabel("");
		changeLabel.setBounds(88, 30, 83, 16);
		cashPanel.add(changeLabel);
		
		moneyTxt = new JTextField();
		moneyTxt.setBounds(92, 18, 67, 26);
		cashPanel.add(moneyTxt);
		moneyTxt.setColumns(10);
		
		payPanel.setVisible(false);
		{
			JLabel label = new JLabel("");
			label.setBounds(0, 0, 450, 256);
			contentPanel.add(label);
			 java.net.URL url = getClass().getResource("/dialogBG.png");
			label.setIcon(new ImageIcon(url));
		}
		ProgressBarThread t = new ProgressBarThread(this,qty);
		new Thread(t).start();
		
		
	}
	
	private class eventListener implements ActionListener,ItemListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnPay)
			{
				float money=-1;
				float change=-1;
				boolean check=true;
				String error="";
				if(methodCB.getSelectedIndex()==1)
				{
					try
					{
						money = Float.parseFloat(moneyTxt.getText());
						if(money<0)
						{
							check = false;
							error+= "illegal money value!\n";
						}
					}
					catch (NumberFormatException e1)
					{
						e1.printStackTrace();
						check = false;
						error+= "illegal money value!\n";
					}
					change =money-currentPrice;
					if(change<0 && check)
					{
						check = false;
						error+= "Not enough money!\n";
					}
				}
				if(check)
				{
					setAlwaysOnTop(false);
				if(methodCB.getSelectedIndex()==1)
					JOptionPane.showMessageDialog(null, "Your change is : "+new DecimalFormat("##.##").format(change),"Your Change",JOptionPane.PLAIN_MESSAGE);	
				actions.createPurchase();
				
				dispose();
				setVisible(false);
				}
				
				else
				{
					setAlwaysOnTop(false);
					JOptionPane.showMessageDialog(null, error,"Error",JOptionPane.ERROR_MESSAGE);
					setAlwaysOnTop(true);
				}
			}
		}

		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			if(e.getStateChange()==ItemEvent.DESELECTED &&  e.getSource() == methodCB)
			{
				setPay();
				
			}
		}
	}
	
	public void setProgress(float value) {
		// TODO Auto-generated method stub
		progressBar.setValue((int)value);
		if(progressBar.getValue() == (int)qty)
			payPanel.setVisible(true);
		
		currentPrice =value*p;
		liter.setText("" + new DecimalFormat("##.##").format(value)+" Liters");
		price.setText("" + new DecimalFormat("##.##").format((currentPrice))+" NIS");
	}

	private void setPay() {
		// TODO Auto-generated method stub
		
		if(methodCB.getSelectedIndex()==1)
		{
			cashPanel.setVisible(true);
			lblCreditCardNo.setVisible(false);
			ccLabel.setVisible(false);
			
		}
		else
		{
			cashPanel.setVisible(false);
			lblCreditCardNo.setVisible(true);
			ccLabel.setVisible(true);
		}
		
	}
}	

