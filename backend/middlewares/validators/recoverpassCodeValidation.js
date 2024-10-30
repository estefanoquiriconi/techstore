const { body } = require('express-validator')

module.exports = [
  body('recoverpass_code')
    .trim()
    .notEmpty()
    .withMessage('Debes ingresar un código de recuperación')
    .bail()
    .isLength({ min: 8, max: 8 })
    .withMessage('El código de recuperación debe contener exactamente 8 caracteres')
    .bail()
]
