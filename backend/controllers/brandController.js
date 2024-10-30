const { Brand } = require('../models/index.js')
const { notFoundError } = require('../helpers/error.helper.js')

exports.index = async (req, res, next) => {
  try {
    const brands = await Brand.findAll()
    res.json(brands)
  } catch (error) {
    next(error)
  }
}

exports.show = async (req, res, next) => {
  try {
    const brand = await Brand.findByPk(req.params.id)
    if (!brand) notFoundError('Marca no encontrada', 'BRAND_NOT_FOUND')
    res.json(brand)
  } catch (error) {
    next(error)
  }
}
