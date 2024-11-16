/**
 * @swagger
 * components:
 *   schemas:
 *     Order:
 *       type: object
 *       properties:
 *         id:
 *           type: integer
 *           description: ID de la orden
 *           example: 1
 *         total_amount:
 *           type: number
 *           format: float
 *           description: Monto total de la orden
 *           example: 999.99
 *         status:
 *           type: string
 *           description: Estado de la orden
 *           enum:
 *             - preparación
 *             - enviado
 *             - entregado
 *           example: "preparación"
 *         user_id:
 *           type: integer
 *           description: ID del usuario que realizó la orden
 *           example: 123
 *         created_at:
 *           type: string
 *           format: date-time
 *           description: Fecha de creación de la orden
 *           example: "2023-03-01T13:00:00.000Z"
 */
