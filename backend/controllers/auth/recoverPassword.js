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

    const body = `
    <div style="font-family: Arial, sans-serif; max-width: 600px; margin: auto; padding: 20px; border: 1px solid #f0f0f0; border-radius: 10px; background-color: #ffffff;">
        <div style="text-align: center; padding: 10px;">
            <h1>TechStore 😎</h1>
        </div>
        <h2 style="color: #333;">¡Recupera tu contraseña!</h2>
        <p style="color: #555;">Has solicitado restablecer tu contraseña en <b>TechStore</b>.</p>
        <p>Utiliza el siguiente código para continuar con el proceso:</p>
        <div style="text-align: center; margin: 20px 0;">
            <span style="display: inline-block; padding: 15px 30px; font-size: 22px; color: #fff; background-color: #40404C; border-radius: 5px;">
                ${recoverpassCode}
            </span>
        </div>
        <p style="color: #555;">Si no solicitaste este cambio, puedes ignorar este correo.</p>
        <p>¡Gracias por confiar en <b>TechStore</b>!</p>
        <hr style="border: none; border-top: 1px solid #f0f0f0; margin: 20px 0;">
        <p style="font-size: 12px; color: #999; text-align: center;">
            © ${new Date().getFullYear()} TechStore. Todos los derechos reservados.
        </p>
    </div>`

    await sendMail(user.email, 'Recupera tu contraseña - TechStore', body)

    res.status(200).json({
      status: 'success',
      message: 'Código de recuperación enviado por correo.'
    })
  } catch (error) {
    next(error)
  }
}

module.exports = { recoverPassword }
