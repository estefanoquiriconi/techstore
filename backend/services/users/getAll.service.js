const { User } = require('../../models/index.js')

const getAll = async () => {
  const users = await User.findAll({
    attributes: [
      'id',
      'first_name',
      'last_name',
      'email',
      'phone',
      'address',
      'avatar'
    ]
  })
  return users
}

module.exports = { getAll }
