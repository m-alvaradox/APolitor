package com.espol.proy2p_ed;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.fxml.Initializable;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.util.Duration;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PrimaryController implements Initializable{
    
    @FXML
    ImageView vista; 
    private Image image1;
    private Image image2;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inicializa las imágenes
        image1 = new Image("/imagenes/tortuga.png");
        image2 = new Image("/imagenes/tortuga2.png");

        // Establece la imagen inicial
        vista.setImage(image1);

        // Configura la transición de imágenes
        startTransition();
    }
    
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
    
    private void startTransition(){

        FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), vista);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);

        FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), vista);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);

        // Crear un Timeline para alternar entre las imágenes cada 5 segundos
        Timeline timeline = new Timeline(
            new KeyFrame(Duration.seconds(0), event -> {
                vista.setImage(image1);
                fadeIn.play(); // Reproduce la transición de aparición
            }),
            new KeyFrame(Duration.seconds(5), event -> {
                fadeOut.play(); // Reproduce la transición de desaparición
                fadeOut.setOnFinished(e -> vista.setImage(image2)); // Cambia la imagen después de desaparecer
            }),
            new KeyFrame(Duration.seconds(6), event -> {
                fadeIn.play(); // Reproduce la transición de aparición para la nueva imagen
            }),
            new KeyFrame(Duration.seconds(11), event -> {
                fadeOut.play(); // Reproduce la transición de desaparición para la nueva imagen
                fadeOut.setOnFinished(e -> vista.setImage(image1)); // Cambia la imagen después de desaparecer
            })
        );

        timeline.setCycleCount(Timeline.INDEFINITE); // Repetir indefinidamente
        timeline.play(); // Iniciar la animación
    }
        
}
