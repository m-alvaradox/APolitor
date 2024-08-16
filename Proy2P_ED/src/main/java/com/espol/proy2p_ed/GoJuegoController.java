/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.espol.proy2p_ed;

import Objects.Juego;
import TDAs.DecisionTree;
import TDAs.NodeDecisionTree;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;  
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class GoJuegoController implements Initializable {
    
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
    ImageView tortuga;
    @FXML
    VBox conteinerButton;
    Juego JuegoAUsar;
    DecisionTree<String> ArbolJuego;
    int levelQuestions;
    int numQuestionsUsar;
    int contador=0; // contador que sirve para no acceder el limite de preguntas que eligió el jugador
    @FXML
    AnchorPane containerDetalles;
    @FXML
    TextField numQuestions;
    @FXML
    Label Question;
    @FXML
    ToggleGroup botonDecision;
    @FXML
    Button botonSi;
    @FXML
    Button botonNo;
    @FXML
    Button botonCorregir;
    @FXML
    Button botonReiniciar;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Transition t1 = new Transition();
        t1.Transitionround(c1,c2,c3,c4,c5,c6);
        t1.rotateLogo(imagenLogo);
        JuegoAUsar= JuegosController.JuegoEnUso;
        try {
            ArbolJuego = App.buildDecisionTree(JuegoAUsar.getPreguntas(), JuegoAUsar.getAnswers());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        levelQuestions = ArbolJuego.getElements().size();
        
    }    
    
    private void actualizarVentana(){
        contador++;
        if(contador==1){
            botonCorregir.setVisible(false);
        }
        if(contador<=numQuestionsUsar){
            Question.setText(contador+". "+ArbolJuego.getRoot().getContent());               
        }else{
            mostrarPosiblesResultados();
        }
       
    }
    
    private void mostrarResultado(){
        botonReiniciar.setVisible(true);
        botonNo.setVisible(false);
        botonSi.setVisible(false);
        botonCorregir.setVisible(false);
        tortuga.setImage(new Image("/imagenes/tortuga_satisfecha.png"));
        Question.setText("Lo que pensó fue un "+ ArbolJuego.getRoot().getContent());
    }
    
    private void mostrarNoEncontrado(){
        botonReiniciar.setVisible(true);
        botonNo.setVisible(false);
        botonSi.setVisible(false);
        botonCorregir.setVisible(false);
        tortuga.setImage(new Image("/imagenes/tortugaTriste.png"));
        Question.setText("Que pena, no pude adivinar :( ");
    }
    
    private void mostrarPosiblesResultados(){
        botonReiniciar.setVisible(true);
        conteinerButton.getChildren().removeAll(botonNo, botonSi, botonCorregir);
        Question.setText("Con sus números de preguntas se obtuvo estas posibles respuestas");
        ArrayList<DecisionTree> hojas = ArbolJuego.obtenerLeaves();
        for(DecisionTree hoja:hojas){
            Label lb = new Label();
            lb.setText(hoja.getRoot().getContent().toString());
            lb.getStyleClass().add("text-atributos");
            conteinerButton.getChildren().add(lb);
        }
        
    }
    
    @FXML
    private void Reiniciar() throws IOException{
        App.setRoot("goJuego");
    }
    
    @FXML
    private void botonSi() throws IOException {
        if(contador==1){
            botonCorregir.setVisible(true);
        }
        if (ArbolJuego.getRoot().getYesBranch()!=null) {
            ArbolJuego = ArbolJuego.getRoot().getYesBranch();
            if (ArbolJuego.isLeaf()) {
                mostrarResultado();
            } else {
                actualizarVentana();
            }
        } else {
            mostrarNoEncontrado();
        }  
    }

    @FXML
    private void botonNo() throws IOException {
        if(contador==1){
            botonCorregir.setVisible(true);
        }
        if (ArbolJuego.getRoot().getNoBranch()!=null) {
            ArbolJuego = ArbolJuego.getRoot().getNoBranch();
            if (ArbolJuego.isLeaf()) {
                mostrarResultado();
            } else {
                actualizarVentana();
            }
        } else {
            mostrarNoEncontrado();
        } 
    }
    
    @FXML
    private void questionAnterior(){
        DecisionTree<String> ArbolOriginal = null;
        try {
            ArbolOriginal = App.buildDecisionTree(JuegoAUsar.getPreguntas(), JuegoAUsar.getAnswers());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        DecisionTree<String> padre = ArbolOriginal.findParent(ArbolJuego.getRoot());
        ArbolJuego = padre;
        contador-=2;
        actualizarVentana();
    }
    
    @FXML
    private void regresar() throws IOException {
        App.setRoot("juegos");
    }
    
    @FXML
    private void OmitirQuestion() throws IOException{
        containerDetalles.setVisible(false);
        numQuestionsUsar = levelQuestions;
        actualizarVentana();    
    }
    
    @FXML
    private void EmpezarJuego() throws IOException{
        if(numQuestions.getText()!=null && !numQuestions.getText().isEmpty()){
           int levels = Integer.parseInt(numQuestions.getText());
            if(levels > levelQuestions){
                mostraralertaerror("Este numero excede el numero de preguntas");
            } else {
                numQuestionsUsar = levels;
                containerDetalles.setVisible(false);
                actualizarVentana();
            } 
        }else{
            mostraralertaerror("Digite un numero");
        }
            
    }
    
    public void mostraralertaerror(String msg) {
        
        Alert alert= new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Operación Cancelada");
        alert.setTitle("Error");
        String msgerr = String.format(msg);
        alert.setContentText(msgerr);
        String css = this.getClass().getResource("/styles/estilos.css").toExternalForm();
        alert.getDialogPane().getStylesheets().add(css);
        alert.getDialogPane().getStyleClass().add("dialog-paneError");
        alert.showAndWait();      
    }
}
