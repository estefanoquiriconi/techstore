# TechStore Backend

Este es el backend para la aplicación de comercio electrónico **TechStore**. Está desarrollado con Node.js, Express.js y Sequelize ORM, utilizando MySQL como base de datos.

## Funcionalidades

- API RESTful para gestionar usuarios, productos, categorías, marcas, pedidos, reseñas y cupones.
- Integración con la base de datos MySQL utilizando Sequelize ORM.
- Configuración basada en entornos.

## Requisitos Previos

- Node.js (v14.0.0 o superior)  
- MySQL (v5.7 o superior) 

## Instalación

1. Clona el repositorio:
   ```bash
   git clone https://github.com/estefanoquiriconi/techstore.git
   cd backend
   ```

2. Instala las dependencias:
   ```bash
   npm install
   ```

3. Crea un archivo `.env` en la raíz del proyecto y añade tu configuración:
   ```bash
   DB_HOST=localhost
   DB_USER=tu_usuario_mysql
   DB_PASS=tu_contraseña_mysql
   DB_NAME=techstore_db
   PORT=3000
   ```

4. Crea la base de datos MySQL:
   ```sql
   CREATE DATABASE techstore_db;
   ```

## Uso

Para iniciar el servidor en modo desarrollo:
```bash
npm run dev
```

Para iniciar el servidor en modo producción:
```bash
npm start
```

El servidor se ejecutará en el puerto especificado en tu archivo `.env` (por defecto es 3000).

## Endpoints de la API

- **Usuarios**: `/api/users`
- **Categorías**: `/api/categories`
- **Marcas**: `/api/brands`
- **Productos**: `/api/products`
- **Pedidos**: `/api/orders`
- **Reseñas**: `/api/reviews`
- **Cupones**: `/api/coupons`

Cada recurso soporta las operaciones CRUD estándar:

- **GET /** - Listar todos los elementos  
- **GET /:id** - Obtener un elemento específico  
- **POST /** - Crear un nuevo elemento  
- **PUT /:id** - Actualizar un elemento existente  
- **DELETE /:id** - Eliminar un elemento

## Esquema de la Base de Datos

La base de datos incluye las siguientes tablas:

- `users`
- `categories`
- `brands`
- `products`
- `orders`
- `order_details`
- `reviews`
- `coupons`