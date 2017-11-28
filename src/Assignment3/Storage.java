package Assignment3;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class Storage {

    private LinkedList<FoodItem> queue;
    private boolean isFull;
    private boolean isEmpty;
    private int size;
    private GUISemaphore guiSemaphore;
    private static Semaphore semaphore = new Semaphore(1);

    public Storage(GUISemaphore guiSemaphore, int size) {
        this.size = size;
        this.guiSemaphore = guiSemaphore;
        queue = new LinkedList<FoodItem>();
        isEmpty = true;
        isFull = false;
    }

    public boolean addToQueue(FoodItem foodItem) {
        System.out.println("Trying to add a item");
        System.out.println("Item name: " + foodItem.getName());
        try {
            semaphore.acquire();
            System.out.println("Got permit");
            if (!(queue.size() >= size)) {
                queue.add(foodItem);
            } else {
                semaphore.release();
                return false;
            }
            //Update bar ?
            semaphore.release();
            return true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    public FoodItem getFromQueue() {
        try {
            semaphore.acquire();
            System.out.println("Got permit");
            FoodItem temp = null;
            if (!queue.isEmpty()) {
                temp = queue.remove();
            }
            //update bar ?
            semaphore.release();
            return temp;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

}
