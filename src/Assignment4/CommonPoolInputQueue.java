package Assignment4;

import java.util.Random;

/**
 * A class that takes an customer from the queue or Adventure pool
 * and puts the customer in the commonpool
 *
 * @author Jonatan Fridsten
 */
public class CommonPoolInputQueue implements Runnable {

    private AdventurePool adventurePool;
    private CommonPool commonPool;
    private EntranceWaitingQueue queue;
    private Random random;

    /**
     * The constructor for the class
     *
     * @param adventurePool The adventure pool
     * @param commonPool    The common pool
     * @param queue         The queue to the pool
     */
    public CommonPoolInputQueue(AdventurePool adventurePool, CommonPool commonPool, EntranceWaitingQueue queue) {
        this.adventurePool = adventurePool;
        this.commonPool = commonPool;
        this.queue = queue;
        this.random = new Random();
    }

    /**
     * The run method for the thread.
     */
    @Override
    public void run() {
        while (true) {
            //Checks if the customer should come from the queue or Adventure pool
            if (random.nextBoolean()) {
                if (queue.getEntrance()) {
                    commonPool.enter(new Customer(false));
                }
            } else {
                Customer customer = adventurePool.GetCustomerToCommon();
                if (customer != null) {
                    commonPool.enter(customer);
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
