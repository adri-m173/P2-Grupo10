import java.io.Serializable;

//La clase Encuesta hereda de la clase Entrada
public class Encuesta extends Entrada implements Serializable {
    private static final long serialVersionUID = 1L;
    String Respuesta1;
    String Respuesta2;
    String Respuesta3;
    
    public Encuesta(Usuario autor_, String titulo_, String pregunta,String r1, String r2, String r3, int n) {
        super(autor_, titulo_, pregunta, n);
        this.Respuesta1 = r1;
        this.Respuesta2 = r2;
        this.Respuesta3 = r3;
    }
}