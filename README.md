## **TechStore**  🎧📱💻✨
Una aplicación de tienda electrónica donde los usuarios pueden explorar, comprar y gestionar pedidos de productos tecnológicos.

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
TechStore es una aplicación móvil diseñada para ofrecer una experiencia eficiente en la compra de productos tecnológicos, como celulares, notebooks, tablets y accesorios. Incluye funcionalidades como autenticación, búsqueda avanzada, carrito de compras, gestión de pedidos y reseñas de productos. Además, cuenta con **geolocalización** para sugerir automáticamente la dirección del usuario durante el checkout.

---

## **Características**  
- **Registro y autenticación:**  
  El sistema permite registrarse y autenticarse mediante email.  
- **Exploración de productos:**  
  Búsqueda por categorías y filtros (precio, marca).  
- **Carrito de compras:**  
  Agregar, modificar o eliminar productos y aplicar cupones.  
- **Gestión de pedidos:**  
  Visualización del estado y acceso al historial de pedidos.  
- **Reseñas:**  
  Los usuarios pueden valorar productos y ver reseñas de otros clientes.  
- **Geolocalización:**  
  Sugiere automáticamente la dirección del usuario en el checkout mediante GPS.

---

## **Requerimientos**  

### **Requerimientos Funcionales**  
- El sistema debe permitir el registro de usuarios y la autenticación con email.  
- El sistema debe permitir la búsqueda de productos por palabra clave, categorías y filtros.  
- El sistema debe permitir al usuario agregar productos al carrito y gestionar su contenido.  
- El sistema debe permitir la sugerencia de dirección en el checkout usando la ubicación del usuario.  
- El sistema debe manejar el estado de los pedidos y mostrar el historial.

### **Requerimientos No Funcionales**  
- **Usabilidad:** La interfaz debe ser intuitiva y fácil de usar.  
- **Rendimiento:** Las operaciones críticas deben realizarse en menos de 3 segundos.  
- **Seguridad:** Los datos sensibles deben ser encriptados.  
- **Escalabilidad:** El sistema debe soportar un crecimiento de usuarios y productos.

---

## **Uso**
1. **Registro e Inicio de Sesión:** Crea una cuenta o ingresa usando tus credenciales.  
2. **Explora Productos:** Navega por categorías o busca productos específicos.  
3. **Agrega al Carrito:** Selecciona productos y agrégalos al carrito.  
4. **Checkout:** Ingresa tu dirección manualmente o usa la sugerencia automática basada en tu ubicación.  
5. **Realiza el Pago:** Selecciona tu método de pago y confirma el pedido.  
6. **Historial de Pedidos:** Visualiza el estado actual y revisa pedidos anteriores.

---

## **Funcionalidad de Geolocalización**

1. **Descripción:**  
   En el proceso de checkout, se sugiere automáticamente la dirección del usuario basándose en la ubicación obtenida por GPS.

---

## **Arquitectura del Proyecto**

- **Cliente (Android App):**  
  Maneja la interfaz de usuario y la interacción con el usuario.

- **API RESTful (Node.js + Express):**  
  Provee servicios para la autenticación, gestión del carrito y pedidos.

- **Base de Datos (MySQL):**  
  Almacena usuarios, productos, pedidos y reseñas.

---

## **Tecnologías Utilizadas**

- **Backend:**  
  - Node.js  
  - Express  
  - Sequelize (ORM)  
  - MySQL

- **Frontend:**  
  - Android con Java  
  - Volley o Retrofit para peticiones HTTP  
  - Geocoder o Google Maps API para geolocalización
---

Desarrollado por **Estéfano Quiriconi**.
