package Assigment1;

/**
 * Class that rotates an triangle.
 *
 * @author Jonatan Fridsten
 */
public class RotatingTriangleTask implements Runnable {

    private Controller controller;
    private volatile boolean isRunning;
    private double angle;

    /**
     * Constructor
     *
     * @param controller Instance of the controller
     */
    public RotatingTriangleTask(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void run() {
        //Lets the thread running all the time
        while (true) {
            //If the thread should be running or not
            if (isRunning) {
                //Rests the angle
                if (angle > 360) {
                    angle = 0;
                }
                controller.rotateTriangle(angle);
                angle++;
            }

            //Tries too sleep
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * Sets if the thread should be doing something or sleep
     * @param running true == running, false == sleep
     */
    public void setRunning(boolean running) {
        isRunning = running;
    }
}
