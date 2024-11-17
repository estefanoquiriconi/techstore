# REQUERIMIENTOS

## 1. Requerimientos Funcionales

1. **Registro y autenticación**:
   - El sistema debe permitir que los usuarios se registren mediante **correo electrónico**.
   - El sistema debe permitir la **activación de la cuenta** a través de un enlace enviado por correo.
   - El sistema debe permitir que los usuarios **inicien sesión** con su cuenta registrada.
   - El sistema debe permitir la **recuperación de contraseña** a través de correo electrónico.
   - El sistema debe permitir el **inicio y cierre de sesión seguros** para proteger la sesión del usuario.

2. **Exploración y búsqueda de productos**:
   - El sistema debe permitir a los usuarios filtrar por **categorías** (celulares, notebooks, tablets, auriculares).
   - El sistema debe permitir a los usuarios buscar productos por **palabras clave** y **ordenar** por menor o mayor precio.
   - El sistema debe mostrar una **pantalla de detalle del producto** que incluya imagen, descripción y reseñas.

3. **Carrito de compras y proceso de pago**:
   - El sistema debe permitir agregar, modificar y eliminar productos del **carrito de compras**.
   - El sistema debe permitir la confirmación del pedido, mostrando un **resumen** antes del pago.

4. **Pedidos**:
   - El sistema debe permitir que los usuarios visualicen el **estado de sus pedidos** (en preparación, enviado, entregado).
   - El sistema debe permitir visualizar el monto del pedido y la fecha en la que se realizó.

5. **Reseñas**:
   - El sistema debe mostrar las valoraciones de otros usuarios en los productos
   - El sistema debe permitir mostrar el rating de las reseñas con su respectiva fecha.
   - El sistema debe permtir mostrar un promedio del rating.
   
6. **Google Maps**:  
   - El sistema debe integrar **Google Maps**.  
   - El sistema debe permitir al usuario **almacenar su dirección**
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
   - Como usuario, quiero buscar productos por categorías y ordenar resultados por precio, para encontrar rápidamente lo que necesito.

3. **Historia de Compra**:  
   - Como usuario, quiero agregar productos al carrito y confirmar el pedido.

4. **Historia de Pedido**:  
   - Como usuario, quiero ver el historial de mis pedidos.

5. **Historia de Reseñas**:  
   - Como usuario, quiero visualizar reseñas sobre los productos.
     
6. **Historia de Almacenamiento de Direcciones**  
   - Como usuario de la aplicación, quiero guardar mi dirección para recibir mis pedidos.
