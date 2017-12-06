package Assignment4;

import javax.swing.*;
import java.util.LinkedList;

public class CommonPool {

    private LinkedList<Customer> queue;
    private int maxGuest;
    private JLabel jLabel;

    public CommonPool(int maxGuest, JLabel jLabel) {
        this.maxGuest = maxGuest;
        this.jLabel = jLabel;
        queue = new LinkedList<Customer>();
    }

    /**
     * Check if vip is true then return the customer
     * else if vip is false return null
     *
     * @return A customer or null
     */
    public Customer getCustomerToAdventure() {
        return null;
    }
}
