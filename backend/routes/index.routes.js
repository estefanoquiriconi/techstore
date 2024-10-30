const express = require('express')
const authRouter = require('./auth.routes.js')
const bannerRouter = require('./banners.routes.js')
const brandRouter = require('./brands.routes.js')
const categoryRouter = require('./categories.routes.js')
const orderRouter = require('./orders.routes.js')
const productRouter = require('./products.routes.js')
const reviewRouter = require('./reviews.routes.js')
const userRouter = require('./users.routes.js')

const router = express.Router()

router.use('/auth', authRouter)
router.use('/banners', bannerRouter)
router.use('/brands', brandRouter)
router.use('/categories', categoryRouter)
router.use('/orders', orderRouter)
router.use('/products', productRouter)
router.use('/reviews', reviewRouter)
router.use('/users', userRouter)

module.exports = router
