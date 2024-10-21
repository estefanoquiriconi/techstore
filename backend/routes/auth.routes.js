const express = require('express');
const router = express.Router();

const { login } = require('../controllers/auth/loginController');
const { register } = require('../controllers/auth/registerController');
const { activeAccount } = require('../controllers/auth/activeAccountController')

const loginValidation = require('../middlewares/validators/loginValidation');
const registerValidation = require('../middlewares/validators/registerValidation');
const registrationCodeValidation = require('../middlewares/validators/registrationCodeValidation');

router.post('/login', loginValidation, login);
router.post('/register', registerValidation, register);
router.post('/active', registrationCodeValidation, activeAccount)

module.exports = router;