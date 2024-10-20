const User = require('../../models/user')
const bcryptjs = require('bcryptjs');
const authService = require('../../services/auth/index.service');
const { validationResult } = require('express-validator');
const { v4: uuidv4 } = require('uuid');
const { badRequestError, conflictError } = require('../../helpers/error.helper')

const register = async (req, res, next) => {
    try {
        const errors = validationResult(req);
        if (!errors.isEmpty()) badRequestError(errors.array());

        const { email, password, first_name, last_name } = req.body;
        const userExist = await User.findOne({ where: { email: email } });

        if (userExist) conflictError('El email ya está registrado.');

        const registration_code = uuidv4().split('-')[0].toUpperCase();

        const hashedPassword = bcryptjs.hashSync(password, 10);

        await authService.register(first_name, last_name, email, hashedPassword, registration_code);

        await authService.registerSendEmail(first_name, last_name, email, registration_code);

        return res.status(201).json({
            status: "success",
            message: "Usuario registrado con éxito.",
        });
    } catch (error) {
        next(error);
    }
}

module.exports = { register }