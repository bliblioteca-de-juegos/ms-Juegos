CREATE TABLE juegos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    descripcion VARCHAR(100) NOT NULL,
    precio DOUBLE NOT NULL,
    categoria_id BIGINT NOT NULL,
    clasificacion_id BIGINT NOT NULL
);
