const User = require('../../models/user');
const bcryptjs = require('bcryptjs');
const { notFoundError } = require('../../helpers/error.helper');


const resetPassword = async (req, res, next) => {
    try {
        const { email, new_password } = req.body;
        
        const user = await User.findOne({where : { email }});
        if(!user) notFoundError('Usuario no encontrado', 'USER_NOT_FOUND');

        const hashedPassword = bcryptjs.hashSync(new_password, 10);

        user.password = hashedPassword;
        user.save();

        res.status(200).json({
            status: "success",
            message: "Contraseña actualizada con éxito."
        })

    } catch (error) {
        next(error)
    }
}

module.exports = { resetPassword }