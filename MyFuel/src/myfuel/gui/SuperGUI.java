package myfuel.gui;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.print.DocFlavor.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import myfuel.GUIActions.GUIActions;
import myfuel.Tools.ClockPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JToolBar;

import com.alee.laf.button.WebButton;
import com.alee.laf.toolbar.WebToolBar;

import java.awt.Component;

/**
 * This class is the Basic User Interface with the basic appearance.
 *
 */
@SuppressWarnings("serial")
public abstract class SuperGUI extends JFrame {
	private WaitDialog waitD;
	/**
	 * Content Panel of the Frame.
	 */
	protected JPanel contentPane;
	/**
	 * The Panel over the background.
	 */
	protected JPanel panel;
	/**
	 * Label that contains the Background Image as ImageIcon.
	 */
	protected JLabel background;
	/**
	 * Menu Bar of the Frame.
	 */
	protected  JMenuBar menuBar;
	/**
	 * Menu component
	 */
	protected  JMenu mnMenu;
	/**
	 * Menu Item.
	 */
	protected JMenuItem mainMenu;
	/**
	 * Title of Frame.
	 */
	protected JLabel lblTitle;
	/**
	 * Clock Panel (contains current Date and Time).
	 */
	protected ClockPane clockPanel;


	/**
	 * Create new Basic User Interface with all the Basic apperance.
	 */
	public SuperGUI(GUIActions actions) {
		super("MyFuel System");
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); 
		setResizable(false);
		
		setBounds(100, 100, 596, 486);
		setLocationRelativeTo(null);
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnMenu = new JMenu("Menu");
		menuBar.add(mnMenu);
		
		 mainMenu = new JMenuItem("Return to MainMenu");
		mnMenu.add(mainMenu);
		
		
		JMenuItem Exit = new JMenuItem("Exit");
		Exit.addActionListener(new exitHandler());
		
		JMenuItem LogOut = new JMenuItem("Log Out");
		LogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actions.LogOut();
			}
		});
		mnMenu.add(LogOut);
		mnMenu.add(Exit);
		clockPanel = new ClockPane();
		menuBar.add(clockPanel);
		clockPanel.setOpaque(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setOpaque(false);
		panel.setBounds(0, 0, 596, 458);
		contentPane.add(panel);
		panel.setLayout(null);
		 
		 lblTitle = new JLabel("Title");
		 lblTitle.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 18));
		 lblTitle.setBounds(271, 6, 61, 16);
		 panel.add(lblTitle);
		 background = new JLabel("");
		 background.setBounds(0, 0, 596, 481);
		 java.net.URL url = getClass().getResource("/BackGround.png");
		
		 ImageIcon icon = new ImageIcon(url);
		 
		 java.net.URL url2 = getClass().getResource("/logout.png");
			
		 ImageIcon icon2 = new ImageIcon(url2);
		 
		 java.net.URL url3 = getClass().getResource("/exit.png");
			
		 ImageIcon icon3 = new ImageIcon(url3);
		 
		 java.net.URL url4 = getClass().getResource("/return.png");
			
		 ImageIcon icon4 = new ImageIcon(url4);
		 LogOut.setIcon(icon3);
		 Exit.setIcon(icon2);
		 mainMenu.setIcon(icon4);
		
		 background.setIcon(icon);
		contentPane.add( background);
		this.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        if (JOptionPane.showConfirmDialog(null, 
		            "Are you sure to exit MyFuel?", "Really Closing?", 
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
		            System.exit(0);
		           
		        }
		    }
		});
	}

	/**
	 * Show Error Message to the user.
	 * @param msg - The Message(String).
	 */
	public void showErrorMessage(String msg){
	
		JOptionPane.showMessageDialog(this, msg,"Error",JOptionPane.ERROR_MESSAGE);	
	}
	
	/**
	 * Show Confirm Message to the user.
	 * @param msg - The Message(String)
	 */
	public void showOKMessage(String msg){
		
		JOptionPane.showMessageDialog(this, msg,"Message",JOptionPane.INFORMATION_MESSAGE);	
	}
	
	/**
	 * This method handle all the user input events(Click a button and etc..).
	 * @param e
	 */
	public abstract void getInput(ActionEvent e);

	/**
	 *This is ActionListener for exit menu option.
	 *
	 */
	private class exitHandler implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			   if (JOptionPane.showConfirmDialog(null, 
			            "Are you sure to exit MyFuel?", "Really Closing?", 
			            JOptionPane.YES_NO_OPTION,
			            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
			            System.exit(0); }
			
		}
		
	}
	
	/***
	 * Creates waiting dialog with a message tp the user
	 * @param msg - the message for the user
	 */
	public void createWaitDialog(String msg)
	{
		waitD = new WaitDialog(msg);
		waitD.setVisible(true);
	}
	
	/***
	 * Closing the waiting dialog
	 */
	public void setWaitProgress()
	{
		waitD.setProgress(1);
	}
}
