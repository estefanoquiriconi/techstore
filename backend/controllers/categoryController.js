const Category = require('../models/category');

exports.index = async (req, res) => {
  try {
    const categories = await Category.findAll({
      order: [['id', 'ASC']]
    });
    categories[0].setDataValue('image_url', 'https://armoto.vtexassets.com/arquivos/ids/165316-1200-auto?v=638350946189500000&width=1200&height=auto&aspect=true')
    categories[1].setDataValue('image_url', 'https://samsungar.vtexassets.com/arquivos/ids/192816-1200-auto?width=1200&height=auto&aspect=true')
    categories[2].setDataValue('image_url', 'https://ar-media.hptiendaenlinea.com/catalog/product/cache/74c1057f7991b4edb2bc7bdaa94de933/9/1/91S43LA-1_T1717517862.png')
    categories[3].setDataValue('image_url', 'https://www.jbl.com.ar/dw/image/v2/AAUJ_PRD/on/demandware.static/-/Sites-masterCatalog_Harman/default/dw4df8ac7e/01.JBL_Tune%20520BT_Product%20Image_Hero_Blue.png?sw=537&sfrm=png')
    
    res.json(categories);
  } catch (error) {
    res.status(500).json({ message: error.message });
  }
};

exports.store = async (req, res) => {
  try {
    const category = await Category.create(req.body);
    res.status(201).json(category);
  } catch (error) {
    res.status(400).json({ message: error.message });
  }
};

exports.show = async (req, res) => {
  try {
    const category = await Category.findByPk(req.params.id);
    if (category) {
      res.json(category);
    } else {
      res.status(404).json({ message: 'Category not found' });
    }
  } catch (error) {
    res.status(500).json({ message: error.message });
  }
};

exports.update = async (req, res) => {
  try {
    const category = await Category.findByPk(req.params.id);
    if (category) {
      await category.update(req.body);
      res.json(category);
    } else {
      res.status(404).json({ message: 'Category not found' });
    }
  } catch (error) {
    res.status(400).json({ message: error.message });
  }
};

exports.destroy = async (req, res) => {
  try {
    const category = await Category.findByPk(req.params.id);
    if (category) {
      await Category.destroy();
      res.json({ message: 'Category deleted' });
    } else {
      res.status(404).json({ message: 'Category not found' });
    }
  } catch (error) {
    res.status(500).json({ message: error.message });
  }
};