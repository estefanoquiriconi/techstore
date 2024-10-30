const { DataTypes } = require('sequelize');
const sequelize = require('../config/database');

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
  },
  subtotal: {
    type: DataTypes.VIRTUAL,
    get() {
      return this.quantity * this.price;
    },
    set(value) {
      throw new Error('Do not try to set the `subtotal` value!');
    }
  }
}, {
  timestamps: false,
  tableName: 'order_details'
});

module.exports = OrderDetail;