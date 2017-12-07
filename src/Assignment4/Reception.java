package Assignment4;

import java.util.Random;

public class Reception implements Runnable {

    private boolean isOpen;
    private EntranceWaitingQueue adventureQueue;
    private EntranceWaitingQueue commonQueue;
    private Random random;

    public Reception(EntranceWaitingQueue adventureQueue, EntranceWaitingQueue commonQueue) {
        this.adventureQueue = adventureQueue;
        this.commonQueue = commonQueue;
        random = new Random();
    }

    @Override
    public void run() {
        System.out.println("Hello??");
        while (true) {
            System.out.println("Running");
            if (isOpen) {
                System.out.println("Running reception");
                if (random.nextInt(2) == 0) {
                    adventureQueue.putInQueue();
                } else {
                    commonQueue.putInQueue();
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void triggerOpen() {
        isOpen = !isOpen;
    }
}
