package com.espol.proy2p_ed;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import Objects.*;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;

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
    Label titleImagenes;
    @FXML
    HBox conteinerQuestion; 
    @FXML
    HBox conteinerAnswer; 
    @FXML
    HBox conteinerImagenes;
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
    ImageView bttnmute;
    @FXML
    ImageView bttnunmute;
    @FXML
    ImageView bttnregresar;
    
    final File[] questions = new File[1];
    final File[] answers = new File[1];
    final File[] imgFile = new File[1];
    final File[] imagenesFile = new File[1];
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Transition t1 = new Transition();
        t1.Transitionround(c1,c2,c3,c4,c5,c6);
        t1.rotateLogo(imagenLogo);
        
        Tooltip.install(bttnmute, new Tooltip("Silenciar"));
        Tooltip.install(bttnunmute, new Tooltip("Activar audio"));
        Tooltip.install(bttnregresar, new Tooltip("Regresar"));
        
                if(App.isMuted) {
            bttnmute.setVisible(false);
            bttnunmute.setVisible(true);
        } else {
            bttnmute.setVisible(true);
            bttnunmute.setVisible(false);
        }
    }    
    
    
    @FXML
    private void regresar() throws IOException {
        App.setRoot("juegos");
    }
    
    @FXML
    private void audio() {
        
        if(App.isMuted) {
            App.backgroundMusicPlayer.setMute(false);
            App.isMuted = false;
            bttnmute.setVisible(true);
            bttnunmute.setVisible(false);
        } else {
            App.isMuted = true;
            App.backgroundMusicPlayer.setMute(true);
            bttnmute.setVisible(false);
            bttnunmute.setVisible(true);
        }
       
    }
    
    @FXML
    private void AddQuestion() throws IOException {
        try {
            questions[0] = buscarArchivo();
                
            if(questions != null) {
                //namefilequestion.setText(questions[0].getAbsolutePath());
                titleQuestion.setText(questions[0].getName());
                conteinerQuestion.setVisible(true);
            }
        } catch (IOException ex) {
                System.out.println("Busqueda cancelada");
        }
        
    }
    
    @FXML
    private void AddAnswer() throws IOException{
        try {
             answers[0] = buscarArchivo();
                
        if(answers != null) {
             // namefileanswers.setText(answers[0].getAbsolutePath());
             titleAnswer.setText(answers[0].getName());
             conteinerAnswer.setVisible(true);
            }
        } catch (IOException ex) {
                System.out.println("Busqueda cancelada");
         }
        
    }
    
    @FXML
    private void AddImagenes() throws IOException{
        try {
             imagenesFile[0] = buscarArchivo();
                
        if(answers != null) {
             // namefileanswers.setText(answers[0].getAbsolutePath());
             titleImagenes.setText(imagenesFile[0].getName());
             conteinerImagenes.setVisible(true);
            }
        } catch (IOException ex) {
                System.out.println("Busqueda cancelada");
         }
    }
    
    
    @FXML
    private void CerrarQuestion() throws IOException{
        conteinerQuestion.setVisible(false);
       questions[0] = null;
    }
    
    @FXML
    private void CerrarAnswer() throws IOException{
        conteinerAnswer.setVisible(false);
        answers[0] = null;
    }
    
    @FXML
    private void CerrarImagenes() throws IOException{
        conteinerImagenes.setVisible(false);
        imagenesFile[0] = null;
    }
    
    @FXML
    private void addImage() throws IOException{
        try {
            imgFile[0] = buscarFoto();
        } catch (IOException ex) {
             System.out.println("Busqueda cancelada");
        }
            
         if(imgFile != null) {
             // Mostramos la imagen               
             Image image = new Image("file:" + imgFile[0].getAbsolutePath());               
             imagen.setImage(image);
         }   
    }
    
    @FXML
    private void makeGame() throws IOException{
        
        File questionscopy = questions[0];
        File answerscopy = answers[0];
        File imagenportada = imgFile[0];  
        File imagenesAnswer = imagenesFile[0];
        String rutaImagen;           
        if(titleGame.getText() != null && !titleGame.getText().trim().isEmpty()) {
            if(validarArchivos(questionscopy, answerscopy, imagenesAnswer) == true) {   
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
                String ruta3 = rutaCarpeta+"/"+foldergame+"-imagenes.txt";
                Path rutaDestino =  projectDir.resolve(Paths.get( ruta1));
                Path rutaDestino2 =  projectDir.resolve(Paths.get( ruta2));
                Path rutaDestino4 = projectDir.resolve(Paths.get(ruta3));
                
                try {
                    Files.copy(questionscopy.toPath(), rutaDestino, StandardCopyOption.REPLACE_EXISTING);
                    Files.copy(answerscopy.toPath(), rutaDestino2, StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException ex) {
                   ex.printStackTrace();
                } 
                
                copyImagesFromFile(imagenesAnswer, rutaCarpeta, rutaDestino4.toFile());
                
                if(imagenportada == null) {
                    rutaImagen = App.class.getResource("/imagenes/defaultgame.jpg").toExternalForm();
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
                if(descripcionGame.getText() != null) {
                        descripcion1 = descripcionGame.getText();
                }    
                App.juegos.add(new Juego(titleGame.getText(), descripcion1, ruta1, ruta2, rutaImagen, ruta3));
                App.serializarJuegos();
                mostraralertaconfirmacion("Juego creado exitosamente");
                App.setRoot("juegos");
                }
            }
            else {
                mostraralertaerror("Ponga un titulo");
            }                
    }
    
     public File buscarArchivo() throws IOException {
        FileChooser fc = new FileChooser();
        fc.setTitle("Buscar archivo");
        
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("txt", "*.txt")
        );
        
        File file = fc.showOpenDialog(null);
        return file;    
    }
    
    public File buscarFoto() throws IOException {
        FileChooser fc = new FileChooser();
        fc.setTitle("Buscar Foto");
        
        fc.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("All Images", "*.*"),
        new FileChooser.ExtensionFilter("jpg", "*.jpg"),
        new FileChooser.ExtensionFilter("jpeg", "*.jpeg"),
        new FileChooser.ExtensionFilter("png", "*.png")
        );
        
        File file = fc.showOpenDialog(null);
        return file;    
    }
    
     public void mostraralertaconfirmacion(String msg) {
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Resultado de la operacion");
        alert.setContentText(msg);
        String css = this.getClass().getResource("/styles/estilos.css").toExternalForm();
        alert.getDialogPane().getStylesheets().add(css);
        alert.getDialogPane().getStyleClass().add("dialog-paneConfirmacion");
        alert.showAndWait();      
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

    private boolean validarArchivos(File questionscopy, File answerscopy, File imagenesCopy) throws IOException {
        
        // Subio los dos archivos
        if(questionscopy == null || answerscopy == null || imagenesCopy==null) {
            mostraralertaerror("Intente subir los dos archivos");
            return false;
        }
        
        // Archivo vacio
     
        if((int) questionscopy.length() == 0 || (int) answerscopy.length() == 0 || (int) imagenesCopy.length()==0) {
            mostraralertaerror("Archivo vacio");
            return false;
        } 
        
        // Archivo de respuestas e imagenes 
        
        try {
            // Contar el número de líneas en cada archivo
            long answersCount = Files.lines( answerscopy.toPath()).count();
            long imagesCount = Files.lines(imagenesCopy.toPath()).count();
            System.out.println(answersCount);
            System.out.println(imagesCount);
            // Comparar los conteos
            if (answersCount != imagesCount) {
                mostraralertaerror("El total de líneas de tanto del archivo de respuestas como de imágenes deben ser iguales.");
                return false;
            } 
        } catch (IOException e) {
            e.printStackTrace();
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
        
        
        // Archivo de Imagenes 
        try (BufferedReader br = new BufferedReader(new FileReader(imagenesCopy))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // Divide la línea según el delimitador
                Path sourcePath = Paths.get(linea);

                // Verificar si la imagen existe en la ruta especificada
                if (!Files.exists(sourcePath)) {
                    mostraralertaerror("La ruta de la imagen no existe: " + linea);
                    return false;
                }     
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
       return true; 
    }
    
    
   public static void copyImagesFromFile(File file, String destinationDirectory, File logFile) throws IOException {
        // Asegurarse de que el directorio de destino exista
        Path destinationDirPath = Paths.get(destinationDirectory);
        if (!Files.exists(destinationDirPath)) {
            Files.createDirectories(destinationDirPath);  // Crear el directorio si no existe
        }

        // Directorio base para las rutas relativas
        Path baseDir = Paths.get("src", "main", "resources");

        // Crear un archivo de registro para las rutas actuales de las imágenes
        try (FileWriter logWriter = new FileWriter(logFile)) {
            // Leer el archivo de texto línea por línea
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    String imagePath = scanner.nextLine().trim();
                    Path sourcePath = Paths.get(imagePath);

                    // Verificar si la imagen existe en la ruta especificada
                    if (Files.exists(sourcePath)) {
                        // Crear la ruta de destino para la imagen
                        Path destinationPath = destinationDirPath.resolve(sourcePath.getFileName());

                        // Copiar la imagen al directorio de destino
                        Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
                        System.out.println("Imagen copiada: " + sourcePath.getFileName());

                        // Construir la ruta relativa desde src/main/resources
                        Path relativePath = baseDir.relativize(destinationPath);

                        // Convertir la ruta a un string con barras normales
                        String relativePathStr = relativePath.toString().replace("\\", "/");

                        // Escribir la ruta relativa en el archivo de registro
                        logWriter.write("/" + relativePathStr + System.lineSeparator());
                    } else {
                        // Lanza una excepción si la imagen no existe
                        throw new IOException("La ruta de la imagen no existe: " + imagePath);
                    }
                }
            }
        }
    }
}
