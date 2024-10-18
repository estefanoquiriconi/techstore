const User = require('../models/user')
const { createToken } = require('../services/security/createToken')
const {notFoundError, notAuthorizedError,forbiddenError} = require('../helpers/error.helper')


exports.login = async (req, res, next) => {
    try {
        const { email, password } = req.body

        const user = await User.findOne({ where: {
            email: email
        } });
        if (!user) {
            notFoundError('Usuario no encontrado', 'USER_NOT_FOUND')
        }

        //const passwordOk = await bcryptjs.compare(password, user.password);
        if (user.password != password) {
            notAuthorizedError('Credenciales incorrectas', 'INVALID_CREDENTIALS')
        }

        if (!user?.active) {
            const errorMessage = user?.registrationCode ? 'El usuario aún no fue activado' : 'El usuario está desactivado';
            forbiddenError(errorMessage, user?.registrationCode ? 'PENDING_ACTIVATION' : 'USER_INACTIVE');
        }

        const tokenInfo = {
            id: user.id,
            email: user.email
        }

        const token = createToken(tokenInfo);

        return res.status(200).json({
            status: "success",
            message: "Usuario logueado con éxito",
            token
        });

    } catch (error) {
        next(error);
    }

};


exports.register = async (req, res) => {

};