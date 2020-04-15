import java.util.ArrayList;

public class Demostrador {
    public static void main(String[] args) {
        Sistema sistema = new Sistema();
        Subforo subforo = new Subforo();
        Entrada entrada = new Entrada();

        sistema.registrarUsuario("usr1", "Usuario", "Uno", "contra","usr1@urjc.es");
        if (sistema.hacerLogin("usr1", "contra")){
            System.out.println("Login realizado correctamente");
        } else {
            System.out.println("Error al hacer login");
        };
        subforo.crearSubforo("Titulo de prueba");
        sistema.añadirSubforo(subforo);
        System.out.println("Subforo creado correctamente");
        entrada.crearEntrada("Entrada de prueba", "Este es el contenido de una entrada de prueba en el subforo");
        subforo.añadirEntrada(entrada);
        System.out.println("Entrada añadida correctamente");
    }
}
