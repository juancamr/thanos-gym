package com.uni.thanosgym.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.uni.thanosgym.model.*;
import com.uni.thanosgym.utils.Querys;

public class CRUDDetalleBoleta extends BaseCrud<DetalleBoleta> {

    public static CRUDDetalleBoleta crudDetalleBoleta;

    private CRUDDetalleBoleta() {
    }

    public static CRUDDetalleBoleta getInstance() {
        if (crudDetalleBoleta == null) {
            crudDetalleBoleta = new CRUDDetalleBoleta();
        }
        return crudDetalleBoleta;
    }

    public Response<DetalleBoleta> create(DetalleBoleta detalle) {
        return baseCreate(detalle, Querys.detalleBoleta.create);
    }

    public Response<DetalleBoleta> getAllByBoletaId(int id) {
        try {
            ps = connection.prepareStatement(Querys.detalleBoleta.getDetallesByBoletaId);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            List<DetalleBoleta> detalles = new ArrayList<>();
            while (rs.next()) {
                DetalleBoleta detalle = generateObject(rs);
                detalles.add(detalle);
            }
            return new Response<>(true, "Lista de detalles obtenida correctamente", detalles);
        } catch (SQLException ex) {
            System.out.println(ex);
            return new Response<>(false, "Error al obtener los detalles del boleta: " + ex.getMessage());
        }
    }

    public Response<DetalleBoleta> getById(int id) {
        return baseGetById(Querys.getByIdTemplate(DetalleBoleta.tableName), id);
    }

    @Override
    public DetalleBoleta generateObject(ResultSet rs) throws SQLException {
        Response<Producto> resProducto = CRUDProducto.getInstance().getById(rs.getInt(DetalleBoleta.productoIdField));
        //Response<Boleta> resBoleta = CRUDBoleta.getInstance().getById(rs.getInt(DetalleBoleta.boletaIdField));
        int id = rs.getInt(DetalleBoleta.idField);
        return new DetalleBoleta.Builder()
                .setId(id)
                .setProducto(resProducto.getData())
                .setCantidad(rs.getInt(DetalleBoleta.cantidadField))
                .setTotal(rs.getDouble(DetalleBoleta.precioField))
                .build();
    }

    @Override
    public void sendObject(String consulta, DetalleBoleta data) throws SQLException {
        ps = connection.prepareStatement(consulta, PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setInt(1, data.getBoleta().getId());
        ps.setInt(2, data.getProducto().getId());
        ps.setInt(3, data.getCantidad());
        ps.setDouble(4, data.getTotal());
    }
}
