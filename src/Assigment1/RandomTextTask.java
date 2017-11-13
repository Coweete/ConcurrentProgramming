package Assigment1;

import java.util.Random;

/**
 * A class that will place a text on a random place.
 *
 * @author Jonatan Fridsten
 */
public class RandomTextTask implements Runnable {

    private volatile boolean isRunning = false;
    private String text;
    private int x = 0;
    private int y = 0;
    private int dimensionX = 200;
    private int dimensionY = 200;
    private Random random;
    private Controller controller;

    /**
     * The constructor
     *
     * @param text       The text that will be displayed
     * @param controller The controller object
     */
    public RandomTextTask(String text, Controller controller) {
        this.text = text;
        this.controller = controller;
        random = new Random();  // Creates an instance off a random object
    }

    @Override
    public void run() {
        while (true) {
            //Checks if the thread should do something or try to sleep
            if (isRunning) {
                //Generates random int values
                x = random.nextInt(dimensionX);
                y = random.nextInt(dimensionY);
                //Updates the text position1
                controller.updateRandomPos(text, x, y);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Set maximum random value on the x position.
     *
     * @param dimensionX
     */
    public void setDimensionX(int dimensionX) {
        this.dimensionX = dimensionX;
    }

    /**
     * Set maximum random value on the y position.
     *
     * @param dimensionY
     */
    public void setDimensionY(int dimensionY) {
        this.dimensionY = dimensionY;
    }

    /**
     * Sets if the thread should be doing something or sleep
     *
     * @param running true == running, false == sleep
     */
    public void setRunning(boolean running) {
        isRunning = running;
    }
}
