const userService = require('../services/users/index.service.js')
const { notFoundError } = require('../helpers/error.helper.js')
const User = require('../models/user.js')

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

exports.update = async (req, res, next) => {
  try {
    const { id } = req.params
    const { phone, address, latitude, longitude } = req.body
    const user = await User.findByPk(id)
    if (!user) notFoundError('Usuario no encontrado', 'USER_NOT_FOUND')
    user.phone = phone
    user.address = address
    user.latitude = latitude
    user.longitude = longitude
    await user.save()
    res.json({
      message: 'Usuario actualizado correctamente'
    })
  } catch (error) {
    next(error)
  }
}
