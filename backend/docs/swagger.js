const swaggerJSDoc = require('swagger-jsdoc')
const swageerUI = require('swagger-ui-express')

const swaggerDefinition = {
  openapi: '3.0.0',
  info: {
    title: 'TechStore API',
    version: '1.0.0',
    description: 'Documentación de la API REST: TechStore'
  },
  servers: [
    {
      url: 'http://localhost:3000/api/',
      description: 'Servidor local'
    }
  ]
}

const options = {
  swaggerDefinition,
  apis: ['./routes/*.js', './docs/schemas/*.js']
}

const swaggerSpec = swaggerJSDoc(options)

module.exports = (app) => {
  app.use('/', swageerUI.serve, swageerUI.setup(swaggerSpec))
}