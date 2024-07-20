package com.uni.thanosgym.utils;

import com.uni.thanosgym.model.*;

public class Querys {

    public class admin {

        public static String create = generateCreateQuery(Admin.tableName,
                new String[] { Admin.fullNameField, Admin.emailField,
                        Admin.phoneField, Admin.usernameField, Admin.passwordField,
                        Admin.rolField, Admin.photoUrlField, });
        public static String update = generateUpdateQuery(Admin.tableName,
                new String[] { Admin.fullNameField, Admin.emailField,
                        Admin.phoneField, Admin.usernameField, Admin.passwordField,
                        Admin.rolField, Admin.photoUrlField, Admin.lastSigninField });

    }

    public class plan {

        public static String create = generateCreateQuery(Plan.tableName,
                new String[] { Plan.nameField, Plan.priceField, Plan.durationDaysField });
        public static String update = generateUpdateQuery(Plan.tableName,
                new String[] { Plan.nameField, Plan.priceField, Plan.durationDaysField, Plan.isVisibleField });
    }

    public class client {

        public static String obtenerCantidadClientesSuscritos = "SELECT COUNT(DISTINCT c.client_id) AS num_clientes_vigentes "
                +
                "FROM client c " +
                "JOIN contrato co ON c.client_id = co.client_id " +
                "WHERE co.subscription_until >= CURDATE() " +
                "AND (co.is_frozen = 0 OR (co.is_frozen = 1 AND (co.freeze_until IS NULL OR co.freeze_until < CURDATE())))";
        public static String obtenerClientesSuscritosHoy = "SELECT COUNT(*) AS contador FROM client WHERE DATE(created_at) = CURDATE()";
        public static String create = generateCreateQuery(Client.tableName,
                new String[] { Client.dniField, Client.fullNameField, Client.emailField,
                        Client.addressField, Client.phoneField, Client.photoUrlField });
        public static String update = generateUpdateQuery(Client.tableName,
                new String[] { Client.dniField, Client.fullNameField, Client.emailField,
                        Client.addressField, Client.phoneField, Client.photoUrlField });

        // public static String getByDni = "SELECT * FROM client WHERE dni = ?";
        // public static String getByEmail = "SELECT * FROM client WHERE email = ?";
    }

    public class producto {

        public static String create = generateCreateQuery(Producto.tableName,
                new String[] { Producto.nombreField, Producto.codigoField });
        public static String update = generateUpdateQuery(Producto.tableName,
                new String[] { Producto.nombreField });
    }

    public class detalleProducto {
        public static String create = generateCreateQuery(DetalleProducto.tableName,
                new String[] { DetalleProducto.productoIdField, DetalleProducto.stockField,
                        DetalleProducto.fechaVencimientoField, DetalleProducto.precioField });
        public static String update = generateUpdateQuery(DetalleProducto.tableName,
                new String[] { DetalleProducto.productoIdField, DetalleProducto.stockField, DetalleProducto.fechaVencimientoField,
                        DetalleProducto.precioField });

        public static String getDetallesForVenta = "WITH stock_details AS ( " +
                "SELECT detalle_producto_id, producto_id, stock, fecha_vencimiento, precio, created_at, " +
                "SUM(stock) OVER (PARTITION BY producto_id ORDER BY fecha_vencimiento) AS cumulative_stock " +
                "FROM detalle_producto " +
                "WHERE fecha_vencimiento >= CURDATE() " +
                "ORDER BY fecha_vencimiento " +
                ") " +
                "SELECT detalle_producto_id, producto_id, stock, fecha_vencimiento, precio, created_at " +
                "FROM stock_details " +
                "WHERE producto_id = ? AND cumulative_stock - stock < ? " +
                "ORDER BY fecha_vencimiento;";
    }

    public class contrato {

        public static String create = generateCreateQuery(Contrato.tableName,
                new String[] { Contrato.clientIdField, Contrato.planIdField, Contrato.adminIdField,
                        Contrato.transactionCodeField, Contrato.subscriptionUntilField });

        public static String obtenerUltimosTresContratos = "SELECT * FROM contrato ORDER BY contrato_id DESC LIMIT 3";

        public static String updateCongelar = generateUpdateQuery(Contrato.tableName,
                new String[] { Contrato.subscriptionUntilField, Contrato.isFrozenField, Contrato.freezeCountField,
                        Contrato.freezeUntilField, Contrato.lastFreezeDateField });

    }

