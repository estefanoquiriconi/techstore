const User = require('../../models/user')
const bcryptjs = require('bcryptjs')
const authService = require('../../services/auth/index.service')
const { validationResult } = require('express-validator')
const { v4: uuidv4 } = require('uuid')
const { badRequestError, conflictError } = require('../../helpers/error.helper')

const register = async (req, res, next) => {
  try {
    const errors = validationResult(req)
    if (!errors.isEmpty()) badRequestError(errors.array())

    const { email, password, first_name: firstName, last_name: lastName } = req.body
    const userExist = await User.findOne({ where: { email } })

    if (userExist) conflictError('El email ya está registrado.')

    const registrationCode = uuidv4().split('-')[0].toUpperCase()

    const hashedPassword = await bcryptjs.hash(password, 10)

    await authService.register(firstName, lastName, email, hashedPassword, registrationCode)

    await authService.registerSendEmail(firstName, email, registrationCode)

    return res.status(201).json({
      status: 'success',
      message: 'Usuario registrado con éxito.'
    })
  } catch (error) {
    next(error)
  }
}

module.exports = { register }
