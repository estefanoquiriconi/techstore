/**
 * @swagger
 * components:
 *   schemas:
 *     Product:
 *       type: object
 *       properties:
 *         id:
 *           type: integer
 *           example: 1
 *         name:
 *           type: string
 *           example: "Motorola Edge 50 Fusion"
 *         price:
 *           type: string  # Por ahora lo dejamos como string
 *           example: "799999.00"
 *         description:
 *           type: string
 *           example: "Protección IP68, disponible en varios colores."
 *         image_url:
 *           type: string
 *           example: "https://example.com/image.png"
 *         stock:
 *           type: integer
 *           example: 50
 *         created_at:
 *           type: string
 *           format: date-time
 *           example: "2024-10-29T02:53:56.000Z"
 *         updated_at:
 *           type: string
 *           format: date-time
 *           example: "2024-10-29T02:53:56.000Z"
 *         Category:
 *           type: object
 *           properties:
 *             id:
 *               type: integer
 *               example: 1
 *             name:
 *               type: string
 *               example: "Celulares"
 *         Brand:
 *           type: object
 *           properties:
 *             id:
 *               type: integer
 *               example: 1
 *             name:
 *               type: string
 *               example: "Motorola"
 */
