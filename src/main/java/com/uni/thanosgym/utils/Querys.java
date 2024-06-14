package com.uni.thanosgym.utils;

public class Querys {

    public class plan {
        public static String get = "SELECT * FROM plan WHERE plan_id=?";
        public static String getAll = "select * from plan";
        public static String create = "INSERT INTO plan(name, price, duration_days) VALUES(?, ?, ?)";
        public static String update = "UPDATE plan SET name=?, price=?, duration_days=? WHERE plan_id=?";
        public static String getByName = "SELECT * FROM plan WHERE name=?";
        public static String delete = "DELETE FROM plan WHERE plan_id = ?";
    }

    public class cliente {
        public static String getByDni = "SELECT * FROM client WHERE dni = ?";
        public static String getById = "SELECT * FROM client WHERE client_id = ?";
        public static String getAll = "SELECT * FROM client";
        public static String delete = "DELETE FROM client WHERE client_id = ?";
        public static String getByEmail = "SELECT * FROM client WHERE email = ?";
        public static String update = "UPDATE client SET dni = ?, created_at = ?, subscription_until = ?, full_name = ?, email = ?, address = ?, phone = ? WHERE client_id = ?";
        public static String create = "INSERT INTO client(dni, created_at, subscription_until, full_name, email, address, phone) VALUES(?, ?, ?, ?, ?, ?, ?)";
    }

    public class admin {
        public static String create = "INSERT INTO admin(full_name, username, password, email, phone) VALUES(?, ?, ?, ?, ?)";
        public static String getByUsername = "SELECT * FROM admin WHERE username = ?";
        public static String getById = "SELECT * from admin where admin_id=?";
        public static String verify = "SELECT full_name FROM admin WHERE username=? AND password=?";
        public static String delete = "DELETE from admin where admin_id = ?";
    }

    public class producto {
        public static String create = "INSERT INTO producto(nombre, cantidad, precio) VALUES(?, ?, ?)";
        public static String getByName = "SELECT * FROM producto where nombre=?";
        public static String delete = "DELETE from producto where producto_id=?";
        public static String update = "UPDATE producto SET nombre=?, cantidad=?, precio=? where producto_id=?";
    }

    public class utility {
        public static String create = "INSERT INTO utility(nombre, peso, cantidad) values(?, ?, ?)";
        public static String getByName = "SELECT * FROM utility where nombre=?";
        public static String delete = "DELETE from utility where utility_id=?";
        public static String update = "UPDATE utility SET nombre=?, cantidad=?, peso=? where utility_id=?";
    }

    public class payment {
        public static String create = "INSERT INTO payment(created_at, ticket_code, client_id, plan_id, transaction_code) values(?, ?, ?, ?, ?)";
        public static String get = "select * from payment where payment_id=?";
        public static String delete = "DELETE from payment where payment_id=?";
    }
}
