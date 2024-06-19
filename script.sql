create database if not exists thanosgym;
USE thanosgym;

CREATE TABLE if not exists admin (
    admin_id INT NOT NULL AUTO_INCREMENT,
    created_at DATE NOT NULL default current_timestamp,
    full_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone VARCHAR(20),
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    rol ENUM('MASTER', 'EMPLEADO'),
    photo_url VARCHAR(255),
    last_signin DATE NOT NULL default current_timestamp,
    PRIMARY KEY (admin_id)
) Engine=InnoDB;

CREATE TABLE if not exists plan (
    plan_id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    duration_days INT NOT NULL,
    indicador ENUM('V', 'F') NOT NULL,
    PRIMARY KEY (plan_id)
) Engine=InnoDB;

CREATE TABLE if not exists client (
    client_id INT NOT NULL AUTO_INCREMENT,
    dni varchar(20) NOT NULL,
    created_at DATE NOT NULL default current_timestamp,
    full_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    address VARCHAR(255),
    phone VARCHAR(20),
    photo_url VARCHAR(255),
    PRIMARY KEY (client_id)
) Engine=InnoDB;

CREATE TABLE if not exists producto (
    producto_id INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    cantidad int NOT NULL,
    precio decimal(10, 2) NOT NULL,
    photo_url VARCHAR(255),
    PRIMARY KEY (producto_id)
) Engine=InnoDB;


CREATE TABLE if not exists contrato (
    contrato_id INT NOT NULL AUTO_INCREMENT,
    client_id INT NOT NULL,
    plan_id INT NOT NULL,
    admin_id INT NOT NULL,
    transaction_code varchar(100) NOT NULL,

    is_frozen BOOLEAN NOT NULL default false,
    freeze_until DATE not null,
    freeze_count INT NOT NULL default 0,
    last_freeze_date DATE NOT NULL,
    
    created_at DATETIME NOT NULL default CURRENT_TIMESTAMP,
    subscription_until DATE NOT NULL,
    PRIMARY KEY (contrato_id),
    FOREIGN KEY (client_id) REFERENCES client(client_id),
    FOREIGN KEY (plan_id) REFERENCES plan(plan_id),
    FOREIGN KEY (admin_id) REFERENCES admin(admin_id)
) Engine=InnoDB;

create table if not exists boleta (
    boleta_id INT NOT NULL AUTO_INCREMENT,
    client_id INT NOT NULL,
    admin_id INT NOT NULL,
    created_at DATE NOT NULL default current_timestamp,
    total_boleta DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (boleta_id),
    FOREIGN KEY (admin_id) REFERENCES admin(admin_id)
) Engine=InnoDB;

create table if not exists detalle_boleta (
    detalle_boleta_id int not null auto_increment,
    boleta_id int not null,
    producto_id int not null,
    cantidad int not null,
    precio int not null,
    PRIMARY KEY (detalle_boleta_id),
    FOREIGN KEY (boleta_id) REFERENCES boleta(boleta_id),
    FOREIGN KEY (producto_id) REFERENCES producto(producto_id)
) Engine=InnoDB;

CREATE TABLE if not exists utility (
    utility_id INT NOT NULL AUTO_INCREMENT,
    admin_id INT NOT NULL,
    nombre VARCHAR(255) NOT NULL,
    peso DECIMAL(10,2) NOT NULL,
    cantidad INT NOT NULL,
    photo_url VARCHAR(255),
    PRIMARY KEY (utility_id),
    FOREIGN key (admin_id) REFERENCES admin(admin_id)
) Engine=InnoDB;

create table if not exists asistencia (
    asistencia_id INT NOT NULL AUTO_INCREMENT,
    client_id INT NOT NULL,
    ingreso DATETIME NOT NULL,
    primary key (asistencia_id),
    FOREIGN KEY (client_id) REFERENCES client(client_id)
) Engine=InnoDB;
