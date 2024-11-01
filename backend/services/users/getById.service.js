const { User } = require('../../models/index.js')

const getById = async (id) => {
  const user = await User.findByPk(id, {
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
  return user
}

module.exports = { getById }
