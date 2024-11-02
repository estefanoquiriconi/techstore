const { sendMail } = require('../../helpers/email.helper')
const { notFoundError } = require('../../helpers/error.helper')
const User = require('../../models/user')
const { v4: uuidv4 } = require('uuid')

const recoverPassword = async (req, res, next) => {
  try {
    const { email } = req.body

    const user = await User.findOne({ where: { email } })
    if (!user) notFoundError('Usuario no encontrado', 'USER_NOT_FOUND')

    const recoverpassCode = uuidv4().split('-')[0].toUpperCase()

    user.recoverpass_code = recoverpassCode
    await user.save()

    await sendMail(user.email, 'Código de recuperación', `<h1>${recoverpassCode}</h1>`)

    res.status(200).json({
      status: 'success',
      message: 'Código de recuperación enviado por correo.'
    })
  } catch (error) {
    next(error)
  }
}

module.exports = { recoverPassword }
