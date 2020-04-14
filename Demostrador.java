import java.util.ArrayList;

public class Demostrador {
    public static void main(String[] args) {
        Sistema sistema = new Sistema();
        Foro foro = new Foro();
        Subforo subforo = new Subforo();
        Entrada entrada = new Entrada();

        sistema.registrarUsuario("usr1", "Usuario", "Uno", "contra","usr1@urjc.es");
        if (sistema.hacerLogin("usr1", "contra")){
            System.out.println("Login realizado correctamente");
        } else {
            System.out.println("Error al hacer login");
        };
        subforo.crearSubforo("Titulo de prueba");
        foro.aniadirSubforo(subforo);
        System.out.println("Subforo creado correctamente");
        entrada.crearEntrada("Entrada de prueba", "Este es el contenido de una entrada de prueba en el subforo");
        entrada.comentar("Este es el contenido del comentario de una entrada de prueba en el subforo");
        subforo.aniadirEntrada(entrada);
        System.out.println("Entrada añadida correctamente");
    }
}
