package com.p3;
import org.junit.Test;

import static org.junit.Assert.*;
public class SubforoTest {
    Usuario usuario = new Usuario("nick", "nombre", "apellidos", "contra", "email");
    Usuario usuario2 = new Usuario("nick2", "nombre2", "apellidos2", "contra2", "email2");
    Subforo subforo = new Subforo("titulo");
    Entrada entrada = new Entrada(usuario, "titulo", "contenido", 0);

    @Test
    public void aniadirEntrada() {
        int tamanioEsperado = subforo.getEntradas().size() + 1; //Se guarda el tamanio esperado del ArrayList que guarda las entradas del subforo
        subforo.aniadirEntrada(entrada); //Se aniade la entrada al subforo
        assertEquals(tamanioEsperado, subforo.getEntradas().size()); //Se comprueba que el ArrayList que guarda las entradas que tiene el subforo ahora tiene una entrada mas
    }

    @Test
    public void entradaMasVotada() {
        subforo.aniadirEntrada(entrada);//Añadimos una entrada al subforo para poder votar positivamente
        entrada.votarPositivamente(usuario);//Votamos positivamente la entrada para que haya alguna mas votada
        assertEquals(subforo.EntradaMasVotada().getTitulo(), entrada.getTitulo());
    }

    @Test
    public void aniadirSubscriptor() {
        int tamanioEsperado = subforo.getUsuariosSubscritos().size() + 1; //Se guarda el tamanio del ArrayList esperado en la variable tamanioEsperado
        subforo.aniadirSubscriptor(usuario); //El usuario se subscribe al subforo
        assertEquals(tamanioEsperado, subforo.getUsuariosSubscritos().size()); //Se comprueba que el ArrayList que guarda los subscriptores del subforo ahora tiene un subscriptor mas
    }

    @Test
    public void notificar() {
        subforo.aniadirSubscriptor(usuario);//Añadimos un subscriptor que pueda ser notificado y medir sus notificaciones
        int tamanioEsperado = usuario.getNotificaciones().size() + 1; //Se guarda el tamanio esperado del ArrayList que guarda las notificaciones de un usuario
        subforo.notificar(); //Se notifica a los usuarios subscritos al subforo
        assertEquals(tamanioEsperado, usuario.getNotificaciones().size()); //Se comprueba que el ArrayList que guarda las notificaciones del usuario subscrito ahora tiene una notificacion mas
    }

    @Test
    public void eliminarSubscriptor() {
        
        subforo.aniadirSubscriptor(usuario);//Añadimos un subscriptor para posterior eliminar y ver el funcionamiento del metodo
        subforo.aniadirSubscriptor(usuario2);//Añadimos otro subscriptor el cual sirve para comparar de manera mas concreta el tamaño de la lista de susbscriptores y ver que el anterior se elimina correctamente
        int tamanioEsperado = subforo.getUsuariosSubscritos().size()-1; //Se guarda el tamanio del ArrayList esperado en la variable tamanioEsperado
        subforo.eliminarSubscriptor(usuario); //El usuario se da de baja del subforo
        assertEquals(tamanioEsperado, subforo.getUsuariosSubscritos().size()); //Se comprueba que el ArrayList que guarda los subscriptores del subforo ahora tiene un subscriptor menos
    }
}