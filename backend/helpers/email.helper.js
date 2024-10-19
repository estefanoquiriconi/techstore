const nodemailer = require('nodemailer')
const { SMTP_HOST, SMTP_PORT, SMTP_USER, SMTP_PASS } = process.env
const errors = require('./error.helper')

const transporter = nodemailer.createTransport({
    host: SMTP_HOST,
    port: SMTP_PORT,
    auth:{
        user: SMTP_USER,
        pass: SMTP_PASS
    }
});

const sendMail = async (to, subject, body) => {
    try {
        const mail = {
            from: SMTP_USER,
            to,
            subject,
            html: body
        }
        await transporter.sendMail(mail)
    } catch (error) {
        console.error(error);
        errors.sendEmailError();
    }
}

module.exports = { sendMail }