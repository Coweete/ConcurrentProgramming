package Assignment3;

public class Truck implements Runnable {

    private int item;
    private int totalWeight;
    private int totalVolume;
    private GUISemaphore guiSemaphore;
    private Storage storage;
    private boolean isRunning = false;

    public Truck(Storage storage, GUISemaphore guiSemaphore) {
        this.storage = storage;
        this.guiSemaphore = guiSemaphore;
    }

    @Override
    public void run() {

        while (isRunning) {

        }
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }
}
