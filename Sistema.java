package com.p2;

import java.util.ArrayList;

public class Sistema {
    private ArrayList<Usuario> usuarios;

    public void registrarUsuario(String nick_, String nombre_, String apellidos_, String pass_, String email_){
        Usuario nuevo = new Usuario(nick_, nombre_, apellidos_, pass_, email_);
        usuarios.add(nuevo);
    }

    public boolean hacerLogin(String nick_, String pass_){
        if (usuarios == null || usuarios.size()<=0){
            System.out.println("No existen usuarios registrados");
            return false;
        }
        for (Usuario usr: usuarios) {
            if (usr.getNick().equals(nick_) && usr.getPass().equals(pass_)) {
                return true;
            } else {
                return false;
            }
        }
    }
}
