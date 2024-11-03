const { login } = require('./loginController')
const { register } = require('./registerController')
const { activateAccount } = require('./activateAccount')
const { recoverPassword } = require('./recoverPassword')
const { verifyRecoverCode } = require('./verifyRecoverCode')
const { resetPassword } = require('./resetPassword')
const { verifyToken } = require('./verifyTokenController.js')

module.exports = {
  login,
  register,
  activateAccount,
  recoverPassword,
  verifyRecoverCode,
  resetPassword,
  verifyToken
}
