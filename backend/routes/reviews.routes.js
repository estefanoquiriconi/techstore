const express = require('express')
const router = express.Router()
const controller = require('../controllers/reviewController')

/**
 * @swagger
 * /reviews:
 *   get:
 *     summary: Obtener todas las reseñas
 *     tags:
 *       - Reviews
 *     responses:
 *       200:
 *         description: Lista de reseñas.
 *         content:
 *           application/json:
 *             schema:
 *               type: array
 *               items:
 *                 $ref: '#/components/schemas/Review'
 */
router.get('/', controller.index)

/**
 * @swagger
 * /reviews/{id}:
 *   get:
 *     summary: Obtener una reseña por ID
 *     tags:
 *       - Reviews
 *     parameters:
 *       - in: path
 *         name: id
 *         schema:
 *           type: integer
 *         required: true
 *         description: ID de la reseña
 *     responses:
 *       200:
 *         description: Reseña encontrada.
 *         content:
 *           application/json:
 *             schema:
 *               $ref: '#/components/schemas/Review'
 */
router.get('/:id', controller.show)

/**
 * @swagger
 * /reviews/product/{id}:
 *   get:
 *     summary: Obtener reseñas por ID de producto
 *     tags:
 *       - Reviews
 *     parameters:
 *       - in: path
 *         name: id
 *         schema:
 *           type: integer
 *         required: true
 *         description: ID del producto
 *     responses:
 *       200:
 *         description: Lista de reseñas del producto.
 *         content:
 *           application/json:
 *             schema:
 *               type: array
 *               items:
 *                 $ref: '#/components/schemas/Review'
 */
router.get('/product/:id', controller.getByProductId)

module.exports = router
