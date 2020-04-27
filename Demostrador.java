import java.io.Serializable;

public class Demostrador implements Serializable {
    private static final long serialVersionUID = 1L;

    public static void main(String[] args) {
        Sistema sistema = Sistema.getInstance();
        Usuario usuario1 = sistema.registrarUsuario("usr1", "Usuario", "Uno", "contra","usr1@urjc.es");
        Usuario usuario2 = sistema.registrarUsuario("usr2", "Usuario", "Dos", "contra", "usr2@alumnos.urjc.es");
        Usuario admin = sistema.registrarAdmin("usr3", "Administrador", "Uno", "contra", "usr3@urjc.es");
        //Usuario usuario1 = sistema.getUsuarios().get(0);
        //Usuario usuario2 = sistema.getUsuarios().get(1);
        //Usuario admin = sistema.getUsuarios().get(2);
        if (sistema.hacerLogin("usr1", "contra")){
            if (sistema.comprobarusuario("usr1")){
                Subforo subforo1 = sistema.iniciarSubforo(usuario1,"Subforo 1");
                Subforo subforo2 = sistema.iniciarSubforo(usuario1, "Subforo 2");
                Subforo subforo3 = sistema.iniciarSubforo(usuario2, "Subforo 3");

                sistema.subscribirse(usuario1, 1);

                TextoPlano entrada1 = sistema.crearTextoPlano(usuario1, "Entrada 1", "Contenido de la entrada 1", 0);
                TextoPlano entrada2 = sistema.crearTextoPlano(usuario1, "Entrada 2", "Contenido de la entrada 2", 1);
                Encuesta entrada3 = sistema.crearEncuesta(usuario1, "Pregunta Seria", "Los usuarios deben intentar aprobar?", "Si", "No", "Tal vez", 0);

                sistema.mostrarNotificaciones(usuario1);

                sistema.darseBaja(usuario1, 1);


                sistema.comentarEntrada(entrada1, usuario1, "Esto es un comentario para la entrada 1");
                sistema.votarEntradaPositivamente(entrada1, usuario1);
                sistema.hacerLogout();
                sistema.crearEjercicio(usuario1, "Ejercicio1", "Enunciado del ejercicio", 0);
                sistema.comentarEntrada(entrada2, usuario2, "Comentario de prueba");
                sistema.mostrarEntradaSinLog(subforo1);
                if (sistema.hacerLogin("usr2", "contra")){
                    if (sistema.comprobarusuario("usr2")) {
                        Encuesta entrada4 = sistema.crearEncuesta(usuario2, "Pregunta Seria", "Los usuarios deben intentar aprobar?", "Sí", "No", "", 0);
                        sistema.comentarEntrada(entrada1, usuario2, "Esto es otro comentario para la entrada 1");
                        sistema.comentarEntrada(entrada2, usuario2, "Esto es un comentario para la entrada 2");
                        sistema.votarEntradaPositivamente(entrada1, usuario2);
                        sistema.votarEntradaNegativamente(entrada3, usuario2);
                        sistema.votarComentarioPositivamente(entrada1, usuario2, 0);
                        sistema.votarComentarioNegativamente(entrada2, usuario2, 0);
                        sistema.mostrarEntrada(entrada1);
                        sistema.verificarEntrada((Administrador) admin, entrada1);
                        sistema.mostrarEntrada(entrada1);
                        sistema.hacerLogout();
                    }
                }
            }
        }
        if (sistema.guardarSistema()){
            System.out.println("Sistema guardado correctamente");
        }
    }
}