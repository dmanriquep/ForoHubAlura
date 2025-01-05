CREATE TABLE Respuesta (
    id INT AUTO_INCREMENT PRIMARY KEY,
    contenido TEXT NOT NULL,
    autor VARCHAR(100) NOT NULL,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    publicacion_id INT NOT NULL,
    FOREIGN KEY (publicacion_id) REFERENCES Publicacion(id) ON DELETE CASCADE
);