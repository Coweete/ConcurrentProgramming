package Assignment3;

import javax.swing.*;

/**
 * A class that collects items form a buffer and print it out on the UI.
 *
 * @author Jonatan Fridsten
 */
public class Truck implements Runnable {

    private int currentItem, totalItem;
    private double currentWeight, totalWeight;
    private double currentVolume, totalVolume;
    private JLabel lblItem;
    private JLabel lblVolume;
    private JLabel lblWeight;
    private JLabel lblStatus;
    private JCheckBox checkBox;
    private boolean isRunning;
    private Storage storage;
    private JTextArea jTextArea;
    private boolean isFull;

    /**
     * The constructor for Truck
     *
     * @param totalItem   The total items the truck handles.
     * @param totalWeight The total weight the truck handles.
     * @param totalVolume The total volume of the truck.
     * @param lblItem     Label for item status.
     * @param lblVolume   Label for volume status.
     * @param lblWeight   Label for weight status.
     * @param lblStatus   Label for the thread status.
     * @param checkBox    Checkbox for status about the thread running.
     * @param storage     The storage object(Buffer).
     * @param jTextArea   TextArea for item names.
     */
    public Truck(int totalItem, double totalWeight, double totalVolume, JLabel lblItem,
                 JLabel lblVolume, JLabel lblWeight, JLabel lblStatus, JCheckBox checkBox,
                 Storage storage, JTextArea jTextArea) {
        this.totalItem = totalItem;
        this.totalWeight = totalWeight;
        this.totalVolume = totalVolume;
        this.lblItem = lblItem;
        this.lblVolume = lblVolume;
        this.lblWeight = lblWeight;
        this.lblStatus = lblStatus;
        this.checkBox = checkBox;
        this.storage = storage;
        this.jTextArea = jTextArea;
        currentItem = 0;
        currentVolume = 0.0;
        currentWeight = 0.0;
    }

    /**
     * The threads running method
     */
    @Override
    public void run() {
        //If the thread has been stopped before then clear out the old values
        if (currentWeight != 0 || currentItem != 0 || currentVolume != 0) {
            currentItem = 0;
            currentVolume = 0.0;
            currentWeight = 0.0;
            lblItem.setText("0");
            lblVolume.setText("0");
            lblWeight.setText("0");
            jTextArea.setText("");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        while (isRunning) {
            System.out.println("Running truck V2");
            //Checks if the truck is full or not
            if (!isFull) {
                lblStatus.setText("Running");
                isFull = true;
            }
            //Collects food item
            FoodItem foodItem = storage.getFromQueue();

            //Checks if there was an foodItem
            if (foodItem != null) {

                //Checks if the truck can add the item to the own storage
                if (((foodItem.getVolume() + currentVolume) < totalVolume) &&
                        ((foodItem.getWeight() + currentWeight) < totalWeight) &&
                        ((currentItem + 1) < totalItem)) {
                    //Updates variables and the Ui
                    currentWeight = currentWeight + foodItem.getWeight();
                    currentVolume = currentVolume + foodItem.getVolume();
                    currentItem++;
                    lblItem.setText(String.valueOf(currentItem));
                    lblWeight.setText(String.valueOf(currentWeight));
                    lblVolume.setText(String.valueOf(currentVolume));
                    jTextArea.append(foodItem.getName() + "\n");

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    //Try to put the item back too the queue
                    storage.addToQueue(foodItem);
                    //Checks if the truck should collect more stuff
                    if (checkBox.isSelected()) {
                        //Clears the truck
                        currentItem = 0;
                        currentVolume = 0.0;
                        currentWeight = 0.0;
                        isFull = false;
                        lblStatus.setText("Full");
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        //Clears the Ui
                        lblItem.setText("0");
                        lblVolume.setText("0");
                        lblWeight.setText("0");
                        jTextArea.setText("");
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        //Stops the truck because its full
                        lblStatus.setText("Full and stop");
                        isRunning = false;
                    }
                }
            } else {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Sets if the thread should be running or not.
     *
     * @param running True = Running, False = Not Running.
     */
    public void setRunning(boolean running) {
        isRunning = running;
    }
}
