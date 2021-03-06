package Assignment3;

import javax.swing.*;
import java.util.Random;

/**
 * This class acts as an Writer.
 * Generates an object that it tries to put into the Storage(Buffer)
 *
 * @author Jonatan Fridsten
 */
public class FactoryV2 implements Runnable {

    private FoodItem[] foodBuffer;
    private boolean isRunning;
    private boolean isIdle;
    private Storage storage;
    private Random random;
    private JLabel lblStatus;

    /**
     * The constructor.
     *
     * @param storage   Storage reference.
     * @param lblStatus Label to display status on the thread.
     */
    public FactoryV2(Storage storage, JLabel lblStatus) {
        this.storage = storage;
        this.lblStatus = lblStatus;
        random = new Random();
        isRunning = false;
        isIdle = true;
        initFoodItems();
    }

    /**
     * The run method for the class.
     */
    @Override
    public void run() {

        while (isRunning) {

            //Generates a random object.
            FoodItem newFoodItem = foodBuffer[random.nextInt(20)];

            //Tries to put the object into the storage.
            if (storage.addToQueue(newFoodItem)) {
                //Checks if the label should be updated
                if (!isIdle) {
                    lblStatus.setText("Producing");
                    isIdle = true;
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                //Checks if the label should be updated
                if (isIdle) {
                    lblStatus.setText("idle");
                    isIdle = false;
                }
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Creates 20 objects and puts them into an array.
     */
    private void initFoodItems() {
        foodBuffer = new FoodItem[20];
        foodBuffer[0] = new FoodItem("Milk", 1.1, 0.5);
        foodBuffer[1] = new FoodItem("Cream", 0.6, 0.1);
        foodBuffer[2] = new FoodItem("Youghurt", 1.1, 0.5);
        foodBuffer[3] = new FoodItem("Butter", 2.34, 0.66);
        foodBuffer[4] = new FoodItem("Flower", 3.4, 1.2);
        foodBuffer[5] = new FoodItem("Sugar", 3.7, 1.8);
        foodBuffer[6] = new FoodItem("Salt", 1.55, 0.27);
        foodBuffer[7] = new FoodItem("Almonds", 0.6, 0.19);
        foodBuffer[8] = new FoodItem("Bread", 1.98, 0.75);
        foodBuffer[9] = new FoodItem("Donuts", 1.4, 0.5);
        foodBuffer[10] = new FoodItem("Jam", 1.3, 1.5);
        foodBuffer[11] = new FoodItem("Ham", 4.1, 2.5);
        foodBuffer[12] = new FoodItem("Chicken", 6.8, 3.9);
        foodBuffer[13] = new FoodItem("Salat", 0.87, 0.55);
        foodBuffer[14] = new FoodItem("Orange", 2.46, 0.29);
        foodBuffer[15] = new FoodItem("Apple", 2.44, 0.4);
        foodBuffer[16] = new FoodItem("Pear", 1.3, 0.77);
        foodBuffer[17] = new FoodItem("Soda", 2.98, 2.0);
        foodBuffer[18] = new FoodItem("Beer", 3.74, 1.5);
        foodBuffer[19] = new FoodItem("Hotdogs", 2.0, 1.38);
    }

    /**
     * Place the thread into running or not.
     *
     * @param running True = Running, False = Not running.
     */
    public void setRunning(boolean running) {
        isRunning = running;
    }
}
