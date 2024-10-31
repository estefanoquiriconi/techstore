# TechStore Backend

Este es el backend para la aplicación **TechStore**. Está desarrollado con Node.js, Express.js y Sequelize ORM, utilizando MySQL como base de datos.

## Funcionalidades

- API RESTful para gestionar usuarios, productos, categorías, banners, marcas, pedidos y reseñas.
- Integración con la base de datos MySQL utilizando Sequelize ORM.

## Requisitos Previos

- Node.js (v20.0.0 o superior)  
- MySQL (v8.3 o superior) 

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

3. Crear un archivo `.env` copiando el archivo `.env.example` en la raíz del proyecto y añade tu configuración:
   ```bash
   cp .env.example .env
   ```

4. Crear y poblar la base de datos en MySQL con los archivos `database.sql` y `population.sql`:

   ```bash
   mysql -u root -p -h localhost < ./database/database.sql
   mysql -u root -p -h localhost techstore_db < ./database/population.sql
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


### Exponer el Servidor con HTTPS para Pruebas

Para realizar pruebas del backend desde una URL pública utilizando HTTPS, puedes usar **ngrok**. Esto es útil para probar la API desde dispositivos externos o servicios que requieran conexiones HTTPS.

1. Descarga [ngrok](https://ngrok.com/download) e instálalo.
2. En una terminal, asegúrate de que el servidor de TechStore esté ejecutándose en el puerto especificado en el archivo `.env` (por defecto, 3000).
3. Ejecuta ngrok indicando el puerto del servidor:

   ```bash
   ngrok http 3000
   ```

4. Ngrok generará una URL pública HTTPS (por ejemplo, `https://abcdef.ngrok.io`). Puedes utilizar esta URL para probar la API desde ubicaciones externas.

5. Está url será importante para poder consumir la API desde un frontend.
