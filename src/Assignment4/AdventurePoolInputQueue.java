package Assignment4;

import java.util.Random;

/**
 * This class will get a customer from the common pool or the
 * queue and put the customer into the adventure pool
 *
 * @author Joantan Fridsten
 */
public class AdventurePoolInputQueue implements Runnable {

    private AdventurePool adventurePool;
    private CommonPool commonPool;
    private EntranceWaitingQueue queue;
    private Random random;

    /**
     * The constructor for the class
     *
     * @param adventurePool Adventure pool
     * @param commonPool    Common pool
     * @param queue         The queue
     */
    public AdventurePoolInputQueue(AdventurePool adventurePool, CommonPool commonPool, EntranceWaitingQueue queue) {
        this.adventurePool = adventurePool;
        this.commonPool = commonPool;
        this.queue = queue;
        this.random = new Random();
    }


    /**
     * Run method for this thread
     */
    @Override
    public void run() {

        while (true) {
            //Random if the customer should come from common pool or the queue
            if (random.nextBoolean()) {
                if (queue.getEntrance()) {
                    adventurePool.enter(new Customer(true));
                }
            } else {
                Customer customer = commonPool.getCustomerToAdventure();
                if (customer != null) {
                    adventurePool.enter(customer);
                }
            }

            //Trying to pause the thread
            try {
                Thread.sleep(random.nextInt(200) + 100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
