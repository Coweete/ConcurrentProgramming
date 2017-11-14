package Assignment2;

public class WriterTask implements Runnable {

    private boolean isRunning;
    private String string;
    private int counter;
    private Controller controller;
    private CharacterBuffer characterBuffer;

    public WriterTask(Controller controller, CharacterBuffer characterBuffer) {
        this.controller = controller;
        this.characterBuffer = characterBuffer;
        isRunning = false;
    }

    @Override
    public void run() {
        while (true) {
            if (isRunning) {
                if (!characterBuffer.hasCharacter()) {
                    char temp = string.charAt(counter);
                    characterBuffer.addCharacter(temp);
                    controller.printWriter("Writing " + temp + "\n");
                    counter++;

                    if (counter >= string.length()) {
                        isRunning = false;
                        controller.printWriterString(string);
                    }

                } else {
                    controller.printWriter("Data Exists Writer waits" + "\n");
                }
            }

            try {
                Thread.sleep(1000);
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
}
