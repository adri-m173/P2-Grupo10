import java.util.ArrayList;

public class Entrada {
    private final String titulo;
    private final String contenido;
    private final Usuario autor;
    private int puntuacion;
    private ArrayList<Comentario> comentarios = new ArrayList<>();

    public Entrada(Usuario autor_, String titulo_,String contenido_){
        this.autor = autor_;
        this.titulo = titulo_;
        this.contenido = contenido_;
        this.puntuacion = 0;
    }
    public void comentarEntrada(Usuario autor_, String comentario_) {
        Comentario coment = new Comentario(autor_, comentario_);
        comentarios.add(coment);
        System.out.println("Comentario realizado en la entrada: " + getTitulo());
    }

    public void votarPositivamente(Usuario usuario){
        if (usuario.getNick().equals(autor.getNick())) {
            System.out.println("Error, no puedes votar tu propia entrada");
        } else {
            this.puntuacion = puntuacion+1;
            System.out.println("Has votado positivamente la entrada: " + getTitulo());
        }
    }

    public void votarNegativamente(Usuario usuario){
        if (usuario.getNick().equals(autor.getNick())) {
            System.out.println("Error, no puedes votar tu propia entrada");
        } else {
            this.puntuacion = puntuacion-1;
            System.out.println("Has votado negativamente la entrada: " + getTitulo());
        }
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