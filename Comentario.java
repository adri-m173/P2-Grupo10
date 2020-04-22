public class Comentario {
    private final String comentario;
    private final Usuario autor;
    private int puntuacion;

    public Comentario(Usuario autor_, String comentario_) {
        this.autor = autor_;
        this.comentario = comentario_;
    }

    public String getComentario() {
        return comentario;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public boolean votarPositivamente(Usuario usuario) {
        boolean salida = false;
        if (usuario.getNick().equals(autor.getNick())) {
            System.out.println("Error. No puedes votar tu propio comentario");
        } else {
            this.puntuacion = puntuacion+1;
            salida = true;
        }
        return salida;
    }

    public boolean votarNegativamente(Usuario usuario) {
        boolean salida = false;
        if (usuario.getNick().equals(autor.getNick())) {
            System.out.println("Error. No puedes votar tu propio comentario");
        } else {
            this.puntuacion = puntuacion-1;
            salida = true;
        }
        return salida;
    }
}