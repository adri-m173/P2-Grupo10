
package practicamp2;

public class Encuesta extends Entrada{
    String Respuesta1;
    String Respuesta2;
    String Respuesta3;
    
    public Encuesta(String titulo_, String pregunta,String r1, String r2, String r3) {
        super(titulo_, pregunta,1, r1, r2, r3);
        this.Respuesta1 = r1;
        this.Respuesta2 = r2;
        this.Respuesta3 = r3;
    }
    
}
