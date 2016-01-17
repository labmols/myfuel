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
import myfuel.Tools.CarFuelProgress;
import myfuel.client.Rate;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JTextArea;

/**
 *Fueling Dialog process user interface.
 */
public class FuelDialog extends JDialog {

	/**
	 * Content Panel
	 */
	private final JPanel contentPanel = new JPanel();
	
	/**
	 * CarFuel GUI Controller, for handle logic functionality.
	 */
	private CarFuelActions actions;
	
	/**
	 * Fueling Progress bar, indicates when the fueling is over.
	 */
	private JProgressBar progressBar;
	/**
	 * Current liter label.
	 */
	private JLabel liter;
	/**
	 * Current price label.
	 */
	private JLabel price;
	/**
	 * Price for liter.
	 */
	private float p;
	/**
	 * Fuel amount needed.
	 */
	private float qty;
	/**
	 * Pay Panel, appeared when the fueling is over.
	 */
	private JPanel payPanel;
	/**
	 * Pay button - creating new purchase.
	 */
	private JButton btnPay;
	/**
	 * Order change- if customer payed with cash.
	 */
	private JLabel changeLabel;
	/**
	 * Credit Card number.
	 */
	private JLabel ccLabel;
	/**
	 * Cash Panel - appeared when customer want to pay with cash.
	 */
	private JPanel cashPanel;
	/**
	 *Payment method comboBox(Cash/Credit Card).
	 */
	private JComboBox methodCB;
	/**
	 * Cash money textField.
	 */
	private JTextField moneyTxt;
	/**
	 * Credit card label(disabled when in cash option).
	 */
	private JLabel lblCreditCardNo;
	/**
	 * Car Fuel GUI (the parent component).
	 */
	private CarFuelGUI gui;
	/**
	 * Current Price (according to the progress bar).
	 */
	private float currentPrice;
	/**
	 * Discount from total order(according to the customer model).
	 */
	private float discount;
	/**
	 * Total price without discount
	 */
	private float totalPriceN;
	/**
	 * Total price(after discount) and amount that calculated in the gui controller.
	 */
	private float origQty,origPrice;
	/**
	 * 
	 */
	private JLabel lblPriceAfter;
	/**
	 * Total discount(including promotion).
	 */
	private JLabel lblDisc;
	
	/**
	 * Total price(after discount).
	 */
	private JLabel lblTotal;
	
	int customerModel;
	private JLabel PayMethodlbl;
	private JTextArea FullyMonthlyPane;
	private JLabel lblFew;
	private int accessType;
	
