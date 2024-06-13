package com.uni.thanosgym.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.uni.thanosgym.model.Producto;
import com.uni.thanosgym.model.Response;
import com.uni.thanosgym.utils.Querys;

public class CRUDProducto extends BaseCrud<Producto> {
    public static CRUDProducto crudProducto;

    public static CRUDProducto getInstance() {
        if (crudProducto == null) {
            crudProducto = new CRUDProducto();
        }
        return crudProducto;
    }

    public Response<Producto> create(Producto producto) {
        try {
            ps = connection.prepareStatement(Querys.producto.getByName);
            ps.setString(1, producto.getNombre());
            rs = ps.executeQuery();
            boolean[] conditions = new boolean[] { !rs.next() };
            String error = String.format("El producto con el nombre %s ya existe", producto.getNombre());
            return baseCreateWithConditions(producto, Querys.producto.create, conditions, error);
        } catch (Exception e) {
            return somethingWentWrong(e);
        }
    }

    public Response<Producto> delete(int id) {
        return baseDeleteById(Querys.producto.delete, id);
    }

    public Response<Producto> update(Producto producto) {
        try {
            sendObject(Querys.producto.update, producto);
            ps.setInt(4, producto.getId());
            ps.executeUpdate();
            ps.close();
            return new Response<Producto>(true, "Datos actualizados con exito");
        } catch (Exception e) {
            return somethingWentWrong(e);
        }
    }

	@Override
	public Producto generateObject(ResultSet rs) throws SQLException {
        Producto producto = new Producto();
        producto.setId(rs.getInt(1));
        producto.setNombre(rs.getString(2));
        producto.setCantidad(rs.getInt(3));
        producto.setPrecio(rs.getDouble(4));
        return producto;
	}

	@Override
	public void sendObject(String consulta, Producto data) throws SQLException {
        ps = connection.prepareStatement(consulta, PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setString(1, data.getNombre());
        ps.setInt(2, data.getCantidad());
        ps.setDouble(3, data.getPrecio());
	}
}
