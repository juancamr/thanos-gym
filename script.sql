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
    last_signin DATE not null,
    PRIMARY KEY (admin_id)
);

CREATE TABLE if not exists plan (
    plan_id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    duration_days INT NOT NULL,
    indicador ENUM('V', 'F') NOT NULL,
    PRIMARY KEY (plan_id)
);

CREATE TABLE if not exists client (
    client_id INT NOT NULL AUTO_INCREMENT,
    dni INT NOT NULL,
    created_at DATE NOT NULL,
    full_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    address VARCHAR(255),
    is_frozen ENUM('SI', 'NO'),
    photo_url VARCHAR(255),
    phone VARCHAR(20),
    CONSTRAINT PRIMARY KEY (client_id)
);

CREATE TABLE if not exists contrato (
    contrato_id INT NOT NULL AUTO_INCREMENT,
    client_id INT NOT NULL,
    plan_id INT NOT NULL,
    admin_id int not null,
    transaction_code INT NOT NULL,
    created_at DATETIME NOT NULL,
    subscription_until DATE NOT NULL,
    PRIMARY KEY (contrato_id),
    FOREIGN KEY (client_id) REFERENCES client(client_id),
    FOREIGN KEY (plan_id) REFERENCES plan(plan_id),
    FOREIGN KEY (admin_id) REFERENCES admin(admin_id)
);

create table if not exists boleta (
    boleta_id int not null auto_increment,
    client_id int not null,
    admin_id not null,
    created_at date not null,
    total decimal(10,2) not null,
    constraint primary key (boleta_id),
    FOREIGN KEY (admin_id) REFERENCES admin(admin_id)
);

create table if not exists detalle_boleta (
    detalle_boleta_id int not null auto_increment,
    boleta_id int not null,
    producto_id int not null,
    cantidad int not null,
    precio int not null,
    constraint primary key (detalle_boleta_id),
    FOREIGN KEY (boleta_id) REFERENCES boleta(boleta_id),
    FOREIGN KEY (producto_id) REFERENCES producto(producto_id)
);

CREATE TABLE if not exists producto (
    producto_id INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    cantidad int NOT NULL,
    precio decimal(10, 2) NOT NULL,
    photo_url VARCHAR(255),
    PRIMARY KEY (producto_id)
);

CREATE TABLE if not exists utility (
    utility_id INT NOT NULL AUTO_INCREMENT,
    admin_id INT NOT NULL,
    nombre VARCHAR(255) NOT NULL,
    peso DECIMAL(10,2) NOT NULL,
    cantidad INT NOT NULL,
    photo_url VARCHAR(255),
    FOREIGN key (admin_id) REFERENCES admin(admin_id),
    PRIMARY KEY (utility_id)
);

create table if not exists asistencia (
    asistencia_id int not null auto_increment,
    client_id int not null,
    ingreso datetime not null,
    constraint primary key (asistencia_id),
    FOREIGN KEY (client_id) REFERENCES client(client_id)
);
