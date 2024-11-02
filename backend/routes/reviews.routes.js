const express = require('express')
const router = express.Router()
const controller = require('../controllers/reviewController')

router.get('/', controller.index)
router.get('/:id', controller.show)

module.exports = router