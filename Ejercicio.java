import java.io.Serializable;

public class Ejercicio extends Entrada implements Serializable {
    private static final long serialVersionUID = 1L;

    public Ejercicio(Usuario autor_, String titulo, String enunciado) {
        super(autor_, titulo, enunciado);
    }
}