const { DataTypes } = require('sequelize')
const sequelize = require('../database/config/sequelize.js')

const Review = sequelize.define('Review', {
  id: {
    type: DataTypes.BIGINT,
    primaryKey: true,
    autoIncrement: true
  },
  comment: {
    type: DataTypes.TEXT
  },
  rating: {
    type: DataTypes.INTEGER,
    validate: {
      min: 1,
      max: 5
    }
  }
}, {
  timestamps: true,
  createdAt: 'created_at',
  updatedAt: 'updated_at',
  tableName: 'reviews'
})

module.exports = Review
