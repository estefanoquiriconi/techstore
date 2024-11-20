const User = require('./user.js')
const Category = require('./category.js')
const Banner = require('./banner.js')
const Brand = require('./brand.js')
const Product = require('./product.js')
const Order = require('./order.js')
const OrderDetail = require('./orderDetail.js')
const Review = require('./review.js')

Product.belongsTo(Brand, { foreignKey: 'brand_id' })
Product.belongsTo(Category, { foreignKey: 'category_id' })
Product.hasMany(OrderDetail, { foreignKey: 'product_id' })

Order.belongsTo(User, { foreignKey: 'user_id' })
Order.hasMany(OrderDetail, { foreignKey: 'order_id' })
OrderDetail.belongsTo(Order, { foreignKey: 'order_id' })
OrderDetail.belongsTo(Product, { foreignKey: 'product_id' })

Review.belongsTo(User, { foreignKey: 'user_id' })
Review.belongsTo(Product, { foreignKey: 'product_id' })

module.exports = {
  User,
  Category,
  Banner,
  Brand,
  Product,
  Order,
  OrderDetail,
  Review
}
