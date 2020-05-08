import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class ComentarioTest {
    Usuario usuario1 = new Usuario("nick1", "nombre1", "apellido1", "pass1", "email1");
    Usuario usuario2 = new Usuario("nick2", "nombre2", "apellido2", "pass2", "email2");
    Comentario comentario1 = new Comentario(usuario1,"comentario1");
    Comentario comentario2 = new Comentario(usuario2,"comentario2");
    
    public ComentarioTest() {
    }

    /**
     * Test of votarPositivamente method, of class Comentario.
     */
    @Test
    public void testVotarPositivamente() {
        
        comentario1.votarPositivamente(usuario2);
        int puntuacioncomentario1 = comentario1.getPuntuacion();
        assertEquals(1,puntuacioncomentario1);
    }

    /**
     * Test of votarNegativamente method, of class Comentario.
     */
    @Test
    public void testVotarNegativamente() {
        
        comentario2.votarPositivamente(usuario1);
        int puntuacioncomentario2 = comentario2.getPuntuacion();
        assertEquals(-1,puntuacioncomentario2);
    }

    /**
     * Test of getComentario method, of class Comentario.
     */
    @Test
    public void testGetComentario() {
        String comentario = comentario1.getComentario();
        assertEquals("comentario1",comentario);
    }

    /**
     * Test of getPuntuacion method, of class Comentario.
     */
    @Test
    public void testGetPuntuacion() {
        int puntuacion = comentario1.getPuntuacion();
        assertEquals(1,puntuacion);
    }
    
}
