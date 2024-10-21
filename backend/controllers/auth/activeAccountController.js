const User = require('../../models/user')
const authService = require('../../services/auth/index.service');
const { validationResult } = require('express-validator');
const { notFoundError, badRequestError } = require('../../helpers/error.helper');

const activeAccount = async (req, res, next) => {
    try {
        const errors = validationResult(req);
        if (!errors.isEmpty()) badRequestError(errors.array());

        const { registration_code } = req.body;

        const user = await User.findOne({ where: { registration_code } });
        if (!user) notFoundError("Usuario no encontrado", "USER_NOT_FOUND");

        authService.activeAccount(user);

        res.status(200).json({
            status: "success",
            message: "Cuenta activa con éxito."
        })


    } catch (error) {
        next(error);
    }
}

module.exports = { activeAccount }