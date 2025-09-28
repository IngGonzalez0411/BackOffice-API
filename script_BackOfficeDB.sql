-- =====================================================
-- CREACIÓN DE BASE DE DATOS
-- =====================================================
CREATE DATABASE BackOfficeDB;
GO

USE BackOfficeDB;
GO

-- =====================================================
-- TABLA: USUARIOS
-- =====================================================
CREATE TABLE Usuarios (
    id INT IDENTITY(1,1) PRIMARY KEY,
    nombreCompleto NVARCHAR(150) NOT NULL,
    username NVARCHAR(50) NOT NULL UNIQUE,
    clave NVARCHAR(255) NOT NULL, -- encriptada (ej: BCrypt o SHA-256)
    fechaCreacion DATETIME DEFAULT GETDATE(),
    fechaUltimoIngreso DATETIME NULL,
    nivelAcceso NVARCHAR(20) CHECK (nivelAcceso IN ('USER','ADMIN')) NOT NULL,
    estado NVARCHAR(20) CHECK (estado IN ('ACTIVO','DESACTIVADO')) NOT NULL
);
GO

-- =====================================================
-- TABLA: CATEGORÍAS
-- =====================================================
CREATE TABLE Categorias (
    id INT IDENTITY(1,1) PRIMARY KEY,
    nombre NVARCHAR(100) NOT NULL UNIQUE,
    fechaCreacion DATETIME DEFAULT GETDATE(),
    fechaActualizacion DATETIME DEFAULT GETDATE(),
    estado NVARCHAR(20) CHECK (estado IN ('ACTIVO','DESACTIVADO')) NOT NULL
);
GO

-- =====================================================
-- TABLA: PRODUCTOS
-- =====================================================
CREATE TABLE Productos (
    id INT IDENTITY(1,1) PRIMARY KEY,
    nombre NVARCHAR(150) NOT NULL,
    categoriaId INT NOT NULL,
    costo DECIMAL(10,2) NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    tags NVARCHAR(255), -- lista de tags separada por comas
    fechaCreacion DATETIME DEFAULT GETDATE(),
    fechaActualizacion DATETIME DEFAULT GETDATE(),
    estado NVARCHAR(20) CHECK (estado IN ('ACTIVO','DESACTIVADO')) NOT NULL,
    CONSTRAINT FK_Productos_Categorias FOREIGN KEY (categoriaId) REFERENCES Categorias(id)
);
GO

-- =====================================================
-- DATOS DE PRUEBA
-- =====================================================

-- Usuarios (ejemplo de claves encriptadas con BCrypt simuladas)
INSERT INTO Usuarios (nombreCompleto, username, clave, nivelAcceso, estado)
VALUES
('Administrador General', 'admin', '$2a$10$zH/fqi850vx3kq9uj0RLLuTjUNfYu.ZSkqF9Q4gdXo0dRq7snHTfe', 'ADMIN', 'ACTIVO'), 
-- clave: admin123
('Usuario Normal', 'usuario', '$2a$10$HtC208j5ra3GMyqpguL1weHgdph4.C9w/GaVMc7Pz9MF1vbha8g3S', 'USER', 'ACTIVO'); 
-- clave: user123

-- Categorías
INSERT INTO Categorias (nombre, estado)
VALUES
('Electrónica', 'ACTIVO'),
('Ropa', 'ACTIVO'),
('Juguetes', 'ACTIVO');

-- Productos
INSERT INTO Productos (nombre, categoriaId, costo, precio, tags, estado)
VALUES
('Smartphone XYZ', 1, 300.00, 450.00, 'android,5G,128GB', 'ACTIVO'),
('Laptop ABC', 1, 800.00, 1200.00, 'intel,i7,16GB', 'ACTIVO'),
('Camiseta Roja', 2, 5.00, 12.00, 'ropa,algodon,rojo', 'ACTIVO'),
('Muñeco de Acción', 3, 10.00, 20.00, 'juguetes,coleccion', 'ACTIVO');
