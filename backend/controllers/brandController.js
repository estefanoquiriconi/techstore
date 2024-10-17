const Brand = require('../models/brand');

exports.index = async (req, res) => {
  try {
    const brands = await Brand.findAll();
    res.json(brands);
  } catch (error) {
    res.status(500).json({ message: error.message });
  }
};

exports.store = async (req, res) => {
  try {
    const brand = await Brand.create(req.body);
    res.status(201).json(brand);
  } catch (error) {
    res.status(400).json({ message: error.message });
  }
};

exports.show = async (req, res) => {
  try {
    const brand = await Brand.findByPk(req.params.id);
    if (brand) {
      res.json(brand);
    } else {
      res.status(404).json({ message: 'Brand not found' });
    }
  } catch (error) {
    res.status(500).json({ message: error.message });
  }
};

exports.update = async (req, res) => {
  try {
    const brand = await Brand.findByPk(req.params.id);
    if (brand) {
      await brand.update(req.body);
      res.json(brand);
    } else {
      res.status(404).json({ message: 'Brand not found' });
    }
  } catch (error) {
    res.status(400).json({ message: error.message });
  }
};

exports.destroy = async (req, res) => {
  try {
    const brand = await Brand.findByPk(req.params.id);
    if (brand) {
      await brand.destroy();
      res.json({ message: 'Brand deleted' });
    } else {
      res.status(404).json({ message: 'Brand not found' });
    }
  } catch (error) {
    res.status(500).json({ message: error.message });
  }
};