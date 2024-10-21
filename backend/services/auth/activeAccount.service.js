const User = require('../../models/user')
const { internalServerError } = require('../../helpers/error.helper')

const activeAccount = async (user) => {
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

module.exports = { activeAccount }