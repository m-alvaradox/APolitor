/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.espol.proy2p_ed;

import Objects.Juego;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
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
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
    
    @FXML
    VBox allGames;
    @FXML
    AnchorPane containerDetalles;
    @FXML
    Label titleGame;
    @FXML
    ImageView imagen;
    @FXML
    Label descripcion;
    
    ArrayList<Juego> juegos;
    public static Juego JuegoEnUso;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Transition t1 = new Transition();
        t1.Transitionround(c1,c2,c3,c4,c5,c6);
        t1.rotateLogo(imagenLogo);
        juegos = App.juegos;
        mostrarGames();
    }    
    
    
    @FXML
    private void home() throws IOException {
        App.setRoot("principal");
    }
    
    @FXML
    private void makeGame() throws IOException{
        App.setRoot("newJuego");
    }
    
    
    private void mostrarGames(){
        int numJuegos = juegos.size();
        int contador=0;
        while(contador!=numJuegos){
           HBox hb = new HBox();
           hb.setPrefWidth(510);
           hb.setPrefHeight(155);
           hb.getStyleClass().add("recuadro_allGames");
           hb.setPadding(new javafx.geometry.Insets(10,10,10,10));
           hb.setSpacing(25);
           for(int i=1;i<=3;i++){
                if(contador!=numJuegos){
                   VBox vb = new VBox();
                   Juego j = juegos.get(contador);
                   TextField title= new TextField(j.getTitulo());
                   title.getStyleClass().add("texto_juego");
                   title.setPrefWidth(105);
                   Image image;
                    if (j.getPortada().contains("defaultgame.jpg")) {
                        image = new Image("/imagenes/defaultgame.jpg");
                    } else {
                        // Usa la ruta directamente para cargar la imagen en lugar de convertirla en Path.
                        image = new Image(Paths.get(j.getPortada()).toUri().toString());
                    }
                    ImageView imagen = new ImageView(image);
                    imagen.setFitWidth(113);
                    imagen.setFitHeight(99);
                   vb.getChildren().addAll(title, imagen);
                   vb.getStyleClass().add("recuadro_juego");
                   vb.setAlignment(Pos.TOP_CENTER);
                   vb.setSpacing(5);
                   vb.setPadding(new javafx.geometry.Insets(5,5,5,5));
                   vb.setPrefWidth(150);
                   vb.setPrefHeight(121);
                   vb.setOnMouseClicked(event -> {
                       ventanaAdicional(j);
                   });        
                   hb.getChildren().add(vb);
                   contador++;
                }
            } 
            allGames.getChildren().add(hb); 
        }
    }
    
    private void ventanaAdicional(Juego j){
        titleGame.setText(j.getTitulo());
        descripcion.setText(j.getDescripcion());
        Image image;
        if (j.getPortada().contains("defaultgame.jpg")) {
              image = new Image("/imagenes/defaultgame.jpg");
         } else {
               // Usa la ruta directamente para cargar la imagen en lugar de convertirla en Path.
               image = new Image(Paths.get(j.getPortada()).toUri().toString());
          }
        imagen.setImage(image);
        JuegoEnUso = j;
        containerDetalles.setVisible(true);
    }
    
    @FXML
    private void goGame() throws IOException{
        App.setRoot("goJuego");
    }
    
    @FXML
    private void cerrarDetalles() throws IOException{
        containerDetalles.setVisible(false);
    }
}
