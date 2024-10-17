USE techstore_db;
-- Script SQL para generar datos de prueba

-- Desactivar verificación de claves foráneas temporalmente
SET FOREIGN_KEY_CHECKS = 0;

-- Limpiar tablas existentes
TRUNCATE TABLE users;
TRUNCATE TABLE categories;
TRUNCATE TABLE brands;
TRUNCATE TABLE products;
TRUNCATE TABLE orders;
TRUNCATE TABLE order_details;
TRUNCATE TABLE reviews;
TRUNCATE TABLE coupons;
TRUNCATE TABLE coupon_usage;
TRUNCATE TABLE payment_methods;
TRUNCATE TABLE product_images;
TRUNCATE TABLE product_specifications;

SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO categories (name) VALUES 
('Celulares'), 
('Notebooks'), 
('Tablets'), 
('Auriculares');

INSERT INTO brands (name) VALUES 
('Samsung'), 
('Apple'), 
('LG'), 
('Sony'), 
('HP');

INSERT INTO users (first_name, last_name, email, avatar, phone, address, latitude, longitude, password, active) VALUES
('Martín', 'Gómez', 'martin.gomez@gmail.com', NULL, '1156783456', 'Calle Falsa 123, Buenos Aires', -34.603722, -58.381592, 'hashed_password1', TRUE),
('Lucía', 'Fernández', 'lucia.fernandez@gmail.com', NULL, '1123456789', 'Av. Rivadavia 4567, Buenos Aires', -34.609869, -58.388625, 'hashed_password2', TRUE),
('Carlos', 'Pérez', 'carlos.perez@gmail.com', NULL, '1165432198', 'Calle 50, La Plata', -34.920494, -57.953565, 'hashed_password3', TRUE);

INSERT INTO payment_methods (name) VALUES 
('Efectivo'), 
('MercadoPago');

INSERT INTO coupons (code, discount, max_uses, start_date, end_date, active) VALUES
('BLACKFRIDAY', 10.00, 100, '2024-10-01 00:00:00', '2024-12-31 23:59:59', TRUE),
('HOTSALE', 15.00, 50, '2024-10-01 00:00:00', '2024-12-31 23:59:59', TRUE);

INSERT INTO products (name, brand_id, category_id, price, description, image_url, stock, active) VALUES
('Samsung Galaxy S23', 1, 1, 450000.00, 'Último modelo de la serie Galaxy con cámara avanzada.', NULL, 50, TRUE),
('MacBook Air M2', 2, 2, 800000.00, 'Notebook ultraligera con chip M2.', NULL, 20, TRUE),
('Auriculares Sony WH-1000XM4', 4, 4, 150000.00, 'Auriculares inalámbricos con cancelación de ruido.', NULL, 100, TRUE),
('Notebook HP Pavilion', 5, 2, 500000.00, 'Notebook de alto rendimiento para uso profesional.', NULL, 25, TRUE);

INSERT INTO product_images (product_id, image_url) VALUES
(1, 'https://example.com/images/samsung_galaxy_s23.jpg'),
(2, 'https://example.com/images/macbook_air_m2.jpg');

INSERT INTO product_specifications (product_id, spec_name, spec_value) VALUES
(1, 'Pantalla', '6.1 pulgadas'),
(1, 'Batería', '3900 mAh'),
(2, 'Procesador', 'Apple M2'),
(2, 'Almacenamiento', '256 GB SSD');

INSERT INTO orders (user_id, total_amount, status, coupon_id, payment_method_id) VALUES
(1, 450000.00, 'preparación', 1, 1),
(2, 800000.00, 'enviado', NULL, 2);

INSERT INTO order_details (order_id, product_id, quantity, price) VALUES
(1, 1, 1, 450000.00),
(2, 2, 1, 800000.00);

INSERT INTO coupon_usage (user_id, coupon_id) VALUES
(1, 1);

INSERT INTO reviews (user_id, product_id, comment, rating) VALUES
(1, 1, 'Excelente teléfono, muy rápido y con buena cámara.', 5),
(2, 2, 'Muy buena notebook, ideal para diseño gráfico.', 4);