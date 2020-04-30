import java.io.Serializable;
import java.util.ArrayList;

public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String nick;
    private final String nombre;
    private final String apellidos;
    private final String pass;
    private final String email;
    private boolean baneado;
    private int DiasBaneado;
    private boolean esProfesorAlumno = false;
    private ArrayList<String> notificaciones = new ArrayList<>();
    
    public Usuario(String nick_, String n_, String a_, String p_, String e_) {
        nick = nick_;
        nombre = n_;
        apellidos = a_;
        pass = p_;
        email = e_;
        this.baneado=false;
        this.DiasBaneado=0;
    }
    public void avanzarDias(int dias){
        this.DiasBaneado=DiasBaneado-dias;
        System.out.println("Se han avanzado "+ dias + " dias");
        if (this.DiasBaneado<=0){
            this.baneado=false;
            this.DiasBaneado=0;
            System.out.println("El usuario ya no está baneado");
        }
    }
    public void recibirNotificacion(String noti) {
        notificaciones.add(noti);
    }

    public void verNotificaciones() {
        System.out.println("Tienes las siguientes notificaciones: " + notificaciones.toString());
    }

    public void UsuarioBaneado(int dias) {
        baneado = true;
        DiasBaneado = dias;
    }

    public void UsuarioDesBaneado() {
        baneado = false;
    }

    public boolean comprobarnick(String nick, Usuario u) {
        return nick.equals(u.nick);
    }

    public boolean comprobarbaneo(Usuario u) {
        return u.baneado;
    }
    
    public void setProfesor(){
        this.esProfesorAlumno = true;
    }

    public String getEmail() {
        return email;
    }

    public boolean getEsProfesorAlumno() {
        return esProfesorAlumno;
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
}