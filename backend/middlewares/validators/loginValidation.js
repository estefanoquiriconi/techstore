const { body } = require("express-validator");

module.exports = [
    body("email")
        .notEmpty()
        .withMessage("Debes ingresar un email")
        .bail()
        .isEmail()
        .withMessage("Debes ingresar un email válido")
        .bail(),
    body("password")
        .notEmpty()
        .withMessage("Debes ingresar una contraseña")
        .bail(),
];
