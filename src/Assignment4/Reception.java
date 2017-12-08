package Assignment4;

import java.util.Random;

/**
 * A class that put customers in an queue for the two pools
 *
 * @author Jonatan Fridsten
 */
public class Reception implements Runnable {

    private boolean isOpen;
    private EntranceWaitingQueue adventureQueue;
    private EntranceWaitingQueue commonQueue;
    private Random random;

    /**
     * The constructor for the class
     *
     * @param adventureQueue Adventure pool queue
     * @param commonQueue    Common pool queue
     */
    public Reception(EntranceWaitingQueue adventureQueue, EntranceWaitingQueue commonQueue) {
        this.adventureQueue = adventureQueue;
        this.commonQueue = commonQueue;
        random = new Random();
    }

    /**
     * The run method for the thread.
     */
    @Override
    public void run() {
        while (true) {
            //Checks if open
            if (isOpen) {
                if (random.nextBoolean()) {
                    adventureQueue.putInQueue();
                } else {
                    commonQueue.putInQueue();
                }
            }
            try {
                Thread.sleep(random.nextInt(200) + 100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Method for triggering if its open or not.
     */
    public void triggerOpen() {
        isOpen = !isOpen;
    }
}
