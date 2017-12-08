package Assignment4;

import javax.swing.*;

/**
 * A buffer for the entrance of a pool
 */
public class EntranceWaitingQueue {

    private int nbrOfWaiting;
    private JLabel jLabel;

    /**
     * The constructor for the class
     *
     * @param jLabel Outputs number of customers in the queue
     */
    public EntranceWaitingQueue(JLabel jLabel) {
        this.jLabel = jLabel;
        jLabel.setText(String.valueOf(0));
        nbrOfWaiting = 0;
    }

    /**
     * Adds a customer to the queue
     */
    public synchronized void putInQueue() {
        nbrOfWaiting++;
        jLabel.setText(String.valueOf(nbrOfWaiting));
    }

    /**
     * Removes a customer from the queue
     *
     * @return True customer exists, False no customer
     */
    public synchronized boolean getEntrance() {
        if (nbrOfWaiting > 0) {
            nbrOfWaiting--;
            jLabel.setText(String.valueOf(nbrOfWaiting));
            return true;
        } else {
            return false;
        }

    }

}
