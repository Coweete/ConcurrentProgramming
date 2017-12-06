package Assignment4;

import javax.swing.*;
import java.util.LinkedList;

public class AdventurePool {

    private LinkedList<Customer> customers;
    private int maxGuest;
    private JLabel label;

    public AdventurePool(int maxGuest, JLabel label) {
        this.maxGuest = maxGuest;
        this.label = label;
        customers = new LinkedList<Customer>();
    }

    public void enter(Customer customer) {

    }

    //@TODO Should send a customer to the common pool
    public Customer GetCustomerToCommon(){
        return null;
    }

    //
    public void display(){

    }

    public void picture(){

    }

    //Return or ?
    public void removeCustomer(){

    }

    public void setSign(){

    }
}
