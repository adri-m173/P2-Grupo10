package com.p3;
import org.junit.Test;

import static org.junit.Assert.*;

public class SistemaTest {
    Sistema sistema = Sistema.getInstance();

    @Test
    public void guardarSistema() {
    }

    @Test
    public void cargarSistema() {
    }

    @Test
    public void registrarAdmin() {
        int adminAniadido = sistema.getUsuarios().size();
        sistema.registrarAdmin("nick", "nombre", "apellidos", "contra", "admin@email.com"); //Intentamos registrar un nuevo administrador 1 en el sistema con correo no valido
        assertEquals(adminAniadido, sistema.getUsuarios().size()); //Comprobamos que el administrador 1 no se ha añadido a la lista de usuarios del sistema por no tener un correo válido
        sistema.registrarAdmin("nick", "nombre", "apellidos", "contra", "email@urjc.es"); //Registramos un nuevo administrador 2 en el sistema con un correo de profesor
        assertEquals(adminAniadido + 1, sistema.getUsuarios().size()); //Comprobamos que el administrador 2 se ha añadido a la lista de usuarios del sistema por tener un correo de profesor valido
        sistema.registrarAdmin("nick2", "nombre", "apellidos", "contra", "email@alumnos.urjc.es"); //Registramos un nuevo administrador 3 en el sistema con un correo de alumno
        assertEquals(adminAniadido + 2, sistema.getUsuarios().size()); //Comprobamos que el administrador 3 se ha añadido a la lista de usuarios del sistema al tener un correo de alumno valido
        sistema.registrarAdmin("nick", "nombre", "apellidos", "contra", "email@urjc.es"); //Intentamos registrar un nuevo administrador 4 con datos ya registrados en el sistema
        assertEquals(adminAniadido + 2, sistema.getUsuarios().size()); //Comprobamos que la lista de usuarios del sistema no ha aumentado su tamaño y por tanto no se ha añadido el administrador 4
    }

    @Test
    public void registrarUsuario() {
        int usuarioAniadido = sistema.getUsuarios().size();
        sistema.registrarUsuario("nick", "nombre", "apellidos", "contra", "admin@email.com"); //Intentamos registrar un nuevo usuario 1 en el sistema con correo no valido
        assertEquals(usuarioAniadido, sistema.getUsuarios().size()); //Comprobamos que el usuario 1 no se ha añadido a la lista de usuarios del sistema por no tener un correo válido
        sistema.registrarUsuario("nick2", "nombre", "apellidos", "contra", "email@urjc.es"); //Registramos un nuevo usuario 2 en el sistema con un correo de profesor
        assertEquals(usuarioAniadido + 1, sistema.getUsuarios().size()); //Comprobamos que el usuario 2 se ha añadido a la lista de usuarios del sistema por tener un correo de profesor valido
        sistema.registrarUsuario("nick4", "nombre", "apellidos", "contra", "email@alumnos.urjc.es"); //Registramos un nuevo usuario 3 en el sistema con un correo de alumno
        assertEquals(usuarioAniadido + 2, sistema.getUsuarios().size()); //Comprobamos que el usuario 3 se ha añadido a la lista de usuarios del sistema al tener un correo de alumno valido
        sistema.registrarUsuario("nick", "nombre", "apellidos", "contra", "email@urjc.es"); //Intentamos registrar un nuevo usuario 4 con datos ya registrados en el sistema
        assertEquals(usuarioAniadido + 2, sistema.getUsuarios().size()); //Comprobamos que la lista de usuarios del sistema no ha aumentado su tamaño y por tanto no se ha añadido el usuario 4
    }

    @Test
    public void hacerLogin() {
        Usuario u = sistema.registrarUsuario("nick7", "nombre", "apellidos", "contra","email2@urjc.es");
        boolean logeado = sistema.hacerLogin("nick7", "contra");
        assertEquals(sistema.getUsuarioConectado(),u);
        assertEquals(logeado,true);
    }

    @Test
    public void hacerLogout() {
        sistema.hacerLogout();
        assertEquals(sistema.getSesionIniciada(),false);
        assertEquals(sistema.getUsuarioConectado(),null);
    }

    @Test
    public void comprobarusuario() {
        System.out.println("desde aqui");
        sistema.registrarUsuario("nick8", "nombre", "apellidos", "contra", "profe@urjc.es");
        sistema.hacerLogin("nick8", "contra"); //sesion iniciada con un usuario profesor
        Usuario u = sistema.getUsuarioConectado();
        sistema.iniciarSubforo(sistema.getUsuarioConectado(), "titulo"); //el profesor crea un subforo
        sistema.crearTextoPlano(sistema.getUsuarioConectado(), "titulo prueba", "contenido", 0); // el profesor añade una entrada
        sistema.hacerLogout();
        sistema.registrarAdmin("nick9", "nombre", "apellidos", "contra", "admin@urjc.es");
        sistema.hacerLogin("nick9", "contra"); //un administrador inicia sesion
        sistema.vetarEntradas((Administrador) sistema.getUsuarioConectado(), 5); //el administrador veta por 5 dias a un usuario
        sistema.vetarEntradas((Administrador) sistema.getUsuarioConectado(), 5); //por algun motivo ya hay una entrada creada, asi que hay que utilizarlo dos veces
        sistema.hacerLogout();
        assertEquals(sistema.comprobarusuario("nick8"),false); //el usuario está correctamente baneado
        u.avanzarDias(6);
        assertEquals(sistema.comprobarusuario("nick8"),true);//han pasado los dias y el usuario ya no está baneado
    }

    @Test
    public void iniciarSubforo() {
    }

    @Test
    public void subscribirse() {
    }

    @Test
    public void darseBaja() {
    }

    @Test
    public void crearEncuesta() {
    }

    @Test
    public void crearTextoPlano() {
    }

    @Test
    public void validarEntradas() {
    }

    @Test
    public void vetarEntradas() {
    }

    @Test
    public void crearEjercicio() {
    }

    @Test
    public void crearTipoMixto() {
    }

    @Test
    public void comentarEntrada() {
    }

    @Test
    public void votarEntradaPositivamente() {
    }

    @Test
    public void votarEntradaNegativamente() {
    }

    @Test
    public void votarComentarioPositivamente() {
    }

    @Test
    public void votarComentarioNegativamente() {
    }

    @Test
    public void mostrarEntradaSinLog() {
    }

    @Test
    public void mostrarEntrada() {
    }
}