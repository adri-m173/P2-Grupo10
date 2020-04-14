import java.util.ArrayList;

public class Subforo {
    private String titulo;
    private ArrayList <Entrada> entradas = new ArrayList<>();
    
    public void crearSubforo(String titulo_){
        this.titulo=titulo_;
    }
    public void añadirEntrada(Entrada entrada_){
        entradas.add(entrada_);
    }

    public ArrayList<Entrada> getEntradas() {
        return entradas;
    }
    

    
}
