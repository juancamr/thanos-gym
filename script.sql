create database if not exists thanosgym;
USE thanosgym;

CREATE TABLE if not exists admin (
    admin_id INT NOT NULL AUTO_INCREMENT,
    full_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone VARCHAR(20),
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    rol ENUM('MASTER', 'EMPLEADO'),
    photo_url VARCHAR(255),
    created_at DATETIME NOT NULL default current_timestamp,
    last_signin DATETIME NOT NULL default current_timestamp,
    PRIMARY KEY (admin_id)
) Engine=InnoDB;

CREATE TABLE if not exists plan (
    plan_id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    duration_days INT NOT NULL,
    is_visible boolean NOT NULL default true,
    PRIMARY KEY (plan_id)
) Engine=InnoDB;

CREATE TABLE if not exists client (
    client_id INT NOT NULL AUTO_INCREMENT,
    dni varchar(20) NOT NULL,
    full_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    address VARCHAR(255),
    phone VARCHAR(20),
    photo_url VARCHAR(255),
    created_at DATETIME NOT NULL default current_timestamp,
    PRIMARY KEY (client_id)
) Engine=InnoDB;

CREATE TABLE if not exists producto (
    producto_id INT NOT NULL AUTO_INCREMENT,
    codigo VARCHAR(20) NOT NULL,
    nombre VARCHAR(255) NOT NULL,
    PRIMARY KEY (producto_id)
) Engine=InnoDB;

CREATE TABLE if not exists detalle_producto (
    detalle_producto_id int not null auto_increment,
    producto_id int not null,
    stock int not null,
    fecha_vencimiento DATE NOT NULL,
    created_at DATETIME NOT NULL default current_timestamp,
    precio decimal(10, 2) NOT NULL,
    PRIMARY KEY (detalle_producto_id),
    FOREIGN KEY (producto_id) REFERENCES producto(producto_id)
) Engine=InnoDB;

CREATE TABLE if not exists contrato (
    contrato_id INT NOT NULL AUTO_INCREMENT,
    client_id INT NOT NULL,
    plan_id INT NOT NULL,
    admin_id INT NOT NULL,
    transaction_code varchar(100) NOT NULL,
    freeze_until DATE,
    last_freeze_date DATE,
    subscription_until DATE NOT NULL,
    created_at DATETIME NOT NULL default current_timestamp,
    is_frozen BOOLEAN NOT NULL default false,
    freeze_count INT NOT NULL default 0,
    PRIMARY KEY (contrato_id),
    FOREIGN KEY (client_id) REFERENCES client(client_id),
    FOREIGN KEY (plan_id) REFERENCES plan(plan_id),
    FOREIGN KEY (admin_id) REFERENCES admin(admin_id)
) Engine=InnoDB;

create table if not exists boleta (
    boleta_id INT NOT NULL AUTO_INCREMENT,
    client_id INT NOT NULL,
    admin_id INT NOT NULL,
    total_boleta DECIMAL(10, 2) NOT NULL,
    created_at DATETIME NOT NULL default current_timestamp,
    PRIMARY KEY (boleta_id),
    FOREIGN KEY (admin_id) REFERENCES admin(admin_id)
) Engine=InnoDB;

create table if not exists detalle_boleta (
    detalle_boleta_id int not null auto_increment,
    boleta_id int not null,
    producto_id int not null,
    cantidad int not null,
    precio decimal(10, 2) not null,
    total decimal(10, 2) not null,
    PRIMARY KEY (detalle_boleta_id),
    FOREIGN KEY (boleta_id) REFERENCES boleta(boleta_id),
    FOREIGN KEY (producto_id) REFERENCES producto(producto_id)
) Engine=InnoDB;

CREATE TABLE if not exists proveedor (
    proveedor_id INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    ruc VARCHAR(20) NOT NULL,
    phone VARCHAR(20),
    address VARCHAR(255),
    is_visible boolean NOT NULL default true,
    created_at DATETIME NOT NULL default current_timestamp,
    PRIMARY KEY (proveedor_id),
    UNIQUE KEY (ruc)
) Engine=InnoDB;

CREATE TABLE if not exists utility (
    utility_id INT NOT NULL AUTO_INCREMENT,
    admin_id INT NOT NULL,
    proveedor_id INT NOT NULL,
    nombre VARCHAR(255) NOT NULL,
    peso DECIMAL(10,2) NOT NULL,
    cantidad INT NOT NULL,
    photo_url VARCHAR(255),
    created_at DATETIME NOT NULL default current_timestamp,
    PRIMARY KEY (utility_id),
    FOREIGN key (admin_id) REFERENCES admin(admin_id),
    FOREIGN key (proveedor_id) REFERENCES proveedor(proveedor_id)
) Engine=InnoDB;

create table if not exists asistencia (
    asistencia_id INT NOT NULL AUTO_INCREMENT,
    client_id INT NOT NULL,
    ingreso DATETIME NOT NULL,
    primary key (asistencia_id),
    FOREIGN KEY (client_id) REFERENCES client(client_id)
) Engine=InnoDB;
