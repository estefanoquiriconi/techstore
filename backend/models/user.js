const { DataTypes } = require('sequelize');
const sequelize = require('../config/database');

const User = sequelize.define('User', {
  id: {
    type: DataTypes.BIGINT,
    primaryKey: true,
    autoIncrement: true
  },
  first_name: {
    type: DataTypes.STRING
  },
  last_name: {
    type: DataTypes.STRING
  },
  email: {
    type: DataTypes.STRING,
    unique: true
  },
  avatar: {
    type: DataTypes.STRING
  },
  phone: {
    type: DataTypes.STRING(20)
  },
  address: {
    type: DataTypes.STRING
  },
  password: {
    type: DataTypes.STRING
  },
  active: {
    type: DataTypes.BOOLEAN,
    defaultValue: true
  },
  registration_code: {
    type: DataTypes.STRING(50)
  },
  recoverpass_code: {
    type: DataTypes.STRING(10)
  }
}, {
  timestamps: true,
  createdAt: 'created_at',
  updatedAt: 'updated_at',
  tableName: 'users'
});

module.exports = User;