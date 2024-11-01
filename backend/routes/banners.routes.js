const express = require('express')
const router = express.Router()
const controller = require('../controllers/bannerController.js')

/**
 * @swagger
 * /banners:
 *   get:
 *     summary: Obtener todos los banners
 *     tags:
 *       - Banners
 *     responses:
 *       200:
 *         description: Lista de banners.
 *         content:
 *           application/json:
 *             schema:
 *               type: array
 *               items:
 *                 $ref: '#/components/schemas/Banner'
 */
router.get('/', controller.index)

/**
 * @swagger
 * /banners/{id}:
 *   get:
 *     summary: Obtener un banner por ID
 *     tags:
 *       - Banners
 *     parameters:
 *       - in: path
 *         name: id
 *         schema:
 *           type: integer
 *         required: true
 *         description: ID del banner
 *     responses:
 *       200:
 *         description: Banner encontrado.
 *         content:
 *           application/json:
 *             schema:
 *               $ref: '#/components/schemas/Banner'
 */
router.get('/:id', controller.show)

module.exports = router
