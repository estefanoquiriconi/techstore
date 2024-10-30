const { Category } = require('../models/index.js')
const { notFoundError } = require('../helpers/error.helper.js')

exports.index = async (req, res, next) => {
  try {
    const categories = await Category.findAll()
    res.json(categories)
  } catch (error) {
    next(error)
  }
}

exports.show = async (req, res, next) => {
  try {
    const category = await Category.findByPk(req.params.id)
    if (!category) notFoundError('Categoría no encontrada', 'CATEGORY_NOT_FOUND')
    res.json(category)
  } catch (error) {
    next(error)
  }
}
