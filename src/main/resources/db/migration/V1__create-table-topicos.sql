CREATE TABLE topicos (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    mensaje TEXT NOT NULL,
    fecha_creacion DATETIME NOT NULL,
    status VARCHAR(50) NOT NULL,
    nombre VARCHAR(255) NOT NULL,
    curso VARCHAR(255) NOT NULL
);
