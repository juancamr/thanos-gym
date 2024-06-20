package com.uni.thanosgym.dao;

import com.uni.thanosgym.model.Admin;
import com.uni.thanosgym.model.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CRUDAdministradorTest {

    CRUDAdministrador crudAdministrador = CRUDAdministrador.getInstance();

    @Test
    public void mainTest() {

        // create admin master
        Admin adminMaster = new Admin.Builder()
                .setFullName("Admin test")
                .setPhone("986327221")
                .setEmail("test.testmaster@gmail")
                .setUsername("testusername")
                .setPassword("testusername")
                .setRol(Admin.Rol.MASTER)
                .setPhotoUrl("photo")
                .build();

        Response<Admin> resAdminMaster = crudAdministrador.create(adminMaster,
                new Admin.Builder().build());
        assertEquals(true, resAdminMaster.isSuccess());

        // create empleado 1
        Admin adminEmpleado1 = new Admin.Builder()
                .setFullName("Empleado test 1")
                .setPhone("986327241")
                .setEmail("test.testemple1@gmail")
                .setUsername("testusername1")
                .setPassword("testusername1")
                .setRol(Admin.Rol.EMPLEADO)
                .setPhotoUrl("photo")
                .build();

        Response<Admin> resEmpleado1 = crudAdministrador.create(adminEmpleado1, resAdminMaster.getData());
        assertEquals(true, resEmpleado1.isSuccess());

        // create empleado 2
        Admin adminEmpleado2 = new Admin.Builder()
                .setFullName("Empleado test 2")
                .setPhone("986327242")
                .setEmail("test.testemple2@gmail")
                .setUsername("testusername2")
                .setPassword("testusername2")
                .setRol(Admin.Rol.EMPLEADO)
                .setPhotoUrl("photo")
                .build();
        Response<Admin> resEmpleado2 = crudAdministrador.create(adminEmpleado2, resEmpleado1.getData());
        assertEquals(false, resEmpleado2.isSuccess());

        // delete empleado 1
        Response<Admin> resDeleteEmpleado1 = crudAdministrador.deleteOnlyForTesting(resEmpleado1.getId());
        assertEquals(true, resDeleteEmpleado1.isSuccess());

        // delete admin master
        Response<Admin> resDeleteAdminMaster = crudAdministrador.deleteOnlyForTesting(resAdminMaster.getId());
        assertEquals(true, resDeleteAdminMaster.isSuccess());
    }
}
