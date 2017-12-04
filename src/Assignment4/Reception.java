package Assignment4;

import java.util.Random;

public class Reception  implements Runnable{

    private boolean         isOpen;
    private AdventurePool   adventurePool;
    private CommonPool      commonPool;
    private Random          random;

    public Reception(AdventurePool adventurePool,CommonPool commonPool){
        this.adventurePool = adventurePool;
        this.commonPool = commonPool;
        random = new Random();
    }

    @Override
    public void run() {
        while (isOpen){

        }
    }

    public void RunReception(){
        isOpen = true;
        //Start here or nah ?
    }

    public boolean isOpen() {
        return isOpen;
    }
}
