import java.util.ArrayList;

public class Sistema {
    private ArrayList<Usuario> usuarios = new ArrayList<>();
    private Entrada entrada = new Entrada();
    private Subforo subforo = new Subforo();
    private Foro foro = new Foro();

    public void registrarUsuario(String nick_, String nombre_, String apellidos_, String pass_, String email_) {
        Usuario nuevo = new Usuario(nick_, nombre_, apellidos_, pass_, email_);
        usuarios.add(nuevo);
        System.out.println("Usuario creado correctamente");
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

    public void iniciarSubforo(String titulo) {
        subforo.crearSubforo(titulo);
        foro.aniadirSubforo(subforo);
        System.out.println("Subforo creado correctamente");
    }

    public void iniciarEntrada(String titulo, String contenido) {
        entrada.crearEntrada(titulo, contenido);
        foro.getForo().get(0).aniadirEntrada(entrada);
        //añadida al unico subforo creado
        System.out.println("Entrada añadida correctamente");
    }

    public void votarEntradaPositivamente(){
        entrada.votarPositivamente();
    }

    public void votarEntradaNegativamente(){
        entrada.votarNegativamente();
    }

    public void comentarEntrada(String comentario){
        entrada.comentar(comentario);
        System.out.println("Comentario realizado");
    }

    public void mostrarEntrada(){
        System.out.println(entrada.toString());
    }
}
