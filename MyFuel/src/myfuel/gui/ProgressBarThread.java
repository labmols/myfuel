package myfuel.gui;

import javax.swing.JProgressBar;

class ProgressBarThread implements Runnable {

    /**
     * Progress bar that shows the current status
     */
    private CarFuelGUI gui;
    private int value=0;

    /**
     * Constructor
     * @param jpb The progress bar this has to update
     */
    public ProgressBarThread(CarFuelGUI gui) {
        this.gui = gui;
    }


    /**
     * Action of the thread will be executed here. The value of the progress bar will be set here.
     */
    public void run() {
        do {
        		value++;
        		gui.setProgress(value);
            
            try {
                java.lang.Thread.sleep(200);
            } catch (java.lang.InterruptedException ex) {
                ex.printStackTrace();
            }
        } while (value < 100);
    }
}