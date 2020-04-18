import java.util.ArrayList;
import java.util.Scanner;

public class Sistema {
    private boolean sesionIniciada;
    private ArrayList<Usuario> usuarios = new ArrayList<>();
    private Foro foro = new Foro();

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

    public void registrarUsuario(String nick_, String nombre_, String apellidos_, String pass_, String email_) {
        while (!estaDisponible(email_, nick_)) {
            System.out.println("Error. El email o nick utilizado ya esta en uso");
        }
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
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public boolean hacerLogin(String nick_, String pass_) {
        sesionIniciada = false;
        if (usuarios == null || usuarios.size() <= 0) {
            System.out.println("No existen usuarios registrados");
            sesionIniciada = false;
        }
        for (Usuario usr : usuarios) {
            if (usr.getNick().equals(nick_) && usr.getPass().equals(pass_)) {
                sesionIniciada = true;
                System.out.println("Login realizado correctamente");
                System.out.println("Bienvenido, " + usr.getNombre() + " " + usr.getApellidos());
            } else {
                sesionIniciada = false;
            }
        }
        return sesionIniciada;
    }
    public void hacerLogout (){
        sesionIniciada = false;
        System.out.println("Sesión Cerrada Correctamente");
    }
    public boolean comprobarusuario(String nick){
        int j;
        boolean salida;
        salida = false;
        int i = 0;
        boolean encontrado=false;
        for (j=0;j<=usuarios.size();j++){
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
        return salida;
    }

    public Subforo iniciarSubforo(String titulo) {
        Subforo nuevoSubforo = new Subforo(titulo);
        foro.aniadirSubforo(nuevoSubforo);
        System.out.println("Subforo " + "'" + nuevoSubforo.getTitulo() + "'" + " creado correctamente");
        return nuevoSubforo;
    }

    public void subscribirse(Usuario usuario, int numSubforo){
        Subforo subforo = foro.getForo().get(numSubforo);
        subforo.aniadirSubscriptor(usuario);
    }

    public void aniadirASubforo(Entrada nuevaEntrada, int numSubforo) {
        foro.getForo().get(numSubforo).aniadirEntrada(nuevaEntrada);
        foro.getForo().get(numSubforo).notificar();
        System.out.println("Entrada " + "'" + nuevaEntrada.getTitulo() + "'" + " añadida correctamente al subforo: " + foro.getForo().get(numSubforo).getTitulo());
    }
    public Encuesta crearEncuesta(String titulo, String contenido, String r1, String r2, String r3, int numSubforo){
        Encuesta e = new Encuesta(titulo, contenido, r1,r2,r3);
        aniadirASubforo(e, numSubforo);
        return e;
    }
    public TextoPlano crearTextoPlano(String titulo, String contenido, int numSubforo){
        TextoPlano e = new TextoPlano(titulo,contenido);
        aniadirASubforo(e, numSubforo);
        return e;
    }
    public Ejercicio crearEjercicio (String titulo, String enunciado, int numSubforo){
        Ejercicio e = new Ejercicio(titulo,enunciado);
        aniadirASubforo(e, numSubforo);
        return e;
    }
    public TipoMixto CrearTipoMixto(String titulo, String contenido, String r1, String r2, String r3, int numSubforo){
        TipoMixto e = new TipoMixto(titulo,contenido,r1,r2,r3);
        aniadirASubforo(e, numSubforo);
        return e;
    }

    public void comentarEntrada(Entrada entrada, String comentario){
        entrada.comentarEntrada(comentario);
        System.out.println("Comentario realizado en la entrada: " + entrada.getTitulo());
    }

    public void votarEntradaPositivamente(Entrada entrada){
        entrada.votarPositivamente();
        System.out.println("Has votado positivamente la entrada: " + entrada.getTitulo());
    }

    public void votarEntradaNegativamente(Entrada entrada) {
        entrada.votarNegativamente();
        System.out.println("Has votado negativamente la entrada: " + entrada.getTitulo());
    }

    public void votarComentarioPositivamente(Entrada entrada, int numeroComent) {
        entrada.getComentarios().get(numeroComent).votarPositivamente();
        System.out.println("Has votado positivamente el comentario " + numeroComent + " de la entrada: " + entrada.getTitulo());
    }

    public void votarComentarioNegativamente(Entrada entrada, int numeroComent) {
        entrada.getComentarios().get(numeroComent).votarNegativamente();
        System.out.println("Has votado negativamente el comentario " + numeroComent + " de la entrada: " + entrada.getTitulo());
    }
    public void mostrarEntradaSinLog(Subforo f){
        Entrada e = f.EntradaMasVotada();
        mostrarEntrada (e);
    }
    public void mostrarEntrada(Entrada entrada){
        System.out.println("Mostrando entrada:");
        System.out.println("Titulo: " + entrada.getTitulo() + ". Contenido: " + entrada.getContenido() + ". Puntuacion: " + entrada.getPuntuacion());
    }
    public void mostrarComentarios(Entrada entrada){
        System.out.println("Los comentarios de la entrada: " + entrada.getTitulo() + " son: ");
        for (int i = 0; i <= entrada.getComentarios().size(); i++) {
            System.out.println(i+1 + ": " + entrada.getComentarios().get(i).getComentario() + " / Puntuacion: " + entrada.getComentarios().get(i).getPuntuacion());
        }
    }
    public void mostrarNotificaciones(Usuario usuario){
        usuario.verNotificaciones();
    }
}