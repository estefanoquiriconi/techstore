const { getAll } = require('./getAll.service.js')
const { getById } = require('./getById.service.js')
const { getByQuery } = require('./getByQuery.service.js')

module.exports = {
  getAll,
  getById,
  getByQuery
}
