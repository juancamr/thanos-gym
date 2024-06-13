create database if not exists thanosgym;
USE thanosgym;

CREATE TABLE if not exists admin (
    admin_id INT NOT NULL AUTO_INCREMENT,
    full_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone BIGINT,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    PRIMARY KEY (admin_id)
);

CREATE TABLE if not exists plan (
    plan_id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    duration_days INT NOT NULL,
    PRIMARY KEY (plan_id)
);

CREATE TABLE if not exists client (
    client_id INT NOT NULL AUTO_INCREMENT,
    dni INT NOT NULL,
    plan_id INT NOT NULL,
    created_at DATE NOT NULL,
    subscription_until DATE NOT NULL,
    full_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    address VARCHAR(255),
    phone BIGINT,
    CONSTRAINT PRIMARY KEY (client_id),
    CONSTRAINT FOREIGN KEY (plan_id) REFERENCES plan(plan_id)
);

CREATE TABLE if not exists payment (
    payment_id INT NOT NULL AUTO_INCREMENT,
    created_at DATETIME NOT NULL,
    ticket_code INT NOT NULL,
    user_id INT NOT NULL,
    plan_id INT NOT NULL,
    transaction_code INT NOT NULL,
    PRIMARY KEY (payment_id),
    FOREIGN KEY (user_id) REFERENCES client(client_id),
    FOREIGN KEY (plan_id) REFERENCES plan(plan_id)
);

CREATE TABLE if not exists admingeneral (
    admingen_id INT NOT NULL AUTO_INCREMENT,
    full_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone INT NOT NULL,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    rol ENUM('GENERAL', 'NORMAL'),
    PRIMARY KEY (admingen_id)
);


CREATE TABLE if not exists productos (
    productos_id INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    cantidad VARCHAR(255) NOT NULL,
    precio INT NOT NULL,
    PRIMARY KEY (productos_id)
);


CREATE TABLE if not exists utilities (
    utilities_id INT NOT NULL AUTO_INCREMENT,
    tipo VARCHAR(255) NOT NULL,
    peso DECIMAL(10,2) NOT NULL,
    cantidad INT NOT NULL,
    PRIMARY KEY (utilities_id)
);
