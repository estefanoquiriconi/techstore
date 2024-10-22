const express = require('express');
const router = express.Router();

const authController = require('../controllers/auth/index');

const loginValidation = require('../middlewares/validators/loginValidation');
const registerValidation = require('../middlewares/validators/registerValidation');
const registrationCodeValidation = require('../middlewares/validators/registrationCodeValidation');
const recoverpassCodeValidation = require('../middlewares/validators/recoverpassCodeValidation');

router.post('/login', loginValidation, authController.login);
router.post('/register', registerValidation, authController.register);
router.post('/activate', registrationCodeValidation, authController.activateAccount)
router.post('/recover-password', authController.recoverPassword)
router.post('/verify-recover-code', recoverpassCodeValidation, authController.verifyRecoverCode);
router.post('/reset-password', authController.resetPassword);

module.exports = router;