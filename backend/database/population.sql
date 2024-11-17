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

-- Inserción de productos
INSERT INTO products (id, name, brand_id, category_id, price, description, image_url, stock, active)
VALUES
(1, 'Motorola Edge 50 Fusion', 1, 1, 799999, 'Diferentes colores y materiales con protección dentro y fuera del agua (IP68)', 'https://armoto.vtexassets.com/arquivos/ids/166359', 50, 1),
(2, 'Moto G54', 1, 1, 549999, 'Diseño elegante y cómodo con acabado en cuero vegano color azul índigo', 'https://armoto.vtexassets.com/arquivos/ids/165278', 25, 1),
(3, 'Motorola Edge 40', 1, 1, 699999, 'Pantalla pOLED 144 Hz, procesador MediaTek Dimensity 8020', 'https://armoto.vtexassets.com/arquivos/ids/165180', 30, 1),
(4, 'Moto G84', 1, 1, 459999, 'Pantalla pOLED de 6.5", procesador Snapdragon 695', 'https://armoto.vtexassets.com/arquivos/ids/165303', 40, 1),
(5, 'Motorola Edge 40 Neo', 1, 1, 629999, 'Diseño premium con procesador MediaTek Dimensity 7030', 'https://armoto.vtexassets.com/arquivos/ids/165401', 35, 1),
(6, 'Galaxy S24 Ultra', 2, 1, 1299999, 'El último flagship de Samsung con S Pen integrado', 'https://samsungar.vtexassets.com/arquivos/ids/193728', 20, 1),
(7, 'Galaxy Tab S9 Ultra', 2, 2, 959999, 'Tablet premium con S Pen y pantalla AMOLED de 14.6"', 'https://samsungar.vtexassets.com/arquivos/ids/189239', 40, 1),
(8, 'Galaxy Book3 Pro', 2, 3, 1399999, 'Laptop premium con pantalla AMOLED y procesador Intel Core i7', 'https://samsungar.vtexassets.com/arquivos/ids/188240', 15, 1),
(9, 'Galaxy Buds2 Pro', 2, 4, 159999, 'Auriculares TWS con cancelación activa de ruido', 'https://samsungar.vtexassets.com/arquivos/ids/173104', 45, 1),
(10, 'Galaxy Z Fold5', 2, 1, 1899999, 'Smartphone plegable con pantalla de 7.6"', 'https://samsungar.vtexassets.com/arquivos/ids/189044', 10, 1),
(11, 'iPhone 15 Pro Max', 3, 1, 1799999, 'Titanio. A17 Pro. USB-C. Cámara principal de 48MP', 'https://www.molex.com.ar/wp-content/uploads/2024/06/15promax1-600x600.jpeg', 25, 1),
(12, 'MacBook Pro 16"', 3, 3, 2499999, 'Chip M3 Max, hasta 128GB de RAM unificada', 'https://http2.mlstatic.com/D_NQ_NP_718048-MLC54340285050_032023-O.webp', 15, 1),
(13, 'iPad Pro 12.9"', 3, 2, 1499999, 'Chip M2, pantalla Liquid Retina XDR', 'https://www.sagitariodigital.com.ar/wp-content/uploads/2022/11/IPAD-PRO-M2-1.jpg', 30, 1),
(14, 'AirPods Pro', 3, 4, 219999, 'Cancelación activa de ruido, audio espacial', 'https://ipoint.com.ar/25134-thickbox_default/apple-airpods-pro-2da-generacion.jpg', 50, 1),
(15, 'MacBook Air 15"', 3, 3, 1899999, 'Chip M2, diseño delgado y ligero', 'https://tiendaishop.com.ar/wp-content/uploads/2023/08/macbook-air1522-600x600.jpeg', 20, 1),
(16, 'Sony WH-1000XM5', 4, 4, 249999, 'La última generación en cancelación de ruido', 'https://arsonyb2c.vtexassets.com/arquivos/ids/364192-1600-auto?v=638623507689900000&width=1600&height=auto&aspect=true', 30, 1),
(17, 'Sony WF-1000XM4', 4, 4, 179999, 'Auriculares TWS premium con LDAC', 'https://http2.mlstatic.com/D_NQ_NP_724582-MLU75357013089_032024-O.webp', 25, 1),
(18, 'Sony LinkBuds S', 4, 4, 129999, 'Los auriculares más ligeros con cancelación de ruido', 'https://arsonyb2c.vtexassets.com/arquivos/ids/360515-1600-auto?v=637998283164700000&width=1600&height=auto&aspect=true', 40, 1),
(19, 'Sony WH-CH720N', 4, 4, 89999, 'Auriculares over-ear con cancelación de ruido accesibles', 'https://arsonyb2c.vtexassets.com/arquivos/ids/362228-1600-auto?v=638187375473070000&width=1600&height=auto&aspect=true', 35, 1),
(20, 'Sony WF-C700N', 4, 4, 79999, 'TWS compactos con cancelación de ruido', 'https://arsonyb2c.vtexassets.com/arquivos/ids/362389-1600-auto?v=638204558509900000&width=1600&height=auto&aspect=true', 45, 1),
(21, 'Moto G04', 1, 1, 329999, 'Smartphone económico con gran batería', 'https://armoto.vtexassets.com/arquivos/ids/165823', 60, 1),
(22, 'Motorola Edge 30 Neo', 1, 1, 549999, 'Diseño compacto con pantalla pOLED', 'https://motorolaes.vtexassets.com/arquivos/ids/158472', 30, 1),
(23, 'Galaxy A54 5G', 2, 1, 449999, 'Gama media premium con 5G', 'https://samsungar.vtexassets.com/arquivos/ids/191135', 55, 1),
(24, 'Galaxy Tab A9+', 2, 2, 399999, 'Tablet accesible con gran pantalla', 'https://images.samsung.com/is/image/samsung/p6pim/ar/sm-x210nzaaaro/gallery/ar-galaxy-tab-a9-plus-sm-x210-sm-x210nzaaaro-539822793?$684_547_PNG$', 40, 1),
(25, 'Galaxy Book3', 2, 3, 999999, 'Laptop versátil para productividad', 'https://samsungar.vtexassets.com/arquivos/ids/188282', 20, 1),
(26, 'iPhone 15', 3, 1, 1299999, 'Dynamic Island. Cámara de 48MP. USB-C', 'https://http2.mlstatic.com/D_NQ_NP_812116-MLA71783168214_092023-O.webp', 40, 1),
(27, 'iPad 10th gen', 3, 2, 899999, 'Nuevo diseño todo pantalla con USB-C', 'https://powermaccenter.com/cdn/shop/files/iPad_10th_generation_Wi-Fi_Pink_PDP_Image_Fall23_Position-1-alt__en-US_5ae13873-7308-4de7-8bd7-2a384fd29645.jpg', 35, 1),
(28, 'AirPods Max', 3, 4, 399999, 'Auriculares over-ear premium de Apple', 'https://ar.oneclickstore.com/wp-content/uploads/2023/05/MGYL3BEA-AirPods-Max-Sky-Blue-1-800x800.jpg', 15, 1),
(29, 'Sony WH-CH520', 4, 4, 49999, 'Auriculares inalámbricos básicos', 'https://nissei.com/media/catalog/product/cache/24e3af3791642c18c52611620aeb2e21/1/0/109498_-_1_1.jpg', 70, 1);

