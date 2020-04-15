package mp2;
import java.io.Serializable;
import java.util.ArrayList;

public class Sistema implements Serializable {
    private ArrayList<Usuario> usuarios = new ArrayList<>();
    private ArrayList<Subforo> foro = new ArrayList<>();

    public void registrarUsuario(String nick_, String nombre_, String apellidos_, String pass_, String email_){
        Usuario nuevo = new Usuario(nick_, nombre_, apellidos_, pass_, email_);
        usuarios.add(nuevo);
        System.out.println("Usuario creado correctamente");
    }
    public void iniciarSubforo(){
        Subforo subforo = new Subforo();
        subforo.crearSubforo("Titulo de prueba");
        añadirSubforo(subforo);
        System.out.println("Subforo creado correctamente");
    }
    public void iniciarEntrada(){
        Entrada entrada = new Entrada();
        entrada.crearEntrada("Entrada de prueba", "Este es el contenido de una entrada de prueba en el subforo");
        foro.get(0).añadirEntrada(entrada);
            //añadido al unico subforo creado
        System.out.println("Entrada añadida correctamente");
    }
    public boolean hacerLogin(String nick_, String pass_){
        boolean salida = false;
        if (usuarios == null || usuarios.size()<=0){
            System.out.println("No existen usuarios registrados");
            salida = false;
        }
        for (Usuario usr: usuarios) {
            if (usr.getNick().equals(nick_) && usr.getPass().equals(pass_)) {
                salida = true;
            } else {
                salida = false;
            }
        }
        return salida;
    }
    
    public void añadirSubforo(Subforo subforo_){
        foro.add(subforo_);
    }

    public ArrayList<Subforo> getForo() {
        return foro;
    }


    
    
}