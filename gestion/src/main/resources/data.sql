--
-- Datos Iniciales (DML) para el Microservicio de Gestión/Logística
--

-- ===========================
-- CLIENTES
-- ===========================
-- (id, razon_social, cuil, numero)
INSERT INTO clientes (razon_social, cuil, numero) VALUES
('Constructora Omega S.A.', '20-45123789-3', 1155551234),
('Inversiones del Sur SRL', '30-78945612-8', 3416789012),
('Viviendas Prefabricadas Sol', '27-65432198-0', 3518889999);


-- ===========================
-- CAMIONES
-- ===========================
-- (id, dominio, nombre_transportista, telefono, cap_peso, cap_volumen, disponibilidad, costos, consumo_promedio)
-- Costos y Consumos son cruciales para los cálculos de tarifa
INSERT INTO camiones (dominio, nombre_transportista, telefono, cap_peso, cap_volumen, disponibilidad, costos, consumo_promedio) VALUES
-- Camión 1: Grande, mayor capacidad, mayor consumo, costo base alto
('ABC400', 'Pedro Martínez', 1160001000, 30000, 50, TRUE, 18.00, 0.50),
-- Camión 2: Mediano, estándar
('DEF250', 'Ana Rodríguez', 1160002000, 15000, 30, TRUE, 14.50, 0.35),
-- Camión 3: Pequeño, bajo consumo, actualmente NO disponible (ocupado en otro tramo)
('GHI100', 'Mario Fernández', 1160003000, 8000, 20, FALSE, 10.00, 0.25);


-- ===========================
-- DEPOSITOS
-- ===========================
-- (id, nombre, direccion, latitud, longitud)
-- Se usan coordenadas de referencia en Argentina para probar OSRM
INSERT INTO depositos (nombre, direccion, latitud, longitud) VALUES
-- Depósito ID 1: Córdoba (Origen/Destino típico)
('Deposito CÓRDOBA Norte', 'Bv. Los Granaderos 2000, Córdoba', -31.3789, -64.2050),
-- Depósito ID 2: Rosario (Punto intermedio o transbordo)
('Deposito ROSARIO Puerto', 'Av. Circunvalación S/N, Rosario', -32.9645, -60.6698),
-- Depósito ID 3: Mendoza (Destino final o punto de origen lejano)
('Deposito MENDOZA Oeste', 'Acceso Sur Km 8, Mendoza', -33.0034, -68.8078);


-- ===========================
-- TARIFAS
-- ===========================
-- (id, tipo, valor, peso_min, peso_max)
-- Necesarias para el Requerimiento 8 (Cálculo de Costo)
INSERT INTO tarifas (tipo, valor, peso_min, peso_max) VALUES
-- Valor del Litro de Combustible (Necesario para el cálculo real y estimado)
('LITRO_COMBUSTIBLE', 950.00, 0.00, 0.00),

-- Cargo de Gestión Fijo (Aplica por cada tramo, valor fijo)
('CARGO_GESTION_TRAMO', 2500.00, 0.00, 0.00),

-- Costo de Estadía Diario por Depósito (Valor fijo)
('COSTO_ESTADIA_DIARIO', 1800.00, 0.00, 0.00),

-- Tarifas Base por Volumen para el cálculo ESTIMADO
('BASE_VOLUMEN_BAJO', 0.80, 0.00, 25.00),   -- $0.80 por km base (contenedor < 25m3)
('BASE_VOLUMEN_ALTO', 1.20, 25.01, 99999.00); -- $1.20 por km base (contenedor > 25m3)