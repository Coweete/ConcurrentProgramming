package Assigment1;

import java.awt.*;

public class RotatingTriangleTask implements Runnable{

    private Controller          controller;
    private volatile boolean    isRunning;
    private double              angle;

    public RotatingTriangleTask(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void run() {

        while (true){
            if (isRunning){
                if (angle > 360){
                    angle = 0;
                }
                controller.rotateTriangle(angle);
                angle++;
            }

            sleep(10);
        }
    }

    public void sleep(int miliSeconds){

        try{
            Thread.sleep(miliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }
}
