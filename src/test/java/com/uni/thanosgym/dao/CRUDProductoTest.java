package com.uni.thanosgym.dao;

import com.uni.thanosgym.model.Response;
import com.uni.thanosgym.model.Producto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CRUDProductoTest {

    CRUDProducto crudProducto = CRUDProducto.getInstance();

    @Test
    public void mainTest() {
        // create
        Producto producto = new Producto("Test Producto", 100, 30);
        Response<Producto> response = crudProducto.create(producto);
        assertEquals(true, response.isSuccess());

        //update
        int id = response.getId();
        String nameEdited = "Test Producto edited";
        producto.setNombre(nameEdited);
        producto.setId(id);
        Response<Producto> response2 = crudProducto.update(producto);
        assertEquals(true, response2.isSuccess());

        // delete
        Response<Producto> response3 = crudProducto.delete(id);
        assertEquals(true, response3.isSuccess());
    }
}
