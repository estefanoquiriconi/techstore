const express = require('express');
const router = express.Router();

const { login } = require('../controllers/auth/loginController');
const { register } = require('../controllers/auth/registerController');
const { activateAccount } = require('../controllers/auth/activateAccount')
const { recoverPassword } = require('../controllers/auth/recoverPassword')
const { verifyRecoverCode } = require('../controllers/auth/verifyRecoverCode')

const loginValidation = require('../middlewares/validators/loginValidation');
const registerValidation = require('../middlewares/validators/registerValidation');
const registrationCodeValidation = require('../middlewares/validators/registrationCodeValidation');
const recoverpassCodeValidation = require('../middlewares/validators/recoverpassCodeValidation');

router.post('/login', loginValidation, login);
router.post('/register', registerValidation, register);
router.post('/activate', registrationCodeValidation, activateAccount)
router.post('/recover-password', recoverPassword)
router.post('/verify-recover-code', recoverpassCodeValidation, verifyRecoverCode);

module.exports = router;