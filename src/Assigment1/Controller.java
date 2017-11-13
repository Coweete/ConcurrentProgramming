package Assigment1;


import java.awt.*;

/**
 * Controller class for the application.
 *
 * @author Jonatan Fridsten
 */
public class Controller {

    private GUIAssignment1 gui;
    private RotatingTriangleTask rotatingTriangleTask;
    private RandomTextTask randomTextTask;
    private Thread randomTextThread, rotatingThread;

    /**
     * The constructor for the class.
     */
    public Controller() {
        //Creates an new instance of random task and binds it to a new thread.
        randomTextTask = new RandomTextTask("RandomTextTask", this);
        randomTextThread = new Thread(randomTextTask);
        randomTextThread.start();

        //Creates an new instance of the rotating task and binds it to a new thread.
        rotatingTriangleTask = new RotatingTriangleTask(this);
        rotatingThread = new Thread(rotatingTriangleTask);
        rotatingThread.start();

        //Creates an new instance of the UI.
        gui = new GUIAssignment1(this);
        gui.start();

        //Calculates the boarders
        calculateBorder();
    }

    /**
     * Will start display the random text.
     */
    public void startDisplay() {
        randomTextTask.setRunning(true);
    }

    /**
     * Will stop displaying the random text.
     */
    public void stopDisplay() {
        randomTextTask.setRunning(false);
    }

    /**
     * Updates the random text position.
     *
     * @param text The text that should be displayed.
     * @param x    The position on x-axis.
     * @param y    The position on y-axis.
     */
    public void updateRandomPos(String text, int x, int y) {
        calculateBorder();
        gui.placeRandomText(text, x, y);
    }

    /**
     * Start rotating the triangle.
     */
    public void startRotate() {
        rotatingTriangleTask.setRunning(true);
    }

    /**
     * Stop rotating the triangle.
     */
    public void stopRotate() {
        rotatingTriangleTask.setRunning(false);
    }

    /**
     * Rotates the angle
     *
     * @param angle amount to rotate
     */
    public void rotateTriangle(double angle) {
        gui.rotateTriangle(angle);
    }

    /**
     * Calculates the boarder for the random text
     */
    private void calculateBorder() {
        Dimension lblDimension = gui.getRandomTextDimension();
        Dimension pnlDimension = gui.getDisplayDimension();
        randomTextTask.setDimensionX(pnlDimension.width - lblDimension.width);
        randomTextTask.setDimensionY(pnlDimension.height - lblDimension.height);
    }
}
