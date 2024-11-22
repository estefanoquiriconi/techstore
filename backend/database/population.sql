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
TRUNCATE TABLE banners; 

SET FOREIGN_KEY_CHECKS = 1;

-- EL PASSWORD DE LOS USUARIOS ES '12345'
INSERT INTO users (id, first_name, last_name, email, avatar, phone, address, latitude, longitude, password, active)
VALUES
(1, 'Juan', 'Pérez', 'juan.perez@gmail.com', 'https://randomuser.me/api/portraits/men/66.jpg', '385-534-5678', 'Urquiza Sur 420, La Banda', -27.737542, -64.247713, '$2a$10$XWxJSX1SuclmN6k41T1a.ewCJwnYL/VGDUskPBJIugllobQHWvujC', 1),
(2, 'María', 'González', 'maria.gonzalez@gmail.com', 'https://randomuser.me/api/portraits/women/2.jpg', '386-587-6543', 'Av. Moreno Sur 2159, Santiago del Estero', -27.805937, -64.253019, '$2a$10$XWxJSX1SuclmN6k41T1a.ewCJwnYL/VGDUskPBJIugllobQHWvujC', 1),
(3, 'Diego', 'López', 'diego.lopez@gmail.com', 'https://randomuser.me/api/portraits/men/5.jpg', '385-545-6789', 'Pellegrini 532, Santiago del Estero', -27.783514, -64.256903, '$2a$10$XWxJSX1SuclmN6k41T1a.ewCJwnYL/VGDUskPBJIugllobQHWvujC', 1);

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
(1, 'Motorola Edge 50 Fusion', 1, 1, 799999, 'Diferentes colores y materiales con protección dentro y fuera del agua (IP68)', 'https://armoto.vtexassets.com/arquivos/ids/166359', 50, 1),
(2, 'Moto G54', 1, 1, 549999, 'Diseño elegante y cómodo con acabado en cuero vegano color azul índigo', 'https://armoto.vtexassets.com/arquivos/ids/165278', 25, 1),
(3, 'Motorola Edge 40', 1, 1, 699999, 'Pantalla pOLED 144 Hz, procesador MediaTek Dimensity 8020', 'https://armoto.vtexassets.com/arquivos/ids/165180', 30, 1),
(4, 'Moto G84', 1, 1, 459999, 'Pantalla pOLED de 6.5", procesador Snapdragon 695', 'https://armoto.vtexassets.com/arquivos/ids/165303', 40, 1),
(5, 'Motorola Edge 40 Neo', 1, 1, 629999, 'Diseño premium con procesador MediaTek Dimensity 7030', 'https://armoto.vtexassets.com/arquivos/ids/165401', 35, 1),
(6, 'Galaxy S24 Ultra', 2, 1, 1299999, 'El último flagship de Samsung con S Pen integrado', 'https://samsungar.vtexassets.com/arquivos/ids/193728', 20, 1),
(7, 'Galaxy Tab S9 Ultra', 2, 2, 959999, 'Tablet premium con S Pen y pantalla AMOLED de 14.6"', 'https://multipoint.com.ar/Image/0/700_700-Samsung-124868191-ar-galaxy-tab-s9-ultra-wifi-x910-sm-x910nzedaro-537330377--Download-Source--zoom.png', 40, 1),
(8, 'Galaxy Book3 Pro', 2, 3, 1399999, 'Laptop premium con pantalla AMOLED y procesador Intel Core i7', 'https://images.samsung.com/is/image/samsung/p6pim/ar/np960qfg-ka1ar/gallery/ar-galaxy-book3-pro-360-16-inch-np960-np960qfg-ka1ar-539476328?$650_519_PNG$', 15, 1),
(9, 'Galaxy Buds2 Pro', 2, 4, 159999, 'Auriculares TWS con cancelación activa de ruido', 'https://samsungar.vtexassets.com/arquivos/ids/173104', 45, 1),
(10, 'Galaxy Z Fold5', 2, 1, 1899999, 'Smartphone plegable con pantalla de 7.6"', 'https://images.samsung.com/is/image/samsung/p6pim/co/2307/gallery/co-galaxy-z-fold5-f946-sm-f946bzeultc-537223630?$650_519_PNG$', 10, 1),
(11, 'iPhone 15 Pro Max', 3, 1, 1799999, 'Titanio. A17 Pro. USB-C. Cámara principal de 48MP', 'https://tienda.personal.com.ar/images/720/webp/i_Phone_15_Pro_Max_256_GB_Black_Titanium_aed3d2a94d.png', 25, 1),
(12, 'MacBook Pro 16"', 3, 3, 2499999, 'Chip M3 Max, hasta 128GB de RAM unificada', 'https://maximstore.com/_next/image?url=https%3A%2F%2Fback.maximstore.com%2Fstatic%2Fimages%2Fcc7b2739-ffe7-489e-bc66-2d8a3b9e92fb.png&w=1920&q=75', 15, 1),
(13, 'iPad Pro 12.9"', 3, 2, 1499999, 'Chip M2, pantalla Liquid Retina XDR', 'https://www.macstation.com.ar/web/image/product.product/88856/image_1024/[MVV83LE-A]%20iPad%20Pro%2012%22%20M4%20WiFi%20256GB%2C%20Vidrio%20Est%C3%A1ndar%20-%20Negro%20Espacial?unique=b17a31c', 30, 1),
(14, 'AirPods Pro', 3, 4, 219999, 'Cancelación activa de ruido, audio espacial', 'https://www.macstation.com.ar/web/image/product.product/62602/image_1024/%5BMQD83BE-A%5D%20AirPods%20Pro%202da%20Generaci%C3%B3n?unique=8852d96', 50, 1),
(15, 'MacBook Air 15"', 3, 3, 1899999, 'Chip M2, diseño delgado y ligero', 'https://maximstore.com/_next/image?url=https%3A%2F%2Fback.maximstore.com%2Fstatic%2Fimages%2F350cd365-4334-4aa3-8f61-380d022439c9.png&w=1920&q=75', 20, 1),
(16, 'Sony WH-1000XM5', 4, 4, 249999, 'La última generación en cancelación de ruido', 'https://store.sony.com.sg/cdn/shop/products/WH1000XM5_Silver_800x.png?v=1655357681', 30, 1),
(17, 'Sony WF-1000XM4', 4, 4, 179999, 'Auriculares TWS premium con LDAC', 'https://store.sony.com.sg/cdn/shop/products/WH1000XM4Black_800x.png?v=1615519640', 25, 1),
(18, 'Sony LinkBuds S', 4, 4, 129999, 'Los auriculares más ligeros con cancelación de ruido', 'https://store.sony.com.sg/cdn/shop/products/LinkBudSWhite_1b830b10-cafc-4dc9-9d27-44fb0cedebee_800x.png?v=1705667382', 40, 1),
(19, 'Sony WH-CH720N', 4, 4, 89999, 'Auriculares over-ear con cancelación de ruido accesibles', 'https://store.sony.com.sg/cdn/shop/products/CH720NBlueangle2_800x.png?v=1677134154', 35, 1),
(20, 'Sony WF-C700N', 4, 4, 79999, 'TWS compactos con cancelación de ruido', 'https://store.sony.com.sg/cdn/shop/products/WF-C700BlackMAIN_800x.png?v=1721020051', 45, 1),
(21, 'Moto G04', 1, 1, 329999, 'Smartphone económico con gran batería', 'https://armoto.vtexassets.com/arquivos/ids/165823', 60, 1),
(22, 'Motorola Edge 30 Neo', 1, 1, 549999, 'Diseño compacto con pantalla pOLED', 'https://motorolaes.vtexassets.com/arquivos/ids/158472', 30, 1),
(23, 'Galaxy A54 5G', 2, 1, 449999, 'Gama media premium con 5G', 'https://samsungar.vtexassets.com/arquivos/ids/191135', 55, 1),
(24, 'Galaxy Tab A9+', 2, 2, 399999, 'Tablet accesible con gran pantalla', 'https://images.samsung.com/is/image/samsung/p6pim/ar/sm-x210nzaaaro/gallery/ar-galaxy-tab-a9-plus-sm-x210-sm-x210nzaaaro-539822806?$650_519_PNG$', 40, 1),
(25, 'Galaxy Book3', 2, 3, 999999, 'Laptop versátil para productividad', 'https://images.samsung.com/is/image/samsung/p6pim/ar/np750xfg-kb1ar/gallery/ar-galaxy-book3-156-inch-int-np750xfga-np750xfg-kb1ar-536358864?$650_519_PNG$', 20, 1),
(26, 'iPhone 15', 3, 1, 1299999, 'Dynamic Island. Cámara de 48MP. USB-C', 'https://tienda.claro.com.ar/staticContent/Claro/images/catalog/productos/646x1000/70012268_2.webp', 40, 1),
(27, 'iPad 10th gen', 3, 2, 899999, 'Nuevo diseño todo pantalla con USB-C', 'https://www.macstation.com.ar/web/image/product.product/85630/image_1024/%5BMPQ03LE-A%5D%20iPad%2010.9%22%20Wi-Fi%2064GB%20-%20Plata?unique=74eaf44', 35, 1),
(28, 'AirPods Max', 3, 4, 399999, 'Auriculares over-ear premium de Apple', 'https://maximstore.com/_next/image?url=https%3A%2F%2Fback.maximstore.com%2Fstatic%2Fimages%2Ffd25be3b-a589-43a6-9edd-76ab1ea4349e.png&w=1920&q=75', 15, 1),
(29, 'Sony WH-CH520', 4, 4, 49999, 'Auriculares inalámbricos básicos', 'https://store.sony.com.sg/cdn/shop/products/CH520BeigeAngle1_800x.png?v=1677134346', 70, 1);

