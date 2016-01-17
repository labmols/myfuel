package myfuel.Tools;

import myfuel.GUI.FuelDialog;


/**
 * This class simulate fuel progress by Thread execute.
 * @author Maor
 *
 */
public class CarFuelProgress implements Runnable {

   /**
    * The User Interface object.
    */
    private FuelDialog gui;
    /**
     * Initial value.
     */
    private float value=0;
    /**
     * 
     */
    private float max ;

  /**
   * Create new Car Fuel Progress thread.
   * @param gui - the User interface.
   * @param max - Max value(the order amount).
   */
    public CarFuelProgress(FuelDialog gui,float max) {
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
                java.lang.Thread.sleep(50);
            } catch (java.lang.InterruptedException ex) {
                ex.printStackTrace();
            }
        } while (value < max);
    }
}