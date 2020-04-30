import java.io.Serializable;

public class Demostrador implements Serializable {
    private static final long serialVersionUID = 1L;

    public static void main(String[] args) {
        //Si existe un fichero que guarde los datos del sistema, se carga. Si no, se crea un fichero nuevo que guarde el nuevo sistema
        Sistema sistema = Sistema.getInstance(); //Singleton

        //Registramos los diferentes usuarios en el sistema
        Usuario usuario1 = sistema.registrarUsuario("usr1", "Usuario", "Uno", "contra","usr1@urjc.es");
        //Usuario profesor
        Usuario usuario2 = sistema.registrarUsuario("usr2", "Usuario", "Dos", "contra", "usr2@alumnos.urjc.es");
        //Usuario alumno
        Usuario usuario5 = sistema.registrarUsuario("usr5", "Usuario", "Cinco", "contra", "usr5@alumnos.urjc.es");
        //Usuario alumno2
        Usuario admin = sistema.registrarAdmin("usr3", "Administrador", "Uno", "contra", "usr3@urjc.es");
        //Usuario administrador
        Usuario usuario3 = sistema.registrarUsuario("usr2", "Usuario", "Tres", "contra", "usr2@alumnos.uc3m.es");
        //Cuenta de correo no valida
        Usuario usuario4 = sistema.registrarUsuario("usr2", "Usuario", "Cuatro", "contra", "usr2@alumnos.urjc.es");
        //Cuenta de correo en uso

        if (sistema.hacerLogin("usr1", "contra", usuario1)) { //Si se hace login correctamente, entonces:
            if (sistema.comprobarusuario("usr1")) { //Se comprueba si el usuario está baneado
                Subforo subforo1 = sistema.iniciarSubforo(sistema.getUsuarioConectado(),"Subforo 1");
                Subforo subforo2 = sistema.iniciarSubforo(sistema.getUsuarioConectado(), "Subforo 2");
                //Los profesores pueden crear subforos sin ser verificados por el administrador

                sistema.subscribirse(sistema.getUsuarioConectado(), 1); //El usuario conectado se subscribe al Subforo 2

                TextoPlano entrada1 = sistema.crearTextoPlano(sistema.getUsuarioConectado(), "Entrada 1", "Contenido de la entrada 1", 0);
                TextoPlano entrada2 = sistema.crearTextoPlano(sistema.getUsuarioConectado(), "Entrada 2", "Contenido de la entrada 2", 1);
                Encuesta entrada3 = sistema.crearEncuesta(sistema.getUsuarioConectado(), "Pregunta Seria", "Los usuarios deben intentar aprobar?", "Si", "No", "Tal vez", 0);
                //El usuario crea diferentes entradas y quedan pendientes de verificacion por el administrador

                sistema.mostrarEntrada(entrada1);
                //La entrada debe ser verificada por un administrador

                sistema.hacerLogout(); //El usuario 1 hace logout en el sistema

                if (sistema.hacerLogin("usr3", "contra",usuario3)) { //El administrador del sistema hace login en el mismo. Si lo hace correctamente, entonces:
                    //No se comprueba si el administrador está baneado
                    sistema.validarEntradas((Administrador) sistema.getUsuarioConectado());
                    sistema.validarEntradas((Administrador) sistema.getUsuarioConectado());
                    sistema.validarEntradas((Administrador) sistema.getUsuarioConectado());
                    sistema.validarEntradas((Administrador) sistema.getUsuarioConectado());
                    //Administrador valida las entradas las entradas una por una
                    sistema.hacerLogout(); //El administrador hace logout en el sistema
                }

                if (sistema.hacerLogin("usr5", "contra",usuario5)) {
                    if (sistema.comprobarusuario("usr5")) {
                        TextoPlano entradaNA = sistema.crearTextoPlano(sistema.getUsuarioConectado(), "Entrada No Apropiada", "Contenido de la entrada 1", 0);
                        //Esta entrada creada será vetada por el administrador posteriormente
                        sistema.hacerLogout();
                    }
                }

                if (sistema.hacerLogin("usr3", "contra",usuario3)) { //El administrador vuelve a hacer login en el sistema
                    sistema.vetarEntradas((Administrador) sistema.getUsuarioConectado(),3);
                    //El administrador puede vetar entradas, y al hacerlo su autor queda baneado automáticamente
                    sistema.hacerLogout();
                }

                if (sistema.hacerLogin("usr5", "contra",usuario5)) {
                    if (sistema.comprobarusuario("usr5")) {
                        TextoPlano entradaNA2 = sistema.crearTextoPlano(sistema.getUsuarioConectado(), "Entrada Que no será visible", "Contenido de la entrada 1", 0);
                        //El usuario no puede hacer nada ya que está baneado por crear una entrada que ha sido vetada
                    }
                    
                    usuario5.avanzarDias(3);
                    //avanzan los dias y el usuario deja de estar baneado
                    
                    if (sistema.comprobarusuario("usr5")) {
                        TextoPlano entradaNA2 = sistema.crearTextoPlano(sistema.getUsuarioConectado(), "Entrada que comprueba baneos", "enesimo contenido", 0);
                    //el usuario deja de estar baneado, por tanto, puede volver utilizar el sistema.
                    }
                    sistema.hacerLogout();
                }
                
                if (sistema.hacerLogin("usr1", "contra",usuario1)) {
                    if (sistema.comprobarusuario("usr1")) {
                        sistema.darseBaja(sistema.getUsuarioConectado(), 1); //El usuario se da de baja del Subforo 2
                        sistema.comentarEntrada(entrada1, sistema.getUsuarioConectado(), "Esto es un comentario para la entrada 1");
                        //El usuario hace un comentario en su propia entrada
                        sistema.votarEntradaPositivamente(entrada1, usuario1);
                        //Sin embargo, un usuario no puede votar su propia entrada
                        sistema.hacerLogout();
                    }
                }

                // *********USUARIO NO CONECTADO**********

                sistema.crearTextoPlano(sistema.getUsuarioConectado(), "Ejercicio1", "Enunciado del ejercicio", 0);
                sistema.crearEncuesta(sistema.getUsuarioConectado(), "Pregunta Seria", "Los usuarios deben intentar aprobar?", "Si", "No", "Tal vez", 0);
                sistema.crearEjercicio(sistema.getUsuarioConectado(), "Ejercicio1", "Enunciado del ejercicio", 0);
                //Usuarios no registrados no pueden crear entradas


                sistema.comentarEntrada(entrada2, sistema.getUsuarioConectado(), "Comentario de prueba");
                //Usuarios no registrados no pueden comentar

                sistema.votarEntradaPositivamente(entrada2, sistema.getUsuarioConectado());
                sistema.mostrarEntradaSinLog(subforo1);
                //Usuario no registrado puede ver las entradas más votadas

                // *********FIN USUARIO NO CONECTADO**********

                if (sistema.hacerLogin("usr2", "contra",usuario2)) {
                    if (sistema.comprobarusuario("usr2")) {
                        Subforo subforo3 = sistema.iniciarSubforo(usuario2, "Subforo 3");
                        //Los usuarios que no son profesores no pueden crear subforos

                        Encuesta entrada4 = sistema.crearEncuesta(sistema.getUsuarioConectado(), "Pregunta Seria", "Los usuarios deben intentar aprobar?", "Sí", "No", "", 0);
                        //Solo los profesores son capaces de crear encuestas

                        TipoMixto entradafinal1 = sistema.CrearTipoMixto(sistema.getUsuarioConectado(), "TipoMixto que no va a funcionar", "Cotnenido", "Si", "No", "No va a salir", 1);
                        //Solo los profesores son capaces de crear Tipos Mixtos

                        Ejercicio entradafinal2 = sistema.crearEjercicio(sistema.getUsuarioConectado(), "ejercicio que no va a salir", "enunciado", 1);
                        //Solo los profesores son capaces de crear ejercicios

                        sistema.comentarEntrada(entrada1, sistema.getUsuarioConectado(), "Esto es un comentario para la entrada 1");
                        sistema.comentarEntrada(entrada2, sistema.getUsuarioConectado(), "Esto es otro comentario para la entrada 2");
                        //Los usuarios pueden comentar las entradas que quieran

                        //--Demostración del sistema de votaciones--
                        sistema.votarEntradaPositivamente(entrada1, sistema.getUsuarioConectado());
                        sistema.votarEntradaPositivamente(entrada1, sistema.getUsuarioConectado());
                        //No se puede votar una entrada positivamente más de una vez
                        sistema.votarEntradaNegativamente(entrada1, sistema.getUsuarioConectado());
                        //Se puede votar positiva y negativamente cualquier entrada, solapandose los votos
                        sistema.votarEntradaNegativamente(entrada1, sistema.getUsuarioConectado());
                        //No se puede votar una entrada negativamente más de una vez
                        sistema.votarEntradaNegativamente(entrada3, sistema.getUsuarioConectado());
                        //Los comentarios también pueden ser votados individualmente
                        sistema.votarComentarioPositivamente(entrada1, sistema.getUsuarioConectado(), 0);
                        sistema.votarComentarioNegativamente(entrada2, sistema.getUsuarioConectado(), 0);

                        //--Se muestran las entradas conjuntamente con sus comentarios y valoraciones
                        sistema.mostrarEntrada(entrada1);
                        sistema.mostrarEntrada(entrada2);
                        sistema.mostrarEntrada(entrada3);

                        sistema.hacerLogout();
                    }
                }
            }
        }

        //El sistema es guardado en un fichero para garantizar la persistencia de los datos
        if (sistema.guardarSistema()) {
            System.out.println("Sistema guardado correctamente");
        }
    }
}