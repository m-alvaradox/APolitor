package com.espol.proy2p_ed;

import Objects.Juego;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    public static String filegames = "src\\main\\resources\\datos\\games.ser";
    public static ArrayList<Juego> juegos = loadGames();;


    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 640, 480);
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

    public static void main(String[] args) {
        launch();
    }

}