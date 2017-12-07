package Assignment4;

import java.util.LinkedList;
import java.util.Random;

public class CommonPoolInputQueue implements Runnable {

    private AdventurePool adventurePool;
    private CommonPool commonPool;
    private EntranceWaitingQueue queue;
    private Random random;
    private boolean isRunning;

    public CommonPoolInputQueue(AdventurePool adventurePool, CommonPool commonPool, EntranceWaitingQueue queue) {
        this.adventurePool = adventurePool;
        this.commonPool = commonPool;
        this.queue = queue;
        this.random = new Random();
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("Running Common pool");
            if (random.nextInt(2) == 1) {
                if (queue.getEntrance()) {
                    commonPool.enter(new Customer(false));
                }
            } else {
                Customer customer = adventurePool.GetCustomerToCommon();
                if (customer != null) {
                    commonPool.enter(customer);
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
