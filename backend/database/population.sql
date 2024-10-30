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

INSERT INTO users (id, first_name, last_name, email, avatar, phone, address, password, active, registration_code, recoverpass_code)
VALUES
(1, 'Juan', 'Pérez', 'juan.perez@gmail.com', 'https://randomuser.me/api/portraits/men/1.jpg', '11-1234-5678', 'Av. Corrientes 1234, CABA', 'hashed_password', 1, 'REG123', 'REC456'),
(2, 'María', 'González', 'maria.gonzalez@gmail.com', 'https://randomuser.me/api/portraits/women/2.jpg', '351-987-6543', 'Bv. San Juan 567, Córdoba', 'hashed_password', 1, 'REG124', 'REC457'),
(3, 'Diego', 'López', 'diego.lopez@gmail.com', 'https://randomuser.me/api/portraits/men/5.jpg', '381-345-6789', 'Laprida 2233, San Miguel de Tucumán', 'hashed_password', 1, 'REG127', 'REC460');

INSERT INTO categories (id, name, image_url)
VALUES
(1, 'Celulares', 'https://armoto.vtexassets.com/arquivos/ids/165316-1200-auto?v=638350946189500000&width=1200&height=auto&aspect=true'),
(2, 'Tablets', 'https://samsungar.vtexassets.com/arquivos/ids/192816-1200-auto?width=1200&height=auto&aspect=true'),
(3, 'Notebooks', 'https://ar-media.hptiendaenlinea.com/catalog/product/cache/74c1057f7991b4edb2bc7bdaa94de933/9/1/91S43LA-1_T1717517862.png'),
(4, 'Auriculares', 'https://www.jbl.com.ar/dw/image/v2/AAUJ_PRD/on/demandware.static/-/Sites-masterCatalog_Harman/default/dw4df8ac7e/01.JBL_Tune%20520BT_Product%20Image_Hero_Blue.png?sw=537&sfrm=png');

INSERT INTO banners (id, name, image_url)
VALUES
(1, 'Samsug 55 años', 'https://samsungar.vtexassets.com/assets/vtex.file-manager-graphql/images/d5e315d3-ab8f-46a4-b13a-3d24c5456839___72922747f5367b3f8567058060478202.png'),
(2, 'Samsung Galaxy S24 FE', 'https://samsungar.vtexassets.com/assets/vtex.file-manager-graphql/images/1fe03eec-519e-4f4d-a5ac-b3c916faeabc___2cded0e383aee58d49352e9614f8aa6e.png'),
(3, 'Samsung Beneficio exclusivo', 'https://samsungar.vtexassets.com/assets/vtex.file-manager-graphql/images/61f85c50-47cb-4a63-a7ce-53df734134e2___297e6638a8668914e0049d758187c675.png');

INSERT INTO brands (id, name)
VALUES
(1, 'Motorola'),
(2, 'Samsung'),
(3, 'Apple'),
(4, 'Sony');

INSERT INTO products (id, name, brand_id, category_id, price, description, image_url, stock, active)
VALUES
(1, 'Motorola Edge 50 Fusion', 1, 1, 799999, 'Diferentes colores y materiales con protección dentro y fuera del agua (IP68)', 'https://armoto.vtexassets.com/arquivos/ids/166359-1200-auto?v=638537819436230000&width=1200&height=auto&aspect=true', 50, 1),
(2, 'Moto G54', 1, 1, 549999, 'Diseño elegante y cómodo. La cubierta de la cámara de aluminio y el acabado en cuero vegano color azul índigo, hace que el moto g54 se destaque.', 'https://armoto.vtexassets.com/arquivos/ids/165278-1200-auto?v=638350936558330000&width=1200&height=auto&aspect=true', 25, 1),
(3, 'Galaxy Tab S9 Ultra', 2, 2, 959999, 'ook cover Keyboard (Wi-Fi) Graphite 256GB', 'https://images.samsung.com/is/image/samsung/p6pim/ar/ef-zx912pwegww/gallery/ar-galaxy-tab-s9-ultra-notepaper-screen-white-ef-zx912pwegww-ef-zx912pwegww-front-white-537976200?$650_519_PNG$', 40, 1),
(4, 'MacBook Pro', 3, 3, 1499999.99, 'La MacBook Pro da un salto al futuro con los chips M3, M3 Pro y M3 Max. Fabricados con tecnología de 3 nanómetros y una nueva arquitectura de GPU.', 'https://cdsassets.apple.com/live/SZLF0YNV/images/sp/111901_mbp16-gray.png', 30, 1),
(5, 'iPad Air', 3, 2, 1199999.99, 'El rediseñado iPad Air. Chip M2. Cámara frontal en horizontal. Hermosos colores.', 'https://www.apple.com/v/ipad-air/x/images/overview/hero/hero_endframe__fvm22b45e5me_large.png', 35, 1),
(6, 'Sony WH-1000XM4', 4, 4, 89999.99, 'Auriculares con cancelación de ruido', 'https://www.sony.com.ar/image/f3f1e4cde7ef052790bfd64ce85f39bb?fmt=png-alpha&wid=1200', 25, 1);

INSERT INTO orders (user_id, total_amount, status, created_at, updated_at)
VALUES
(1, 799999.00, 'preparación', '2023-01-15 10:00:00', '2023-01-15 10:00:00'), -- Motorola Edge 50 Fusion
(2, 959999.00, 'enviado', '2023-02-20 11:30:00', '2023-02-21 09:00:00'), -- Tablet S9 Ultra
(3, 1199999.99, 'entregado', '2023-03-10 14:45:00', '2023-03-12 16:30:00'); -- MacBook Pro

INSERT INTO order_details (order_id, product_id, quantity, price)
VALUES
(1, 1, 1, 799999.00),  -- Motorola Edge 50 Fusion
(2, 3, 1, 959999.00),  -- Galaxy Tab S9 Ultra
(3, 4, 1, 1199999.99); -- MacBook Pro


INSERT INTO reviews (user_id, product_id, comment, rating, created_at, updated_at)
VALUES
(1, 1, 'Teléfono rápido y con gran cámara.', 5, '2023-03-01 10:00:00', '2023-03-01 10:00:00'), -- Motorola Edge 50 Fusion
(2, 3, 'El diseño es hermoso, pero esperaba más batería.', 4, '2023-03-15 14:30:00', '2023-03-15 14:30:00'), -- Moto G54
(3, 4, 'Perfecta para el trabajo diario y entretenimiento.', 5, '2023-04-02 09:45:00', '2023-04-02 09:45:00'); -- Galaxy Tab S9 Ultra
