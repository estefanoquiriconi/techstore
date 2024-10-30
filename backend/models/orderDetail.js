const { DataTypes } = require('sequelize')
const sequelize = require('../database/config/sequelize.js')

const OrderDetail = sequelize.define('OrderDetail', {
  id: {
    type: DataTypes.BIGINT,
    primaryKey: true,
    autoIncrement: true
  },
  quantity: {
    type: DataTypes.INTEGER,
    validate: {
      min: 1
    }
  },
  price: {
    type: DataTypes.DECIMAL(10, 2)
  }
}, {
  timestamps: false,
  tableName: 'order_details'
})

module.exports = OrderDetail
