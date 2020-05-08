import org.junit.Test;
import static org.junit.Assert.*;


public class AdministradorTest {
    Usuario usuario = new Usuario("nick", "nombre", "apellido", "pass", "email");
    Administrador administrador = new Administrador("admin","nombre","apellido","pass","email");
    Entrada entrada = new Entrada(usuario,"entrada","contenido",1);
    
    public AdministradorTest() {
    }


    @Test
    public void testBanear() {
        administrador.banear(usuario, 5); //El administrador banea al usuario por 5 dias
        boolean baneo = usuario.comprobarbaneo(usuario); //Se mira el estado de baneo del usuario
        assertTrue(baneo); //Se comprueba si el usuario ha sido baneado correctamente
        
    }

    /**
     * Test of desbanear method, of class Administrador.
     */
    @Test
    public void testDesbanear() {
        administrador.banear(usuario, 5); //El administrador banea al usuario por 5 dias
        administrador.desbanear(usuario); //El administrador desbanea al usuario
        boolean baneo = usuario.comprobarbaneo(usuario); //Se mira el estado de baneo del usuario
        assertFalse(baneo); //Se comprueba si el usuario ha sido desbaneado correctamente
    }


    @Test
    public void testVerificarEntrada() {
        administrador.verificarEntrada(entrada); //El administrador verifica una entrada para que sea visible
        boolean visibilidad = entrada.getEsVisible(); //Se mira el estado de visibilidad de la entrada
        assertTrue(visibilidad); //Se comprueba si la entrada ha sido verificada correctamente y por lo cual es visible
        
    }
    
}