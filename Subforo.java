import java.io.Serializable;
import java.util.ArrayList;

public class Subforo implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String titulo;
    private ArrayList <Entrada> entradas = new ArrayList<>();
    private ArrayList <Usuario> usuariosSubscritos = new ArrayList<>();

    public Subforo(String titulo_) {
        this.titulo = titulo_;
    }

    public Entrada EntradaMasVotada() {
        int i;
        Entrada e = entradas.get(0);
        for (i=0;i<entradas.size();i++) {
        //se comprueban todas hasta encontrar la mas votada
            Entrada e2 = entradas.get(i);
            if (e.getPuntuacion() < e2.getPuntuacion()) {
                e = e2;
            }
        }
        return e;
    }
    
    public void aniadirEntrada(Entrada entrada_) {
        entradas.add(entrada_);
    }

    public void aniadirSubscriptor(Usuario subs) {
        usuariosSubscritos.add(subs);
        System.out.println("El usuario " + subs.getNick() + " se ha dado de alta en el subforo " + getTitulo());
    }

    public void eliminarSubscriptor(Usuario subs) {
        usuariosSubscritos.remove(subs);
        System.out.println("El usuario " + subs.getNick() + " se ha dado de baja en el subforo " + getTitulo());
    }

    public void notificar() {
        String notificacion = "Una nueva entrada ha sido anadida en el subforo:" + titulo;
        for (Usuario x: usuariosSubscritos) {
            x.recibirNotificacion(notificacion);
        }
    }

    public String getTitulo() {
        return titulo;
    }

    public ArrayList<Usuario> getUsuariosSubscritos() {
        return usuariosSubscritos;
    }
    
    public ArrayList<Entrada> getEntradas() {
        return entradas;
    }
}