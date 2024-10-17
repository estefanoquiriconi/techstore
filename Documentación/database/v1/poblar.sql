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

SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO users (first_name, last_name, email, avatar, phone, address, password, active, registration_code, recoverpass_code)
VALUES
('Juan', 'Pérez', 'juan.perez@gmail.com', 'https://randomuser.me/api/portraits/men/1.jpg', '11-1234-5678', 'Av. Corrientes 1234, CABA', 'hashed_password', 1, 'REG123', 'REC456'),
('María', 'González', 'maria.gonzalez@hotmail.com', 'https://randomuser.me/api/portraits/women/2.jpg', '351-987-6543', 'Bv. San Juan 567, Córdoba', 'hashed_password', 1, 'REG124', 'REC457'),
('Carlos', 'Rodríguez', 'carlos.rodriguez@yahoo.com.ar', 'https://randomuser.me/api/portraits/men/3.jpg', '341-234-5678', 'Pellegrini 890, Rosario', 'hashed_password', 0, 'REG125', 'REC458'),
('Ana', 'Fernández', 'ana.fernandez@gmail.com', 'https://randomuser.me/api/portraits/women/4.jpg', '261-876-5432', 'San Martín 1122, Mendoza', 'hashed_password', 1, 'REG126', 'REC459'),
('Diego', 'López', 'diego.lopez@outlook.com', 'https://randomuser.me/api/portraits/men/5.jpg', '381-345-6789', 'Laprida 2233, San Miguel de Tucumán', 'hashed_password', 1, 'REG127', 'REC460');

INSERT INTO categories (name)
VALUES
('Celulares'),
('Tablets'),
('Notebooks'),
('Auriculares');

INSERT INTO brands (name)
VALUES
('Apple'),
('Samsung'),
('Dell'),
('Sony'),
('HP');

INSERT INTO products (name, brand_id, category_id, price, description, image_url, stock, active)
VALUES
('iPhone 13', 1, 1, 299999.99, 'Último modelo de iPhone', 'https://example.com/iphone13.jpg', 50, 1),
('Galaxy S21', 2, 1, 259999.99, 'Teléfono Android de gama alta', 'https://example.com/galaxys21.jpg', 40, 1),
('MacBook Pro', 1, 3, 499999.99, 'Notebook potente para profesionales', 'https://example.com/macbookpro.jpg', 30, 1),
('iPad Air', 1, 2, 199999.99, 'Tablet liviana y versátil', 'https://example.com/ipadair.jpg', 35, 1),
('Sony WH-1000XM4', 4, 4, 89999.99, 'Auriculares con cancelación de ruido', 'https://example.com/sonywh1000xm4.jpg', 25, 1);

INSERT INTO orders (user_id, total_amount, status, created_at, updated_at)
VALUES
(1, 599999.98, 'preparación', '2023-01-15 10:00:00', '2023-01-15 10:00:00'),
(2, 259999.99, 'enviado', '2023-02-20 11:30:00', '2023-02-21 09:00:00'),
(3, 699999.98, 'entregado', '2023-03-10 14:45:00', '2023-03-12 16:30:00'),
(4, 199999.99, 'preparación', '2023-04-05 09:15:00', '2023-04-05 09:15:00'),
(5, 889999.97, 'enviado', '2023-05-01 16:00:00', '2023-05-02 10:30:00');

INSERT INTO order_details (order_id, product_id, quantity, price)
VALUES
(1, 1, 2, 299999.99),
(2, 2, 1, 259999.99),
(3, 3, 1, 499999.99),
(3, 4, 1, 199999.99),
(4, 4, 1, 199999.99),
(5, 1, 1, 299999.99),
(5, 3, 1, 499999.99),
(5, 5, 1, 89999.99);

INSERT INTO reviews (user_id, product_id, comment, rating, created_at, updated_at)
VALUES
(1, 2, '¡Buenísimo celular, la cámara es una locura!', 5, '2023-03-01 10:00:00', '2023-03-01 10:00:00'),
(2, 1, 'Buen teléfono, pero la batería podría durar más', 4, '2023-03-15 14:30:00', '2023-03-15 14:30:00'),
(3, 3, 'Excelente notebook para trabajo y entretenimiento', 5, '2023-04-02 09:45:00', '2023-04-02 09:45:00'),
(4, 4, 'Linda tablet, pero un poco cara', 3, '2023-04-20 16:15:00', '2023-04-20 16:15:00'),
(5, 5, 'Compactos y potentes, perfectos para viajar', 4, '2023-05-10 11:30:00', '2023-05-10 11:30:00');