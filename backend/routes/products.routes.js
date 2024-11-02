const express = require('express')
const router = express.Router()
const controller = require('../controllers/productController')

/**
 * @swagger
 * /products:
 *   get:
 *     summary: Obtener todos los productos
 *     tags:
 *       - Products
 *     parameters:
 *       - in: query
 *         name: category
 *         required: false
 *         description: Nombre de la categoría para filtrar productos
 *         schema:
 *           type: string
 *       - in: query
 *         name: sortBy
 *         required: false
 *         description: Campo por el cual ordenar los productos
 *         schema:
 *           type: string
 *           enum: [price, stock, name, created_at]
 *       - in: query
 *         name: order
 *         required: false
 *         description: Orden de los productos
 *         schema:
 *           type: string
 *           enum: [asc, desc]
 *     responses:
 *       200:
 *         description: Lista de productos.
 *         content:
 *           application/json:
 *             schema:
 *               type: array
 *               items:
 *                 $ref: '#/components/schemas/Product'
 */
router.get('/', controller.getAllProducts)

/**
 * @swagger
 * /products/{id}:
 *   get:
 *     summary: Obtener un producto por ID
 *     tags:
 *       - Products
 *     parameters:
 *       - in: path
 *         name: id
 *         schema:
 *           type: integer
 *         required: true
 *         description: ID del producto
 *     responses:
 *       200:
 *         description: Producto encontrado.
 *         content:
 *           application/json:
 *             schema:
 *               $ref: '#/components/schemas/Product'
 */
router.get('/:id', controller.getProductById)

/**
 * @swagger
 * /products/search/{query}:
 *   get:
 *     summary: Obtener productos por (query)
 *     tags:
 *       - Products
 *     parameters:
 *       - in: path
 *         name: query
 *         schema:
 *           type: string
 *         required: true
 *         description: query para buscar productos
 *     responses:
 *       200:
 *         description: Producto encontrado.
 *         content:
 *           application/json:
 *             schema:
 *               $ref: '#/components/schemas/Product'
 */
router.get('/search/:query', controller.getProductsByQuery)

module.exports = router
