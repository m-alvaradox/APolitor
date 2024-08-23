package Objects;

import java.io.Serializable;

public class Juego implements Serializable {
    
    private String titulo;
    private String descripcion;
    private String preguntas;
    private String answers;
    private String imagenes;
    private String portada;

    public Juego(String titulo, String descripcion, String preguntas, String answers, String portada, String imagenes) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.preguntas = preguntas;
        this.answers = answers;
        this.portada = portada;
        this.imagenes = imagenes;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(String preguntas) {
        this.preguntas = preguntas;
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }

    public String getPortada() {
        return portada;
    }

    public void setPortada(String portada) {
        this.portada = portada;
    }

    public String getImagenes() {
        return imagenes;
    }

    public void setImagenes(String imagenes) {
        this.imagenes = imagenes;
    }
    
    
    
    
    
    
}
