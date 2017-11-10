package Assigment1;

import javafx.scene.media.Media;

import java.awt.*;
import java.io.File;

public class Controller {

    private GUIAssignment1 gui;
    private MusicPlayer musicPlayer;
    private File file;
    private RotatingTriangleTask rotatingTriangleTask;
    private RandomTextTask randomTextTask;
    private Thread randomTextThread, rotatingThread;

    public Controller() {
        randomTextTask = new RandomTextTask("RandomTextTask", this);
        randomTextThread = new Thread(randomTextTask);
        randomTextThread.start();

        rotatingTriangleTask = new RotatingTriangleTask(this);
        rotatingThread = new Thread(rotatingTriangleTask);
        rotatingThread.start();

        gui = new GUIAssignment1(this);
        gui.start();

        calculateBorder();
    }


    public void openFileChooser() {
        file = gui.openFIleChooser();
    }

    public void startMusic() {
        if (file != null) {
            musicPlayer = new MusicPlayer(new Media(file.toURI().toString()));
            musicPlayer.startMusic();
        }
    }

    public void stoppMusic() {
        if (musicPlayer != null) {
            musicPlayer.endMusic();
        }
    }

    public void startDisplay() {
        randomTextTask.setRunning(true);
    }

    public void stopDisplay() {
        randomTextTask.setRunning(false);
    }

    public void updateRandomPos(String text, int x, int y) {
        calculateBorder();
        gui.placeRandomText(text, x, y);
    }

    public void startRotate() {
        rotatingTriangleTask.setRunning(true);
    }

    public void stopRotate() {
        rotatingTriangleTask.setRunning(false);
    }

    public void rotateTriangle(double angle) {
        gui.rotateTriangle(angle);
    }

    private void calculateBorder(){
        Dimension lblDimension = gui.getRandomTextDimension();
        Dimension pnlDimension = gui.getDisplayDimension();
        randomTextTask.setDimensionX(pnlDimension.width-lblDimension.width);
        randomTextTask.setDimensionY(pnlDimension.height-lblDimension.height);
    }
}
