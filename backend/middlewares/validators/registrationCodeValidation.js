const { body } = require('express-validator')

module.exports = [
  body('registration_code')
    .trim()
    .notEmpty()
    .withMessage('Debes ingresar un código de validación')
    .bail()
    .isLength({ min: 8, max: 8 })
    .withMessage('El código de validación debe contener exactamente 8 caracteres')
    .bail()
]
