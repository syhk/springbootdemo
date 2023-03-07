import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import prismjsPlugin from 'vite-plugin-prismjs'
// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue()
],
  resolve: {
    alias: {
      '@': './src'
    }
  },
  server: {
    port: 5173,
    proxy: {
      '/*': {
        target: 'http://localhost:8800/',
        changeOrigin: true,
      }
    }
  }
})