    public class boleta {
        public static String create = generateCreateQuery(Boleta.tableName,
                new String[] { Boleta.clientIdField, Boleta.adminIdField, Boleta.totalField });
        public static String getMontoTotalBetweenDates = "SELECT DATE(created_at) AS reporte_fecha, SUM(total_boleta) AS reporte_monto FROM boleta WHERE created_at BETWEEN ? AND ? GROUP BY DATE(created_at) ORDER BY DATE(created_at)";
        public static String obtenerUltimasTresBoletas = "SELECT * FROM boleta ORDER BY created_at DESC LIMIT 3";
        public static String getUltimoMayorIdBoleta = "SELECT MAX(id) AS ultimo_numero_boleta FROM boleta";
        public static String getReporteLast4Months = "SELECT " +
                "m.mes AS reporte_mes, " +
                "IFNULL(SUM(b.total_boleta), 0) AS reporte_monto " +
                "FROM ( " +
                "    SELECT DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL 0 MONTH), '%b') AS mes, DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL 0 MONTH), '%Y-%m') AS orden "
                +
                "    UNION ALL " +
                "    SELECT DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL 1 MONTH), '%b') AS mes, DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL 1 MONTH), '%Y-%m') AS orden "
                +
                "    UNION ALL " +
                "    SELECT DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL 2 MONTH), '%b') AS mes, DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL 2 MONTH), '%Y-%m') AS orden "
                +
                "    UNION ALL " +
                "    SELECT DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL 3 MONTH), '%b') AS mes, DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL 3 MONTH), '%Y-%m') AS orden "
                +
                ") m " +
                "LEFT JOIN boleta b ON DATE_FORMAT(b.created_at, '%Y-%m') = m.orden " +
                "GROUP BY m.mes, m.orden " +
                "ORDER BY m.orden;";
        public static String update = generateUpdateQuery(Boleta.tableName,
                new String[] { Boleta.adminIdField, Boleta.totalField });
    }

    public class detalleBoleta {
        public static String getDetallesByBoletaId = "SELECT * FROM detalle_boleta WHERE boleta_id=?";
        public static String create = generateCreateQuery(DetalleBoleta.tableName,
                new String[] { DetalleBoleta.boletaIdField, DetalleBoleta.productoIdField,
                        DetalleBoleta.cantidadField, DetalleBoleta.precioField, DetalleBoleta.totalField });
    }

    public class utility {

        public static String create = generateCreateQuery(Utility.tableName,
                new String[] { Utility.nombreField, Utility.pesoField, Utility.cantidadField, Utility.adminIdField,
                        Utility.proveedorField });
        public static String update = generateUpdateQuery(Utility.tableName,
                new String[] { Utility.nombreField, Utility.pesoField, Utility.cantidadField });
    }

    public class proveedor {
        public static String create = generateCreateQuery(Proveedor.tableName, new String[] { Proveedor.nombreField,
                Proveedor.rucField, Proveedor.phoneField, Proveedor.addressField });
        public static String update = generateUpdateQuery(Proveedor.tableName, new String[] { Proveedor.nombreField,
                Proveedor.rucField, Proveedor.phoneField, Proveedor.addressField, Proveedor.isVisibleField });
    }

    public class asistencia {
        public static String getUltimasCatorceAsistencias = "WITH RECURSIVE DateSeries AS (" +
                "SELECT CURDATE() AS fecha " +
                "UNION ALL " +
                "SELECT fecha - INTERVAL 1 DAY " +
                "FROM DateSeries " +
                "WHERE fecha > CURDATE() - INTERVAL 13 DAY" +
                ") " +
                "SELECT " +
                "DateSeries.fecha, " +
                "a.asistencia_id, " +
                "a.client_id, " +
                "a.ingreso " +
                "FROM " +
                "DateSeries " +
                "LEFT JOIN " +
                "asistencia a ON DATE(a.ingreso) = DateSeries.fecha AND a.client_id = ? " +
                "ORDER BY " +
                "DateSeries.fecha DESC;";
        public static String create = generateCreateQuery(Asistencia.tableName,
                new String[] { Asistencia.clientIdField, Asistencia.ingresoField });
        public static String obtenerAsistenciasDeHoy = "SELECT COUNT(*) AS contador FROM asistencia WHERE DATE(ingreso) = CURDATE()";
        public static String update = generateUpdateQuery(Asistencia.tableName,
                new String[] { Asistencia.clientIdField, Asistencia.ingresoField });
    }

    public static String getAllTemplate(String tableName) {
        return "SELECT * FROM " + tableName;
    }

    public static String getByIdTemplate(String tableName) {
        return "SELECT * FROM " + tableName + " WHERE " + tableName + "_id=?";
    }

    public static String deleteTemplate(String tableName) {
        return "DELETE from " + tableName + " where " + tableName + "_id=?";
    }

    public static String getTemplateWithConditions(String tableName, String condition) {
        return "SELECT * FROM " + tableName + " WHERE " + condition + "=?";
    }

    public static String getTemplateWithConditions(String tableName, String[] conditions) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM ").append(tableName);
        query.append(" WHERE ");
        for (int i = 0; i < conditions.length; i++) {
            query.append(conditions[i]);
            query.append("=?");
            if (i != conditions.length - 1) {
                query.append(" AND ");
            }
        }
        return query.toString();
    }

    public static String generateCreateQuery(String tableName, String[] columns) {
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO ").append(tableName).append("(");
        for (int i = 0; i < columns.length; i++) {
            query.append(columns[i]);
            if (i != columns.length - 1) {
                query.append(", ");
            }
        }
        query.append(") VALUES(");
        for (int i = 0; i < columns.length; i++) {
            query.append("?");
            if (i != columns.length - 1) {
                query.append(", ");
            }
        }
        query.append(")");
        return query.toString();
    }

    public static String generateUpdateQuery(String tableName, String[] columns) {
        StringBuilder query = new StringBuilder();
        query.append("UPDATE ").append(tableName).append(" SET ");
        for (int i = 0; i < columns.length; i++) {
            query.append(columns[i]).append("=? ");
            if (i != columns.length - 1) {
                query.append(", ");
            }
        }
        query.append("WHERE ").append(tableName).append("_id=?");
        return query.toString();
    }
}
