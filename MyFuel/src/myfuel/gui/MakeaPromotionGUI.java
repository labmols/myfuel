package myfuel.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import myfuel.Entity.Promotion;
import myfuel.Entity.PromotionTemplate;
import myfuel.GUIActions.MakeAPromotionActions;
import myfuel.Tools.BackMainMenu;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextPane;
import javax.swing.JTextField;

import com.alee.extended.date.WebDateField;

import javax.swing.JButton;

import java.awt.Color;
import java.awt.Font;

/***
 * User interface for making new promotion
 * @author karmo
 *
 */
@SuppressWarnings("serial")
public class MakeaPromotionGUI extends SuperGUI{
	/***
	 * Controller for this GUI
	 */
	private MakeAPromotionActions actions;
	/***
	 * Description for user
	 */
	private JLabel dis;  // discount amount
	/***
	 * Description for user
	 */
	private JLabel type; // type of customer
	/***
	 * Description for user
	 */
	private JLabel end; // end time 
	/***
	 * Description for user
	 */
	private JLabel start; // start time
	/***
	 * ConboBox for showing the templates names
	 */
	private JComboBox<String> templates;
	/***
	 * The promotion selected 
	 */
	private PromotionTemplate p ;
	/***
	 * End date of the promotion
	 */
	private Date endDate;
	/***
	 * Start Date of the promtoin
	 */
	private Date startDate;
	/***
	 * Description for user
	 */
	private JLabel fuel;
	/***
	 * Combobox model 
	 */
	private DefaultComboBoxModel<String> model;
	/***
	 * Date picker for choosing the start date
	 */
	private WebDateField datePicker;
	/***
	 * Date picker for choosing the end date
	 */
	private WebDateField datePicker2;
	/***
	 * Creation Button
	 */
	private JButton btnCreate;
	/***
	 * MakeaPromotionGUI Constructor
	 * @param actions - controller for this GUI
	 */
	public MakeaPromotionGUI(MakeAPromotionActions actions) 
	{
		super(actions);
		model = new DefaultComboBoxModel<String>(  );
		
		
		this.mainMenu.addActionListener(new BackMainMenu(actions));
		lblTitle.setBounds(201, 6, 206, 20);
		lblTitle.setText("Make a Promotion");
		this.actions = actions;
		this.setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Select a Template:");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 13));
		lblNewLabel.setBounds(112, 71, 129, 14);
		panel.add(lblNewLabel);
		
		templates = new JComboBox<String>();
		templates.setFont(new Font("Arial", Font.PLAIN, 13));
		templates.addActionListener(new handler());
		templates.setModel(model);
		templates.setBounds(241, 66, 137, 27);
		panel.add(templates);
		
		JLabel lblDiscountPercentage = new JLabel("Discount Percentage:");
		lblDiscountPercentage.setFont(new Font("Arial", Font.BOLD, 13));
		lblDiscountPercentage.setBounds(102, 139, 137, 14);
		panel.add(lblDiscountPercentage);
		
		
		
		JLabel sign = new JLabel("%");
		sign.setFont(new Font("Arial", Font.PLAIN, 13));
		sign.setForeground(Color.WHITE);
		sign.setBounds(275, 139, 18, 14);
		panel.add(sign);
		
		dis = new JLabel();
		dis.setForeground(Color.WHITE);
		dis.setBounds(242, 139, 37, 14);
		panel.add(dis);
		
		JLabel lblTypeOfCustomer = new JLabel("Type of Customer:");
		lblTypeOfCustomer.setFont(new Font("Arial", Font.BOLD, 13));
		lblTypeOfCustomer.setBounds(102, 169, 117, 14);
		panel.add(lblTypeOfCustomer);
		
		 type = new JLabel();
		 type.setFont(new Font("Arial", Font.PLAIN, 13));
		 type.setForeground(Color.WHITE);
		type.setBounds(228, 169, 77, 14);
		panel.add(type);
		
		JLabel lblStartHour = new JLabel("Start Time:");
		lblStartHour.setFont(new Font("Arial", Font.BOLD, 13));
		lblStartHour.setBounds(335, 139, 77, 14);
		panel.add(lblStartHour);
		
		JLabel lblEndTime = new JLabel("End Time:");
		lblEndTime.setFont(new Font("Arial", Font.BOLD, 13));
		lblEndTime.setBounds(335, 169, 77, 14);
		panel.add(lblEndTime);
		
		 start = new JLabel();
		 start.setFont(new Font("Arial", Font.PLAIN, 13));
		 start.setForeground(Color.WHITE);
		start.setBounds(415, 139, 84, 14);
		panel.add(start);
		
		 end = new JLabel();
		 end.setFont(new Font("Arial", Font.PLAIN, 13));
		 end.setForeground(Color.WHITE);
		end.setBounds(415, 169, 84, 14);
		panel.add(end);
		
		 btnCreate = new JButton("Create");
		btnCreate.setBounds(239, 348, 114, 38);
		btnCreate.addActionListener(new handler());
		panel.add(btnCreate);
		
		JLabel lblStartDate = new JLabel("Start Date:");
		lblStartDate.setFont(new Font("Arial", Font.BOLD, 13));
		lblStartDate.setBounds(180, 255, 91, 14);
		panel.add(lblStartDate);
		
		 datePicker = new WebDateField ( new Date () );
		 datePicker.setLocation(250, 249);
		 datePicker.setSize(128, 25);
		 panel.add(datePicker);
		JLabel lblEndDate = new JLabel("End Date:");
		lblEndDate.setFont(new Font("Arial", Font.BOLD, 13));
		lblEndDate.setBounds(181, 295, 90, 14);
		panel.add(lblEndDate);
		
		 datePicker2 = new WebDateField ( new Date () );
		 datePicker2.setLocation(250, 289);
		 datePicker2.setSize(128, 25);
		 panel.add(datePicker2);
		JLabel lblTypeOfFuel = new JLabel("Type of Fuel:");
		lblTypeOfFuel.setFont(new Font("Arial", Font.PLAIN, 13));
		lblTypeOfFuel.setBounds(180, 195, 117, 14);
		panel.add(lblTypeOfFuel);
		
		 fuel = new JLabel("New label");
		 fuel.setFont(new Font("Arial", Font.PLAIN, 13));
		fuel.setForeground(Color.WHITE);
		fuel.setBounds(275, 195, 81, 14);
		panel.add(fuel);
		
		JLabel lblTemplateDetails = new JLabel("Template Details: ");
		lblTemplateDetails.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		lblTemplateDetails.setBounds(229, 111, 129, 16);
		panel.add(lblTemplateDetails);
	}

