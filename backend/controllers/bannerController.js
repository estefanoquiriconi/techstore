const { Banner } = require('../models/index.js')
const { notFoundError } = require('../helpers/error.helper.js')

exports.index = async (req, res, next) => {
  try {
    const banners = await Banner.findAll()
    res.json(banners)
  } catch (error) {
    next(error)
  }
}

exports.show = async (req, res, next) => {
  try {
    const banner = await Banner.findByPk(req.params.id)
    if (!banner) notFoundError('Banner no encontrado', 'BANNER_NOT_FOUND')
    res.json(banner)
  } catch (error) {
    next(error)
  }
}
