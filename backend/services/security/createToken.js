const jwt = require('jsonwebtoken')

const createToken = (info) => {
    const { JWT_SECRET_KEY, JWT_EXPIRATION_TIME } = process.env
    return jwt.sign(
        info,
        JWT_SECRET_KEY,
        { expiresIn: JWT_EXPIRATION_TIME }
    );
}

module.exports = { createToken }