const { User } = require('../../models/index.js')
const { internalServerError } = require('../../helpers/error.helper')

const activate = async (user) => {
  try {
    await User.update(
      {
        active: true,
        registration_code: null
      },
      { where: { id: user.id } }
    )
  } catch (error) {
    internalServerError(error.message, 'DATE_UPDATE_ERROR')
  }
}

module.exports = { activate }
