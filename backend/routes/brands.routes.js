const express = require('express')
const router = express.Router()
const controller = require('../controllers/brandController')

/**
 * @swagger
 * /brands:
 *   get:
 *     summary: Obtener todas las marcas
 *     tags:
 *       - Brands
 *     responses:
 *       200:
 *         description: Lista de marcas.
 *         content:
 *           application/json:
 *             schema:
 *               type: array
 *               items:
 *                 $ref: '#/components/schemas/Brand'
 */
router.get('/', controller.index)

/**
 * @swagger
 * /brands/{id}:
 *   get:
 *     summary: Obtener una marca por ID
 *     tags:
 *       - Brands
 *     parameters:
 *       - in: path
 *         name: id
 *         schema:
 *           type: integer
 *         required: true
 *         description: ID de la marca
 *     responses:
 *       200:
 *         description: Marca encontrada.
 *         content:
 *           application/json:
 *             schema:
 *               $ref: '#/components/schemas/Brand'
 */
router.get('/:id', controller.show)

module.exports = router
