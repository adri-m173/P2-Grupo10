import java.util.ArrayList;

public class Entrada {
    private String titulo;
    private String contenido;
    private ArrayList<String> comentarios = new ArrayList<>();
    
    public void crearEntrada(String titulo_,String contenido_){
        this.titulo=titulo_;
        this.contenido=contenido_;
    }

    public void comentar(String comentario_){
        comentarios.add(comentario_);
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
    
}
