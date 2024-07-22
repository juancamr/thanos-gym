package com.uni.thanosgym.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.uni.thanosgym.model.DetalleProducto;
import com.uni.thanosgym.model.Producto;
import com.uni.thanosgym.model.Response;
import com.uni.thanosgym.utils.Querys;
import com.uni.thanosgym.utils.StringUtils;

public class CRUDDetalleProducto extends BaseCrud<DetalleProducto> {
    private static CRUDDetalleProducto crudDetalleProducto;;

    public static CRUDDetalleProducto getInstance() {
        if (crudDetalleProducto == null) {
            crudDetalleProducto = new CRUDDetalleProducto();
        }
        return crudDetalleProducto;
    }

    public Response<DetalleProducto> create(DetalleProducto detalle) {
        return baseCreate(detalle, Querys.detalleProducto.create);
    }

    public Response<DetalleProducto> delete(int id) {
        return baseDeleteById(Querys.deleteTemplate(DetalleProducto.tableName), id);
    }

    public Response<DetalleProducto> update(DetalleProducto detalle) {
        try {
            sendObject(Querys.detalleProducto.update, detalle);
            ps.setInt(5, detalle.getId());
            ps.executeUpdate();
            ps.close();
            return new Response<DetalleProducto>(true);
        } catch (Exception e) {
            System.out.println(e);
            return new Response<DetalleProducto>(false);
        }
    }

    public Response<DetalleProducto> getById(int id) {
        return baseGetById(Querys.getByIdTemplate(DetalleProducto.tableName), id);
    }

    public Response<DetalleProducto> obtenerProductosParaVenta(int productoId, int cantidadSolicitada) {
        try {
            ps = connection.prepareStatement(Querys.detalleProducto.getDetallesForVenta);
            ps.setInt(1, productoId);
            ps.setInt(2, cantidadSolicitada);
            rs = ps.executeQuery();
            List<DetalleProducto> detalles = new ArrayList<>();
            while (rs.next()) {
                DetalleProducto detalle = generateObject(rs);
                detalles.add(detalle);
            }

            if (detalles.stream().mapToInt(DetalleProducto::getStock).sum() < cantidadSolicitada) {
                return new Response<>(false, "No hay stock suficiente");
            }
            return new Response<>(true, detalles);
        } catch (SQLException e) {
            e.printStackTrace();
            return new Response<>(false, e.getMessage());
        }
    }

    public Response<Integer> descontar(List<Integer> ids, int cantidadSolicitada) {
        List<DetalleProducto> detalles = ids.stream().map(id -> {
            return getById(id).getData();
        }).collect(Collectors.toList());
        List<Integer> stockRetirados = new ArrayList<>();
        for (DetalleProducto detalle : detalles) {
            int stockDelDetalle = detalle.getStock();
            if (stockDelDetalle == cantidadSolicitada) {
                delete(detalle.getId());
                stockRetirados.add(stockDelDetalle);
                break;
            }
            if (stockDelDetalle > cantidadSolicitada) {
                detalle.setStock(detalle.getStock() - cantidadSolicitada);
                update(detalle);
                stockRetirados.add(stockDelDetalle);
                break;
            }

            cantidadSolicitada = cantidadSolicitada - stockDelDetalle;
            delete(detalle.getId());
            stockRetirados.add(stockDelDetalle);
        }
        return new Response<Integer>(true, stockRetirados);
    }

    // mostrar en la ventana al dar click en un producto en la tabla (mostrando
    // todos los ingresos de ese producto)
    public Response<DetalleProducto> getAllByProductoId(int productoId) {
        return baseGetAllById(
                Querys.getTemplateWithConditions(DetalleProducto.tableName, DetalleProducto.productoIdField),
                productoId);
    }

    @Override
    public DetalleProducto generateObject(ResultSet rs) throws SQLException {
        Response<Producto> res = CRUDProducto.getInstance().getById(rs.getInt(DetalleProducto.productoIdField));
        if (!res.isSuccess()) {
            return null;
        }
        return new DetalleProducto.Builder()
                .setId(rs.getInt(DetalleProducto.idField))
                .setStock(rs.getInt(DetalleProducto.stockField))
                .setFechaVencimiento(rs.getDate(DetalleProducto.fechaVencimientoField))
                .setCreatedAt(rs.getDate(DetalleProducto.createdAtField))
                .setPrecio(rs.getDouble(DetalleProducto.precioField))
                .setProducto(res.getData())
                .build();
    }

    @Override
    public void sendObject(String consulta, DetalleProducto data) throws SQLException {
        ps = connection.prepareStatement(consulta, PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setInt(1, data.getProducto().getId());
        ps.setInt(2, data.getStock());
        ps.setString(3, StringUtils.parseDate(data.getFechaVencimiento()));
        ps.setDouble(4, data.getPrecio());
    }
}
