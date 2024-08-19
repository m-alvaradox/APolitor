package com.espol.proy2p_ed;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Tooltip;
import javafx.util.Duration;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

public class Principal implements Initializable{
    
    @FXML
    ImageView vista; 
    private Image image1;
    private Image image2;
    @FXML
    Circle c1;
    @FXML
    Circle c2;
    @FXML
    Circle c3;
    @FXML
    Circle c4;
    @FXML
    Circle c5;
    @FXML
    Circle c6;
    @FXML
    ImageView bttnmute;
    @FXML
    ImageView bttnunmute;
    
    //private MediaPlayer backgroundMusicPlayer;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        image1 = new Image("/imagenes/tortuga_preguntando.png");
        image2 = new Image("/imagenes/tortuga_satisfecha.png");
        
        Tooltip.install(bttnmute, new Tooltip("Silenciar"));
        Tooltip.install(bttnunmute, new Tooltip("Activar audio"));
        
        vista.setImage(image1);

        startTransition();

        Transition t1 = new Transition();
        t1.Transitionround(c1,c2,c3,c4,c5,c6);
        
        if(App.isMuted) {
            bttnmute.setVisible(false);
            bttnunmute.setVisible(true);
        } else {
            bttnmute.setVisible(true);
            bttnunmute.setVisible(false);
        }
        
}
    @FXML
    private void audio() {
        
        if(App.isMuted) {
            App.backgroundMusicPlayer.setMute(false);
            App.isMuted = false;
            bttnmute.setVisible(true);
            bttnunmute.setVisible(false);
        } else {
            App.isMuted = true;
            App.backgroundMusicPlayer.setMute(true);
            bttnmute.setVisible(false);
            bttnunmute.setVisible(true);
        }
       
    }
    
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("juegos");
    }
    
    private void startTransition(){

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
            if (vista.getImage().equals(image1)) {
                vista.setImage(image2);
            } else {
                vista.setImage(image1);
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    
    @FXML
    private void makeJuego() throws IOException{
        App.setRoot("newJuego");
    }
        
}
