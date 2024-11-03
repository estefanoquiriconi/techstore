const jwt = require('jsonwebtoken')
const userService = require('../../services/users/index.service.js')

const verifyToken = (req, res) => {
  const authorization = req.headers.authorization
  const token = authorization && authorization.split(' ')[1]
  const { JWT_SECRET_KEY } = process.env

  if (!token) {
    return res.status(403).json({ msg: 'Token requerido' })
  }

  jwt.verify(token, JWT_SECRET_KEY, async (err, payload) => {
    if (err) {
      return res.status(403).json({ msg: 'Token inválido' })
    }
    const user = await userService.getById(payload.id)
    res.status(200).json(user)
  })
}

module.exports = { verifyToken }
