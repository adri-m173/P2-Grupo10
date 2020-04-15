package practicamp2;

public class Administrador extends Usuario {
    
    public Administrador(String nick_, String n_, String a_, String p_, String e_) {
        super(nick_, n_, a_, p_, e_);
    }
    public void banear (Usuario u){
        u.UsuarioBaneado();
    }
    public void desbanear (Usuario u){
        u.UsuarioDesBaneado();
    }
}
