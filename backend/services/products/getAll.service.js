const { Op } = require('sequelize')
const { Product, Category, Brand } = require('../../models/index.js')

const getAll = async ({ category, sortBy = 'created_at', order = 'desc' }) => {
  const products = await Product.findAll({
    include: [
      {
        model: Category,
        attributes: ['id', 'name'],
        where: category ? { name: category } : undefined
      },
      {
        model: Brand,
        attributes: ['id', 'name']
      }
    ],
    where: {
      active: true,
      stock: { [Op.gt]: 0 }
    },
    attributes: [
      'id',
      'name',
      'price',
      'description',
      'image_url',
      'stock',
      'created_at',
      'updated_at'
    ],
    order: [[sortBy, order.toUpperCase()]]
  })
  return products
}

module.exports = { getAll }
