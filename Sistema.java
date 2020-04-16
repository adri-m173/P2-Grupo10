package practicamp2;

import java.util.ArrayList;
import java.util.Scanner;

public class Sistema {
    private ArrayList<Usuario> usuarios = new ArrayList<>();
    private Foro foro = new Foro();
    private String s1 = "urjc.es";
    private String s2 = "alumnos.urjc.es";

    public void registrarUsuario(String nick_, String nombre_, String apellidos_, String pass_, String email_) {
        Usuario nuevo = new Usuario(nick_, nombre_, apellidos_, pass_, email_);
        Scanner sc = new Scanner(email_);
        sc.useDelimiter("@");
        sc.next();
        String s = sc.next();
        System.out.println(s);
        if (s.equals(s1)) {
            usuarios.add(nuevo);
            System.out.println("Usuario como profesor creado correctamente");
        }
        else if (s.equals(s2)) {
            usuarios.add(nuevo);
            System.out.println("Usuario como alumno creado correctamente");
        }
        else {
            System.out.println("El correo introducido no es valido");
        }
    }

    public boolean hacerLogin(String nick_, String pass_) {
        boolean salida = false;
        if (usuarios == null || usuarios.size() <= 0) {
            System.out.println("No existen usuarios registrados");
            salida = false;
        }
        for (Usuario usr : usuarios) {
            if (usr.getNick().equals(nick_) && usr.getPass().equals(pass_)) {
                salida = true;
            } else {
                salida = false;
            }
        }
        return salida;
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
        System.out.println("Subforo creado correctamente");
        return nuevoSubforo;
    }

    public Entrada iniciarEntrada(String titulo, String contenido) {
        Entrada nuevaEntrada = new Entrada(titulo, contenido);
        //TODO HAY QUE PODER A�ADIR LA ENTRADA A CUALQUIER SUBFORO
        foro.getForo().get(0).aniadirEntrada(nuevaEntrada);
        //añadida al unico subforo creado
        System.out.println("Entrada añadida correctamente");
        return nuevaEntrada;
    }

    public void votarEntradaPositivamente(Entrada entrada){
        entrada.votarPositivamente();
        System.out.println("Has votado positivamente la entrada");
    }

    public void votarEntradaNegativamente(Entrada entrada){
        entrada.votarNegativamente();
        System.out.println("Has votado negativamente la entrada");
    }

    public void comentarEntrada(Entrada entrada, String comentario){
        entrada.comentar(comentario);
        System.out.println("Comentario realizado");
    }

    public void mostrarEntrada(Entrada entrada){
        System.out.println(entrada.toString());
    }
}