package com.p2;

import java.util.ArrayList;

public class Sistema {
    private ArrayList<Usuario> usuarios;

    public void registrarUsuario(String nick_, String nombre_, String apellidos_, String pass_, String email_){
        Usuario nuevo = new Usuario(nick_, nombre_, apellidos_, pass_, email_);
        usuarios.add(nuevo);
    }
}
