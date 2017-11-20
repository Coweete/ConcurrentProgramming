package Assignment2;

import java.util.Random;

/**
 * A writer class that writes an character to the buffer.
 *
 * @author Jonatan Fridsten
 */
public class WriterTask implements Runnable {

    private volatile boolean isRunning;
    private volatile boolean sync;
    private String string;
    private int counter;
    private Controller controller;
    private CharacterBuffer characterBuffer;
    private Random random;

    /**
     * The Constructor.
     *
     * @param controller                  The controller object.
     * @param characterBuffer The sync buffer.
     */
    public WriterTask(Controller controller,CharacterBuffer characterBuffer) {
        this.controller = controller;
        this.characterBuffer = characterBuffer;
        random = new Random();
        isRunning = false;
    }

    /**
     * The running method for the thread
     */
    @Override
    public void run() {

        while (isRunning) {
            if (sync) {

                //Checks if the buffer is empty
                char temp = string.charAt(counter);
                if (characterBuffer.addCharacter(temp)) {
                    controller.printWriter("Writing " + temp + "\n");
                    counter++;

                    //If there is more character to put in the buffer.
                    if (counter >= string.length()) {
                        isRunning = false;
                        controller.printWriterString(string);
                    }
                } else {
                    controller.printWriter("Data Exists Writer waits" + "\n");
                }


            } else {

                char temp = string.charAt(counter);
                characterBuffer.addCharacters(temp);
                controller.printWriter("Writing " + temp + "\n");
                counter++;

                //If there is more character to put in the buffer.
                if (counter >= string.length()) {
                    isRunning = false;
                    controller.printWriterString(string);
                }
            }
            try {
                //Tries to sleep the thread a random amount of time.
                Thread.sleep((random.nextInt(50) + 5));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }


    /**
     * Setts if the thread should be running or not.
     *
     * @param running true for running
     */
    public void setRunning(boolean running) {
        //Restarts the counter.
        counter = 0;
        isRunning = running;
    }

    /**
     * Sets the string that should be put into the buffer.
     *
     * @param string The input string
     */
    public void setString(String string) {
        this.string = string;
    }

    /**
     * Returns the string that where put into the buffer.
     *
     * @return The input string.
     */
    public String getString() {
        return string;
    }

    public void setSync(boolean sync) {
        this.sync = sync;
    }
}
