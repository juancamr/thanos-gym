package com.uni.thanosgym.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.uni.thanosgym.model.Asistencia;
import com.uni.thanosgym.model.Client;
import com.uni.thanosgym.model.Response;
import com.uni.thanosgym.utils.Querys;
import com.uni.thanosgym.utils.StringUtils;

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

    public int obtenerCantidadAsistenciasDeHoy() {
        try {
            ps = connection.prepareStatement(Querys.asistencia.obtenerAsistenciasDeHoy);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("contador");
            }
            return 0;
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        }
    }

    public Response<Asistencia> delete(int id) {
        return baseDeleteById(Querys.deleteTemplate(Asistencia.tableName), id);
    }

    public Response<Asistencia> getAll() {
        return baseGetAll(Querys.getAllTemplate(Asistencia.tableName));
    }

    public Response<Asistencia> getUltimasCatorceAsistencias(int idClient) {
        try {
            ps = connection.prepareStatement(Querys.asistencia.getUltimasCatorceAsistencias);
            ps.setInt(1, idClient);
            rs = ps.executeQuery();
            List<Asistencia> asistencias = new ArrayList<>();
            while (rs.next()) {
                Asistencia asistencia = generateObject(rs);
                asistencias.add(asistencia);
            }
            return new Response<Asistencia>(true, asistencias);
        } catch (SQLException e) {
            System.out.println("error aquis");
            return somethingWentWrong(e);
        }
    }

    public Response<Asistencia> getAllById(int id) {
        try {
            String query = Querys.getTemplateWithConditions(Asistencia.tableName, Asistencia.clientIdField);
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            List<Asistencia> asistencias = new ArrayList<>();
            while (rs.next()) {
                Asistencia asistencia = generateObject(rs);
                asistencias.add(asistencia);
            }
            return new Response<Asistencia>(true, asistencias);
        } catch (SQLException e) {
            return somethingWentWrong(e);
        }
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
        Client cliente = CRUDCliente.getInstance().getById(rs.getInt(Asistencia.clientIdField)).getData();
        return new Asistencia.Builder()
                .setId(rs.getInt(Asistencia.idField))
                .setCliente(cliente)
                .setIngreso(rs.getDate(Asistencia.ingresoField))
                .build();
    }

    @Override
    public void sendObject(String consulta, Asistencia data) throws SQLException {
        ps = connection.prepareStatement(consulta, PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setInt(1, data.getCliente().getId());
        ps.setString(2, StringUtils.parseDate(data.getIngreso()));
    }
}
