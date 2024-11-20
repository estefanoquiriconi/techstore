const express = require('express')
const router = express.Router()
const controller = require('../controllers/orderController')

/**
 * @swagger
 * /orders:
 *   get:
 *     summary: Obtener todas las órdenes
 *     tags:
 *       - Orders
 *     responses:
 *       200:
 *         description: Lista de órdenes.
 *         content:
 *           application/json:
 *             schema:
 *               type: array
 *               items:
 *                 $ref: '#/components/schemas/Order'
 */
router.get('/', controller.index)

/**
 * @swagger
 * /orders/{id}:
 *   get:
 *     summary: Obtener una orden por ID
 *     tags:
 *       - Orders
 *     parameters:
 *       - in: path
 *         name: id
 *         schema:
 *           type: integer
 *         required: true
 *         description: ID de la orden
 *     responses:
 *       200:
 *         description: Orden encontrada.
 *         content:
 *           application/json:
 *             schema:
 *               $ref: '#/components/schemas/Order'
 */
router.get('/:id', controller.show)

/**
 * @swagger
 * /orders/user/{id}:
 *   get:
 *     summary: Obtener órdenes por ID de usuario
 *     tags:
 *       - Orders
 *     parameters:
 *       - in: path
 *         name: id
 *         schema:
 *           type: integer
 *         required: true
 *         description: ID del usuario
 *     responses:
 *       200:
 *         description: Lista de órdenes del usuario.
 *         content:
 *           application/json:
 *             schema:
 *               type: array
 *               items:
 *                 $ref: '#/components/schemas/Order'
 */
router.get('/user/:id', controller.getByUserId)

/**
 * @swagger
 * /orders:
 *   post:
 *     summary: Crear una nueva orden
 *     tags:
 *       - Orders
 *     requestBody:
 *       required: true
 *       content:
 *         application/json:
 *           schema:
 *             $ref: '#/components/schemas/Order'
 *     responses:
 *       201:
 *         description: Orden creada exitosamente.
 *         content:
 *           application/json:
 *             schema:
 *               $ref: '#/components/schemas/Order'
 */
router.post('/', controller.store)

router.post('/details', controller.storeWithDetails)

module.exports = router
