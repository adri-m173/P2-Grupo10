import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Sistema implements Serializable {
    private static Sistema instancia = null;
    private Usuario usuarioConectado = null;
    private static final long serialVersionUID = 1L;
    private boolean sesionIniciada = false;
    private ArrayList<Usuario> usuarios = new ArrayList<>();
    private ArrayList<Subforo> foro = new ArrayList<>();
    private ArrayList<Entrada> EntradasParaRevisar = new ArrayList<>();

    private void aniadirSubforoEnForo(Subforo subforo_) {
        foro.add(subforo_);
    }

    private void aniadirAEntradasParaRevisar(Entrada e) {
        EntradasParaRevisar.add(e);
    }

    private ArrayList<Subforo> getForo() {
        return foro;
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public boolean getSesionIniciada() {
        return sesionIniciada;
    }

    Sistema () {
        //crea la instancia en caso de que no haya sido creada
    }

    public static Sistema getInstance() {
        if (instancia == null) {
            File f = new File("BaseDeDatos.obj");
            if (f.exists()){
                instancia = cargarSistema();
                //el sistema es igual a la carga guardada anteriormente
            } else {
                instancia =  new Sistema();
            }
        }
        return instancia;
    }

    public boolean guardarSistema() {
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

    public static Sistema cargarSistema() {
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

    public void Mostrar() {
        System.out.println(usuarios.toString());
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

    public Administrador registrarAdmin(String nick_, String nombre_, String apellidos_, String pass_, String email_) {
        if (!estaDisponible(email_, nick_)) {
            System.out.println("Error. El email o nick utilizado ya esta en uso");
            return null;
        } else {
            Administrador nuevoAdmin = new Administrador(nick_, nombre_, apellidos_, pass_, email_);
            Scanner sc = new Scanner(email_);
            sc.useDelimiter("@");
            sc.next();
            String s = sc.next();
            if (s.equals("urjc.es") || s.equals("alumnos.urjc.es")) {
                usuarios.add(nuevoAdmin);
                System.out.println("Administrador creado corrrectamente");
            } else {
                System.out.println("El correo introducido no es valido");
            }
            sc.close();
            return nuevoAdmin;
        }
    }

    public Usuario registrarUsuario(String nick_, String nombre_, String apellidos_, String pass_, String email_) {
        if (!estaDisponible(email_, nick_)) {
            System.out.println("Error. El email o nick utilizado ya esta en uso");
            return null;
        } else {
            Usuario nuevoUsuario = new Usuario(nick_, nombre_, apellidos_, pass_, email_);
            Scanner sc = new Scanner(email_);
            sc.useDelimiter("@");
            sc.next();
            String s = sc.next();
            String s1 = "urjc.es";
            String s2 = "alumnos.urjc.es";
            if (s.equals(s1)) {
                usuarios.add(nuevoUsuario);
                System.out.println("Usuario como profesor creado correctamente");
                nuevoUsuario.setProfesor();
            } else if (s.equals(s2)) {
                usuarios.add(nuevoUsuario);
                System.out.println("Usuario como alumno creado correctamente");
            } else {
                System.out.println("El correo introducido no es valido, por favor introduzca una cuenta de la Universidad");
            }
            sc.close();
            return nuevoUsuario;
        }
    }

    public boolean hacerLogin(String nick_, String pass_) {
        if (usuarios == null || usuarios.size() <= 0) {
            System.out.println("No existen usuarios registrados");
        }
        for (Usuario usr : usuarios) {
            if (usr.getNick().equals(nick_) && usr.getPass().equals(pass_)) {
                sesionIniciada = true;
                usuarioConectado = usr;
                System.out.println("Login realizado correctamente");
                System.out.println("Bienvenido, " + usr.getNombre() + " " + usr.getApellidos());
                if (comprobarLogin()) {
                    usr.verNotificaciones();
                }
                break;
            }
        }
        if (!sesionIniciada) {
            System.out.println("Error al hacer login");
        }
        return sesionIniciada;
    }

    public void hacerLogout () {
        sesionIniciada = false;
        usuarioConectado = null;
        System.out.println("Sesion Cerrada Correctamente");
    }

    public Usuario getUsuarioConectado() {
        return usuarioConectado;
    }

    public boolean comprobarusuario(String nick) {
        boolean salida = false;
        int i = 0;
        boolean encontrado=false;
        for (int j=0;j<=usuarios.size();j++) {
            if(!encontrado) {
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
            System.out.println("Tu usuario esta bandeado, por favor, regrese cuando se le levante el castigo");
        }
        return !salida;
    }

    public Subforo iniciarSubforo(Usuario usuario, String titulo) {
        Subforo salida = null;
        if (comprobarLogin()) {
            if (usuario.getEsProfesorAlumno()) {
                Subforo nuevoSubforo = new Subforo(titulo);
                aniadirSubforoEnForo(nuevoSubforo);
                salida = nuevoSubforo;
                System.out.println("Subforo creado correctamente");
            } else {
                System.out.println("Solo los profesores estan autorizados a crear un subforo");
            }
        }
        return salida;
    }

    public void subscribirse(Usuario usuario, int numSubforo) {
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

    public Encuesta crearEncuesta(Usuario autor, String titulo, String contenido, String r1, String r2, String r3, int numSubforo) {
        Encuesta salida = null;
        if (comprobarLogin()) {
            if (autor.getEsProfesorAlumno()) {
                Encuesta e = new Encuesta(autor, titulo, contenido, r1,r2,r3,numSubforo);
                aniadirAEntradasParaRevisar(e);
                salida = e;
                System.out.println("La entrada ha sido creada correctamente, debe ser revisada por el administrador");
            } else {
                System.out.println("Error. Solo los profesores están autorizados a crear encuestas");
            }
        }
        return salida;
    }

    public TextoPlano crearTextoPlano(Usuario autor, String titulo, String contenido, int numSubforo) {
        TextoPlano salida = null;
        if (comprobarLogin()) {
            TextoPlano e = new TextoPlano(autor, titulo,contenido, numSubforo);
            aniadirAEntradasParaRevisar(e);
            salida = e;
            System.out.println("La entrada ha sido creada correctamente, debe ser revisada por el administrador");

        }
        return salida;
    }

    public void validarEntradas(Administrador a) {
        if (EntradasParaRevisar.isEmpty()) {
            System.out.println("No hay entradas para revisasr");
        } else {
            Entrada e = EntradasParaRevisar.get(0);
            a.verificarEntrada(e);
            EntradasParaRevisar.remove(e);
            aniadirASubforo(e,e.getNumSubforo());
        }
    }

    public void vetarEntradas (Administrador a,int dias) {
        if (EntradasParaRevisar.isEmpty()){
            System.out.println("No hay entradas para revisasr");
        } else {
            Entrada e = EntradasParaRevisar.get(0);
            EntradasParaRevisar.remove(e);
            a.banear(e.getAutor(),dias);
            System.out.println("Entrada denegada correctamente. El autor ha sido baneado");
        }
    }

    public Ejercicio crearEjercicio (Usuario autor, String titulo, String enunciado, int numSubforo) {
        Ejercicio salida = null;
        if (comprobarLogin()) {
            if (autor.getEsProfesorAlumno()) {
                Ejercicio e = new Ejercicio(autor, titulo,enunciado,numSubforo);
                aniadirAEntradasParaRevisar(e);
                salida = e;
                System.out.println("La entrada ha sido creada correctamente, debe ser revisada por el administrador");

            } else {
                System.out.println("Error. Solo los profesores estan autorizados a crear ejercicios");
            }
        }
        return salida;
    }

    public TipoMixto CrearTipoMixto(Usuario autor, String titulo, String contenido, String r1, String r2, String r3, int numSubforo) {
        TipoMixto salida = null;
        if (comprobarLogin()) {
            if (autor.getEsProfesorAlumno()) {
                TipoMixto e = new TipoMixto(autor, titulo,contenido,r1,r2,r3,numSubforo);
                aniadirAEntradasParaRevisar(e);
                salida = e;
                System.out.println("La entrada ha sido creada correctamente, debe ser revisada por el administrador");
            } else {
                System.out.println("Error. Solo los profesores estan autorizados a crear entradas mixtas");
            }
        }
        return salida;
    }

    public void comentarEntrada(Entrada entrada, Usuario autor, String comentario) {
        if (comprobarLogin()) {
            entrada.comentarEntrada(autor, comentario);
        }
    }

    public void votarEntradaPositivamente(Entrada entrada, Usuario usuario) {
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

    public void mostrarEntradaSinLog(Subforo f) {
        Entrada e = f.EntradaMasVotada();
        System.out.println("La entrada mas votada es:");
        System.out.println("Titulo: " + e.getTitulo() + ". Contenido: " + e.getContenido() + ". Puntuacion: " + e.getPuntuacion());
    }

    private void mostrarComentarios(Entrada entrada) {
        if (comprobarLogin()) {
            System.out.println("Los comentarios de la entrada: " + entrada.getTitulo() + " son: ");
            for (int i = 0; i <= entrada.getComentarios().size()-1; i++) {
                System.out.println(i+1 + ": " + entrada.getComentarios().get(i).getComentario() + " / Puntuacion: " + entrada.getComentarios().get(i).getPuntuacion());
            }
        }
    }

    public void mostrarEntrada(Entrada entrada) {
        if (comprobarLogin()) {
            if (entrada.getEsVisible()) {
                System.out.println("Mostrando entrada:");
                System.out.println("Titulo: " + entrada.getTitulo() + ". Contenido: " + entrada.getContenido() + ". Puntuacion: " + entrada.getPuntuacion());
                mostrarComentarios(entrada);
            } else {
                System.out.println("Error. La entrada no es visible porque debe ser verificada por un administrador");
            }
        }
    }
}