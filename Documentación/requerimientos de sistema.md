# REQUERIMIENTOS

## 1. Requerimientos Funcionales

1. **Registro y autenticación**:
   - El sistema debe permitir que los usuarios se registren mediante **correo electrónico**.
   - El sistema debe permitir la **activación de la cuenta** a través de un enlace enviado por correo.
   - El sistema debe permitir que los usuarios **inicien sesión** con su cuenta registrada.
   - El sistema debe permitir la **recuperación de contraseña** a través de correo electrónico.
   - El sistema debe permitir el **inicio y cierre de sesión seguros** para proteger la sesión del usuario.

2. **Exploración y búsqueda de productos**:
   - El sistema debe permitir a los usuarios navegar por **categorías** (celulares, notebooks, tablets, auriculares).
   - El sistema debe permitir a los usuarios buscar productos por **palabras clave** y **filtrar** por atributos como marcas, categorías. Ordenar por menor o mayor precio.
   - El sistema debe mostrar una **pantalla de detalle del producto** que incluya imágenes, descripciones, reseñas y características.

3. **Carrito de compras y proceso de pago**:
   - El sistema debe permitir agregar, modificar y eliminar productos del **carrito de compras**.
   - El sistema debe permitir aplicar **cupones y descuentos** válidos durante la compra.
   - El sistema debe permitir que los usuarios seleccionen entre varios **métodos de pago** (efectivo contra entrega y mercadopago).
   - El sistema debe permitir la sugerencia de dirección en el checkout usando la ubicación del usuario. 
   - El sistema debe permitir la confirmación del pedido, mostrando un **resumen** antes del pago.

4. **Pedidos**:
   - El sistema debe permitir que los usuarios visualicen el **estado de sus pedidos** (en preparación, enviado, entregado).
   - El sistema debe permitir acceder al **historial de pedidos** anteriores con detalles completos.

5. **Valoraciones y reseñas**:
   - El sistema debe permitir a los usuarios dejar **valoraciones** y **comentarios** sobre los productos comprados.
   - El sistema debe mostrar las valoraciones de otros usuarios para cada producto.
   
6. **Almacenamiento de direcciones y uso de Google Maps**:  
   - El sistema debe integrar **Google Maps**.  
   - El sistema debe permitir al usuario **almacenar múltiples direcciones** (ejemplo: "Casa", "Trabajo", "Otra dirección").  
   - El sistema debe permitir que el usuario **seleccione una dirección guardada** para usarla como dirección de entrega.   
   - El usuario debe poder **editar, agregar o eliminar direcciones** desde su perfil.
   - 
---

## 2. Requerimientos No Funcionales

1. **Usabilidad**:
   - El sistema debe proporcionar una **interfaz intuitiva** y de fácil navegación para los usuarios.
   - El sistema debe funcionar sin problemas en dispositivos Android modernos.

2. **Rendimiento**:
   - El sistema debe responder rápidamente, manteniendo **tiempos de carga menores a 3 segundos**.
   - El sistema debe permitir una **búsqueda eficiente** y **actualización dinámica del inventario**.

3. **Escalabilidad**:
   - El sistema debe ser capaz de manejar un crecimiento significativo en la **cantidad de usuarios** y **productos** sin pérdida de rendimiento.

4. **Seguridad**:
   - El sistema debe encriptar las **contraseñas** y otros datos sensibles utilizando **algoritmos seguros**.
   - El sistema debe utilizar **tokens JWT** para la gestión segura de sesiones de usuario.

---

## 3. Historias de Usuario

1. **Historia de Registro**:  
   - **Como** usuario nuevo, **quiero** registrarme utilizando mi correo electrónico **para** acceder a todas las funcionalidades de la tienda.

2. **Historia de Exploración**:  
   - Como usuario, quiero buscar productos por categorías, marcas y ordenar resultados por precio, para encontrar rápidamente lo que necesito.

3. **Historia de Compra**:  
   - Como usuario, quiero agregar productos al carrito, aplicar cupones y seleccionar un método de pago, para completar mi compra fácilmente.

