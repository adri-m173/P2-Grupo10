import java.io.Serializable;
import java.util.ArrayList;

public class Entrada implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String titulo;
    private final String contenido;
    private final Usuario autor;
    private int puntuacion;
    private final int numSubforo;
    private boolean esVisible = false;
    private ArrayList<Comentario> comentarios = new ArrayList<>();
    private ArrayList<Usuario> Likes = new ArrayList<>();
    //en esta lista se guarda información sobre qué usuarios han votado positivamente
    private ArrayList<Usuario> Dislikes = new ArrayList<>();
    //en esta lista se guarda información sobre qué usuarios han votado negativamente

    public Entrada(Usuario autor_, String titulo_, String contenido_, int n) {
        this.autor = autor_;
        this.titulo = titulo_;
        this.contenido = contenido_;
        this.puntuacion = 0;
        this.numSubforo = n;
    }

    public void comentarEntrada(Usuario autor_, String comentario_) {
        Comentario coment = new Comentario(autor_, comentario_);
        comentarios.add(coment);
        System.out.println("Comentario realizado en la entrada: " + getTitulo());
    }

    public void votarPositivamente(Usuario usuario) {
        if (usuario.getNick().equals(autor.getNick())) {
            System.out.println("Error, no puedes votar tu propia entrada");
        } else {
            if (Likes.contains(usuario)) {
                System.out.println("No puedes volver a votar esta entrada");
            }
            else {
                this.puntuacion = puntuacion + 1;
                //el sistema comprueba si el usuario ha dado un voto negativo anteriormente
                if(Dislikes.contains(usuario)) {
                    Dislikes.remove(usuario);
                    this.puntuacion = puntuacion + 1;
                }
                Likes.add(usuario);
                System.out.println("Has votado positivamente la entrada: " + getTitulo());
            }
        }
    }

    public void votarNegativamente(Usuario usuario) {
        if (usuario.getNick().equals(autor.getNick())) {
            System.out.println("Error, no puedes votar tu propia entrada");
        } else {
            if (Dislikes.contains(usuario)) {
                System.out.println("No puedes volver a votar esta entrada");
            }
            else{
                this.puntuacion = puntuacion - 1;
                //el sistema comprueba si el usuario ha dado un voto positivo anteriormente
                if (Likes.contains(usuario)) {
                    Likes.remove(usuario);
                    this.puntuacion = puntuacion - 1;
                }
                Dislikes.add(usuario);
                System.out.println("Has votado negativamente la entrada: " + getTitulo());

            }
        }
    }

    public int getNumSubforo() {
        return numSubforo;
    }

    public void hacerVisible() {
        this.esVisible = true;
    }

    public String getTitulo() {
        return titulo;
    }

    public Usuario getAutor() {
        return autor;
    }

    public String getContenido() {
        return contenido;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public boolean getEsVisible() {
        return esVisible;
    }

    public ArrayList<Comentario> getComentarios() {
        return comentarios;
    }
}