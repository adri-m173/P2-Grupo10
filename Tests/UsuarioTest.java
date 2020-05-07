package com.p2;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {
    Administrador admin = new Administrador("nick", "nombre", "apellidos", "pass", "email");
    Usuario usuario = new Usuario("nick", "nombre", "apellido", "pass", "email");
    private final int dias = 5;

    @Test
    public void avanzarDias() {
        admin.banear(usuario, dias);
        usuario.avanzarDias(1);
        assertEquals(dias - 1, usuario.getDiasBaneado());
    }

    @Test
    public void UsuarioBaneado() {
        admin.banear(usuario, dias);
        assertTrue(usuario.comprobarbaneo(usuario));
        assertEquals(dias, usuario.getDiasBaneado());
    }

    @Test
    public void UsuarioDesBaneado() {
        admin.desbanear(usuario);
        assertFalse(usuario.comprobarbaneo(usuario));
    }

    @Test
    public void comprobarnick() {
        assertTrue(usuario.comprobarnick("nick", usuario));
        assertFalse(usuario.comprobarnick("nick2", usuario));
    }

    @Test
    public void recibirNotificacion() {
        usuario.recibirNotificacion("Notificacion");
        assertFalse(usuario.getNotificaciones().isEmpty());
        usuario.recibirNotificacion("Notificacion 2");
        assertEquals(2, usuario.getNotificaciones().size());
    }
}