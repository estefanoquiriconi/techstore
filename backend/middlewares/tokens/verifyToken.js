const jwt = require('jsonwebtoken')

const verifyToken = (req, res, next) => {
  const authorization = req.headers.authorization
  const token = authorization && authorization.split(' ')[1]
  const { JWT_SECRET_KEY } = process.env

  if (!token) {
    return res.status(403).json({ msg: 'Token requerido' })
  }

  jwt.verify(token, JWT_SECRET_KEY, (err, user) => {
    if (err) {
      return res.status(403).json({ msg: 'Token inválido' })
    }
    req.user = user
    next()
  })
}

module.exports = { verifyToken }
