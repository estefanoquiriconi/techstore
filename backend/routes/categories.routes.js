const express = require('express')
const router = express.Router()
const controller = require('../controllers/categoryController')

router.get('/', controller.index)
router.get('/:id', controller.show)

module.exports = router
