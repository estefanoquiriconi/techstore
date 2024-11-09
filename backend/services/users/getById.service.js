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
      'latitude',
      'longitude',
      'avatar'
    ]
  })
  return user
}

module.exports = { getById }
