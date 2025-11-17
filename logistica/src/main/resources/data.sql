-- Datos iniciales para logistica
-- Este archivo se carga automáticamente después de crear las tablas

-- Contenedores de ejemplo
INSERT INTO contenedores (numero_contenedor, tipo, estado, peso, volumen, cliente_id) VALUES
('CONT001', 'STANDARD_20', 'EN_TRANSITO', 15000, 33, 1),
('CONT002', 'STANDARD_40', 'EN_DEPOSITO', 25000, 67, 2),
('CONT003', 'HIGH_CUBE_40', 'DESCARGADO', 20000, 76, 3);
