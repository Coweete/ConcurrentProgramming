package Assignment3;

import javax.swing.*;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;

/**
 * A class that acts as a storage for the truck and factory.
 * (Buffer)
 *
 * @author Jonatan Fridsten
 */
public class Storage {

    private LinkedList<FoodItem> queue;
    private volatile int size;
    private JProgressBar jProgressBar;
    private static Semaphore mutex = new Semaphore(1);
    private static Semaphore readerSemaphore = new Semaphore(0);
    private static Semaphore writerSemaphore = new Semaphore(100);
    private JLabel jLabel;

    /**
     * The constructor for the class.
     *
     * @param size         The size of the storage.
     * @param jLabel       Information label.
     * @param jProgressBar Progressbar for storage status.
     */
    public Storage(int size, JLabel jLabel, JProgressBar jProgressBar) {
        this.size = size;
        this.jLabel = jLabel;
        this.jProgressBar = jProgressBar;
        queue = new LinkedList<FoodItem>();
        jLabel.setText("Max capacity(" + size + "):");
    }

    /**
     * Tries to add an item to the buffer,
     * only one thread can access to the storage at the time fixed by mutex.
     *
     * @param foodItem The item to add.
     * @return True = Added to Storage, False = not added to storage.
     */
    public boolean addToQueue(FoodItem foodItem) {
        try {
            //Tries to get the key.
            writerSemaphore.acquire();
            System.out.println("Got permit add");
            //Checks if the object can be added to the buffer.
            mutex.acquire();
            if (!(queue.size() >= size)) {
                queue.push(foodItem);
                //Updates the progressbar
                jProgressBar.setValue(queue.size());
                mutex.release();
            } else {
                mutex.release();
                readerSemaphore.release();
                return false;
            }
            //Releases the key
            readerSemaphore.release();
            return true;
        } catch (InterruptedException e) {
            e.printStackTrace();
            //Something went wrong.
            mutex.release();
            readerSemaphore.release();
            return false;
        }
    }

    /**
     * Tries to get an item from the buffer,
     * only one thread can access to the storage at the time fixed by mutex.
     *
     * @return Null = Queue is empty, FoodItem = got something from the queue
     */
    public FoodItem getFromQueue() {
        try {
            //Gets the key
            readerSemaphore.acquire();
            System.out.println("Got permit remove");

            FoodItem temp = null;
            //Checks if there is elements in the queue
            mutex.acquire();
            if (!queue.isEmpty()) {
                //Collects the item from the queue
                temp = queue.pop();
                //Updates the progressbar.
                jProgressBar.setValue(queue.size());

            }
            mutex.release();
            //Releases the key
            writerSemaphore.release();
            return temp;
        } catch (InterruptedException e) {
            e.printStackTrace();
            //Something went wrong
            writerSemaphore.release();
            return null;
        }
    }
}