	/**
	 * Create new Fuel Dialog(indicates the fuel progress and make new purchase).
	 * @param actions - Car Fuel GUI Controller.
	 * @param gui - Car Fuel GUI(the parent component).
	 * @param qty - Fuel Amount.
	 * @param p - Price for liter.
	 * @param origQty - Total fuel amount.
	 * @param origPrice -Total order price(calculated in the controller and after discount).
	 * @param discount - Total Discount.
	 * @param totalPriceN - Total order price(before discount).
	 */
	public FuelDialog(CarFuelActions actions,CarFuelGUI gui,int accessType, float qty, float p,float origQty, float origPrice,float discount,float totalPriceN) 
	{
		
		this.actions = actions;
		this.gui = gui;
		this.origPrice = origPrice;
		this.origQty = origQty;
		this.discount = discount;
		this.totalPriceN = totalPriceN;
		this.accessType = accessType;
		customerModel = actions.getCustomerModel();
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
		progressBar.setBounds(174, 22, 146, 20);
		progressBar.setMaximum((int)qty);
		contentPanel.add(progressBar);
		{
			JLabel lblLiter = new JLabel("Liter: ");
			lblLiter.setFont(new Font("Arial", Font.BOLD, 13));
			lblLiter.setBounds(136, 50, 46, 16);
			contentPanel.add(lblLiter);
		}
		{
			liter = new JLabel("");
			liter.setFont(new Font("Arial", Font.PLAIN, 13));
			liter.setBounds(182, 50, 121, 16);
			contentPanel.add(liter);
		}
		{
			JLabel lblPrice = new JLabel("Price: ");
			lblPrice.setFont(new Font("Arial", Font.BOLD, 13));
			lblPrice.setBounds(136, 76, 56, 16);
			contentPanel.add(lblPrice);
		}
		{
			price = new JLabel("");
			price.setFont(new Font("Arial", Font.PLAIN, 13));
			price.setBounds(182, 76, 114, 16);
			contentPanel.add(price);
		}
		
		JLabel lblFuelProgress = new JLabel("Fuel Progress: ");
		lblFuelProgress.setFont(new Font("Arial", Font.BOLD, 13));
		lblFuelProgress.setBounds(78, 22, 97, 16);
		contentPanel.add(lblFuelProgress);
		
		payPanel = new JPanel();
		payPanel.setOpaque(false);
		payPanel.setBounds(19, 104, 404, 195);
		contentPanel.add(payPanel);
		payPanel.setLayout(null);
		
		PayMethodlbl = new JLabel("Payment Method: ");
		PayMethodlbl.setFont(new Font("Arial", Font.BOLD, 13));
		PayMethodlbl.setBounds(62, 51, 129, 16);
		payPanel.add(PayMethodlbl);
		
		methodCB = new JComboBox();
		methodCB.setFont(new Font("Arial", Font.PLAIN, 13));
		methodCB.setModel(new DefaultComboBoxModel(new String[] {"Credit Card", "Cash"}));
		methodCB.setBounds(197, 47, 127, 27);
		methodCB.addItemListener(new eventListener());
		payPanel.add(methodCB);
		
		lblCreditCardNo = new JLabel("Credit Card no: ");
		lblCreditCardNo.setFont(new Font("Arial", Font.BOLD, 13));
		lblCreditCardNo.setBounds(99, 79, 112, 16);
		payPanel.add(lblCreditCardNo);
		
		ccLabel = new JLabel("");
		ccLabel.setForeground(Color.WHITE);
		ccLabel.setFont(new Font("Arial", Font.BOLD, 13));
		ccLabel.setText(actions.getCustomerCC());
		ccLabel.setBounds(196, 79, 158, 16);
		payPanel.add(ccLabel);
		
		btnPay = new JButton("Confirm");
		btnPay.setFont(new Font("Arial", Font.PLAIN, 13));
		btnPay.setBounds(151, 160, 100, 29);
		btnPay.addActionListener(new eventListener());
		payPanel.add(btnPay);
		
		cashPanel = new JPanel();
		cashPanel.setVisible(false);
		cashPanel.setOpaque(false);
		cashPanel.setBounds(99, 86, 176, 55);
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
		
		lblTotal = new JLabel("");
		lblTotal.setBounds(256, 23, 76, 16);
		payPanel.add(lblTotal);
		lblTotal.setFont(new Font("Arial", Font.BOLD, 13));
		lblTotal.setForeground(Color.WHITE);
		
		JLabel lblDiscount = new JLabel("Discount: ");
		lblDiscount.setBounds(197, 23, 65, 16);
		payPanel.add(lblDiscount);
		lblDiscount.setFont(new Font("Arial", Font.BOLD, 13));
		
		lblPriceAfter = new JLabel("Price after ");
		lblPriceAfter.setBounds(73, 23, 78, 16);
		payPanel.add(lblPriceAfter);
		lblPriceAfter.setFont(new Font("Arial", Font.BOLD, 13));
		
		lblDisc = new JLabel("");
		lblDisc.setBounds(148, 23, 43, 16);
		payPanel.add(lblDisc);
		lblDisc.setForeground(Color.WHITE);
		lblDisc.setFont(new Font("Arial", Font.BOLD, 13));
		
		FullyMonthlyPane = new JTextArea();
		FullyMonthlyPane.setVisible(false);
		FullyMonthlyPane.setFont(new Font("Arial", Font.ITALIC, 14));
		FullyMonthlyPane.setOpaque(false);
		FullyMonthlyPane.setText("Your Credit Card will be charged in the next month.\nYou can see all your purchases every time you want\n in the Purchases option.");
		FullyMonthlyPane.setBounds(39, 103, 339, 55);
		payPanel.add(FullyMonthlyPane);
		
		lblFew = new JLabel("You have been charged for Few networks fee of 2%");
		lblFew.setForeground(Color.RED);
		lblFew.setFont(new Font("Arial", Font.PLAIN, 13));
		lblFew.setBounds(56, 0, 311, 16);
		lblFew.setVisible(false);
		payPanel.add(lblFew);
		
		payPanel.setVisible(false);
		{
			JLabel label = new JLabel("");
			label.setBounds(0, -14, 450, 319);
			contentPanel.add(label);
			 java.net.URL url = getClass().getResource("/dialogBG.png");
			label.setIcon(new ImageIcon(url));
		}
		
		JLabel label = new JLabel("");
		label.setBounds(252, 116, 90, 16);
		contentPanel.add(label);
		CarFuelProgress t = new CarFuelProgress(this,qty);
		new Thread(t).start();
		 
		
		
	}
	
	/**
	 * Handle all components events.
	 *
	 */
	private class eventListener implements ActionListener,ItemListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnPay)
			{
				CheckAndMakePurchase();
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
	
	/**
	 * Check customer input , if all details are correct make new purchase via the gui controller.
	 */
	private void CheckAndMakePurchase()
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
	
	/**
	 * Set progress bar with the value from the Progress thread.
	 * @param value - The value received from the progress Thread.
	 */
	public void setProgress(float value) {
		// TODO Auto-generated method stub
		progressBar.setValue((int)value);
		if(progressBar.getValue() == progressBar.getMaximum())
		{
			setView();
			
		}
		currentPrice =value*p;
		if(currentPrice < totalPriceN)
		{
		
		liter.setText("" + new DecimalFormat("##.##").format(value)+" Liters");
		price.setText("" + new DecimalFormat("##.##").format((currentPrice))+"  NIS");
		}
		else
		{
			liter.setText("" + new DecimalFormat("##.##").format(origQty)+" Liters");
			price.setText("" + new DecimalFormat("##.##").format((totalPriceN))+"  NIS");
		}
	}

	private void setView() {
		// TODO Auto-generated method stub
		if(accessType == 1)
			lblFew.setVisible(true);
		this.lblDisc.setText(new DecimalFormat("##.##").format((discount))+"%");
		this.lblTotal.setText(new DecimalFormat("##.##").format((origPrice))+"NIS");
		payPanel.setVisible(true);
		if(customerModel == Rate.FullyMonthly)
		{
			FullyMonthlyPane.setVisible(true);
			this.PayMethodlbl.setVisible(false);
			this.methodCB.setVisible(false);
		}
	}

	/**
	 * Show Pay panel options.
	 */
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

