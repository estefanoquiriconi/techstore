const jwt = require('jsonwebtoken')

const createToken = (payload) => {
  const { JWT_SECRET_KEY, JWT_EXPIRATION_TIME } = process.env
  return jwt.sign(
    payload,
    JWT_SECRET_KEY,
    { expiresIn: JWT_EXPIRATION_TIME }
  )
}

module.exports = { createToken }
