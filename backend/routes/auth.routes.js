const express = require('express');
const router = express.Router();

const { login } = require('../controllers/auth/loginController');
const { register } = require('../controllers/auth/registerController');

const loginValidation = require('../middlewares/validators/loginValidation');
const registerValidation = require('../middlewares/validators/registerValidation');

router.post('/login', loginValidation, login);
router.post('/register', registerValidation, register);

module.exports = router;