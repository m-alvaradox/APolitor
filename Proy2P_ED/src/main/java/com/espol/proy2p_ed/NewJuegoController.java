/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.espol.proy2p_ed;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class NewJuegoController implements Initializable {
    
    @FXML
    TextField titleGame;
    @FXML
    TextArea descripcionGame;
    @FXML
    ImageView imagen;
    @FXML
    Label titleQuestion;
    @FXML
    Label titleAnswer;
    @FXML
    HBox conteinerQuestion; 
    @FXML
    HBox conteinerAnswer; 
    
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
    private void regresar() throws IOException {
        App.setRoot("juegos");
    }
    
    @FXML
    private void AddQuestion() throws IOException {
        conteinerQuestion.setVisible(true);
    }
    
    @FXML
    private void AddAnswer() throws IOException{
        conteinerAnswer.setVisible(true);
    }
    
    @FXML
    private void CerrarQuestion() throws IOException{
        conteinerQuestion.setVisible(false);
    }
    
    @FXML
    private void CerrarAnswer() throws IOException{
        conteinerAnswer.setVisible(true);
    }
    
    @FXML
    private void addImage() throws IOException{
        
    }
    
    @FXML
    private void makeGame() throws IOException{
        
    }
}
