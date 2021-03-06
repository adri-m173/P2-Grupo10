import java.io.Serializable;

//La clase TipoMixto hereda de la clase Entrada
public class TipoMixto extends Entrada implements Serializable {
    private static final long serialVersionUID = 1L;
    String Respuesta1;
    String Respuesta2;
    String Respuesta3;

    public TipoMixto(Usuario autor_, String titulo_, String contenido_, String r1, String r2, String r3, int n) {
        super(autor_, titulo_, contenido_, n);
        this.Respuesta1 = r1;
        this.Respuesta2 = r2;
        this.Respuesta3 = r3;
    }
}