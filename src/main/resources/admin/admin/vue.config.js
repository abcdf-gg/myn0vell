// var webpack = require('webpack');
const path = require('path')
function resolve(dir) {
    return path.join(__dirname, dir)
}
function publicPath(){
    if (process.env.NODE_ENV == 'production') {
        return "././";
    } else {
        return "/";
    }
}
// vue.config.js
module.exports = {
    // publicPath:"././",
    publicPath: publicPath(),
    // 国际化配置 使用其它语言，默认情况下中文语言包依旧是被引入的
    configureWebpack: {
        // plugins: [
        //     new webpack.NormalModuleReplacementPlugin(/element-ui[\/\\]lib[\/\\]locale[\/\\]lang[\/\\]zh-CN/, 'element-ui/lib/locale/lang/en')
        // ]
        resolve: {
            alias: {
                '@': resolve('src')
            }
        }
    },
lintOnSave: false,
    devServer: {
        host: "0.0.0.0",
        port: 8081,
        hot: true,
        https: false,
        proxy: {
            '/springbootm3h7l': {
                target: 'http://localhost:8080/springbootm3h7l/',
                changeOrigin: true,
                secure: false,
                pathRewrite: {
                    '^/springbootm3h7l': ''
                }
            }
        }
    },
chainWebpack(config) {
    config.module
      .rule('svg')
      .exclude.add(resolve('src/icons'))
      .end()
    config.module
      .rule('icons')
      .test(/\.svg$/)
      .include.add(resolve('src/icons'))
      .end()
      .use('svg-sprite-loader')
      .loader('svg-sprite-loader')
      .options({
        symbolId: 'icon-[name]'
      })
      .end()
}
}
