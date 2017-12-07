package Assignment4;

import javax.swing.*;
import java.util.LinkedList;

public class CommonPool {

    private LinkedList<Customer> queue;
    private int maxGuest;
    private JLabel limit;
    private JLabel current;

    public CommonPool(int maxGuest, JLabel limit, JLabel current) {
        this.maxGuest = maxGuest;
        this.limit = limit;
        this.current = current;
        limit.setText(String.valueOf(maxGuest));
        current.setText(String.valueOf(0));
        queue = new LinkedList<Customer>();
    }

    public synchronized void enter(Customer customer) {
        System.out.println("Trying to add into Common pool");
        try {
            while ((queue.size() == maxGuest)) {
                wait();
            }
            queue.push(customer);
            current.setText(String.valueOf(queue.size()));
            //notify();
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
