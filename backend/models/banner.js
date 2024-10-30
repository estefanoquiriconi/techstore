const { DataTypes } = require('sequelize')
const sequelize = require('../database/config/sequelize.js')

const Banner = sequelize.define('Banner', {
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
  },
  active: {
    type: DataTypes.BOOLEAN,
    defaultValue: true
  }
}, {
  timestamps: false,
  tableName: 'banners'
})

module.exports = Banner
