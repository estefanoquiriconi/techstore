const { sendMail } = require('../../helpers/email.helper')

const registerSendEmail = async (firstName, email, registrationCode) => {
  const subject = 'Bienvenido a TechStore - Activa tu cuenta'

  const body = `
    <div style="font-family: Arial, sans-serif; max-width: 600px; margin: auto; padding: 20px; border: 1px solid #f0f0f0; border-radius: 10px; background-color: #ffffff;">
        <div style="text-align: center; padding: 10px;">
            <h1>TechStore 😎</h1>
        </div>
        <h2 style="color: #333;">¡Hola ${firstName}!</h2>
        <p style="color: #555;">Nos alegra que hayas decidido unirte a <b>TechStore</b>.</p>
        <p>Para completar tu registro, por favor utiliza el siguiente código de activación:</p>
        <div style="text-align: center; margin: 20px 0;">
            <span style="display: inline-block; padding: 15px 30px; font-size: 22px; color: #fff; background-color: #40404C; border-radius: 5px;">
                ${registrationCode}
            </span>
        </div>
        <p style="color: #555;">Si tienes alguna pregunta, no dudes en contactarnos respondiendo a este correo.</p>
        <p>Gracias por unirte a <b>TechStore</b>. ¡Esperamos verte pronto comprando lo que más te gusta!</p>
        <hr style="border: none; border-top: 1px solid #f0f0f0; margin: 20px 0;">
        <p style="font-size: 12px; color: #999; text-align: center;">
            © ${new Date().getFullYear()} TechStore. Todos los derechos reservados.
        </p>
    </div>`

  await sendMail(email, subject, body)
}

module.exports = { registerSendEmail }
