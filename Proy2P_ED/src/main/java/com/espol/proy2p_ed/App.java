package com.espol.proy2p_ed;

import Objects.Juego;
import TDAs.DecisionTree;
import TDAs.NodeDecisionTree;
import java.io.BufferedReader;
import java.io.FileReader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    public static String filegames = "src\\main\\resources\\datos\\games.ser";
    public static ArrayList<Juego> juegos = loadGames();;


    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("principal"), 640, 480);
        stage.setTitle("APolitor");
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    
    public static void serializarJuegos() {
        try {        
            Container.serialize(juegos, filegames);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public static ArrayList<Juego> loadGames() {

        ArrayList<Juego> games_list = new ArrayList<>();
        
        try {
            games_list = Container.deserialize(filegames);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e);
        }
        
        return games_list;
    }
    
    public static List<String> readFile(String filePath) throws IOException {
        List<String> lineas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                lineas.add(linea);
            }
        }
        return lineas;
    }

    public static void main(String[] args) {
        launch();
    }
    
    
    public static void buildDecisionTree(String questionsFile, String answersFile) throws IOException {
        
        DecisionTree<String> decisionTree = new DecisionTree();
        
        List<String> questions = readFile(questionsFile);
        List<String> answers = readFile(answersFile);
        
        decisionTree.setElements(questions);
        decisionTree.setRoot(new NodeDecisionTree(questions.get(0)));
        
        for(String answer : answers) {
            String[] sep_answer = answer.split(" ");
            String element = sep_answer[0];
            
            NodeDecisionTree<String> current = decisionTree.getRoot();
            
            
        }
        
    }

}