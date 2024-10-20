const { register } = require('./register.service.js')
const { registerSendEmail } = require('./registerSendEmail.service.js')
const { activeAccount } = require('./activeAccount.service.js')

module.exports = {
    register,
    registerSendEmail,
    activeAccount,
}