	@Override
	public void getInput(ActionEvent e)
	{
	
		if(e.getSource() == templates)
		{
			p = actions.getPromotion(templates.getSelectedIndex());
			dis.setText(""+p.getDiscount());
			
			switch(p.getTypeOfFuel())
			{
			case 1:
				fuel.setText("95");
				break;
				
			case 2:
				fuel.setText("Diesel");
				break;
			case 3:
				fuel.setText("Scooter");
				break;
			case 4:
				fuel.setText("Home Fuel");
				break;
			}
			
			if(p.getTypeOfCustomer() == 0)
				type.setText("Private");
			else
				type.setText("Company");
			start.setText(""+p.getStartTime());
			end.setText(""+p.getEndTime());
		}
		
		else if(e.getSource() == btnCreate)
		{
			setStartDate((Date) datePicker.getDate());
			setEndDate((Date) datePicker2.getDate());
			actions.verifyDetails(startDate, endDate);
		}
		
	}
	
	/***
	 * Action Listener for handling action events
	 * @author karmo
	 *
	 */
	private class handler implements ActionListener
	{
		
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			getInput(e);
		}
		
	}
	

	
	public PromotionTemplate getP()
	{
		return this.p;
	}
	
	public void addElementToModel(String name)
	{
		model.addElement(name);
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}
