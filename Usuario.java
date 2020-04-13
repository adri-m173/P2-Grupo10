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

    public void setNick(String nick_) {
        this.nick = nick_;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String n_) {
        this.nombre = n_;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String a_) {
        this.apellidos = a_;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String p_) {
        this.pass = p_;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String e_) {
        this.email = e_;
    }
}
