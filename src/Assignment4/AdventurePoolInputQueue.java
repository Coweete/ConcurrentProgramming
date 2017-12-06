package Assignment4;

import java.util.LinkedList;
import java.util.Random;

public class AdventurePoolInputQueue implements Runnable{

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
        Customer customer;
        while (true){
            if(random.nextInt(2) == 1){
                //Enter Adventure pool
                adventurePool.enter(new Customer(true));
            }else{
                customer = commonPool.getCustomerToAdventure();
                if(customer != null){
                    adventurePool.enter(customer);
                }
            }

        }
    }
}
