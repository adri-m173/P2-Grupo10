package mp2;

public class Demostrador {
    public static void main(String[] args) {
        Sistema sistema = new Sistema();
        //Subforo subforo = new Subforo();
        //Entrada entrada = new Entrada();

        sistema.registrarUsuario("usr1", "Usuario", "Uno", "contra","usr1@urjc.es");
        if (sistema.hacerLogin("usr1", "contra")){
            System.out.println("Login realizado correctamente");
        } else {
            System.out.println("Error al hacer login");
        };
        sistema.iniciarSubforo();
        sistema.iniciarEntrada();
    }
}
