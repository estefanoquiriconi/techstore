const { register } = require('./register.service.js')
const { registerSendEmail } = require('./registerSendEmail.service.js')
const { activate } = require('./activate.service.js')

module.exports = {
  register,
  registerSendEmail,
  activate
}
