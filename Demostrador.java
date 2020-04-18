package practicamp2;
public class Demostrador {
    public static void main(String[] args) {
        Sistema sistema = new Sistema();
        //usuario1 = sistema.registrarUsuario(...)
        sistema.registrarUsuario("usr1", "Usuario", "Uno", "contra","usr1@urjc.es");
        if (sistema.hacerLogin("usr1", "contra")){
            if (!sistema.comprobarusuario("usr1")){
                System.out.println("Login realizado correctamente");
                Subforo subforo1 = sistema.iniciarSubforo("Subforo 1");
                Subforo subforo2 = sistema.iniciarSubforo("Subforo 2");
                
                //sistema.subscribirse(usuario1, 0)
                
                TextoPlano entrada1 = sistema.crearTextoPlano("Entrada 1", "Contenido de la entrada 1", 0);
                TextoPlano entrada2 = sistema.crearTextoPlano("Entrada 2", "Contenido de la entrada 2", 1);
                Encuesta entrada3 = sistema.crearEncuesta("Pregunta Seria", "Los usuarios deben intentar aprobar?", "Sí", "No", "", 0);
                
                //sistema.verNotificaciones(usuario1)
                
                sistema.votarEntradaPositivamente(entrada1);
                sistema.votarEntradaNegativamente(entrada2);
                sistema.hacerLogout();
                sistema.mostrarEntradaSinLog(subforo2);
                if (sistema.hacerLogin("usr1", "contra")){
                    System.out.println("Login realizado correctamente");
                    sistema.comentarEntrada(entrada1, "Esto es un comentario para la entrada 1");
                    sistema.comentarEntrada(entrada1, "Esto es otro comentario para la entrada 1");
                    sistema.comentarEntrada(entrada2, "Esto es un comentario para la entrada 2");
                    sistema.votarComentarioPositivamente(entrada1, 0);
                    sistema.votarComentarioNegativamente(entrada2, 0);
                    sistema.mostrarEntrada(entrada1);
                    sistema.mostrarComentarios(entrada1);
                }
            }
            else{
                System.out.println("El usuario ha sido baneado");
            }
        } else {
            System.out.println("Error al hacer login");
        }
    }
}