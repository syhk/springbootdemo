import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
// ===============================
import AutoImport from 'unplugin-vue-components/vite'
import Components from 'unplugin-auto-import/vite'
import { ElementPlusResolver } from 'unplugin-vue-components/resolvers'
// https://vitejs.dev/config/
export default defineConfig({
  // 配置完跨域后需要重启服务器才有用
  server: {
    // open:true, //启动项目自动弹出浏览器
    host: '127.0.0.1',
    port: 5173, //项目启动端口
    cors: true,
    // 上面这三种参数目前对我来说，不配置也没有任何问题
    proxy: {
      // 这里需要更换，具体要自己的访问路径 eg:http://localhost:8800/admin/getAllAdmin
      // admin 只是一个识别字符，检测接口中是否出现的 '/admin'  这段字符来进行判断是否进行检测转发，实际中应该用正则来匹配，把它设置为空 
      //  就是把我们的项目地址进行代理： eg:  http://localhost:5173/admin/getAllAdmin 变成 http://localhost:8800/admin/getAllAdmin 地址，而后面这个地址才是真正的访问地址
      '/admin': {
        // 配置的是你要访问的跨域的地址
        target: 'http://localhost:8800',
        changeOrigin: true,
        // rewrite: (path) => path.replace(/^\/api/, ""),
      }
    }
  },
  plugins: [vue()
    ,
  AutoImport({
    resolvers: [ElementPlusResolver()],
  }),
  Components({
    resolvers: [ElementPlusResolver()],
  }),
  ],
})

/**
 * 同源策略：是浏览器一项安全策略，浏览器只允许 js 代码请求和当前所在服务器域名，端口，协议相同的数据
 * 接口上的数据
 * JS 采用的是同源策略
 * 当协议，端口，域名其中任意一个不相同时，都会产生跨域问题
 * error: Access-Control-Allow-Origin....
 * 
 */
