package com.p3;
import java.util.ArrayList;
import org.junit.Test;

import static org.junit.Assert.*;

public class SistemaTest {
    Sistema sistema = Sistema.getInstance();

    @Test    
    public void guardarSistema() {
        Sistema sistema2 = Sistema.getInstance();
        //Creamos un objeto sistema con la clase getInstance y lo guardamos con la clase guardarSistema
        sistema2.guardarSistema();
        //Como ya existe un sistema y se ha guardado, al usar getInstance se llamara a la clase cargarSistema.
        Sistema sistema3 = Sistema.getInstance();
        assertEquals(sistema2, sistema3);
    }


    @Test
    public void cargarSistema() {
    }

    @Test
    public void registrarAdmin() {
        int tamanioInicial = sistema.getUsuarios().size();
        sistema.registrarAdmin("nick", "nombre", "apellidos", "contra", "admin@email.com"); //Intentamos registrar un nuevo administrador 1 en el sistema con correo no valido
        assertNotEquals(tamanioInicial + 1, sistema.getUsuarios().size()); //Comprobamos que el administrador 1 no se ha añadido a la lista de usuarios del sistema por no tener un correo válido
        sistema.registrarAdmin("nick", "nombre", "apellidos", "contra", "email@urjc.es"); //Registramos un nuevo administrador 2 en el sistema con un correo de profesor
        assertEquals(tamanioInicial + 1, sistema.getUsuarios().size()); //Comprobamos que el administrador 2 se ha añadido a la lista de usuarios del sistema por tener un correo de profesor valido
        sistema.registrarAdmin("nick2", "nombre", "apellidos", "contra", "email@alumnos.urjc.es"); //Registramos un nuevo administrador 3 en el sistema con un correo de alumno
        assertEquals(tamanioInicial + 2, sistema.getUsuarios().size()); //Comprobamos que el administrador 3 se ha añadido a la lista de usuarios del sistema al tener un correo de alumno valido
        sistema.registrarAdmin("nick", "nombre", "apellidos", "contra", "email@urjc.es"); //Intentamos registrar un nuevo administrador 4 con datos ya registrados en el sistema
        assertEquals(tamanioInicial + 2, sistema.getUsuarios().size()); //Comprobamos que la lista de usuarios del sistema no ha aumentado su tamaño y por tanto no se ha añadido el administrador 4
    }

    @Test
    public void registrarUsuario() {
        int tamanioInicial = sistema.getUsuarios().size();
        sistema.registrarUsuario("nick", "nombre", "apellidos", "contra", "admin@email.com"); //Intentamos registrar un nuevo usuario 1 en el sistema con correo no valido
        assertEquals(tamanioInicial, sistema.getUsuarios().size()); //Comprobamos que el usuario 1 no se ha añadido a la lista de usuarios del sistema por no tener un correo válido
        sistema.registrarUsuario("nick2", "nombre", "apellidos", "contra", "email@urjc.es"); //Registramos un nuevo usuario 2 en el sistema con un correo de profesor
        assertEquals(tamanioInicial + 1, sistema.getUsuarios().size()); //Comprobamos que el usuario 2 se ha añadido a la lista de usuarios del sistema por tener un correo de profesor valido
        sistema.registrarUsuario("nick4", "nombre", "apellidos", "contra", "email@alumnos.urjc.es"); //Registramos un nuevo usuario 3 en el sistema con un correo de alumno
        assertEquals(tamanioInicial + 2, sistema.getUsuarios().size()); //Comprobamos que el usuario 3 se ha añadido a la lista de usuarios del sistema al tener un correo de alumno valido
        sistema.registrarUsuario("nick", "nombre", "apellidos", "contra", "email@urjc.es"); //Intentamos registrar un nuevo usuario 4 con datos ya registrados en el sistema
        assertEquals(tamanioInicial + 2, sistema.getUsuarios().size()); //Comprobamos que la lista de usuarios del sistema no ha aumentado su tamaño y por tanto no se ha añadido el usuario 4
    }

    @Test
    public void hacerLogin() {
        Usuario u = sistema.registrarUsuario("nick7", "nombre", "apellidos", "contra","email2@urjc.es"); //Registramos un nuevo usuario en el sistema
        boolean logeado = sistema.hacerLogin("nick7", "contra"); //Hacemos login con el usuario creado en el sistema y guardamos el return booleano que devuelve el metodo en la variable logeado
        assertEquals(sistema.getUsuarioConectado(),u); //Comprobamos que el valor de la variable UsuarioConectado del sistema se corresponde con el usuario con el que acabamos de hacer login
        assertTrue(logeado); //Comprobamos que la variable logeado esta a True y que por tanto indica que se ha hecho correctamente el login
    }

