package myfuel.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class ClockPane extends JPanel {
		
		/**
		 * clock value label
		 */
        private JLabel clock;

       
        /**
         * Create new Clock Panel that contains JLabel clock
         */
        public ClockPane() {
            setLayout(new BorderLayout());
            clock = new JLabel();
            clock.setHorizontalAlignment(JLabel.CENTER);
            clock.setFont(clock.getFont().deriveFont(clock.getFont().getStyle() | Font.BOLD, 12f));
            tickTock();
            add(clock);

            Timer timer = new Timer(500, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    tickTock();
                }
            });
            timer.setRepeats(true);
            timer.setCoalesce(true);
            timer.setInitialDelay(0);
            timer.start();
        }
        
        /**
         *   Update clock label every half of a second.
         */
        public void tickTock() {
        	SimpleDateFormat format = new SimpleDateFormat("EEE, d MMM yyyy HH:mm");
            clock.setText(format.format(new Date()));
        }
    
}