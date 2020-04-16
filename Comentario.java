public class Comentario {
    private String comentario;
    private int puntuacion;

    public Comentario(String comentario_) {
        this.comentario = comentario_;
    }

    public String getComentario() {
        return comentario;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void votarPositivamente() {
        this.puntuacion = puntuacion+1;
    }

    public void votarNegativamente() {
        this.puntuacion = puntuacion-1;
    }
}
