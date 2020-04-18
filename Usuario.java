package practicamp2;
import java.util.ArrayList;

public class Usuario {
    private String nick;
    private String nombre;
    private String apellidos;
    private String pass;
    private String email;
    private boolean baneado;
    private ArrayList<String> notificaciones;

    public Usuario(String nick_, String n_, String a_, String p_, String e_){
        nick = nick_;
        nombre = n_;
        apellidos = a_;
        pass = p_;
        email = e_;
        this.baneado=false;
    }

    public void recibirNotificacion(String noti){
        notificaciones.add(noti);
    }
    
    public void verNotificaciones(){
        System.out.println("Tienes las siguientes notificaciones: " + notificaciones.toString());
    }
    

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

    public void UsuarioBaneado() {
        baneado = true;
    }

    public void UsuarioDesBaneado() {
        baneado = false;
    }

    public boolean comprobarnick(String nick, Usuario u) {
        return nick==u.nick;
    }

    public boolean comprobarbaneo(Usuario u) {
        return u.baneado;
    }
}