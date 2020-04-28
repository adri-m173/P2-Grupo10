package practicamp2;
import java.io.Serializable;

public class Demostrador implements Serializable {
    private static final long serialVersionUID = 1L;

    public static void main(String[] args) {
        Sistema sistema = Sistema.getInstance();
        
        Usuario usuario1 = sistema.registrarUsuario("usr1", "Usuario", "Uno", "contra","usr1@urjc.es");
        //usuario profesor
        Usuario usuario2 = sistema.registrarUsuario("usr2", "Usuario", "Dos", "contra", "usr2@alumnos.urjc.es");
        //usuario alumno
        Usuario usuario5 = sistema.registrarUsuario("usr5", "Usuario", "Cinco", "contra", "usr5@alumnos.urjc.es");
        //usuario alumno2
        Usuario admin = sistema.registrarAdmin("usr3", "Administrador", "Uno", "contra", "usr3@urjc.es");
        //usuario administrador
        Usuario usuario3 = sistema.registrarUsuario("usr2", "Usuario", "Tres", "contra", "usr2@alumnos.uc3m.es");
        //cuenta de correo no valida
        Usuario usuario4 = sistema.registrarUsuario("usr2", "Usuario", "Cuatro", "contra", "usr2@alumnos.urjc.es");
        //cuenta de correo en uso
        
        if (sistema.hacerLogin("usr1", "contra")){
            if (sistema.comprobarusuario("usr1")){
                Subforo subforo1 = sistema.iniciarSubforo(sistema.getUsuarioConectado(),"Subforo 1");
                Subforo subforo2 = sistema.iniciarSubforo(sistema.getUsuarioConectado(), "Subforo 2");
                //los profesores pueden crear foros sin ser verificados por el administrador
                
                
                
                sistema.subscribirse(sistema.getUsuarioConectado(), 1);

                TextoPlano entrada1 = sistema.crearTextoPlano(sistema.getUsuarioConectado(), "Entrada 1", "Contenido de la entrada 1", 0);
                TextoPlano entrada2 = sistema.crearTextoPlano(sistema.getUsuarioConectado(), "Entrada 2", "Contenido de la entrada 2", 1);
                Encuesta entrada3 = sistema.crearEncuesta(sistema.getUsuarioConectado(), "Pregunta Seria", "Los usuarios deben intentar aprobar?", "Si", "No", "Tal vez", 0);
                
                sistema.mostrarEntrada(entrada1);
                //la entrada debe ser verificada por un administrador
                
                sistema.hacerLogout();
        
        if (sistema.hacerLogin("usr3", "contra")){
            sistema.validarEntradas((Administrador) sistema.getUsuarioConectado());
            sistema.validarEntradas((Administrador) sistema.getUsuarioConectado());
            sistema.validarEntradas((Administrador) sistema.getUsuarioConectado());
            sistema.validarEntradas((Administrador) sistema.getUsuarioConectado());
            sistema.hacerLogout();
            //administrador valida las entradas las entradas
        }
        if (sistema.hacerLogin("usr5", "contra")){
            if (sistema.comprobarusuario("usr5")){
                TextoPlano entradaNA = sistema.crearTextoPlano(sistema.getUsuarioConectado(), "Entrada No Apropiada", "Contenido de la entrada 1", 0);
                sistema.hacerLogout();
            }
        }
        
        if (sistema.hacerLogin("usr3", "contra")){
            sistema.vetarEntradas((Administrador) sistema.getUsuarioConectado());
            //el administrador puede vetar entradas
            sistema.hacerLogout();
        }
        
        if (sistema.hacerLogin("usr5", "contra")){
            if (sistema.comprobarusuario("usr5")){
                TextoPlano entradaNA2 = sistema.crearTextoPlano(sistema.getUsuarioConectado(), "Entrada Que no será visible", "Contenido de la entrada 1", 0);
                //el usuario no puede hacer nada ya que está baneado
            }
            sistema.hacerLogout();
        }
        if (sistema.hacerLogin("usr1", "contra")){
            if (sistema.comprobarusuario("usr1")){
                sistema.mostrarNotificaciones(sistema.getUsuarioConectado());
                sistema.darseBaja(sistema.getUsuarioConectado(), 1);
                sistema.comentarEntrada(entrada1, sistema.getUsuarioConectado(), "Esto es un comentario para la entrada 1");
                
                sistema.votarEntradaPositivamente(entrada1, usuario1);
                //un usuario no puede votar su propia entrada
                
                sistema.hacerLogout();
            }
        }     
        // *********USUARIO NO CONECTADO**********
                sistema.crearTextoPlano(sistema.getUsuarioConectado(), "Ejercicio1", "Enunciado del ejercicio", 0);
                sistema.crearEncuesta(sistema.getUsuarioConectado(), "Pregunta Seria", "Los usuarios deben intentar aprobar?", "Si", "No", "Tal vez", 0);
                sistema.crearEjercicio(sistema.getUsuarioConectado(), "Ejercicio1", "Enunciado del ejercicio", 0);
                //usuarios no registrados no pueden crear entradas
                
                
                sistema.comentarEntrada(entrada2, sistema.getUsuarioConectado(), "Comentario de prueba");
                //usuarios no registrados no pueden comentar
                
                sistema.votarEntradaPositivamente(entrada2, sistema.getUsuarioConectado());
                sistema.mostrarEntradaSinLog(subforo1);
                //un usuario no logeado puede ver las entradas mas votadas
                
        // *********FIN USUARIO NO CONECTADO**********
                
                if (sistema.hacerLogin("usr2", "contra")){
                    if (sistema.comprobarusuario("usr2")){
                        Subforo subforo3 = sistema.iniciarSubforo(usuario2, "Subforo 3");
                        //los usuarios que no son profesores no pueden crear subforos
                
                        Encuesta entrada4 = sistema.crearEncuesta(sistema.getUsuarioConectado(), "Pregunta Seria", "Los usuarios deben intentar aprobar?", "Sí", "No", "", 0);
                        //solo los profesores son capaces de crear encuestas
                        
                        TipoMixto entradafinal1 = sistema.CrearTipoMixto(sistema.getUsuarioConectado(), "TipoMixto que no va a funcionar", "Cotnenido", "Si", "No", "No va a salir", 1);
                        //solo los profesores son capaces de crear Tipos Mixtos
                        
                        Ejercicio entradafinal2 = sistema.crearEjercicio(sistema.getUsuarioConectado(), "ejercicio que no va a salir", "enunciado", 1);
                        //solo los profesores son capaces de crear ejercicios
                        
                        sistema.comentarEntrada(entrada1, sistema.getUsuarioConectado(), "Esto es un comentario para la entrada 1");
                        sistema.comentarEntrada(entrada2, sistema.getUsuarioConectado(), "Esto es otro comentario para la entrada 2");
                        
                        sistema.votarEntradaPositivamente(entrada1, sistema.getUsuarioConectado());
                        sistema.votarEntradaPositivamente(entrada1, sistema.getUsuarioConectado());
                        //no se puede votar dos veces con el mismo valor positivo
                        
                        sistema.votarEntradaNegativamente(entrada1, sistema.getUsuarioConectado());
                        //se puede votar positiva y negativamente cualquier entrada, solapandose los votos
                        
                        sistema.votarEntradaNegativamente(entrada1, sistema.getUsuarioConectado());
                        //no se puede votar dos veces con el mismo valor negativo
                        
                        sistema.votarEntradaNegativamente(entrada3, sistema.getUsuarioConectado());
                        sistema.votarComentarioPositivamente(entrada1, sistema.getUsuarioConectado(), 0);
                        sistema.votarComentarioNegativamente(entrada2, sistema.getUsuarioConectado(), 0);
                        sistema.mostrarEntrada(entrada1);
                        sistema.mostrarEntrada(entrada2);
                        sistema.mostrarEntrada(entrada3);
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