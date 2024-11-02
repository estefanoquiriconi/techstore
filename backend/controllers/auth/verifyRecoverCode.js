const { validationResult } = require('express-validator')
const { notFoundError, badRequestError } = require('../../helpers/error.helper')
const User = require('../../models/user')

const verifyRecoverCode = async (req, res, next) => {
  try {
    const errors = validationResult(req)
    if (!errors.isEmpty()) badRequestError(errors.array())

    const { recoverpass_code: recoverpassCode } = req.body

    const user = await User.findOne({ where: { recoverpass_code: recoverpassCode } })
    if (!user) notFoundError('Usuario no encontrado', 'USER_NOT_FOUND')

    if (user.recoverpass_code === recoverpassCode) {
      user.recoverpass_code = null
      await user.save()
    }

    res.status(200).json({
      status: 'success',
      message: 'Código de recuperación verificado.'
    })
  } catch (error) {
    next(error)
  }
}

module.exports = { verifyRecoverCode }
