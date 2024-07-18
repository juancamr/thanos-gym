
package com.uni.thanosgym.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.uni.thanosgym.model.Boleta;
import com.uni.thanosgym.model.DetalleBoleta;
import com.uni.thanosgym.model.Response;
import com.uni.thanosgym.utils.Querys;
import com.uni.thanosgym.utils.UserPreferences;

public class CRUDBoleta extends BaseCrud<Boleta> {
    public static CRUDBoleta crudBoleta;

    private CRUDBoleta() {
    }

    public static CRUDBoleta getInstance() {
        if (crudBoleta == null) {
            crudBoleta = new CRUDBoleta();
        }
        return crudBoleta;
    }

    public Response<Boleta> create(Boleta boleta) {
        return baseCreate(boleta, Querys.boleta.create);
    }

    public Response<Boleta> getById(int id) {
        return baseGetById(Querys.getByIdTemplate(Boleta.tableName), id);
    }

    public Response<Boleta> delete(int id) {
        return baseDeleteById(Querys.deleteTemplate(Boleta.tableName), id);
    }

    public Response<Boleta> getByCliente(int clientId) {
        try {
            ps = connection
                    .prepareStatement(Querys.getTemplateWithConditions(Boleta.tableName, Boleta.clientIdField));
            ps.setInt(1, clientId);
            rs = ps.executeQuery();

            List<Boleta> boletas = new ArrayList<>();
            while (rs.next()) {
                Boleta payment = generateObject(rs);
                boletas.add(payment);
            }
            return new Response<>(true, "Lista de pagos obtenida correctamente", boletas);
        } catch (SQLException e) {
            return new Response<>(false, "Error al obtener los pagos del cliente: " + e.getMessage());
        }
    }

    public double getPorcentajeDeGanancianMesAnteriorConElDeAhora() {
        return 0;
    }

    @Override
    public Boleta generateObject(ResultSet rs) throws SQLException {
        int id = rs.getInt(Boleta.idField);
        Response<DetalleBoleta> resDetalle = CRUDDetalleBoleta.getInstance().getAllByBoletaId(id);
        return new Boleta.Builder()
                .setId(id)
                .setCliente(CRUDCliente.getInstance().getById(rs.getInt(Boleta.clientIdField)).getData())
                .setAdmin(CRUDAdministrador.getInstance().getById(rs.getInt(Boleta.adminIdField)).getData())
                .setCreatedAt(rs.getDate(Boleta.createdAtField))
                .setTotal(rs.getDouble(Boleta.totalField))
                .setDetallesBoleta(resDetalle.getDataList())
                .build();
    }

    @Override
    public void sendObject(String consulta, Boleta data) throws SQLException {
        ps = connection.prepareStatement(consulta, PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setInt(1, data.getCliente().getId());
        ps.setInt(2, UserPreferences.getData().getId());
        ps.setDouble(3, data.getTotal());
    }
}
