const { User } = require('../../models/index.js')
const { internalServerError } = require('../../helpers/error.helper')

const register = async (firstName, lastName, email, password, registrationCode) => {
  try {
    const newUser = await User.create({
      first_name: firstName,
      last_name: lastName,
      email,
      password,
      avatar: 'https://randomuser.me/api/portraits/men/75.jpg',
      registration_code: registrationCode
    })
    return newUser
  } catch (error) {
    internalServerError(error.message, 'DATA_INSERT_ERROR')
  }
}

module.exports = { register }
