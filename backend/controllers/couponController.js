const Coupon = require('../models/coupon');

exports.index = async (req, res) => {
  try {
    const coupons = await Coupon.findAll();
    res.json(coupons);
  } catch (error) {
    res.status(500).json({ message: error.message });
  }
};

exports.store = async (req, res) => {
  try {
    const coupon = await Coupon.create(req.body);
    res.status(201).json(coupon);
  } catch (error) {
    res.status(400).json({ message: error.message });
  }
};

exports.show = async (req, res) => {
  try {
    const coupon = await Coupon.findByPk(req.params.id);
    if (coupon) {
      res.json(coupon);
    } else {
      res.status(404).json({ message: 'coupon not found' });
    }
  } catch (error) {
    res.status(500).json({ message: error.message });
  }
};

exports.update = async (req, res) => {
  try {
    const coupon = await Coupon.findByPk(req.params.id);
    if (coupon) {
      await Coupon.update(req.body);
      res.json(coupon);
    } else {
      res.status(404).json({ message: 'Coupon not found' });
    }
  } catch (error) {
    res.status(400).json({ message: error.message });
  }
};

exports.destroy = async (req, res) => {
  try {
    const coupon = await Coupon.findByPk(req.params.id);
    if (coupon) {
      await coupon.destroy();
      res.json({ message: 'Coupon deleted' });
    } else {
      res.status(404).json({ message: 'Coupon not found' });
    }
  } catch (error) {
    res.status(500).json({ message: error.message });
  }
};