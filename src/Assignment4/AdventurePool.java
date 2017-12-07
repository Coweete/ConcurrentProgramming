package Assignment4;

import javax.swing.*;
import java.util.LinkedList;

public class AdventurePool {

    private LinkedList<Customer> customers;
    private int maxGuest;
    private JLabel limit;
    private JLabel current;

    public AdventurePool(int maxGuest, JLabel limit, JLabel current) {
        this.maxGuest = maxGuest;
        this.limit = limit;
        this.current = current;
        limit.setText(String.valueOf(maxGuest));
        current.setText(String.valueOf(0));
        customers = new LinkedList<Customer>();
    }

    public synchronized void enter(Customer customer) {
        System.out.println("Trying to add into Adventure pool");
        try {
            while (customers.size() == maxGuest) {
                wait();
            }
            customers.push(customer);
            current.setText(String.valueOf(customers.size()));
            //notify();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized Customer GetCustomerToCommon() {
        if (!customers.isEmpty()) {
            current.setText(String.valueOf(customers.size()));
            notify();
            return customers.pop();
        }
        return null;
    }

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
