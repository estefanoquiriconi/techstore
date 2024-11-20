const { Product } = require('../../models/index.js')
const { Op } = require('sequelize')

const getByQuery = async (query) => {
  const products = await Product.findAll({
    where: {
      [Op.or]: [
        { name: { [Op.like]: `%${query}%` } },
        { description: { [Op.like]: `%${query}%` } },
        { image_url: { [Op.like]: `%${query}%` } }
      ],
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
    ]
  })

  return products
}

module.exports = { getByQuery }
