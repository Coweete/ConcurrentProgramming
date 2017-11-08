package Assigment1;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MusicPlayer {

    private MediaPlayer mediaPlayer;

    public MusicPlayer(Media media){
        mediaPlayer = new MediaPlayer(media);
    }

    public void startMusic(){
        mediaPlayer.play();
    }

    public void endMusic(){
        mediaPlayer.stop();
    }
}
