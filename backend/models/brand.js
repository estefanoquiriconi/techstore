const { DataTypes } = require('sequelize')
const sequelize = require('../database/config/sequelize.js')

const Brand = sequelize.define('Brand', {
  id: {
    type: DataTypes.BIGINT,
    primaryKey: true,
    autoIncrement: true
  },
  name: {
    type: DataTypes.STRING,
    unique: true
  }
}, {
  timestamps: false,
  tableName: 'brands'
})

module.exports = Brand
