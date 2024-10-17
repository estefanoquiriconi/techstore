# TechStore Database

## Nuevos Requerimientos
1. **Ubicación de Usuario**: Ahora se almacena la latitud y longitud de los usuarios para facilitar la sugerencia de direcciones en el checkout.
2. **Imágenes Múltiples de Productos**: Se permite almacenar múltiples imágenes por producto.
3. **Especificaciones Detalladas de Productos**: Se pueden almacenar especificaciones técnicas detalladas para cada producto.
4. **Métodos de Pago**: Se ha implementado una tabla para manejar diferentes métodos de pago.
5. **Uso Único de Cupones por Usuario**: Cada usuario solo puede usar un cupón específico una vez.

## Estructura de la Base de Datos

### Tablas Principales
- `users`: Almacena información de los usuarios, incluyendo su ubicación.
- `products`: Contiene los detalles básicos de los productos.
- `categories`: Categorías de productos.
- `brands`: Marcas de productos.
- `orders`: Pedidos realizados por los usuarios.
- `order_details`: Detalles de los productos en cada pedido.
- `reviews`: Reseñas de productos por los usuarios.
- `coupons`: Información sobre cupones de descuento.

### Nuevas Tablas
- `product_images`: Almacena múltiples imágenes por producto.
- `product_specifications`: Guarda especificaciones técnicas detalladas de los productos.
- `payment_methods`: Métodos de pago disponibles.
- `coupon_usage`: Registra el uso de cupones por usuario.

## Cambios Importantes

### 1. Tabla de Usuarios
Se han añadido campos para almacenar la ubicación del usuario:
```sql
ALTER TABLE users
ADD COLUMN latitude DECIMAL(10, 8),
ADD COLUMN longitude DECIMAL(11, 8);
```

### 2. Imágenes de Productos
Nueva tabla para manejar múltiples imágenes por producto:
```sql
CREATE TABLE product_images (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    product_id BIGINT,
    image_url VARCHAR(255),
    FOREIGN KEY (product_id) REFERENCES products(id)
);
```

### 3. Especificaciones de Productos
Nueva tabla para almacenar especificaciones detalladas:
```sql
CREATE TABLE product_specifications (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    product_id BIGINT,
    spec_name VARCHAR(255),
    spec_value VARCHAR(255),
    FOREIGN KEY (product_id) REFERENCES products(id)
);
```

### 4. Métodos de Pago
Nueva tabla para manejar métodos de pago:
```sql
CREATE TABLE payment_methods (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) UNIQUE
);
```

### 5. Uso de Cupones
Nueva tabla para rastrear el uso de cupones por usuario:
```sql
CREATE TABLE coupon_usage (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT,
    coupon_id BIGINT,
    used_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (coupon_id) REFERENCES coupons(id),
    UNIQUE (user_id, coupon_id)
);
```