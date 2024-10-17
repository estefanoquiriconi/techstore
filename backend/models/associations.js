const User = require('./user');
const Category = require('./category');
const Brand = require('./brand');
const Product = require('./product');
const Order = require('./order');
const OrderDetail = require('./orderDetail');
const Review = require('./review');
const Coupon = require('./coupon');

Product.belongsTo(Brand, { foreignKey: 'brand_id'});
Product.belongsTo(Category, { foreignKey: 'category_id'});

Order.belongsTo(User, { foreignKey: 'user_id'});
Order.hasMany(OrderDetail, { foreignKey: 'order_id'});
OrderDetail.belongsTo(Order, { foreignKey: 'order_id'});
OrderDetail.belongsTo(Product, { foreignKey: 'product_id'});

Review.belongsTo(User, { foreignKey: 'user_id'});
Review.belongsTo(Product, { foreignKey: 'product_id'});

module.exports = {
  User,
  Category,
  Brand,
  Product,
  Order,
  OrderDetail,
  Review,
  Coupon
};