const { DataTypes } = require('sequelize')
const sequelize = require('../database/config/sequelize.js')

const Product = sequelize.define('Product', {
  id: {
    type: DataTypes.BIGINT,
    primaryKey: true,
    autoIncrement: true
  },
  name: {
    type: DataTypes.STRING
  },
  price: {
    type: DataTypes.DECIMAL(10, 2)
  },
  description: {
    type: DataTypes.TEXT
  },
  image_url: {
    type: DataTypes.STRING
  },
  stock: {
    type: DataTypes.INTEGER,
    validate: {
      min: 0
    }
  },
  active: {
    type: DataTypes.BOOLEAN,
    defaultValue: true
  }
}, {
  timestamps: true,
  createdAt: 'created_at',
  updatedAt: 'updated_at',
  tableName: 'products'
})

module.exports = Product
