module.exports = {
    publicPath: '/game/',
    devServer: {
        proxy: {
            '^/memorygame': {
                target: 'http://localhost:8081',
                ws: true,
                changeOrigin: false
            }
        }
    },
}
