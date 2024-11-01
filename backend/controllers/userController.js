const userService = require('../services/users/index.service.js')
const { notFoundError } = require('../helpers/error.helper.js')

exports.index = async (req, res, next) => {
  try {
    const users = await userService.getAll()
    res.json(users)
  } catch (error) {
    next(error)
  }
}

exports.show = async (req, res, next) => {
  try {
    const user = await userService.getById(req.params.id)
    if (!user) notFoundError('Usuario no encontrado', 'USER_NOT_FOUND')
    res.json(user)
  } catch (error) {
    next(error)
  }
}
