const { Order, OrderDetail, Product } = require('../models/index.js')
const { notFoundError, badRequestError } = require('../helpers/error.helper.js')
const sequelize = require('../database/config/sequelize.js')

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
    const order = await Order.findByPk(req.params.id, {
      include: [
        {
          model: OrderDetail,
          attributes: ['price', 'quantity', 'subtotal'],
          include: [
            {
              model: Product,
              attributes: ['name', 'image_url']
            }
          ]
        }
      ]
    })
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

exports.storeWithDetails = async (req, res, next) => {
  const transaction = await sequelize.transaction()
  try {
    const {
      total_amount: totalAmount,
      user_id: userId,
      OrderDetails: orderDetails
    } = req.body

    for (const detail of orderDetails) {
      const product = await Product.findByPk(detail.product_id)
      if (!product) {
        badRequestError(`Producto con ID ${detail.product_id} no encontrado.`)
      }
      if (product.stock < detail.quantity) {
        badRequestError(`Stock insuficiente para el producto "${product.name}". Disponible: ${product.stock}, solicitado: ${detail.quantity}.`)
      }
    }

    const order = await Order.create({
      total_amount: totalAmount,
      user_id: userId
    }, { transaction })

    const orderDetailsToCreate = await Promise.all(orderDetails.map(async detail => {
      const product = await Product.findByPk(detail.product_id)
      product.stock -= detail.quantity
      product.save({ transaction })
      return {
        quantity: detail.quantity,
        price: product.price,
        product_id: detail.product_id,
        order_id: order.id
      }
    }))

    await OrderDetail.bulkCreate(orderDetailsToCreate, { transaction })

    await (transaction).commit()

    res.status(201).json({
      message: 'Orden creada',
      order
    })
  } catch (error) {
    await (transaction).rollback()
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
