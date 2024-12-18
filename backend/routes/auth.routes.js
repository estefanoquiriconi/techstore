const express = require('express')
const router = express.Router()

const authController = require('../controllers/auth/index')

const loginValidation = require('../middlewares/validators/loginValidation')
const registerValidation = require('../middlewares/validators/registerValidation')
const registrationCodeValidation = require('../middlewares/validators/registrationCodeValidation')
const recoverpassCodeValidation = require('../middlewares/validators/recoverpassCodeValidation')

/**
 * @swagger
 * /auth/login:
 *   post:
 *     summary: Iniciar sesión
 *     description: Permite a un usuario iniciar sesión con su correo y contraseña.
 *     requestBody:
 *       required: true
 *       content:
 *         application/json:
 *           schema:
 *             type: object
 *             properties:
 *               email:
 *                 type: string
 *                 example: "estefanoquiriconi@gmail.com"
 *               password:
 *                 type: string
 *                 example: "12345"
 *     responses:
 *       200:
 *         description: Usuario logueado con éxito.
 *       400:
 *         description: Error de validación.
 *       404:
 *         description: Usuario no encontrado.
 *       403:
 *         description: Credenciales incorrectas.
 */
router.post('/login', loginValidation, authController.login)

/**
 * @swagger
 * /auth/register:
 *   post:
 *     summary: Registrar un nuevo usuario
 *     description: Permite registrar un nuevo usuario en la plataforma.
 *     requestBody:
 *       required: true
 *       content:
 *         application/json:
 *           schema:
 *             type: object
 *             properties:
 *               email:
 *                 type: string
 *                 example: "estefanoquiriconi@gmail.com"
 *               password:
 *                 type: string
 *                 example: "12345"
 *               first_name:
 *                 type: string
 *                 example: "Estéfano"
 *               last_name:
 *                 type: string
 *                 example: "Quiriconi"
 *     responses:
 *       201:
 *         description: Usuario registrado con éxito.
 *       400:
 *         description: Error de validación.
 *       409:
 *         description: El email ya está registrado.
 */
router.post('/register', registerValidation, authController.register)

/**
 * @swagger
 * /auth/activate:
 *   post:
 *     summary: Activar cuenta de usuario
 *     description: Permite activar la cuenta de un usuario utilizando un código de registro.
 *     requestBody:
 *       required: true
 *       content:
 *         application/json:
 *           schema:
 *             type: object
 *             properties:
 *               registration_code:
 *                 type: string
 *                 example: "C6423A54"
 *     responses:
 *       200:
 *         description: Cuenta activa con éxito.
 *       400:
 *         description: Error de validación.
 *       404:
 *         description: Usuario no encontrado.
 */
router.post('/activate', registrationCodeValidation, authController.activateAccount)

/**
 * @swagger
 * /auth/recover-password:
 *   post:
 *     summary: Recuperar contraseña
 *     description: Envía un código de recuperación al correo del usuario.
 *     requestBody:
 *       required: true
 *       content:
 *         application/json:
 *           schema:
 *             type: object
 *             properties:
 *               email:
 *                 type: string
 *                 example: "estefanoquiriconi@gmail.com"
 *     responses:
 *       200:
 *         description: Código de recuperación enviado por correo.
 *       404:
 *         description: Usuario no encontrado.
 */
router.post('/recover-password', authController.recoverPassword)

/**
 * @swagger
 * /auth/verify-recover-code:
 *   post:
 *     summary: Verificar código de recuperación
 *     description: Verifica el código de recuperación proporcionado por el usuario.
 *     requestBody:
 *       required: true
 *       content:
 *         application/json:
 *           schema:
 *             type: object
 *             properties:
 *               recoverpass_code:
 *                 type: string
 *                 example: "BDD5CF95"
 *     responses:
 *       200:
 *         description: Código de recuperación verificado.
 *       400:
 *         description: Error de validación.
 *       404:
 *         description: Código de recuperación inválido.
 */
router.post('/verify-recover-code', recoverpassCodeValidation, authController.verifyRecoverCode)

/**
 * @swagger
 * /auth/reset-password:
 *   post:
 *     summary: Restablecer contraseña
 *     description: Permite a un usuario restablecer su contraseña.
 *     requestBody:
 *       required: true
 *       content:
 *         application/json:
 *           schema:
 *             type: object
 *             properties:
 *               email:
 *                 type: string
 *                 example: "estefanoquiriconi@gmail.com"
 *               new_password:
 *                 type: string
 *                 example: "123"
 *     responses:
 *       200:
 *         description: Contraseña actualizada con éxito.
 *       404:
 *         description: Usuario no encontrado.
 */
router.post('/reset-password', authController.resetPassword)

/**
 * @swagger
 * /auth/verify-token:
 *   get:
 *     summary: Verificar token
 *     description: Verifica el token de autenticación del usuario.
 *     responses:
 *       200:
 *         description: Token válido y usuario encontrado.
 *       403:
 *         description: Token requerido o inválido.
 */
router.get('/verify-token', authController.verifyToken)

module.exports = router
