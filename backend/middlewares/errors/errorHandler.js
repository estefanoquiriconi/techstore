const errorHandler = (err, req, res, next) => {
  console.error(err)
  res.status(err.httpStatus || 500).json({
    ...err,
    status: 'error',
    message: err.message
  })
}

module.exports = { errorHandler }