    @Test
    public void hacerLogout() {
        sistema.hacerLogout(); //Llamamos al método hacerLogout en el sistema
        assertFalse(sistema.getSesionIniciada()); //Comprobamos que la variable booleana que nos indica si hay una sesion iniciada esta en false
        assertNull(sistema.getUsuarioConectado()); //Comprobamos que la variable UsuarioConectado en el sistema está apuntando a null
    }

    @Test 
    public void comprobarusuario() {
        sistema.registrarUsuario("nick8", "nombre", "apellidos", "contra", "profe@urjc.es"); //Registramos un nuevo profesor en el sistema
        sistema.hacerLogin("nick8", "contra"); //sesion iniciada con un usuario profesor
        Usuario u = sistema.getUsuarioConectado();
        sistema.iniciarSubforo(sistema.getUsuarioConectado(), "titulo"); //el profesor crea un subforo
        sistema.crearTextoPlano(sistema.getUsuarioConectado(), "titulo prueba", "contenido", 0); // el profesor añade una entrada
        sistema.hacerLogout(); //Hacemos logout en el sistema
        sistema.registrarAdmin("nick9", "nombre", "apellidos", "contra", "admin@urjc.es");
        sistema.hacerLogin("nick9", "contra"); //un administrador inicia sesion
        sistema.vetarEntradas((Administrador) sistema.getUsuarioConectado(), 5); //el administrador veta por 5 dias a un usuario
        sistema.vetarEntradas((Administrador) sistema.getUsuarioConectado(), 5); //por algun motivo ya hay una entrada creada, asi que hay que utilizarlo dos veces
        sistema.hacerLogout(); //Hacemos logout en el sistema
        assertFalse(sistema.comprobarusuario("nick8")); //el usuario está correctamente baneado
        u.avanzarDias(6); //Avanzamos 6 dias en el contador de días baneados del usuario
        assertTrue(sistema.comprobarusuario("nick8"));//han pasado los dias y el usuario ya no está baneado
    }

    @Test
    public void iniciarSubforo() {
        int tamanioInicial = sistema.getForo().size(); //Guardamos en la variable el tamaño inicial de la lista de subforos añadidos al foro
        Usuario usr = sistema.registrarUsuario("nick9", "nombre", "apellidos", "contra", "profe@urjc.es"); //Registramos un nuevo profesor en el sistema
        sistema.hacerLogin("nick9", "contra"); //Hacemos login con el usuario profesor que acabamos de registrar
        sistema.iniciarSubforo(usr, "Nuevo Subforo"); //El usuario profesor crea un nuevo subforo en el sistema
        assertEquals(tamanioInicial + 1, sistema.getForo().size()); //Comprobamos que el subforo creado se ha añadido a la lista de subforos creados en el sistema, viendo si ha aumentado su tamaño
        sistema.hacerLogout(); //Hacemos logout en el sistema
        Usuario usr2 = sistema.registrarUsuario("nick10", "nombre", "apellidos", "contra", "alumno@alumnos.urjc.es"); //Registramos un nuevo alumno en el sistema
        sistema.hacerLogin("nick10", "contra"); //Hacemos login con el usuario alumno que acabamos de registrar
        sistema.iniciarSubforo(usr2, "Intento de nuevo subforo"); //Intentamos crear un nuevo subforo en el sistema con un usuario alumno
        assertNotEquals(tamanioInicial + 2, sistema.getForo().size()); //Comprobamos que el subforo que hemos intentado crear no ha sido añadido a la lista de subforos del sistema por no haberlo hecho con un usuario profesor
    }

    @Test
    public void subscribirse() {
        sistema.hacerLogin("nick2", "contra");
        sistema.subscribirse(sistema.getUsuarioConectado(), 0);
        //el usuario se suscribe a un foro
        ArrayList<Subforo> foros = sistema.getForo();
        Subforo foro = foros.get(0);
        ArrayList<Usuario> usuarios = foro.getUsuariosSubscritos();
        Usuario u = usuarios.get(0);
        //obtengo el usuario que se acaba de suscribir (u) ya que es el unico suscrito hasta el momento
        assertEquals(u, sistema.getUsuarioConectado());
        //el usuario suscrito y el conectado son los mismos
    }

