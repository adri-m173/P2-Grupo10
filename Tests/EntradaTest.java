
import org.junit.Test;
import static org.junit.Assert.*;

public class EntradaTest {
    
    Usuario usuario = new Usuario("nick", "nombre", "apellido", "pass", "email");
    Usuario usuario2 = new Usuario("nick2", "nombre2", "apellido2", "pass2", "email2");
    Entrada entrada = new Entrada(usuario, "titulo", "contenido", 0);

    /**
     * Test of comentarEntrada method, of class Entrada.
     */
    @Test
    public void testComentarEntrada() {
        String texto = "Me gusta mucho esta entrada";
        entrada.comentarEntrada(usuario2,texto);
        assertEquals(texto, entrada.getComentarios().get(0).getComentario());       
    }

    /**
     * Test of votarPositivamente method, of class Entrada.
     */
    @Test
    public void testVotarPositivamente() {
        int puntuacionFutura = entrada.getPuntuacion()+1;
        entrada.votarPositivamente(usuario2);
        assertEquals(puntuacionFutura, entrada.getPuntuacion());
        
    }

    /**
     * Test of votarNegativamente method, of class Entrada.
     */
    @Test
    public void testVotarNegativamente() {
        int puntuacionFutura = entrada.getPuntuacion()-1;
        entrada.votarNegativamente(usuario2);
        assertEquals(puntuacionFutura, entrada.getPuntuacion());
    }

   
    
}
