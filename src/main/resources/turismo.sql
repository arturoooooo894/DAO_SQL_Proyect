DROP DATABASE IF EXISTS turismo;

CREATE DATABASE turismo;

USE turismo;



-- Tabla de Paquetes Turísticos
CREATE TABLE paquete_turistico (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    destino VARCHAR(100) NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    duracion_dias INT NOT NULL
);

-- Tabla de Clientes
CREATE TABLE cliente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_completo VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    telefono VARCHAR(20)
);

-- Tabla de Reservas
CREATE TABLE reserva (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente INT NOT NULL,
    id_paquete INT NOT NULL,
    fecha_reserva DATE NOT NULL,
    estado ENUM('PENDIENTE', 'CONFIRMADA', 'CANCELADA') DEFAULT 'PENDIENTE',
    FOREIGN KEY (id_cliente) REFERENCES cliente(id)
        ON DELETE CASCADE,
    FOREIGN KEY (id_paquete) REFERENCES paquete_turistico(id)
        ON DELETE CASCADE
);

-- Tabla de Actividades
CREATE TABLE actividad (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_paquete INT NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    costo_adicional DECIMAL(8,2) DEFAULT 0.00,
    FOREIGN KEY (id_paquete) REFERENCES paquete_turistico(id)
        ON DELETE CASCADE
);
