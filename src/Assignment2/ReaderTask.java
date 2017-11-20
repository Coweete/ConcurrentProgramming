package Assignment2;

import java.util.Random;

/**
 * A reader task that reads from the character buffer.
 *
 * @author Jonatan Fridsten
 */
public class ReaderTask implements Runnable {

    private volatile boolean isRunning;
    private Controller controller;
    private CharacterBufferSynchronized characterBufferSynchronized;
    private CharacterBuffer characterBuffer;
    private char[] chars;
    private Random random;
    private int endLength, counter;

    /**
     * The constructor
     *
     * @param controller                  Controller object
     * @param characterBufferSynchronized Sync buffer
     * @param characterBuffer             Async buffer
     */
    public ReaderTask(Controller controller, CharacterBufferSynchronized characterBufferSynchronized, CharacterBuffer characterBuffer) {
        this.controller = controller;
        this.characterBufferSynchronized = characterBufferSynchronized;
        this.characterBuffer = characterBuffer;
        random = new Random();
        isRunning = false;
    }

    /**
     * The run method of the thread
     */
    @Override
    public void run() {
        while (isRunning) {
            //Checks if there is something in the buffer
            if (characterBufferSynchronized.hasCharacter()) {
                char temp = characterBufferSynchronized.removeCharacter();
                controller.printReader("Reading " + temp + "\n");
                chars[counter] = temp;
                counter++;

                //Checks if there should be
                // more in the buffer or not
                if (counter >= endLength) {
                    isRunning = false;
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 0; i < chars.length; i++) {
                        stringBuilder.append(chars[i]);

                    }
                    String res = stringBuilder.toString();
                    //Prints and calls the compare method
                    controller.printReaderString(res);
                    controller.compareStrings(res);
                }

            } else {
                controller.printReader("No data, Reader waits" + "\n");
            }
            try {
                //Tries to sleep for a random amount of time
                Thread.sleep((random.nextInt(25) + 5));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Set if the thread should be running or not.
     *
     * @param running True if the thread should be running.
     */
    public void setRunning(boolean running) {
        //Rests the counter
        counter = 0;
        isRunning = running;
    }

    /**
     * Sets the end status.
     *
     * @param length The amount of character
     *               there should be in the buffer
     */
    public void setEndLength(int length) {
        chars = new char[length];
        endLength = length;
    }

}
