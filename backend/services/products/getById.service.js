const { Product, Category, Brand } = require('../../models/associations.js')
const getById = async (id) => {
  const product = await Product.findByPk(id, {
    include: [
      {
        model: Category,
        attributes: ['id', 'name']
      },
      {
        model: Brand,
        attributes: ['id', 'name']
      }
    ],
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
  })
  return product
}

module.exports = { getById }