CREATE DATABASE FacturationProject;

USE FacturationProject;

CREATE TABLE USUARIOS (
    id_usuario int(10) AUTO_INCREMENT PRIMARY KEY NOT NULL,
    nombre varchar(30) NOT NULL,
    apellido varchar(30) NOT NULL,
    usuario varchar(15) NOT NULL,
    clave varchar(15) NOT NULL,
    celular varchar(10) NOT NULL
);

INSERT INTO USUARIOS(nombre, apellido, usuario, clave, celular)
VALUE("Andres", "Menchaca", "4ndres", "12345", "77251387");

INSERT INTO USUARIOS(nombre, apellido, usuario, clave, celular)
VALUE("Brayan", "Apaza", "bryan", "1234", "71903372");

SELECT * FROM USUARIOS;
-- SELECT usuario, clave FROM USUARIOS WHERE usuario = "4ndres" AND clave = "12345";
SELECT usuario, clave FROM USUARIOS;
CREATE TABLE CLIENTES (
    id_cliente int(10) AUTO_INCREMENT PRIMARY KEY NOT NULL,
    nombre varchar(30) NOT NULL,
    apellido varchar(30) NOT NULL,
    cedula varchar(10) NOT NULL,
    celular varchar(10) NOT NULL
);

SELECT * FROM CLIENTES;

CREATE TABLE CATEGORIAS (
    id_categoria int(10) AUTO_INCREMENT PRIMARY KEY NOT NULL,
    descripcion varchar(200) NOT NULL
);
SELECT * FROM CATEGORIAS;
SELECT DESCRIPCION FROM CATEGORIAS WHERE DESCRIPCION = '';
TRUNCATE TABLE CATEGORIAS;

CREATE TABLE PRODUCTOS (
    id_producto int(10) AUTO_INCREMENT PRIMARY KEY NOT NULL,
    id_categoria int(10) NOT NULL,
    nombre varchar(200) NOT NULL,
    stock int(11) NOT NULL,
    precio numeric(10,2) NOT NULL,
    descripcion varchar(200) NOT NULL
);
SELECT * FROM PRODUCTOS;

CREATE TABLE FACTURAS (
    id_factura int(1) AUTO_INCREMENT PRIMARY KEY NOT NULL,
    id_cliente int(10) NOT NULL,
    valor_pagar numeric(10,2) NOT NULL,
    fecha_emision date NOT NULL
);

select * from FACTURAS;

CREATE TABLE DETALLES (
    id_detalle int(10) AUTO_INCREMENT PRIMARY KEY NOT NULL,
    id_factura int(10) NOT NULL,
    id_producto int(10) NOT NULL,
    cantidad int(11) NOT NULL,
    precio_unitario numeric(10,2) NOT NULL,
    subtotal numeric(10,2) NOT NULL,
    total_pagar numeric(10,2) NOT NULL
    -- FOREIGN KEY(id_factura) references FACTURAS(id_factura)
);
DROP TABLE DETALLES;
select * from DETALLES;