public class Demostrador {
    public static void main(String[] args) {
        Sistema sistema = new Sistema();

        sistema.registrarUsuario("usr1", "Usuario", "Uno", "contra","usr1@urjc.es");
        if (sistema.hacerLogin("usr1", "contra")){
            System.out.println("Login realizado correctamente");
            sistema.iniciarSubforo("Titulo del subforo");
            sistema.iniciarEntrada("Titulo de entrada de prueba", "Contenido de la entrada de prueba");
            sistema.votarEntradaPositivamente();
            sistema.votarEntradaPositivamente();
            sistema.votarEntradaNegativamente();
            sistema.comentarEntrada("Esto es un comentario");
            sistema.mostrarEntrada();
        } else {
            System.out.println("Error al hacer login");
        };
    }
}