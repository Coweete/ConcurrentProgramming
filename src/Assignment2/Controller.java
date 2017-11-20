package Assignment2;

import java.awt.*;

/**
 * A controller class for the application,
 * Assignment 2
 *
 * @author Jonatan Fridsten
 */
public class Controller {

    private Gui gui;
    private ReaderTask readerTask;
    private WriterTask writerTask;
    private Thread readerThread;
    private Thread writerThread;
    private CharacterBufferSynchronized characterBufferSynchronized;
    private CharacterBuffer characterBuffer;

    /**
     * A constructor
     */
    public Controller() {

        //Creates new instances for the following objects
        characterBufferSynchronized = new CharacterBufferSynchronized();
        characterBuffer = new CharacterBuffer();
        readerTask = new ReaderTask(this, characterBufferSynchronized, characterBuffer);
        writerTask = new WriterTask(this, characterBufferSynchronized, characterBuffer);
        gui = new Gui(this);

        //Display the gui.
        gui.start();
    }

    /**
     * Starts the write/read process
     */
    public void startRun() {
        gui.setBtnRunEnabled(false);
        gui.setBtnClearEnabled(true);

        //The input from gui
        String temp = gui.getInputText();

        //Creates the writer thread
        writerTask.setString(temp);
        writerTask.setRunning(true);
        writerThread = new Thread(writerTask);
        writerThread.start();

        //Creates the reader thread
        readerTask.setEndLength(temp.length());
        readerTask.setRunning(true);
        readerThread = new Thread(readerTask);
        readerThread.start();
    }

    /**
     * Clears the GUI
     */
    public void clear() {
        readerTask.setRunning(false);
        writerTask.setRunning(false);
        if (characterBufferSynchronized.hasCharacter()) {
            characterBufferSynchronized.removeCharacter();
        }
        gui.clearText();
        gui.setResult(Color.WHITE, "Status goes here");
        gui.setBtnClearEnabled(false);
        gui.setBtnRunEnabled(true);
    }

    /**
     * Prints on the text area for the writer
     *
     * @param text To be displayed.
     */
    public void printWriter(String text) {
        gui.printWriter(text);
    }

    /**
     * Prints on the text area for the reader
     *
     * @param text To be displayed.
     */
    public void printReader(String text) {
        gui.printReader(text);
    }

    /**
     * Prints the final string
     *
     * @param text To be displayed.
     */
    public void printReaderString(String text) {
        gui.printReaderString(text);
    }

    /**
     * Prints the final string
     *
     * @param text To be displayed.
     */
    public void printWriterString(String text) {
        gui.printWriterString(text);
    }

    /**
     * Compares the to strings and prints
     * the result on the gui
     *
     * @param reader The string from the reader
     */
    public void compareStrings(String reader) {
        String writer = writerTask.getString(); //  String from writer

        if (writer.equals(reader)) {
            gui.setResult(Color.GREEN, "Success");  //if true
        } else {
            gui.setResult(Color.RED, "FAILED");     //if false
        }
    }
}
