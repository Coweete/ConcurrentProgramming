package Assignment3;

public class Assignment3 {

    public static void main(String[] args) {
        Storage storage = new Storage(100);
        GUISemaphore guiSemaphore = new GUISemaphore(storage);
        guiSemaphore.start();
    }
}
