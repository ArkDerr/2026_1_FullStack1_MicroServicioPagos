CREATE TABLE Pagos (
    idPagos BIGINT AUTO_INCREMENT PRIMARY KEY,
    montoPagado INT NOT NULL,
    estadoPago VARCHAR(255) NOT NULL,
    idFiesta INT NOT NULL,
    nombreFiesta VARCHAR(255) NOT NULL,
    fechaPago DATE NOT NULL
);