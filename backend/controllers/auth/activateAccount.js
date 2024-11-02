const User = require('../../models/user')
const authService = require('../../services/auth/index.service')
const { validationResult } = require('express-validator')
const { notFoundError, badRequestError } = require('../../helpers/error.helper')

const activateAccount = async (req, res, next) => {
  try {
    const errors = validationResult(req)
    if (!errors.isEmpty()) badRequestError(errors.array())

    const { registration_code: registrationCode } = req.body

    const user = await User.findOne({ where: { registration_code: registrationCode } })
    if (!user) notFoundError('Usuario no encontrado', 'USER_NOT_FOUND')

    authService.activate(user)

    res.status(200).json({
      status: 'success',
      message: 'Cuenta activa con éxito.'
    })
  } catch (error) {
    next(error)
  }
}

module.exports = { activateAccount }
