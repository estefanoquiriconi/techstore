const { DataTypes } = require('sequelize')
const sequelize = require('../database/config/sequelize.js')

const Order = sequelize.define('Order', {
  id: {
    type: DataTypes.BIGINT,
    primaryKey: true,
    autoIncrement: true
  },
  total_amount: {
    type: DataTypes.DECIMAL(10, 2)
  },
  status: {
    type: DataTypes.ENUM('preparación', 'enviado', 'entregado'),
    defaultValue: 'preparación'
  }
}, {
  timestamps: true,
  createdAt: 'created_at',
  updatedAt: 'updated_at',
  tableName: 'orders'
})

module.exports = Order
