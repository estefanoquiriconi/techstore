# Documentación de la base de datos

## Descripción general
La base de datos `techstore_db` está diseñada para gestionar una tienda en línea de productos tecnológicos. Incluye funcionalidades para manejar usuarios, productos, categorías, marcas, pedidos y reseñas.
## Estructura de la base de datos

### Tabla: users
Almacena información de los usuarios registrados en la tienda.

| Columna | Tipo | Descripción |
|---------|------|-------------|
| id | BIGINT | Clave primaria, autoincremental |
| first_name | VARCHAR(255) | Nombre del usuario |
| last_name | VARCHAR(255) | Apellido del usuario |
| email | VARCHAR(255) | Email del usuario (único) |
| avatar | VARCHAR(255) | URL del avatar del usuario |
| phone | VARCHAR(20) | Número de teléfono |
| address | VARCHAR(255) | Dirección del usuario |
| latitude | DECIMAL(9,6) | Representa la coordenada de latitud de la ubicación usuario |
| longitude | DECIMAL(9,6)| Representa la coordenada de longitud de la ubicación del usuario |
| password | VARCHAR(255) | Contraseña encriptada |
| active | BOOLEAN | Estado de la cuenta (activa/inactiva) |
| registration_code | VARCHAR(50) | Código de registro |
| recoverpass_code | VARCHAR(10) | Código para recuperar contraseña |
| created_at | TIMESTAMP | Fecha de creación del registro |
| updated_at | TIMESTAMP | Fecha de última actualización |

### Tabla: categories
Contiene las categorías de productos.

| Columna | Tipo | Descripción |
|---------|------|-------------|
| id | BIGINT | Clave primaria, autoincremental |
| name | VARCHAR(255) | Nombre de la categoría (único) |

### Tabla: brands
Almacena las marcas de los productos.

| Columna | Tipo | Descripción |
|---------|------|-------------|
| id | BIGINT | Clave primaria, autoincremental |
| name | VARCHAR(255) | Nombre de la marca (único) |

### Tabla: products
Contiene información detallada de los productos.

| Columna | Tipo | Descripción |
|---------|------|-------------|
| id | BIGINT | Clave primaria, autoincremental |
| name | VARCHAR(255) | Nombre del producto |
| brand_id | BIGINT | ID de la marca (clave foránea a brands) |
| category_id | BIGINT | ID de la categoría (clave foránea a categories) |
| price | DECIMAL(10, 2) | Precio del producto |
| description | TEXT | Descripción del producto |
| image_url | VARCHAR(255) | URL de la imagen del producto |
| stock | INT | Cantidad en stock (no puede ser negativo) |
| active | BOOLEAN | Estado del producto (activo/inactivo) |
| created_at | TIMESTAMP | Fecha de creación del registro |
| updated_at | TIMESTAMP | Fecha de última actualización |

### Tabla: orders
Registra los pedidos realizados por los usuarios.

| Columna | Tipo | Descripción |
|---------|------|-------------|
| id | BIGINT | Clave primaria, autoincremental |
| user_id | BIGINT | ID del usuario que realizó el pedido (clave foránea a users) |
| total_amount | DECIMAL(10, 2) | Monto total del pedido |
| status | ENUM | Estado del pedido ('preparación', 'enviado', 'entregado') |
| created_at | TIMESTAMP | Fecha de creación del pedido |
| updated_at | TIMESTAMP | Fecha de última actualización |

### Tabla: order_details
Almacena los detalles de cada pedido.

| Columna | Tipo | Descripción |
|---------|------|-------------|
| id | BIGINT | Clave primaria, autoincremental |
| order_id | BIGINT | ID del pedido (clave foránea a orders) |
| product_id | BIGINT | ID del producto (clave foránea a products) |
| quantity | INT | Cantidad de productos (debe ser mayor que 0) |
| price | DECIMAL(10, 2) | Precio unitario del producto |
| subtotal | DECIMAL(10, 2) | Subtotal calculado (quantity * price) |

### Tabla: reviews
Contiene las reseñas de los productos realizadas por los usuarios.

| Columna | Tipo | Descripción |
|---------|------|-------------|
| id | BIGINT | Clave primaria, autoincremental |
| user_id | BIGINT | ID del usuario que realizó la reseña (clave foránea a users) |
| product_id | BIGINT | ID del producto reseñado (clave foránea a products) |
| comment | TEXT | Comentario de la reseña |
| rating | INT | Calificación (entre 1 y 5) |
| created_at | TIMESTAMP | Fecha de creación de la reseña |
| updated_at | TIMESTAMP | Fecha de última actualización |

## Relaciones entre tablas

1. `products` tiene claves foráneas a `brands` (brand_id) y `categories` (category_id).
2. `orders` tiene una clave foránea a `users` (user_id).
3. `order_details` tiene claves foráneas a `orders` (order_id) y `products` (product_id).
4. `reviews` tiene claves foráneas a `users` (user_id) y `products` (product_id).

## Notas adicionales

- La tabla `users` incluye campos para gestión de cuentas como `active`, `registration_code`, y `recoverpass_code`.
- La tabla `products` tiene un campo `active` para gestionar la visibilidad de los productos.
- La tabla `orders` utiliza un ENUM para el estado del pedido, limitando los valores posibles.
- La tabla `order_details` incluye un campo calculado `subtotal` basado en la cantidad y el precio.
- La tabla `reviews` tiene una restricción UNIQUE para evitar múltiples reseñas del mismo usuario para el mismo producto.