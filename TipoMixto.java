public class TipoMixto extends Entrada{
    String Respuesta1;
    String Respuesta2;
    String Respuesta3;

    public TipoMixto(Usuario autor_, String titulo_, String contenido_, String r1, String r2, String r3) {
        super(autor_, titulo_, contenido_);
        this.Respuesta1 = r1;
        this.Respuesta2 = r2;
        this.Respuesta3 = r3;
    }
}
