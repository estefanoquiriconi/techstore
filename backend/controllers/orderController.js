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

exports.store = async (req, res, next) => {
  try {
    const { total_amount: totalAmount, user_id: userId } = req.body
    const order = await Order.create({
      total_amount: totalAmount,
      user_id: userId
    })
    res.status(201).json(order)
  } catch (error) {
    next(error)
  }
}

exports.getByUserId = async (req, res, next) => {
  try {
    const { id } = req.params
    const orders = await Order.findAll({
      where: { user_id: id },
      attributes: ['id', 'total_amount', 'status', 'created_at'],
      order: [['created_at', 'DESC']]
    })
    return res.json(orders)
  } catch (error) {
    next(error)
  }
}
