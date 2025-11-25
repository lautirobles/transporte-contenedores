-- Datos iniciales para logistica
-- Este archivo se carga automáticamente después de crear las tablas

-- ##########################
-- CONTENEDORES
-- ##########################
INSERT INTO contenedores (id, peso, volumen, estado, cliente_id) VALUES
(1, 1000.50, 12.3, 'EN_DEPOSITO', 1),
(2, 850.00, 10.2, 'EN_VIAJE', 2),
(3, 1200.75, 15.0, 'ENTREGADO', 3);


-- ##########################
-- RUTAS
-- ##########################
INSERT INTO rutas (id, cantidad_tramos, cantidad_depositos) VALUES
(1, 3, 2),
(2, 2, 1),
(3, 2, 1),
(4, 2, 1);

-- ##########################
-- TRAMOS
-- ##########################
INSERT INTO tramos (
    id_tramo, origen, destino, tipo, estado,
    costo_aproximado, costo_real,
    fecha_hora_inicio_estimada, fecha_hora_fin_estimada,
    fecha_hora_inicio_real, fecha_hora_fin_real,
    camion_id, ruta_id
) VALUES
-- Tramos de la ruta 1
(1, 'Buenos Aires', 'Rosario', 'TERRESTRE', 'COMPLETADO',
 5000, 5200,
 '2025-01-10 08:00:00', '2025-01-10 14:00:00',
 '2025-01-10 08:15:00', '2025-01-10 14:10:00',
 21, 1),

(2, 'Rosario', 'Cordoba', 'TERRESTRE', 'COMPLETADO',
 4000, 4100,
 '2025-01-11 09:00:00', '2025-01-11 16:00:00',
 '2025-01-11 09:05:00', '2025-01-11 16:20:00',
 22, 1),

(3, 'Cordoba', 'Mendoza', 'TERRESTRE', 'PENDIENTE',
 6000, NULL,
 '2025-01-12 07:00:00', '2025-01-12 15:00:00',
 NULL, NULL,
 23, 1),

-- Tramos de la ruta 2
(4, 'Neuquén', 'Bahía Blanca', 'TERRESTRE', 'COMPLETADO',
 3000, 3100,
 '2025-02-01 10:00:00', '2025-02-01 15:00:00',
 '2025-02-01 10:10:00', '2025-02-01 15:05:00',
 24, 2),

(5, 'Bahía Blanca', 'Mar del Plata', 'TERRESTRE', 'EN_PROCESO',
 3500, NULL,
 '2025-02-02 08:00:00', '2025-02-02 14:00:00',
 '2025-02-02 08:20:00', NULL,
 25, 2),

(6, 'Cordoba', 'Rosario', 'TERRESTRE', 'PENDIENTE',
 6000, NULL,
 '2025-01-12 07:00:00', '2025-01-12 15:00:00',
 NULL, NULL,
 NULL, 3),

(7, 'Rosario', 'Buenos Aires', 'TERRESTRE', 'PENDIENTE',
 6000, NULL,
 '2025-01-12 16:00:00', '2025-01-13 01:00:00',
 NULL, NULL,
 NULL, 3),

(8, 'Cordoba', 'Santa Rosa de la Pampa', 'TERRESTRE', 'PENDIENTE',
 6000, NULL,
 '2025-01-12 07:00:00', '2025-01-12 17:00:00',
 NULL, NULL,
 NULL, 4),

 (9, 'Santa Rosa de la Pampa', 'Buenos Aires', 'TERRESTRE', 'PENDIENTE',
 6000, NULL,
 '2025-01-13 03:00:00', '2025-01-12 12:00:00',
 NULL, NULL,
 NULL, 4);

-- ##########################
-- SOLICITUD
-- ##########################
INSERT INTO solicitudes (
    numero, contenedor_id, cliente_id, estado,
    ruta_id, costo_estimado, tiempo_estimado,
    costo_final, tiempo_real
) VALUES
(1001, 1, 1, 'EN_PROCESO', 1, 15000, 1800, NULL, NULL),
(1002, 2, 2, 'COMPLETADA', 2, 9000, 900, 9200, 940),
(1003, 3, 3, 'PENDIENTE', NULL, 5000, 600, NULL, NULL);


-- ##########################
-- ESTADIA
-- ##########################
INSERT INTO estadias (
    id, deposito_id, contenedor_id, fecha_ingreso, fecha_salida
) VALUES
(1, 101, 1, '2025-01-05 10:00:00', '2025-01-08 08:30:00'),
(2, 102, 2, '2025-02-03 12:00:00', NULL),
(3, 101, 3, '2025-03-01 09:00:00', '2025-03-02 14:00:00');


-- ##########################
-- CAMBIO ESTADO
-- ##########################
INSERT INTO cambios_estado (
    id, contenedor_id, estado_anterior, estado_nuevo, fecha_cambio
) VALUES
(1, 1, 'EN_DEPOSITO', 'EN_VIAJE', '2025-01-09 07:45:00'),
(2, 1, 'EN_VIAJE', 'EN_DEPOSITO', '2025-01-12 19:50:00'),
(3, 2, 'EN_DEPOSITO', 'EN_VIAJE', '2025-02-01 09:10:00'),
(4, 3, 'EN_VIAJE', 'ENTREGADO', '2025-03-02 18:20:00');

-- Ajustar la secuencia de contenedores para que empiece después del ID 3
ALTER TABLE contenedores ALTER COLUMN id RESTART WITH 4;

-- Ajustar también la secuencia de solicitudes (ya usaste 1001, 1002, 1003)
ALTER TABLE solicitudes ALTER COLUMN numero RESTART WITH 1004;