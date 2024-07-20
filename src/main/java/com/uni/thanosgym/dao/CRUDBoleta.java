
package com.uni.thanosgym.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.uni.thanosgym.model.Boleta;
import com.uni.thanosgym.model.DetalleBoleta;
import com.uni.thanosgym.model.Reporte;
import com.uni.thanosgym.model.ReporteMensual;
import com.uni.thanosgym.model.Response;
import com.uni.thanosgym.utils.Querys;
import com.uni.thanosgym.utils.StringUtils;
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

    public Response<Reporte> getReportBetweenDates(Date desde, Date hasta) {
        String desdeFecha = StringUtils.parseDate(desde);
        String hastaFecha = StringUtils.parseDate(hasta);
        try {
            ps = connection.prepareStatement(Querys.boleta.getMontoTotalBetweenDates);
            ps.setString(1, desdeFecha);
            ps.setString(2, hastaFecha);
            rs = ps.executeQuery();

            List<Reporte> reportes = new ArrayList<>();
            while (rs.next()) {
                Date fecha = rs.getDate("reporte_fecha");
                double monto = rs.getDouble("reporte_monto");
                reportes.add(new  Reporte(fecha, monto));
            }
            return new Response<>(true, "Lista de pagos obtenida correctamente", reportes);
        } catch (SQLException e) {
            return new Response<>(false, "Error al obtener los pagos del cliente: " + e.getMessage());
        }
    }

    public Response<Boleta> getAllBoletas() {
        try {
            ps = connection.prepareStatement("SELECT * FROM boleta ORDER BY created_at DESC");
            rs = ps.executeQuery();
            List<Boleta> boletas = new ArrayList<>();
            while (rs.next()) {
                Boleta boleta = generateObject(rs);
                boletas.add(boleta);
            }
            return new Response<Boleta>(true, "Lista de boletas obtenida correctamente", boletas);
        } catch (SQLException e) {
            return new Response<>(false, "Error al obtener las boletas del cliente: " + e.getMessage());
        }
    }

    public int getUltimoNumeroBoleta() {
        try {
            ps = connection.prepareStatement(Querys.boleta.getUltimoMayorIdBoleta);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("ultimo_numero_boleta");
            }
            return 0;
        } catch (SQLException e) {
            return 0;
        }
    }

    public Response<ReporteMensual> getReporteLast4Months() {
        try {
            ps = connection.prepareStatement(Querys.boleta.getReporteLast4Months);
            rs = ps.executeQuery();
            List<ReporteMensual> reportes = new ArrayList<>();
            while (rs.next()) {
                String fecha = rs.getString("reporte_mes");
                double monto = rs.getDouble("reporte_monto");
                reportes.add(new ReporteMensual(fecha, monto));
            }
            return new Response<>(true, "Lista de pagos obtenida correctamente", reportes);
        } catch (SQLException e) {
            return new Response<>(false, "Error al obtener los pagos del cliente: " + e.getMessage());
        }
    }

    public Response<Boleta> obtenerUltimasTresBoletas() {
        try {
            ps = connection.prepareStatement(Querys.boleta.obtenerUltimasTresBoletas);
            rs = ps.executeQuery();
            List<Boleta> boletas = new ArrayList<>();
            while (rs.next()) {
                Boleta boleta = generateObject(rs);
                boletas.add(boleta);
            }
            return new Response<Boleta>(true, boletas);
        } catch (SQLException e) {
            System.out.println(e);
            return new Response<Boleta>(false);
        }
    }

    public Response<Boleta> getBoletasInDate(Date fecha) {
        String date = StringUtils.parseDate(fecha);
        String query = Querys.getTemplateWithConditions(Boleta.tableName, "DATE(created_at)");
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, date);
            rs = ps.executeQuery();
            List<Boleta> boletas = new ArrayList<>();
            while (rs.next()) {
                Boleta boleta = generateObject(rs);
                boletas.add(boleta);
            }
            return new Response<Boleta>(true, "Lista de boletas obtenida correctamente", boletas);
        } catch (SQLException e) {
            return new Response<Boleta>(false, "Error al obtener las boletas del cliente: " + e.getMessage());
        }
    }

    public Response<Boleta> getBoletasByIdCliente(int idCliente) {
        try {
            ps = connection.prepareStatement(Querys.getTemplateWithConditions(Boleta.tableName, Boleta.clientIdField));
            ps.setInt(1, idCliente);
            rs = ps.executeQuery();
            List<Boleta> boletas = new ArrayList<>();
            while (rs.next()) {
                Boleta boleta = generateObject(rs);
                boletas.add(boleta);
            }
            return new Response<Boleta>(true, "Lista de boletas obtenida correctamente", boletas);
        } catch (SQLException e) {
            return new Response<Boleta>(false, "Error al obtener las boletas del cliente: " + e.getMessage());
        }
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
