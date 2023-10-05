CREATE DATABASE hotel_alura;
USE hotel_alura;

CREATE TABLE reservas (
    id INT NOT NULL AUTO_INCREMENT,
    fechaEntrada DATE NOT NULL,
    fechaSalida DATE NOT NULL,
    valor VARCHAR(10) NOT NULL,
    formaPago VARCHAR(45) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE huespedes (
    id INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(45) NOT NULL,
    apellido VARCHAR(45) NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    nacionalidad VARCHAR(45) NOT NULL,
    telefono VARCHAR(45) NOT NULL,
    idReserva INT FOREIGN KEY REFERENCES reservas(id),
    PRIMARY KEY (id)
);

