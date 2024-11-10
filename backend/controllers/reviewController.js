const { Review } = require('../models/index.js')
const { notFoundError } = require('../helpers/error.helper.js')

exports.index = async (req, res, next) => {
  try {
    const reviews = await Review.findAll({
      attributes: ['id', 'comment', 'rating', 'created_at']
    })
    res.json(reviews)
  } catch (error) {
    next(error)
  }
}

exports.show = async (req, res, next) => {
  try {
    const review = await Review.findByPk(req.params.id, {
      attributes: ['id', 'comment', 'rating', 'created_at']
    })
    if (!review) notFoundError('Reseña no encontrada', 'REVIEW_NOT_FOUND')
    res.json(review)
  } catch (error) {
    next(error)
  }
}

exports.getByProductId = async (req, res, next) => {
  try {
    const { id } = req.params
    const reviews = await Review.findAll({
      where: { product_id: id },
      attributes: ['id', 'comment', 'rating', 'created_at']
    })
    return res.json(reviews)
  } catch (error) {
    next(error)
  }
}
