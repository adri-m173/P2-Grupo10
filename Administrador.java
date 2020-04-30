import java.io.Serializable;

//La clase Administrador hereda de la clase Usuario
public class Administrador extends Usuario implements Serializable {
    private static final long serialVersionUID = 1L;

    public Administrador(String nick_, String n_, String a_, String p_, String e_) {
        super(nick_, n_, a_, p_, e_);
    }
    public void banear (Usuario u){
        u.UsuarioBaneado();
    }
    public void desbanear (Usuario u){
        u.UsuarioDesBaneado();
    }
    public void verificarEntrada (Entrada e) {
        e.hacerVisible();
        System.out.println("Entrada " + e.getTitulo() + " verificada");
    }
}