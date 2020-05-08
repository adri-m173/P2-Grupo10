package com.p3;

import org.junit.Test;

import static org.junit.Assert.*;

public class EntradaTest {
    //Se crean los objetos necesarios para los tests
    Usuario usuario = new Usuario("nick", "nombre", "apellido", "pass", "email");
    Usuario usuario2 = new Usuario("nick2", "nombre2", "apellido2", "pass2", "email2");
    Entrada entrada = new Entrada(usuario, "titulo", "contenido", 0);

    @Test
    public void comentarEntrada() {
        String texto = "Me gusta mucho esta entrada"; //Se guarda el texto del comentario en la varible texto
        entrada.comentarEntrada(usuario2, texto); //El usuario2 pone un comentario en la entrada del usuario
        assertEquals(texto, entrada.getComentarios().get(0).getComentario()); //Se comprueba que efectivamente ese comentario se ha efectuado
    }

    @Test
    public void votarPositivamente() {
        int puntuacionFutura = entrada.getPuntuacion() + 1; //Se guarda la puntuacion esperada en la variable puntuacionFutura
        entrada.votarPositivamente(usuario2); //El usuario2 vota positivamente la entrada del usuario
        assertEquals(puntuacionFutura, entrada.getPuntuacion()); //Se comprueba que la puntuacion esperada se corresponde con la puntuacion de la entrada del usuario
        entrada.votarPositivamente(usuario2); //El usuario2 intenta votar positivamente de nuevo la entrada del usuario
        assertEquals(puntuacionFutura, entrada.getPuntuacion()); //Se comprueba que la puntuacion de la entrada no varia y se sigue correspondiendo con la esperada
        entrada.votarPositivamente(usuario); //El usuario intenta votar positivamente su propia entrada
        assertEquals(puntuacionFutura, entrada.getPuntuacion()); //Se comprueba que la puntuacion de la entrada no varia y se sigue correspondiendo con la esperada
    }

    @Test
    public void votarNegativamente() {
        int puntuacionFutura = entrada.getPuntuacion() - 1; //Se guarda la puntuacion esperada en la variable puntuacionFutura
        entrada.votarNegativamente(usuario2); //El usuario2 vota negativamente la entrada del usuario
        assertEquals(puntuacionFutura, entrada.getPuntuacion()); //Se comprueba que la puntuacion esperada se corresponde con la puntuacion de la entrada del usuario
        entrada.votarNegativamente(usuario2); //El usuario2 intenta votar negativamente de nuevo la entrada del usuario
        assertEquals(puntuacionFutura, entrada.getPuntuacion()); //Se comprueba que la puntuacion de la entrada no varia y se sigue correspondiendo con la esperada
        entrada.votarNegativamente(usuario); //El usuario intenta votar negativamente su propia entrada
        assertEquals(puntuacionFutura, entrada.getPuntuacion()); //Se comprueba que la puntuacion de la entrada no varia y se sigue correspondiendo con la esperada
    }
}