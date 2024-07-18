package com.uni.thanosgym.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.uni.thanosgym.model.Asistencia;
import com.uni.thanosgym.model.Response;
import com.uni.thanosgym.utils.Querys;

public class CRUDAsistencia extends BaseCrud<Asistencia> {
    public static CRUDAsistencia crudAsistencia;

    public static CRUDAsistencia getInstance() {
        if (crudAsistencia == null) {
            crudAsistencia = new CRUDAsistencia();
        }
        return crudAsistencia;
    }

    public Response<Asistencia> create(Asistencia producto) {
        return baseCreate(producto, Querys.producto.create);
    }

    public int obtenerAsistenciasDeHoy() {
        return 0;
    }

    public Response<Asistencia> delete(int id) {
        return baseDeleteById(Querys.deleteTemplate(Asistencia.tableName), id);
    }

    public Response<Asistencia> getAll() {
        return baseGetAll(Querys.getAllTemplate(Asistencia.tableName));
    }

    public Response<Asistencia> getById(int id) {
        return baseGetById(Querys.getByIdTemplate(Asistencia.tableName), id);
    }

    public Response<Asistencia> update(Asistencia producto) {
        try {
            sendObject(Querys.producto.update, producto);
            ps.setInt(4, producto.getId());
            ps.executeUpdate();
            ps.close();
            return new Response<Asistencia>(true, "Datos actualizados con exito");
        } catch (Exception e) {
            return somethingWentWrong(e);
        }
    }

    @Override
    public Asistencia generateObject(ResultSet rs) throws SQLException {
        return new Asistencia.Builder()
                .build();
    }

    @Override
    public void sendObject(String consulta, Asistencia data) throws SQLException {
        ps = connection.prepareStatement(consulta, PreparedStatement.RETURN_GENERATED_KEYS);
    }
}
