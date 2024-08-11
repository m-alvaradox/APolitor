package com.espol.proy2p_ed;

import Objects.Juego;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.fxml.Initializable;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;

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
    private void creategame() throws IOException {
        
        final File[] questions = new File[1];
        final File[] answers = new File[1];
        final File[] imgFile = new File[1];
               
        Dialog dialog = new Dialog();
        dialog.setTitle("Crear Juego");
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        
        // Ajustar el tamaño del diálogo
        dialog.setResizable(true); // Hacer el diálogo redimensionable
        dialog.getDialogPane().setPrefSize(600, 600); // Ajustar tamaño preferido
          
        GridPane gridPane = new GridPane();
        gridPane.add(new Label("Titulo"),0,0);
        gridPane.add(new Label("Descripcion"),0,1);
        gridPane.add(new Label("Archivo Preguntas"), 0, 2);
        gridPane.add(new Label("Archivo Respuestas"), 0, 3);
        gridPane.add(new Label("Suba una Portada"), 0, 4);
        
        TextField titulo = new TextField();
        TextField descripcion = new TextField();
        TextField namefilequestion = new TextField();
        namefilequestion.setDisable(true);
        TextField namefileanswers = new TextField();
        namefileanswers.setDisable(true);
        TextField nameportada = new TextField();
        nameportada.setDisable(true);
        
        Button cr = new Button("+");
        Button cr2 = new Button("+");
        Button cr3 = new Button("Crear");
        Button cr4 = new Button("+");
        
        ImageView prev = new ImageView();
        
        cr4.setOnMouseClicked(evento3 -> {
            try {
                imgFile[0] = buscarFoto();
            } catch (IOException ex) {
                System.out.println("Busqueda cancelada");
            }
            
            if(imgFile != null) {
                // Mostramos la imagen
                nameportada.setText(imgFile[0].getAbsolutePath());
                
                Image image = new Image("file:" + imgFile[0].getAbsolutePath());
                
                prev.setImage(image);
                prev.setFitWidth(300);
                prev.setFitHeight(250);
       
            }   
        });
              
        cr.setOnMouseClicked(evento1 -> {
            try {
                questions[0] = buscarArchivo();
                
                if(questions != null) {
                    namefilequestion.setText(questions[0].getAbsolutePath());
                }
            } catch (IOException ex) {
                System.out.println("Busqueda cancelada");
            }
        });
        
        cr2.setOnMouseClicked(evento2 -> {
            try {
                answers[0] = buscarArchivo();
                
                if(answers != null) {
                    namefileanswers.setText(answers[0].getAbsolutePath());
                }
            } catch (IOException ex) {
                System.out.println("Busqueda cancelada");
            }
        }); 
        
        cr3.setOnMouseClicked(evento3 -> {
                        
            File questionscopy = questions[0];
            File answerscopy = answers[0];
            File imagenportada = imgFile[0];
            
            String rutaImagen;
            
            if(titulo.getText() != null && !titulo.getText().trim().isEmpty()) {
            
                if(validarArchivos(questionscopy, answerscopy) == true) {
                   
                    int numjuegos = 0;
                    
                    if(App.juegos != null) {
                        numjuegos = App.juegos.size();
                    }
         
                    String foldergame = "game-"+numjuegos;
            
                    Path projectDir = Paths.get("").toAbsolutePath();
                    
                    String rutaCarpeta = "src/main/resources/datos/"+foldergame;
                    File carpeta = new File(rutaCarpeta);
                    
                    if (carpeta.mkdirs()) {
                        System.out.println("Carpeta creada exitosamente");
                    } else {
                        System.out.println("No se pudo crear la carpeta");
                    }
            
                    String ruta1 = rutaCarpeta+"/"+foldergame+"-questions.txt";
                    String ruta2 = rutaCarpeta+"/"+foldergame+"-answers.txt";
                   
            
                    Path rutaDestino =  projectDir.resolve(Paths.get( ruta1));
                    Path rutaDestino2 =  projectDir.resolve(Paths.get( ruta2));
            
                    try {
                        Files.copy(questionscopy.toPath(), rutaDestino, StandardCopyOption.REPLACE_EXISTING);
                        Files.copy(answerscopy.toPath(), rutaDestino2, StandardCopyOption.REPLACE_EXISTING);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    
                    if(imagenportada == null) {
                        rutaImagen = "src/main/resources/imagenes/defaultgame.jpg";
                    } else {
                        String extension = imagenportada.getName().substring(imagenportada.getName().lastIndexOf('.') + 1);
                        rutaImagen = rutaCarpeta+"/portada-"+foldergame+"."+extension;
                        Path rutaDestino3 =  projectDir.resolve(Paths.get( rutaImagen));
                        try {
                            Files.copy(imagenportada.toPath(), rutaDestino3, StandardCopyOption.REPLACE_EXISTING);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                    String descripcion1 = "No hay informacion";
                            
                    if(descripcion.getText() != null) {
                        descripcion1 = descripcion.getText();
                    }
                    
                    App.juegos.add(new Juego(titulo.getText(), descripcion1, ruta1, ruta2, rutaImagen));
                    App.serializarJuegos();
            
                    dialog.close();
                    mostraralertaconfirmacion("Juego creado exitosamente");
                }
            }
            else {
                mostraralertaerror("Ponga un titulo");
            }  
        });
        
        gridPane.add(titulo, 1, 0);
        gridPane.add(descripcion, 1,1);
        gridPane.add(namefilequestion,1,2);
        gridPane.add(cr,2,2);
        gridPane.add(namefileanswers,1,3);
        gridPane.add(nameportada, 1, 4);
        gridPane.add(cr2,2,3);
        gridPane.add(cr3, 0, 5);
        gridPane.add(cr4, 2, 4);
        gridPane.add(prev, 2, 5);
        
        dialog.getDialogPane().setContent(gridPane);
        dialog.show(); 
    }
    
    
    public File buscarArchivo() throws IOException {
        FileChooser fc = new FileChooser();
        fc.setTitle("Buscar archivo");
        
        // Agregar filtros para facilitar la busqueda
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("txt", "*.txt")
        );
        
        File file = fc.showOpenDialog(null);
        return file;    
    }
    
    public File buscarFoto() throws IOException {
        FileChooser fc = new FileChooser();
        fc.setTitle("Buscar Foto");
        
        // Agregar filtros para facilitar la busqueda
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("jpg", "*.jpg"),
                new FileChooser.ExtensionFilter("jpeg", "*.jpeg"),
                new FileChooser.ExtensionFilter("png", "*.png")
        );
        
        File file = fc.showOpenDialog(null);
        return file;    
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
    
    
    public void mostraralertaconfirmacion(String msg) {
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setTitle("Confirmation Dialog");
             alert.setHeaderText("Resultado de la operacion");
             alert.setContentText(msg);
             alert.showAndWait();      
    }
    
    public void mostraralertaerror(String msg) {
        
        Alert alert= new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Operación Cancelada");
        alert.setTitle("Error");
        String msgerr = String.format(msg);
        alert.setContentText(msgerr);
        //String css = this.getClass().getResource("/styles/estilos.css").toExternalForm();
        //alert.getDialogPane().getStylesheets().add(css);
        alert.getDialogPane().getStyleClass().add("dialog-paneError");
        alert.showAndWait();      
    }

    private boolean validarArchivos(File questionscopy, File answerscopy) {
        
        // Subio los dos archivos
        if(questionscopy == null || answerscopy == null) {
            mostraralertaerror("Intente subir los dos archivos");
            return false;
        }
        
        // Archivo vacio
     
        if((int) questionscopy.length() == 0 || (int) answerscopy.length() == 0) {
            mostraralertaerror("Archivo vacio");
            return false;
        } 
        
        // Archivo preguntas
        
        try (BufferedReader br = new BufferedReader(new FileReader(questionscopy))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                
                if(!linea.contains("¿") || !linea.contains("?")) {
                    mostraralertaerror("Archivo preguntas no cumple con los requisitos");
                    return false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
        
        // Archivo respuestas
        
        try (BufferedReader br = new BufferedReader(new FileReader(answerscopy))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // Divide la línea según el delimitador
                String[] partes = linea.split(" ");
                
                if(partes[0].toUpperCase().equals("SI") || partes[0].toUpperCase().equals("SÍ") || partes[0].toUpperCase().equals("NO")) {
                    mostraralertaerror("Archivo respuestas no cumple con los requisitos");
                    return false;
                }

                // Procesa las partes según sea necesario
                for (int i = 1; i<partes.length; i++) {
                    
                    if(!partes[i].toUpperCase().equals("SI") && !partes[i].toUpperCase().equals("SÍ") && !partes[i].toUpperCase().equals("NO")) {
                        mostraralertaerror("Archivo respuestas no cumple con los requisitos");
                        return false;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    
       return true; 
    }
    
        
}