INSERT INTO orders (user_id, total_amount, status, created_at, updated_at)
VALUES
(1, 799999.00, 'preparación', '2023-01-15 10:00:00', '2023-01-15 10:00:00'),
(2, 959999.00, 'enviado', '2023-02-20 11:30:00', '2023-02-21 09:00:00'),
(3, 1199999.99, 'entregado', '2023-03-10 14:45:00', '2023-03-12 16:30:00');

INSERT INTO order_details (order_id, product_id, quantity, price)
VALUES
(1, 1, 1, 799999.00),
(2, 3, 1, 959999.00),
(3, 4, 1, 1199999.99);


INSERT INTO reviews (user_id, product_id, comment, rating, created_at, updated_at)
VALUES
(1, 1, 'Teléfono rápido y con gran cámara.', 5, '2023-03-01 10:00:00', '2023-03-01 10:00:00'),
(2, 2, 'El diseño es hermoso, pero esperaba más batería.', 4, '2023-03-15 14:30:00', '2023-03-15 14:30:00'),
(3, 8, 'Perfecta para el trabajo diario y entretenimiento.', 5, '2023-04-02 09:45:00', '2023-04-02 09:45:00');



INSERT INTO orders (user_id, total_amount, status, created_at, updated_at)
VALUES 
(1, 1299999.00, 'entregado', '2024-02-15 09:30:00', '2024-02-17 14:20:00'),
(1, 249999.00, 'enviado', '2024-03-01 11:45:00', '2024-03-01 15:30:00'),
(2, 2699998.00, 'entregado', '2024-02-20 13:15:00', '2024-02-22 16:45:00'),
(2, 629999.00, 'preparación', '2024-03-15 10:20:00', '2024-03-15 10:20:00'),
(3, 2499999.00, 'entregado', '2024-01-25 08:45:00', '2024-01-27 11:30:00'),
(3, 179999.00, 'enviado', '2024-03-10 14:30:00', '2024-03-10 16:15:00');

INSERT INTO order_details (order_id, product_id, quantity, price)
VALUES
(4, 6, 1, 1299999.00),
(5, 16, 1, 249999.00),
(6, 11, 1, 1799999.00),
(6, 14, 1, 219999.00),
(7, 5, 1, 629999.00),
(8, 12, 1, 2499999.00),
(9, 17, 1, 179999.00);

INSERT INTO reviews (user_id, product_id, comment, rating, created_at, updated_at)
VALUES
(1, 6, 'El mejor Samsung que he tenido. La cámara es increíble y el S Pen es muy útil.', 5, '2024-02-25 16:30:00', '2024-02-25 16:30:00'),
(1, 16, 'La cancelación de ruido es espectacular. Perfectos para trabajar.', 5, '2024-03-10 09:15:00', '2024-03-10 09:15:00'),
(2, 11, 'Excelente calidad de construcción. La cámara es extraordinaria.', 5, '2024-03-01 11:20:00', '2024-03-01 11:20:00'),
(2, 14, 'El audio espacial es increíble. Muy cómodos para uso prolongado.', 4, '2024-03-02 14:45:00', '2024-03-02 14:45:00'),
(3, 12, 'Una bestia para desarrollo. El chip M3 Max es increíblemente rápido.', 5, '2024-02-10 13:25:00', '2024-02-10 13:25:00'),
(3, 17, 'Calidad de sonido excepcional. La batería dura muchísimo.', 4, '2024-03-12 17:40:00', '2024-03-12 17:40:00');
