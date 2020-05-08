package com.p3;

import org.junit.Test;

import static org.junit.Assert.*;

public class ComentarioTest {
    Usuario usuario1 = new Usuario("nick1", "nombre1", "apellido1", "pass1", "email1");
    Usuario usuario2 = new Usuario("nick2", "nombre2", "apellido2", "pass2", "email2");
    Comentario comentario1 = new Comentario(usuario1,"comentario1");
    Comentario comentario2 = new Comentario(usuario2,"comentario2");

    @Test
    public void votarPositivamente() {
        comentario1.votarPositivamente(usuario2);
        assertEquals(1, comentario1.getPuntuacion());
    }

    @Test
    public void votarNegativamente() {
        comentario2.votarNegativamente(usuario1);
        assertEquals(-1, comentario2.getPuntuacion());
    }

    @Test
    public void votarComentarioPropio() {
        comentario1.votarPositivamente(usuario1);
        assertEquals(0, comentario1.getPuntuacion());
        comentario1.votarNegativamente(usuario1);
        assertEquals(0, comentario1.getPuntuacion());
        comentario2.votarPositivamente(usuario2);
        assertEquals(0, comentario2.getPuntuacion());
        comentario2.votarNegativamente(usuario2);
        assertEquals(0, comentario2.getPuntuacion());
    }
}