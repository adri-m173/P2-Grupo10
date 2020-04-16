package practicamp2;
public class Demostrador {
    public static void main(String[] args) {
        Sistema sistema = new Sistema();

        sistema.registrarUsuario("usr1", "Usuario", "Uno", "contra","usr1@urjc.es");
        if (sistema.hacerLogin("usr1", "contra")){
            if (!sistema.comprobarusuario("usr1")){
                System.out.println("Login realizado correctamente");
                Subforo subforo1 = sistema.iniciarSubforo("Titulo del subforo");
                Entrada entrada1 = sistema.iniciarEntrada("Titulo de entrada de prueba", "Contenido de la entrada de prueba");
                sistema.votarEntradaPositivamente(entrada1);
                sistema.votarEntradaPositivamente(entrada1);
                sistema.votarEntradaNegativamente(entrada1);
                sistema.comentarEntrada(entrada1, "Esto es un comentario");
                sistema.mostrarEntrada(entrada1);
            }
            else{
                System.out.println("El usuario ha sido baneado");
            }
        } else {
            System.out.println("Error al hacer login");
        }
    }
}