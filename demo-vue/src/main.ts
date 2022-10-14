import { createApp } from 'vue'
import './style.css'
import App from './App.vue'
import router from "../src/router/index"
import Antd from 'ant-design-vue';
import 'ant-design-vue/dist/antd.css';

const app = createApp(App)

app.use(router)
	.use(Antd)
	.mount("#app")