package practicamp2;
import java.util.ArrayList;

public class Subforo {
    private String titulo;
    private ArrayList <Entrada> entradas = new ArrayList<>();
    private ArrayList <Usuario> usuariosSubscritos = new ArrayList<>();

    public Subforo(String titulo_){
        this.titulo=titulo_;
    }

    public String getTitulo() {
        return titulo;
    }
    public Entrada EntradaMasVotada(){
        int i;
        Entrada e = entradas.get(0);
        for (i=0;i<entradas.size();i++){
            Entrada e2 = entradas.get(i);
            if (e.getPuntuacion() < e2.getPuntuacion()){
                e = e2;
            }
        }
        return e;
    }
    public void aniadirEntrada(Entrada entrada_){
        entradas.add(entrada_);
    }

    public ArrayList<Entrada> getEntradas() {
        return entradas;
    }

    public void aniadirSubscriptor(Usuario subs){
        usuariosSubscritos.add(subs);
    }

    public void eliminarSubscriptor(Usuario subs){
        usuariosSubscritos.remove(subs);
    }

    public void notificar(){
        String notificacion = "Una nueva entrada ha sido anadida en el subforo:" + titulo;
        for (Usuario x: usuariosSubscritos){
            x.recibirNotificacion(notificacion);
        }
    }
}
