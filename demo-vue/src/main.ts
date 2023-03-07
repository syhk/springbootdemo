import { createApp } from 'vue'
import './style.css'
import App from './App.vue'
import router from "../src/router/index"
import Antd from 'ant-design-vue';
import 'ant-design-vue/dist/antd.css';
import { createPinia } from 'pinia'
import VMdEditor from '@kangc/v-md-editor';
import '@kangc/v-md-editor/lib/style/base-editor.css';
import vuepressTheme from '@kangc/v-md-editor/lib/theme/vuepress.js';
import '@kangc/v-md-editor/lib/theme/style/vuepress.css';
// md 预览依赖导入
import VMdPreview from '@kangc/v-md-editor/lib/preview';
import '@kangc/v-md-editor/lib/style/preview.css';
import githubTheme from '@kangc/v-md-editor/lib/theme/github.js';
import '@kangc/v-md-editor/lib/theme/style/github.css';
// highlightjs
import hljs from 'highlight.js';

// Prism
import Prism from 'prismjs';
// highlight code
import 'prismjs/components/prism-json';
// 引入代码行号
import VueMarkdownEditor from '@kangc/v-md-editor';
import createLineNumbertPlugin from '@kangc/v-md-editor/lib/plugins/line-number/index';

// 支持复制代码
import VMdPreviewHtml from '@kangc/v-md-editor/lib/preview-html';
import createCopyCodePreview from '@kangc/v-md-editor/lib/plugins/copy-code/preview';
import '@kangc/v-md-editor/lib/plugins/copy-code/copy-code.css';

VMdPreviewHtml.use(createCopyCodePreview());

import VueMarkdownEditor from '@kangc/v-md-editor';
import createCopyCodePlugin from '@kangc/v-md-editor/lib/plugins/copy-code/index';
import '@kangc/v-md-editor/lib/plugins/copy-code/copy-code.css';
VueMarkdownEditor.use(createCopyCodePlugin());
VueMarkdownEditor.use(createLineNumbertPlugin());

// 视频播放器
import vue3videoPlay from 'vue3-video-play' // 引入组件
import 'vue3-video-play/dist/style.css' // 引入css




VMdEditor.use(vuepressTheme, {
	Prism,
});
VMdPreview.use(githubTheme, {
	Hljs: hljs,
})
const app = createApp(App)
const pinia = createPinia()

app.use(router)
	.use(Antd)
	.use(pinia)
	.use(vue3videoPlay)
	.use(VMdEditor)
	.use(VMdPreview)

	.mount("#app")

