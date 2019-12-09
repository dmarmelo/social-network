module.exports = {
  pluginOptions: {
    quasar: {
      importStrategy: 'kebab',
      rtlSupport: true
    }
  },
  transpileDependencies: [
    'quasar'
  ],
  // Used to proxy all the application requests to a defined URL in development mode
  /*devServer: {
    proxy: 'http://localhost:8080/',
  }*/
}
