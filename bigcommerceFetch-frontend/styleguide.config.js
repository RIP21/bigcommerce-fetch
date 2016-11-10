const path = require('path');
module.exports = {
  title: 'My Great Style Guide',
  components: './src/containers/**/*.js',
  updateWebpackConfig(webpackConfig) {
    // Your source files folder or array of folders, should not include node_modules
    const dir = path.join(__dirname, 'src');
    webpackConfig.module.loaders.push(
      // Babel loader will use your project’s .babelrc
      {test: /\.jsx?$/, include: dir, exclude: /node_modules/, loaders: ['babel']},
      {test: /(\.css|\.scss)$/, include: dir, loaders: ['style', 'css?sourceMap', 'postcss', 'sass?sourceMap']}
    );
    return webpackConfig;
  },
};
