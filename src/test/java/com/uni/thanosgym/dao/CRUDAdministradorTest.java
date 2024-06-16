package com.uni.thanosgym.dao;

import com.uni.thanosgym.model.Administrador;
import com.uni.thanosgym.model.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;

public class CRUDAdministradorTest {

    CRUDAdministrador crudAdministrador = CRUDAdministrador.getInstance();

    @Test
    public void mainTest() {

        // create admin master
        Administrador adminMaster = new Administrador.Builder()
                .setPhone(986327221)
                .setUsername("testusername")
                .setPassword("testusername")
                .setEmail("test.testmaster@gmail")
                .setFullName("Admin test")
                .setLastSignin(new Date())
                .setRol(Administrador.Rol.MASTER)
                .build();
        Response<Administrador> resAdminMaster = crudAdministrador.create(adminMaster,
                new Administrador.Builder().build());
        assertEquals(true, resAdminMaster.isSuccess());

        // create empleado 1
        Administrador adminEmpleado1 = new Administrador.Builder()
                .setPhone(986327241)
                .setUsername("testusername1")
                .setPassword("testusername1")
                .setEmail("test.testemple1@gmail")
                .setFullName("Empleado test 1")
                .setLastSignin(new Date())
                .setRol(Administrador.Rol.EMPLEADO)
                .build();
        Response<Administrador> resEmpleado1 = crudAdministrador.create(adminEmpleado1, resAdminMaster.getData());
        assertEquals(true, resEmpleado1.isSuccess());

        // create empleado 2
        Administrador adminEmpleado2 = new Administrador.Builder()
                .setPhone(986327242)
                .setUsername("testusername2")
                .setPassword("testusername2")
                .setEmail("test.testemple2@gmail")
                .setFullName("Empleado test 2")
                .setLastSignin(new Date())
                .setRol(Administrador.Rol.EMPLEADO)
                .build();
        Response<Administrador> resEmpleado2 = crudAdministrador.create(adminEmpleado2, resEmpleado1.getData());
        assertEquals(false, resEmpleado2.isSuccess());

        // delete empleado 1
        Response<Administrador> resDeleteEmpleado1 = crudAdministrador.deleteOnlyForTesting(resEmpleado1.getId());
        assertEquals(true, resDeleteEmpleado1.isSuccess());

        // delete admin master
        Response<Administrador> resDeleteAdminMaster = crudAdministrador.deleteOnlyForTesting(resAdminMaster.getId());
        assertEquals(true, resDeleteAdminMaster.isSuccess());
    }
}
