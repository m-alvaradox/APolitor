package com.espol.proy2p_ed;

import Objects.Juego;
import TDAs.DecisionTree;
import TDAs.NodeDecisionTree;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class App extends Application {

    private static Scene scene;
    public static String filegames = "src\\main\\resources\\datos\\games.ser";
    public static ArrayList<Juego> juegos = loadGames();
    public static MediaPlayer backgroundMusicPlayer;
    
    public static boolean isMuted = false;
    
    @Override
    public void start(Stage stage) throws IOException {
               
        File musicFile = new File("src\\main\\resources\\techmusic.mp3");
        String musicPath = musicFile.toURI().toString();

        Media musicMedia = new Media(musicPath);
        backgroundMusicPlayer = new MediaPlayer(musicMedia);
        backgroundMusicPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Reproducir en bucle
        backgroundMusicPlayer.setVolume(0.5); // Ajustar el volumen según sea necesario
        backgroundMusicPlayer.play();
        
        scene = new Scene(loadFXML("principal"), 640, 400);
        stage.setTitle("APolitor");
        Image icon = new Image("/imagenes/tortuga2.png");
        stage.getIcons().add(icon);
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
        
        /* //Juego en la consola
        try {
            DecisionTree arbol = buildDecisionTree("src/main/resources/datos/game-0/game-0-questions.txt", "src/main/resources/datos/game-0/game-0-answers.txt");
            arbol.recorrerArbol();
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
        launch();
    }
    
    
    public static DecisionTree<String> buildDecisionTree(String questionsFile, String answersFile) throws IOException {
        
        DecisionTree<String> decisionTree = new DecisionTree();
        
        List<String> questions = readFile(questionsFile);
        List<String> answers = readFile(answersFile);
        
        decisionTree.setElements(questions);
        decisionTree.setRoot(new NodeDecisionTree<String>(questions.get(0)));
        
        for(String answer : answers) {
            String[] sep_answer = answer.split(" ");
            String element = sep_answer[0];
            
            NodeDecisionTree<String> current = decisionTree.getRoot();
            
            for(int i = 1; i< sep_answer.length; i++) {
                if(sep_answer[i].toUpperCase().equals("SI") || sep_answer[i].toUpperCase().equals("SÍ") ) {
                    if(current.getYesBranch() == null) {
                        if(i == sep_answer.length -1) {
                            current.setYesBranch(new DecisionTree<String>(new NodeDecisionTree<String>(element)));
                        } else {
                            current.setYesBranch(new DecisionTree<String>(new NodeDecisionTree<String>(questions.get(i))));
                        }
                    }
                
                    current = current.getYesBranch().getRoot();
                
                } else {
                    if (current.getNoBranch() == null) {
                        if(i == sep_answer.length -1) {
                            current.setNoBranch(new DecisionTree<String>(new NodeDecisionTree<String>(element)));
                        } else {
                            current.setNoBranch(new DecisionTree<String>(new NodeDecisionTree<String>(questions.get(i))));
                        }
                    }
                    
                    current = current.getNoBranch().getRoot();
                }
                    
            }
                    
                
        }
        
        return decisionTree;
                    
    }
            
            
        
        
    

}