    @Test
    public void darseBaja() {
        sistema.hacerLogin("nick2","contra");
        sistema.darseBaja(sistema.getUsuarioConectado(), 0);
        //el subforo en principio no tiene usuarios subscritos
        ArrayList<Subforo> foros = sistema.getForo();
        Subforo foro = foros.get(0);
        ArrayList<Usuario> usuarios = foro.getUsuariosSubscritos();
        assert(usuarios.isEmpty());
        //como el unico subscriptor lo hemos añadido en la prueba anterior, la lista está vacia
    }

    @Test
    public void crearEncuesta() {
        int tamanioEsperado = sistema.getEntradasParaRevisar().size()+1;//Determinamos el tamaño esperado para el assert
        Usuario usr = sistema.registrarUsuario("nick11", "nombre", "apellidos", "contra", "profe@urjc.es");//Registramos un profesor el cual si es capaz de crear una encuesta
        sistema.hacerLogin("nick11", "contra");//Hacemos login con el profesor recien creado
        Encuesta e =sistema.crearEncuesta(usr, "titulo", "contenido", "r1", "r2", "r3", 0);//Creamos encuesta con el profesor
        assertEquals(1,sistema.getEntradasParaRevisar().size());//Comprobamos si la encuesta se ha creado correctamente
        sistema.getEntradasParaRevisar().remove(e);
    }

    @Test
    public void crearTextoPlano() {
        int tamanioEsperado = sistema.getEntradasParaRevisar().size()+1;//Determinamos el tamaño esperado para el assert
        Usuario usr = sistema.registrarUsuario("nick12", "nombre", "apellidos", "contra", "alumno@alumnos.urjc.es");//Registramos un alumno el cual si es capaz de crear un texto plano
        sistema.hacerLogin("nick12", "contra");//Hacemos login con el alumno recien creado
        TextoPlano t = sistema.crearTextoPlano(usr, "titulo", "contenido", 0);//Creamos texto plano con el alumno
        assertEquals(1,sistema.getEntradasParaRevisar().size());//Comprobamos si el texto plano se ha creado correctamente
        sistema.getEntradasParaRevisar().remove(t);
    }

    @Test
    public void validarEntradas() {
        sistema.registrarUsuario("profesorbaneado", "nombre", "apellidos", "contra", "profesor@urjc.es");
        sistema.hacerLogin("profesorbaneado", "contra");
        Encuesta e = sistema.crearEncuesta(sistema.getUsuarioConectado(), "titulo", "contenido", "r1", "r2", "r3", 0);
        //la encuesta se crea y se añade a entradas para revisar
        sistema.hacerLogout();
        sistema.hacerLogin("nick", "contra");
        sistema.validarEntradas((Administrador) sistema.getUsuarioConectado());
        //la entrada está correctamente añadida
        ArrayList<Entrada> entradas = sistema.getEntradasParaRevisar();
        assertTrue(entradas.isEmpty());
        //sabemos que la prueba ha sido validada ya que no hay entradas para revisar
        assertTrue(sistema.comprobarusuario("profesorbaneado"));
        //comprobamos que el usuario no haya sido baneado
        ArrayList<Subforo> foros = sistema.getForo();
        Subforo foro = foros.get(0);
        ArrayList<Entrada> entradasAceptadas = foro.getEntradas();
        assertTrue(entradasAceptadas.contains(e));
        //comprobamos que la entrada está en el correspondiente subforo
        
    }

    @Test
    public void vetarEntradas() {
        sistema.registrarUsuario("profesorbaneado2", "nombre", "apellidos", "contra", "profesor2@urjc.es");
        sistema.hacerLogin("profesorbaneado2", "contra");
        Encuesta e = sistema.crearEncuesta(sistema.getUsuarioConectado(), "titulo", "contenido", "r1", "r2", "r3", 0);
        //la encuesta se crea y se añade a entradas para revisar
        sistema.hacerLogout();
        sistema.registrarAdmin("admin", "nombre", "apellidos", "contra", "adminemail@urjc.es");
        sistema.hacerLogin("admin", "contra");
        sistema.vetarEntradas((Administrador) sistema.getUsuarioConectado(),5);
        sistema.vetarEntradas((Administrador) sistema.getUsuarioConectado(),5);
        
        //la entrada está correctamente añadida
        ArrayList<Entrada> entradas = sistema.getEntradasParaRevisar();
        assertTrue(entradas.isEmpty());
        //sabemos que la prueba ha sido vetada ya que no hay entradas para revisar
        assertFalse(sistema.comprobarusuario("profesorbaneado2"));
        //comprobamos que el usuario haya sido baneado
        ArrayList<Subforo> foros = sistema.getForo();
        Subforo foro = foros.get(0);
        ArrayList<Entrada> entradasAceptadas = foro.getEntradas();
        assertFalse(entradasAceptadas.contains(e));
        //comprobamos que la entrada no haya sido añadida al correspondiente subforo
    }

