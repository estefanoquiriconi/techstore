const { DataTypes } = require('sequelize')
const sequelize = require('../database/config/sequelize.js')

const Category = sequelize.define('Category', {
  id: {
    type: DataTypes.BIGINT,
    primaryKey: true,
    autoIncrement: true
  },
  name: {
    type: DataTypes.STRING,
    unique: true
  },
  image_url: {
    type: DataTypes.STRING
  }
}, {
  timestamps: false,
  tableName: 'categories'
})

module.exports = Category
