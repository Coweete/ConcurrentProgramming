package Assignment2;

public class Controller {

    private Gui gui;
    private ReaderTask readerTask;
    private WriterTask writerTask;
    private Thread readerThread;
    private Thread writerThread;
    private CharacterBufferSynchronized characterBufferSynchronized;
    private CharacterBuffer characterBuffer;

    public Controller() {

        characterBufferSynchronized = new CharacterBufferSynchronized();
        characterBuffer = new CharacterBuffer();

        readerTask = new ReaderTask(this, characterBufferSynchronized, characterBuffer);
        readerThread = new Thread(readerTask);
        readerThread.start();

        writerTask = new WriterTask(this, characterBufferSynchronized, characterBuffer);
        writerThread = new Thread(writerTask);
        writerThread.start();

        gui = new Gui(this);
        gui.start();
    }

    public void startRun(boolean sync) {
        if (sync) {
            writerTask.setSync(true);
            readerTask.setSync(true);
        } else {
            writerTask.setSync(false);
            readerTask.setSync(false);
        }
        String temp = gui.getInputText();
        writerTask.setString(temp);
        writerTask.setRunning(true);

        readerTask.setEndLength(temp.length());
        readerTask.setRunning(true);

        gui.setBtnRunEnabled(false);
        gui.setBtnClearEnabled(true);
    }

    public void clear() {
        gui.clearText();
        gui.setBtnClearEnabled(false);
        gui.setBtnRunEnabled(true);
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

    public void printWriterString(String text) {
        gui.printWriterString(text);
    }
}
