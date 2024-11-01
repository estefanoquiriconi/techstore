const express = require('express')
const router = express.Router()
const controller = require('../controllers/categoryController')

/**
 * @swagger
 * /categories:
 *   get:
 *     summary: Obtener todas las categorías
 *     tags:
 *       - Categories
 *     responses:
 *       200:
 *         description: Lista de categorías.
 *         content:
 *           application/json:
 *             schema:
 *               type: array
 *               items:
 *                 $ref: '#/components/schemas/Category'
 */
router.get('/', controller.index)

/**
 * @swagger
 * /categories/{id}:
 *   get:
 *     summary: Obtener una categoría por ID
 *     tags:
 *       - Categories
 *     parameters:
 *       - in: path
 *         name: id
 *         schema:
 *           type: integer
 *         required: true
 *         description: ID de la categoría
 *     responses:
 *       200:
 *         description: Categoría encontrada.
 *         content:
 *           application/json:
 *             schema:
 *               $ref: '#/components/schemas/Category'
 */
router.get('/:id', controller.show)

module.exports = router
