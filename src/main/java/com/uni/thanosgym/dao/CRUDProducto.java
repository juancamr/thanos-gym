package com.uni.thanosgym.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.uni.thanosgym.model.Producto;
import com.uni.thanosgym.model.Response;

public class CRUDProducto extends BaseCrud<Producto> {
    public static Response<Producto> create() {
		throw new UnsupportedOperationException("Unimplemented method 'generateObject'");
    }

	@Override
	public Producto generateObject(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'generateObject'");
	}

	@Override
	public void sendObject(String consulta, Producto data) throws SQLException {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'sendObject'");
	}
}
