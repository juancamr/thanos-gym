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
        Administrador adminMaster = new Administrador(
                new Date(),
                "Admin test",
                "986327221",
                "test.testmaster@gmail",
                "testusername",
                "testusername",
                Administrador.Rol.MASTER,
                "photo",
                new Date());
        Response<Administrador> resAdminMaster = crudAdministrador.create(adminMaster,
                new Administrador());
        assertEquals(true, resAdminMaster.isSuccess());

        // create empleado 1
        Administrador adminEmpleado1 = new Administrador(
                new Date(),
                "Empleado test 1",
                "986327241",
                "test.testemple1@gmail",
                "testusername1",
                "testusername1",
                Administrador.Rol.EMPLEADO,
                "photo",
                new Date());
        Response<Administrador> resEmpleado1 = crudAdministrador.create(adminEmpleado1, resAdminMaster.getData());
        assertEquals(true, resEmpleado1.isSuccess());

        // create empleado 2
        Administrador adminEmpleado2 = new Administrador(
                new Date(),
                "Empleado test 2",
                "986327242",
                "test.testemple2@gmail",
                "testusername2",
                "testusername2",
                Administrador.Rol.EMPLEADO,
                "photo",
                new Date());
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
