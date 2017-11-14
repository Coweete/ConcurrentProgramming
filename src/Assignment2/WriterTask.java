package Assignment2;

import java.util.Random;

public class WriterTask implements Runnable {

    private boolean isRunning;
    private boolean sync;
    private String string;
    private int counter;
    private Controller controller;
    private CharacterBufferSynchronized characterBufferSynchronized;
    private CharacterBuffer characterBuffer;
    private Random random;

    public WriterTask(Controller controller, CharacterBufferSynchronized characterBufferSynchronized, CharacterBuffer characterBuffer) {
        this.controller = controller;
        this.characterBufferSynchronized = characterBufferSynchronized;
        this.characterBuffer = characterBuffer;
        random = new Random();
        isRunning = false;
    }

    @Override
    public void run() {

        while (true) {
            if (isRunning) {
                if (sync) {
                    if (!characterBufferSynchronized.hasCharacter()) {
                        char temp = string.charAt(counter);
                        characterBufferSynchronized.addCharacter(temp);
                        controller.printWriter("Writing " + temp + "\n");
                        counter++;

                        if (counter >= string.length()) {
                            isRunning = false;
                            controller.printWriterString(string);
                        }

                    } else {
                        controller.printWriter("Data Exists Writer waits" + "\n");
                    }
                } else {
                    if (!characterBuffer.hasCharacter()) {
                        char temp = string.charAt(counter);
                        characterBufferSynchronized.addCharacter(temp);
                        controller.printWriter("Writing " + temp + "\n");
                        counter++;

                        if (counter >= string.length()) {
                            isRunning = false;
                            controller.printWriterString(string);
                        }

                    }
                }

            }

            try {
                Thread.sleep((random.nextInt(15) + 5));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    public void setRunning(boolean running) {
        counter = 0;
        isRunning = running;
    }

    public void setString(String string) {
        this.string = string;
    }

    public void setSync(boolean sync) {
        this.sync = sync;
    }
}
