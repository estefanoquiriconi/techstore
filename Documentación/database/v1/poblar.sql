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
('Motorola'),
('Samsung'),
('Dell'),
('Sony'),
('HP');

INSERT INTO products (name, brand_id, category_id, price, description, image_url, stock, active)
VALUES
('Motorola Edge 50 Fusion', 1, 1, 799999, 'Diferentes colores y materiales con protección dentro y fuera del agua (IP68)', 'https://armoto.vtexassets.com/arquivos/ids/166359-1200-auto?v=638537819436230000&width=1200&height=auto&aspect=true', 50, 1),
('Moto G54', 1, 1, 549999, 'Diseño elegante y cómodo. La cubierta de la cámara de aluminio y el acabado en cuero vegano color azul índigo, hace que el moto g54 se destaque.', 'https://armoto.vtexassets.com/arquivos/ids/165278-1200-auto?v=638350936558330000&width=1200&height=auto&aspect=true', 25, 1),
('Galaxy Tab S9 Ultra', 2, 2, 959999, 'ook cover Keyboard (Wi-Fi) Graphite 256GB', 'https://images.samsung.com/is/image/samsung/p6pim/ar/ef-zx912pwegww/gallery/ar-galaxy-tab-s9-ultra-notepaper-screen-white-ef-zx912pwegww-ef-zx912pwegww-front-white-537976200?$650_519_PNG$', 40, 1),
('MacBook Pro', 1, 3, 1499999.99, 'La MacBook Pro da un salto al futuro con los chips M3, M3 Pro y M3 Max. Fabricados con tecnología de 3 nanómetros y una nueva arquitectura de GPU.', 'https://cdsassets.apple.com/live/SZLF0YNV/images/sp/111901_mbp16-gray.png', 30, 1),
('iPad Air', 1, 2, 1199999.99, 'El rediseñado iPad Air. Chip M2. Cámara frontal en horizontal. Hermosos colores.', 'https://www.apple.com/v/ipad-air/x/images/overview/hero/hero_endframe__fvm22b45e5me_large.png', 35, 1),
('Sony WH-1000XM4', 4, 4, 89999.99, 'Auriculares con cancelación de ruido', 'https://www.sony.com.ar/image/f3f1e4cde7ef052790bfd64ce85f39bb?fmt=png-alpha&wid=1200', 25, 1);

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