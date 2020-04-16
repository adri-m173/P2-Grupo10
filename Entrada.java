import java.util.ArrayList;

public class Entrada {
    private String titulo;
    private String contenido;
    private int puntuacion;
    private ArrayList<Comentario> comentarios = new ArrayList<>();

    public Entrada(String titulo_,String contenido_){
        this.titulo = titulo_;
        this.contenido = contenido_;
        this.puntuacion = 0;
    }

    public void comentarEntrada(String comentario_) {
        Comentario coment = new Comentario(comentario_);
        comentarios.add(coment);
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

    public int getPuntuacion() {
        return puntuacion;
    }

    public ArrayList<Comentario> getComentarios() {
        return comentarios;
    }
}