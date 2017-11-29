package Assignment3;

import javax.swing.*;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class Storage {

    private LinkedList<FoodItem> queue;
    private volatile int size;
    private GUISemaphore guiSemaphore;
    private static Semaphore semaphore = new Semaphore(1);
    private JLabel jLabel;

    public Storage(GUISemaphore guiSemaphore, int size, JLabel jLabel) {
        this.size = size;
        this.guiSemaphore = guiSemaphore;
        this.jLabel = jLabel;
        queue = new LinkedList<FoodItem>();
        jLabel.setText("Max capacity(" + size + "):");
    }

    public boolean addToQueue(FoodItem foodItem) {
        System.out.println("Trying to add an item");
        System.out.println("Item name: " + foodItem.getName());
        try {
            semaphore.acquire();
            System.out.println("Got permit add");
            if (!(queue.size() >= size)) {
                queue.add(foodItem);
            } else {
                semaphore.release();
                return false;
            }
            //Update bar
            guiSemaphore.updateProgressbar(queue.size());
            semaphore.release();
            return true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    public FoodItem getFromQueue() {
        //@Todo Make check in this class ?
        try {
            semaphore.acquire();
            System.out.println("Got permit remove");
            FoodItem temp = null;
            System.out.println("Queue empty? " + queue.isEmpty());
            if (!queue.isEmpty()) {
                temp = queue.remove();
            }
            //update bar ?
            guiSemaphore.updateProgressbar(queue.size());
            semaphore.release();
            return temp;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }


}
