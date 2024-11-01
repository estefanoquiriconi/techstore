const { Review } = require('../models/index.js')
const { notFoundError } = require('../helpers/error.helper.js')

exports.index = async (req, res, next) => {
  try {
    const reviews = await Review.findAll()
    res.json(reviews)
  } catch (error) {
    next(error)
  }
}

exports.show = async (req, res, next) => {
  try {
    const review = await Review.findByPk(req.params.id)
    if (!review) notFoundError('Reseña no encontrada', 'REVIEW_NOT_FOUND')
    res.json(review)
  } catch (error) {
    next(error)
  }
}
