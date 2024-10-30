const { Order } = require('../models/index.js')
const { notFoundError } = require('../helpers/error.helper.js')

exports.index = async (req, res, next) => {
  try {
    const orders = await Order.findAll()
    res.json(orders)
  } catch (error) {
    next(error)
  }
}

exports.show = async (req, res, next) => {
  try {
    const order = await Order.findByPk(req.params.id)
    if (!order) notFoundError('Orden no encontrada', 'ORDER_NOT_FOUND')
    res.json(order)
  } catch (error) {
    next(error)
  }
}
