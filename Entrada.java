package practicamp2;
import java.util.ArrayList;

public class Entrada {
    private String titulo;
    private String contenido;
    private int puntuacion;
    private ArrayList<String> comentarios = new ArrayList<>();
    
    public void crearEntrada(String titulo_,String contenido_){
        this.titulo = titulo_;
        this.contenido = contenido_;
        this.puntuacion = 0;
    }

    public void comentar(String comentario_){
        comentarios.add(comentario_);
    }

    public void votarPositivamente(){
        this.puntuacion = puntuacion+1;
    }

    public void votarNegativamente(){
        this.puntuacion = puntuacion-1;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public ArrayList<String> getComentarios(){
        return comentarios;
    }

    public String toString(){
        return "Titulo: " + titulo + ". Contenido: " + contenido + ". Puntuacion: " + puntuacion;
    }
    
}
