import java.util.ArrayList;

public class Subforo {
    private String titulo;
    private ArrayList <Entrada> entradas = new ArrayList<>();
    
    public void crearSubforo(String titulo_){
        this.titulo = titulo_;
    }

    public void aniadirEntrada(Entrada entrada_){
        entradas.add(entrada_);
    }

    public String getTitulo(){
        return titulo;
    }

    public ArrayList<Entrada> getEntradas() {
        return entradas;
    }
    

    
}
