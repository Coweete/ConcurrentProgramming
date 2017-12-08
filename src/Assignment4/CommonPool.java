package Assignment4;

import javax.swing.*;
import java.util.LinkedList;

/**
 * Shared resources for the common pool
 *
 * @author Jonatan Fridsten
 */
public class CommonPool {

    private LinkedList<Customer> queue;
    private int maxGuest;
    private JLabel limit;
    private JLabel current;

    /**
     * The constructor for the class
     *
     * @param maxGuest maximum number of customers
     * @param limit    Shows the limit number of customers
     * @param current  Shows the current number of customers
     */
    public CommonPool(int maxGuest, JLabel limit, JLabel current) {
        this.maxGuest = maxGuest;
        this.limit = limit;
        this.current = current;
        limit.setText(String.valueOf(maxGuest));
        current.setText(String.valueOf(0));
        queue = new LinkedList<Customer>();
    }

    /**
     * Add a customer to the pool
     *
     * @param customer the customer that joins the pool
     */
    public synchronized void enter(Customer customer) {
        System.out.println("Trying to add into Common pool");
        try {
            while ((queue.size() == maxGuest)) {
                wait();
            }
            queue.push(customer);
            current.setText(String.valueOf(queue.size()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * Check if vip is true then return the customer
     * else if vip is false return null
     *
     * @return A customer or null
     */
    public synchronized Customer getCustomerToAdventure() {
        if (!queue.isEmpty()) {
            if (queue.peek().isVip()) {
                Customer temp = queue.pop();
                current.setText(String.valueOf(queue.size()));
                notify();
                return temp;
            }
        }
        return null;
    }

    /**
     * Removes a customer from the pool
     *
     * @return True = remove,False no remove
     */
    public synchronized boolean removeCustomer() {
        if (!queue.isEmpty()) {
            queue.pop();
            current.setText(String.valueOf(queue.size()));
            notify();
            return true;
        }
        return false;
    }

}
