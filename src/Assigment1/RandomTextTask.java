package Assigment1;

import java.util.Random;

public class RandomTextTask implements Runnable {

    private volatile boolean isRunning = false;
    private String text;
    private int x = 0, y = 0;
    private int dimensionX = 200,dimensionY = 200;
    private Random random;
    private Controller controller;

    public RandomTextTask(String text, Controller controller) {
        this.text = text;
        this.controller = controller;
        random = new Random();
    }

    @Override
    public void run() {
        while (true) {
            if (isRunning) {
                x = random.nextInt(dimensionX);
                y = random.nextInt(dimensionY);
                controller.updateRandomPos(text, x, y);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setDimensionX(int dimensionX) {
        this.dimensionX = dimensionX;
    }

    public void setDimensionY(int dimensionY) {
        this.dimensionY = dimensionY;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }
}
