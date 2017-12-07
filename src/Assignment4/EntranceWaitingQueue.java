package Assignment4;

import javax.swing.*;

public class EntranceWaitingQueue {

    private int nbrOfWaiting;
    private JLabel jLabel;

    public EntranceWaitingQueue(JLabel jLabel) {
        this.jLabel = jLabel;
        jLabel.setText(String.valueOf(0));
        nbrOfWaiting = 0;
    }

    public synchronized void putInQueue() {
        nbrOfWaiting++;
        jLabel.setText(String.valueOf(nbrOfWaiting));
    }

    public synchronized boolean getEntrance() {
        if (nbrOfWaiting > 0) {
            nbrOfWaiting--;
            jLabel.setText(String.valueOf(nbrOfWaiting));
            return true;
        } else {
            return false;
        }

    }

}
