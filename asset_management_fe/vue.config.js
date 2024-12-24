const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
})

module.exports = {
  devServer:{
    // host:'localhost',
    port:8000,
    proxy:{
      '/api':{
        target:'http://localhost:8080',
        changeOrigin:true,
        pathRewrite:{
          '^/api':'/asset_management'
        }
      }
    }
  }
}
