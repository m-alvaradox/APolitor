/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.espol.proy2p_ed;

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

/**
 * FXML Controller class
 *
 * @author USER
 */
public class JuegosController implements Initializable {
    
    
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
    ImageView imagenLogo;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Transition t1 = new Transition();
        t1.Transitionround(c1,c2,c3,c4,c5,c6);
        t1.rotateLogo(imagenLogo);
    }    
    
    
    @FXML
    private void home() throws IOException {
        App.setRoot("principal");
    }
}
