package com.uni.thanosgym.dao;

import com.uni.thanosgym.model.Response;
import com.uni.thanosgym.model.Producto;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CRUDProductoTest {

    CRUDProducto crudProducto = CRUDProducto.getInstance();

    @Test
    public void mainTest() {
        // create
        Producto producto = new Producto.Builder()
                .setNombre("Test Producto")
                .setCantidad(10)
                .setPrecio(100)
                .setPhotoUrl("photo_url")
                .build();
        Response<Producto> response = crudProducto.create(producto);
        assertTrue(response.isSuccess());

        //update
        int id = response.getId();
        String nameEdited = "Test Producto edited";
        producto.setNombre(nameEdited);
        producto.setId(id);
        Response<Producto> response2 = crudProducto.update(producto);
        assertTrue(response2.isSuccess());

        // delete
        Response<Producto> response3 = crudProducto.delete(id);
        assertTrue(response3.isSuccess());
    }
}