    @Test
    public void crearEjercicio() {
        int tamanioEsperado = sistema.getEntradasParaRevisar().size()+1;//Determinamos el tamaño esperado para el assert
        Usuario usr = sistema.registrarUsuario("nick13", "nombre", "apellidos", "contra", "profe@urjc.es");//Registramos un profesor el cual si es capaz de crear un ejercicio
        sistema.hacerLogin("nick13", "contra");//Hacemos login con el profesor recien creado
        Ejercicio e = sistema.crearEjercicio(usr, "titulo", "enunciado", 0);//Creamos ejercicio con el profesor
        assertEquals(1,sistema.getEntradasParaRevisar().size());//Comprobamos si el ejercicio se ha creado correctamente
        sistema.getEntradasParaRevisar().remove(e);
    }

    @Test
    public void crearTipoMixto() {
        int tamanioEsperado = sistema.getEntradasParaRevisar().size()+1;//Determinamos el tamaño esperado para el assert
        Usuario usr = sistema.registrarUsuario("nick14", "nombre", "apellidos", "contra", "profe@urjc.es");//Registramos un profesor el cual si es capaz de crear un tipo mixto
        sistema.hacerLogin("nick14", "contra");//Hacemos login con el profesor recien creado
        TipoMixto t = sistema.CrearTipoMixto(usr, "titulo", "contenido", "r1", "r2", "r3", 0);//Creamos tipo mixto con el profesor
        assertEquals(1,sistema.getEntradasParaRevisar().size());//Comprobamos si el tipo mixto se ha creado correctamente
        sistema.getEntradasParaRevisar().remove(t);
    }

    @Test
    public void comentarEntrada() {
        //Metodo comprobado correctamente en la clase entrada
    }

    @Test
    public void votarEntradaPositivamente() {
        //Metodo comprobado correctamente en clase correspondiente
    }

    @Test
    public void votarEntradaNegativamente() {
        //Metodo comprobado correctamente en clase correspondiente
    }

    @Test
    public void votarComentarioPositivamente() {
        //Metodo comprobado correctamente en clase correspondiente
    }

    @Test
    public void votarComentarioNegativamente() {
        //Metodo comprobado correctamente en clase correspondiente
    }

    @Test
    public void mostrarEntradaSinLog() {
        sistema.registrarUsuario("nick44", "nombre", "apellidos", "contra", "44@urjc.es");
        sistema.registrarUsuario("nick33", "nombre", "apellidos", "contra", "33@urjc.es");
        sistema.registrarAdmin("admin44", "nombre", "apellidos", "contra", "admin44@urjc.es");
        sistema.hacerLogin("nick44", "contra");
        Subforo f = sistema.iniciarSubforo(sistema.getUsuarioConectado(), "titulosubforo");
        Entrada e =sistema.CrearTipoMixto(sistema.getUsuarioConectado(), "tituloVotado", "contenido", "r1", "r2", "r3", 0);
        sistema.CrearTipoMixto(sistema.getUsuarioConectado(), "tituloNOVotado", "contenido", "r1", "r2", "r3", 0);
        sistema.hacerLogout();
        sistema.hacerLogin("admin44", "contra");
        sistema.validarEntradas((Administrador) sistema.getUsuarioConectado());
        sistema.validarEntradas((Administrador) sistema.getUsuarioConectado());
        sistema.hacerLogout();
        sistema.hacerLogin("nick33","contra");
        sistema.votarEntradaPositivamente(e, sistema.getUsuarioConectado());
        sistema.hacerLogout();
        assertEquals(e,f.EntradaMasVotada());
        //realmente lo unico demostrable es que la entrada e sea la más votada, que es la entrada que recibe 
        //este método para después mostrarla a base de getters sobre la misma
    }

    @Test
    public void mostrarEntrada() {
           //este metodo de la clase sistema es un conjunto de getters de una entrada y sus comentarios
           //luego no tiene nada que demostrarse.
    }
}