package practicamp2;
import java.util.ArrayList;

public class Entrada {
    private String titulo;
    private int TipoEjercicio;
    private String contenido;
    private int puntuacion;
    private ArrayList<Comentario> comentarios = new ArrayList<>();
    
    public Entrada(String titulo_,String contenido_,int tipo, String r1, String r2, String r3){
        this.titulo = titulo_;
        this.contenido = contenido_;
        this.puntuacion = 0;
        this.TipoEjercicio = tipo;
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

    public int getTipoEjercicio() {
        return TipoEjercicio;
    }
    
    public ArrayList<Comentario> getComentarios() {
        return comentarios;
    }
}