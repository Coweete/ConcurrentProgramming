package Assignment2;

public class Controller {

    private Gui gui;
    private ReaderTask readerTask;
    private WriterTask writerTask;
    private Thread readerThread;
    private Thread writerThread;
    private CharacterBuffer characterBuffer;

    public Controller() {

        characterBuffer = new CharacterBuffer();

        readerTask = new ReaderTask(this, characterBuffer);
        readerThread = new Thread(readerTask);
        readerThread.start();

        writerTask = new WriterTask(this, characterBuffer);
        writerThread = new Thread(writerTask);
        writerThread.start();

        gui = new Gui(this);
        gui.start();
    }

    public void startRun() {
        String temp = gui.getInputText();
        writerTask.setString(temp);
        writerTask.setRunning(true);

        readerTask.setEndLength(temp.length());
        readerTask.setRunning(true);
    }

    public void clear() {

    }

    public void printWriter(String text) {
        gui.printWriter(text);
    }

    public void printReader(String text) {
        gui.printReader(text);
    }

    public void printReaderString(String text) {
        gui.printReaderString(text);
    }

    public void printWriterString(String text){
        gui.printWriterString(text);
    }
}