4. **Historia de Pedido**:  
   - Como usuario, quiero ver el historial de mis pedidos.

5. **Historia de Valoración**:  
   - Como usuario, quiero calificar los productos que compré para ayudar a otros usuarios con sus decisiones.
     
6. **Historia de Almacenamiento de Direcciones**  
   - Como usuario de la aplicación, quiero guardar varias direcciones y seleccionar una en el checkout, para facilitar el proceso de compra y no tener que ingresar la dirección cada vez.  

---

## 4. Casos de Uso

**Caso de Uso 1: Registro de Usuario**  
**Actor:** Usuario  
**Descripción:** Un usuario se registra en la plataforma proporcionando su correo electrónico.  
**Flujo Principal:**  
1. El usuario accede a la pantalla de registro.
2. Ingresa su correo electrónico
3. Completa los datos y confirma el registro.
4. El sistema envía un correo para activar la cuenta.
5. El usuario activa su cuenta mediante el enlace enviado.


**Caso de Uso 2: Búsqueda y Filtro de Productos**  
**Actor:** Usuario  
**Descripción:** El usuario explora los productos y utiliza filtros para encontrar opciones específicas.  
**Flujo Principal:**  
1. El usuario accede a la lista de productos.
2. Selecciona una categoría.
3. Ingresa una palabra clave en el buscador.
4. Aplica filtros de marcas, ordenar por menor o mayor precio.
5. Visualiza los resultados filtrados.


**Caso de Uso 3: Proceso de Pago**  
**Actor:** Usuario  
**Descripción:** El usuario completa la compra de los productos en su carrito.  
**Flujo Principal:**  
1. El usuario revisa el carrito de compras.
2. Aplica cupones de descuento (si tiene).
3. Selecciona un método de pago.
4. Confirma la compra.
5. El sistema muestra un resumen del pedido.


**Caso de Uso 4: Almacenamiento de Direcciones y Uso de Google Maps**  
**Actor:** Usuario  
**Descripción:** El usuario gestiona y selecciona direcciones guardadas para usarlas durante el checkout utilizando la integración con Google Maps.  
**Flujo Principal:**  
1. El usuario accede al **perfil** y selecciona la opción **"Mis Direcciones"**.  
2. El usuario hace clic en **"Agregar Nueva Dirección"**.  
3. La aplicación sugiere la dirección basada en la **ubicación actual del dispositivo** mediante Google Maps.  
4. El usuario puede **aceptar la sugerencia** o **buscar manualmente una dirección** en el mapa.  
5. El usuario guarda la dirección asignando un **nombre** (por ejemplo, "Casa" o "Trabajo").  
6. Durante el proceso de checkout, el sistema muestra las **direcciones guardadas**.  
7. El usuario selecciona una dirección para la **entrega del pedido**.  
8. La dirección elegida se muestra en el **resumen del pedido**.  
9. Si el usuario necesita realizar cambios, puede **editar o eliminar** una dirección desde el perfil.

---

## 5. Componentes Técnicos

1. **Backend:**
   - **Node.js**: Servidor para gestionar la lógica de negocio.
   - **Express**: Framework para la creación de rutas y API RESTful.
   - **Sequelize**: ORM para conectar con **MySQL** y gestionar las operaciones con la base de datos.
   - **MySQL**: Base de datos para almacenar usuarios, productos, pedidos y promociones.

2. **Frontend (Android):**
   - **Java**: Lenguaje principal para desarrollar la aplicación.
   - **Retrofit**: Librerías para realizar peticiones HTTP hacia el backend.
   - **SQLite**: Base de datos local para almacenar datos temporales.

   **Actividades Principales:**
   - `LoginActivity`: Gestión de inicio de sesión y registro.
   - `ProductListActivity`: Lista de productos por categoría.
   - `ProductDetailActivity`: Detalles del producto.
   - `CartActivity`: Vista del carrito de compras.
   - `ProfileActivity`: Vista del perfil del usuario.
