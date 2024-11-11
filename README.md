## **TechStore**  🎧📱💻✨
Una aplicación de tienda electrónica donde los usuarios pueden explorar y comprar productos tecnológicos.

## Organización del Proyecto

Para gestionar el desarrollo de este proyecto, estoy utilizando metodologías ágiles.

- [Ver el tablero de Trello](https://trello.com/invite/b/670ecde80c302a900d729363/ATTIc9fc1f9e6c12a260df39bc6f5e0fd0ae21417E55/techstore)

## Diseño de Pantallas

El diseño de las pantallas del proyecto los estoy haciendo en Figma.

- [Ver el diseño en Figma](https://www.figma.com/design/BsdnPBZP3NZTDnVg6WEO3Q/TechStore?node-id=0-1&t=C1ntNFAqxwwoWwIK-1)


---

### **Tabla de Contenidos**  
1. [Descripción](#descripción)  
2. [Características](#características)  
3. [Requerimientos](#requerimientos)  
4. [Uso](#uso)  
6. [Arquitectura del Proyecto](#arquitectura-del-proyecto)  
7. [Funcionalidad de Geolocalización](#funcionalidad-de-geolocalización)  
8. [Tecnologías Utilizadas](#tecnologías-utilizadas)  

---

## **Descripción**  
TechStore es una aplicación móvil diseñada para ofrecer una experiencia eficiente en la compra de productos tecnológicos, como celulares, notebooks, tablets y auriculares. Incluye funcionalidades como autenticación, búsqueda avanzada, carrito de compras, historial de compras y reseñas de productos. Además, cuenta con **geolocalización** para visualizar la dirección del usuario.

---

## **Características**  
- **Registro y autenticación:**  
  El sistema permite registrarse y autenticarse mediante correo electrónico.  
- **Exploración de productos:**  
  Búsqueda de productos, filtro por categoría, ordenamiento por menor o mayor precio.  
- **Carrito de compras:**  
  Agregar, modificar cantidades o eliminar productos.
- **Gestión de pedidos:**  
  Visualización del estado y acceso al historial de pedidos.  
- **Reseñas:**  
  Los usuarios pueden valorar productos y ver reseñas de otros clientes.  
- **Geolocalización:**  
  El sistema sugiere automáticamente la dirección del usuario mediante GPS.

---

## **Uso**
1. **Registro e Inicio de Sesión:** Crea una cuenta o ingresa usando tus credenciales.  
2. **Explora Productos:** Navega por categorías o busca productos específicos.  
3. **Agrega al Carrito:** Selecciona productos y agrega al carrito.
4. **Perfil:** Visualiza la información del usuario. 
5. **Historial de Pedidos:** Visualiza el estado actual y revisa pedidos anteriores.
---

## **Arquitectura del Proyecto**

- **Cliente (Android App):**  
  Maneja la interfaz de usuario y la interacción con el usuario.

- **API RESTful (Node.js + Express):**  
  Provee servicios para la autenticación, productos, gestión de pedidos, etc.

- **Base de Datos (MySQL):**  
  Almacena usuarios, productos, marcas, categorias, pedidos, reseñas y direcciones.

---

## **Tecnologías Utilizadas**

- **Backend:**  
  - Node.js  
  - Express  
  - Sequelize (ORM)  
  - MySQL

- **Frontend:**  
  - Android con Java  
  - Retrofit para peticiones HTTP  
  - Google Maps API para geolocalización
---

Desarrollado por **Estéfano Quiriconi**.
