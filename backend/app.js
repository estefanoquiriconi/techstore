const express = require('express')
const path = require('path')
const router = require('./routes/index.routes.js')
const swaggerSetup = require('./docs/swagger.js')
const { errorHandler } = require('./middlewares/errors/errorHandler.js')

const app = express()
app.use(express.json())
app.use(express.static(path.resolve('./public')))
app.use('/api', router)
app.use(errorHandler)

const PORT = process.env.PORT || 3000

swaggerSetup(app)
app.listen(PORT, () => {
  console.log(`Server is running on http://localhost:${PORT}`)
})
