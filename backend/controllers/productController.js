const productService = require('../services/products/index.service.js')
const { notFoundError, badRequestError } = require('../helpers/error.helper.js')

exports.getAllProducts = async (req, res, next) => {
  const validSortFields = ['price', 'name', 'created_at', 'stock']
  const validOrderValues = ['asc', 'desc']
  const { category, sortBy = 'created_at', order = 'desc' } = req.query
  try {
    if (!validSortFields.includes(sortBy) || !validOrderValues.includes(order)) badRequestError('Parámetros inválidos')
    const products = await productService.getAll({ category, sortBy, order })
    res.json(products)
  } catch (error) {
    next(error)
  }
}

exports.getProductById = async (req, res, next) => {
  const { id } = req.params
  try {
    const product = await productService.getById(id)
    if (!product) notFoundError('Producto no encontrado', 'PRODUCT_NOT_FOUND')
    res.json(product)
  } catch (error) {
    next(error)
  }
}
