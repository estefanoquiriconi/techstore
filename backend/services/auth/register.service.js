const User = require('../../models/user')
const { internalServerError } = require('../../helpers/error.helper')

const register = async (first_name, last_name, email, password, registration_code) => {
    try {
        const newUser = await User.create({
            first_name,
            last_name,
            email,
            password,
            registration_code,
        });
        return newUser;
    } catch (error) {
        internalServerError(error.message, 'DATA_INSERT_ERROR');
    }
}

module.exports = { register };