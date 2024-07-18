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

        // public static String getByUsername = "SELECT * FROM admin WHERE username =
        // ?";
        // public static String verify = "SELECT * FROM admin WHERE username=? AND
        // password=?";
        // public static String verifyMaster = "SELECT * FROM admin WHERE username=? AND
        // password=? AND rol='MASTER'";
    }

    public class plan {

        public static String create = generateCreateQuery(Plan.tableName,
                new String[] { Plan.nameField, Plan.priceField, Plan.durationDaysField });
        public static String update = generateUpdateQuery(Plan.tableName,
                new String[] { Plan.nameField, Plan.priceField, Plan.durationDaysField, Plan.isVisibleField });
    }

    public class client {

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
                new String[] { Producto.nombreField, Producto.cantidadField, Producto.precioField });
        public static String update = generateUpdateQuery(Producto.tableName,
                new String[] { Producto.nombreField, Producto.cantidadField, Producto.precioField });
    }

    public class contrato {

        public static String create = generateCreateQuery(Contrato.tableName,
                new String[] { Contrato.clientIdField, Contrato.planIdField, Contrato.adminIdField,
                        Contrato.transactionCodeField, Contrato.subscriptionUntilField });

        public static String congelar = generateUpdateQuery(Contrato.tableName,
                new String[] { Contrato.subscriptionUntilField, Contrato.isFrozenField, Contrato.freezeCountField,
                        Contrato.freezeUntilField, Contrato.lastFreezeDateField });

    }

    public class boleta {
        public static String create = generateCreateQuery(Boleta.tableName,
                new String[] { Boleta.clientIdField, Boleta.adminIdField, Boleta.totalField });
        public static String update = generateUpdateQuery(Boleta.tableName,
                new String[] { Boleta.adminIdField, Boleta.totalField });
    }

    public class detalleBoleta {
        public static String create = generateCreateQuery(DetalleBoleta.tableName,
                new String[] { DetalleBoleta.boletaIdField, DetalleBoleta.productoIdField,
                        DetalleBoleta.cantidadField,
                        DetalleBoleta.precioField });
        public static String update = generateUpdateQuery(DetalleBoleta.tableName,
                new String[] { DetalleBoleta.boletaIdField, DetalleBoleta.productoIdField,
                        DetalleBoleta.cantidadField,
                        DetalleBoleta.precioField });
    }

    public class utility {

        public static String create = generateCreateQuery(Utility.tableName,
                new String[] { Utility.nombreField, Utility.pesoField, Utility.cantidadField, Utility.adminIdField });
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
        public static String create = generateCreateQuery(Asistencia.tableName,
                new String[] { Asistencia.clientIdField, Asistencia.ingresoField });
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
