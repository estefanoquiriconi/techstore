/**
 * @swagger
 * components:
 *   schemas:
 *     User:
 *       type: object
 *       properties:
 *         id:
 *           type: integer
 *           description: Identificador único del usuario
 *           example: 1
 *         first_name:
 *           type: string
 *           description: Nombre del usuario
 *           example: "Juan"
 *         last_name:
 *           type: string
 *           description: Apellido del usuario
 *           example: "Pérez"
 *         email:
 *           type: string
 *           format: email
 *           description: Correo electrónico del usuario
 *           example: "juan.perez@gmail.com"
 *         phone:
 *           type: string
 *           description: Número de teléfono del usuario
 *           example: "11-1234-5678"
 *         address:
 *           type: string
 *           description: Dirección del usuario
 *           example: "Av. Corrientes 1234, CABA"
 *         avatar:
 *           type: string
 *           format: uri
 *           description: URL de la imagen de perfil del usuario
 *           example: "https://randomuser.me/api/portraits/men/1.jpg"
 */
