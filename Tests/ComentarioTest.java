package com.p3;

import org.junit.Test;

import static org.junit.Assert.*;

public class ComentarioTest {
    //Se crean los objetos necesarios para los tests
    Usuario usuario1 = new Usuario("nick1", "nombre1", "apellido1", "pass1", "email1");
    Usuario usuario2 = new Usuario("nick2", "nombre2", "apellido2", "pass2", "email2");
    Comentario comentario1 = new Comentario(usuario1,"comentario1");
    Comentario comentario2 = new Comentario(usuario2,"comentario2");

    @Test
    public void votarPositivamente() {
        comentario1.votarPositivamente(usuario2); //El usuario2 vota positivamente el comentario1 del usuario1
        assertEquals(1, comentario1.getPuntuacion()); //Se comprueba que la puntuacion del comentario1 es ahora igual a 1
    }

    @Test
    public void votarNegativamente() {
        comentario2.votarNegativamente(usuario1); //El usuario1 vota negativamente el comentario2 del usuario2
        assertEquals(-1, comentario2.getPuntuacion()); //Se comprueba que la puntuacion del comentario2 es ahora igual a -1
    }

    @Test
    public void votarComentarioPropio() {
        comentario1.votarPositivamente(usuario1); //El usuario1 intenta votar positivamente su propio comentario
        assertEquals(0, comentario1.getPuntuacion()); //Se comprueba que la puntuacion del comentario del usuario1 no ha variado
        comentario1.votarNegativamente(usuario1); //El usuario1 intenta votar negativamente su propio comentario
        assertEquals(0, comentario1.getPuntuacion()); //Se comprueba que la puntuacion del comentario del usuario1 no ha variado
        comentario2.votarPositivamente(usuario2); //El usuario2 intenta votar positivamente su propio comentario
        assertEquals(0, comentario2.getPuntuacion()); //Se comprueba que la puntuacion del comentario del usuario2 no ha variado
        comentario2.votarNegativamente(usuario2); //El usuario2 intenta votar negativamente su propio comentario
        assertEquals(0, comentario2.getPuntuacion()); //Se comprueba que la puntuacion del comentario del usuario2 no ha variado
    }
}