package Assignment4;

import javax.swing.*;
import java.util.Random;

public class CustomerExit implements Runnable{

    private AdventurePool adventurePool;
    private CommonPool commonPool;
    private int nbtExitAdventurePool;
    private int nbrExitCommonPool;
    private Random random;
    private JLabel commmonPoolExit;
    private JLabel adventurePoolExit;
    private boolean isRunning;

    public CustomerExit(AdventurePool adventurePool, CommonPool commonPool, JLabel commmonPoolExit, JLabel adventurePoolExit) {
        this.adventurePool = adventurePool;
        this.commonPool = commonPool;
        this.commmonPoolExit = commmonPoolExit;
        this.adventurePoolExit = adventurePoolExit;
        nbrExitCommonPool = 0;
        nbtExitAdventurePool = 0;
        random = new Random();
    }

    @Override
    public void run() {
        while (isRunning){

        }
    }

    //outAdv & outCom


    public void setRunning(boolean running) {
        isRunning = running;
    }
}
