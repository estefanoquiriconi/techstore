const { DataTypes } = require('sequelize');
const sequelize = require('../config/database');

const Coupon = sequelize.define('Coupon', {
  id: {
    type: DataTypes.BIGINT,
    primaryKey: true,
    autoIncrement: true
  },
  code: {
    type: DataTypes.STRING(50),
    unique: true
  },
  discount: {
    type: DataTypes.DECIMAL(5, 2)
  },
  uses: {
    type: DataTypes.INTEGER,
    defaultValue: 0
  },
  max_uses: {
    type: DataTypes.INTEGER,
    defaultValue: 1
  },
  start_date: {
    type: DataTypes.DATE
  },
  end_date: {
    type: DataTypes.DATE
  },
  active: {
    type: DataTypes.BOOLEAN,
    defaultValue: true
  }
}, {
  timestamps: false,
  tableName: 'coupons'
});

module.exports = Coupon;