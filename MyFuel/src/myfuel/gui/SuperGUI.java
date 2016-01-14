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

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

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
	public SuperGUI() {
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
	
	public void createWaitDialog(String msg)
	{
		waitD = new WaitDialog(msg);
		waitD.setVisible(true);
	}
	
	public void setWaitProgress()
	{
		waitD.setProgress(1);
	}
	
	
}
