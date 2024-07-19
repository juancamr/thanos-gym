package com.uni.thanosgym.dao;

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

    public Response<DetalleBoleta> create(DetalleBoleta boleta) {
        return baseCreate(boleta, Querys.boleta.create);
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
            return new Response<>(false, "Error al obtener los detalles del boleta: " + ex.getMessage());
        }
    }

    public Response<DetalleBoleta> getById(int id) {
        return baseGetById(Querys.getByIdTemplate(DetalleBoleta.tableName), id);
    }

    @Override
    public DetalleBoleta generateObject(ResultSet rs) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'generateObject'");
    }

    @Override
    public void sendObject(String consulta, DetalleBoleta data) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sendObject'");
    }
}
