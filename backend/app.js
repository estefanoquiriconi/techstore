const express = require('express');
const path = require('path')
const userRoutes = require('./routes/users.routes');
const categoryRoutes = require('./routes/categories.routes');
const brandRoutes = require('./routes/brands.routes');
const productRoutes = require('./routes/products.routes');
const orderRoutes = require('./routes/orders.routes');
const reviewRoutes = require('./routes/reviews.routes');
const authRoutes = require('./routes/auth.routes');
const { errorHandler } = require('./middlewares/errors/errorHandler')

const app = express();
app.use(express.json());
app.use(express.static(path.resolve('./public')))

app.use('/api/users', userRoutes);
app.use('/api/categories', categoryRoutes);
app.use('/api/brands', brandRoutes);
app.use('/api/products', productRoutes);
app.use('/api/orders', orderRoutes);
app.use('/api/reviews', reviewRoutes);
app.use('/api/auth', authRoutes);

app.get('/api/banners', (req, res) => {
  res.json([
    {"id" : 1, "image_url": "https://samsungar.vtexassets.com/assets/vtex.file-manager-graphql/images/d5e315d3-ab8f-46a4-b13a-3d24c5456839___72922747f5367b3f8567058060478202.png"},
    {"id" : 2, "image_url": "https://samsungar.vtexassets.com/assets/vtex.file-manager-graphql/images/1fe03eec-519e-4f4d-a5ac-b3c916faeabc___2cded0e383aee58d49352e9614f8aa6e.png"},
    {"id" : 3, "image_url": "https://samsungar.vtexassets.com/assets/vtex.file-manager-graphql/images/61f85c50-47cb-4a63-a7ce-53df734134e2___297e6638a8668914e0049d758187c675.png"}
  ])
})

app.use(errorHandler);

const PORT = process.env.PORT || 3000;

app.listen(PORT, () => {
  console.log(`Server is running on http://localhost:${PORT}`);
});