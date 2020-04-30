package practicamp2;
import java.io.Serializable;

//La clase TextoPlano hereda de la clase Entrada
public class TextoPlano extends Entrada implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public TextoPlano(Usuario autor_, String titulo_, String contenido_, int n) {
        super(autor_, titulo_, contenido_, n);
    }
}