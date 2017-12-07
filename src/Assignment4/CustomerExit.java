package Assignment4;

import javax.swing.*;
import java.util.Random;

public class CustomerExit implements Runnable{

    private AdventurePool adventurePool;
    private CommonPool commonPool;
    private int nbrExitAdventurePool;
    private int nbrExitCommonPool;
    private Random random;
    private JLabel lblCmnExit;
    private JLabel lblAdvExit;
    private boolean isRunning;

    public CustomerExit(AdventurePool adventurePool, CommonPool commonPool, JLabel lblCmnExit, JLabel lblAdvExit) {
        this.adventurePool = adventurePool;
        this.commonPool = commonPool;
        this.lblCmnExit = lblCmnExit;
        this.lblAdvExit = lblAdvExit;
        nbrExitCommonPool = 0;
        nbrExitAdventurePool = 0;
        lblCmnExit.setText(String.valueOf(nbrExitCommonPool));
        lblAdvExit.setText(String.valueOf(nbrExitAdventurePool));
        random = new Random();

    }

    @Override
    public void run() {
        while (true){
                if (random.nextBoolean()){
                    if (adventurePool.removeCustomer()){
                        nbrExitAdventurePool++;
                        lblAdvExit.setText(String.valueOf(nbrExitAdventurePool));
                    }
                }   else {
                    if (commonPool.removeCustomer()){
                        nbrExitCommonPool++;
                        lblCmnExit.setText(String.valueOf(nbrExitCommonPool));
                    }
                }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



    public void setRunning(boolean running) {
        isRunning = running;
    }
}
