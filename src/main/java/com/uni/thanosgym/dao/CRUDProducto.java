package com.uni.thanosgym.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.uni.thanosgym.model.Producto;
import com.uni.thanosgym.model.Response;
import com.uni.thanosgym.utils.Querys;
import com.uni.thanosgym.utils.StringUtils;

public class CRUDProducto extends BaseCrud<Producto> {
    public static CRUDProducto crudProducto;

    public static CRUDProducto getInstance() {
        if (crudProducto == null) {
            crudProducto = new CRUDProducto();
        }
        return crudProducto;
    }

    public Response<Producto> create(Producto producto) {
        return baseCreate(producto, Querys.producto.create);
    }

    public Response<Producto> delete(int id) {
        return baseDeleteById(Querys.deleteTemplate(Producto.tableName), id);
    }

    public Response<Producto> getAll() {
        return baseGetAll(Querys.getAllTemplate(Producto.tableName));
    }

    public Response<Producto> getById(int id) {
        return baseGetById(Querys.getByIdTemplate(Producto.tableName), id);
    }

    public Response<Producto> getByCodigo(String codigo) {
        return baseGetByString(Querys.getTemplateWithConditions(Producto.tableName, Producto.codigoField), codigo);
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
        return new Producto.Builder()
                .setId(rs.getInt(Producto.idField))
                .setCodigo(rs.getString(Producto.codigoField))
                .setNombre(rs.getString(Producto.nombreField))
                .setCantidad(rs.getInt(Producto.cantidadField))
                .setPrecio(rs.getDouble(Producto.precioField))
                .setPhotoUrl(rs.getString(Producto.photoUrlField))
                .setFechaVencimiento(rs.getDate(Producto.fechaVencimientoField))
                .build();
    }

    @Override
    public void sendObject(String consulta, Producto data) throws SQLException {
        ps = connection.prepareStatement(consulta, PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setString(1, data.getNombre());
        ps.setInt(2, data.getCantidad());
        ps.setDouble(3, data.getPrecio());
        ps.setString(4, StringUtils.parseDate(data.getFechaVencimiento()));
        ps.setString(5, data.getCodigo());
    }
}
