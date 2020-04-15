package com.p2;

import java.util.ArrayList;

public class Subforo {
    private String titulo;
    private ArrayList <Entrada> entradas = new ArrayList<>();
    private ArrayList <Usuario> usuariosSubscritos = new ArrayList<>();

    public void crearSubforo(String titulo_){
        this.titulo=titulo_;
    }

    public void aniadirEntrada(Entrada entrada_){
        entradas.add(entrada_);
    }

    public ArrayList<Entrada> getEntradas() {
        return entradas;
    }

    public void aniadirSubscriptor(Usuario subs) {
        usuariosSubscritos.add(subs);
    }

    public void eliminarSubscriptor(Usuario subs){
        usuariosSubscritos.remove(subs);
    }

    public void notificar(){
        String notificacion = "Una nueva entrada ha sido añadida en el subforo:" + titulo;
        for (Usuario x: usuariosSubscritos){
            x.recibirNotificacion(notificacion);
        }
    }
}
