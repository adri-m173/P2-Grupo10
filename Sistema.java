import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Sistema implements Serializable {
    private static final long serialVersionUID = 1L;
    private boolean sesionIniciada = false;
    private ArrayList<Usuario> usuarios = new ArrayList<>();
    private ArrayList<Subforo> foro = new ArrayList<>();

    private void aniadirSubforoEnForo(Subforo subforo_) {
        foro.add(subforo_);
    }

    private ArrayList<Subforo> getForo() {
        return foro;
    }

    public boolean guardarSistema(){
        try {
            FileOutputStream f = new FileOutputStream("BaseDeDatos.obj");
            ObjectOutputStream finalFile = new ObjectOutputStream(f);
            finalFile.writeObject(this);
            finalFile.close();
            f.close();
            return true;

        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public Sistema cargarSistema(){
        Sistema s = null;
        try {
            FileInputStream file =new FileInputStream("BaseDeDatos.obj");
            ObjectInputStream inputFile = new ObjectInputStream(file);
            s = (Sistema) inputFile.readObject();
            
            inputFile.close();
            file.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
        return s;
    }

    private boolean estaDisponible(String email, String nick) {
        boolean salida = true;
        if (usuarios.size() > 0) {
            for (Usuario usr: usuarios) {
                if (usr.getEmail().equals(email) && usr.getNick().equals(nick)) {
                    salida = false;
                    break;
                }
            }
        }
        return salida;
    }

    private void aniadirASubforo(Entrada nuevaEntrada, int numSubforo) {
        getForo().get(numSubforo).aniadirEntrada(nuevaEntrada);
        getForo().get(numSubforo).notificar();
        System.out.println("Entrada " + "'" + nuevaEntrada.getTitulo() + "'" + " añadida correctamente al subforo: " + getForo().get(numSubforo).getTitulo());
    }

    private boolean comprobarLogin() {
        boolean salida = false;
        if (sesionIniciada) {
            salida = true;
        } else {
            System.out.println("Error. Solo los usuarios registrados estan autorizados a hacer esta operacion");
        }
        return salida;
    }

    public Usuario registrarUsuario(String nick_, String nombre_, String apellidos_, String pass_, String email_) {
        if (!estaDisponible(email_, nick_)) {
            System.out.println("Error. El email o nick utilizado ya esta en uso");
            return null;
        }
        else{
            Usuario nuevoUsuario = new Usuario(nick_, nombre_, apellidos_, pass_, email_);
            Scanner sc = new Scanner(email_);
            sc.useDelimiter("@");
            sc.next();
            String s = sc.next();
            System.out.println(s);
            String s1 = "urjc.es";
            String s2 = "alumnos.urjc.es";
            if (s.equals(s1)) {
                usuarios.add(nuevoUsuario);
                System.out.println("Usuario como profesor creado correctamente");
            }
            else if (s.equals(s2)) {
                usuarios.add(nuevoUsuario);
                System.out.println("Usuario como alumno creado correctamente");
            }
            else {
                System.out.println("El correo introducido no es valido");
            }
            sc.close();
            return nuevoUsuario;
        }
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public boolean hacerLogin(String nick_, String pass_) {
        if (usuarios == null || usuarios.size() <= 0) {
            System.out.println("No existen usuarios registrados");
        }
        for (Usuario usr : usuarios) {
            if (usr.getNick().equals(nick_) && usr.getPass().equals(pass_)) {
                sesionIniciada = true;
                System.out.println("Login realizado correctamente");
                System.out.println("Bienvenido, " + usr.getNombre() + " " + usr.getApellidos());
                break;
            }
        }
        if (!sesionIniciada) {
            System.out.println("Error al hacer login");
        }
        return sesionIniciada;
    }
    public void hacerLogout (){
        sesionIniciada = false;
        System.out.println("Sesion Cerrada Correctamente");
    }

    public boolean comprobarusuario(String nick){
        boolean salida = false;
        int i = 0;
        boolean encontrado=false;
        for (int j=0;j<=usuarios.size();j++){
            if(!encontrado){
                Usuario u = usuarios.get(i);
                encontrado = u.comprobarnick(nick, u);
                if (!encontrado)
                    i++;
            }
        }
        if (encontrado) {
            Usuario u = usuarios.get(i);
            salida = u.comprobarbaneo(u);
        }
        if (salida) {
            System.out.println("El usuario esta bandeado");
        }
        return salida;
    }

    public Subforo iniciarSubforo(String titulo) {
        Subforo salida = null;
        if (comprobarLogin()) {
            Subforo nuevoSubforo = new Subforo(titulo);
            aniadirSubforoEnForo(nuevoSubforo);
            salida = nuevoSubforo;
        }
        return salida;
    }

    public void subscribirse(Usuario usuario, int numSubforo){
        if (comprobarLogin()) {
            Subforo subforo = getForo().get(numSubforo);
            subforo.aniadirSubscriptor(usuario);
        }
    }

    public void darseBaja(Usuario usuario, int numSubforo) {
        if (comprobarLogin()) {
            Subforo subforo = getForo().get(numSubforo);
            subforo.eliminarSubscriptor(usuario);
        }
    }

    public Encuesta crearEncuesta(Usuario autor, String titulo, String contenido, String r1, String r2, String r3, int numSubforo){
        Encuesta salida = null;
        if (comprobarLogin()){
            Encuesta e = new Encuesta(autor, titulo, contenido, r1,r2,r3);
            aniadirASubforo(e, numSubforo);
            salida = e;
        }
        return salida;
    }

    public TextoPlano crearTextoPlano(Usuario autor, String titulo, String contenido, int numSubforo){
        TextoPlano salida = null;
        if (comprobarLogin()) {
            TextoPlano e = new TextoPlano(autor, titulo,contenido);
            aniadirASubforo(e, numSubforo);
            salida = e;
        }
        return salida;
    }

    public Ejercicio crearEjercicio (Usuario autor, String titulo, String enunciado, int numSubforo){
        Ejercicio salida = null;
        if (comprobarLogin()) {
            Ejercicio e = new Ejercicio(autor, titulo,enunciado);
            aniadirASubforo(e, numSubforo);
            salida = e;
        }
        return salida;
    }

    public TipoMixto CrearTipoMixto(Usuario autor, String titulo, String contenido, String r1, String r2, String r3, int numSubforo){
        TipoMixto salida = null;
        if (comprobarLogin()) {
            TipoMixto e = new TipoMixto(autor, titulo,contenido,r1,r2,r3);
            aniadirASubforo(e, numSubforo);
            salida = e;
        }
        return salida;
    }

    public void comentarEntrada(Entrada entrada, Usuario autor, String comentario){
        if (comprobarLogin()) {
            entrada.comentarEntrada(autor, comentario);
        }
    }

    public void votarEntradaPositivamente(Entrada entrada, Usuario usuario){
        if (comprobarLogin()) {
            entrada.votarPositivamente(usuario);
        }
    }

    public void votarEntradaNegativamente(Entrada entrada, Usuario usuario) {
        if (comprobarLogin()) {
            entrada.votarNegativamente(usuario);
        }
    }

    public void votarComentarioPositivamente(Entrada entrada, Usuario usuario, int numeroComent) {
        if (comprobarLogin()) {
            if (entrada.getComentarios().get(numeroComent).votarPositivamente(usuario)) {
                System.out.println("Has votado positivamente el comentario " + numeroComent + " de la entrada: " + entrada.getTitulo());
            }
        }
    }

    public void votarComentarioNegativamente(Entrada entrada, Usuario usuario, int numeroComent) {
        if (comprobarLogin()) {
            if (entrada.getComentarios().get(numeroComent).votarNegativamente(usuario)) {
                System.out.println("Has votado negativamente el comentario " + numeroComent + " de la entrada: " + entrada.getTitulo());
            }
        }
    }

    public void mostrarEntradaSinLog(Subforo f){
        Entrada e = f.EntradaMasVotada();
        System.out.println("La entrada mas votada es:");
        System.out.println("Titulo: " + e.getTitulo() + ". Contenido: " + e.getContenido() + ". Puntuacion: " + e.getPuntuacion());
    }

    public void mostrarEntrada(Entrada entrada){
        if (comprobarLogin()) {
            System.out.println("Mostrando entrada:");
            System.out.println("Titulo: " + entrada.getTitulo() + ". Contenido: " + entrada.getContenido() + ". Puntuacion: " + entrada.getPuntuacion());
        }
    }

    public void mostrarComentarios(Entrada entrada){
        if (comprobarLogin()) {
            System.out.println("Los comentarios de la entrada: " + entrada.getTitulo() + " son: ");
            for (int i = 0; i <= entrada.getComentarios().size()-1; i++) {
                System.out.println(i+1 + ": " + entrada.getComentarios().get(i).getComentario() + " / Puntuacion: " + entrada.getComentarios().get(i).getPuntuacion());
            }
        }
    }

    public void mostrarNotificaciones(Usuario usuario){
        if (comprobarLogin()) {
            usuario.verNotificaciones();
        }
    }
}