const { body } = require('express-validator')

module.exports = [
  body('first_name')
    .notEmpty()
    .withMessage('Debes ingresar un nombre')
    .bail()
    .isLength({ min: 3 })
    .withMessage('El nombre debe contener como mínimo 3 caracteres')
    .bail()
    .isAlpha('es-ES', { ignore: ' ' })
    .withMessage('El nombre debe contener solo letras'),
  body('last_name')
    .notEmpty()
    .withMessage('Debes ingresar un apellido')
    .bail()
    .isLength({ min: 3 })
    .withMessage('El apellido debe contener como mínimo 3 caracteres')
    .isAlpha('es-ES', { ignore: ' ' })
    .withMessage('El apellido debe contener solo letras'),
  body('email')
    .notEmpty()
    .withMessage('Debes ingresar un email')
    .bail()
    .isEmail()
    .withMessage('Debes ingresar un email válido')
    .bail(),
  body('password')
    .notEmpty()
    .withMessage('Debes ingresar una contraseña')
    .bail()
]
