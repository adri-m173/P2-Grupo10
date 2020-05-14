package com.p3;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({SistemaTest.class, UsuarioTest.class, ComentarioTest.class, EntradaTest.class, AdministradorTest.class, SubforoTest.class})

public class TestAll {
}
