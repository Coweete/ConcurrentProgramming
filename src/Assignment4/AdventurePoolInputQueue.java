package Assignment4;

import java.util.LinkedList;
import java.util.Random;

public class AdventurePoolInputQueue implements Runnable {

    private AdventurePool adventurePool;
    private CommonPool commonPool;
    private EntranceWaitingQueue queue;
    private Random random;


    public AdventurePoolInputQueue(AdventurePool adventurePool, CommonPool commonPool, EntranceWaitingQueue queue) {
        this.adventurePool = adventurePool;
        this.commonPool = commonPool;
        this.queue = queue;
        this.random = new Random();
    }


    @Override
    public void run() {

        while (true) {
            System.out.println("Running Adventure pool");

            if (random.nextInt(2) == 1) {
                if (queue.getEntrance()) {
                    adventurePool.enter(new Customer(true));
                }
            } else {
                Customer customer = commonPool.getCustomerToAdventure();
                if (customer != null) {
                    adventurePool.enter(customer);
                }
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
