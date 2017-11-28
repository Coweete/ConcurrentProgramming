package Assignment3;

import java.util.ArrayList;

public class Storage {

    private ArrayList<FoodItem> queue;
    private boolean isFull;
    private boolean isEmpty;
    private int size;


    public Storage(int size){
        this.size = size;
        queue = new ArrayList<FoodItem>();
        isEmpty = true;
        isFull = false;
    }

    public synchronized boolean addToQueue(FoodItem foodItem){


        return false;
    }

}
