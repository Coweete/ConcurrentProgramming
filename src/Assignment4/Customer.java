package Assignment4;

/**
 * A class that represents a customer
 */
public class Customer {

    private boolean isVip;

    /**
     * The constructor
     *
     * @param isVip true = allowed to join adventure pool
     */
    public Customer(boolean isVip) {
        this.isVip = isVip;
    }

    /**
     * Gets if the customer is a vip or not
     *
     * @return vip status
     */
    public boolean isVip() {
        return isVip;
    }

}
