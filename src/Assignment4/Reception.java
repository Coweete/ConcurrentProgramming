package Assignment4;

import java.util.Random;

public class Reception implements Runnable {

    private boolean isOpen;
    private EntranceWaitingQueue waitingQueue;
    private Random random;

    public Reception(EntranceWaitingQueue waitingQueue) {
        this.waitingQueue = waitingQueue;
        random = new Random();
    }

    @Override
    public void run() {
        while (isOpen) {
            if (random.nextInt(2) == 0){
                Customer temp = new Customer(true);
                waitingQueue.putInAdventurePool(temp);
            }else {
                Customer temp = new Customer(false);
                waitingQueue.putInCommonPool(temp);
            }
        }
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}
