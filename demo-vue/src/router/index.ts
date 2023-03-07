import { nextTick } from 'vue'
import { createRouter, RouteRecordRaw, createWebHistory } from 'vue-router'
const routes: Array<RouteRecordRaw> = [
	{
		path: '/',
		name: 'home',
		component: () => import('../views/index.vue'),
		meta: {
			title: "首页",
			requiresAuth:true
		}
	},
	{
		path: '/login',
		name: 'login',
		component: () => import('../views/loginbox.vue'),
		meta: {
			title: "登录",
		}
	},
	{
		path: '/reg',
		name: 'reg',
		component: () => import('../components/login.vue'),
		meta: {
			title: "注册",
		}
	},
	{
		path: '/cart',
		name: 'cart',
		component: () => import('../views/cartoon.vue'),
		meta: {
			title: "动漫",
			requiresAuth:true
		}
	},
	{
		path: '/blog',
		name: 'blog',
		component: () => import('../views/blogindex.vue'),
		meta: {
			title: "博客列表",
			requiresAuth:true
		}
	},
	{
		path: '/zx',
		name: 'zx',
		component: () => import('../views/peopleindex.vue'),
		meta: {
			title: '个人中心',
			requiresAuth:true
		}
	},
	// {
	// 	path: '/cart',
	// 	name: 'cart',
	// 	component: () => import('../components/cart.vue')
	// 	meta: {
	// 		title: "404",
	// 		isLogin: false
	// 	}
	// },
	{
		path: '/md',
		name: 'md',
		component: () => import('../views/md.vue'),
		meta: {
			title: "发布博客",
			requiresAuth:true
		}
	},
	{
		path: '/pre',
		name: 'pre',
		component: () => import('../views/preview.vue'),
		meta: {
			title: "博客内容浏览",
			requiresAuth:true
		}
	},
	{
		path: '/manager',
		name: 'manager',
		component: () => import('../views/root/manager.vue'),
		meta: {
			title: "后台管理",
			requiresAuth:true
		}
	},
	{
		path: '/select',
		name: 'select',
		component: () => import('../views/root/index.vue'),
		meta: {
			title: "root 用户选择",
			requiresAuth:true
		}
	},
	// {
	// 	path: '/publog',
	// 	name: 'publog',
	// 	component: () => import('../views/blog/pubblog.vue'),
	// 	meta: {
	// 		title: "公共博客",
	// 		// isLogin: true
	// 	}
	// },
	{
		path: '/:pathMatch(.*)*',
		name: 'NOTFound',
		component: () => import('../views/error.vue'),
		meta: {
			title: "404",
		}
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
// 拦截用户没有登录访问其他页面
router.beforeEach(async (to, from, next) => {
	const isAuthenticated=localStorage.getItem('userInfo') !== null;
	if(to.matched.some(record=> record.meta.requiresAuth))
	{
		// 如果用户未登录则路由到登录页面
		if(!isAuthenticated)
		{
			next({
				path:'/login',
				query:{redirect:to.fullPath}
			});
		}else {
			next();
		}
	}else{
		next();
	}
})
export default router


