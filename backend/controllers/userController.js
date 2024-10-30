const { User } = require('../models/index.js')
const { notFoundError } = require('../helpers/error.helper.js')

exports.index = async (req, res, next) => {
  try {
    const users = await User.findAll()
    res.json(users)
  } catch (error) {
    next(error)
  }
}

exports.show = async (req, res, next) => {
  try {
    const user = await User.findByPk(req.params.id)
    if (!user) notFoundError('Marca no encontrada', 'USER_NOT_FOUND')
    res.json(user)
  } catch (error) {
    next(error)
  }
}
