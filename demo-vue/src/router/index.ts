import { createRouter, RouteRecordRaw, createWebHistory } from 'vue-router'

const routes: Array<RouteRecordRaw> = [
	{
		path: '/',
		name: 'home',
		component: () => import('../components/index.vue')
	},
	{
		path: '/login',
		name: 'login',
		component: () => import('../components/loginbox.vue')
	},
	{
		path: '/test',
		name: 'test',
		component: () => import('../components/element_test.vue')
	},
	{
		path: '/test1',
		name: 'test1',
		component: () => import('../components/nwfbox.vue')
	},
	{
		path: '/blog',
		name: 'blog',
		component: () => import('../components/blogindex.vue')
	},
	{
		path: '/nav',
		name: 'nav',
		component: () => import('../components/test/navtest.vue')
	},
	{
		path: '/ware',
		name: 'ware',
		component: () => import('../components/test/waretest.vue')
	},
	{
		path: '/nav2',
		name: 'nav2',
		component: () => import('../components/test/nav2.vue')
	},
	{
		path: '/nav3',
		name: 'nav3',
		component: () => import('../components/test/nav3.vue')
	}


]
// createWebHashHistory() 路由模式路径带 # 号
// createWebHistory 路由模式路径不带 # 号
// history 模式可以使用 history.state 获取完整的路由信息，后端可以获取完整的路由信
// hash 模式：改变 # 后的路径不会自动刷新页面
const router = createRouter({
	history: createWebHistory(),
	routes
})

export default router


