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
	private float discount;
	private float borigPrice;
	private float origQty,origPrice;
	private JLabel lblPriceAfter;
	private JLabel lblDisc;
	private JLabel lblDiscount;
	private JLabel lblTotal;
	
	/**
	 * Create the dialog.
	 * @param p 
	 */
	public FuelDialog(CarFuelActions actions,CarFuelGUI gui, float qty, float p,float origQty, float origPrice,float discount,float borigPrice) {
		this.actions = actions;
		this.gui = gui;
		this.origPrice = origPrice;
		this.origQty = origQty;
		this.discount = discount;
		this.borigPrice = borigPrice;
		setTitle("Fueling...");
		setBounds(100, 100, 451, 327);
		this.setAlwaysOnTop(true);
		setLocationRelativeTo(gui);
		setModalityType(JDialog.DEFAULT_MODALITY_TYPE);
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
			liter.setBounds(209, 62, 121, 16);
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
			price.setBounds(209, 88, 114, 16);
			contentPanel.add(price);
		}
		
		JLabel lblFuelProgress = new JLabel("Fuel Progress: ");
		lblFuelProgress.setFont(new Font("Arial", Font.PLAIN, 13));
		lblFuelProgress.setBounds(78, 22, 97, 16);
		contentPanel.add(lblFuelProgress);
		
		payPanel = new JPanel();
		payPanel.setOpaque(false);
		payPanel.setBounds(78, 110, 276, 189);
		contentPanel.add(payPanel);
		payPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Payment Method: ");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 13));
		lblNewLabel.setBounds(16, 30, 112, 16);
		payPanel.add(lblNewLabel);
		
		methodCB = new JComboBox();
		methodCB.setFont(new Font("Arial", Font.PLAIN, 13));
		methodCB.setModel(new DefaultComboBoxModel(new String[] {"Credit Card", "Cash"}));
		methodCB.setBounds(130, 26, 127, 27);
		methodCB.addItemListener(new eventListener());
		payPanel.add(methodCB);
		
		lblCreditCardNo = new JLabel("Credit Card no: ");
		lblCreditCardNo.setFont(new Font("Arial", Font.BOLD, 13));
		lblCreditCardNo.setBounds(33, 65, 112, 16);
		payPanel.add(lblCreditCardNo);
		
		ccLabel = new JLabel("");
		ccLabel.setText(actions.getCustomerCC());
		ccLabel.setBounds(148, 64, 158, 16);
		payPanel.add(ccLabel);
		
		btnPay = new JButton("Pay");
		btnPay.setFont(new Font("Arial", Font.PLAIN, 13));
		btnPay.setBounds(80, 142, 100, 29);
		btnPay.addActionListener(new eventListener());
		payPanel.add(btnPay);
		
		cashPanel = new JPanel();
		cashPanel.setVisible(false);
		cashPanel.setOpaque(false);
		cashPanel.setBounds(51, 71, 176, 83);
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
		moneyTxt.setBounds(92, 18, 78, 26);
		cashPanel.add(moneyTxt);
		moneyTxt.setColumns(10);
		
		lblPriceAfter = new JLabel("Price after ");
		lblPriceAfter.setFont(new Font("Arial", Font.BOLD, 13));
		lblPriceAfter.setBounds(16, 2, 78, 16);
		payPanel.add(lblPriceAfter);
		
		lblDisc = new JLabel("");
		lblDisc.setForeground(Color.WHITE);
		lblDisc.setFont(new Font("Arial", Font.BOLD, 13));
		lblDisc.setBounds(91, 2, 28, 16);
		payPanel.add(lblDisc);
		
		lblDiscount = new JLabel("Discount: ");
		lblDiscount.setFont(new Font("Arial", Font.BOLD, 13));
		lblDiscount.setBounds(130, 2, 65, 16);
		payPanel.add(lblDiscount);
		
		lblTotal = new JLabel("");
		lblTotal.setFont(new Font("Arial", Font.BOLD, 13));
		lblTotal.setForeground(Color.WHITE);
		lblTotal.setBounds(194, 2, 76, 16);
		payPanel.add(lblTotal);
		
		payPanel.setVisible(false);
		{
			JLabel label = new JLabel("");
			label.setBounds(0, 0, 450, 305);
			contentPanel.add(label);
			 java.net.URL url = getClass().getResource("/dialogBG.png");
			label.setIcon(new ImageIcon(url));
		}
		
		JLabel label = new JLabel("");
		label.setBounds(252, 116, 90, 16);
		contentPanel.add(label);
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
					change =money-origPrice;
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
					JOptionPane.showMessageDialog(null, "Your change is : "+new DecimalFormat("##.##").format(change),"Your Change",JOptionPane.INFORMATION_MESSAGE);	
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
		{
			this.lblDisc.setText(new DecimalFormat("##.##").format((discount))+"%");
			this.lblTotal.setText(new DecimalFormat("##.##").format((origPrice))+"NIS");
			payPanel.setVisible(true);
		}
		currentPrice =value*p;
		if(currentPrice < origPrice)
		{
		
		liter.setText("" + new DecimalFormat("##.##").format(value)+" Liters");
		price.setText("" + new DecimalFormat("##.##").format((currentPrice))+"  NIS");
		}
		else
		{
			liter.setText("" + new DecimalFormat("##.##").format(origQty)+" Liters");
			price.setText("" + new DecimalFormat("##.##").format((borigPrice))+"  NIS");
		}
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

