package Assignment2;


public class ReaderTask implements Runnable {

    private boolean isRunning;
    private Controller controller;
    private CharacterBuffer characterBuffer;
    private char[] chars;
    private int endLength, counter;

    public ReaderTask(Controller controller, CharacterBuffer characterBuffer) {
        this.controller = controller;
        this.characterBuffer = characterBuffer;
        isRunning = false;
    }

    @Override
    public void run() {
        while (true) {
            if (isRunning) {
                if (characterBuffer.hasCharacter()) {
                    char temp = characterBuffer.removeCharacter();
                    controller.printReader("Reading " + temp + "\n");
                    chars[counter] = temp;
                    counter++;

                    if (counter >= endLength){
                        isRunning = false;
                        StringBuilder stringBuilder = new StringBuilder();
                        for (int i = 0; i < chars.length; i++) {
                            stringBuilder.append(chars[i]);
                        }
                        controller.printReaderString(stringBuilder.toString());
                        //Should print out it here ?
                        //And notify so that the compare can be done
                    }


                } else {
                    controller.printReader("No data, Reader waits" + "\n");
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

    public void setEndLength(int length) {
        chars = new char[length];
        endLength = length;
    }
}
