package practicamp2;
import java.util.ArrayList;

public class Foro {
    private ArrayList<Subforo> foro = new ArrayList<>();

    public void aniadirSubforo(Subforo subforo_) {
        foro.add(subforo_);
        System.out.println("Subforo " + "'" + subforo_.getTitulo() + "'" + " creado correctamente");
    }

    public ArrayList<Subforo> getForo() {
        return foro;
    }
}