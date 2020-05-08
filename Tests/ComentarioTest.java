import org.junit.*;

import static org.junit.Assert.*;

/**
 *
 * @author anton
 */
public class ComentarioTest {
    Usuario usuario1 = new Usuario("nick1", "nombre1", "apellido1", "pass1", "email1");
    Usuario usuario2 = new Usuario("nick2", "nombre2", "apellido2", "pass2", "email2");
    Comentario comentario = new Comentario(usuario1,"comentario1");

    
    public ComentarioTest() {
    }

    /**
     * Test of votarPositivamente method, of class Comentario.
     */
    @Test
    public void testVotarPositivamente() {
        
        comentario.votarPositivamente(usuario2);
        int puntuacioncomentario1 = comentario.getPuntuacion();
        assertEquals(1,puntuacioncomentario1);
    }

    /**
     * Test of votarNegativamente method, of class Comentario.
     */
    @Test
    public void testVotarNegativamente() {
        
        comentario.votarNegativamente(usuario2);
        int puntuacioncomentario2 = comentario.getPuntuacion();
        assertEquals(-1,puntuacioncomentario2);
    }

    /**
     * Test of getComentario method, of class Comentario.
     */
    @Test
    public void testGetComentario() {
        String com = comentario.getComentario();
        assertEquals("comentario1",com);
    }

    /**
     * Test of getPuntuacion method, of class Comentario.
     */
    @Test
    public void testGetPuntuacion() {
        int puntuacion = comentario.getPuntuacion();
        assertEquals(0,puntuacion);
    }
    
}