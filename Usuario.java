package com.p2;

import java.util.ArrayList;

public class Usuario {
    private String nick;
    private String nombre;
    private String apellidos;
    private String pass;
    private String email;
    private ArrayList<String> notificaciones;

    public Usuario(String nick_, String n_, String a_, String p_, String e_){
        nick = nick_;
        nombre = n_;
        apellidos = a_;
        pass = p_;
        email = e_;
    }

    public void recibirNotificacion(String noti){}

    public String getNick() {
        return nick;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getPass() {
        return pass;
    }

    public String getEmail() {
        return email;
    }

    public String toString(){
        return "Nick: " + nick + ", Nombre: " + nombre + ", Apellidos: " + apellidos + ", Email: " + email;
    }
}