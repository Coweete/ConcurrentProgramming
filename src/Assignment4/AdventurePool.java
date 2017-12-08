package Assignment4;

import javax.swing.*;
import java.util.LinkedList;

/**
 * A class that represents an adventure pool that
 * customers needs to have a vip status to enter
 *
 * @author Jonatan Fridsten
 */
public class AdventurePool {

    private LinkedList<Customer> customers;
    private int maxGuest;
    private JLabel limit;
    private JLabel current;

    /**
     * The constructor for the class
     *
     * @param maxGuest  maximum number of customers
     * @param limit     Shows the limit number of customers
     * @param current   Shows the current number of customers
     */
    public AdventurePool(int maxGuest, JLabel limit, JLabel current) {
        this.maxGuest = maxGuest;
        this.limit = limit;
        this.current = current;
        limit.setText(String.valueOf(maxGuest));
        current.setText(String.valueOf(0));
        customers = new LinkedList<Customer>();
    }

    /**
     * Adds a customer to the pool
     *
     * @param customer to join the pool
     */
    public synchronized void enter(Customer customer) {
        System.out.println("Trying to add into Adventure pool");
        try {
            while (customers.size() == maxGuest) {
                wait();
            }
            customers.push(customer);
            current.setText(String.valueOf(customers.size()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets a customer to the common pool
     *
     * @return Null = no costumer
     */
    public synchronized Customer GetCustomerToCommon() {
        if (!customers.isEmpty()) {
            current.setText(String.valueOf(customers.size()));
            notify();
            return customers.pop();
        }
        return null;
    }

    /**
     * Removes a customer from the queue
     *
     * @return True = removed, False = not removed
     */
    public synchronized boolean removeCustomer() {
        if (!customers.isEmpty()) {
            customers.pop();
            current.setText(String.valueOf(customers.size()));
            notify();
            return true;
        } else {
            return false;
        }
    }

}
