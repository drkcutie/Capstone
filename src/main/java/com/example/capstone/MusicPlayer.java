package com.example.capstone;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.scene.control.ProgressBar;
import javafx.util.Duration;
import java.io.File;


import java.io.File;
import java.util.*;

public class MusicPlayer {

    public Label myLabel;
    @FXML
    private Label chooseMusic;
    private Media media;
    private MediaPlayer mediaPlayer;
    private Timer timer;
    private TimerTask task;
    private boolean running;


    @FXML
    private ProgressBar progressBar;

    @FXML
    void chooseMusic(MouseEvent event) {
        if(mediaPlayer != null)
        {
            mediaPlayer.stop();
        }
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Select your music");
        File file = chooser.showOpenDialog(null);
        if (file != null) {
            String selectedFile = file.toURI().toString();
            Media media = new Media(selectedFile);
            mediaPlayer = new MediaPlayer(media);  // Use the class-level variable
            mediaPlayer.setOnReady(()->{
                chooseMusic.setText(file.getName());
            });
        }
    }

    @FXML
    void pause(MouseEvent event) {
        if(mediaPlayer != null)
    mediaPlayer.pause();
    }

    @FXML
    void play(MouseEvent event) {
        if(mediaPlayer != null)
            mediaPlayer.play();
    }

    @FXML
    void stop(MouseEvent event) {
        if(mediaPlayer != null)
            mediaPlayer.stop();
    }

    public void playProgress(ActionEvent actionEvent) {
        if (mediaPlayer != null) {
            timer = new Timer();
            task = new TimerTask(){
                public void run(){
                    running = true;
                    double current = mediaPlayer.getCurrentTime().toSeconds();
                    double end = mediaPlayer.getTotalDuration().toSeconds();
                    progressBar.setProgress(current/end);
                    if(current/end == 1.0){
                        mediaPlayer.stop();
                        running = false;
                    }
                }

            };
            timer.scheduleAtFixedRate(task, 1000,1000);




        }
    }

}



