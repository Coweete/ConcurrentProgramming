package Assignment4;

import javax.swing.*;
import java.util.Random;

/**
 * A class that takes a customer from one of the two pools and places it into
 * the exit queue
 *
 * @author Jonatan Fridsten
 */
public class CustomerExit implements Runnable {

    private AdventurePool adventurePool;
    private CommonPool commonPool;
    private int nbrExitAdventurePool;
    private int nbrExitCommonPool;
    private Random random;
    private JLabel lblCmnExit;
    private JLabel lblAdvExit;

    /**
     * The constructor for the class
     *
     * @param adventurePool Adventure pool
     * @param commonPool    Common pool
     * @param lblCmnExit    Common label output
     * @param lblAdvExit    Adventure label output
     */
    public CustomerExit(AdventurePool adventurePool, CommonPool commonPool, JLabel lblCmnExit, JLabel lblAdvExit) {
        this.adventurePool = adventurePool;
        this.commonPool = commonPool;
        this.lblCmnExit = lblCmnExit;
        this.lblAdvExit = lblAdvExit;
        nbrExitCommonPool = 0;
        nbrExitAdventurePool = 0;
        lblCmnExit.setText(String.valueOf(nbrExitCommonPool));
        lblAdvExit.setText(String.valueOf(nbrExitAdventurePool));
        random = new Random();

    }

    /**
     * The run method for this thread.
     */
    @Override
    public void run() {
        while (true) {
            //Randomly selects which pool that the customer should leave
            if (random.nextBoolean()) {
                //Checks for customer
                if (adventurePool.removeCustomer()) {
                    nbrExitAdventurePool++;
                    lblAdvExit.setText(String.valueOf(nbrExitAdventurePool));
                }
            } else {
                //Checks for customer
                if (commonPool.removeCustomer()) {
                    nbrExitCommonPool++;
                    lblCmnExit.setText(String.valueOf(nbrExitCommonPool));
                }
            }
            //Try to pause the thread.
            try {
                Thread.sleep(random.nextInt(800) + 100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
