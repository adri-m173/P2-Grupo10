package com.p3;

import org.junit.*;

import static org.junit.Assert.*;

public class UsuarioTest {
    //Se crean los objetos y variables necesarios para los tests
    Administrador admin = new Administrador("nick", "nombre", "apellidos", "pass", "email");
    Usuario usuario = new Usuario("nick", "nombre", "apellido", "pass", "email");
    private final int dias = 5;

    @Test
    public void avanzarDias() {
        admin.banear(usuario, dias); //Se banea al usuario 5 dias
        usuario.avanzarDias(1); //Se avanza 1 dia en el contador de dias restantes del baneo
        assertEquals(dias - 1, usuario.getDiasBaneado()); //Se comprueba que los dias restantes de baneo es 1 dia menor al inicial
    }

    @Test
    public void UsuarioBaneado() {
        admin.banear(usuario, dias); //Se banea al usuario 5 dias
        assertTrue(usuario.comprobarbaneo(usuario)); //Se comprueba que la condicion booleana de si un usuario esta baneado esta a True
        assertEquals(dias, usuario.getDiasBaneado()); //Se comprueba que la variable que guarda los dias que ese usuario esta baneado guarda el numero correcto
    }

    @Test
    public void UsuarioDesBaneado() {
        admin.desbanear(usuario); //Se desbanea al usuario
        assertFalse(usuario.comprobarbaneo(usuario)); //Se comprueba que la condicion booleana de si un usuario esta baneado esta a False
    }

    @Test
    public void comprobarnick() { //Se comprueba que el sistema reconoce si un nick ya esta en uso por otro usuario
        assertTrue(usuario.comprobarnick("nick", usuario));
        assertFalse(usuario.comprobarnick("nick2", usuario));
    }

    @Test
    public void recibirNotificacion() {
        usuario.recibirNotificacion("Notificacion"); //El usuario recibe una notificacion
        assertFalse(usuario.getNotificaciones().isEmpty()); //Se comprueba que el ArrayList que guarda las notificaciones de un usuario ya no esta vacio
        usuario.recibirNotificacion("Notificacion 2"); //El usuario recibe otra notificacion
        assertEquals(2, usuario.getNotificaciones().size()); //Se comprueba que el ArrayList de las notificaciones ahora tiene tamanio 2
    }
}