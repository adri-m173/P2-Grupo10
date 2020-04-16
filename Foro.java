import java.util.ArrayList;

public class Foro {
    private ArrayList<Subforo> foro = new ArrayList<>();

    public void aniadirSubforo(Subforo subforo_) {
        foro.add(subforo_);
    }

    public ArrayList<Subforo> getForo() {
        return foro;
    }
}
