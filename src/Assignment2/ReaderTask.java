package Assignment2;


import java.util.Random;

public class ReaderTask implements Runnable {

    private boolean isRunning;
    private Controller controller;
    private CharacterBufferSynchronized characterBufferSynchronized;
    private CharacterBuffer characterBuffer;
    private boolean sync;
    private char[] chars;
    private Random random;
    private int endLength, counter;

    public ReaderTask(Controller controller, CharacterBufferSynchronized characterBufferSynchronized, CharacterBuffer characterBuffer) {
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
                System.out.println("Running");
                if (sync) {
                    if (characterBufferSynchronized.hasCharacter()) {
                        char temp = characterBufferSynchronized.removeCharacter();
                        controller.printReader("Reading " + temp + "\n");
                        chars[counter] = temp;
                        counter++;

                        if (counter >= endLength) {
                            isRunning = false;
                            StringBuilder stringBuilder = new StringBuilder();
                            for (int i = 0; i < chars.length; i++) {
                                stringBuilder.append(chars[i]);

                            }
                            controller.printReaderString(stringBuilder.toString());
                        }


                    } else {
                        controller.printReader("No data, Reader waits" + "\n");
                    }
                } else {
                    if (characterBuffer.hasCharacter()) {
                        char temp = characterBufferSynchronized.removeCharacter();
                        controller.printReader("Reading " + temp + "\n");
                        chars[counter] = temp;
                        counter++;

                        if (counter >= endLength) {
                            isRunning = false;
                            StringBuilder stringBuilder = new StringBuilder();
                            for (int i = 0; i < chars.length; i++) {
                                stringBuilder.append(chars[i]);

                            }
                            controller.printReaderString(stringBuilder.toString());

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
    }

    public void setRunning(boolean running) {
        counter = 0;
        isRunning = running;
    }

    public void setEndLength(int length) {
        chars = new char[length];
        endLength = length;
    }

    public void setSync(boolean sync) {
        this.sync = sync;
    }
}
