package myfuel.gui;

import javax.swing.JProgressBar;

class ProgressBarThread implements Runnable {

    /**
     * Progress bar that shows the current status
     */
    private FuelDialog gui;
    private float value=0;
    private float max ;

    /**
     * Constructor
     * @param jpb The progress bar this has to update
     */
    public ProgressBarThread(FuelDialog gui,float max) {
        this.gui = gui;
        this.max = max;
    }


    /**
     * Action of the thread will be executed here. The value of the progress bar will be set here.
     */
    public void run() {
        do {
        		value+=0.1;
        		gui.setProgress(value);
            
            try {
                java.lang.Thread.sleep(20);
            } catch (java.lang.InterruptedException ex) {
                ex.printStackTrace();
            }
        } while (value < max);
    }
}