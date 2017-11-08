package Assigment1;

import javafx.scene.media.Media;

import java.io.File;

public class Controller {

    private GUIAssignment1  gui;
    private MusicPlayer     musicPlayer;
    private File            file;


    public Controller(){
        gui = new GUIAssignment1(this);
        gui.start();
    }


    public void openFileChooser(){
        file = gui.openFIleChooser();
    }

    public void startMusic(){
        if (file != null){
            musicPlayer = new MusicPlayer(new Media(file.toURI().toString()));
            musicPlayer.startMusic();
        }
    }

    public void stoppMusic(){
        if (musicPlayer != null){
            musicPlayer.endMusic();
        }
    }
}
