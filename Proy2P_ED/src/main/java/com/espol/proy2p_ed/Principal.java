package com.espol.proy2p_ed;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.fxml.Initializable;
import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.util.Duration;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

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
    
    private MediaPlayer backgroundMusicPlayer;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inicializa las imágenes
        image1 = new Image("/imagenes/tortuga.png");
        image2 = new Image("/imagenes/tortuga2.png");

        // Establece la imagen inicial
        vista.setImage(image1);

        // Configura la transición de imágenes
        startTransition();
        //
        Transition t1 = new Transition();
        t1.Transitionround(c1,c2,c3,c4,c5,c6);
        
        // Musica de fondo
        
        File musicFile = new File("src\\main\\resources\\techmusic.mp3");
        String musicPath = musicFile.toURI().toString();

        Media musicMedia = new Media(musicPath);
        backgroundMusicPlayer = new MediaPlayer(musicMedia);
        backgroundMusicPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Reproducir en bucle
        backgroundMusicPlayer.setVolume(0.5); // Ajustar el volumen según sea necesario
        backgroundMusicPlayer.play();
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
        timeline.setCycleCount(Timeline.INDEFINITE); // Repetir indefinidamente
        timeline.play(); // Iniciar la animación
    }
    
    
        
}