INSERT INTO orders (id, user_id, total_amount, status, created_at, updated_at)
VALUES
(1, 1, 1399998.00, 'entregado', '2024-10-10 14:30:00', '2024-10-15 16:45:00'), -- Pedido de Juan
(2, 2, 1999998.00, 'entregado', '2024-10-12 10:15:00', '2024-10-17 12:00:00'), -- Pedido de María
(3, 3, 1749998.00, 'entregado', '2024-10-14 09:00:00', '2024-10-19 18:00:00'); -- Pedido de Diego

INSERT INTO order_details (order_id, product_id, quantity, price)
VALUES
(1, 1, 1, 799999.00),  -- Juan compró el Motorola Edge 50 Fusion
(1, 3, 1, 599999.00),  -- Juan compró el Motorola Edge 40
(2, 6, 1, 1299999.00), -- María compró el Galaxy S24 Ultra
(2, 9, 1, 699999.00),  -- María compró los Galaxy Buds2 Pro
(3, 11, 1, 1499999.00),-- Diego compró el iPhone 15 Pro Max
(3, 14, 1, 249999.00); -- Diego compró los AirPods Pro

INSERT INTO reviews (user_id, product_id, comment, rating, created_at, updated_at)
VALUES
(1, 1, 'Increíble rendimiento y duración de batería.', 5, '2024-10-20 11:00:00', '2024-10-20 11:00:00'), -- Reseña de Juan -> Motorola Edge 50 Fusion
(1, 3, 'Pantalla muy fluida, ideal para gaming.', 4, '2024-10-21 14:30:00', '2024-10-21 14:30:00'), -- Reseña de Juan ->  Motorola Edge 40
(2, 6, 'El mejor smartphone que he tenido, la cámara es espectacular.', 5, '2024-10-22 10:15:00', '2024-10-22 10:15:00'), -- Reseña de María -> Galaxy S24 Ultra
(2, 9, 'Auriculares con gran calidad de audio y buen ANC.', 4, '2024-10-23 16:00:00', '2024-10-23 16:00:00'), -- Reseña de María -> Galaxy Buds2 Pro
(3, 11, 'El diseño es excelente, aunque el precio es alto.', 4, '2024-10-25 09:45:00', '2024-10-25 09:45:00'), -- Reseña de Diego -> Iphone 15 Pro Max
(3, 14, 'Cómodos y con buen audio espacial.', 5, '2024-10-26 15:00:00', '2024-10-26 15:00:00'); -- Reseña de Diego -> AirPods Pro